<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.request_.form.label.title" path="title"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.request_.form.label.moment" path="moment" readonly="true" />
	</jstl:if>
	
	<acme:form-moment code="authenticated.request_.form.label.dateLimit" path="dateLimit"/>
	<acme:form-textarea code="authenticated.request_.form.label.text" path="text"/>
	<acme:form-money code="authenticated.request_.form.label.money" path="money"/>
	<acme:form-textbox placeholder="RABCD-12345" code="authenticated.request_.form.label.ticker" path="ticker"/>
	
	<acme:form-checkbox code="anonymous.user-account.label.accept" path="accept"/>
	
	<acme:form-submit  test="${command == 'create' }"
	code="authenticated.request_.form.button.create" 
	action="/authenticated/request_/create"/>

	<acme:form-return code="authenticated.request_.form.button.return"/>
	</acme:form> 

