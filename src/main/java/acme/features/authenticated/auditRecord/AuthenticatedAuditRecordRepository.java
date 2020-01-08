
package acme.features.authenticated.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRecordRepository extends AbstractRepository {

	@Query("select ar from AuditRecord ar where ar.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select ar from AuditRecord ar where ar.job.id =?1 and published = true")
	Collection<AuditRecord> findManyByJobId(int jobId);

	@Query("select ar.job.reference from AuditRecord ar where ar.id =?1")
	String findJobReference(int arId);
}
