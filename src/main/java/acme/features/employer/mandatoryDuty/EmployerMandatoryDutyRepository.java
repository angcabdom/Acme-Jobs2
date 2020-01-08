
package acme.features.employer.mandatoryDuty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.mandatoryDuties.MandatoryDuty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerMandatoryDutyRepository extends AbstractRepository {

	@Query("select a from InvestorRecord a")
	Collection<MandatoryDuty> findManyAll();

	@Query("select m from MandatoryDuty m where m.job.id = ?1")
	Collection<MandatoryDuty> findManybyJobId(int id);

	@Query("select m from MandatoryDuty m where m.id = ?1")
	MandatoryDuty findOneById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int id);

	@Query("select j.reference from Job j where j.id =?1")
	String findJobReference(int jobId);

}
