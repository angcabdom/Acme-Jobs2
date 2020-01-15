<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox placeholder="EMP1-JOB1:WOR1" code="worker.application.form.label.reference" path="reference"/>
	<acme:form-moment code="worker.application.form.label.deadline" path="deadline"/>
	
	<jstl:if test="${status == 'pending' && command != 'create'}">
		<acme:form-select readonly="true" code="worker.application.form.label.status" path="status">
			<acme:form-option code="worker.application.form.label.status.pending" value="pending" selected="true"/>
			<acme:form-option code="worker.application.form.label.status.rejected" value="rejected"/>
			<acme:form-option code="worker.application.form.label.status.accepted" value="accepted"/>	
		</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'rejected' && command != 'create'}">
		<acme:form-select readonly="true" code="worker.application.form.label.status" path="status">
			<acme:form-option code="worker.application.form.label.status.pending" value="pending"/>
			<acme:form-option code="worker.application.form.label.status.rejected" value="rejected" selected="true"/>
			<acme:form-option code="worker.application.form.label.status.accepted" value="accepted"/>	
		</acme:form-select>
	</jstl:if>
	
		<jstl:if test="${status == 'accepted' && command != 'create'}">
		<acme:form-select readonly="true" code="worker.application.form.label.status" path="status">
			<acme:form-option code="worker.application.form.label.status.pending" value="pending"/>
			<acme:form-option code="worker.application.form.label.status.rejected" value="rejected"/>
			<acme:form-option code="worker.application.form.label.status.accepted" value="accepted" selected="true"/>	
		</acme:form-select>
	</jstl:if>
	
	<acme:form-textarea readonly="true" code="worker.application.form.label.justification" path="justification"/>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
    <acme:form-textbox readonly="true" code="worker.application.form.label.jobReference" path="jobReference"/>



<acme:form-submit  test="${command == 'create' }"
	code="worker.application.form.button.create" 
	action="/worker/application/create?idj=${idj}"/>
	
	<acme:form-return code="worker.application.form.button.return"/>
	</acme:form> 

