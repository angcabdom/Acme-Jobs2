
package acme.features.authenticated.request_;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests_.Request_;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequest_ShowService implements AbstractShowService<Authenticated, Request_> {
	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedRequest_Repository repository;


	// AbstractListService<Authenticated, Request_> interface ------------

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

		request.unbind(entity, model, "title", "moment", "dateLimit", "text", "money", "ticker");

	}

	@Override
	public Request_ findOne(final Request<Request_> request) {
		assert request != null;

		Request_ result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
