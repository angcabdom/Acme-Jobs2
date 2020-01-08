<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox readonly="true" code="authenticated.message.form.label.title" path="title"/>
	<acme:form-moment readonly="true" code="authenticated.message.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea readonly="true" code="authenticated.message.form.label.tags" path="tags"/>
	<acme:form-textarea readonly="true" code="authenticated.message.form.label.body" path="body"/>
	<acme:form-textbox readonly="true" code="authenticated.message.form.label.creatorName" path="creatorName"/>
	</jstl:if>
	<jstl:if test="${command != 'show'}">
	<acme:form-textbox code="authenticated.message.form.label.title" path="title"/>
	<acme:form-moment readonly="true" code="authenticated.message.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	<acme:form-textbox readonly="true" code="authenticated.message.form.label.creatorName" path="creatorName"/>
	<acme:form-checkbox code="authenticated.message.form.label.accept" path="accept"/>
	</jstl:if>
<acme:form-submit test="${command == 'create'}" code="authenticated.message.form.button.message.create" action="/authenticated/message/create?idt=${idt}"/>
	<acme:form-return code="authenticated.message.button.return"/>
</acme:form> 

