
package acme.features.employer.mandatoryDuty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/mandatory-duty/")
public class EmployerMandatoryDutyController extends AbstractController<Employer, MandatoryDuty> {

	//	Internal state ------------

	@Autowired
	private EmployerMandatoryDutyListService	list;

	@Autowired
	private EmployerMandatoryDutyShowService	showService;

	@Autowired
	private EmployerMandatoryDutyCreateService	createService;

	@Autowired
	private EmployerMandatoryDutyUpdateService	updateService;

	@Autowired
	private EmployerMandatoryDutyDeleteService	deleteService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.list);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
