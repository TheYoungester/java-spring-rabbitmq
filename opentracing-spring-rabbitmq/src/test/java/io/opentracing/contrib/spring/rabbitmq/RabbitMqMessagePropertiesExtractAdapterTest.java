/**
 * Copyright 2017-2020 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.opentracing.contrib.spring.rabbitmq;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.junit.Test;

/**
 * @author Gilles Robert
 */
public class RabbitMqMessagePropertiesExtractAdapterTest {

  @Test
  public void testIterator() {
    // given
    String key = "myKey";
    String value = "myValue";
    Map<String, String> map = Collections.singletonMap(key, value);
    Map<String, Object> headers = Collections.singletonMap(key, value);
    RabbitMqMessagePropertiesExtractAdapter adapter = new RabbitMqMessagePropertiesExtractAdapter(headers);

    // when
    final Iterator<Map.Entry<String, String>> iterator = adapter.iterator();

    // then
    assertThat(iterator).containsAll(map.entrySet());
  }

  @Test
  public void testIterator_whenNullValue() {
    // given
    Map<String, Object> headers = Collections.singletonMap("myKey", null);
    RabbitMqMessagePropertiesExtractAdapter adapter = new RabbitMqMessagePropertiesExtractAdapter(headers);

    // when
    final Iterator<Map.Entry<String, String>> iterator = adapter.iterator();

    // then
    assertThat(iterator).doesNotContainNull();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testPut() {
    // given
    String key = "myKey";
    String value = "myValue";
    Map<String, Object> headers = Collections.singletonMap(key, value);
    RabbitMqMessagePropertiesExtractAdapter adapter = new RabbitMqMessagePropertiesExtractAdapter(headers);

    // when
    adapter.put(key, value);

    // then exception
  }
}
