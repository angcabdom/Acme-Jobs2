
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	//	Internal state ------------

	@Autowired
	private EmployerApplicationListMineService			listMineService;

	@Autowired
	private EmployerApplicationShowService				showService;

	@Autowired
	private EmployerApplicationUpdateService			updateService;

	@Autowired
	private EmployerApplicationListByReferenceService	listByReferenceService;

	@Autowired
	private EmployerApplicationListByStatusService		listByStatusService;

	@Autowired
	private EmployerApplicationListByMomentService		listByMomentService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_BY_REFERENCE, BasicCommand.LIST, this.listByReferenceService);
		super.addCustomCommand(CustomCommand.LIST_BY_STATUS, BasicCommand.LIST, this.listByStatusService);
		super.addCustomCommand(CustomCommand.LIST_BY_CREATION, BasicCommand.LIST, this.listByMomentService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}
}
