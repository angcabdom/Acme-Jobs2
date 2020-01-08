<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.investor-record.form.label.name" path="name" />
	<acme:form-textbox code="anonymous.investor-record.form.label.sector" path="sector" />
	<acme:form-textbox code="anonymous.investor-record.form.label.statement" path="statement" />
	<acme:form-textbox code="anonymous.investor-record.form.label.stars" path="stars" />

	<acme:form-return code="anonymous.investor-record.form.button.return" />
</acme:form>