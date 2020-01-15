<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox readonly="true" code="employer.application.form.label.reference" path="reference"/>
	<acme:form-moment readonly="true" code="employer.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment readonly="true" code="employer.application.form.label.deadline" path="deadline"/>
	
	<jstl:if test="${status == 'pending'}">
		<acme:form-select code="worker.application.form.label.status" path="status">
			<acme:form-option code="worker.application.form.label.status.pending" value="pending" selected="true"/>
			<acme:form-option code="worker.application.form.label.status.rejected" value="rejected"/>
			<acme:form-option code="worker.application.form.label.status.accepted" value="accepted"/>	
		</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'rejected'}">
		<acme:form-select readonly="true" code="worker.application.form.label.status" path="status">
			<acme:form-option code="worker.application.form.label.status.pending" value="pending"/>
			<acme:form-option code="worker.application.form.label.status.rejected" value="rejected" selected="true"/>
			<acme:form-option code="worker.application.form.label.status.accepted" value="accepted"/>	
		</acme:form-select>
	</jstl:if>
	
		<jstl:if test="${status == 'accepted'}">
		<acme:form-select readonly="true" code="worker.application.form.label.status" path="status">
			<acme:form-option code="worker.application.form.label.status.pending" value="pending"/>
			<acme:form-option code="worker.application.form.label.status.rejected" value="rejected"/>
			<acme:form-option code="worker.application.form.label.status.accepted" value="accepted" selected="true"/>	
		</acme:form-select>
	</jstl:if>
	
	<acme:form-textarea code="employer.application.form.label.justification" path="justification"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.qualifications" path="qualifications"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.jobReference" path="jobReference"/>

	<acme:form-return code="employer.application.form.button.return"/>
	
	<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.update"
		action="/employer/application/update" />
	
	<acme:form-submit test="${command == 'update'}" code="employer.application.form.button.update"
		action="/employer/application/update" />
	</acme:form> 

