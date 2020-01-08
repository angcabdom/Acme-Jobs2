
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j where j in (select ar.job from AuditRecord ar where ar.auditor.id = ?1)")
	Collection<Job> findManyWithAuditorRecord(int auditorId);

	@Query("select j from Job j where j not in (select ar.job from AuditRecord ar where ar.auditor.id = ?1)")
	Collection<Job> findManyWithoutAuditorRecord(int auditorId);

	@Query("select a.accepted from Auditor a where a.id =?1")
	String findAccepted(int id);
}
