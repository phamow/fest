/*
 * Created on Aug 11, 2008
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

import javax.swing.text.JTextComponent;

import org.fest.swing.core.GuiTask;

import static org.fest.swing.core.GuiActionRunner.execute;

/**
 * Understands a task that selects text in a given <code>{@link JTextComponent}</code>. This task is executed in the 
 * event dispatch thread.
 *
 * @author Alex Ruiz
 */
class JTextComponentSelectTextTask {
  
  static void selectTextInRange(final JTextComponent textBox, final int start, final int end) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        textBox.setCaretPosition(start);
        textBox.moveCaretPosition(end);
      }
    });
  }
}