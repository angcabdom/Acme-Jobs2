<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox readonly="true" code="auditor.audit-record.form.label.jobReference" path="jobReference"/>

	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="auditor.audit-record.form.label.creationMoment" path="creationMoment" readonly="true" />
	</jstl:if>
	<acme:form-url code="auditor.audit-record.form.label.body" path="body"/>
	<acme:form-textbox code="auditor.audit-record.form.label.published" path="published"/>

	<acme:form-return code="auditor.audit-record.form.button.return"/>
	
	<acme:form-submit  test="${command == 'create' }" code="auditor.audit-record.form.button.create" 
	action="/auditor/audit-record/create?idj=${idj}"/>
	
	</acme:form> 

