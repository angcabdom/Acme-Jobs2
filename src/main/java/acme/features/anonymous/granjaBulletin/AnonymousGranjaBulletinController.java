
package acme.features.anonymous.granjaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.granjaBulletins.GranjaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/granja-bulletin/")
public class AnonymousGranjaBulletinController extends AbstractController<Anonymous, GranjaBulletin> {

	//	Internal state ------------

	@Autowired
	private AnonymousGranjaBulletinListService		listService;

	@Autowired
	private AnonymousGranjaBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
