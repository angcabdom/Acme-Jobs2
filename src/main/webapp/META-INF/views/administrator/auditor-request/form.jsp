<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox readonly="true" code="administrator.auditorRequest.form.label.authenticatedUsername" path="authenticatedUsername"/>
	<acme:form-textbox readonly="true" code="administrator.auditorRequest.form.label.firm" path="firm"/>
	<acme:form-textarea readonly="true" code="administrator.auditorRequest.form.label.statement" path="responsabilityStatement"/>
	<acme:form-checkbox  code="administrator.auditorRequest.form.label.accepted" path="accepted"/>
	
	
	<acme:form-submit  code="administrator.auditorRequest.form.button.update" action="/administrator/auditor-request/update"/>
	<acme:form-return code="administrator.auditorRequest.form.button.return"/>
</acme:form>
