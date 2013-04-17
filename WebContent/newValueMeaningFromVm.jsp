<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<sj:div id="newVmFromVmDiv"
	cssClass="result ui-widget-content ui-corner-all">
	<sj:div align="center">
		<strong>Create New VM from the Selected VM</strong>
	</sj:div>
	<sj:div id="vmInfoDiv">
		<s:form id="editVmForm" action="ValueMeaningAction" target="_blank">
			<s:textfield name="longName" label="VM Long Name" value="Lung" />
			<s:textfield name="version" label="Version" value="1.0" />
			<s:textarea name="description" label="Description/Definition"
				value="One of a pair of viscera occupying the pulmonary cavities of the thorax, the organs of respiration in which aeration of the blood takes place. As a rule, the right lung is slightly larger than the left and is divided into three lobes (an upper, a middle, and a lower or basal), while the left has but two lobes (an upper and a lower or basal). Each lung is irregularly conical in shape, presenting a blunt upper extremity (the apex), a concave base following the curve of the diaphragm, an outer convex surface (costal surface), an inner or mediastinal surface (mediastinal surface), a thin and sharp anterior border, and a thick and rounded posterior border. SYN pulmo. "
				rows="4" cols="80">
			</s:textarea>
			<s:url id="searchConceptForVm2Url" value="searchConceptForVm2.jsp" />
			<sj:dialog id="searchConceptForVm2" autoOpen="false" modal="false"
				title="vm options diaglog" openTopics="openRemoteDialog"
				position="right" height="600" width="1100"
				closeTopics="closeSearchConceptDialog" />
		</s:form>
		<br>
		<table width="100%">
			<tr>
				<td>Concepts:</td>
				<td><sj:a openDialog="searchConceptForVm2"
						openDialogTitle="Search EVS Concept"
						href="%{searchConceptForVm2Url}" button="true"
						buttonIcon="ui-icon-newwin">
    	Search New Concept
   			</sj:a></td>
			</tr>
			<tr>
				<td></td>
				<td><br> <sj:div id="newVmFromVmConceptTableDiv">
						<s:include value="conceptsGrid.jsp">
							<s:param name="withParent">no</s:param>
							<s:param name="gridModel">addedPvConceptModel</s:param>
							<s:param name="gridId">addedPvConceptGrid</s:param>
							<s:param name="gridCaption">VM Concepts</s:param>
							<s:param name="readOnly">no</s:param>
						</s:include>
					</sj:div><br>
				</td>
			</tr>
			<tr>
				<s:url id="secondMatchingVmsUrl" value="secondMatchedVmsGrid.jsp" />
				<sj:dialog id="secondMatchingVmDialog"
					href="%{secondMatchingVmsUrl}" title="Matching VMs"
					autoOpen="false" modal="true" height="400" width="1000" modal="false"
					closeTopics="closeSecondVmMatchDialog" position="right">
				</sj:dialog>
				<td colspan="2" align="right"><sj:a
						openDialog="secondMatchingVmDialog" button="true"
						buttonIcon="ui-icon-newwin">Save the VM</sj:a> <%--
		<s:url id="selectedVmResultUrl" action="selectedVmResult" />
		<sj:a id="useSelectedVm" indicator="indicator2"
			href="%{selectedVmResultUrl}" targets="selectedVmResultDiv"
			button="true" buttonIcon="ui-icon-gear"
			onClickTopics="closeSearchedConceptDialog" onclick="hideDiv('vmDiv')">Save the VM</sj:a>--%>
					<sj:a id="cancelNewVmFromConcept" onclick="hideDiv('vmDiv')"
						button="true" buttonIcon="ui-icon-gear">
    	Cancel
    </sj:a></td>
			</tr>
		</table>
	</sj:div>

</sj:div>