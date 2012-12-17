/** 
 *  Copyright (C) 2012  Just Do One More
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */package com.jdom.util.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import com.jdom.util.reflection.ReflectionException;
import com.jdom.util.reflection.ReflectionUtils;

//import com.jdom.domain.common.AbstractEntity;
//import com.jdom.domain.user.User;

//TODO: Fix this test to not use the User class!
public class ReflectionUtilsTest {

//    private static final User TEST_USER = new User("myName", "myPassword", "Admin", "test@email.com");

    @Test
    public void testForName() {
        assertEquals(String.class, ReflectionUtils.forName("java.lang.String"));
    }

    @Test(expected = ReflectionException.class)
    public void testForNameThrowsReflectionExceptionWhenClassNotFoundIsThrown() {
        ReflectionUtils.forName("nonexistant");
    }

    @Test
    public void testGetBooleanGetterName() {
        assertEquals("isTest", ReflectionUtils.getBooleanGetterName("test"));
    }

    @Test
    public void testGetClone() {
//        User clone = ReflectionUtils.getClone(TEST_USER);

//        assertEquals(TEST_USER, clone);
    }

    @Test(expected = ReflectionException.class)
    public void testGetConstructorThrowsReflectionExceptionWhenErrorOccurs() {
        ReflectionUtils.getConstructor(String.class, boolean.class);
    }

    @Test
    public void testGetConstructorWithOneParameterType() throws SecurityException, NoSuchMethodException {
        Constructor<String> constructor = String.class.getConstructor(String.class);
        assertEquals(constructor, ReflectionUtils.getConstructor(String.class, String.class));
    }

    @Test
    public void testGetConstructorWithMultipleParameterTypes() throws SecurityException, NoSuchMethodException {
//        Constructor<User> constructor = User.class.getConstructor(String.class, String.class, String.class,
//                String.class);
//        assertEquals(constructor, ReflectionUtils.getConstructor(User.class, String.class, String.class, String.class,
//                String.class));
    }

    @Test
    public void testGetGetterName() {
        assertEquals("getTest", ReflectionUtils.getGetterName("test"));
    }

    @Test
    public void testGettingAMethodWithOneParameterType() throws SecurityException, NoSuchMethodException {
        Method expected = String.class.getMethod("equals", Object.class);
        Method method = ReflectionUtils.getMethod(String.class, "equals", Object.class);

        assertEquals(expected, method);
    }

    @Test
    public void testGettingAStringPropertyValue() {
//        Object name = ReflectionUtils.getPropertyValue(TEST_USER, "name");
//
//        assertEquals(TEST_USER.getName(), name);
    }

    @Test
    public void testGettingBooleanPropertyValue() {
        MockObject obj = new MockObject();
        obj.finished = true;
        obj.started = false;

        assertEquals(Boolean.TRUE, ReflectionUtils.getPropertyValue(obj, "finished"));
        assertEquals(Boolean.FALSE, ReflectionUtils.getPropertyValue(obj, "started"));
    }

    @Test
    public void testGetSetterName() {
        assertEquals("setTest", ReflectionUtils.getSetterName("test"));
    }

    @Test
    public void testGettingTypeOfBooleanProperty() {
        assertEquals(boolean.class, ReflectionUtils.getType(MockObject.class, "finished"));
    }

    @Test
    public void testGettingTypeOfStringProperty() {
//        assertEquals(String.class, ReflectionUtils.getType(User.class, "name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeThrowsIllegalArgumentExceptionWhenClassParameterNull() {
        ReflectionUtils.getType(null, "property");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeThrowsIllegalArgumentExceptionWhenPropertyNameParameterNull() {
        ReflectionUtils.getType(String.class, null);
    }

    @Test
    public void testInvokingMethod() throws SecurityException, NoSuchMethodException {
        String test = "test";
        String equal = "test";
        String notEqual = "notequal";

        Method equalsMethod = String.class.getMethod("equals", Object.class);

        assertEquals(true, ReflectionUtils.invoke(equalsMethod, test, equal));
        assertEquals(false, ReflectionUtils.invoke(equalsMethod, test, notEqual));
    }

//    @Test(expected = ReflectionException.class)
//    public void testInvokingMethodThrowsReflectionExceptionWhenObjectDoesntSupportMethods() throws SecurityException,
//            NoSuchMethodException {
//        Method equalsMethod = String.class.getMethod("equals", Object.class);

//        ReflectionUtils.invoke(equalsMethod, TEST_USER, TEST_USER);
//    }

    @Test
    public void testIsGetter() {
        assertFalse(ReflectionUtils.isGetter("name"));
        assertTrue(ReflectionUtils.isGetter("getName"));
        assertTrue(ReflectionUtils.isGetter("isName"));
    }

    @Test
    public void testNewInstanceNoParameters() {
        assertNotNull(ReflectionUtils.newInstance(String.class));
    }

    @Test(expected = ReflectionException.class)
    public void testNewInstanceThrowsReflectionExceptionWhenExceptionOccurs() throws Exception {
        Constructor<ThrowsException> constructor = ThrowsException.class.getConstructor(Exception.class);
        ReflectionUtils.newInstance(constructor, new IllegalArgumentException());
    }

    @Test(expected = ReflectionException.class)
    public void testNewInstanceThrowsReflectionExceptionWhenExceptionOccursOnDefaultCosntructor() {
        ReflectionUtils.newInstance(ThrowsException.class);
    }

    @Test
    public void testNewInstanceOneParameter() throws SecurityException, NoSuchMethodException {
        String value = "test";

        Constructor<String> constructor = String.class.getConstructor(String.class);

        String string = ReflectionUtils.newInstance(constructor, value);
        assertEquals(value, string);
    }

//    @Test
//    public void testNewInstanceOfAssignableTypeWithParameters() {
//        AbstractEntity<?> entity = ReflectionUtils.newInstanceOfAssignableType(AbstractEntity.class, User.class
//                .getName(), "myName", "myPassword", "Admin", "test@email.com");
//
//        assertTrue("The entity should have been a User object!", entity instanceof User);
//
//        User user = (User) entity;
//
//        assertEquals("myName", user.getName());
//        assertEquals("myPassword", user.getPassword());
//        assertEquals("Admin", user.getRoles());
//        assertEquals("test@email.com", user.getEmailAddress());
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testNewinstanceOfAssignableTypeWithParametersThrowsExceptionWhenNullPassed() {
//        ReflectionUtils.newInstanceOfAssignableType(AbstractEntity.class, User.class.getName(), "myName", "myPassword",
//                "Admin", null);
//    }
//
//    @Test(expected = ReflectionException.class)
//    public void testNewinstanceOfAssignableTypeWithParametersThrowsExceptionWhenClassNotFound() {
//        ReflectionUtils.newInstanceOfAssignableType(AbstractEntity.class, "nonexistant", "myName", "myPassword",
//                "Admin", "test@email.com");
//    }
//
//    @Test
//    public void testNewInstanceOfAssignableTypeWithNoParameters() {
//        AbstractEntity<?> entity = ReflectionUtils.newInstanceOfAssignableType(AbstractEntity.class, User.class
//                .getName());
//        assertTrue("The entity should have been a User object!", entity instanceof User);
//
//    }

    @Test
    public void testParseStringValueAsDate() {
        Date original = new Date(2000);
        Date obj = ReflectionUtils.parseStringValue(Date.class, original.toString());

        assertEquals(original, obj);
    }

    @Test
    public void testSetPropertyValue() {
        MockObject obj = new MockObject();
        assertFalse(obj.isFinished());
        ReflectionUtils.setPropertyValue(obj, "finished", Boolean.TRUE.booleanValue());
        assertTrue(obj.isFinished());
    }

    private static class MockObject {
        boolean finished;

        boolean started;

        public MockObject() {
            // TODO Auto-generated constructor stub
        }

        public boolean isFinished() {
            return finished;
        }

        public boolean getStarted() {
            return started;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }
    }

    private static class ThrowsException {
        public ThrowsException() {
            throw new RuntimeException();
        }

        public ThrowsException(Exception exception) throws Exception {
            throw exception;
        }

    }
}
