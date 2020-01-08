<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.company-record.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.company-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.company-record.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="anonymous.company-record.form.label.activities" path="activities"/>
	<acme:form-url code="anonymous.company-record.form.label.webSite" path="webSite"/>
	<acme:form-textbox placeholder="+34 600000000" code="anonymous.company-record.form.label.phone" path="phone"/>
	<acme:form-textbox placeholder="example@example.com" code="anonymous.company-record.form.label.email" path="email"/>
	<acme:form-textbox code="anonymous.company-record.form.label.incorporated" path="incorporated"/>
	<acme:form-textbox code="anonymous.company-record.form.label.stars" path="stars"/>

	<acme:form-return code="anonymous.company-record.form.button.return"/>
	</acme:form> 

