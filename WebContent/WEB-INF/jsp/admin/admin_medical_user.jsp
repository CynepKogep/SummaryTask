<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE ADMIN <<MEDICAL USER>> NUMBER 1 --%> 

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
						<fmt:message key="resource_jsp.admin.Sorting"/>:
					</div>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listMedicalUser" /> 
						<input type="hidden" name="sorting_order" value="sort_by_id" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по id' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort by id' />
						</c:if>
					</form>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listMedicalUser" /> 
						<input type="hidden" name="sorting_order" value="sort_by_login" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по логину' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort by login' />
						</c:if>
					</form>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listMedicalUser" /> 
						<c:if test="${localization_value == 'ru'}">
						    <input type="hidden" name="sorting_order" value="sort_by_lastname_ru" />
							<input type="submit" value='по фамилии'  />
						</c:if>
						<c:if test="${localization_value == 'en'}">
						    <input type="hidden" name="sorting_order" value="sort_by_lastname"/>
							<input type="submit" value='sort by lastname' />
						</c:if>
					</form>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listMedicalUser" /> 
						<c:if test="${localization_value == 'ru'}">
						    <input type="hidden" name="sorting_order" value="sort_by_profession_ru" />
							<input type="submit" value='по специальности'  />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="hidden" name="sorting_order" value="sort_by_profession" />
							<input type="submit" value='sort by profession' />
						</c:if>
					</form>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listMedicalUser" /> 
						<input type="hidden" name="sorting_order" value="sort_by_number_of_pations" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по количеству пациентов'  />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort by number of pations' />
						</c:if>
					</form>
				</div>
				
				<c:choose>
					<c:when test="${fn:length(usersList) == 0}">
					    No such orders
					</c:when>

					<c:otherwise>
						<fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
						<table id="list_medical_user">
							<thead>
								<tr>
									<td align="center" bgcolor="#E0E0E0">№</td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.admin.login" /></td>
									<td align="center" bgcolor="#E0E0E0""><fmt:message key="resource_jsp.admin.lastName" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.admin.firstName" /></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.admin.professionName"/></td>
									<td align="center" bgcolor="#E0E0E0"><fmt:message key="resource_jsp.admin.numberOfPatients"/></td>
								</tr>
							</thead>
							<c:forEach var="bean" items="${usersList}">
								<tr>
									<%-- id --%> 
									<td>
									    ${bean.id}
									</td>
									<td>
									    ${bean.login}
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
									    <c:if test="${localization_value == 'ru'}">
						                    ${bean.professionNameRu}
						                </c:if> 
						                <c:if test="${localization_value == 'en'}">
						                    ${bean.professionName}
						                </c:if>
									</td>
									<td align="center">
						                ${bean.numberOfPatients}
									</td>
								</tr>
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