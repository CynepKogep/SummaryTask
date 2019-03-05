<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE ADMIN NUMBER 3 --%> 

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
				            Такой пользователь уже есть! 
				        </c:if>
					<c:if test="${localization_value == 'en'}">
				            This user already exists!
				        </c:if>
				</c:if> 
				<c:choose>
					<c:when test="${fn:length(usersList) == 0}">No such users</c:when>
				<c:otherwise>
                   
                        
						<form id="settings_form" action="controller" method="post">

							<input type="hidden" name="command" value="RegistrationMedicalUser" /> 
							<input type="hidden" name="name_button" value="button_registration" />
							
							<fieldset>
							<table border="0" cellpadding="0" cellspacing="0" width="50%">
							
							    <!-- ---------------------------------------------------------------- -->
							    <!-- Login -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                        <td style="border: 0;"> 
				                            Логин:
				                        </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;"> 
				                            Login: 
				                        </td> 
				                    </c:if>
								    <td style="border: 0;"> 
								        <input name="login_registration" required pattern="^[0-9a-zA-Z]+$"> 
								    </td> 
								</tr>	
							    <!-- ---------------------------------------------------------------- -->
							    <!-- Password -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">
									<c:if test="${localization_value == 'ru'}">
				                       <td style="border: 0;"> 
				                           Пароль:
				                       </td>
				                    </c:if>
									<c:if test="${localization_value == 'en'}">
				                        <td style="border: 0;"> 
				                            Password: 
				                        </td> 
				                    </c:if>
								    <td style="border: 0;">  
								        <input name="password_registration" required pattern="^[0-9a-zA-Z]+$"> 
								    </td>
							    </tr>
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
			    				<!-- Profession -->
				    			<!-- ---------------------------------------------------------------- -->
					    		<tr class="noborders">
							        <td style="border: 0;"> 
							            <fmt:message key="resource_jsp.profession"/>:
							        </td>
				                    <td style="border: 0;">
				                        <select name="profession_registration">
						                    <c:forEach var="item" items="${professionList}">
							                   <option value="${item.getId()}">${item.professionName}</option>
						                    </c:forEach>
				                        </select>
				                    </td>
							    </tr>
							    <!-- ---------------------------------------------------------------- -->
							    <!-- Role -->
							    <!-- ---------------------------------------------------------------- -->
							    <tr class="noborders">							
							        <td style="border: 0;"> 
							            <fmt:message key="resource_jsp.Role"/>:
							        </td>
				                    <td style="border: 0;">
				                        <select name="role">
						                    <c:forEach var="item" items="${role}">
							                    <option value="${item}">${item.getName()}</option>
						                    </c:forEach>
				                        </select>
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