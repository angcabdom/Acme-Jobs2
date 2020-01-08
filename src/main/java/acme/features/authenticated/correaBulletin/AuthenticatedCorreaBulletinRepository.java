
package acme.features.authenticated.correaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.correaBulletins.CorreaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCorreaBulletinRepository extends AbstractRepository {

	@Query("select c from CorreaBulletin c")
	Collection<CorreaBulletin> findMany();
}
