
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	// AbstractListService<Auditor, AuditorRecord> interface ------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		boolean result;
		int auditorRecordId;
		AuditRecord auditorRecord;
		Auditor auditor;
		Principal principal;

		int userId = request.getPrincipal().getActiveRoleId();
		String accepted = this.repository.findAccepted(userId);

		auditorRecordId = request.getModel().getInteger("id");
		auditorRecord = this.repository.findOneById(auditorRecordId);
		auditor = auditorRecord.getAuditor();
		principal = request.getPrincipal();
		result = auditor.getUserAccount().getId() == principal.getAccountId() && accepted.equals("true");
		return result;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int auditRecordId = request.getModel().getInteger("id");
		int jobId = this.repository.findOneById(auditRecordId).getJob().getId();
		String jobReference = this.repository.findJobReference(jobId);
		model.setAttribute("jobReference", jobReference);

		request.unbind(entity, model, "title", "creationMoment");
		request.unbind(entity, model, "body", "published");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
