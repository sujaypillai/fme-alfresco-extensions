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

package de.fme.topx.webscript;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import de.fme.topx.component.TopXSearchComponent;
import de.fme.topx.model.Node;

/**
 * the search webscript delivers the search results from the
 * {@link TopXSearchComponent} as json response.
 * 
 * @author jgoldhammer
 * 
 */
public class TopXSearchWebscript extends AbstractWebScript {

	private static final String DEFAULT_MAX_ITEMS = "10";
	TopXSearchComponent searchComponent;
	private ObjectMapper mapper;
	private final static Log LOG = LogFactory.getLog(TopXSearchWebscript.class);

	public TopXSearchComponent getSearchComponent() {
		return searchComponent;
	}

	public void setSearchComponent(TopXSearchComponent searchComponent) {
		this.searchComponent = searchComponent;
	}

	public void init() {
		// instance should be cached for performance reasons.
		mapper = new ObjectMapper();
	}

	/**
	 * executes the search- checks input parameter, executes the search and
	 * sends back the json result.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.extensions.webscripts.WebScript#execute(org.springframework.extensions.webscripts.WebScriptRequest,
	 *      org.springframework.extensions.webscripts.WebScriptResponse)
	 */
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse response) throws IOException {
		// get input parameter
		String queryType = req.getParameter("queryType");
		String parentId = req.getParameter("parentId");
		String maxItems = req.getParameter("maxItems") != null ? req.getParameter("maxItems") : DEFAULT_MAX_ITEMS;
		LOG.debug("Incoming request: queryType=" + queryType + ", parentId=" + parentId + ", maxItems=" + maxItems + "");

		if (StringUtils.isBlank(queryType)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				List<Node> results = searchComponent.submitSearch(queryType, maxItems, parentId);
				generateJson(results, response);
			} catch (Throwable e) {
				LOG.error("Encountered exception while searching and generating json ", e);
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}

	/**
	 * generates the json for the list of results.
	 * 
	 * @param results
	 *            the list of nodes
	 * @param response
	 *            the outputstream where json should write in.
	 * @throws IOException
	 *             if json generation fails.
	 */
	private void generateJson(List<Node> results, WebScriptResponse response) throws IOException {
		mapper.writeValue(response.getOutputStream(), results);
	}

}
