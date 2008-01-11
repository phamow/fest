/*
 * Created on Nov 1, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.testng.annotations.Test;

/**
 * Tests for <code>{@link JPanelFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class JPanelFixtureTest extends ComponentFixtureTestCase<JPanel> {

  /** ${@inheritDoc} */
  @Override protected ComponentFixture<JPanel> createFixture() {
    return new JPanelFixture(robot(), "panel");
  }

  /** ${@inheritDoc} */
  @Override protected JPanel createTarget() {
    JPanel panel = new JPanel();
    panel.setName("panel");
    panel.setPreferredSize(new Dimension(100, 100));
    return panel;
  }

  @Test public void justAddedToRunClassAsTest() {}
}