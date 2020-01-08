
package acme.features.anonymous.caballeroBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.caballeroBulletins.CaballeroBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCaballeroBulletinRepository extends AbstractRepository {

	@Query("select c from CaballeroBulletin c")
	Collection<CaballeroBulletin> findMany();
}
