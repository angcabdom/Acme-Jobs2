
package acme.features.authenticated.shout;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedShoutRepository extends AbstractRepository {

	@Query("select s from Shout s")
	Collection<Shout> findMany();

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
