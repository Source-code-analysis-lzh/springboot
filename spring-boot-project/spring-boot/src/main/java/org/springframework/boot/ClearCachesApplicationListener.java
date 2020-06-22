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

import java.lang.reflect.Method;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.ReflectionUtils;

/**
 * 一旦加载上下文后，{@link ApplicationListener}清理缓存。
 *
 * @author Phillip Webb
 */
class ClearCachesApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	// 监听上下文刷新事件
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ReflectionUtils.clearCache(); // 清空ReflectionUtils中缓存
		clearClassLoaderCaches(Thread.currentThread().getContextClassLoader());
	}

	private void clearClassLoaderCaches(ClassLoader classLoader) {
		if (classLoader == null) {
			return;
		}
		try {
			Method clearCacheMethod = classLoader.getClass().getDeclaredMethod("clearCache");
			clearCacheMethod.invoke(classLoader);
		}
		catch (Exception ex) {
			// Ignore
		}
		clearClassLoaderCaches(classLoader.getParent()); // 递归清空
	}

}
