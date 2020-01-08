
package acme.features.anonymous.moranteBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moranteBulletins.MoranteBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousMoranteBulletinCreateService implements AbstractCreateService<Anonymous, MoranteBulletin> {

	//	Internal state ---------------------

	@Autowired
	AnonymousMoranteBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MoranteBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public MoranteBulletin instantiate(final Request<MoranteBulletin> request) {
		assert request != null;

		MoranteBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new MoranteBulletin();
		result.setAuthor("Antonio Naranjo");
		result.setText("Buenas!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<MoranteBulletin> request, final MoranteBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public void bind(final Request<MoranteBulletin> request, final MoranteBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<MoranteBulletin> request, final MoranteBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MoranteBulletin> request, final MoranteBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
