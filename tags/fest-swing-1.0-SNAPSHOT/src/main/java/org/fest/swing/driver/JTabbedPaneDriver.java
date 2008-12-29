/*
 * Created on Jan 27, 2008
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

import java.awt.Point;

import javax.swing.JTabbedPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.LocationUnavailableException;
import org.fest.swing.util.Pair;

import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JTabbedPaneSelectTabTask.setSelectedTab;
import static org.fest.swing.driver.JTabbedPaneTabTitlesQuery.tabTitlesOf;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands simulation of user input on a <code>{@link JTabbedPane}</code>. Unlike <code>JTabbedPaneFixture</code>,
 * this driver only focuses on behavior present only in <code>{@link JTabbedPane}</code>s. This class is intended for
 * internal use only.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTabbedPaneDriver extends JComponentDriver {

  private final JTabbedPaneLocation location;

  /**
   * Creates a new </code>{@link JTabbedPaneDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JTabbedPaneDriver(Robot robot) {
    this(robot, new JTabbedPaneLocation());
  }

  /**
   * Creates a new </code>{@link JTabbedPaneDriver}</code>.
   * @param robot the robot to use to simulate user input.
   * @param location knows how to find the location of a tab.
   */
  JTabbedPaneDriver(Robot robot, JTabbedPaneLocation location) {
    super(robot);
    this.location = location;
  }

  /**
   * Returns the titles of all the tabs.
   * @param tabbedPane the target <code>JTabbedPane</code>.
   * @return the titles of all the tabs.
   */
  @RunsInEDT
  public String[] tabTitles(JTabbedPane tabbedPane) {
    return tabTitlesOf(tabbedPane);
  }

  /**
   * Simulates a user selecting the tab containing the given title.
   * @param tabbedPane the target <code>JTabbedPane</code>.
   * @param title the given text to match.
   * @throws IllegalStateException if the <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if the <code>JTabbedPane</code> is not showing on the screen.
   * @throws LocationUnavailableException if a tab matching the given title could not be found.
   */
  @RunsInEDT
  public void selectTab(JTabbedPane tabbedPane, String title) {
    Pair<Integer, Point> tabToSelectInfo = tabToSelectInfo(location, tabbedPane, title);
    Point target = tabToSelectInfo.ii;
    if (target != null) {
      click(tabbedPane, target);
      return;
    }
    setTabDirectly(tabbedPane, tabToSelectInfo.i);
  }

  @RunsInEDT
  private static Pair<Integer, Point> tabToSelectInfo(final JTabbedPaneLocation location, final JTabbedPane tabbedPane, final String title) {
    return execute(new GuiQuery<Pair<Integer, Point>>() {
      protected Pair<Integer, Point> executeInEDT() {
        validateIsEnabledAndShowing(tabbedPane);
        int index = location.indexOf(tabbedPane, title);
        Point point = null;
        try {
          point = location.pointAt(tabbedPane, index);
        } catch (LocationUnavailableException e) {}
        return new Pair<Integer, Point>(index, point);
      }
    });
  }

  /**
   * Simulates a user selecting the tab located at the given index.
   * @param tabbedPane the target <code>JTabbedPane</code>.
   * @param index the index of the tab to select.
   * @throws IllegalStateException if the <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if the <code>JTabbedPane</code> is not showing on the screen.
   * @throws IllegalArgumentException if the given index is not within the <code>JTabbedPane</code> bounds.
   */
  public void selectTab(JTabbedPane tabbedPane, int index) {
    try {
      Point p = pointAt(location, tabbedPane, index);
      click(tabbedPane, p);
    } catch (LocationUnavailableException e) {
      setTabDirectly(tabbedPane, index);
    }
  }

  @RunsInEDT
  private static Point pointAt(final JTabbedPaneLocation location, final JTabbedPane tabbedPane, final int index) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        validateIsEnabledAndShowing(tabbedPane);
        return location.pointAt(tabbedPane, index);
      }
    });
  }

  @RunsInEDT
  void setTabDirectly(JTabbedPane tabbedPane, int index) {
    setSelectedTab(tabbedPane, index);
    robot.waitForIdle();
  }
}
