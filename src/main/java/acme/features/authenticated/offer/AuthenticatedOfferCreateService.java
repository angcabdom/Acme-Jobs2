package acme.features.authenticated.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedOfferCreateService implements AbstractCreateService<Authenticated, Offer> {

	//	Internal state ---------------------

	@Autowired
	AuthenticatedOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {

		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "ticker", "moneyMin", "moneyMax", "text");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment"); //ejemplo: request.bind(entity, errors, "moment");
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		/*
		 * it can be used to validate that a deadline is in future,
		 * that a range is sequential, that a ticker is unique,
		 * or that an amount of money is positive and its currency is EUR.
		 */
		boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.offer.error.must-accept");


		if (entity.getMoneyMax() != null) {
			String s = entity.getMoneyMax().getCurrency();
			boolean isAccepted2 = s.equals("EUR") || s.equals("€");
			errors.state(request, isAccepted2, "moneyMax", "authenticated.request_.error.moneyMax");
		}
		if (entity.getMoneyMin() != null) {
			String s = entity.getMoneyMin().getCurrency();
			boolean isAccepted3 = s.equals("EUR") || s.equals("€");
			errors.state(request, isAccepted3, "moneyMin", "authenticated.request_.error.moneyMin");
		}

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "authenticated.offer.error.deadline-future");
		}


	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {

		//		assert request != null;
		//		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		//"title", "deadline", "ticker", "moneyMin", "moneyMax", "text"

		//		String title = entity.getTitle();
		//		entity.setTitle(title);
		//
		//		Date deadline = entity.getDeadline();
		//		entity.setDeadline(deadline);
		//
		//		String ticker = entity.getTicker();
		//		entity.setTicker(ticker);
		//
		//		Money moneyMin = entity.getMoneyMin();
		//		entity.setMoneyMin(moneyMin);
		//
		//		Money moneyMax = entity.getMoneyMax();
		//		entity.setMoneyMin(moneyMax);
		//
		//		String text = entity.getText();
		//		entity.setText(text);

		this.repository.save(entity);

	}

}

