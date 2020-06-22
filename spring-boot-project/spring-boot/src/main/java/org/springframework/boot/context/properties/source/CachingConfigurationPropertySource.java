/*
 * Copyright 2012-2020 the original author or authors.
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

/**
 * Interface used to indicate that a {@link ConfigurationPropertySource} supports
 * {@link ConfigurationPropertyCaching}.
 * 用于指示{@link ConfigurationPropertySource}支持{@link ConfigurationPropertyCaching}的接口。
 *
 * @author Phillip Webb
 */
interface CachingConfigurationPropertySource {

	/**
	 * 为此源返回{@link ConfigurationPropertyCaching}。
	 * @return source caching
	 */
	ConfigurationPropertyCaching getCaching();

	/**
	 * 查找给定源的{@link ConfigurationPropertyCaching}。
	 * @param source the configuration property source
	 * @return a {@link ConfigurationPropertyCaching} instance or {@code null} if the
	 * source does not support caching.
	 */
	static ConfigurationPropertyCaching find(ConfigurationPropertySource source) {
		if (source instanceof CachingConfigurationPropertySource) {
			return ((CachingConfigurationPropertySource) source).getCaching();
		}
		return null;
	}

}
