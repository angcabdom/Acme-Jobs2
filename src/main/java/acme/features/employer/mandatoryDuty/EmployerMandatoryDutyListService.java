
package acme.features.employer.mandatoryDuty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EmployerMandatoryDutyListService implements AbstractListService<Employer, MandatoryDuty> {

	// Internal State ------------------------------------------------------

	@Autowired
	EmployerMandatoryDutyRepository repository;


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

		int jobId = request.getModel().getInteger("idj");
		model.setAttribute("idj", jobId);
		request.unbind(entity, model, "title", "dutyDescription", "percentage");

	}

	@Override
	public Collection<MandatoryDuty> findMany(final Request<MandatoryDuty> request) {
		assert request != null;

		Collection<MandatoryDuty> result;
		result = this.repository.findManybyJobId(request.getModel().getInteger("idj"));

		return result;
	}

}
