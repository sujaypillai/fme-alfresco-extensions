/**
 * Topx Dashlet configuration component POST method
 */

function main()
{
   var c = sitedata.getComponent(url.templateArgs.componentId),
       parentId = String(json.get("parentId")),
       title = String(json.get("title")),
       maxItems = String(json.get("maxItems"));
   
   c.properties["parentId"] = parentId;
   model.parentId= parentId;
   
   c.properties["title"] = title;
   model.title = title;
   
   c.properties["maxItems"] = maxItems;
   model.maxItems = maxItems;
     
   c.save();
}

main();