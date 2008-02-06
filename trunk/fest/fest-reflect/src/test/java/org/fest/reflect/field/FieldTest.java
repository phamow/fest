/*
 * Created on May 18, 2007
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
package org.fest.reflect.field;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.fest.reflect.Jedi;
import org.fest.reflect.Person;
import org.fest.reflect.exception.ReflectionError;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for the fluent interface for fields and static fields.
 *
 * @author Alex Ruiz
 */
public class FieldTest {
  
  private Person person;
  
  @BeforeTest public void setUp() {
    person = new Person("Luke");
  }
  
  @Test public void shouldGetFieldValue() {
    String personName = new Name("name").ofType(String.class).in(person).get();
    assertThat(personName).isEqualTo("Luke");
  }
  
  @Test public void shouldSetFieldValue() {
    new Name("name").ofType(String.class).in(person).set("Leia");
    assertThat(person.getName()).isEqualTo("Leia");
  }
  
  @Test public void shouldReturnFieldInfo() {
    java.lang.reflect.Field field = new Name("name").ofType(String.class).in(person).info();
    assertThat(field).isNotNull();
    assertThat(field.getName()).isEqualTo("name");
    assertThat(field.getType()).isEqualTo(String.class);
  }
  
  @Test(expectedExceptions = ReflectionError.class)
  public void shouldThrowErrorIfWrongFieldTypeSpecified() {
    Class<Integer> invalidType = Integer.class;
    new Name("name").ofType(invalidType).in(person).get();
  }

  @Test(expectedExceptions = ReflectionError.class)
  public void shouldThrowErrorIfInvalidFieldName() {
    new Name("age").ofType(Integer.class).in(person);
  }
  
  @Test public void shouldGetFieldInSuperType() {
    Jedi jedi = new Jedi("Yoda");
    String jediName = new Name("name").ofType(String.class).in(jedi).get();
    assertThat(jediName).isEqualTo("Yoda");
  }
  
  @Test public void shouldGetStaticFieldValue() {
    Person.setCount(6);
    int count = new StaticName("count").ofType(int.class).in(Person.class).get();
    assertThat(count).isEqualTo(6);
  }
  
  @Test public void shouldSetStaticFieldValue() {
    new StaticName("count").ofType(int.class).in(Person.class).set(8);
    assertThat(Person.getCount()).isEqualTo(8);
  }
  
  @Test public void shouldReturnStaticFieldInfo() {
    java.lang.reflect.Field field = new StaticName("count").ofType(int.class).in(Person.class).info();
    assertThat(field).isNotNull();
    assertThat(field.getName()).isEqualTo("count");
    assertThat(field.getType()).isEqualTo(int.class);
  }
  
  @Test(expectedExceptions = ReflectionError.class)
  public void shouldThrowErrorIfWrongStaticFieldTypeSpecified() {
    Class<Float> invalidType = Float.class;
    new StaticName("count").ofType(invalidType).in(Person.class).get();
  }

  @Test(expectedExceptions = ReflectionError.class)
  public void shouldThrowErrorIfInvalidStaticFieldName() {
    new StaticName("age").ofType(int.class).in(Person.class);
  }
  
  @Test public void shouldGetStaticFieldInSuperType() {
    Person.setCount(8);
    int count = new StaticName("count").ofType(int.class).in(Person.class).get();
    assertThat(count).isEqualTo(8);
  }
}
