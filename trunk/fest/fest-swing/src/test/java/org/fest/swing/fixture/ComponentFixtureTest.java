/*
 * Created on Sep 5, 2007
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
package org.fest.swing.fixture;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.RobotFixture;
import org.fest.swing.TestFrame;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link ComponentFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ComponentFixtureTest {

  private ComponentFixture<JTextField> fixture;
  private RobotFixture robot;
  private MyFrame frame;

  @BeforeMethod public void setUp() {
    robot = RobotFixture.robotWithNewAwtHierarchy();
    frame = new MyFrame(getClass());
    fixture = new ComponentFixture<JTextField>(robot, frame.textBox) {
      @Override public ComponentFixture<JTextField> click() { return null; }
      @Override public ComponentFixture<JTextField> doubleClick() { return null; }
      @Override public ComponentFixture<JTextField> focus() { return null; }
      @Override public ComponentFixture<JTextField> pressKeys(int... keyCodes) { return null; }
      @Override public ComponentFixture<JTextField> requireDisabled() { return null; }
      @Override public ComponentFixture<JTextField> requireEnabled() { return null; }
      @Override public ComponentFixture<JTextField> requireNotVisible() { return null; }
      @Override public ComponentFixture<JTextField> requireVisible() { return null; }
      @Override public ComponentFixture<JTextField> rightClick() { return null; }
    };
    robot.showWindow(frame);
  }
  
  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }
  
  @Test public void shouldShowPopupMenuInTextFieldContainingPopupMenu() {
    JPopupMenuFixture popupMenu = fixture.showPopupMenu();
    assertThat(popupMenu.target).isSameAs(frame.popupMenu);
  }

  @Test public void shouldDoubleClickComponent() {
    ComponentEvents events = ComponentEvents.attachTo(frame.textBox);
    fixture.doDoubleClick();
    assertThat(events.doubleClicked()).isTrue();
  }
  
  private static class MyFrame extends TestFrame {
    private static final long serialVersionUID = 1L;

    private final JPopupMenu popupMenu = new JPopupMenu("Popup Menu");
    private final JTextField textBox = new JTextField(20);
    
    MyFrame(Class testClass) {
      super(testClass);
      add(textBox);
      textBox.setComponentPopupMenu(popupMenu);
      popupMenu.add(new JMenuItem("First"));
      popupMenu.add(new JMenuItem("Second"));
    }
  }  
}
