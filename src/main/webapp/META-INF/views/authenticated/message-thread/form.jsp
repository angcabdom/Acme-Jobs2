<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" />
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox readonly="true" code="authenticated.message-thread.form.label.title" path="title" />
	</jstl:if>
	<acme:form-moment readonly="true" code="authenticated.message-thread.form.label.creationMoment" path="creationMoment" />
	<acme:form-textbox readonly="true" code="authenticated.message-thread.form.label.creator" path="creatorName" />

	<acme:form-return code="authenticated.message-thread.form.button.return" />
	<acme:form-submit test="${command != 'create' }" code="authenticated.message-thread.form.button.users" method="get"
		action="/authenticated/authenticated-message-thread/list_by_message_thread?idt=${id}" />
	<acme:form-submit test="${command != 'create' && isCreator == true}" code="authenticated.message-thread.form.button.users.add"
		method="get" action="/authenticated/authenticated-message-thread/create?idt=${id}" />
	<acme:form-submit test="${command != 'create' }" code="authenticated.message-thread.form.button.messages" method="get"
		action="/authenticated/message/list?id=${id}" />

	<acme:form-submit test="${command != 'create'}" code="authenticated.message.form.button.message.create" method="get"
		action="/authenticated/message/create?idt=${id}" />

	<acme:form-submit test="${command == 'create' }" code="authenticated.message-thread.form.button.create"
		action="/authenticated/message-thread/create" />


</acme:form>

