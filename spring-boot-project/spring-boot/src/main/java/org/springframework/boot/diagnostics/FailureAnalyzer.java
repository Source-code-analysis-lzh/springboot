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

package org.springframework.boot.diagnostics;

/**
 * {@code FailureAnalyzer}用于分析故障并提供可以显示给用户的诊断信息。
 *
 * @author Andy Wilkinson
 * @since 1.4.0
 */
@FunctionalInterface
public interface FailureAnalyzer {

	/**
	 * 返回对给定的{@code failure}的分析，如果无法进行分析，则返回{@code null}。
	 * @param failure the failure
	 * @return the analysis or {@code null}
	 */
	FailureAnalysis analyze(Throwable failure);

}
