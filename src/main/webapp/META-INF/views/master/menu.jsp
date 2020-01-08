<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>



		<acme:menu-option code="master.menu.bulletins" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.javi-link" action="https://www.youtube.com/?hl=es&gl=ES" />
			<acme:menu-suboption code="master.menu.anonymous.angel-link" action="https://myanimelist.net/" />
			<acme:menu-suboption code="master.menu.anonymous.flor-link" action="https://www.xataka.com/" />
			<acme:menu-suboption code="master.menu.anonymous.abel-link" action="https://www.digitalcombatsimulator.com/en/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.shout.list" action="/anonymous/shout/list" />
			<acme:menu-suboption code="master.menu.shout.create" action="/anonymous/shout/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.caballero-bulletins" action="/anonymous/caballero-bulletin/list" />
			<acme:menu-suboption code="master.menu.caballero-bulletin.create" action="/anonymous/caballero-bulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.correa-bulletins" action="/anonymous/correa-bulletin/list" />
			<acme:menu-suboption code="master.menu.correa-bulletin.create" action="/anonymous/correa-bulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.granja-bulletins" action="/anonymous/granja-bulletin/list" />
			<acme:menu-suboption code="master.menu.granja-bulletin.create" action="/anonymous/granja-bulletin/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.morante-bulletins" action="/anonymous/morante-bulletin/list" />
			<acme:menu-suboption code="master.menu.morante-bulletin.create" action="/anonymous/morante-bulletin/create" />
		</acme:menu-option>


		<!---------------------------- ANONYMOUS --------------------------------------->
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.company-record.list" action="/anonymous/company-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.investor-record.list" action="/anonymous/investor-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.announcement.list" action="/anonymous/announcement/list" />
		</acme:menu-option>



		<!---------------------------- AUTHENTICATED  ---------------------------------->
		<acme:menu-option code="master.menu.anonymous" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.announcement.list" action="/authenticated/announcement/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.company-record.list" action="/authenticated/company-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.investor-record.list" action="/authenticated/investor-record/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.offer.list" action="/authenticated/offer/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.request_.list" action="/authenticated/request_/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.challenge.list" action="/authenticated/challenge/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.job.listAll" action="/authenticated/job/list" />
		    <acme:menu-separator/>
			<acme:menu-suboption code="master.menu.messageThread.listMine" action="/authenticated/message-thread/list_mine" />
			<acme:menu-suboption code="master.menu.messageThread.create" action="/authenticated/message-thread/create" />


		</acme:menu-option>

		<!---------------------------- EMPLOYER  --------------------------------------->
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.job.listMine" action="/employer/job/list_mine" />
			<acme:menu-suboption code="master.menu.job.create" action="/employer/job/create" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.employer.application" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.application.list" action="/employer/application/list_mine" />
		</acme:menu-option>


		<!---------------------------- WORKER  --------------------------------------->
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.application.list2" action="/worker/application/list_mine" />
		</acme:menu-option>

		<!---------------------------- AUDITOR  --------------------------------------->
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.job.list.auditorRecord" action="/auditor/job/list-auditor-record" />
			<acme:menu-suboption code="master.menu.job.list.NoauditorRecord" action="/auditor/job/list-no-auditor-record" />
			<acme:menu-suboption code="master.menu.auditRecord.list" action="/auditor/audit-record/list_mine" />
		</acme:menu-option>

		<!----------------------------- CONSUMER --------------------------------------->
		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.offer.create" action="/authenticated/offer/create" />
		</acme:menu-option>


		<!----------------------------- PROVIDER --------------------------------------->
		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.request_.create" action="/authenticated/request_/create" />
		</acme:menu-option>



		<!---------------------------- ADMINISTRATOR ----------------------------------->


		<acme:menu-option code="master.menu.administrator.auditor" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.auditor.list" action="/administrator/auditor/list" />
		</acme:menu-option>


		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.announcement.list" action="/administrator/announcement/list" />
			<acme:menu-suboption code="master.menu.announcement.create" action="/administrator/announcement/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.company-record.list" action="/administrator/company-record/list" />
			<acme:menu-suboption code="master.menu.company-record.create" action="/administrator/company-record/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.investor-record.list" action="/administrator/investor-record/list" />
			<acme:menu-suboption code="master.menu.investor-record.create" action="/administrator/investor-record/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.challenge.list" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.challenge.form" action="/administrator/challenge/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.configuration.list" action="/administrator/configuration/list" />

		</acme:menu-option>



	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />

			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create"
				access="!hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update"
				access="hasRole('Employer')" />

			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create"
				access="!hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')" />

			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor/create"
				access="!hasRole('Auditor')" />
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')" />

			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>
