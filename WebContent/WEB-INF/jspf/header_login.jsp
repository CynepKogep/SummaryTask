<%--=========================================================================== 
Header (top area). 
===========================================================================--%>

<%@ page import="ua.kharkov.khpi.database.enums.Role"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<tr>
	<td id="header">
		<div id="leftHeader">
			<a href="controller?command=viewSettings"> <fmt:message
					key="resource_jsp.settings" />
			</a> &nbsp;
		</div>
<%--		
		<div id="rightHeader"></div>
		<div id="rightHeader">
			<a href="controller?command=registration"> <fmt:message
					key="resource_jsp.registration" />
			</a>
		</div>
--%>		
	</td>
</tr>