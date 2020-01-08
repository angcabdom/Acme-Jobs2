
package acme.features.authenticated.request_;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests_.Request_;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/request_/")
public class AuthenticatedRequest_Controller extends AbstractController<Authenticated, Request_> {
	//	Internal state ------------

	@Autowired
	private AuthenticatedRequest_ListService	listService;

	@Autowired
	private AuthenticatedRequest_CreateService	createService;

	@Autowired
	private AuthenticatedRequest_ShowService	showService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}
}
