<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.descriptor.form.label.jobDescription" path="jobDescription"/>
	
	<acme:form-return code="authenticated.descriptor.form.button.return"/>
	
	<acme:form-submit code="authenticated.descriptor.form.button.listMandatoryDuty" method ="get" action="/authenticated/mandatory-duty/list?id=${id}"/>
	</acme:form> 

