<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE DOCTOR <<PATIENT>> NUMBER 1 --%> 

<html>
    <c:set var="title" value="List orders" scope="page" />
    <%@ include file="/WEB-INF/jspf/head.jspf"%>

    <body>
    	<fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
	    <table id="main-container">
		    <%@ include file="/WEB-INF/jspf/header.jspf"%>
		    <tr>
			    <td class="content">
				<%-- CONTENT --%> 
				<div>
					<div style="display: inline-block">
                        <fmt:message key="resource_jsp.doctor.Sorting"/>:					
                    </div>
                    <%--
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listPatientForDoctor" /> 
						<input type="hidden" name="sorting_order" value="sort_by_id" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по id' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort by id' />
						</c:if>
					</form>
					--%>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listPatientForDoctor" /> 
						<c:if test="${localization_value == 'ru'}">
						    <input type="hidden" name="sorting_order" value="sort_by_lastname_ru" />
							<input type="submit" value='по фамилии'  />
						</c:if>
						<c:if test="${localization_value == 'en'}">
						    <input type="hidden" name="sorting_order" value="sort_by_lastname"/>
							<input type="submit" value='sort by lastname' />
						</c:if>
					</form>
					<%--
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listPatientForDoctor" /> 
						<input type="hidden" name="sorting_order" value="sort_by_email" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по почтовому ящику'  />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort by email' />
						</c:if>
					</form>
					--%>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listPatientForDoctor" /> 
						<input type="hidden" name="sorting_order" value="sort_by_date_of_birth" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по дню рождению' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort by date of birth' />
						</c:if>
					</form>
				</div>
				
				<c:choose>
					<c:when test="${fn:length(patient_list) == 0}">
					    No such orders
					</c:when>

					<c:otherwise>
						<fmt:message key="settings_jsp.label.localization.value"
							var="localization_value" />
						<table id="list_order_table">
							<thead>
								<tr>
									<td align="center" bgcolor="#E0E0E0">№</td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.doctor.lastName" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.doctor.firstName" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.doctor.telephoneNumber" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.doctor.email" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.doctor.dateOfBirth" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.doctor.CardOfPatient" /></td>
								</tr>
							</thead>
							<c:forEach var="bean" items="${patient_list}">
							    <c:if test="${!bean.discharged}">
								<tr>
									<%-- id --%> 
									<td>
									    ${bean.id}
									</td>
									<td>
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean.lastNameRu}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean.lastName}
						                </c:if>
						            </td>
									<td>
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean.firstNameRu}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean.firstName}
						                </c:if>
						            </td>
									<td>
									    ${bean.telephoneNumber}
									</td>
									<td>
						                ${bean.email}
									</td>
									<td>
						                ${bean.dateOfBirth}
									</td>
									<td align="center" style="vertical-align: middle">
				                        <form action="controller" method="post">
					                        <input type="hidden" name="command" value="patientCardDoctor" />
					                        <input type="hidden" name="patient_id" value="${bean.getId()}"/>   
					                        <%-- <input type="submit" class="btn btn-success" value = <fmt:message key="resource_jsp.OpenCard"/>/> --%>
					                        <c:if test="${localization_value == 'ru'}">
							                    <input type="submit" value='открыть карту' />
						                    </c:if>
						                    <c:if test="${localization_value == 'en'}">
							                     <input type="submit" value='open card' />
							                </c:if>
					                        
				                        </form>
				                    </td>
								</tr>
								</c:if>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>