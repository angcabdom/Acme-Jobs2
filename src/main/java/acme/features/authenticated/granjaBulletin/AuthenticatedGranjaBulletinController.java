
package acme.features.authenticated.granjaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.granjaBulletins.GranjaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/granja-bulletin/")
public class AuthenticatedGranjaBulletinController extends AbstractController<Authenticated, GranjaBulletin> {

	//	Internal state ------------

	@Autowired
	private AuthenticatedGranjaBulletinListService		listService;

	@Autowired
	private AuthenticatedGranjaBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
