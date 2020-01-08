<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox placeholder="EMP1-JOB1:WOR1" code="worker.application.form.label.reference" path="reference"/>
	<acme:form-moment code="worker.application.form.label.deadline" path="deadline"/>
	<acme:form-textbox readonly="true" code="worker.application.form.label.status" path="status"/>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
    <acme:form-textarea readonly="true" code="worker.application.form.label.justification" path="justification"/>
    <acme:form-textbox readonly="true" code="worker.application.form.label.jobReference" path="jobReference"/>



<acme:form-submit  test="${command == 'create' }"
	code="worker.application.form.button.create" 
	action="/worker/application/create?idj=${idj}"/>
	
	<acme:form-return code="worker.application.form.button.return"/>
	</acme:form> 

