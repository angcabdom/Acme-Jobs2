<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.caballero-bulletin.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.caballero-bulletin.form.label.text" path="text"/>

	<acme:form-submit code="anonymous.caballero-bulletin.form.button.create" action="/anonymous/caballero-bulletin/create"/>
	<acme:form-return code="anonymous.caballero-bulletin.form.button.return"/>
	</acme:form> 