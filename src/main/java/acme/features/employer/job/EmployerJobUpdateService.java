
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {

		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneById(jobId);
		return job.getEmployer().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "finalMode", "descriptor");

	}

	@Override
	public Job findOne(final Request<Job> request) {

		int jobId = request.getModel().getInteger("id");
		Job result = this.repository.findOneById(jobId);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int jobId = request.getModel().getInteger("id");

		Boolean oldFinalMode = this.repository.findfinalModeByJobId(jobId);
		Boolean newFinalMode = request.getModel().getBoolean("finalMode");

		String descriptor = request.getModel().getString("descriptor");

		if (oldFinalMode == true) {
			Boolean atributos = request.getModel().getString("reference") == entity.getReference() && request.getModel().getString("title") == entity.getTitle() && request.getModel().getDate("deadline") == entity.getDeadline()
				&& request.getModel().getAttribute("salary") == entity.getSalary() && request.getModel().getString("moreInfo") == entity.getMoreInfo() && request.getModel().getString("descriptor") == entity.getDescriptor();
			errors.state(request, atributos, "finalMode", "employer.job.error.finalMode");
		}

		if (newFinalMode == true && oldFinalMode != true) {
			Integer workload = this.repository.findWorkloadByJobId(jobId);
			errors.state(request, workload == 100, "finalMode", "employer.job.error.finalMode.workload");

			Boolean esSpam = this.esSpam(descriptor);

			errors.state(request, !esSpam, "finalMode", "employer.job.error.finalMode.spam");
		}

	}

	private Boolean esSpam(final String descriptor) {
		Configuration c = this.repository.findConfiguration();
		Collection<String> spamWords = c.getSpamWords();
		Double spamThreshold = c.getSpamThreshold();
		Boolean result;

		String[] palabrasDescriptor = descriptor.split(" ");
		Double contadorSpam = 0.0;
		for (String s : palabrasDescriptor) {
			if (spamWords.contains(s.trim().toLowerCase())) {
				contadorSpam = contadorSpam + 1;
			}
		}
		Double totalPalabras = (double) palabrasDescriptor.length;
		Double percentageSpam = contadorSpam / totalPalabras;

		result = percentageSpam >= spamThreshold;
		return result;
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
