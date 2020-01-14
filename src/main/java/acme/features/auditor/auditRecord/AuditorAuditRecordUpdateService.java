
package acme.features.auditor.auditRecord;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.configuration.Configuration;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService implements AbstractUpdateService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


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

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "finalMode");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {

		int auditId = request.getModel().getInteger("id");
		AuditRecord result = this.repository.findOneById(auditId);
		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int auditId = request.getModel().getInteger("id");

		Boolean oldFinalMode = this.repository.findOneById(auditId).getFinalMode();
		Boolean newFinalMode = request.getModel().getBoolean("finalMode");

		String body = request.getModel().getString("body");

		if (oldFinalMode == true) {
			Boolean atributos = request.getModel().getString("title") == entity.getTitle() && request.getModel().getString("body") == entity.getBody() && request.getModel().getDate("creationMoment") == entity.getCreationMoment();
			errors.state(request, atributos, "finalMode", "auditor.auditRecord.error.finalMode");
		}

		if (newFinalMode == true && oldFinalMode != true) {

			Boolean esSpam = this.esSpam(body);

			errors.state(request, !esSpam, "finalMode", "auditor.auditRecord.error.finalMode.spam");
		}

	}

	private Boolean esSpam(final String body) {
		Configuration c = this.repository.findConfiguration();
		Collection<String> spamWords = c.getSpamWords();
		Double spamThreshold = c.getSpamThreshold();
		Boolean result;

		String[] palabrasDescriptor = body.split(" ");
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
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}

}
