
package acme.features.anonymous.moranteBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moranteBulletins.MoranteBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousMoranteBulletinListService implements AbstractListService<Anonymous, MoranteBulletin> {

	//Internal state ---------------------------------------------------
	@Autowired
	AnonymousMoranteBulletinRepository repository;


	//AbstractListService<Anonymous,Shout> interface ---------------------------------------------------

	@Override
	public boolean authorise(final Request<MoranteBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<MoranteBulletin> findMany(final Request<MoranteBulletin> request) {
		assert request != null;
		Collection<MoranteBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<MoranteBulletin> request, final MoranteBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}
}
