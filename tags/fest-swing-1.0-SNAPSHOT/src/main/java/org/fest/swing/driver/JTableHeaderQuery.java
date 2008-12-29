package org.fest.swing.driver;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the <code>{@link JTableHeader}</code> in
 * a <code>{@link JTable}</code>.
 * @see JTable#getTableHeader()
 *
 * @author Alex Ruiz
 */
final class JTableHeaderQuery {

  @RunsInEDT
  static JTableHeader tableHeader(final JTable table) {
    return execute(new GuiQuery<JTableHeader>() {
      protected JTableHeader executeInEDT() {
        return table.getTableHeader();
      }
    });
  }

  private JTableHeaderQuery() {}
}