
package acme.features.authenticated.correaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.correaBulletins.CorreaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/correa-bulletin/")
public class AuthenticatedCorreaBulletinController extends AbstractController<Authenticated, CorreaBulletin> {

	//	Internal state ------------

	@Autowired
	private AuthenticatedCorreaBulletinListService		listService;

	@Autowired
	private AuthenticatedCorreaBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
