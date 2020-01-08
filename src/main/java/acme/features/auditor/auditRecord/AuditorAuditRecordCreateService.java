
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	// AbstractCreateService<Auditor, AuditRecord> interface ------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		int userId = request.getPrincipal().getActiveRoleId();
		String accepted = this.repository.findAccepted(userId);

		return request.getPrincipal().getActiveRole().equals(Auditor.class) && accepted.equals("true");
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int jobId = request.getModel().getInteger("idj");
		String jobReference = this.repository.findJobReference(jobId);
		model.setAttribute("idj", jobId);
		model.setAttribute("jobReference", jobReference);

		request.unbind(entity, model, "title", "body", "published");
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		AuditRecord result;
		Date moment;

		Auditor auditor;
		int auditorId;
		auditorId = request.getPrincipal().getActiveRoleId();
		auditor = this.repository.findAuditorById(auditorId);

		Job job;
		int jobId;
		jobId = request.getModel().getInteger("idj");
		job = this.repository.findJobById(jobId);

		result = new AuditRecord();
		result.setAuditor(auditor);
		result.setJob(job);
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
