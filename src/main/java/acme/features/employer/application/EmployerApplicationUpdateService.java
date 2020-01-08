
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	private EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		int ApplicationId = request.getModel().getInteger("id");
		Application result = this.repository.findOneById(ApplicationId);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int applicationId = request.getModel().getInteger("id");
		String oldStatus = this.repository.findStatus(applicationId);

		String status = request.getModel().getString("status");
		String justification = request.getModel().getString("justification");

		Boolean r1 = status.equals("rejected") || status.equals("accepted");
		errors.state(request, r1, "status", "employer.application.error.status");

		Boolean r2 = status.equals("rejected") && justification.equals("");
		errors.state(request, !r2, "justification", "employer.application.error.justification");

		if (oldStatus.equals("rejected") || oldStatus.equals("accepted")) {
			Boolean atributos = request.getModel().getString("status") == entity.getStatus() && request.getModel().getString("justification") == entity.getJustification();
			errors.state(request, !atributos, "status", "employer.application.error.status2");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
