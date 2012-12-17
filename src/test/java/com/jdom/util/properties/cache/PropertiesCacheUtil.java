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
 */package com.jdom.util.properties.cache;

import java.io.IOException;

import org.junit.Assert;

import com.jdom.util.properties.cache.PropertiesCache;

public class PropertiesCacheUtil {

    private static final String PROPERTIES_FILE = "/flashcard-client.properties";

    public static void configureForFlashcardClient() {
        try {
            PropertiesCache.configure(PROPERTIES_FILE);
        } catch (IOException ioe) {
            Assert.fail("The file [" + PROPERTIES_FILE + "] was not found on the classpath!");
        }
    }

    public static void setProperty(String key, String value) {
        PropertiesCache.setProperty(key, value);
    }
}
