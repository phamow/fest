/*
 * Created on Oct 19, 2007
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

import org.fest.swing.monitor.WindowMonitor;

import static org.fest.swing.query.WindowOwnedWindowsQuery.ownedWindowsOf;
import static org.fest.swing.util.AWT.*;


/**
 * Understands access to the current AWT hierarchy.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ExistingHierarchy implements ComponentHierarchy {

  private static WindowMonitor windowMonitor = WindowMonitor.instance();

  private final ParentFinder parentFinder;
  private final ChildrenFinder childrenFinder;

  /** Creates a new </code>{@link ExistingHierarchy}</code>. */
  public ExistingHierarchy() {
    this(new ParentFinder(), new ChildrenFinder());
  }

  ExistingHierarchy(ParentFinder parentFinder, ChildrenFinder childrenFinder) {
    this.parentFinder = parentFinder;
    this.childrenFinder = childrenFinder;
  }

  /** {@inheritDoc} */
  public Collection<? extends Container> roots() {
    return windowMonitor.rootWindows();
  }

  /** {@inheritDoc} */
  public Container parentOf(Component c) {
    return parentFinder.parentOf(c);
  }

  /**
   * Returns whether the given component is reachable from any of the root windows. The default is to consider all
   * components to be contained in the hierarchy, whether they are reachable or not.
   * @param c the given component.
   * @return <code>true</code>.
   */
  public boolean contains(Component c) {
    return true;
  }

  /**
   * Returns all descendants of interest of the given component.
   * @param c the given component.
   * @return all descendants of interest of the given component.
   */
  public Collection<Component> childrenOf(Component c) {
    return childrenFinder.childrenOf(c);
  }

  /**
   * Properly dispose of the given window, making it and its native resources available for garbage collection.
   * @param w the window to dispose.
   */
  public void dispose(Window w) {
    if (isAppletViewer(w)) return;
    for (Window owned : ownedWindowsOf(w)) dispose(owned);
    if (isSharedInvisibleFrame(w)) return;
    WindowDisposeTask.dispose(w);
  }

  ParentFinder parentFinder() { return parentFinder; }
  ChildrenFinder childrenFinder() { return childrenFinder; }
}
