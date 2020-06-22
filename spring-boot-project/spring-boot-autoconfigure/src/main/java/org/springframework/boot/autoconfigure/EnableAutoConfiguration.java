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

package org.springframework.boot.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * 启用Spring Application Context的自动配置，尝试猜测和配置您可能需要的bean。
 * 通常根据您的类路径和定义的bean来应用自动配置类。 例如，如果您在类路径上具有{@code tomcat-embedded.jar}，
 * 则可能需要{@link TomcatServletWebServerFactory}（除非已定义自己的{@link ServletWebServerFactory} bean）。
 * <p>
 * 当使用{@link SpringBootApplication @SpringBootApplication}时，将自动启用上下文的自动配置，
 * 因此添加此注释不会产生任何额外影响。
 * <p>
 * 自动配置会尝试尽可能智能化，并且在您定义更多自己的配置时会自动退出。 您始终可以手动{@link #exclude()}
 * 任何您不想应用的配置（如果您无权访问它们，请使用{@link #excludeName()}）。
 * 您也可以通过{@code spring.autoconfigure.exclude}属性排除它们。 自动配置始终在注册用户定义的bean之后应用。
 * <p>
 * 通常通过{@code @SpringBootApplication}用{@code @EnableAutoConfiguration}注释的类的程序包具有特定的意义，
 * 通常被用作“默认值”。 例如，在扫描@Entity类时将使用它。 通常建议您将{@code @EnableAutoConfiguration}
 * （如果您未使用{@code @SpringBootApplication}）放在根包中，以便可以搜索所有子包和类。
 * <p>
 * 自动配置类是常规的Spring {@link Configuration @Configuration} bean。
 * 它们使用{@link SpringFactoriesLoader}机制定位（针对此类）。 通常，自动配置bean是
 * {@link Conditional @Conditional} Bean（最经常使用{@link ConditionalOnClass @ConditionalOnClass}
 * 和{@link ConditionalOnMissingBean @ConditionalOnMissingBean} 注释）。
 *
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 1.0.0
 * @see ConditionalOnBean
 * @see ConditionalOnMissingBean
 * @see ConditionalOnClass
 * @see AutoConfigureAfter
 * @see SpringBootApplication
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

	/**
	 * 排除特定的自动配置类，使其永远不会应用。
	 * @return the classes to exclude
	 */
	Class<?>[] exclude() default {};

	/**
	 * 排除特定的自动配置类名称，以使它们永远不会应用。
	 * @return the class names to exclude
	 * @since 1.3.0
	 */
	String[] excludeName() default {};

}
