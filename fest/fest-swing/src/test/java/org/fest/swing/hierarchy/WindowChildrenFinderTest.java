/*
 * Created on Oct 25, 2007
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
 * Copyright @2007-2008 the original author or authors.
 */
package org.fest.swing.hierarchy;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.util.Collection;

import javax.swing.JFrame;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.lock.ScreenLock;
import org.fest.swing.testing.TestDialog;
import org.fest.swing.testing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.factory.JFrames.frame;
import static org.fest.swing.factory.JTextFields.textField;
import static org.fest.swing.testing.TestGroups.GUI;

/**
 * Tests for <code>{@link WindowChildrenFinder}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class WindowChildrenFinderTest {

  private WindowChildrenFinder finder;

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeMethod public void setUp() {
    finder = new WindowChildrenFinder();
  }

  @Test public void shouldReturnEmptyCollectionIfComponentIsNotWindow() {
    Container container = textField().createNew();
    assertThat(finder.nonExplicitChildrenOf(container)).isEmpty();
  }

  @Test public void shouldReturnEmptyCollectionIfComponentIsNull() {
    assertThat(finder.nonExplicitChildrenOf(null)).isEmpty();
  }

  @Test public void shouldReturnEmptyCollectionIfWindowNotHavingOwnedWindows() {
    final JFrame frame = frame().createNew();
    Collection<Component> children = findChildren(finder, frame);
    assertThat(children).isEmpty();
  }

  @Test(groups = GUI)
  public void shouldReturnOwnedWindowsIfComponentIsWindow() {
    ScreenLock.instance().acquire(this);
    TestWindow window = TestWindow.createAndShowNewWindow(getClass());
    TestDialog dialog = TestDialog.createAndShowNewDialog(window);
    try {
      Collection<Component> children = findChildren(finder, window);
      assertThat(children).containsOnly(dialog);
    } finally {
      try {
        dialog.destroy();
        window.destroy();
      } finally {
        ScreenLock.instance().release(this);
      }
    }
  }

  @RunsInEDT
  private static Collection<Component> findChildren(final WindowChildrenFinder finder, final Window w) {
    return execute(new GuiQuery<Collection<Component>>() {
      protected Collection<Component> executeInEDT() {
        return finder.nonExplicitChildrenOf(w);
      }
    });
  }
}
