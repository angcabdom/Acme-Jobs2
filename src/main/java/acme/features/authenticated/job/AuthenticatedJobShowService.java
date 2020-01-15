
package acme.features.authenticated.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedJobShowService implements AbstractShowService<Authenticated, Job> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedJobRepository repository;


	// AbstractListService<Authenticated, Announcemen> interface ------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean result;

		int jobId;
		Job job;
		//Employer employer;
		//Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneById(jobId);
		//employer = job.getEmployer();
		//principal = request.getPrincipal();

		result = job.isFinalMode();
		//|| !job.isFinalMode() && employer.getUserAccount().getId() == principal.getActiveRoleId();
		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int accountId = request.getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.findOneUserAccountById(accountId);
		Boolean isOwner = userAccount.getRole(Employer.class).getId() == entity.getEmployer().getId();
		model.setAttribute("isOwner", isOwner);
		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "finalMode", "descriptor");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

}
