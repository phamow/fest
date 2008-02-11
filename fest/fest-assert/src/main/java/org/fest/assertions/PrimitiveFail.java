/*
 * Created on Jan 25, 2008
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

import static org.fest.assertions.Fail.*;
import static org.fest.assertions.Formatting.*;
import static org.fest.util.Strings.concat;

/**
 * Understands failure methods for primitive types.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author David DIDIER
 */
public final class PrimitiveFail {

  /**
   * Fails if the given $<code>boolean</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>boolean</code>s are equal.
   */
  static void failIfEqual(String message, boolean actual, boolean second) {
    if (actual == second) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>char</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>char</code>s are equal.
   */
  static void failIfEqual(String message, char actual, char second) {
    if (actual == second) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>byte</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>byte</code>s are equal.
   */
  static void failIfEqual(String message, byte actual, byte second) {
    if (actual == second) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>short</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>short</code>s are equal.
   */
  static void failIfEqual(String message, short actual, short second) {
    if (actual == second) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>int</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>int</code>s are equal.
   */
  static void failIfEqual(String message, int actual, int second) {
    if (actual == second) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>long</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>long</code>s are equal.
   */
  static void failIfEqual(String message, long actual, long second) {
    if (actual == second) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>float</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>float</code>s are equal.
   */
  static void failIfEqual(String message, float actual, float second) {
    if (Float.compare(actual, second) == 0) fail(errorMessageIfEqual(message, actual, second));
  }
  /**
   * Fails if the given $<code>double</code>s are equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the given <code>double</code>s are equal.
   */
  static void failIfEqual(String message, double actual, double second) {
    if (Double.compare(actual, second) == 0) fail(errorMessageIfEqual(message, actual, second));
  }

  /**
   * Fails if the given <code>boolean</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>boolean</code>s are not equal.
   */
  static void failIfNotEqual(String message, boolean actual, boolean expected) {
    if (actual != expected) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>char</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>char</code>s are not equal.
   */
  static void failIfNotEqual(String message, char actual, char expected) {
    if (actual != expected) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>byte</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>byte</code>s are not equal.
   */
  static void failIfNotEqual(String message, byte actual, byte expected) {
    if (actual != expected) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>short</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>short</code>s are not equal.
   */
  static void failIfNotEqual(String message, short actual, short expected) {
    if (actual != expected) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>int</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>int</code>s are not equal.
   */
  static void failIfNotEqual(String message, int actual, int expected) {
    if (actual != expected) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>long</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>long</code>s are not equal.
   */
  static void failIfNotEqual(String message, long actual, long expected) {
    if (actual != expected) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>float</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>float</code>s are not equal.
   */
  static void failIfNotEqual(String message, float actual, float expected) {
    if (Float.compare(actual, expected) != 0) fail(errorMessageIfNotEqual(message, actual, expected));
  }
  /**
   * Fails if the given <code>double</code>s are not equal.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the value to check against <code>expected</code>.
   * @param expected expected value.
   * @throws AssertionError if the given <code>double</code>s are not equal.
   */
  static void failIfNotEqual(String message, double actual, double expected) {
    if (Double.compare(actual, expected) != 0)
      fail(errorMessageIfNotEqual(message, actual, expected));
  }

  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, char actual, char second) {
    if (actual >= second) fail(errorMessageIfNotLessThan(message, actual, second));
  }
  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, byte actual, byte second) {
    if (actual >= second) fail(errorMessageIfNotLessThan(message, actual, second));
  }
  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, short actual, short second) {
    if (actual >= second) fail(errorMessageIfNotLessThan(message, actual, second));
  }
  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, int actual, int second) {
    if (actual >= second) fail(errorMessageIfNotLessThan(message, actual, second));
  }
  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, long actual, long second) {
    if (actual >= second) fail(errorMessageIfNotLessThan(message, actual, second));
  }
  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, float actual, float second) {
    if (Float.compare(actual, second) >= 0) fail(errorMessageIfNotLessThan(message, actual, second));
  }
  /**
   * Fails if the first value is not less than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less than the second value.
   */
  static void failIfNotLessThan(String message, double actual, double second) {
    if (Double.compare(actual, second) >= 0) fail(errorMessageIfNotLessThan(message, actual, second));
  }

  private static String errorMessageIfNotLessThan(String message, Object actual, Object second) {
    return concat(format(message), "actual value:", inBrackets(actual), " should be less than:", inBrackets(second));
  }

  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, char actual, char second) {
    if (actual <= second) fail(errorMessageIfNotGreaterThan(message, actual, second));
  }
  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, byte actual, byte second) {
    if (actual <= second) fail(errorMessageIfNotGreaterThan(message, actual, second));
  }
  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, short actual, short second) {
    if (actual <= second) fail(errorMessageIfNotGreaterThan(message, actual, second));
  }
  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, int actual, int second) {
    if (actual <= second) fail(errorMessageIfNotGreaterThan(message, actual, second));
  }
  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, long actual, long second) {
    if (actual <= second) fail(errorMessageIfNotGreaterThan(message, actual, second));
  }
  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, float actual, float second) {
    if (Float.compare(actual, second) <= 0) fail(errorMessageIfNotGreaterThan(message, actual, second));
  }
  /**
   * Fails if the first value is not greater than the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater than the second value.
   */
  static void failIfNotGreaterThan(String message, double actual, double second) {
    if (Double.compare(actual, second) <= 0)
      fail(errorMessageIfNotGreaterThan(message, actual, second));
  }

  private static String errorMessageIfNotGreaterThan(String message, Object actual, Object second) {
    return concat(format(message), "actual value:", inBrackets(actual), " should be greater than:", inBrackets(second));
  }

  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, char actual, char second) {
    if (actual > second) fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, byte actual, byte second) {
    if (actual > second) fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, short actual, short second) {
    if (actual > second) fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, int actual, int second) {
    if (actual > second) fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, long actual, long second) {
    if (actual > second) fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, float actual, float second) {
    if (Float.compare(actual, second) > 0)
      fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not less or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not less or equal to the second value.
   */
  static void failIfNotLessOrEqualTo(String message, double actual, double second) {
    if (Double.compare(actual, second) > 0)
      fail(errorMessageIfNotLessOrEqualTo(message, actual, second));
  }

  private static String errorMessageIfNotLessOrEqualTo(String message, Object first, Object second) {
    return concat(format(message),
        "actual value:", inBrackets(first), " should be less than or equal to:", inBrackets(second));
  }

  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, char actual, char second) {
    if (actual < second) fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, byte actual, byte second) {
    if (actual < second) fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, short actual, short second) {
    if (actual < second) fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, int actual, int second) {
    if (actual < second) fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, long actual, long second) {
    if (actual < second) fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, float actual, float second) {
    if (Float.compare(actual, second) < 0)
      fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }
  /**
   * Fails if the first value is not greater or equal to the second value.
   * @param message the identifying message or <code>null</code> for the <code>AssertionError</code>.
   * @param actual the actual value.
   * @param second the value checked against <code>actual</code>.
   * @throws AssertionError if the first value is not greater or equal to the second value.
   */
  static void failIfNotGreaterOrEqualTo(String message, double actual, double second) {
    if (Double.compare(actual, second) < 0)
      fail(errorMessageIfNotGreaterOrEqualTo(message, actual, second));
  }

  private static String errorMessageIfNotGreaterOrEqualTo(String message, Object first, Object second) {
    return concat(format(message),
        "actual value:", inBrackets(first), " should be greater than or equal to:", inBrackets(second));
  }

  private PrimitiveFail() {}
}