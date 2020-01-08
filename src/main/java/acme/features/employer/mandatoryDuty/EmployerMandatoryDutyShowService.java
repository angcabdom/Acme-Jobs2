
package acme.features.employer.mandatoryDuty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerMandatoryDutyShowService implements AbstractShowService<Employer, MandatoryDuty> {

	// Internal State ------------------------------------------------------

	@Autowired
	EmployerMandatoryDutyRepository repository;


	// AbstractListService<Anonymous, Announcemen> interface ------------

	@Override
	public boolean authorise(final Request<MandatoryDuty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<MandatoryDuty> request, final MandatoryDuty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String jobReference = this.repository.findJobReference(request.getModel().getInteger("id"));
		model.setAttribute("jobReference", jobReference);
		request.unbind(entity, model, "title", "dutyDescription", "percentage");

	}

	@Override
	public MandatoryDuty findOne(final Request<MandatoryDuty> request) {
		assert request != null;

		MandatoryDuty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
