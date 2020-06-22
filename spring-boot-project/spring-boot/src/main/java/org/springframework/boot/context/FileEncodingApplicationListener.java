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

package org.springframework.boot.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 如果系统文件编码与环境中设置的期望值不匹配，则{@link ApplicationListener}停止应用程序启动。
 * 默认情况下不起作用，但是如果将{@code spring.mandatory_file_encoding}（或它的某些camelCase或UPPERCASE变体）
 * 设置为字符编码的名称（例如"UTF-8"），则此初始化器在{@code file.encoding}系统属性不等于它时将引发异常。
 *
 * 当系统属性file.encoding与配置的spring强制编码属性不一致时，打印出错信息，并终止程序的启动过程。
 *
 * <p>系统属性{@code file.encoding}通常由JVM设置，以响应{@code LANG}或{@code LC_ALL}环境变量。
 * 它（与其他依赖于这些环境变量的依赖于平台的变量一起）用于编码JVM参数以及文件名和路径。
 * 在大多数情况下，您可以在命令行上覆盖文件编码System属性（具有标准JVM功能），
 * 但也可以考虑将{@code LANG}环境变量设置为显式的字符编码值（例如"en_GB.UTF-8"）。
 *
 * @author Dave Syer
 * @author Madhura Bhave
 * @since 1.0.0
 */
public class FileEncodingApplicationListener
		implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

	private static final Log logger = LogFactory.getLog(FileEncodingApplicationListener.class);

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		ConfigurableEnvironment environment = event.getEnvironment();
		String desired = environment.getProperty("spring.mandatory-file-encoding");
		if (desired == null) {
			return;
		}
		String encoding = System.getProperty("file.encoding");
		if (encoding != null && !desired.equalsIgnoreCase(encoding)) {
			if (logger.isErrorEnabled()) {
				logger.error("System property 'file.encoding' is currently '" + encoding + "'. It should be '" + desired
						+ "' (as defined in 'spring.mandatoryFileEncoding').");
				logger.error("Environment variable LANG is '" + System.getenv("LANG")
						+ "'. You could use a locale setting that matches encoding='" + desired + "'.");
				logger.error("Environment variable LC_ALL is '" + System.getenv("LC_ALL")
						+ "'. You could use a locale setting that matches encoding='" + desired + "'.");
			}
			throw new IllegalStateException("The Java Virtual Machine has not been configured to use the "
					+ "desired default character encoding (" + desired + ").");
		}
	}

}
