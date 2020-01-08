
package acme.features.authenticated.request_;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests_.Request_;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequest_ListService implements AbstractListService<Authenticated, Request_> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedRequest_Repository repository;


	// AbstractListService<Authenticated, Request> interface ------------

	@Override
	public boolean authorise(final Request<Request_> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Request_> request, final Request_ entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "dateLimit", "money", "ticker");
	}

	@Override
	public Collection<Request_> findMany(final Request<Request_> request) {
		assert request != null;

		Collection<Request_> result;
		result = this.repository.findManyAll();

		return result;
	}

}
