/*
 * Created on Jul 3, 2007
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
package org.fest.swing.fixture;

import java.awt.Component;

import org.testng.annotations.Test;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.Timeout;

import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.*;
import static org.easymock.EasyMock.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.MIDDLE_BUTTON;
import static org.fest.swing.core.Timeout.timeout;
import static org.fest.swing.fixture.MouseClickInfo.middleButton;


/**
 * Understands test methods for implementations of <code>{@link CommonComponentFixture}</code>.
 * @param <T> the type of component tested by this test class. 
 *
 * @author Alex Ruiz
 */
@Test
public abstract class CommonComponentFixtureTestCase<T extends Component> extends ComponentFixtureTestCase<T> implements
    KeyboardInputSimulationFixtureTestCase, StateVerificationFixtureTestCase {

  public void shouldClick() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().click(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().click());
      }
    }.run();
  }
  
  public void shouldClickUsingGivenMouseButton() {
    final MouseButton button = MIDDLE_BUTTON;
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().click(target(), button);
        expectLastCall().once();
      } 

      protected void codeToTest() {
        assertThatReturnsThis(fixture().click(button));
      }
    }.run();
  }

  public void shouldClickUsingGivenMouseClickInfo() {
    replaceInputSimulatorIn(fixture());
    final MouseClickInfo mouseClickInfo = middleButton().times(2);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().click(target(), MIDDLE_BUTTON, 2);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().click(mouseClickInfo));
      }
    }.run();
  }

  @Test(expectedExceptions = NullPointerException.class) 
  public void shouldThrowErrorIfMouseClickInfoIsNull() {
    fixture().click((MouseClickInfo)null);
  }

  public void shouldDoubleClick() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().doubleClick(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().doubleClick());
      }
    }.run();
  }

  public void shouldRightClick() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().rightClick(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().rightClick());
      }
    }.run();
  }
  
  public void shouldGiveFocus() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().focus(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().focus());
      }
    }.run();
  }

  public void shouldPressAndReleaseKey() {
    replaceInputSimulatorIn(fixture());
    final int keyCode = VK_A;
    final int[] modifiers = { SHIFT_MASK };
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().pressAndReleaseKey(eq(target()), eq(keyCode), aryEq(modifiers));
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().pressAndReleaseKey(KeyPressInfo.keyCode(keyCode).modifiers(modifiers)));
      }
    }.run();
  }

  @Test(expectedExceptions = NullPointerException.class) 
  public void shouldThrowErrorIfKeyPressInfoIsNull() {
    fixture().pressAndReleaseKey(null);
  }
  
  public void shouldPressAndReleaseKeys() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().pressAndReleaseKeys(target(), VK_A, VK_B, VK_C);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().pressAndReleaseKeys(VK_A, VK_B, VK_C));
      }
    }.run();
  }

  public void shouldPressKey() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().pressKey(target(), VK_A);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().pressKey(VK_A));
      }
    }.run();
  }

  public void shouldReleaseKey() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().releaseKey(target(), VK_A);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().releaseKey(VK_A));
      }
    }.run();
  }

  /** ${@inheritDoc} */
  public void shouldRequireDisabled() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireDisabled(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().requireDisabled());
      }
    }.run();
  }
  
  /** ${@inheritDoc} */
  public void shouldRequireEnabled() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireEnabled(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().requireEnabled());
      }
    }.run();
  }

  /** ${@inheritDoc} */
  public void shouldRequireEnabledUsingTimeout() {
    final Timeout timeout = timeout(2000);
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireEnabled(target(), timeout);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().requireEnabled(timeout));
      }
    }.run();
  }

  /** ${@inheritDoc} */
  public void shouldRequireNotVisible() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireNotVisible(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().requireNotVisible());
      }
    }.run();
  }

  /** ${@inheritDoc} */
  public void shouldRequireVisible() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireVisible(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture().requireVisible());
      }
    }.run();
  }
  
  final void assertThatReturnsThis(Object result) {
    assertThat(result).isSameAs(fixture());
  }

  abstract CommonComponentFixture fixture(); 
}
