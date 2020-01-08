
package acme.features.authenticated.authenticatedMessageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.authenticatedMessageThreads.AuthenticatedMessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuthenticatedMessageThreadListByMessageThreadService implements AbstractListService<Authenticated, AuthenticatedMessageThread> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedAuthenticatedMessageThreadRepository repository;


	// AbstractListService<Authenticated, Announcemen> interface ------------

	@Override
	public boolean authorise(final Request<AuthenticatedMessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<AuthenticatedMessageThread> request, final AuthenticatedMessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String username = entity.getAuthenticated().getUserAccount().getUsername();
		model.setAttribute("username", username);
		request.unbind(entity, model);
	}

	@Override
	public Collection<AuthenticatedMessageThread> findMany(final Request<AuthenticatedMessageThread> request) {
		assert request != null;

		Collection<AuthenticatedMessageThread> result = this.repository.findManyAuthenticatedMessageThreadByThreadId(request.getModel().getInteger("idt"));
		return result;

	}
}
