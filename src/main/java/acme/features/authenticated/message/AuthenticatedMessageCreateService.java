
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		int messageThreadId = request.getModel().getInteger("idt");
		Collection<String> users = this.repository.findManyAuthenticatedByMessageThreadId(messageThreadId);

		return users.contains(request.getPrincipal().getUsername());
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String creatorName = request.getPrincipal().getUsername();
		model.setAttribute("creatorName", creatorName);

		int messageThreadId = request.getModel().getInteger("idt");
		model.setAttribute("idt", messageThreadId);

		request.unbind(entity, model, "title", "creationMoment");
		request.unbind(entity, model, "body", "tags");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result;
		Authenticated creator;
		MessageThread messageThread;
		int creatorId;
		int messageThreadId;

		creatorId = request.getPrincipal().getActiveRoleId();
		creator = this.repository.findCreatorById(creatorId);

		messageThreadId = request.getModel().getInteger("idt");
		messageThread = this.repository.findOneMessageThreadById(messageThreadId);

		result = new Message();
		result.setAuthor(creator);
		result.setMessageThread(messageThread);

		Date creationMoment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(creationMoment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");

		Boolean esSpam = this.esSpam(entity.getBody());

		errors.state(request, !esSpam, "body", "authenticated.message.error.body.spam");
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
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
