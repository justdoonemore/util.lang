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
 */package com.jdom.flashcard.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jdom.util.properties.PropertyLookup;
import com.jdom.util.properties.cache.PropertiesCache;

public class PropertiesCacheTest {
	private static final String PROPERTIESCACHE_TEST_PROPERTIES = "/properties/propertiescache-test.properties";

	@BeforeClass
	public static void beforeClass() throws IOException {
		PropertiesCache.configure(PROPERTIESCACHE_TEST_PROPERTIES);
	}

	@Test(expected = IOException.class)
	public void testConfigureWithBadPathThrowsException() throws IOException {
		try {
			PropertiesCache.configure("/non-existant.properties");
		} finally {
			// Make sure to restore the good properties!
			PropertiesCache.configure(PROPERTIESCACHE_TEST_PROPERTIES);
		}
	}

	@Test
	public void testRetrieveInteger() {
		assertEquals(10,
				PropertiesCache.getInteger(TestPropertyKey.INTEGER_PROPERTY));
	}

	@Test
	public void testRetrieveDouble() {
		assertTrue(2.3D == PropertiesCache
				.getDouble(TestPropertyKey.DOUBLE_PROPERTY));
	}

	@Test
	public void testRetrieveStringArray() {
		Assert.assertArrayEquals(new String[] { "one", "two", "three" },
				PropertiesCache
						.getStringArray(TestPropertyKey.STRING_ARRAY_PROPERTY));
	}

	public enum TestPropertyKey implements PropertyLookup {
		INTEGER_PROPERTY("integer.property"), DOUBLE_PROPERTY("double.property"), STRING_ARRAY_PROPERTY(
				"string.array.property");

		private final String lookup;

		private TestPropertyKey(String lookup) {
			this.lookup = lookup;
		}

		public String getLookup() {
			return lookup;
		}
	}
}
