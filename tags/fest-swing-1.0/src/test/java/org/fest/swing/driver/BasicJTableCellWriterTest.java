/*
 * Created on Jun 8, 2008
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

import org.testng.annotations.Test;

import org.fest.swing.cell.JTableCellWriter;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.TestGroups.GUI;

/**
 * Tests for <code>{@link BasicJTableCellWriter}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@Test(groups = GUI)
public class BasicJTableCellWriterTest extends JTableCellWriterTestCase {

  protected JTableCellWriter createWriter() {
    return new BasicJTableCellWriter(robot());
  }

  public void shouldSelectItemInComboBoxEditor() {
    writer().enterValue(table(), 0, 2, "Pool");
    assertThat(valueAt(0, 2)).isEqualTo("Pool");
  }

  public void shouldSelectItemInCheckBoxEditor() {
    int row = 0;
    int col = 4;
    writer().enterValue(table(), row, col, "false");
    assertThat(valueAt(row,col)).isEqualTo(false);
    writer().enterValue(table(), row, col, "true");
    assertThat(valueAt(row,col)).isEqualTo(true);
    writer().enterValue(table(), row, col, "false");
    assertThat(valueAt(row,col)).isEqualTo(false);
  }

  public void shouldEnterTextInTextComponentEditor() {
    writer().enterValue(table(), 4, 3, "8");
    assertThat(valueAt(4, 3)).isEqualTo(8);
  }
}
