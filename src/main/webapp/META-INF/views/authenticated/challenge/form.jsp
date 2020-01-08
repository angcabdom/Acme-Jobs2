<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.challenge.form.label.gold" path="gold"/>
	<acme:form-textbox code="authenticated.challenge.form.label.silver" path="silver"/>
	<acme:form-textbox code="authenticated.challenge.form.label.bronze" path="bronze"/>

	<acme:form-return code="authenticated.challenge.form.button.return"/>
	</acme:form> 

