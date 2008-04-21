/*
 * Created on Apr 3, 2008
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
 * 
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.core;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link RobotEventGenerator}</code>.
 *
 * @author Alex Ruiz
 */
public class RobotEventGeneratorTest extends InputEventGeneratorTestCase {

  private Settings settings;
  private RobotEventGenerator generator;
  
  @Override void onSetUp() throws Exception {
    settings = new Settings();
    generator = new RobotEventGenerator(settings);
  }
  
  InputEventGenerator generator() {
    return generator;
  }

  @Test public void shouldAttachRobotToSettings() {
    assertThat(generator.robot()).isSameAs(settings.robot());
    assertThat(settings.eventMode()).isEqualTo(EventMode.ROBOT);
  }
}