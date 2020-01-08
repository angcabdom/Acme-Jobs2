
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j where j.employer.id =?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findEmployerById(int employerId);

	@Query("select count(md) from MandatoryDuty md where md.job.id=?1")
	int findManyDutiesByJobId(int jobId);

	@Query("select count(ar) from AuditRecord ar where ar.job.id=?1")
	int findManyAuditRecordsByJobId(int jobId);

	@Query("select count(a) from Application a where a.job.id=?1")
	int findManyApplicationsByJobId(int jobId);

	@Query("select sum(md.percentage) from MandatoryDuty md where md.job.id=?1")
	int findWorkloadByJobId(int jobId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select j.finalMode from Job j where j.id =?1")
	Boolean findfinalModeByJobId(int jobId);
}
