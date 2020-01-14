<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox readonly="true" code="auditor.audit-record.form.label.jobReference" path="jobReference"/>

	<jstl:if test="${finalMode == true}">
	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == false || command == 'create'}">
	<acme:form-textbox code="auditor.audit-record.form.label.title" path="title" readonly="false"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="auditor.audit-record.form.label.creationMoment" path="creationMoment" readonly="true" />
	</jstl:if>
	
	<jstl:if test="${finalMode == true}">
	<acme:form-url code="auditor.audit-record.form.label.body" path="body" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == false || command == 'create'}">
	<acme:form-url code="auditor.audit-record.form.label.body" path="body" readonly="false"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == true }">
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == false || command == 'create'}">
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" readonly="false"/>
	</jstl:if>
	
	<acme:form-return code="auditor.audit-record.form.button.return"/>
	
	<acme:form-submit  test="${command == 'create'}" code="auditor.audit-record.form.button.create" 
	action="/auditor/audit-record/create?idj=${idj}"/>
	
	<acme:form-submit  test="${finalMode == false}" code="auditor.audit-record.form.button.update" 
	action="/auditor/audit-record/update"/>
	
	
	
	</acme:form> 

