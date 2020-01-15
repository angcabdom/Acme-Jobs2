/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditorRequestRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		return !request.getPrincipal().hasRole(Auditor.class);
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int principalId = request.getPrincipal().getActiveRoleId();
		AuditorRequest auditorRequest = this.repository.findAuditorRequestByAuthenticatedId(principalId);
		Boolean alreadyExist = auditorRequest != null;

		errors.state(request, !alreadyExist, "authenticatedUsername", "authenticated.auditorRequest.error.alreadyExist");
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String authenticatedName = request.getPrincipal().getUsername();
		model.setAttribute("authenticatedUsername", authenticatedName);

		request.unbind(entity, model, "firm", "responsabilityStatement");
	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		Principal principal;
		int authenticatedId;
		Authenticated authenticated;

		principal = request.getPrincipal();
		authenticatedId = principal.getActiveRoleId();
		authenticated = this.repository.findOneAuthenticatedById(authenticatedId);

		result = new AuditorRequest();
		result.setAuthenticated(authenticated);
		result.setAccepted(false);
		return result;
	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<AuditorRequest> request, final Response<AuditorRequest> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
