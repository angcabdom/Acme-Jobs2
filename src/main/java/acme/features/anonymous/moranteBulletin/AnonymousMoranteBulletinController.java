
package acme.features.anonymous.moranteBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.moranteBulletins.MoranteBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/morante-bulletin/")
public class AnonymousMoranteBulletinController extends AbstractController<Anonymous, MoranteBulletin> {

	//	Internal state ------------

	@Autowired
	private AnonymousMoranteBulletinListService		listService;

	@Autowired
	private AnonymousMoranteBulletinCreateService	createService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
