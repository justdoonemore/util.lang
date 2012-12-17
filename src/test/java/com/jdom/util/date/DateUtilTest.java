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
 */package com.jdom.util.date;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jdom.util.date.DateUtil;

public class DateUtilTest {

    private static final String EXPECTED_FORMATTED_DATE = "02/20/2010";

    private static Date date;

    @BeforeClass
    public static void classSetUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 1, 20);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        date = calendar.getTime();
    }

    @Test
    public void testDateIsReturnedInCorrectlyFormattedString() {

        assertEquals("The date was not returned as the correct string", EXPECTED_FORMATTED_DATE, DateUtil
                .convertDateToMMDDYYYY(date));
    }

    @Test
    public void testConvertMMDDYYYYToDateObject() throws ParseException {
        Date convertedDate = DateUtil.convertMMDDYYYYToDate(EXPECTED_FORMATTED_DATE);

        assertEquals("The converted date did not return the proper date object!", date, convertedDate);
    }

}
