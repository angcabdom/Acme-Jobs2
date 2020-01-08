
package acme.features.anonymous.caballeroBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.caballeroBulletins.CaballeroBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/caballero-bulletin/")
public class AnonymousCaballeroBulletinController extends AbstractController<Anonymous, CaballeroBulletin> {

	//	Internal state ------------

	@Autowired
	private AnonymousCaballeroBulletinListService	listService;

	@Autowired
	private AnonymousCaballeroBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
