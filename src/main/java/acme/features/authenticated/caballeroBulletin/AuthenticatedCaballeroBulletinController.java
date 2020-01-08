
package acme.features.authenticated.caballeroBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.caballeroBulletins.CaballeroBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/caballero-bulletin/")
public class AuthenticatedCaballeroBulletinController extends AbstractController<Authenticated, CaballeroBulletin> {

	//	Internal state ------------

	@Autowired
	private AuthenticatedCaballeroBulletinListService	listService;

	@Autowired
	private AuthenticatedCaballeroBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
