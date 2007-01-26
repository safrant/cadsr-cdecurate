<!-- Copyright (c) 2006 ScenPro, Inc.
    $Header: /cvsshare/content/cvsroot/cdecurate/ui/jsp/jsp/ACOrigin.jsp,v 1.6 2007-01-26 17:30:13 hegdes Exp $
    $Name: not supported by cvs2svn $
-->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- goes to secondary window error page if error occurs -->
<%@ taglib uri="/WEB-INF/tld/curate.tld" prefix="curate" %>
<curate:checkLogon name="Userbean" page="/jsp/ErrorPageWindow.jsp" />
<html>
	<head>
		<title>
			List of Origins
		</title>

		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="FullDesignArial.css" rel="stylesheet" type="text/css">
		<%@ page import="java.text.*"%>
		<%@ page import="gov.nih.nci.cadsr.cdecurate.tool.*"%>
		<%
			Vector vSource = (Vector) session.getAttribute("vSource");
      if (vSource == null) vSource = new Vector();
      UtilService util = new UtilService();
    %>
    
		<Script Language="JavaScript">
			var sSource = "";
			
			function getSource(sSrc)
			{
				if (sSrc != null && sSrc != "")
				{
					sSource = sSrc;  //store it in the variable
					var useSel = document.getElementById("editSelectedBtn");					//enable the use selection button
					if (useSel != null)
						useSel.disabled = false;
				}
			}
			
			
			function appendOrigin()
			{		
				if (sSource == null || sSource == "")
				{
					alert("Please select the Origin");
					return false;
				}		
				var success = false;
				var curelm;
				if (opener != null && opener.document != null)
				{
					var curelmInd = opener.document.getElementById("currentElmID");
					if (curelmInd != null)
					{
						curelm = curelmInd.value;
						if (curelm != null && curelm != "")
						{
							if (curelm == "allOrigin")
							{
								opener.changeAll(curelm, sSource);
								success = true;
							}
							else
							{
									var curPV = opener.document.getElementById("currentPVInd");
									opener.changeElementText(curPV, curelm, false, sSource, "replace");
									success = true;
									if (curPV != null && curPV.value != null)
										opener.document.getElementById("editPVInd").value = curPV.value;
										
									opener.document.getElementById("currentOrg").value = sSource;
							 }
						}
					}
				}
				if (success == false)
					alert(curelm + " Unable to append the Origin for the selected Permissible Value " + sSource);
				
				//close the window
				window.close();
			}
		</SCRIPT>    
	</head>

	<body marginwidth='2px' marginheight="2px">
		<p> 
		    <input type="button" name="editSelectedBtn" value="Use Selection" onClick="javascript:appendOrigin();"" disabled style="width: 100,height: 30">
        &nbsp;&nbsp;
        <input type="button" name="closeBtn" value="Close Window" onClick="javascript:window.close();" style="width: 93,height: 30">
       	&nbsp;&nbsp;
		</p>
		<p>
			<font size=4>Select<font color="#FF0000">	Origin</font>
			</font>
		</p>
		<table border="1">
			<tr>
				<th height="30" width="30"><img src="../../cdecurate/Assets/CheckBox.gif" border="0"></th>
				<th>Origin Name</th>
			</tr>
			<%
				for (int i=0; i<vSource.size(); i++)
				{
					String src = (String)vSource.elementAt(i);
					if (src == null) src = "";
					String srcId = util.parsedStringSingleQuote(src);
			%>
			<tr>
				<td>
					<input name="rSRC" type="radio"  alt="Select to use" value="" onclick="javascript:getSource('<%=srcId%>');">
				</td>
				<td>
					<%=src%>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>
