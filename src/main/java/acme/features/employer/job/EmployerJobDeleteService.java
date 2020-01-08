
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

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

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int jobId = request.getModel().getInteger("id");
		model.setAttribute("mandatoryDuties", this.repository.findManyDutiesByJobId(jobId));
		model.setAttribute("auditRecords", this.repository.findManyAuditRecordsByJobId(jobId));
		model.setAttribute("applications", this.repository.findManyApplicationsByJobId(jobId));

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

		int mandatoryDuties = this.repository.findManyDutiesByJobId(jobId);
		Boolean noMandatoryDuties = mandatoryDuties == 0;

		int auditRecords = this.repository.findManyAuditRecordsByJobId(jobId);
		Boolean noAuditRecords = auditRecords == 0;

		int applications = this.repository.findManyApplicationsByJobId(jobId);
		Boolean noApplications = applications == 0;

		errors.state(request, noMandatoryDuties, "mandatoryDuties", "employer.job.error.mandatoryDuties");
		errors.state(request, noAuditRecords, "auditRecords", "employer.job.error.auditRecords");
		errors.state(request, noApplications, "applications", "employer.job.error.applications");

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
