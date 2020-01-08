
package acme.features.authenticated.correaBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.correaBulletins.CorreaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedCorreaBulletinCreateService implements AbstractCreateService<Authenticated, CorreaBulletin> {

	//	Internal state ---------------------

	@Autowired
	AuthenticatedCorreaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CorreaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public CorreaBulletin instantiate(final Request<CorreaBulletin> request) {
		assert request != null;

		CorreaBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new CorreaBulletin();
		result.setAuthor("Flor");
		result.setText("¡Hola!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<CorreaBulletin> request, final CorreaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public void bind(final Request<CorreaBulletin> request, final CorreaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CorreaBulletin> request, final CorreaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<CorreaBulletin> request, final CorreaBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
