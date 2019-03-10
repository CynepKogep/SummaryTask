<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<%-- 
<style>
*{font-size: 12pt; font-family:Tahoma;}
</style>
--%>
	<c:set var="title" value="Login" />
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%--	
	<script type ="text/javascript" src='js/api.js'></script>
--%> 
	<script src='https://www.google.com/recaptcha/api.js'></script>

	
	<body>
<%--=========================================================================== 
Here we use a table layout.
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%>
		<table id="main-container">
<%--=========================================================================== 
This is the HEADER, containing a top menu.
header.jspf contains all necessary functionality for it.
Just included it in this JSP document.
===========================================================================--%>
			<%-- HEADER --%>
			    <%@ include file="/WEB-INF/jspf/header_login.jsp"%>	
			<%-- HEADER --%>
<%--=========================================================================== 
This is the CONTENT, containing the main part of the page.
===========================================================================--%>
			<tr>
				<td class="content center">
					<%-- CONTENT --%> 
<%--=========================================================================== 
Defines the web form.
===========================================================================--%>
					<form id="login_form" action="controller" method="post">
<%--=========================================================================== 
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
===========================================================================--%>
						<!-- <input type="hidden" name="command" value="login"/> -->
						<input type="hidden" name="command" value="login" />
						<fieldset>
							<legend>
								<fmt:message key="resource_jsp.login" />
							</legend>
							<input name="login" /> 
							<br />
						</fieldset>
						<br />
						<fieldset>
							<legend>
								<fmt:message key="resource_jsp.password" />
							</legend>
							<input type="password" name="password" />
						</fieldset>
						<br />
						<p></p>
						<fieldset>
						<div class="g-recaptcha" data-sitekey="6Ld1cJYUAAAAADTFie_0YU3xaPxUIZwb3W_Riyxu"></div>
						<%-- <span id="captcha" style="margin-left:100px;color:red"></span> --%>
						</fieldset>
						<p></p>
						<input type="submit" value='<fmt:message key="resource_jsp.enter"/>'>
					</form> 
					<%-- CONTENT --%>
				</td>
			</tr>
			<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		</table>
	</body>
</html>