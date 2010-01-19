/*
 * Created on Feb 25, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.testng.annotations.*;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.LocationUnavailableException;
import org.fest.swing.test.swing.TestWindow;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.BasicRobot.robotWithNewAwtHierarchy;
import static org.fest.swing.driver.JTabbedPaneSelectTabTask.setSelectedTab;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.*;
import static org.fest.swing.test.core.TestGroups.GUI;
import static org.fest.swing.test.task.ComponentSetEnabledTask.disable;
import static org.fest.swing.test.task.ComponentSetVisibleTask.hide;
import static org.fest.util.Arrays.array;
import static org.fest.util.Strings.concat;

/**
 * Tests for <code>{@link JTabbedPaneDriver}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test(groups = GUI)
public class JTabbedPaneDriverTest {

  private Robot robot;
  private JTabbedPaneDriver driver;
  private MyWindow window;
  private JTabbedPane tabbedPane;

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    driver = new JTabbedPaneDriver(robot);
    window = MyWindow.createNew();
    tabbedPane = window.tabbedPane;
    robot.showWindow(window);
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  public void shouldSetTabDirectlyIfLocationOfTabNotFound() {
    setSelectedTab(tabbedPane, 0);
    robot.waitForIdle();
    final JTabbedPaneLocation location = createMock(JTabbedPaneLocation.class);
    final int index = 1;
    driver = new JTabbedPaneDriver(robot, location);
    new EasyMockTemplate(location) {
      protected void expectations() {
        expect(location.pointAt(tabbedPane, index)).andThrow(new LocationUnavailableException("Thrown on purpose"));
      }

      protected void codeToTest() {
        driver.selectTab(tabbedPane, index);
        assertThatSelectedTabIndexIsEqualTo(index);
      }
    }.run();
  }

  @Test(groups = GUI, dataProvider = "indices")
  public void shouldSelectTabWithGivenIndex(int index) {
    driver.selectTab(tabbedPane, index);
    assertThatSelectedTabIndexIsEqualTo(index);
  }

  public void shouldThrowErrorWhenSelectingTabWithGivenIndexInDisabledJTabbedPane() {
    disableTabbedPane();
    try {
      driver.selectTab(tabbedPane, 1);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertActionFailureDueToDisabledComponent(e);
    }
  }
  
  public void shouldThrowErrorWhenSelectingTabWithGivenIndexInNotShowingJTabbedPane() {
    hideWindow();
    try {
      driver.selectTab(tabbedPane, 1);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertActionFailureDueToNotShowingComponent(e);
    }
  }

  private void disableTabbedPane() {
    disable(tabbedPane);
    robot.waitForIdle();
  }
  
  private void hideWindow() {
    hide(window);
    robot.waitForIdle();
  }

  @Test(groups = GUI, dataProvider = "indices")
  public void shouldSetTabWithGivenIndexDirectly(int index) {
    driver.setTabDirectly(tabbedPane, index);
    assertThatSelectedTabIndexIsEqualTo(index);
  }

  @DataProvider(name = "indices")
  public Object[][] tabIndices() {
    return new Object[][] { { 0 }, { 1 } };
  }

  @Test(groups = GUI, dataProvider = "indicesOutOfBounds")
  public void shouldThrowErrorIfIndexOutOfBounds(int index) {
    try {
      driver.selectTab(tabbedPane, index);
      failWhenExpectingException();
    } catch (IndexOutOfBoundsException expected) {
      assertThat(expected.getMessage()).isEqualTo(concat(
        "Index <", index, "> is not within the JTabbedPane bounds of <0> and <1> (inclusive)"));
    }
  }

  @DataProvider(name = "indicesOutOfBounds")
  public Object[][] indicesOutOfBounds() {
    return new Object[][] { { -1 }, { 2 } };
  }

  public void shouldSelectFirstTab() {
    driver.selectTab(tabbedPane, "First");
    assertThatSelectedTabIndexIsEqualTo(0);
  }

  public void shouldSelectSecondTab() {
    driver.selectTab(tabbedPane, "Second");
    assertThatSelectedTabIndexIsEqualTo(1);
  }

  public void shouldThrowErrorWhenSelectingTabWithGivenTitleInDisabledTabbedPane() {
    disableTabbedPane();
    try {
      driver.selectTab(tabbedPane, "Second");
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertActionFailureDueToDisabledComponent(e);
    }
  }

  public void shouldThrowErrorWhenSelectingTabWithGivenTitleInNotShowingTabbedPane() {
    hideWindow();
    try {
      driver.selectTab(tabbedPane, "Second");
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertActionFailureDueToNotShowingComponent(e);
    }
  }

  private void assertThatSelectedTabIndexIsEqualTo(int expected) {
    int selectedIndex = selectedIndexIn(tabbedPane);
    assertThat(selectedIndex).isEqualTo(expected);
  }

  private static int selectedIndexIn(final JTabbedPane tabbedPane) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        return tabbedPane.getSelectedIndex();
      }
    });
  }

  public void shouldReturnTabTitles() {
    assertThat(driver.tabTitles(tabbedPane)).isEqualTo(array("First", "Second"));
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JTabbedPane tabbedPane = new JTabbedPane();

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JTabbedPaneDriverTest.class);
      tabbedPane.addTab("First", new JPanel());
      tabbedPane.addTab("Second", new JPanel());
      add(tabbedPane);
      setPreferredSize(new Dimension(300, 200));
    }
  }
}