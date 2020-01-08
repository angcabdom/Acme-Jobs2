<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>

	<acme:form-textbox readonly="true" code="authenticated.mandatory-duty.form.label.jobReference" path="jobReference"/>
	<acme:form-textbox code="authenticated.mandatory-duty.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.mandatory-duty.form.label.dutyDescription" path="dutyDescription"/>
	<acme:form-textbox code="authenticated.mandatory-duty.form.label.percentage" path="percentage"/>
	
	<acme:form-return code="authenticated.mandatory-duty.form.button.return"/>
	<security:authorize access="hasRole('Employer')">
	<acme:form-submit test="${command == 'show' }" code="employer.mandatoryDuty.form.button.update"
		action="/employer/mandatory-duty/update" />

	<acme:form-submit test="${command == 'show' }" code="employer.mandatoryDuty.form.button.delete"
		action="/employer/mandatory-duty/delete" />
		</security:authorize>

	<acme:form-submit test="${command == 'create' }" code="employer.mandatoryDuty.form.button.create"
		action="/employer/mandatory-duty/create?idj=${idj}" />
    
    
	<acme:form-submit test="${command == 'update'  }" code="employer.mandatoryDuty.form.button.update"
		action="/employer/mandatory-duty/update" />

	<acme:form-submit test="${command == 'delete' }" code="employer.mandatoryDuty.form.button.delete"
		action="/employer/mandatory-duty/delete" />
	
	</acme:form> 

