<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox placeholder="OABCD-12345" code="authenticated.offer.form.label.ticker" path="ticker" />
	<acme:form-textbox code="authenticated.offer.form.label.title" path="title" />

	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.offer.form.label.moment" path="moment" readonly="true" />
	</jstl:if>

	<acme:form-moment code="authenticated.offer.form.label.deadline" path="deadline" />
	<acme:form-textarea code="authenticated.offer.form.label.text" path="text" />
	<acme:form-money code="authenticated.offer.form.label.moneyMin" path="moneyMin" />
	<acme:form-money code="authenticated.offer.form.label.moneyMax" path="moneyMax" />

	<acme:form-checkbox code="authenticated.offer.label.accept" path="accept" />

	<acme:form-submit test="${command == 'create' }" code="authenticated.offer.form.button.create" action="/authenticated/offer/create" />

	<acme:form-return code="authenticated.offer.form.button.return" />
</acme:form>

