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
 */package com.jdom.util.compare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.jdom.util.compare.CompareUtil;

public class CompareUtilTest {

    private static final String STRING_ONE = "test";

    private static final String STRING_TWO = "TEST";

    @Test
    public void ignoreCaseWillReturnTrue() {
        boolean equal = CompareUtil.isEqual(STRING_ONE, STRING_TWO, true);

        assertTrue("The comparison should have returned true!", equal);
    }

    @Test
    public void ignoreCaseWillReturnFalse() {
        boolean equal = CompareUtil.isEqual(STRING_ONE, "bleh", true);

        assertFalse("The comparison should have returned false!", equal);
    }

    @Test
    public void compareNotIgnoringCaseWillReturnFalse() {
        boolean equal = CompareUtil.isEqual(STRING_ONE, STRING_TWO, false);

        assertFalse("The comparison should have returned false!", equal);
    }

    @Test
    public void compareIgnoringCaseWillReturnTrue() {
        boolean equal = CompareUtil.isEqual(STRING_ONE, new String(STRING_ONE), false);

        assertTrue("The comparison should have returned true!", equal);
    }

    @Test
    public void oneNullValueReturnsFalse() {
        boolean equal = CompareUtil.isEqual(STRING_ONE, null);

        assertFalse("The comparison should have returned false!", equal);
    }

    @Test
    public void twoNullValuesReturnsTrue() {
        boolean equal = CompareUtil.isEqual((String) null, null);

        assertTrue("The comparison should have returned true!", equal);
    }

    @Test
    public void testTwoCollectionsOfSameTypeWithSameObjectsInDifferentOrderReturnTrue() {
        List<String> coll1 = Arrays.asList("one", "two", "three");
        List<String> coll2 = Arrays.asList("two", "one", "three");

        assertTrue("The collections should have been determined to be equal!", CompareUtil.isEqual(coll1, coll2));
    }

    @Test
    public void testTwoCollectionsOfNonSameTypeWithSameObjectsInDifferentOrderReturnTrue() {
        List<String> coll1 = Arrays.asList("one", "two", "three");
        Set<String> coll2 = new HashSet<String>();
        coll2.add("three");
        coll2.add("one");
        coll2.add("two");

        assertTrue("The collections should have been determined to be equal!", CompareUtil.isEqual(coll1, coll2));
    }

    @Test
    public void testTwoCollectionsOfSameTypeWithDifferentObjectsReturnFalse() {
        List<String> coll1 = Arrays.asList("one", "two", "three");
        List<String> coll2 = Arrays.asList("one", "two", "four");

        assertFalse("The collections should NOT have been determined to be equal!", CompareUtil.isEqual(coll1, coll2));
    }

    @Test
    public void testTwoNullObjectsOfSameTypeReturnTrue() {
        Object obj1 = null;
        Object obj2 = null;

        assertTrue("The two objects should have been determined to be equal!", CompareUtil.isEqual(obj1, obj2));
    }

    @Test
    public void testTwoCollectionsOfNullReturnTrue() {
        List<String> coll1 = null;
        List<String> coll2 = null;

        assertTrue("The collections should have been determined to be equal!", CompareUtil.isEqual(coll1, coll2));
    }

    @Test
    public void testTwoDateObjectsOffByAMillisecondAreNotEqual() {
        Date date1 = new Date(700);
        Date date2 = new Date(701);

        assertFalse("The date objects should not have been equal!", CompareUtil.isEqual(date1, date2));
    }

    @Test
    public void testTwoDateObjectsWithSameMillisecondAreEqual() {
        Date date1 = new Date(700);
        Date date2 = new Date(700);

        assertTrue("The date objects should have been equal!", CompareUtil.isEqual(date1, date2));
    }

    @Test
    public void testTwoEquivalentStringObjectsReferencedAsObjectsReturnTrue() {
        Object obj1 = new String("blah");
        Object obj2 = new String("blah");

        assertTrue("The two objects should have been equal!", CompareUtil.isEqual(obj1, obj2));
    }

    @Test
    public void testTwoNonEquivalentStringObjectsReferencedAsObjectsReturnFalse() {
        Object obj1 = new String("blah");
        Object obj2 = new String("notsame");

        assertFalse("The two objects should not have been equal!", CompareUtil.isEqual(obj1, obj2));
    }
}
