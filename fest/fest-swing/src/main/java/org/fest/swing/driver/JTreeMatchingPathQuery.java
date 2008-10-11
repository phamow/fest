package org.fest.swing.driver;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.core.GuiQuery;

import static org.fest.swing.core.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that finds a path in a <code>{@link JTree}</code> 
 * that matches a given <code>String</code>.
 *
 * @author Alex Ruiz
 * 
 * @see JTreePathFinder
 */
final class JTreeMatchingPathQuery {
  
  static TreePath matchingPathFor(final JTree tree, final String path, final JTreePathFinder pathFinder) {
    return execute(new GuiQuery<TreePath>() {
      protected TreePath executeInEDT() {
        return pathFinder.findMatchingPath(tree, path);
      }
    });
  }
  
  private JTreeMatchingPathQuery() {}
}