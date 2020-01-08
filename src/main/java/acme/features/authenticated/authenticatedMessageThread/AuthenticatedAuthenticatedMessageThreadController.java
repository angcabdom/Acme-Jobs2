
package acme.features.authenticated.authenticatedMessageThread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.authenticatedMessageThreads.AuthenticatedMessageThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/authenticated-message-thread/")
public class AuthenticatedAuthenticatedMessageThreadController extends AbstractController<Authenticated, AuthenticatedMessageThread> {

	//	Internal state ------------

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadShowService					showService;

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadCreateService				createService;

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadDeleteService				deleteService;

	@Autowired
	private AuthenticatedAuthenticatedMessageThreadListByMessageThreadService	listByMessageThreadService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.LIST_BY_MESSAGE_THREAD, BasicCommand.LIST, this.listByMessageThreadService);

	}
}
