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
 */package com.jdom.util.patterns.mvp;

import java.util.Arrays;

import org.junit.Test;

import com.jdom.junit.utils.AssertUtil;

public class ActionConfigurationTest {

	@Test
	public void testEqualsAndHashcodeContract() {
		final Runnable runnable1 = new Runnable() {
			public void run() {
			}
		};
		ActionConfiguration objectUnderTest = new ActionConfiguration("first",
				runnable1);

		ActionConfiguration equals = new ActionConfiguration("first", runnable1);

		final Runnable runnable2 = new Runnable() {
			public void run() {
			}
		};
		ActionConfiguration notEquals1 = new ActionConfiguration("first",
				runnable2);
		ActionConfiguration notEquals2 = new ActionConfiguration("second",
				runnable1);

		AssertUtil.assertEqualsAndHashcode(objectUnderTest,
				Arrays.asList(equals), Arrays.asList(notEquals1, notEquals2));
	}

}
