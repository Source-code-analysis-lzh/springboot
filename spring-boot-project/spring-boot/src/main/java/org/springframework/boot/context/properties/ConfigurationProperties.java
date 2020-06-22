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

import org.springframework.core.annotation.AliasFor;

/**
 * 外部化配置的注释。 如果要绑定和验证某些外部属性（例如，来自.properties文件），
 * 请将其添加到类定义或{@code @Configuration}类中的{@code @Bean}方法。
 * <p>
 * 绑定可以通过在带注释的类上调用setter来执行，或者，如果正在使用
 * {@link ConstructorBinding @ConstructorBinding}，则可以通过绑定到构造函数参数来执行。
 * <p>
 * 请注意，与{@code @Value}相反，由于属性值是外部化的，因此不计算SpEL表达式。
 *
 * @author Dave Syer
 * @since 1.0.0
 * @see ConfigurationPropertiesScan
 * @see ConstructorBinding
 * @see ConfigurationPropertiesBindingPostProcessor
 * @see EnableConfigurationProperties
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigurationProperties {

	/**
	 * 绑定到该对象的属性的有效前缀。 {@link #prefix()}的同义词。
	 * 有效前缀由一个或多个用点号分隔的单词（例如{@code "acme.system.feature"}）定义。
	 * @return the prefix of the properties to bind
	 */
	@AliasFor("prefix")
	String value() default "";

	/**
	 * 绑定到该对象的属性的有效前缀。 {@link #value()}的同义词。
	 * 有效前缀由一个或多个用点号分隔的单词（例如{@code "acme.system.feature"}）定义。
	 * @return the prefix of the properties to bind
	 */
	@AliasFor("value")
	String prefix() default "";

	/**
	 * Flag to indicate that when binding to this object invalid fields should be ignored.
	 * Invalid means invalid according to the binder that is used, and usually this means
	 * fields of the wrong type (or that cannot be coerced into the correct type).
	 * 指示当绑定到该对象无效字段时应该忽略的标志。
	 * @return the flag value (default false)
	 */
	boolean ignoreInvalidFields() default false;

	/**
	 * Flag to indicate that when binding to this object unknown fields should be ignored.
	 * An unknown field could be a sign of a mistake in the Properties.
	 * 指示绑定到此对象未知字段时应忽略未知字段的标志。
	 * @return the flag value (default true)
	 */
	boolean ignoreUnknownFields() default true;

}
