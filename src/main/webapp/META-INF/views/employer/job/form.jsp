<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
    
    <jstl:if test="${command == 'delete'}">
	<jstl:out value="${mandatoryDuties$error}"/>
	</jstl:if>
	
	<jstl:if test="${command == 'delete'}">
	<jstl:out value="${auditRecords$error}"/>
	</jstl:if>
	
	<jstl:if test="${command == 'delete'}">
	<jstl:out value="${applications$error}"/>
	</jstl:if>
	

	<acme:form-textbox placeholder="EMP1-JOB1" code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.descriptor" path="descriptor"/>
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode"/>
	
	
	<acme:form-return code="employer.job.form.button.return"/>
	
	<security:authorize access="hasRole('Worker')">
	<acme:form-submit test="${command != 'create'}" code="employer.job.form.button.apply" method ="get" action="/worker/application/create?idj=${id}"/>
	</security:authorize>
	
	<security:authorize access="hasRole('Auditor')">
	<acme:form-submit test="${command != 'create'}" code="employer.job.form.button.createAuditRecord" method ="get" action="/auditor/audit-record/create?idj=${id}"/>
	</security:authorize>

	<acme:form-submit test="${command != 'create'}" code="employer.job.form.button.listAuditRecord" method ="get" action="/authenticated/audit-record/list?idj=${id}"/>
	<acme:form-submit test="${command != 'create'}" code="employer.job.form.button.mandatoryDuty" method ="get" action="/authenticated/mandatory-duty/list?idj=${id}"/>
	
	<security:authorize access="hasRole('Employer')">
	<acme:form-submit test="${command != 'create' && finalMode == false}" code="employer.mandatoryDuty.form.button.create" method = "get" action="/employer/mandatory-duty/create?idj=${id}" />
	
	<acme:form-submit test="${command == 'show' }" code="employer.job.form.button.update"
		action="/employer/job/update" />

	<acme:form-submit test="${command == 'show' }" code="employer.job.form.button.delete"
		action="/employer/job/delete" />

	<acme:form-submit test="${command == 'create' }" code="employer.job.form.button.create"
		action="/employer/job/create" />

	<acme:form-submit test="${command == 'update' }" code="employer.job.form.button.update"
		action="/employer/job/update" />

	<acme:form-submit test="${command == 'delete' }" code="employer.job.form.button.delete"
		action="/employer/job/delete" />
		
	<acme:form-submit test="${command == 'show' }" code="employer.job.form.button.list.application.byReference" method ="get" 
		action="/employer/application/list_by_reference?idj=${id}" />
		
	<acme:form-submit test="${command == 'show' }" code="employer.job.form.button.list.application.byStatus" method ="get"
		action="/employer/application/list_by_status?idj=${id}" />
		
	<acme:form-submit test="${command == 'show' }" code="employer.job.form.button.list.application.byCreation" method ="get"
		action="/employer/application/list_by_creation?idj=${id}" />
		
	</security:authorize>
	
	
	
	</acme:form> 

