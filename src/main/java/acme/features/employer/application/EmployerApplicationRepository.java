
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.job.employer.id =?1 ")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select a.job.reference from Application a where a.id =?1")
	String findJobReference(int applicationId);

	@Query("select a.status from Application a where a.id =?1")
	String findStatus(int applicationId);

	@Query("select a from Application a where a.job.id =?1 order by a.reference ASC")
	Collection<Application> findManyByJobIdOrderByReference(int jobId);

	@Query("select a from Application a where a.job.id =?1 order by a.status ASC")
	Collection<Application> findManyByJobIdOrderByStatus(int jobId);

	@Query("select a from Application a where a.job.id =?1 order by a.creationMoment DESC")
	Collection<Application> findManyByJobIdOrderByMoment(int jobId);

}
