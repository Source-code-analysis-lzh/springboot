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

package org.springframework.boot.context.properties;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指示{@link ConfigurationProperties @ConfigurationProperties}对象中的getter被弃用。
 * 该注释与实际的绑定过程无关，但是由{@code spring-boot-configuration-processor}使用它来添加弃用元数据。
 * <p>
 * This annotation <strong>must</strong> be used on the getter of the deprecated element.
 * 必须在不赞成使用的元素的getter上使用此批注。
 *
 * @author Phillip Webb
 * @since 1.3.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeprecatedConfigurationProperty {

	/**
	 * The reason for the deprecation.
	 * @return the deprecation reason
	 */
	String reason() default "";

	/**
	 * 应该改用的字段（如果有）。
	 * @return the replacement field
	 */
	String replacement() default "";

}
