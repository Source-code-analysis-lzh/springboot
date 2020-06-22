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

package org.springframework.boot.web.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * 用于以编程方式配置Servlet 3.0+{@link ServletContext context}的接口。
 * 与{@link WebApplicationInitializer}不同，{@link SpringServletContainerInitializer}不会检测到实现此接口的类
 * ，因此Servlet容器不会自动使用它们启动。
 * <p>
 * 该接口旨在以类似于{@link ServletContainerInitializer}的方式工作，但是其生命周期由Spring而不是Servlet容器管理。
 * <p>
 * For configuration examples see {@link WebApplicationInitializer}.
 *
 * @author Phillip Webb
 * @since 1.4.0
 * @see WebApplicationInitializer
 */
@FunctionalInterface
public interface ServletContextInitializer {

	/**
	 * 使用初始化所需的所有servlets, filters, listeners,context-params 和 attributes来配置给定的ServletContext。
	 * @param servletContext the {@code ServletContext} to initialize
	 * @throws ServletException if any call against the given {@code ServletContext}
	 * throws a {@code ServletException}
	 */
	void onStartup(ServletContext servletContext) throws ServletException;

}
