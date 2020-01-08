
package acme.features.authenticated.granjaBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.granjaBulletins.GranjaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedGranjaBulletinCreateService implements AbstractCreateService<Authenticated, GranjaBulletin> {

	//	Internal state ---------------------

	@Autowired
	AuthenticatedGranjaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GranjaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public GranjaBulletin instantiate(final Request<GranjaBulletin> request) {
		assert request != null;

		GranjaBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new GranjaBulletin();
		result.setAuthor("Antonio Naranjo");
		result.setText("Buenas!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<GranjaBulletin> request, final GranjaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public void bind(final Request<GranjaBulletin> request, final GranjaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<GranjaBulletin> request, final GranjaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<GranjaBulletin> request, final GranjaBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
