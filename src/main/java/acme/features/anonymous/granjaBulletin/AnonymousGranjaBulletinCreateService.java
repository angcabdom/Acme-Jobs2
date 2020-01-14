
package acme.features.anonymous.granjaBulletin;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.granjaBulletins.GranjaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousGranjaBulletinCreateService implements AbstractCreateService<Anonymous, GranjaBulletin> {

	//	Internal state ---------------------

	@Autowired
	AnonymousGranjaBulletinRepository repository;


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

		//validar que las palabras de texto no sean spam
		String texto = request.getModel().getString("text");
		Boolean esSpam = this.esSpam(texto);
		errors.state(request, !esSpam, "text", "error.text.spam");

	}
	//metodo para comprobar que las palabras no sean spam
	private Boolean esSpam(final String descriptor) {
		Configuration c = this.repository.findConfiguration();
		Collection<String> spamWords = c.getSpamWords();
		Double spamThreshold = c.getSpamThreshold();
		Boolean result;

		String[] palabrasDescriptor = descriptor.split(" ");
		Double contadorSpam = 0.0;
		for (String s : palabrasDescriptor) {
			if (spamWords.contains(s.trim().toLowerCase())) {
				contadorSpam = contadorSpam + 1;
			}
		}
		Double totalPalabras = (double) palabrasDescriptor.length;
		Double percentageSpam = contadorSpam / totalPalabras;

		result = percentageSpam >= spamThreshold;
		return result;
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
