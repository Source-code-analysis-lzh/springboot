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

package org.springframework.boot.availability;

/**
 * 应用程序的"Liveness"状态。
 * <p>
 * 当应用程序以正确的内部状态运行时，它被认为是活动的。 "Liveness"失败意味着应用程序的内部状态已损坏，我们无法从中恢复。
 * 因此，平台应重新启动应用程序。
 *
 * @author Brian Clozel
 * @since 2.3.0
 */
public enum LivenessState implements AvailabilityState {

	/**
	 * The application is running and its internal state is correct.
	 */
	CORRECT,

	/**
	 * The application is running but its internal state is broken.
	 */
	BROKEN

}
