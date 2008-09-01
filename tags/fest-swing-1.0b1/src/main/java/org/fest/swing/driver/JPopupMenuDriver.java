/*
 * Created on Jan 31, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands simulation of user input on a <code>{@link JPopupMenu}</code>. Unlike <code>JPopupMenuFixture</code>,
 * this driver only focuses on behavior present only in <code>{@link JPopupMenu}</code>s. This class is intended for
 * internal use only.
 *
 * @author Yvonne Wang
 */
public class JPopupMenuDriver extends JComponentDriver {

  /**
   * Creates a new </code>{@link JPopupMenuDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JPopupMenuDriver(Robot robot) {
    super(robot);
  }

  /**
   * Returns the contents of the pop-up menu as a <code>String</code> array.
   * @param popupMenu the target <code>JPopupMenu</code>.
   * @return the contents of the pop-up menu as a <code>String</code> array.
   */
  public String[] menuLabelsOf(JPopupMenu popupMenu) {
    MenuElement[] subElements = popupMenu.getSubElements();
    String[] result = new String[subElements.length];
    for (int i = 0; i < subElements.length; i++) result[i] = asString(subElements[i]);
    return result;
  }

  static String asString(MenuElement e) {
    Component c = e.getComponent();
    if (c instanceof JMenuItem) return ((JMenuItem) c).getText();
    return "-";
  }

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in the <code>{@link Container}</code>, which name matches
   * the specified one.
   * @param popupMenu the target <code>JPopupMenu</code>.
   * @param name the name to match.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> having a matching name is found.
   */
  public JMenuItem menuItem(JPopupMenu popupMenu, String name) {
    return robot.finder().findByName(popupMenu, name, JMenuItem.class);
  }

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in the <code>{@link Container}</code>, that matches the
   * specified search criteria.
   * @param popupMenu the target <code>JPopupMenu</code>.
   * @param matcher contains the search criteria for finding a <code>JMenuItem</code>.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> that matches the given search criteria could not be
   *         found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> that matches the given search criteria
   *         is found.
   */
  public JMenuItem menuItem(JPopupMenu popupMenu, GenericTypeMatcher<? extends JMenuItem> matcher) {
    return robot.finder().find(popupMenu, matcher);
  }
}