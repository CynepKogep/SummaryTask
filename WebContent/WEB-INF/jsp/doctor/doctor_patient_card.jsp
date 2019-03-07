<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE DOCTOR <<PATIENT_CARD>> NUMBER 2 --%> 

<html>
    <c:set var="title" value="List orders" scope="page" />
    <%@ include file="/WEB-INF/jspf/head.jspf"%>

    <body>
    	<fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
	    <table id="main-container">
		    <%@ include file="/WEB-INF/jspf/header.jspf"%>
		    <tr>
			    <td class="content">
				
				<c:choose>
					<c:when test="${fn:length(patientAssignmentList) == 0}">
					    No such a patient assignmen
					</c:when>

					<c:otherwise>

            <%-- -----------------------------------  --%>
            <%-- Patient Card  --%>
            <%-- -----------------------------------  --%>
            <fmt:message key="resource_jsp.PatientCard"/> 
            <c:if test="${localization_value == 'ru'}">
			    ${patient.firstNameRu}, ${patient.lastNameRu}
			</c:if>
			<c:if test="${localization_value == 'en'}">
                ${patient.firstName}, ${patient.lastName}
			</c:if>
			<%-- -----------------------------------  --%>
            <%-- FORM Current Diagnose  --%>
            <%-- -----------------------------------  --%>
            <fmt:message key="resource_jsp.CurrentDiagnose"/>: ${patient.diagnosisName}
            <form action="controller" method="post">
	            <input type="hidden" name="command" value="setDiagnosisDoctor" />
	            <input type="hidden" name="patient_id" value="${patient.id}" />
	            <fmt:message key="resource_jsp.setDiagnose"/> 
	            <select name = "diagnosis_name">
		            <c:forEach var="diagnosis" items="${diagnosisList}">
			            <option value="${diagnosis.getId()}">${diagnosis.getDiagnosisName()}</option>
		            </c:forEach>
	            </select> 
	            <input type="submit" class="btn btn-success"/>
            </form>
            <%-- -----------------------------------  --%>
            <%-- FORM Add Assignment  --%>
            <%-- -----------------------------------  --%>
            <form action="controller" method="post">
	            <input type="hidden" name="command" value="makeAssignmentDoctor" />
	            <input type="hidden" name="patient_id" value="${patient.id}" /> 
	            <fmt:message key="resource_jsp.addAssignment"/>:
	            <select name="assignment_name">
						<c:forEach var="assignment" items="${assignmentList}">
							<option value="${assignment}">${assignment}</option>
						</c:forEach>
				</select>
	            <input type="submit" class="btn btn-success"/>
            </form>					
					
					
					
					
					    CurrentAssignment:
					    <div class="scrollingTable">
						<table id="list1">
							<thead>
								<tr>
									<td>ID</td>
									<td>Assignment</td>
									<td>AssignmentStatus</td>
									<td>CompleteAssignment</td>
								</tr>
							</thead>
							<c:forEach var="bean" items="${patientAssignmentList}">
							    <c:if test="${bean.getAssignment_status_id()== 0}">
								<tr>
									<td>
									    ${bean.getId()}
									</td>
									<td>
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean.getAssignmentNameRu()}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean.getAssignmentName()}
						                </c:if>
						            </td>
									<td>
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean.getAssignmentStatusNameRu()}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean.getAssignmentStatusName()}
						                </c:if>
						            </td>
									<td>
					                    <form action="controller" method="post">
					                        <input type="hidden" name="command" value="completeAssignmentDoctor" />
					                        <input type="hidden" name="patient_id" value="${patient.id}" />
					                        <input type="hidden" name="assignment_id" value="${bean.getId()}" />   
					                        <input type="submit" class="btn btn-success" value = "Complete"/>
				                        </form>
				                    </td>
								</tr>
								</c:if>
							</c:forEach>
						</table>
						</div>
						CompleteAssignment:
						<div class="scrollingTable">
						<table id="list2">
						<thead>
							<tr>
								<td>ID</td>
								<td>Assignment</td>
								<td>AssignmentStatus</td>
							</tr>
						</thead>
							<c:forEach var="bean2" items="${patientAssignmentList}">
							    <c:if test="${bean2.getAssignment_status_id() == 1}">
								<tr>
									<td>
									    ${bean2.getId()}
									</td>
									<td>
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean2.getAssignmentNameRu()}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean2.getAssignmentName()}
						                </c:if>
						            </td>
									<td>
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean2.getAssignmentStatusNameRu()}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean2.getAssignmentStatusName()}
						                </c:if>
						            </td>
								</tr>
								</c:if>
							</c:forEach>
						</table>
					    DiaschargePatient 
							<c:if test="${localization_value == 'ru'}">
						        ${patient.firstNameRu}, ${patient.lastNameRu}
						    </c:if> 
						    <c:if test="${localization_value == 'en'}">
						        ${patient.firstName}, ${patient.lastName}
						    </c:if>
                        <form action="controller" method="post" style="text-align:center;">
	                        <input type="hidden" name="command" value="dischargedPatientDoctor" />
	                        <input type="hidden" name="patient_id" value="${patient.id}" />
	                        <input type="submit" value="Discharged">
                        </form>
						</div>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>