/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fme.topx.constants;

import org.alfresco.service.namespace.QName;

/**
 * constants for topx data model.
 * 
 * @author jgoldhammer
 * 
 */
public class DataModel {

	/**
	 * URI for the content model.
	 */
	private static final String NAMESPACE_URI = "http://www.alfresco.org/model/topx/1.0";
	/**
	 * the name of the countable aspect.
	 */
	public static final QName TOPX_ASPECTNAME = QName.createQName(NAMESPACE_URI, "countable");

	/**
	 * name of the attribute of the hitcounter for reads.
	 */
	public static final QName TOPX_COUNTER = QName.createQName(NAMESPACE_URI, "hitcount");

	/**
	 * name of the attribute of the hitcounter for updates.
	 */
	public static final QName TOPX_UPDATE_COUNTER = QName.createQName(NAMESPACE_URI, "hitcountUpdate");
	/**
	 * name of the attribute of the hitcount dates when a user reads a document.
	 * Is used to count only an access of a user for one time.
	 */
	public static final QName TOPX_DATE = QName.createQName(NAMESPACE_URI, "hitcountDate");
	public static final QName TOPX_USER = QName.createQName(NAMESPACE_URI, "hitcountUser");
	public static final QName TOPX_UPDATE_USER = QName.createQName(NAMESPACE_URI, "hitcountUpdateUser");

	public static final QName TOPX_UPDATE_DATE = QName.createQName(NAMESPACE_URI, "hitcountUpdateDate");

}
