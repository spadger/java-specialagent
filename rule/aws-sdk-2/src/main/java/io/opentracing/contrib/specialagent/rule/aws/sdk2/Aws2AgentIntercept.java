/* Copyright 2019 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.opentracing.contrib.specialagent.rule.aws.sdk2;

import java.util.function.Consumer;

import software.amazon.awssdk.core.client.builder.SdkClientBuilder;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration.Builder;

public class Aws2AgentIntercept {
  public static void enter(final Object thiz) {
    final SdkClientBuilder<?,?> builder = (SdkClientBuilder<?,?>)thiz;
    builder.overrideConfiguration(new Consumer<Builder>() {
      @Override
      public void accept(final Builder builder) {
        builder.addExecutionInterceptor(new TracingExecutionInterceptor());
      }
    });
  }
}