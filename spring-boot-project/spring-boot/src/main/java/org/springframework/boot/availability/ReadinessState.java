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
 * 应用程序的“就绪”状态。
 * <p>
 * 当应用程序处于运行状态并且愿意接受流量时，它就被认为已准备就绪。 "Readiness"故障意味着应用程序无法接受流量，
 * 并且基础结构应停止将请求路由到该应用程序。
 *
 * @author Brian Clozel
 * @since 2.3.0
 */
public enum ReadinessState implements AvailabilityState {

	/**
	 * The application is ready to receive traffic.
	 */
	ACCEPTING_TRAFFIC,

	/**
	 * The application is not willing to receive traffic.
	 */
	REFUSING_TRAFFIC

}
