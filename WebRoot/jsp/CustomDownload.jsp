<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tld/curate.tld" prefix="curate"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Customizable download</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="js/dojo/dijit/themes/claro/claro.css"
        />
        <style type="text/css">
            body, html { font-family:helvetica,arial,sans-serif; font-size:90%; }
        </style>
	    <style type="text/css">
	        @import "js/dojo/dojox/grid/enhanced/resources/claroEnhancedGrid.css";
	        @import "js/dojo/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
	        .dojoxGrid table { margin: 0; } html, body { width: 100%; height: 100%;
	        margin: 0; }
	    </style>
  </head>
  
  <body class=" claro ">
          <!-- Header -->
            <curate:header displayUser = "false"/>
        <!-- Main Area -->
    
        
      <form name="columnSubmission" method="post" action="../../cdecurate/NCICurationServlet?reqType=cdlColumns">
            <input type="hidden" name="cdlColumns" value=""/>
        </form>
      <button type="button" onClick="submitSelectedColumnNames();">Submit Selected Columns</button>
      <button type="button" onClick="toggleView();">Toggle View</button>
      <input type="checkbox" name="fillIn" value="true"/> Check to fill in all values.
      <br></br>
      <br></br>
      <% ArrayList<String> rows = (ArrayList<String>) session.getAttribute("rows"); %>
      <font size="4"><%=rows.size()%> elements selected for download.</font>
      
      <div id="customDownloadContainer" style="width: 80%; height: 50%; display: block"></div> 
      <div id="simpleViewContainer" style="width: 80%; height: 50%; display: none">
          <form>
          <table border="0">
              <tr>
                  <td>
                      <select id="notSelectedCols"></select>
                  </td>
                  <td align="center" valign="middle">
                      <input type="button" value="--&gt;"
                          onclick="moveOptions(this.form.notSelectedCols, this.form.selectedCols, 0);" /><br />
                      <input type="button" value="&lt;--"
                          onclick="moveOptions(this.form.selectedCols, this.form.notSelectedCols, 1);" />
                  </td>
                  <td>
                      
                      <select id="selectedCols"></select>
                  </td>
                  <td><input type="button" value="up" onclick="moveOption(this.form.selectedCols,'up');"/>
                  <input type="button" value="down" onclick="moveOption(this.form.selectedCols, 'down');"/></td>
              </tr>
              
          </table>
          </form>
      </div>
        <script type="text/javascript">
            var djConfig = {
            parseOnLoad: true,
            isDebug: true,
            locale: 'en-us'
            };
        </script>
        <script src="js/dojo/dojo/dojo.js"></script>
        
        <script type="text/javascript">
        
        	var cdGrid;
            var dndPlugin;
            var gdHeaderMap;
            var gdHeaderArray;
            function addOption(theSel, theText, theValue)
            {
		    	var newOpt = new Option(theText, theValue);
		    	var selLength = theSel.length;
		    	theSel.options[selLength] = newOpt;
            }
            
            function deleteOption(theSel, theIndex)
            { 
		    var selLength = theSel.length;
		    if(selLength>0)
		    {
		   	 theSel.options[theIndex] = null;
		    }
            }
            
            function moveOptions(theSelFrom, theSelTo, dir)
            {
            
		    var selLength = theSelFrom.length;
		    var selectedText = new Array();
		    var selectedValues = new Array();
		    var selectedCount = 0;

		    var i;

		    // Find the selected Options in reverse order
		    // and delete them from the 'from' Select.
		    for(i=selLength-1; i>=0; i--)
		    {
		    if(theSelFrom.options[i].selected)
		    {
		    selectedText[selectedCount] = theSelFrom.options[i].text;
		    selectedValues[selectedCount] = theSelFrom.options[i].value;
		    deleteOption(theSelFrom, i);
		    selectedCount++;
		    }
		    }

		    // Add the selected text/values in reverse order.
		    // This will add the Options to the 'to' Select
		    // in the same order as they were in the 'from' Select.
		    for(i=selectedCount-1; i>=0; i--)
		    {
		    addOption(theSelTo, selectedText[i], selectedValues[i]);
		    }
            
            }
            
            function moveOption(theSel, direction) 
            {
            	    var selLength = theSel.length;
		    var selectedCount = 0;

		    var i;
		    
		    for(i=selLength-1; i>=0; i--)
		    {
		    	if(theSel.options[i].selected)
		    	{
		    		break;
		    	}
		    }
		    
		    swap(theSel, i, direction);
		    
            }
            
            function swap(element,i, direction) 
            {
            	
            	var selLength = element.length;
            	
            	if (direction == 'up' && i > 0)
            	{
            		var temp = new Option(element.options[i-1].text,element.options[i-1].value);
			        var temp2 = new Option(element.options[i].text,element.options[i].value);
			        element.options[i-1] = temp2;
			        element.options[i-1].selected = true;
			        element.options[i] = temp;
            	}
            	if (direction == 'down' && i < selLength- 1)
            	{
            		 var temp = new Option(element.options[i+1].text,element.options[i+1].value);
			        var temp2 = new Option(element.options[i].text,element.options[i].value);
			        element.options[i+1] = temp2;
			        element.options[i+1].selected = true;
			        element.options[i] = temp;
            	}
            }
            
            function toggleView() {
                var cust = dojo.byId("customDownloadContainer"); 
                var simp = dojo.byId("simpleViewContainer");
                
                divstyle = cust.style.display;
                if(divstyle.toLowerCase()=="block" || divstyle == "")
                {
                    cust.style.display = "none";
                    simp.style.display = "block";
                    syncFromGrid();
                }
                else
                {
                    cust.style.display = "block";
                    simp.style.display = "none";
                    selectInGrid();
                }
            }

            function selectInGrid() {
            	dndPlugin.cleanCellSelection();
            	var sel = document.forms[1].selectedCols;
            	for (var i=0;i<sel.options.length;i++) {
            		var col = getCol(sel.options[i].text);
            		if (col != null) dndPlugin.selectColumn(col.index);
            	}
            }

            function getCol(colName) {
				if (cdGrid != null && dndPlugin != null) {
					return getGdHeaderMap()[colName];
				}

				return null;
            }

            function getGdHeaderMap() {
				if (gdHeaderMap == null) {
					gdHeaderMap = {};
					for (var i=0;i<dndPlugin.getHeaderNodes().length;i++) {
						var nme = cdGrid.getCell(i).name;
						gdHeaderMap[nme] = cdGrid.getCell(i);
					}
				}

				return gdHeaderMap;
            }

            function getGdHeaderArray() {
				if (gdHeaderArray == null) {
					gdHeaderArray = new Array();
					for (var i=0;i<dndPlugin.getHeaderNodes().length;i++) {
						var nme = cdGrid.getCell(i).name;
						gdHeaderArray[i] = nme;
					}
				}

				return gdHeaderArray;
            }
            
            function syncFromGrid() {
            	var sel = document.forms[1].selectedCols;
            	var notSel = document.forms[1].notSelectedCols;
            	moveAllLeft();
            	var leftMap = getNotSelectedMap();
            	var selectedCols = getSelectedSpans();
            	var gdHeaderArray = getGdHeaderArray();
            	for (var i=0;i<dndPlugin.getHeaderNodes().length;i++) {
					if (selectedCols.indexOf(gdHeaderArray[i]) != -1) {
						var colName = cdGrid.getCell(i).name;
						var optn = leftMap[colName];
						if (optn != null) {
							addOption(sel, optn.text, optn.value);
							deleteOption(notSel, optn.index);
						}
					}
                }
            }

            function moveAllLeft() {
                var sel = document.forms[1].selectedCols;
                var notSel = document.forms[1].notSelectedCols;
            	var len = sel.length
            	while (sel.options.length > 0) {
					addOption(notSel, sel.options[0].text, sel.options[0].value);
					deleteOption(sel, 0);
                }
            }

            function getNotSelectedMap() {
				var notSelMap = {};
				var notSel = document.forms[1].notSelectedCols;
				var len = notSel.length;
				for (var i=0;i<len;i++) {
					nme = notSel.options[i].text;
					notSelMap[nme] = notSel.options[i];
				}

				return notSelMap;
            }

            function selectColumn(evt) {
        		var nme = evt.target.firstChild.nodeValue;
        		var headCell = getGdHeaderMap()[nme];
        		if (headCell != null) dndPlugin.selectColumn(headCell.index); 
        	}
            
            function submitSelectedColumnNames() {
                
                var cols = {};
                var returnCols = "";
                var i=0;
				
                cols = getSelectedSpans();
                
                for (i=0; i < cols.length; i++) {
                 	returnCols = returnCols + cols[i];
	                  if (i < cols.length-1) {
	                  	returnCols = returnCols+",";    
             	     }
                }
                
                document.columnSubmission.cdlColumns.value = cols;
                document.columnSubmission.submit();
            }
            
            
            function getSelectedSpans() {
	      	   var allSelectedSpans = dojo.query(".dojoxGridHeaderSelected div > span[id^='caption'] ");
	       	   var cols = new Array();
	       	   var i = 0;
	           for (i=0; i < allSelectedSpans.length; i++){
	                  var mySpan = allSelectedSpans[i];
	                  var spanText;
	                  
	                  if (mySpan.innerText == undefined)
	                  	spanText = mySpan.textContent;
	                  else
	                  	spanText = mySpan.innerText;
	                  
	                  cols[i] = spanText;
	                 
              	}
	           return cols;
            }
            
            dojo.require("dijit.form.MultiSelect");
           
            dojo.require("dojox.grid.EnhancedGrid");
            dojo.require("dojo.data.ItemFileReadStore");
            dojo.require("dojox.data.KeyValueStore");
            dojo.require("dojox.grid.enhanced.plugins.DnD");
            dojo.require("dojox.grid.enhanced.plugins.NestedSorting");//This is a must as DnD depends on NestedSorting feature
            
            // our data store:
            var completeData = new dojo.data.ItemFileReadStore({
            url:"NCICurationServlet?reqType=jsonRequest"
            });
            
            var cdlLayout = [
            <%   
            ArrayList<String> headers = (ArrayList<String>) session.getAttribute("headers");
            ArrayList<String> types = (ArrayList<String>) session.getAttribute("types");
            HashMap<String,ArrayList<String[]>> typeMap = (HashMap<String,ArrayList<String[]>>) session.getAttribute("typeMap");
            
            for (int colLoop = 0; colLoop < headers.size(); colLoop++) {
            
            if (typeMap.get(types.get(colLoop)) != null) {
            	String[] subHeaders = typeMap.get(types.get(colLoop)).get(0);
            	
            	for (int i = 0; i < subHeaders.length; i++) {
			out.println("{");
			//Take Column Name from headers and take correct column from current row
			out.println("field:\""+subHeaders[i]+"\",");
			out.println("name:\""+""+subHeaders[i]+"\",");
			out.println("width:"+"10");
			out.println("}");
			out.println(",");
		    
            	}
            } else {
		    out.println("{");
		    //Take Column Name from headers and take correct column from current row
		    out.println("field:\""+headers.get(colLoop)+"\",");
		    out.println("name:\""+""+headers.get(colLoop)+"\",");
		    out.println("width:"+"10");
		    out.println("}");
		    if (colLoop != headers.size()-1)
		    out.println(",");
		    }
            }
            %>	    ];
            
            dojo.addOnLoad(function() {
                var notSel = dojo.byId('notSelectedCols');
                
                <% for (int colLoop = 0; colLoop < headers.size(); colLoop++) { %>
                    var c = dojo.doc.createElement('option');
                    
			<% if (typeMap.get(types.get(colLoop)) != null) {
				String[] subHeaders = typeMap.get(types.get(colLoop)).get(0);
	
				for (int i = 0; i < subHeaders.length; i++) { %>
					c = dojo.doc.createElement('option');
					c.innerHTML = '<%=subHeaders[i]%>';
					c.value = '<%=subHeaders[i]%>';
					notSel.appendChild(c);
				<%  } %>

			<%} else {%>
				c.innerHTML = '<%=headers.get(colLoop)%>';
				c.value = '<%=headers.get(colLoop)%>';
				notSel.appendChild(c);
                    <%  } %>
                    
                <%  } %>
                
                var sel = dojo.byId('selectedCols');
                
                new dijit.form.MultiSelect({
                name: 'notSelectedCols'
                },
                notSel);
                
                new dijit.form.MultiSelect({
                name: 'selectedCols'
                },
                sel);
                });
            
            
            
            // create a new grid:
            cdGrid = new dojox.grid.EnhancedGrid({
            query: {},
            store: completeData,
            clientSort: true,
            rowSelector: '20px',
            structure: cdlLayout,
            plugins:{dnd: true}
            },
            document.createElement('gridDiv'));
            // append the new grid to the div "customDownloadContainer":
            dojo.byId("customDownloadContainer").appendChild(cdGrid.domNode);
            dndPlugin = new dojox.grid.enhanced.plugins.DnD(cdGrid);
            cdGrid.pluginMgr.preInit();
            cdGrid.pluginMgr.postInit();
            // Call startup, in order to render the grid:
            cdGrid.startup();
            dojo.connect(cdGrid, "onHeaderCellClick", selectColumn);
           
        </script>	
        
           <!-- Footer -->
        <curate:footer/>
  </body>
</html>