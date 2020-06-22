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

package org.springframework.boot.env;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * 允许在刷新应用程序上下文之前自定义应用程序的{@link Environment}。
 *
 * 该实现该接口的处理器在ConfigFileApplicationListener类在接收到
 * ApplicationEnvironmentPreparedEvent事件后调用。而@PropertySource注释
 * 加载的配置文件只能在应用上下文刷新时引入环境中。
 *
 * <p>
 * 必须使用此类的完全限定名称作为键，在{@code META-INF/spring.factories}中注册EnvironmentPostProcessor实现。
 * <p>
 * 鼓励{@code EnvironmentPostProcessor}处理器检测是否已实现Spring的
 * {@link org.springframework.core.Ordered Ordered}接口，或者是否存在
 * {@link org.springframework.core.annotation.Order @Order}注释，
 * 并在调用之前对实例进行相应的排序（如果有）。
 *
 * @author Andy Wilkinson
 * @author Stephane Nicoll
 * @since 1.3.0
 */
@FunctionalInterface
public interface EnvironmentPostProcessor {

	/**
	 * 对给定的{@code environment}进行后处理。
	 * @param environment the environment to post-process
	 * @param application the application to which the environment belongs
	 */
	void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application);

}
