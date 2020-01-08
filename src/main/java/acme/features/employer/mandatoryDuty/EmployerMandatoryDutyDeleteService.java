
package acme.features.employer.mandatoryDuty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerMandatoryDutyDeleteService implements AbstractDeleteService<Employer, MandatoryDuty> {

	@Autowired
	private EmployerMandatoryDutyRepository repository;


	@Override
	public boolean authorise(final Request<MandatoryDuty> request) {
		assert request != null;

		int mandatoryDutyId = request.getModel().getInteger("id");
		MandatoryDuty mandatoryDuty = this.repository.findOneById(mandatoryDutyId);
		Job job = mandatoryDuty.getJob();

		return job.getEmployer().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void bind(final Request<MandatoryDuty> request, final MandatoryDuty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<MandatoryDuty> request, final MandatoryDuty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "dutyDescription", "percentage");

	}

	@Override
	public void validate(final Request<MandatoryDuty> request, final MandatoryDuty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public MandatoryDuty findOne(final Request<MandatoryDuty> request) {
		int mandatoryDutyId = request.getModel().getInteger("id");
		MandatoryDuty result = this.repository.findOneById(mandatoryDutyId);
		return result;
	}

	@Override
	public void delete(final Request<MandatoryDuty> request, final MandatoryDuty entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
