<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE ADMIN <<MEDICAL USER>> NUMBER 1 --%> 

<html>

    <c:set var="title" value="List orders" scope="page" />
    <%@ include file="/WEB-INF/jspf/head.jspf"%>

    <body>
	    <table id="main-container">
		    <%@ include file="/WEB-INF/jspf/header.jspf"%>

		    <tr>
			    <td class="content">
				<%-- CONTENT --%> 
				<div>
					<div style="display: inline-block">
						<c:if test="${localization_value == 'ru'}">
			                Сортировка
			            </c:if>
			            <c:if test="${localization_value == 'en'}">
			                Sorting
			            </c:if>    
					</div>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listClientPays" /> 
						<input type="hidden" name="command_number" value="01_number" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по номеру' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort number' />
						</c:if>
					</form>
					<form style="display: inline-block" id="make_order" action="controller">
						<input type="hidden" name="command" value="listClientPays" /> 
						<input type="hidden" name="command_number" value="02_data" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по дате' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort date' />
						</c:if>
					</form>

					<form style="display: inline-block" id="make_order"
						action="controller">
						<input type="hidden" name="command" value="listClientPays" /> 
						<input type="hidden" name="command_number" value="03_data_desc" />
						<c:if test="${localization_value == 'ru'}">
							<input type="submit" value='по дате (desc)' />
						</c:if>
						<c:if test="${localization_value == 'en'}">
							<input type="submit" value='sort date desc' />
						</c:if>
					</form>
				</div>
				
				<c:choose>
					<c:when test="${fn:length(usersList) == 0}">
					    No such orders
					</c:when>

					<c:otherwise>
						<fmt:message key="settings_jsp.label.localization.value"
							var="localization_value" />
						<table id="list_order_table">
							<thead>
								<tr>
									<td>№</td>
									<td><%--<fmt:message key="resource_jsp.second_name" />--%></td>
									<td><%--<fmt:message key="resource_jsp.first_name" />--%></td>
									<td><%--<fmt:message key="resource_jsp.access" />--%></td>
									<td></td>
									<td><%--countBlockCard--%></td>
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
									    ${bean.countBlockCard}
									</td>
									<td>${bean.countBlockCard}</td>
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