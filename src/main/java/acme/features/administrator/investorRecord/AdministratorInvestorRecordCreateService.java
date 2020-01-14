
package acme.features.administrator.investorRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.investorRecords.InvestorRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInvestorRecordCreateService implements AbstractCreateService<Administrator, InvestorRecord> {

	//	Internal state ---------------------

	@Autowired
	AdministratorInvestorRecordRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public InvestorRecord instantiate(final Request<InvestorRecord> request) {

		InvestorRecord result;

		result = new InvestorRecord();

		return result;
	}

	@Override
	public void unbind(final Request<InvestorRecord> request, final InvestorRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "statement", "stars");

		//		if (request.isMethod(HttpMethod.GET)) {
		//			model.setAttribute("accept", "false");
		//		} else {
		//			request.transfer(model, "accept");
		//		}
	}

	@Override
	public void bind(final Request<InvestorRecord> request, final InvestorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors); //ejemplo: request.bind(entity, errors, "moment");
	}

	@Override
	public void validate(final Request<InvestorRecord> request, final InvestorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//validar que las palabras de texto no sean spam
		String texto = request.getModel().getString("statement");
		Boolean esSpam = this.esSpam(texto);
		errors.state(request, !esSpam, "statement", "administrator.investorRecord.error.text.spam");

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
	public void create(final Request<InvestorRecord> request, final InvestorRecord entity) {

		//		assert request != null;
		//		assert entity != null;
		/*
		 * ejemplo
		 * Date moment;
		 *
		 * moment = new Date(System.currentTimeMillis() - 1);
		 * entity.setMoment(moment);
		 * this.repository.save(entity);
		 */
		//		String name = entity.getName();
		//		entity.setName(name);
		//		String sector = entity.getSector();
		//		entity.setSector(sector);
		//		String statement = entity.getStatement();
		//		entity.setStatement(statement);
		//		Integer stars = entity.getStars();
		//		entity.setStars(stars);

		this.repository.save(entity);
	}

}
