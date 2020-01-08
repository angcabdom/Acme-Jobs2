
package acme.features.auditor.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/job/")
public class AuditorJobController extends AbstractController<Auditor, Job> {

	//	Internal state ------------

	@Autowired
	private AuditorJobListAuditRecordService	listAuditRecordService;
	@Autowired
	private AuditorJobNoListAuditRecordService	listNoAuditRecordService;

	@Autowired
	private AuditorJobShowService				showService;


	//	Constructors -------------

	@PostConstruct
	private void initalise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_AUDITOR_RECORD, BasicCommand.LIST, this.listAuditRecordService);
		super.addCustomCommand(CustomCommand.LIST_NO_AUDITOR_RECORD, BasicCommand.LIST, this.listNoAuditRecordService);

	}
}
