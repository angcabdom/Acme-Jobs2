
package acme.features.authenticated.mandatoryDuty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/mandatory-duty/")
public class AuthenticatedMandatoryDutyController extends AbstractController<Authenticated, MandatoryDuty> {

	//	Internal state ------------

	@Autowired
	private AuthenticatedMandatoryDutyListService	list;

	@Autowired
	private AuthenticatedMandatoryDutyShowService	showService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.list);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
