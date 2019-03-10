<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE NURSE <<PATIENT_CARD>> NUMBER 2 --%> 

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
					<c:when test="${fn:length(patientAssignmentList) < 0}">
					    No such a patient assignmen
					</c:when>

					<c:otherwise>

            <%-- -----------------------------------  --%>
            <%-- Patient Card  --%>
            <%-- -----------------------------------  --%>
            <strong> 
                <fmt:message key="resource_jsp.PatientCard"/>:
            </strong>     
            <c:if test="${localization_value == 'ru'}">
			    ${patient.firstNameRu}, ${patient.lastNameRu}
			</c:if>
			<c:if test="${localization_value == 'en'}">
                ${patient.firstName}, ${patient.lastName}
			</c:if>
            <p> </p>
			<%-- -----------------------------------  --%>
            <%-- FORM Current Diagnose  --%>
            <%-- -----------------------------------  --%>
            <strong>
            <fmt:message key="resource_jsp.CurrentDiagnose"/>: 
            </strong>
            ${patient.diagnosisName}
            <p> </p>
<%--            
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
--%>
            <%-- -----------------------------------  --%>
            <%-- FORM Add Assignment  --%>
            <%-- -----------------------------------  --%>
            <form action="controller" method="post">
	            <input type="hidden" name="command" value="makeAssignmentNurse" />
	            <input type="hidden" name="patient_id" value="${patient.id}" /> 
	            <fmt:message key="resource_jsp.addAssignment"/>:
	            <select name="assignment_name">
						<c:forEach var="assignment" items="${assignmentList}">
							<option value="${assignment}">${assignment}</option>
						</c:forEach>
				</select>
				<c:if test="${localization_value == 'ru'}">
				    <input type="submit"  value = "Подача запроса"/>
				</c:if>
				<c:if test="${localization_value == 'en'}">
				    <input type="submit" value='Submit request' />
				</c:if>
            </form>	
            <p> </p>
            <%-- -----------------------------------  --%>
            <%-- TABLE CurrentAssignment  --%>
            <%-- -----------------------------------  --%>
		    <fmt:message key="resource_jsp.nurse.CurrentAssignment"/>:
		    <div>
    			<table id="list1">
	    			<thead>
		    			<tr>
			    			<td align="center" bgcolor="#E0E0E0">ID</td>
							<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.nurse.Assignment"/></td>
							<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.nurse.AssignmentStatus"/></td>
							<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.nurse.CompleteAssignment"/></td>
						</tr>
					</thead>
					<c:forEach var="bean" items="${patientAssignmentList}">
					    <c:if test="${bean.getAssignment_status_id()== 0}">
						    <tr>
								<td>
								    ${bean.getId()}
								</td>
								<td>
                                    ${bean.getAssignmentName()}
						        </td>
							    <td>
                                    ${bean.getAssignmentStatusName()}						                
						        </td>
								<td valign="middle" align="center">
					                <form action="controller" method="post">
					                    <input type="hidden" name="command" value="completeAssignmentNurse" />
					                    <input type="hidden" name="patient_id" value="${patient.id}" />
					                    <input type="hidden" name="assignment_id" value="${bean.getId()}" />   
					                    <c:if test="${localization_value == 'ru'}">
				                            <input type="submit"  value = "Завершить"/>
				                        </c:if>
				                        <c:if test="${localization_value == 'en'}">
				                            <input type="submit" value='Complete' />
				                        </c:if>
				                    </form>
				                </td>
							</tr>
						</c:if>
					</c:forEach>
					</table>
					    </div>
					    <p></p>
						<fmt:message key="resource_jsp.nurse.CompleteAssignments"/>:
						    <div>
						    <table id="list2">
						        <thead>
							        <tr>
			    			            <td align="center" bgcolor="#E0E0E0">ID</td>
							            <td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.nurse.Assignment"/></td>
							            <td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.nurse.AssignmentStatus"/></td>
							        </tr>
						        </thead>
							    <c:forEach var="bean2" items="${patientAssignmentList}">
							        <c:if test="${bean2.getAssignment_status_id() == 1}">
								        <tr>
									        <td>
									            ${bean2.getId()}
									        </td>
									        <td>
                                                ${bean2.getAssignmentName()}						                
						                    </td>
									        <td>
                                               ${bean2.getAssignmentStatusName()}
						                    </td>
								        </tr>
								    </c:if>
							    </c:forEach>
						    </table>
<%--						
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
 --%>                        
						</div>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>