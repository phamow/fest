/*
 * Created on Aug 9, 2008
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

import javax.swing.JSlider;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.core.Robot;
import org.fest.swing.testing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.RobotFixture.robotWithNewAwtHierarchy;
import static org.fest.swing.testing.TestGroups.*;

/**
 * Tests for <code>{@link JSliderValueQuery}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test(groups = { GUI, EDT_ACTION })
public class JSliderValueQueryTest {

  private static final int VALUE = 8;

  private Robot robot;
  private MySlider slider;

  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    MyWindow window = MyWindow.createNew();
    slider = window.slider;
    robot.showWindow(window);
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  public void shouldReturnValueOfJSlider() {
    assertThat(JSliderValueQuery.valueOf(slider)).isEqualTo(VALUE);
    assertThat(slider.methodGetValueWasInvoked()).isTrue();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final MySlider slider = new MySlider(6, 10, VALUE);

    static MyWindow createNew() {
      return new MyWindow();
    }

    private MyWindow() {
      super(JSliderValueQueryTest.class);
      addComponents(slider);
    }
  }

  private static class MySlider extends JSlider {
    private static final long serialVersionUID = 1L;

    private boolean methodGetValueInvoked;

    MySlider(int min, int max, int value) {
      super(min, max, value);
    }

    @Override public int getValue() {
      methodGetValueInvoked = true;
      return super.getValue();
    }

    boolean methodGetValueWasInvoked() { return methodGetValueInvoked; }
  }
}