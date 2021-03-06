
package acme.features.administrator.challenge;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengeRepository extends AbstractRepository {

	@Query("select c from Challenge c")
	Collection<Challenge> findManyAll();

	@Query("select c from Challenge c where c.id = ?1")
	Challenge findOneById(int id);

	@Query("select c from Challenge c where c.deadline >= ?1")
	Challenge findOneByDeadline(Date deadline);

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
