
package acme.features.authenticated.authenticatedMessageThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.authenticatedMessageThreads.AuthenticatedMessageThread;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedAuthenticatedMessageThreadDeleteService implements AbstractDeleteService<Authenticated, AuthenticatedMessageThread> {

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<AuthenticatedMessageThread> request) {
		int authenticatedMessageThreadId = request.getModel().getInteger("id");
		AuthenticatedMessageThread authenticatedMessageThread = this.repository.findOneById(authenticatedMessageThreadId);

		MessageThread messageThread = authenticatedMessageThread.getMessageThread();
		int principalId = request.getPrincipal().getActiveRoleId();
		Authenticated principal = this.repository.findAuthenticatedById(principalId);
		return messageThread.getCreator() == principal;
	}

	@Override
	public void bind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void unbind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated", "messageThread");

	}

	@Override
	public AuthenticatedMessageThread findOne(final Request<AuthenticatedMessageThread> request) {

		AuthenticatedMessageThread result = this.repository.findOneById(request.getModel().getInteger("id"));
		return result;
	}

	@Override
	public void validate(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int authenticatedMessageThreadId = request.getModel().getInteger("id");
		AuthenticatedMessageThread authenticatedMessageThread = this.repository.findOneById(authenticatedMessageThreadId);

		Authenticated authenticatedToDelete = authenticatedMessageThread.getAuthenticated();
		Authenticated creator = authenticatedMessageThread.getMessageThread().getCreator();

		Boolean deleteCreator = creator == authenticatedToDelete;
		errors.state(request, !deleteCreator, "authenticatedName", "authenticated.authenticated-message-thread.error.authenticated.delete");

	}

	@Override
	public void delete(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
