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

package org.springframework.boot.origin;

import java.io.File;

/**
 * 唯一表示项目来源的接口。 例如，从{@link File}加载的项目可能具有由文件名以及行/列号组成的原点。
 * <p>
 * 实现必须提供明智的{@code hashCode()}, {@code equals(...)}和{@code #toString()}实现。
 *
 * @author Madhura Bhave
 * @author Phillip Webb
 * @since 2.0.0
 * @see OriginProvider
 */
public interface Origin {

	/**
	 * 找到对象的{@link Origin}。 检查源对象是否为{@link OriginProvider}，并搜索异常堆栈。
	 * @param source the source object or {@code null}
	 * @return an optional {@link Origin}
	 */
	static Origin from(Object source) {
		if (source instanceof Origin) {
			return (Origin) source;
		}
		Origin origin = null;
		if (source instanceof OriginProvider) {
			origin = ((OriginProvider) source).getOrigin();
		}
		if (origin == null && source instanceof Throwable) {
			return from(((Throwable) source).getCause());
		}
		return origin;
	}

}
