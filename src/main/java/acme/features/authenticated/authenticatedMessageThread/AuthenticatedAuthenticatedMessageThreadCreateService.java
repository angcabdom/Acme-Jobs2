
package acme.features.authenticated.authenticatedMessageThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.authenticatedMessageThreads.AuthenticatedMessageThread;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, AuthenticatedMessageThread> {

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<AuthenticatedMessageThread> request) {
		assert request != null;

		int messageThreadId = request.getModel().getInteger("idt");
		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);

		int principalId = request.getPrincipal().getActiveRoleId();
		Authenticated principal = this.repository.findAuthenticatedById(principalId);
		return messageThread.getCreator() == principal;
	}

	@Override
	public void bind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int messageThreadId = request.getModel().getInteger("idt");
		model.setAttribute("idt", messageThreadId);
		String messageThreadName = this.repository.findOneMessageThreadById(messageThreadId).getTitle();
		model.setAttribute("messageThreadName", messageThreadName);

		request.unbind(entity, model, "authenticated", "messageThread");
	}

	@Override
	public AuthenticatedMessageThread instantiate(final Request<AuthenticatedMessageThread> request) {
		assert request != null;

		AuthenticatedMessageThread result;
		Authenticated authenticated;
		MessageThread messageThread;

		//UserAccount userAccount;

		authenticated = new Authenticated();

		int messageThreadId = request.getModel().getInteger("idt");
		messageThread = this.repository.findOneMessageThreadById(messageThreadId);

		result = new AuthenticatedMessageThread();
		result.setAuthenticated(authenticated);
		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String authenticatedName = request.getModel().getString("authenticatedName");
		Authenticated authenticated = this.repository.findAuthenticatedByName(authenticatedName);

		Boolean exist = authenticated != null;

		errors.state(request, exist, "authenticatedName", "authenticated.authenticated-message-thread.error.authenticated");

		Integer messageThreadId = request.getModel().getInteger("idt");
		if (exist) {

			AuthenticatedMessageThread authenticatedMessageThread = this.repository.findAuthenticatedMessageThreadByMessageThreadIdAndAuthenticatedId(messageThreadId, authenticated.getId());
			Boolean alreadyIn = authenticatedMessageThread != null;

			errors.state(request, !alreadyIn, "authenticatedName", "authenticated.authenticated-message-thread.error.authenticatedMessageThread");

		}
	}

	@Override
	public void create(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity) {
		assert request != null;
		assert entity != null;

		String authenticatedName = request.getModel().getString("authenticatedName");
		Authenticated authenticated = this.repository.findAuthenticatedByName(authenticatedName);

		entity.setAuthenticated(authenticated);
		this.repository.save(entity);

	}

}
