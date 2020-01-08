<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.company-record.form.label.name" path="name" />
	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector" />
	<acme:form-textbox code="administrator.company-record.form.label.ceo" path="ceo" />
	<acme:form-textarea code="administrator.company-record.form.label.activities" path="activities" />
	<acme:form-url code="administrator.company-record.form.label.webSite" path="webSite" />
	<acme:form-textbox placeholder="+34 600000000" code="administrator.company-record.form.label.phone" path="phone" />
	<acme:form-textbox placeholder="example@example.com" code="administrator.company-record.form.label.email" path="email" />
	<acme:form-textbox code="administrator.company-record.form.label.stars" path="stars" />
	<acme:form-checkbox code="administrator.company-record.form.label.incorporated" path="incorporated" />


	<acme:form-submit test="${command == 'show' }" code="administrator.company-record.form.button.update"
		action="/administrator/company-record/update" />

	<acme:form-submit test="${command == 'show' }" code="administrator.company-record.form.button.delete"
		action="/administrator/company-record/delete" />

	<acme:form-submit test="${command == 'create' }" code="administrator.company-record.form.button.create"
		action="/administrator/company-record/create" />

	<acme:form-submit test="${command == 'update' }" code="administrator.company-record.form.button.update"
		action="/administrator/company-record/update" />

	<acme:form-submit test="${command == 'delete' }" code="administrator.company-record.form.button.delete"
		action="/administrator/company-record/delete" />

	<acme:form-return code="administrator.company-record.form.button.return" />
</acme:form>

