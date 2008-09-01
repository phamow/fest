/*
 * Created on Jul 26, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.query;

import java.awt.Color;
import java.awt.Component;

import org.fest.swing.core.GuiQuery;

import static org.fest.swing.core.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the background color of a
 * <code>{@link Component}</code>.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class ComponentBackgroundQuery extends GuiQuery<Color> {

  private final Component component;

  /**
   * Returns the background color of the given <code>{@link Component}</code>. This action is executed in the event
   * dispatch thread.
   * @param component the given <code>Component</code>.
   * @return the background color of the given <code>Component</code>.
   */
  public static Color backgroundOf(Component component) {
    return execute(new ComponentBackgroundQuery(component));
  }

  ComponentBackgroundQuery(Component component) {
    this.component = component;
  }

  /**
   * Returns the background color of this query's <code>{@link Component}</code>. This action is executed in the event
   * dispatch thread.
   * @return the background color of this query's <code>Component</code>.
   */
  protected Color executeInEDT() {
    return component.getBackground();
  }
}