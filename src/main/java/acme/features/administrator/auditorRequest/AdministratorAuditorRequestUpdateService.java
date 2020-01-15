
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRequestUpdateService implements AbstractUpdateService<Administrator, AuditorRequest> {

	@Autowired
	AdministratorAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		return true;
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

		Authenticated authenticated = (Authenticated) request.getModel().getAttribute("authenticated");
		String authenticatedName = authenticated.getUserAccount().getUsername();
		model.setAttribute("authenticatedUsername", authenticatedName);

		request.unbind(entity, model, "firm", "responsabilityStatement", "accepted");

	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;

		int auditorId = request.getModel().getInteger("id");
		AuditorRequest result = this.repository.findOneAuditorRequestById(auditorId);
		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		AuditorRequest oldAuditorRequest = this.repository.findAuditorRequestByAuthenticatedId(entity.getAuthenticated().getId());

		Boolean accepted = oldAuditorRequest.getAccepted() == false || entity.getAccepted() == true;
		errors.state(request, accepted, "accepted", "administrator.auditorRequest.error.accepted");

	}

	@Override
	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getBoolean("accepted") == true) {
			entity.setAccepted(true);

			Auditor newAuditor = new Auditor();
			newAuditor.setFirm(entity.getFirm());
			newAuditor.setResponsabilityStatement(entity.getResponsabilityStatement());
			newAuditor.setUserAccount(entity.getAuthenticated().getUserAccount());
			this.repository.save(newAuditor);
		}

		this.repository.save(entity);
	}
}
