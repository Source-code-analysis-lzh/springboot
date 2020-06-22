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

package org.springframework.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * {@link SpringApplication} {@code run}方法的侦听器。 {@link SpringApplicationRunListener}s
 * 通过{@link SpringFactoriesLoader}加载，并声明一个公共构造函数，
 * 该构造函数接受{@link SpringApplication}实例和参数的{@code String[]}。
 * 每次运行都会创建一个新的{@link SpringApplicationRunListener}实例。
 *
 * @author Phillip Webb
 * @author Dave Syer
 * @author Andy Wilkinson
 * @since 1.0.0
 */
public interface SpringApplicationRunListener {

	/**
	 * 在首次启动run方法时立即调用。 可用于非常早的初始化。
	 */
	default void starting() {
	}

	/**
	 * 一旦环境准备好但在{@link ApplicationContext}创建之前调用。
	 * @param environment the environment
	 */
	default void environmentPrepared(ConfigurableEnvironment environment) {
	}

	/**
	 * 一旦创建并准备了{@link ApplicationContext}，但在加载源之前调用。
	 * @param context the application context
	 */
	default void contextPrepared(ConfigurableApplicationContext context) {
	}

	/**
	 * 一旦应用程序上下文已加载但在刷新之前调用。
	 * @param context the application context
	 */
	default void contextLoaded(ConfigurableApplicationContext context) {
	}

	/**
	 * 上下文已刷新，应用程序已启动，但尚未调用{@link CommandLineRunner CommandLineRunners}
	 * 和{@link ApplicationRunner ApplicationRunners}。
	 * @param context the application context.
	 * @since 2.0.0
	 */
	default void started(ConfigurableApplicationContext context) {
	}

	/**
	 * 在刷新应用程序上下文并且已调用所有{@link CommandLineRunner CommandLineRunners}
	 * 和{@link ApplicationRunner ApplicationRunners}之前，在run方法完成之前立即调用。
	 * @param context the application context.
	 * @since 2.0.0
	 */
	default void running(ConfigurableApplicationContext context) {
	}

	/**
	 * 运行应用程序时发生故障时调用。
	 * @param context the application context or {@code null} if a failure occurred before
	 * the context was created
	 * @param exception the failure
	 * @since 2.0.0
	 */
	default void failed(ConfigurableApplicationContext context, Throwable exception) {
	}

}
