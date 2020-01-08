
package acme.features.authenticated.mandatoryDuty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMandatoryDutyListService implements AbstractListService<Authenticated, MandatoryDuty> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedMandatoryDutyRepository repository;


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
