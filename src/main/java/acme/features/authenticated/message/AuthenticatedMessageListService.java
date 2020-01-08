
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


	// AbstractListService<Authenticated, Announcemen> interface ------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String nombreAutor = entity.getAuthor().getUserAccount().getUsername();
		model.setAttribute("nombreAutor", nombreAutor);
		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		//	Principal principal;
		int messageThreadId = request.getModel().getInteger("id");
		//principal = request.getPrincipal();
		//result = this.repository.findManyByEmployerId(principal.getActiveRoleId());
		result = this.repository.findManyByMessageThreadId(messageThreadId);
		return result;
	}

}
