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

import com.jdom.util.patterns.mvc.AbstractModel;

public class BaseApplicationModelImpl<CONTEXT_FACTORY extends ApplicationContextFactory>
		extends AbstractModel implements BaseApplicationModel<CONTEXT_FACTORY> {

	protected CONTEXT_FACTORY applicationContextFactory;

	public void setApplicationContextFactory(
			CONTEXT_FACTORY applicationContextFactory) {
		this.applicationContextFactory = applicationContextFactory;
	}
}
