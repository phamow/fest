/*
 * Created on May 21, 2007
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

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import abbot.tester.JTreeLocation;
import abbot.tester.JTreeTester;

import org.fest.swing.ComponentLookupException;
import org.fest.swing.RobotFixture;

/**
 * Understands simulation of user events on a <code>{@link JTree}</code> and verification of the state of such
 * <code>{@link JTree}</code>.
 * 
 * @author Keith Coughtrey
 * @author Alex Ruiz
 */
public class JTreeFixture extends ComponentFixture<JTree> {

  /**
   * Creates a new </code>{@link JTreeFixture}</code>.
   * @param robot performs simulation of user events on a <code>JTree</code>.
   * @param treeName the name of the <code>JTree</code> to find using the given <code>RobotFixture</code>.
   * @throws ComponentLookupException if a matching <code>JTree</code> could not be found.
   */
  public JTreeFixture(RobotFixture robot, String treeName) {
    super(robot, treeName, JTree.class);
  }

  /**
   * Creates a new </code>{@link JTreeFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JTree</code>.
   * @param target the <code>JTree</code> to be managed by this fixture.
   */
  public JTreeFixture(RobotFixture robot, JTree target) {
    super(robot, target);
  }

  /**
   * Simulates a user selecting the tree node at the given row.
   * @param row the index of the row to select.
   * @return this fixture.
   */
  public final JTreeFixture selectRow(int row) {
    treeTester().actionSelectRow(target, new JTreeLocation(row));
    return this;
  }

  /**
   * Simulates a user toggling the open/closed state of the tree node at the given row.
   * @param row the index of the row to select.
   * @return this fixture.
   */
  public final JTreeFixture toggleRow(int row) {
    treeTester().actionToggleRow(target, new JTreeLocation(row));
    return this;
  }

  /**
   * Select the given path, expanding parent nodes if necessary. TreePath must consist of usable String representations
   * that can be used in later comparisons. The default &lt;classname&gt;@&lt;hashcode&gt; returned by
   * <code>{@link Object#toString()}</code> is not usable; if that is all that is available, refer to the row number 
   * instead.
   * @param treePath A path comprising an array of Strings that match the toString()'s of the path nodes
   * @return this fixture.
   */
  public final JTreeFixture selectPath(TreePath treePath) {
    treeTester().actionSelectPath(target, treePath);
    return this;
  }

  private JTreeTester treeTester() {
    return testerCastedTo(JTreeTester.class);
  }

  /**
   * Simulates a user clicking the <code>{@link JTree}</code> managed by this fixture.
   * @return this fixture.
   */
  @Override public final JTreeFixture click() {
    return (JTreeFixture)super.click(); 
  }

  /**
   * Gives input focus to the <code>{@link JTree}</code> managed by this fixture.
   * @return this fixture.
   */
  @Override public final JTreeFixture focus() {
    return (JTreeFixture)super.focus();
  }

  /**
   * Asserts that the <code>{@link JTree}</code> managed by this fixture is visible.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JTree</code> is not visible.
   */
  @Override public final JTreeFixture requireVisible() {
    return (JTreeFixture)super.requireVisible();
  }

  /**
   * Asserts that the <code>{@link JTree}</code> managed by this fixture is not visible.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JTree</code> is visible.
   */
  @Override public final JTreeFixture requireNotVisible() {
    return (JTreeFixture)super.requireNotVisible();
  }

  /**
   * Asserts that the <code>{@link JTree}</code> managed by this fixture is enabled.
   * @return this fixture.
   * @throws AssertionError is the managed <code>JTree</code> is disabled.
   */
  @Override public final JTreeFixture requireEnabled() {
    return (JTreeFixture)super.requireEnabled();
  }
  
  /**
   * Asserts that the <code>{@link JTree}</code> managed by this fixture is disabled.
   * @return this fixture.
   * @throws AssertionError is the managed <code>JTree</code> is enabled.
   */
  @Override public final JTreeFixture requireDisabled() {
    return (JTreeFixture)super.requireDisabled();
  }
}