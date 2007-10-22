/*
 * Created on Sep 11, 2007
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
package org.fest.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Understands the base window for all GUI tests.
 *
 * @author Alex Ruiz
 */
public class TestFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private final String typeName;

  public TestFrame(Class testClass) {
    typeName = testClass.getSimpleName();
  }

  public void beVisible() {
    beVisible(new Dimension(400, 200));
  }
  
  public void beVisible(final Dimension size) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          beforeShown();
          setLayout(new FlowLayout());
          setTitle(typeName);
          lookAndFeel();
          setPreferredSize(size);
          pack();
          setVisible(true);
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("Unable to show frame", e);
    }
  }
  
  protected void beforeShown() {}

  protected void lookAndFeel() {
    lookNative();
  }
  
  private void lookNative() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ignored) {}
  }
  
  public void beDisposed() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        setVisible(false);
        dispose();
      }
    });
  }
}
