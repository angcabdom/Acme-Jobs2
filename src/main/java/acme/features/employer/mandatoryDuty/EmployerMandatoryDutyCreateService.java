
package acme.features.employer.mandatoryDuty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.jobs.Job;
import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerMandatoryDutyCreateService implements AbstractCreateService<Employer, MandatoryDuty> {

	@Autowired
	private EmployerMandatoryDutyRepository repository;


	@Override
	public boolean authorise(final Request<MandatoryDuty> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("idj");
		Job job = this.repository.findJobById(jobId);

		return job.getEmployer().getId() == request.getPrincipal().getActiveRoleId() && job.isFinalMode() == false;
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

		int jobId = request.getModel().getInteger("idj");
		String jobReference = this.repository.findJobReference(jobId);
		model.setAttribute("idj", jobId);
		model.setAttribute("jobReference", jobReference);

		request.unbind(entity, model, "title", "dutyDescription", "percentage");
	}

	@Override
	public MandatoryDuty instantiate(final Request<MandatoryDuty> request) {
		assert request != null;

		MandatoryDuty result;
		Job job;
		int jobId;
		//UserAccount userAccount;

		jobId = request.getModel().getInteger("idj");
		job = this.repository.findJobById(jobId);

		result = new MandatoryDuty();
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<MandatoryDuty> request, final MandatoryDuty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//validar que las palabras de texto no sean spam
		String texto = request.getModel().getString("dutyDescription");
		Boolean esSpam = this.esSpam(texto);
		errors.state(request, !esSpam, "dutyDescription", "error.text.spam");

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
	public void create(final Request<MandatoryDuty> request, final MandatoryDuty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
