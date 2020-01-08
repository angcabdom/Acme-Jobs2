
package acme.features.anonymous.granjaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.granjaBulletins.GranjaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousGranjaBulletinListService implements AbstractListService<Anonymous, GranjaBulletin> {

	//Internal state ---------------------------------------------------
	@Autowired
	AnonymousGranjaBulletinRepository repository;


	//AbstractListService<Anonymous,GranjaBulletin> interface ---------------------------------------------------

	@Override
	public boolean authorise(final Request<GranjaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<GranjaBulletin> findMany(final Request<GranjaBulletin> request) {
		assert request != null;
		Collection<GranjaBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<GranjaBulletin> request, final GranjaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}
}
