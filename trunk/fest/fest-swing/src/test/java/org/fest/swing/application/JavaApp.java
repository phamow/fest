/*
 * Created on May 23, 2008
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
package org.fest.swing.application;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.fest.util.Arrays;

/**
 * An application with a "main" method that shows a <code>{@link JFrame}</code>.
 *
 * @author Yvonne Wang
 */
public class JavaApp {

  public static void main(String[] args) {
    System.out.println(Arrays.format(args));
    JFrame frame = new JFrame("Java Application");
    frame.setPreferredSize(new Dimension(200, 200));
    frame.pack();
    frame.setVisible(true);
  }
}