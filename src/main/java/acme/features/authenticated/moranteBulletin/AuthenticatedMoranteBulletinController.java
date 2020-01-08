
package acme.features.authenticated.moranteBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.moranteBulletins.MoranteBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/morante-bulletin/")
public class AuthenticatedMoranteBulletinController extends AbstractController<Authenticated, MoranteBulletin> {

	//	Internal state ------------

	@Autowired
	private AuthenticatedMoranteBulletinListService		listService;

	@Autowired
	private AuthenticatedMoranteBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
