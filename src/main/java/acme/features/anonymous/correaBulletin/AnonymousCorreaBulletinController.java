
package acme.features.anonymous.correaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.correaBulletins.CorreaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/correa-bulletin/")
public class AnonymousCorreaBulletinController extends AbstractController<Anonymous, CorreaBulletin> {

	//	Internal state ------------

	@Autowired
	private AnonymousCorreaBulletinListService		listService;

	@Autowired
	private AnonymousCorreaBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
