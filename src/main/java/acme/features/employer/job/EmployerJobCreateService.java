
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
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return request.getPrincipal().getActiveRole().equals(Employer.class);
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
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Job result;
		Employer employer;
		int employerId;
		//UserAccount userAccount;

		employerId = request.getPrincipal().getActiveRoleId();
		employer = this.repository.findEmployerById(employerId);

		result = new Job();
		result.setEmployer(employer);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean finalMode = request.getModel().getBoolean("finalMode");
		Boolean r = finalMode == false;

		errors.state(request, r, "finalMode", "employer.job.error.finalMode.create");

		//validar que las palabras de texto no sean spam
		String texto = request.getModel().getString("descriptor");
		Boolean esSpam = this.esSpam(texto);
		errors.state(request, !esSpam, "descriptor", "error.text.spam");

	}
	//metodo para comprobar que las palabras no sean spam
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
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
