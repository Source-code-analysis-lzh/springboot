/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.context.properties.source;

import java.util.function.Predicate;

import org.springframework.boot.origin.OriginTrackedValue;

/**
 * 一个{@link ConfigurationProperty ConfigurationProperties}类型的源。
 *
 * @author Phillip Webb
 * @author Madhura Bhave
 * @since 2.0.0
 * @see ConfigurationPropertyName
 * @see OriginTrackedValue
 * @see #getConfigurationProperty(ConfigurationPropertyName)
 */
@FunctionalInterface
public interface ConfigurationPropertySource {

	/**
	 * 从源返回单个{@link ConfigurationProperty} 如果找不到属性，则返回{@code null}。
	 * @param name the name of the property (must not be {@code null})
	 * @return the associated object or {@code null}.
	 */
	ConfigurationProperty getConfigurationProperty(ConfigurationPropertyName name);

	/**
	 * 返回源是否包含指定名称的任何后代。 如果可以确定答案，则可以返回{@link ConfigurationPropertyState#PRESENT}
	 * 或{@link ConfigurationPropertyState#ABSENT}；如果无法确定确定的答案，则可以返回{@link ConfigurationPropertyState#UNKNOWN}。
	 * @param name the name to check
	 * @return if the source contains any descendants
	 */
	default ConfigurationPropertyState containsDescendantOf(ConfigurationPropertyName name) {
		return ConfigurationPropertyState.UNKNOWN;
	}

	/**
	 * 返回此源的已过滤变体，仅包含与给定{@link Predicate}匹配的名称。
	 * @param filter the filter to match
	 * @return a filtered {@link ConfigurationPropertySource} instance
	 */
	default ConfigurationPropertySource filter(Predicate<ConfigurationPropertyName> filter) {
		return new FilteredConfigurationPropertiesSource(this, filter);
	}

	/**
	 * 返回此源的变体，该变体支持名称别名。
	 * @param aliases a function that returns a stream of aliases for any given name
	 * @return a {@link ConfigurationPropertySource} instance supporting name aliases
	 */
	default ConfigurationPropertySource withAliases(ConfigurationPropertyNameAliases aliases) {
		return new AliasedConfigurationPropertySource(this, aliases);
	}

	/**
	 * 返回实际提供属性的底层源。
	 * @return the underlying property source or {@code null}.
	 */
	default Object getUnderlyingSource() {
		return null;
	}

}
