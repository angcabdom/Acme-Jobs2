
package acme.features.worker.application;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	//	Internal state ---------------------

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application result;
		Job job;
		int jobId;
		int workerId;
		Worker worker;
		//String jobRef;

		jobId = request.getModel().getInteger("idj");
		job = this.repository.findJobById(jobId);

		workerId = request.getPrincipal().getActiveRoleId();
		worker = this.repository.findWorkerById(workerId);
		//jobRef =

		result = new Application();
		result.setJob(job);
		result.setWorker(worker);
		result.setStatus("pending");
		//result.set

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int jobId = request.getModel().getInteger("idj");
		String jobReference = this.repository.findJobReference(jobId);
		model.setAttribute("idj", jobId);
		model.setAttribute("jobReference", jobReference);

		request.unbind(entity, model, "reference", "deadline", "status", "statement");
		request.unbind(entity, model, "skills", "qualifications");
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "worker.application.error.deadline-future");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		//		assert request != null;
		//		assert entity != null;

		//		Date moment;
		//
		//		moment = new Date(System.currentTimeMillis() - 1);
		//		entity.setCreationMoment(moment);
		//		this.repository.save(entity);
		this.repository.save(entity);
	}

}
