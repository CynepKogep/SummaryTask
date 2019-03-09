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
					<c:when test="${fn:length(patientAssignmentList) < 0}">
					    No such a patient assignmen
					</c:when>
				    <c:otherwise>

                        <%-- -----------------------------------  --%>
                        <%-- Patient Card  --%>
                        <%-- -----------------------------------  --%>
                        <strong>
                            <fmt:message key="resource_jsp.PatientCard"/>
                        </strong> 
                        <c:if test="${localization_value == 'ru'}">
			                ${patient.firstNameRu}, ${patient.lastNameRu}
			            </c:if>
			            <c:if test="${localization_value == 'en'}">
                            ${patient.firstName}, ${patient.lastName}
			            </c:if>
			            <p> </p>
                        <%-- -----------------------------------  --%>
                        <%-- TITLE Current Diagnose  --%>
                        <%-- -----------------------------------  --%>
                        <strong>
                            <fmt:message key="resource_jsp.CurrentDiagnose"/>:
                        </strong>
                        ${patient.diagnosisName}
                        <p> </p>
			            <%-- -----------------------------------  --%>
                        <%-- FORM Current Diagnose  --%>
                        <%-- -----------------------------------  --%>
                        <form action="controller" method="post">
	                        <input type="hidden" name="command" value="setDiagnosisDoctor" />
	                        <input type="hidden" name="patient_id" value="${patient.id}" />
	                        <fmt:message key="resource_jsp.setDiagnose"/> 
	                        <select name = "diagnosis_name">
		                        <c:forEach var="diagnosis" items="${diagnosisList}">
			                        <option value="${diagnosis.getId()}">${diagnosis.getDiagnosisName()}</option>
		                        </c:forEach>
	                        </select>
	                        <c:if test="${localization_value == 'ru'}">
				                <input type="submit"  value = "установить диагноз"/>
				            </c:if>
				            <c:if test="${localization_value == 'en'}">
				                <input type="submit" value='set diagnose' />
				            </c:if> 
                        </form>
                        <p> </p>
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
                       <%-- -----------------------------------  --%>
                       <%-- TABLE CurrentAssignment  --%>
                       <%-- -----------------------------------  --%>
		               <fmt:message key="resource_jsp.doctor.CurrentAssignment"/>:
			           <div class="scrollingTable">
			               <table id="list1">
					           <thead>
						           <tr>
			    	                   <td align="center">ID</td>
				                       <td align="center"><fmt:message key="resource_jsp.doctor.Assignment"/></td>
					                   <td align="center"><fmt:message key="resource_jsp.doctor.AssignmentStatus"/></td>
					                   <td align="center"><fmt:message key="resource_jsp.doctor.CompleteAssignment"/></td>
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
								           <td>
					                           <form action="controller" method="post">
					                               <input type="hidden" name="command" value="completeAssignmentDoctor" />
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
                       <%-- -----------------------------------  --%>
                       <%-- TABLE CompleteAssignments  --%>
                       <%-- -----------------------------------  --%>
			           <fmt:message key="resource_jsp.doctor.CompleteAssignments"/>:
			           <div class="scrollingTable">
				           <table id="list2">
					           <thead>
						           <tr>
    			                       <td align="center">ID</td>
				                       <td align="center"><fmt:message key="resource_jsp.doctor.Assignment"/></td>
				                       <td align="center"><fmt:message key="resource_jsp.doctor.AssignmentStatus"/></td>
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
				           <p> </p>
				           <div style="display: inline-block">
				               <fmt:message key="resource_jsp.doctor.DischargePatient"/>:
				               <c:if test="${localization_value == 'ru'}">
			                       ${patient.firstNameRu}, ${patient.lastNameRu}
			                   </c:if> 
			                   <c:if test="${localization_value == 'en'}">
			                       ${patient.firstName}, ${patient.lastName}
			                   </c:if>
			               </div>
<%-- 				     <form action="controller" method="post" style="text-align:center;"> --%>
                           <form action="controller" method="post" style="display: inline-block">
	                           <input type="hidden" name="command" value="dischargedPatientDoctor" />
	                           <input type="hidden" name="patient_id" value="${patient.id}" />
	                           <c:if test="${localization_value == 'ru'}">
			                       <input type="submit"  value = "Выписать"/>
				               </c:if>
				               <c:if test="${localization_value == 'en'}">
				                   <input type="submit" value='Discharged' />
				               </c:if>
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