/*
 * Created on Jul 26, 2008
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
package org.fest.swing.query;

import javax.swing.JLabel;

import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the text of a <code>{@link JLabel}</code>.
 * @see JLabel#getText()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class JLabelTextQuery {

  /**
   * Returns the text of the given <code>{@link JLabel}</code>. This action is executed in the event dispatch thread.
   * @param label the given <code>JLabel</code>.
   * @return the text of the given <code>JLabel</code>.
   * @see JLabel#getText()
   */
  public static String textOf(final JLabel label) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return label.getText();
      }
    });
  }

  private JLabelTextQuery() {}
}