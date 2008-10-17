/*
 * Created on Jun 10, 2008
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

import javax.swing.JTable;

import org.fest.swing.testing.TableDialogEditDemo;
import org.fest.swing.testing.TestWindow;

/**
 * Understands a frame hosting <code>{@link TableDialogEditDemo}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class TableDialogEditDemoWindow extends TestWindow {

  private static final long serialVersionUID = 1L;

  final JTable table;

  public static TableDialogEditDemoWindow createNew(Class<?> testClass) {
    return new TableDialogEditDemoWindow(testClass);
  }

  private TableDialogEditDemoWindow(Class<?> testClass) {
    super(testClass);
    TableDialogEditDemo newContentPane = new TableDialogEditDemo();
    table = newContentPane.table;
    newContentPane.setOpaque(true); // content panes must be opaque
    setContentPane(newContentPane);
  }
}