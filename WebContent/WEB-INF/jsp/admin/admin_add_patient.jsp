<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE ADMIN NUMBER 4 --%> 

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
				<c:if test="${is_user == 'is'}">
					<c:if test="${localization_value == 'ru'}">
				            Такой пациент уже есть! 
				        </c:if>
					<c:if test="${localization_value == 'en'}">
				            This patient already exists!
				        </c:if>
				</c:if> 
				<c:choose>
					<c:when test="${fn:length(patientList) == 0}">No such users</c:when>
				<c:otherwise>
                   
                        
						<form id="settings_form" action="controller" method="post">

							<input type="hidden" name="command" value="RegistrationPatient" /> 
							<input type="hidden" name="name_button" value="button_registration" />
							
							<fieldset>
							<table border="0" cellpadding="0" cellspacing="0"">
							
							    <!-- ---------------------------------------------------------------- -->
							    <!--  First Name (eng) -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;">
				                            Имя (eng): 
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;"> 
				                            First Name (eng):
				                        </td> 
				                    </c:if>
     								<td style="border: 0;"> 
     								    <input name="first_name_registration" required pattern="^[a-zA-Z]+$"> 
     								</td>
							    </tr>
							    <!-- ---------------------------------------------------------------- -->
							    <!--  Last Name (eng) -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;">
				                            Фамилия (eng):
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;">
				                            Last Name (eng):
				                        </td>
				                    </c:if>
								    <td style="border: 0;">
								        <input name="last_name_registration" required pattern="^[a-zA-Z]+$">
								    </td>
							    </tr>
							    <!-- ---------------------------------------------------------------- -->
							    <!--  First Name (rus) -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                         <td style="border: 0;">
				                             Имя (rus):
				                         </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                         <td style="border: 0;">
				                             First Name (rus):
				                         </td>
				                    </c:if>
								    <td style="border: 0;"> 
								         <input name="first_name_ru_registration" required pattern="^[а-яА-Я]+$">
								    </td>
							    </tr>
							    <!-- ---------------------------------------------------------------- -->
							    <!-- Last Name (rus) -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;">
				                            Фамилия (rus):
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;">
				                            Last Name (rus):
				                        </td>
				                     </c:if>
								    <td style="border: 0;">
								        <input name="last_name_ru_registration" required pattern="^[а-яА-Я]+$">
     						        </td>
	    						</tr>
		    					<!-- ---------------------------------------------------------------- -->
			    				<!-- Doctor -->
				    			<!-- ---------------------------------------------------------------- -->
					    		<tr class="noborders">
							        <td style="border: 0;"> 
							            <fmt:message key="resource_jsp.Doctor"/>:
							        </td>
				                    <td style="border: 0;">
				                        <select name="doctor_id_registration">
						                    <c:forEach var="item" items="${usersList}">
								                <c:if test="${item.getRoleId() == 1}">
								                     <option value ="${item.getId()}">${item.getFirstName()}  ${item.getLastName()}</option>
								                </c:if>
						                    </c:forEach>
						                </select>
				                    </td>
							    </tr>
							    <!-- ---------------------------------------------------------------- -->
							    <!-- Date Of Birth  -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;">
				                            День рождения:
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;">
				                            Date Of Birth:
				                        </td>
				                     </c:if>
								    <td style="border: 0;">
								        <input type="date" name="date_of_birth_registration" required="required" max="2020-01-01" min="1900-01-01"">
     						        </td>
	    						</tr>
							    <!-- ---------------------------------------------------------------- -->
							    <!-- Telephon number -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;">
				                            Телефонный номер:
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;">
				                            Telephon number:
				                        </td>
				                     </c:if>
								    <td style="border: 0;">
								        <input name="telephon_number_registration" required pattern="^[0-9]+$">
     						        </td>
	    						</tr>
	    						<!-- ---------------------------------------------------------------- -->
							    <!-- email -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;">
				                            email:
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;">
				                            email:
				                        </td>
				                     </c:if>
								    <td style="border: 0;">
								        <input type="email" name="email_registration" required pattern="^[a-zA-Z0-9@_.]+$">
     						        </td>
	    						</tr>
	    						
							    <!-- ---------------------------------------------------------------- -->
							    <!-- submit -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
							         	<td style="border: 0;">
							         	    <input type="submit" value='Регистрация' />
							         	</td>
							        </c:if>
							        <c:if test="${localization_value == 'en'}">
							            <td style="border: 0;">
								            <input type="submit" value='Registration' />
								        </td>
							        </c:if>
							     </tr>
							</table>
							<!-- ---------------------------------------------------------------- -->
						</fieldset>
						</table>
						</form>
						
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>
</body>
</html>