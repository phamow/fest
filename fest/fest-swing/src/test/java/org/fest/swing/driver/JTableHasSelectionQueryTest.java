/*
 * Created on Aug 10, 2008
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

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.core.GuiTask;
import org.fest.swing.core.Robot;
import org.fest.swing.testing.TestTable;
import org.fest.swing.testing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.GuiActionRunner.execute;
import static org.fest.swing.core.RobotFixture.robotWithNewAwtHierarchy;
import static org.fest.swing.driver.JTableClearSelectionTask.clearSelectionOf;
import static org.fest.swing.testing.TestGroups.*;

/**
 * Tests for <code>{@link JTableHasSelectionQuery}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test(groups = { GUI, EDT_ACTION })
public class JTableHasSelectionQueryTest {

  private Robot robot;
  private TestTable table;

  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    MyWindow window = MyWindow.createNew();
    table = window.table;
    robot.showWindow(window);
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  public void shouldReturnFalseIfTableHasNoSelection() {
    clearSelectionOf(table);
    robot.waitForIdle();
    assertThat(JTableHasSelectionQuery.hasSelection(table)).isFalse();
  }

  public void shouldReturnTrueIfTableHasSelection() {
    selectAllIn(table);
    robot.waitForIdle();
    assertThat(JTableHasSelectionQuery.hasSelection(table)).isTrue();
  }

  private static void selectAllIn(final TestTable table) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        table.selectAll();
      }
    });
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    static MyWindow createNew() {
      return new MyWindow();
    }

    final TestTable table = new TestTable(2, 4);

    private MyWindow() {
      super(JTableHasSelectionQueryTest.class);
      addComponents(table);
    }
  }
}