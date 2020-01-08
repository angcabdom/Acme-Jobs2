<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.correa-bulletin.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.correa-bulletin.form.label.text" path="text"/>

	<acme:form-submit code="anonymous.correa-bulletin.form.button.create" action="/anonymous/correa-bulletin/create"/>
	<acme:form-return code="anonymous.correa-bulletin.form.button.return"/>
	</acme:form>