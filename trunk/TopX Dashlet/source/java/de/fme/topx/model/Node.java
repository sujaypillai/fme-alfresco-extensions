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

package de.fme.topx.model;

import org.alfresco.service.cmr.repository.NodeRef;

/**
 * Node wrapper class which holds data for the dashlet and will be jsonified by
 * the jackson library.
 * 
 * @author jgoldhammer
 * 
 */
public class Node implements Comparable<Node> {
	private String nodeRef;

	private String parentNodeRef;

	private String displayPath;

	private String name;

	private int hitCount;

	private String nodeRefId;

	private String modifier;

	private String creator;

	private String modifyDate;

	private String creationDate;

	private String contentSizeFormatted;

	private String contentMimetype;

	private String modifierFormatted;

	private String creatorFormatted;

	private String siteName;

	private String sitePath;

	private String versionType;

	private String versionLabel;

	private String title;

	private String description;

	private String abbreviatedName;

	private String abbreviatedTitle;

	public Node(NodeRef nodeRef) {
		this.nodeRef = nodeRef.toString();
	}

	public void setNodeRef(String nodeRef) {
		this.nodeRef = nodeRef;
	}

	public void setParentNodeRef(String parentNodeRef) {
		this.parentNodeRef = parentNodeRef;
	}

	public void setDisplayPath(String displayPath) {
		this.displayPath = displayPath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public void setNodeRefId(String nodeRefId) {
		this.nodeRefId = nodeRefId;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setContentSizeFormatted(String contentSizeFormatted) {
		this.contentSizeFormatted = contentSizeFormatted;
	}

	public void setContentMimetype(String contentMimetype) {
		this.contentMimetype = contentMimetype;
	}

	public void setModifierFormatted(String modifierFormatted) {
		this.modifierFormatted = modifierFormatted;
	}

	public void setCreatorFormatted(String creatorFormatted) {
		this.creatorFormatted = creatorFormatted;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	public void setVersionLabel(String versionLabel) {
		this.versionLabel = versionLabel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}

	public void setAbbreviatedTitle(String abbreviatedTitle) {
		this.abbreviatedTitle = abbreviatedTitle;
	}

	public String getAbbreviatedTitle() {
		return abbreviatedTitle;
	}

	public String getVersionLabel() {
		return versionLabel;
	}

	public String getVersionType() {
		return versionType;
	}

	public String getAbbreviatedName() {
		return abbreviatedName;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getSitePath() {
		return sitePath;
	}

	public String getModifierFormatted() {
		return modifierFormatted;
	}

	public String getCreatorFormatted() {
		return creatorFormatted;
	}

	public String getContentMimetype() {
		return contentMimetype;
	}

	public String getContentSizeFormatted() {
		return contentSizeFormatted;
	}

	public String getModifier() {
		return modifier;
	}

	public String getCreator() {
		return creator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getDisplayPath() {
		return displayPath;
	}

	public String getNodeRefId() {
		return nodeRefId;
	}

	public int getHitCount() {
		return hitCount;
	}

	public String getNodeRef() {
		return nodeRef;
	}

	public String getName() {
		return name;
	}

	public String getParentNodeRef() {
		return parentNodeRef;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;

	}

	/**
	 * compares the hitcounter of two nodes.
	 * 
	 * * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Node that) {
		int result = 0;
		if (that != null) {
			if (this.getHitCount() > that.getHitCount()) {
				result = -1;
			} else if (this.getHitCount() < that.getHitCount()) {
				result = +1;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Node [nodeRef=" + nodeRef + ", parentNodeRef=" + parentNodeRef + ", displayPath=" + displayPath
				+ ", name=" + name + ", hitCount=" + hitCount + ", nodeRefId=" + nodeRefId + ", modifier=" + modifier
				+ ", creator=" + creator + ", modifyDate=" + modifyDate + ", creationDate=" + creationDate
				+ ", contentSizeFormatted=" + contentSizeFormatted + ", contentMimetype=" + contentMimetype
				+ ", modifierFormatted=" + modifierFormatted + ", creatorFormatted=" + creatorFormatted + ", siteName="
				+ siteName + ", sitePath=" + sitePath + ", versionType=" + versionType + ", versionLabel="
				+ versionLabel + ", title=" + title + ", description=" + description + ", abbreviatedName="
				+ abbreviatedName + ", abbreviatedTitle=" + abbreviatedTitle + "]";
	}

}
