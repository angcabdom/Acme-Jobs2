<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.morante-bulletin.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.morante-bulletin.form.label.text" path="text"/>

	<acme:form-submit code="anonymous.morante-bulletin.form.button.create" action="/anonymous/morante-bulletin/create"/>
	<acme:form-return code="anonymous.morante-bulletin.form.button.return"/>
	</acme:form> 