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
 */package com.jdom.util.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jdom.util.string.StringUtil;

public class StringUtilTest {

    private static final String NULL_STRING = null;

    private static final String EMPTY_STRING = "";

    private static final String NON_EMPTY_STRING = "not-empty";

    @Test
    public void nullStringReturnsTrueForIsEmpty() {
        assertTrue("Null string should return true for being empty", StringUtil.isEmpty(NULL_STRING));
    }

    @Test
    public void emptyStringReturnsTrueForIsEmpty() {
        assertTrue("Empty string should return true for being empty", StringUtil.isEmpty(EMPTY_STRING));
    }

    @Test
    public void nonEmptyStringReturnsFalseForIsEmpty() {
        assertFalse("Non-empty string should return false for being empty", StringUtil.isEmpty(NON_EMPTY_STRING));
    }

    @Test
    public void testTitleCaseWithLowerCaseInput() {
        assertEquals("Test", StringUtil.titleCase("test"));
    }

    @Test
    public void testTitleCaseWithUpperCaseInput() {
        assertEquals("Test", StringUtil.titleCase("TEST"));
    }
    
    @Test
    public void testTitleCaseWithMixedCaseInput() {
        assertEquals("Test", StringUtil.titleCase("tEsT"));
    }
    
    @Test
    public void testTitleCaseWithSingleCharacter() {
        assertEquals("T", StringUtil.titleCase("t"));
    }
}
