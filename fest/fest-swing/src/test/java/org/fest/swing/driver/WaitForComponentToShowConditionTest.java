/*
 * Created on Jul 17, 2008
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
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.JTextField;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link WaitForComponentToShowCondition}</code>.
 *
 * @author Yvonne Wang
 */
@Test public class WaitForComponentToShowConditionTest {

  private WaitForComponentToShowCondition condition;
  private ComponentStub c;

  @BeforeMethod public void setUp() {
    c = new ComponentStub();
    condition = new WaitForComponentToShowCondition(c);
  }

  public void shouldReturnTrueIfComponentIsShowing() {
    c.showing(true);
    assertThat(condition.test()).isTrue();
  }

  public void shouldReturnFalseIfComponentIsNotShowing() {
    assertThat(condition.test()).isFalse();
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowErrorIfComponentIsNull() {
    new WaitForComponentToShowCondition(null);
  }

  private static class ComponentStub extends JTextField {
    private static final long serialVersionUID = 1L;

    private boolean showing;

    public ComponentStub() {}

    void showing(boolean isShowing) {
      this.showing = isShowing;
    }

    @Override public boolean isShowing() {
      return showing;
    }
  }
}
