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

/**
 * Topx Dashlet
 * 
 * @namespace fme
 * @class fme.dashlet.topx
 */
if (typeof(fme) == "undefined") fme={};
if (typeof(fme.dashlet) == "undefined") fme.dashlet={};
if (typeof(fme.module) == "undefined") fme.module={};

(function()
{
   /**
	 * YUI Library aliases
	 */
   var Dom = YAHOO.util.Dom,
      Event = YAHOO.util.Event;

   /**
	 * Alfresco Slingshot aliases
	 */
   var $html = Alfresco.util.encodeHTML,
      $combine = Alfresco.util.combinePaths;

   /**
	 * Dashboard TOPX constructor.
	 * 
	 * @param {String}
	 *            htmlId The HTML id of the parent element
	 * @return {fme.dashlet.topx} The new component instance
	 * @constructor
	 */
   fme.dashlet.topx = function topx_constructor(htmlId)
   {
      return fme.dashlet.topx.superclass.constructor.call(this, "fme.dashlet.topx", htmlId);
   };

   /**
	 * Extend from Alfresco.component.Base and add class implementation
	 */
   YAHOO.extend(fme.dashlet.topx, Alfresco.component.Base,
   {
      /**
		 * Object container for initialization options
		 * 
		 * @property options
		 * @type object
		 */
      options:
      {
         /**
			 * The component id.
			 * 
			 * @property componentId
			 * @type string
			 * @default ""
			 */
         componentId: "",

         /**
			 * number of items to display
			 * 
			 * @property maxItems
			 * @type int
			 * @default
			 */
         maxItems: 10
      },

      /**
		 * documents list DOM container.
		 * 
		 * @property
		 * @type object
		 * @default null
		 */
      documentsList: null,

      /**
		 * Dashlet title DOM container.
		 * 
		 * @property title
		 * @type object
		 * @default null
		 */
      title: null,

      /**
		 * Fired by YUI when parent element is available for scripting
		 * 
		 * @method onReady
		 */
      onReady: function topx_onReady()
      {
    	 var me = this; 
         
         // The dashlet title container
         this.title = Dom.get(this.id + "-title");
         
         // The documentsList Container
         this.documentsList = Dom.get(this.id + "-documentsList");
         
         // Load the dashlet content
         this.load();
         
      },

      /**
		 * Reload the documents and refresh the contents of the dashlet
		 * 
		 * @method load
		 */
      load: function topx_load()
      {
         // Load the documents list.
         Alfresco.util.Ajax.jsonRequest(
         {
        	url: Alfresco.constants.PROXY_URI + "/de/fme/dashlet/topx/find?queryType=d&maxItems="+this.options.maxItems,
            successCallback:
            {
               fn: this.onLoadSuccess,
               scope: this,
               obj: null
            },
            failureCallback:
            {
               fn: this.onLoadFailure,
               scope: this
            },
            scope: this,
            noReloadOnAuthFailure: true
         });
      },
      
      /**
		 * documents loaded successfully
		 * 
		 * @method onLoadSuccess
		 * @param p_response
		 *            {object} Response object from request
		 * @param p_obj
		 *            {object} Custom object passed to function
		 */
      onLoadSuccess: function topx_onLoadSuccess(p_response, p_obj)
      {
         // Update the dashlet title
         this.title.innerHTML = this.msg("header.title", this.options.maxItems);
         var html = "";
         
         if (p_response.json)
         {
            documents = p_response.json;
            this.currentThumbnails = p_response.json;
            
            if (documents.length > 0)
            {
               html += this._generateDocumentsHTML(documents);
            }
            else
            {
               html += "<div class=\"detail-list-item first-item last-item\">\n";
               html += "<span>\n";
               html += this.msg("list.noDocuments");
               html += "</span>\n";
               html += "</div>\n";
            }
         }         
         this.documentsList.innerHTML = html;
      },

      /**
		 * documents load failed
		 * 
		 * @method onLoadFailure
		 * @param p_response
		 *            {object} Response object from request
		 * @param p_obj
		 *            {object} Custom object passed to function
		 */
      onLoadFailure: function topx_onLoadFailure(p_response, p_obj)
      {
         // Update the dashlet title
         this.documentsList.innerHTML = "<div class=\"msg\">" + this.msg("label.error") + "</div>";

      },

      /**
		 * PRIVATE FUNCTIONS
		 */
      
      /**
		 * Generate HTML markup for a collection of documents
		 * 
		 * @method _generateDocumentsHTML
		 * @private
		 * @param documents
		 *            {array} Document objects to render into HTML
		 * @return {string} HTML markup
		 */
      _generateDocumentsHTML: function topx__generateDocumentsHTML(documents)
      {
    	 var lastHits =0;
     	 var lastPlace =0;         
         var html = "";
         for (var i = 0; i < documents.length; i++){
             var place=i;
             if(lastHits == documents[i].hitCount){
            	 place = lastPlace; 
             }
             html += this._generateDocumentHTML(documents[i],i,place);
             lastPlace= i;
             lastHits = documents[i].hitCount;
         }
         return html;
      },
      
      /**
		 * Generate HTML markup for a single document.
		 * 
		 * @method _generateDocumentHTML
		 * @private
		 * @param doc
		 *            {object} document object to render into HTML
		 * @return {string} HTML markup
		 */
      _generateDocumentHTML: function topx__generateDocumentHTML(doc,i, place)
      {
    	 
         var html = "";  
         var icon;
                  
         var background =$html(Alfresco.constants.PROXY_URI)+"api/node/workspace/SpacesStore/"+doc.nodeRefId+"/content/thumbnails/doclib?c=queue&amp;ph=true)";
         if(place==0){
        	 icon =Alfresco.constants.URL_RESCONTEXT+"fme/components/dashlets/images/first_place.gif";
         }
         else if(place==1){
        	 icon=Alfresco.constants.URL_RESCONTEXT+"fme/components/dashlets/images/second_place.gif";
         }
         else if(place==2){      	 
        	 icon=Alfresco.constants.URL_RESCONTEXT+"fme/components/dashlets/images/third_place.gif";
         }
         else{
        	 icon = Alfresco.constants.URL_RESCONTEXT+"fme/components/dashlets/images/other_places.gif";
         }
         
         html += "<div class=\"topx-doc\">\n";
         	html +="<div class=\"thumbnail\">\n <ul id=\"pics\"><li><a class=\"viewImageAction\" href=\""+doc.nodeRefId+"\"><img src=\""+icon+"\" style=\"background: url("+background+";background-repeat:no-repeat \" width=\"75px\" title=\""+(place+1)+". place\"/></a></li></ul></div>\n";
         	html +="<div class=\"pictures\">";
         	html +="<a href=\""+$html(Alfresco.constants.PROXY_URI)+"api/node/content/workspace/SpacesStore/"+doc.nodeRefId+"/"+doc.name+"?a=true\"><img title=\""+doc.contentMimetype+" - "+doc.contentSizeFormatted+"\" src=\""+Alfresco.constants.URL_RESCONTEXT+"components/documentlibrary/images/download-16.png\"/></a></br>";
            html +="<a href=\""+$html(Alfresco.constants.URL_PAGECONTEXT)+"site/"+doc.siteName+"/documentlibrary#filter=path%7C"+doc.sitePath+"\"><img id=\"downloadInfo\" title=\""+doc.displayPath+"\" src=\""+Alfresco.constants.URL_RESCONTEXT+"components/documentlibrary/images/folder-open-16.png\"/></a>";
         		html+= "</div>\n";
         	html += "<div class=\"document\">\n";
         		html += "<div>\n";
         		
         		// http://localhost:8080/share/page/site/demosite/document-details?nodeRef=workspace://SpacesStore/a8f3a268-01e7-41cc-9645-3920855425e6
         		var documentDetailsUrl =$html(Alfresco.constants.URL_PAGECONTEXT)+"site/"+doc.siteName+"/document-details?nodeRef="+doc.nodeRef;
         		if(doc.title){
         			title=doc.abbreviatedTitle;
         			fullTitle=doc.title;
         		}else{
         			title=doc.abbreviatedName;
         			fullTitle=doc.name;
         		}
         		html += "<span title=\""+fullTitle+"\" class=\"doc-title\"><a href=\""+documentDetailsUrl+" \">" + title+ "</a></span>";         		
         		html += "</div>\n";
         	
         		var userpageCreator = Alfresco.constants.URL_PAGECONTEXT+"user/"+doc.creator+"/profile";
         		var userpageModifier = Alfresco.constants.URL_PAGECONTEXT+"user/"+doc.modifier+"/profile";
         		html += "<div>" + doc.hitCount+ " Hits</div>\n";
         		html += "<div class=\"doc-details\"> created " + doc.creationDate+ " by <a href=\""+userpageCreator+"\">"+doc.creatorFormatted+"</a><br/>modified " + doc.modifyDate+ " by <a href=\""+userpageModifier+"\">"+doc.modifierFormatted+"</a></div>\n";
         	html += "</div>\n"; // end tweet
         html += "</div>\n"; // end list-tweet
         
         return html;
      }
   })})
();
