
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAuditorRequestShowService implements AbstractShowService<Administrator, AuditorRequest> {

	// Internal State ------------------------------------------------------

	@Autowired
	AdministratorAuditorRequestRepository repository;


	// AbstractListService<Administrator, Announcemen> interface ------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int authenticatedId = request.getModel().getInteger("id");
		Authenticated authenticated = this.repository.findOneAuditorRequestById(authenticatedId).getAuthenticated();
		String authenticatedName = authenticated.getUserAccount().getUsername();
		model.setAttribute("authenticatedUsername", authenticatedName);

		request.unbind(entity, model, "firm", "responsabilityStatement", "accepted");

	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorRequestById(id);

		return result;
	}

}
