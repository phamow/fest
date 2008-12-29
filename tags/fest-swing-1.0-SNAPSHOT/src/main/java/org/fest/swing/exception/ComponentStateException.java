/*
 * Created on Oct 23, 2008
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
package org.fest.swing.exception;

/**
 * Understands an error thrown when an action cannot be performed due to a GUI component being in an invalid state.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentStateException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new </code>{@link ComponentStateException}</code>.
   * @param message the detail message.
   */
  public ComponentStateException(String message) {
    super(message);
  }

  /**
   * Creates a new </code>{@link ComponentStateException}</code>.
   * @param message the detail message.
   * @param cause the cause of the error.
   */
  public ComponentStateException(String message, Throwable cause) {
    super(message, cause);
  }
}
