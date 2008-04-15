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
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.fest.swing.core.Robot;
import org.fest.swing.testing.ClickRecorder;
import org.fest.swing.testing.TestFrame;
import org.fest.swing.testing.TestTable;

import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.RIGHT_BUTTON;
import static org.fest.swing.core.RobotFixture.robotWithNewAwtHierarchy;
import static org.fest.swing.testing.ClickRecorder.attachTo;
import static org.fest.swing.testing.TestGroups.GUI;
import static org.fest.swing.testing.TestTable.*;

/**
 * Tests for <code>{@link JTableDriver}</code>.
 *
 * @author Alex Ruiz
 */
@Test(groups = GUI)
public class JTableDriverTest {

  private Robot robot;
  private TestTable dragTable;
  private TestTable dropTable;
  private JTableDriver driver;

  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    driver = new JTableDriver(robot);
    MyFrame frame = new MyFrame();
    dragTable = frame.dragTable;
    dropTable = frame.dropTable;
    robot.showWindow(frame);
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  @Test(dataProvider = "cells")
  public void shouldSelectCell(int row, int column) {
    driver.selectCell(dragTable, new TableCell(row, column));
    assertThat(dragTable.isCellSelected(row, column));
  }

  @Test public void shouldSelectCells() {
    dragTable.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
    driver.selectCells(dragTable, new TableCell(0, 0), new TableCell(2, 0));
    assertThat(dragTable.isCellSelected(0, 0));
    assertThat(dragTable.isCellSelected(2, 0));
  }
  
  @Test(dependsOnMethods = "shouldSelectCell")
  public void shouldNotSelectCellIfAlreadySelected() {
    driver.selectCell(dragTable, new TableCell(0, 0));
    assertThat(dragTable.isCellSelected(0, 0));
    driver.selectCell(dragTable, new TableCell(0, 0));
    assertThat(dragTable.isCellSelected(0, 0));
  }
  
  @Test(dataProvider = "cells")
  public void shouldReturnValueOfGivenRowAndColumn(int row, int column) {
    Object value = driver.value(dragTable, row, column);
    assertThat(value).isEqualTo(createCellTextUsing(row, column));
  }

  @Test(dataProvider = "cells")
  public void shouldReturnValueOfGivenCell(int row, int column) {
    Object value = driver.value(dragTable, new TableCell(row, column));
    assertThat(value).isEqualTo(createCellTextUsing(row, column));
  }

  @Test(dependsOnMethods = "shouldSelectCell", dataProvider = "cells")
  public void shouldReturnValueOfSelectedCell(int row, int column) {
    driver.selectCell(dragTable, new TableCell(row, column));
    Object value = driver.selectionValue(dragTable);
    assertThat(value).isEqualTo(createCellTextUsing(row, column));
  }

  @DataProvider(name = "cells") public Object[][] cells() {
    return new Object[][] { { 6, 5 }, { 0, 0 }, { 8, 3 }, { 5, 2 } };
  }

  @Test public void shouldReturnNullAsSelectionContentIfNoSelectedCell() {
    assertThat(dragTable.getSelectedRowCount()).isZero();
    assertThat(driver.selectionValue(dragTable)).isNull();
  }

  @Test public void shouldDragAndDrop() throws Exception {
    int dragRowCount = dragTable.getRowCount();
    int dropRowCount = dropTable.getRowCount();
    driver.drag(dragTable, new TableCell(3, 0));
    driver.drop(dropTable, new TableCell(1, 0));
    assertThat(dragTable.getRowCount()).isEqualTo(dragRowCount - 1);
    assertThat(dragTable.getValueAt(3, 0)).isEqualTo(createCellTextUsing(4, 0));
    assertThat(dropTable.getRowCount()).isEqualTo(dropRowCount + 1);
    assertThat(dropTable.getValueAt(2, 0)).isEqualTo(createCellTextUsing(3, 0));
  }

  @Test public void shouldShowPopupMenuAtCell() {
    JPopupMenu popupMenu = new JPopupMenu();
    popupMenu.add(new JMenuItem("Leia"));
    dragTable.setComponentPopupMenu(popupMenu);
    ClickRecorder recorder = attachTo(dragTable);
    driver.showPopupMenuAt(dragTable, new TableCell(0, 1));
    recorder.clicked(RIGHT_BUTTON).timesClicked(1);
    Point pointClicked = recorder.pointClicked();
    Point pointAtCell = new JTableLocation().pointAt(dragTable, 0, 1);
    assertThat(pointClicked).isEqualTo(pointAtCell);
  }
  
  private static class MyFrame extends TestFrame {
    private static final long serialVersionUID = 1L;

    private static final Dimension TABLE_SIZE = new Dimension(400, 100);

    private static final int COLUMN_COUNT = 6;
    private static final int ROW_COUNT = 10;

    final TestTable dragTable = new TestTable(ROW_COUNT, COLUMN_COUNT);
    final TestTable dropTable = new TestTable(dropTableData(2, COLUMN_COUNT), columnNames(COLUMN_COUNT));

    private static Object[][] dropTableData(int rowCount, int columnCount) {
      Object[][] data = new Object[rowCount][columnCount];
      for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < columnCount; j++)
          data[i][j] = createCellTextUsing(ROW_COUNT + i, j);
      return data;
    }

    MyFrame() {
      super(JTableDriverTest.class);
      add(decorate(dragTable));
      add(decorate(dropTable));
      setPreferredSize(new Dimension(600, 400));
    }

    private Component decorate(JTable table) {
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setPreferredSize(TABLE_SIZE);
      return scrollPane;
    }
  }

  private static class TableCell extends JTableCell {
    public TableCell(int row, int column) {
      super(row, column);
    }
  }
}
