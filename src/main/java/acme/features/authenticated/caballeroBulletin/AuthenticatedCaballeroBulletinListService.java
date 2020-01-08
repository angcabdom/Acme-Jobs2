
package acme.features.authenticated.caballeroBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.caballeroBulletins.CaballeroBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedCaballeroBulletinListService implements AbstractListService<Authenticated, CaballeroBulletin> {

	//Internal state ---------------------------------------------------
	@Autowired
	AuthenticatedCaballeroBulletinRepository repository;


	//AbstractListService<Anonymous,CaballeroBulletin> interface ---------------------------------------------------

	@Override
	public boolean authorise(final Request<CaballeroBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<CaballeroBulletin> findMany(final Request<CaballeroBulletin> request) {
		assert request != null;
		Collection<CaballeroBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<CaballeroBulletin> request, final CaballeroBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}

}
