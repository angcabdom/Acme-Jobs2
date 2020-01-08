
package acme.features.authenticated.authenticatedMessageThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.authenticatedMessageThreads.AuthenticatedMessageThread;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, AuthenticatedMessageThread> {

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<AuthenticatedMessageThread> request) {

		return true;
	}

	@Override
	public void unbind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		int authenticatedMessageThreadId = request.getModel().getInteger("id");
		AuthenticatedMessageThread authenticatedMessageThread = this.repository.findOneAuthenticatedMessageThreadById(authenticatedMessageThreadId);

		int messageThreadId = authenticatedMessageThread.getMessageThread().getId();
		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);
		String messageThreadName = messageThread.getTitle();
		model.setAttribute("messageThreadName", messageThreadName);

		Boolean isCreator = messageThread.getCreator().getUserAccount().getUsername().equals(request.getPrincipal().getUsername());
		model.setAttribute("isCreator", isCreator);

		int authenticatedId = authenticatedMessageThread.getAuthenticated().getId();
		String authenticatedName = this.repository.findAuthenticatedById(authenticatedId).getUserAccount().getUsername();
		model.setAttribute("authenticatedName", authenticatedName);

		request.unbind(entity, model, "authenticated", "messageThread");

	}

	@Override
	public AuthenticatedMessageThread findOne(final Request<AuthenticatedMessageThread> request) {
		int authenticatedMessageThreadId = request.getModel().getInteger("id");
		AuthenticatedMessageThread result = this.repository.findOneById(authenticatedMessageThreadId);
		return result;
	}

}
