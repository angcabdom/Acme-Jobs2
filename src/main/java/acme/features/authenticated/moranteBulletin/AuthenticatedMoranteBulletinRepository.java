
package acme.features.authenticated.moranteBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.moranteBulletins.MoranteBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMoranteBulletinRepository extends AbstractRepository {

	@Query("select m from MoranteBulletin m")
	Collection<MoranteBulletin> findMany();
}
