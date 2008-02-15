/*
 * Created on Feb 14, 2008
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
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.testng.Assert.*;

import org.fest.test.CodeToTest;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link IntArrayAssert}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class IntArrayAssertTest {

  private static final int[] NULL_ARRAY = null;
  private static final int[] EMPTY_ARRAY = new int[0];

  @Test public void shouldSetDescription() {
    IntArrayAssert assertion = new IntArrayAssert(459, 23);
    assertNull(assertion.description());
    assertion.as("A Test");
    assertEquals(assertion.description(), "A Test");
  }

  @Test public void shouldSetDescriptionSafelyForGroovy() {
    IntArrayAssert assertion = new IntArrayAssert(459, 23);
    assertNull(assertion.description());
    assertion.describedAs("A Test");
    assertEquals(assertion.description(), "A Test");
  }

  private static class EmptyOrNullArrayCondition extends Condition<int[]> {
    @Override public boolean matches(int[] array) {
      return array == null || array.length == 0;
    }
  }

  @Test public void shouldPassIfConditionSatisfied() {
    new IntArrayAssert(EMPTY_ARRAY).satisfies(new EmptyOrNullArrayCondition());
  }

  @Test public void shouldThrowErrorIfConditionIsNull() {
    expectIllegalArgumentExceptionIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).satisfies(null);
      }
    });
  }

  @Test public void shouldFailIfConditionNotSatisfied() {
    expectAssertionError("condition failed with:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).satisfies(new EmptyOrNullArrayCondition());
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfConditionNotSatisfied() {
    expectAssertionError("[A Test] condition failed with:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).as("A Test").satisfies(new EmptyOrNullArrayCondition());
      }
    });
  }

  @Test public void shouldFailIfConditionNotSatisfiedShowingDescriptionOfCondition() {
    expectAssertionError("expected:<Empty or null array> but was:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).satisfies(new EmptyOrNullArrayCondition().as("Empty or null array"));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfConditionNotSatisfiedShowingDescriptionOfCondition() {
    expectAssertionError("[A Test] expected:<Empty or null array> but was:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).as("A Test").satisfies(new EmptyOrNullArrayCondition().as("Empty or null array"));
      }
    });
  }

  @Test public void shouldPassIfGivenObjectsIsInArray() {
    new IntArrayAssert(459, 23).contains(459, 23);
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfContainsValues() {
    expectAssertionErrorIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(NULL_ARRAY).contains(459, 23);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingIfContainsValues() {
    expectAssertionErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(NULL_ARRAY).as("A Test").contains(459, 23);
      }
    });
  }

  @Test public void shouldFailIfGivenObjectIsNotInArray() {
    expectAssertionError("array:<[]> does not contain element(s):<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).contains(459, 23);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfGivenObjectIsNotInArray() {
    expectAssertionError("[A Test] array:<[]> does not contain element(s):<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).as("A Test").contains(459, 23);
      }
    });
  }

  @Test public void shouldPassIfGivenObjectsAreNotInArray() {
    new IntArrayAssert(459, 23).excludes(90, 82);
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfIncludesValues() {
    expectAssertionErrorIfArrayIsNull(new CodeToTest() {
      public void run() throws Throwable {
        new IntArrayAssert(NULL_ARRAY).excludes(459, 23);
      }
    });
  }

  @Test public void shouldFailShowindDescriptionIfActualIsNullWhenCheckingIfIncludesValues() {
    expectAssertionErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() throws Throwable {
        new IntArrayAssert(NULL_ARRAY).as("A Test").excludes(459, 23);
      }
    });
  }

  @Test public void shouldFailIfActualIncludesValueAndExpectingExclude() {
    expectAssertionError("array:<[459, 23]> does not exclude element(s):<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).excludes(459, 23);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIncludesValueAndExpectingExclude() {
    expectAssertionError("[A Test] array:<[459, 23]> does not exclude element(s):<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).as("A Test").excludes(459, 23);
      }
    });
  }

  @Test public void shouldPassIfActualIsNull() {
    new IntArrayAssert(NULL_ARRAY).isNull();
  }

  @Test public void shouldFailIfActualIsNotNullAndExpectingNull() {
    expectAssertionError("<[]> should be null").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).isNull();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNotNullAndExpectingNull() {
    expectAssertionError("[A Test] <[]> should be null").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).as("A Test").isNull();
      }
    });
  }

  @Test public void shouldPassIfActualIsNotNull() {
    new IntArrayAssert(EMPTY_ARRAY).isNotNull();
  }

  @Test public void shouldFailIfActualIsNullAndExpectingNotNull() {
    expectAssertionErrorIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(NULL_ARRAY).isNotNull();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullAndExpectingNotNull() {
    expectAssertionErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(NULL_ARRAY).as("A Test").isNotNull();
      }
    });
  }

  @Test public void shouldPassIfActualIsEmpty() {
    new IntArrayAssert(EMPTY_ARRAY).isEmpty();
  }

  @Test public void shouldFailIfActualIsNullAndExpectingEmpty() {
    expectAssertionErrorIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(NULL_ARRAY).isEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullAndExpectingEmpty() {
    expectAssertionErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(NULL_ARRAY).as("A Test").isEmpty();
      }
    });
  }

  @Test public void shouldFailIfActualIsNotEmptyAndExpectingEmpty() {
    expectAssertionError("expecting empty array, but was:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).isEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNotEmptyAndExpectingEmpty() {
    expectAssertionError("[A Test] expecting empty array, but was:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).as("A Test").isEmpty();
      }
    });
  }

  @Test public void shouldPassIfActualIsNotEmpty() {
    new IntArrayAssert(459, 23).isNotEmpty();
  }

  @Test public void shouldFailIfArrayIsEmpty() {
    expectAssertionError("expecting a non-empty array, but it was empty").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).isNotEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfArrayIsEmpty() {
    expectAssertionError("[A Test] expecting a non-empty array, but it was empty").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).as("A Test").isNotEmpty();
      }
    });
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingForNotEmpty() {
    expectAssertionErrorIfArrayIsNull(new CodeToTest() {
      public void run() throws Throwable {
        new IntArrayAssert(NULL_ARRAY).isNotEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingForNotEmpty() {
    expectAssertionErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() throws Throwable {
        new IntArrayAssert(NULL_ARRAY).as("A Test").isNotEmpty();
      }
    });
  }

  @Test public void shouldPassIfArraysAreEqual() {
    new IntArrayAssert(459, 23).isEqualTo(array(459, 23));
  }

  @Test public void shouldFailIfArraysAreNotEqualAndExpectingEqual() {
    expectAssertionError("expected:<[90, 82]> but was:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).isEqualTo(array(90, 82));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfArraysAreNotEqualAndExpectingEqual() {
    expectAssertionError("[A Test] expected:<[90, 82]> but was:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).as("A Test").isEqualTo(array(90, 82));
      }
    });
  }

  @Test public void shouldPassIfArraysAreNotEqual() {
    new IntArrayAssert(459, 23).isNotEqualTo(array(86));
  }

  @Test public void shouldFailIfArraysAreEqualAndExpectingNotEqual() {
    expectAssertionError("actual value:<[459, 23]> should not be equal to:<[459, 23]>").on(
        new CodeToTest() {
          public void run() {
            new IntArrayAssert(459, 23).isNotEqualTo(array(459, 23));
          }
        });
  }

  @Test public void shouldFailShowingDescriptionIfArraysAreEqualAndExpectingNotEqual() {
    expectAssertionError("[A Test] actual value:<[459, 23]> should not be equal to:<[459, 23]>").on(
        new CodeToTest() {
          public void run() {
            new IntArrayAssert(459, 23).as("A Test").isNotEqualTo(array(459, 23));
          }
        });
  }

  @Test public void shouldPassIfActualContainsOnlyExpectedElements() {
    new IntArrayAssert(8).containsOnly(8);
  }

  @Test public void shouldFailIfArrayIsEmptyWhenLookingForSpecificElements() {
    expectAssertionError("array:<[]> does not contain element(s):<[90, 82]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).containsOnly(array(90, 82));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfArrayIsEmptyWhenLookingForSpecificElements() {
    expectAssertionError("[A Test] array:<[]> does not contain element(s):<[90, 82]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(EMPTY_ARRAY).as("A Test").containsOnly(array(90, 82));
      }
    });
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfContainsOnly() {
    expectAssertionErrorIfArrayIsNull(new CodeToTest() {
      public void run() throws Throwable {
        new IntArrayAssert(NULL_ARRAY).containsOnly(array(90, 82));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingIfContainsOnly() {
    expectAssertionErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() throws Throwable {
        new IntArrayAssert(NULL_ARRAY).as("A Test").containsOnly(array(90, 82));
      }
    });
  }

  @Test public void shouldFailIfActualHasExtraElementsWhenCheckingIfContainsOnly() {
    expectAssertionError("unexpected element(s):<[23]> in array:<[459, 23]>").on(
        new CodeToTest() {
          public void run() {
            new IntArrayAssert(459, 23).containsOnly(array(459));
          }
        });
  }

  @Test public void shouldFailShowingDescriptionIfActualHasExtraElementsWhenCheckingIfContainsOnly() {
    expectAssertionError("[A Test] unexpected element(s):<[23]> in array:<[459, 23]>").on(
        new CodeToTest() {
          public void run() {
            new IntArrayAssert(459, 23).as("A Test").containsOnly(array(459));
          }
        });
  }

  @Test public void shouldFailIfActualIsMissingElementsWhenCheckingIfContainsOnly() {
    expectAssertionError("array:<[459, 23]> does not contain element(s):<[90, 82]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).containsOnly(array(90, 82));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsMissingElementsWhenCheckingIfContainsOnly() {
    expectAssertionError("[A Test] array:<[459, 23]> does not contain element(s):<[90, 82]>").on(
        new CodeToTest() {
          public void run() {
            new IntArrayAssert(459, 23).as("A Test").containsOnly(array(90, 82));
          }
        });
  }

  @Test public void shouldPassIfActualHasExpectedSize() {
    int[] array = array(86, 59, 98);
    new IntArrayAssert(array).hasSize(3);
  }

  @Test public void shouldFailIfActualDoesNotHaveExpectedSize() {
    expectAssertionError("expected size:<2> but was:<1> for array:<[459]>").on(new CodeToTest() {
      public void run() {
        int[] array = array(459);
        new IntArrayAssert(array).hasSize(2);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualDoesNotHaveExpectedSize() {
    expectAssertionError("[A Test] expected size:<2> but was:<1> for array:<[459]>").on(new CodeToTest() {
      public void run() {
        int[] array = array(459);
        new IntArrayAssert(array).as("A Test").hasSize(2);
      }
    });
  }

  @Test public void shouldPassIfArraysAreSame() {
    int[] array = array(459, 23);
    new IntArrayAssert(array).isSameAs(array);
  }

  @Test public void shouldFailIfActualIsNotSameAsExpectedAndExpectingSame() {
    expectAssertionError("expected same instance but found:<[459, 23]> and:<[]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).isSameAs(EMPTY_ARRAY);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNotSameAsExpectedAndExpectingSame() {
    expectAssertionError("[A Test] expected same instance but found:<[459, 23]> and:<[]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(459, 23).as("A Test").isSameAs(EMPTY_ARRAY);
      }
    });
  }

  @Test public void shouldFailIfActualIsSameAsExpectedAndExpectingNotSame() {
    expectAssertionError("given objects are same:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        int[] array = array(459, 23);
        new IntArrayAssert(array).isNotSameAs(array);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsSameAsExpectedAndExpectingNotSame() {
    expectAssertionError("[A Test] given objects are same:<[459, 23]>").on(new CodeToTest() {
      public void run() {
        int[] array = array(459, 23);
        new IntArrayAssert(array).as("A Test").isNotSameAs(array);
      }
    });
  }

  @Test public void shouldPassIfArraysAreNotSame() {
    new IntArrayAssert(459).isNotSameAs(EMPTY_ARRAY);
  }

  private int[] array(int... args) { return args; }
}
