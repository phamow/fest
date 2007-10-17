/*
 * Created on Oct 14, 2007
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
package org.fest.swing.monitor;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;

import static org.fest.swing.util.Formatting.format;

import static org.fest.util.Strings.concat;

/**
 * Understands a monitor that maps event queues to GUI components.
 * <p>
 * Adapted from <code>abbot.tester.WindowTracker</code> from <a href="http://abbot.sourceforge.net"
 * target="_blank">Abbot</a>.
 * </p>
 * 
 * @author Alex Ruiz
 */
final class WindowsContext {

  private static Logger logger = Logger.getAnonymousLogger();

  /** Maps unique event queues to the set of root windows found on each queue. */
  private final Map<EventQueue, Map<Component, Boolean>> contexts = new WeakHashMap<EventQueue, Map<Component, Boolean>>();
  
  /** Maps components to their corresponding event queues. */
  private final Map<Component, WeakReference<EventQueue>> queues = new WeakHashMap<Component, WeakReference<EventQueue>>();

  private final Object lock = new Object();
  
  public WindowsContext() {
    contexts.put(Toolkit.getDefaultToolkit().getSystemEventQueue(), new WeakHashMap<Component, Boolean>());
  }
  
  /**
   * Return all available root windows. A root window is one that has a null parent. Nominally this means a list similar
   * to that returned by Frame.getFrames(), but in the case of an <code>{@link java.applet.Applet}</code> may return a
   * few dialogs as well.
   * @return all available root windows. 
   */
  Collection<Component> rootWindows() {
    Set<Component> rootWindows = new HashSet<Component>();
    synchronized (lock) {
      for (EventQueue queue : contexts.keySet())
        rootWindows.addAll(contexts.get(queue).keySet());
    }
    for (Frame f : Frame.getFrames()) rootWindows.add(f);
    return rootWindows;
  }
  
  EventQueue lookupEventQueueFor(Component c) {
    synchronized (lock) {
      WeakReference<EventQueue> reference = queues.get(c);
      if (reference != null) return reference.get();
      return null;
    }
  }

  void removeContextFor(Component component) {
    EventQueue queue = component.getToolkit().getSystemEventQueue();
    synchronized (lock) {
      Map<Component, Boolean> componentMap = contexts.get(queue);
      if (componentMap != null) componentMap.remove(component);
      else {
        EventQueue foundQueue = removeComponentFromContext(component);
        if (foundQueue == null) logger.info(concat("Got WINDOW_CLOSED on ", format(component),
            " on a previously unseen context: ", queue, "(", Thread.currentThread(), ")"));
        else logger.info(concat("Window ", format(component), " sent WINDOW_CLOSED on ", queue,
            " but sent WINDOW_OPENED on ", foundQueue));
      }
    }
  }

  private EventQueue removeComponentFromContext(Component component) {
    EventQueue foundQueue = null;
    for (EventQueue q : contexts.keySet()) {
      Map<Component, Boolean> componentMap = contexts.get(q);
      if (!componentMap.containsKey(component)) continue;
      foundQueue = q;
      componentMap.remove(component);
    }
    return foundQueue;
  }

  void addContextFor(Component component) {
    EventQueue queue = component.getToolkit().getSystemEventQueue();
    synchronized (lock) {
      Map<Component, Boolean> context = contexts.get(queue);
      if (context == null) {
        context = new WeakHashMap<Component, Boolean>();
        contexts.put(queue, context);
      }
      if (component instanceof Window && component.getParent() == null) context.put(component, true);
      queues.put(component, new WeakReference<EventQueue>(queue));
    }
  }  
  
  /**
   * Return the event queue corresponding to the given component. In most cases, this is the same as
   * <code>{@link java.awt.Toolkit#getSystemEventQueue()}</code>, but in the case of applets will bypass the AppContext 
   * and provide the real event queue.
   * @param c the given component.
   * @return the event queue corresponding to the given component
   */
  EventQueue queueFor(Component c) {
    Component component = c;
    // Components above the applet in the hierarchy may or may not share the same context with the applet itself.
    while (!(component instanceof java.applet.Applet) && component.getParent() != null)
      component = component.getParent();
    synchronized (lock) {
      WeakReference<EventQueue> reference = queues.get(component);
      EventQueue queue = reference != null ? reference.get() : null;
      if (queue == null) queue = component.getToolkit().getSystemEventQueue();
      return queue;
    }
  }

  /**
   * Returns all known event queues.
   * @return all known event queues.
   */
  Collection<EventQueue> allEventQueues() {
    HashSet<EventQueue> eventQueues = new HashSet<EventQueue>();
    synchronized (lock) {
      eventQueues.addAll(contexts.keySet());
      for (WeakReference<EventQueue> reference : queues.values()) {
        EventQueue queue = reference.get();
        if (queue != null) eventQueues.add(queue);
      }
    }
    return eventQueues;
  }
}
