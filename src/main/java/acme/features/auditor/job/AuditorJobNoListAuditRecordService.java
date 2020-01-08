
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorJobNoListAuditRecordService implements AbstractListService<Auditor, Job> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuditorJobRepository repository;


	// AbstractListService<Auditor, Job> interface ------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		int userId = request.getPrincipal().getActiveRoleId();
		String accepted = this.repository.findAccepted(userId);
		return accepted.equals("true");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		assert request != null;

		Collection<Job> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyWithoutAuditorRecord(principal.getActiveRoleId());

		return result;
	}

}
