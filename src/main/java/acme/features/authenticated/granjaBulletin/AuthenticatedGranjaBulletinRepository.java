
package acme.features.authenticated.granjaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.granjaBulletins.GranjaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedGranjaBulletinRepository extends AbstractRepository {

	@Query("select g from GranjaBulletin g")
	Collection<GranjaBulletin> findMany();
}
