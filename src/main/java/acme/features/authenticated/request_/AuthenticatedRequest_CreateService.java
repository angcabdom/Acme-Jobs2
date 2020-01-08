
package acme.features.authenticated.request_;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests_.Request_;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedRequest_CreateService implements AbstractCreateService<Authenticated, Request_> {

	// Internal State ------------------------------------------------------

	@Autowired
	AuthenticatedRequest_Repository repository;


	// AbstractCreateService<Authenticated, Request> interface ------------

	@Override
	public boolean authorise(final Request<Request_> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Request_> request, final Request_ entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		//Atributos que se le pasan (no modificable)
		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Request_> request, final Request_ entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "dateLimit", "text", "money", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Request_ instantiate(final Request<Request_> request) {
		Request_ result;
		result = new Request_();
		return result;
	}

	@Override
	public void validate(final Request<Request_> request, final Request_ entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.request_.error.must-accept");


		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("dateLimit")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDateLimit().after(minimumDeadline), "dateLimit", "authenticated.request_.error.dateLimit-future");
    }
		if (entity.getMoney() != null) {
			String s = entity.getMoney().getCurrency();
			boolean isAccepted2 = s.equals("EUR") || s.equals("€");
			errors.state(request, isAccepted2, "money", "authenticated.request_.error.money");

		}

	}

	@Override
	public void create(final Request<Request_> request, final Request_ entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		//"title", "DateLimit", "ticker", "money", "text"

		//		String title = entity.getTitle();
		//		entity.setTitle(title);
		//
		//		Date dateLimit = entity.getDateLimit();
		//		entity.setDateLimit(dateLimit);
		//
		//		String ticker = entity.getTicker();
		//		entity.setTicker(ticker);
		//
		//		Money money = entity.getMoney();
		//		entity.setMoney(money);
		//
		//		String text = entity.getText();
		//		entity.setText(text);

		this.repository.save(entity);
	}

}
