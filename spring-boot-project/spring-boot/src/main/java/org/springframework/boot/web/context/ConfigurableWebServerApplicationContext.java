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

package org.springframework.boot.web.context;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * SPI接口将由大多数（如果不是全部）{@link WebServerApplicationContext Web服务器应用程序上下文}实现。
 * 除了{WebServerApplicationContext}接口中的方法之外，还提供用于配置上下文的工具。
 *
 * @author Phillip Webb
 * @since 2.0.0
 */
public interface ConfigurableWebServerApplicationContext
		extends ConfigurableApplicationContext, WebServerApplicationContext {

	/**
	 * 设置上下文的服务器名称空间。
	 * @param serverNamespace the server namespace
	 * @see #getServerNamespace()
	 */
	void setServerNamespace(String serverNamespace);

}
