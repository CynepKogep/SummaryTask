<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%-- PAGE DOCTOR NUMBER 3 --%> 

<html>
    <c:set var="title" value="List orders" scope="page" />
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
    <body>
    	<fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
	    <table id="main-container">
		    <%@ include file="/WEB-INF/jspf/header.jspf"%>
            <tr>
                <td class="content">
                    <%--
                    <div id = "myText">
                    --%>
                    <div id ="content">
                        ${information}
                    </div>
                    <div id="editor"></div>
                    <p>
                        <button id="cmd">Сохранить PDF</button>
                    </p>
                    <%--
                    <a href="#" onclick="getPDF()">Скачать PDF</a>
                    --%> 
                </td>
            </tr>
            
		    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
	    </table>
    </body>
<%--    
    <script
        src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous">
    </script>
	<script src="js/jspdf.min.js"></script>
	<script src="js/html2canvas.js"></script>
	<script>
        function getPDF() {
            doCanvas();
        }

        function doCanvas() {
            html2canvas(document.querySelector("#myText")).then(canvas => {
                doPDF(canvas);
            });
        }

        function doPDF(canvas) {
            var doc = new jsPDF({
                format: 'a4',
            });
            
            doc.addImage(canvas.toDataURL(), 'PNG', 0, 0);
            doc.save('test.pdf');
        }
	</script>
--%>
    <script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
    <script>
        var doc = new jsPDF();
        var specialElementHandlers = {
            '#editor': function (element, renderer) {
                return true;
            }
        };
 
        $('#cmd').click(function () {   
            doc.fromHTML($('#content').html(), 15, 15, {
                'width': 170,
                'elementHandlers': specialElementHandlers
            });
            doc.save('sample-file.pdf');
        });
 
        // This code is collected but useful, click below to jsfiddle link.
    </script>
    
    
</html>