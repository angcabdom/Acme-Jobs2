<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.authenticated-message-thread.form.label.authenticated" path="authenticatedName"/>
	<acme:form-textbox readonly="true" code="authenticated.authenticated-message-thread.form.label.messageThreadName" path="messageThreadName"/>
	
	<acme:form-return code="authenticated.authenticated-message-thread.button.return"/>
	
	<acme:form-submit test="${command == 'create' }" code="authenticated.authenticated-message-thread.form.button.create" action="/authenticated/authenticated-message-thread/create?idt=${idt}" />
	
	<acme:form-submit test="${command != 'create' && isCreator == true }" code="authenticated.authenticated-message-thread.form.button.delete" action="/authenticated/authenticated-message-thread/delete?id=${id}" />
	
	
	</acme:form> 

