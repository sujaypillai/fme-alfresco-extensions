/*
 * search for documents and add the hitcount aspect with a sample hitcount value.
 */
var path='PATH:"/app:company_home/st:sites/cm:demosite/cm:documentLibrary//*"';
var nodes = search.luceneSearch(path);
var i=3;
for each(n in nodes) {
  if(n.typeShort=="cm:content"){  
	var props = new Array(1);
	props["topx:hitcount"] = i;
	n.addAspect("topx:countable",props);
	i++;  
  }
}
