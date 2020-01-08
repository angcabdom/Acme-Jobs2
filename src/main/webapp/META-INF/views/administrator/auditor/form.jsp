<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox readonly="true" code="administrator.auditor.form.label.firm" path="firm"/>
	<acme:form-textarea readonly="true" code="administrator.auditor.form.label.responsabilityStatement" path="responsabilityStatement"/>
	<acme:form-checkbox  code="administrator.auditor.form.label.accepted" path="accepted"/>

	<acme:form-return code="administrator.auditor.form.button.return"/>
	
	<acme:form-submit test="${command == 'show' }" code="administrator.auditor.form.button.update"
		action="/administrator/auditor/update" />
	
	<acme:form-submit test="${command == 'update' }" code="administrator.auditor.form.button.update"
		action="/administrator/auditor/update" />
	</acme:form> 

