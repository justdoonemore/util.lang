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
 */package com.jdom.util.patterns.mvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jdom.util.patterns.mvc.MvcLocator;
import com.jdom.util.patterns.mvc.MvcLocatorException;
import com.jdom.util.patterns.mvc.interfaces.InterfaceWithImplThatDoesntImplement;
import com.jdom.util.patterns.mvc.interfaces.TestAbstractClassWithImpl;
import com.jdom.util.patterns.mvc.interfaces.TestInterface;
import com.jdom.util.patterns.mvc.interfaces.TestInterfaceSamePackage;
import com.jdom.util.patterns.mvc.interfaces.TestInterfaceSamePackageImpl;
import com.jdom.util.patterns.mvc.interfaces.TestInterfaceWithAbstractClassAsConstructorArgumentImpl;
import com.jdom.util.patterns.mvc.interfaces.TestInterfaceWithImpl;
import com.jdom.util.patterns.mvc.interfaces.TestInterfaceWithImplArguments;
import com.jdom.util.patterns.mvc.interfaces.TestInterfaceWithInterfaceAsConstructorArgument;
import com.jdom.util.patterns.mvc.interfaces.impl.TestInterfaceImpl;
import com.jdom.util.patterns.mvc.interfaces.impl.TestInterfaceWithImplArgumentsImpl;

public class MvcLocatorTest {

    private static final String THE_IMPL_WAS_NOT_THE_EXPECTED_IMPLEMENTATION = "The impl was not the expected implementation!";

    @Before
    public void setUp() {
        // Clear all lookups before each test
        MvcLocator.clearLookups();
    }

    @Test
    public void testCanFindStandardImplInSamePackage() {
        TestInterfaceSamePackage impl = MvcLocator.getImpl(TestInterfaceSamePackage.class);

        assertTrue(THE_IMPL_WAS_NOT_THE_EXPECTED_IMPLEMENTATION, impl instanceof TestInterfaceSamePackageImpl);
    }

    @Test
    public void testCanFindStandardImplInImplPackage() {
        TestInterface impl = MvcLocator.getImpl(TestInterface.class);

        assertTrue(THE_IMPL_WAS_NOT_THE_EXPECTED_IMPLEMENTATION, impl instanceof TestInterfaceImpl);
    }

    @Test
    public void testReuseOfLookups() {
        TestInterface impl = MvcLocator.getImpl(TestInterface.class);
        TestInterface impl2 = MvcLocator.getImpl(TestInterface.class);

        assertTrue("The same instance was not returned!!", impl == impl2);
    }

    @Test
    public void testImplWithArguments() {
        TestInterfaceWithImplArguments impl = MvcLocator.getImpl(TestInterfaceWithImplArguments.class, "test");

        assertTrue(THE_IMPL_WAS_NOT_THE_EXPECTED_IMPLEMENTATION, impl instanceof TestInterfaceWithImplArgumentsImpl);
        assertEquals("test", impl.getField());
    }

    @Test
    public void testAbstractClassImplTypeAsConstructorArgumentIsResolvedAsSuperClass() {
        TestAbstractClassWithImpl implAsConstructorArgument = MvcLocator.getImpl(TestAbstractClassWithImpl.class);

        MvcLocator.getImpl(TestInterfaceWithAbstractClassAsConstructorArgumentImpl.class, implAsConstructorArgument);
    }

    @Test
    public void testInterfaceImplTypeAsConstructorArgumentIsResolvedAsInterface() {
        TestInterface implAsConstructorArgument = MvcLocator.getImpl(TestInterface.class);

        MvcLocator.getImpl(TestInterfaceWithInterfaceAsConstructorArgument.class, implAsConstructorArgument);
    }

    @Test
    public void testInterfaceWithStaticClassImplementation() {
        TestInterfaceWithImpl impl = MvcLocator.getImpl(TestInterfaceWithImpl.class);

        assertNotNull("Should have returned an instance of the static class in the interface!", impl);
    }

    @Test(expected = MvcLocatorException.class)
    public void testInterfaceWithImplThatDoesntImplementInterfaceThrowsException() {
        MvcLocator.getImpl(InterfaceWithImplThatDoesntImplement.class);
    }

    @Test
    public void testAbilityToForceNewImpl() {
        TestInterface impl = MvcLocator.getImpl(TestInterface.class);
        TestInterface impl2 = MvcLocator.getNewImpl(TestInterface.class);

        assertFalse("The same instance should not have been returned!!", impl == impl2);

        TestInterface impl3 = MvcLocator.getImpl(TestInterface.class);
        assertTrue("The same instance should have been returned!!", impl2 == impl3);
    }

}
