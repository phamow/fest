/*
 * Created on Sep 8, 2008
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
package org.fest.swing.task;

import javax.swing.JTree;

import org.fest.swing.core.Condition;
import org.fest.swing.core.GuiTask;
import org.fest.util.Strings;

import static java.lang.String.valueOf;

import static org.fest.swing.core.GuiActionRunner.execute;

/**
 * Understands a task that selects a single row in a <code>{@link JTree}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeSelectRowTask {

  public static void selectRow(final JTree tree, final int row) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        tree.setSelectionRow(row);
      }
    }, new Condition(Strings.concat("JTree's selection row is ", valueOf(row))) {
      public boolean test() {
        int[] selectionRows = tree.getSelectionRows();
        return selectionRows != null && selectionRows.length == 1 && selectionRows[0] == row;
      }
    });
  }

  private JTreeSelectRowTask() {}
}