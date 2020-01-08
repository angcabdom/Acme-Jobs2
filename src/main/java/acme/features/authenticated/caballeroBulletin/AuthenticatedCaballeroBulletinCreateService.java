
package acme.features.authenticated.caballeroBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.caballeroBulletins.CaballeroBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedCaballeroBulletinCreateService implements AbstractCreateService<Authenticated, CaballeroBulletin> {

	//	Internal state ---------------------

	@Autowired
	AuthenticatedCaballeroBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CaballeroBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public CaballeroBulletin instantiate(final Request<CaballeroBulletin> request) {
		assert request != null;

		CaballeroBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new CaballeroBulletin();
		result.setAuthor("Juan Marquez");
		result.setText("Buenas!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<CaballeroBulletin> request, final CaballeroBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public void bind(final Request<CaballeroBulletin> request, final CaballeroBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CaballeroBulletin> request, final CaballeroBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<CaballeroBulletin> request, final CaballeroBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
