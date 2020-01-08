
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select ar from AuditRecord ar where ar.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select ar from AuditRecord ar where ar.auditor.id =?1")
	Collection<AuditRecord> findManyByAuditorId(int auditorId);

	@Query("select a.accepted from Auditor a where a.id =?1")
	String findAccepted(int id);

	@Query("select a from Auditor a where a.id =?1")
	Auditor findAuditorById(int auditorId);

	@Query("select j from Job j where j.id =?1")
	Job findJobById(int jobId);

	@Query("select j.reference from Job j where j.id =?1")
	String findJobReference(int jobId);

}
