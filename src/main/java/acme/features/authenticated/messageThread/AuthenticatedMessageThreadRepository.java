
package acme.features.authenticated.messageThread;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneById(int messageThreadId);

	@Query("select mt.creator from MessageThread mt where mt.id = ?1")
	Authenticated findCreatorByThreadID(int messageThreadId);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findCreatorById(int creatorId);

	@Query("select amt.messageThread from AuthenticatedMessageThread amt where amt.authenticated.id=?1")
	List<MessageThread> findManyByAuthenticated(int authenticatedId);
}
