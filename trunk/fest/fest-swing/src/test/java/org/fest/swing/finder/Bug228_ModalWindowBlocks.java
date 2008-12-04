/*
 * Created on Nov 30, 2008
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
package org.fest.swing.finder;

import java.awt.Frame;

import javax.swing.JDialog;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.core.RobotFixture;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.testing.TestDialog;
import org.fest.swing.testing.TestWindow;

import static javax.swing.SwingUtilities.invokeLater;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.finder.WindowFinder.findDialog;
import static org.fest.swing.testing.TestGroups.*;

/**
 * Test case for <a href="http://code.google.com/p/fest/issues/detail?id=228">Bug 228</a>.
 *
 * @author Ken Geis
 * @author Alex Ruiz
 */
@Test(groups = { GUI, BUG })
public class Bug228_ModalWindowBlocks {

  private Robot robot;
  
  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = RobotFixture.robotWithNewAwtHierarchy();
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }
  
  public void testModalDialog()
  {
    TestWindow window = TestWindow.createNewWindow(getClass());
    robot.showWindow(window);
    MyDialog.createAndShowNew(window);
    DialogFixture found = findDialog(JDialog.class).using(robot);
    assertThat(found.target).isInstanceOf(MyDialog.class);
  }
  
  private static class MyDialog extends TestDialog {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static void createAndShowNew(final Frame owner) {
      invokeLater(new Runnable() {
        public void run() {
          TestDialog.display(new MyDialog(owner));
        }
      });
    }
    
    MyDialog(Frame owner) {
      super(owner);
      setModal(true);
    }
  }
}