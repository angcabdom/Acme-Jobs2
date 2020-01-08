
package acme.features.authenticated.messageThread;

import java.util.Date;

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
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	private AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String creatorName = request.getPrincipal().getUsername();
		model.setAttribute("creatorName", creatorName);

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		Authenticated creator;
		int creatorId;
		//UserAccount userAccount;

		creatorId = request.getPrincipal().getActiveRoleId();
		creator = this.repository.findCreatorById(creatorId);

		result = new MessageThread();
		result.setCreator(creator);

		Date creationMoment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(creationMoment);

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

		AuthenticatedMessageThread authenticatedmessageThread = new AuthenticatedMessageThread();
		authenticatedmessageThread.setAuthenticated(entity.getCreator());
		authenticatedmessageThread.setMessageThread(entity);

		this.repository.save(authenticatedmessageThread);

	}

}
