
package acme.features.administrator.investorRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		/*
		 * it can be used to validate that a deadline is in future,
		 * that a range is sequential, that a ticker is unique,
		 * or that an amount of money is positive and its currency is EUR.
		 */
		//		boolean isAccepted;
		//		isAccepted = request.getModel().getBoolean("accept");
		//		errors.state(request, isAccepted, "accept", "administrator.investor-record.error.must-accept");
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
