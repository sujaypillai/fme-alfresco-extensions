<script type="text/javascript">//<![CDATA[
   new fme.dashlet.topx("${args.htmlid}").setOptions(
   {
      "componentId": "${instance.object.id}",
      "maxItems": 10,
   }).setMessages(
      ${messages}
   );
   new Alfresco.widget.DashletResizer("${args.htmlid}", "${instance.object.id}");
//]]></script>

<div class="dashlet topx-list">
   <div class="title" id="${args.htmlid}-title"></div>
   <div id="${args.htmlid}-body" class="body scrollableList" <#if args.height??>style="height: ${args.height}px;"</#if>>
      <div id="${args.htmlid}-documentsList"></div>
   </div>
</div>