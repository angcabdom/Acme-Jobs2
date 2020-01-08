
package acme.features.administrator.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuditorListService implements AbstractListService<Administrator, Auditor> {

	// Internal State ------------------------------------------------------

	@Autowired
	AdministratorAuditorRepository repository;


	// AbstractListService<Authenticated, Announcemen> interface ------------

	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "accepted");
	}

	@Override
	public Collection<Auditor> findMany(final Request<Auditor> request) {
		assert request != null;

		Collection<Auditor> result;
		result = this.repository.findManyAuditor();

		return result;
	}
}
