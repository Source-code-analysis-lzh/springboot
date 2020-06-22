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

package org.springframework.boot.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;

/**
 * {@link EnvironmentPostProcessor}启用Reactor调试代理（如果可用）。
 * <p>
 * 除非{@code "spring.reactor.debug-agent.enabled"}配置属性设置为false，否则默认情况下将启用调试代理。
 * 我们在这里使用{@link EnvironmentPostProcessor}而不是自动配置类来在启动过程中尽快启用代理。
 *
 * @author Brian Clozel
 * @since 2.2.0
 */
public class DebugAgentEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

	private static final String REACTOR_DEBUGAGENT_CLASS = "reactor.tools.agent.ReactorDebugAgent";

	private static final String DEBUGAGENT_ENABLED_CONFIG_KEY = "spring.reactor.debug-agent.enabled";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		if (ClassUtils.isPresent(REACTOR_DEBUGAGENT_CLASS, null)) {
			Boolean agentEnabled = environment.getProperty(DEBUGAGENT_ENABLED_CONFIG_KEY, Boolean.class);
			if (agentEnabled != Boolean.FALSE) {
				try {
					Class<?> debugAgent = Class.forName(REACTOR_DEBUGAGENT_CLASS);
					debugAgent.getMethod("init").invoke(null);
				}
				catch (Exception ex) {
					throw new RuntimeException("Failed to init Reactor's debug agent");
				}
			}
		}
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
