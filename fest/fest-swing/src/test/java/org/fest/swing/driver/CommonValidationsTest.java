/*
 * Created on Jun 9, 2008
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
package org.fest.swing.driver;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.testing.CommonAssertions.failWhenExpectingException;

/**
 * Tests for <code>{@link CommonValidations}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class CommonValidationsTest {

  @Test public void shouldNotThrowErrorIfCellReaderIsNotNull() {
    CommonValidations.validateCellReader(new Object());
  }

  @Test public void shouldThrowErrorIfCellReaderIsNull() {
    try {
      CommonValidations.validateCellReader(null);
      failWhenExpectingException();
    } catch (NullPointerException e) {
      assertThat(e).message().isEqualTo("Cell reader should not be null");
    }
  }

  @Test public void shouldNotThrowErrorIfCellWriterIsNotNull() {
    CommonValidations.validateCellWriter(new Object());
  }

  @Test public void shouldThrowErrorIfCellWriterIsNull() {
    try {
      CommonValidations.validateCellWriter(null);
      failWhenExpectingException();
    } catch (NullPointerException e) {
      assertThat(e).message().isEqualTo("Cell writer should not be null");
    }
  }
}
