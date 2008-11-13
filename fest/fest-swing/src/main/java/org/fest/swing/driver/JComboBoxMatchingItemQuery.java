/*
 * Created on Nov 13, 2008
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

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Objects.areEqual;

/**
 * Understands lookup of the first item in a <code>{@link JComboBox}</code> whose value matches a given one.
 *
 * @author Alex Ruiz
 */
final class JComboBoxMatchingItemQuery {

  @RunsInEDT
  static int matchingItemIndex(final JComboBox comboBox, final String value, final JComboBoxCellReader cellReader) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() throws Throwable {
        int itemCount = comboBox.getItemCount();
        for (int i = 0; i < itemCount; i++)
          if (areEqual(cellReader.valueAt(comboBox, i), value)) return i;
        return -1;
      }
    });
  }
  
  private JComboBoxMatchingItemQuery() {}
}
