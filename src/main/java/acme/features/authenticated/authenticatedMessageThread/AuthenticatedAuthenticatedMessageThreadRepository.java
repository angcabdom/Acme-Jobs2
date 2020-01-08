
package acme.features.authenticated.authenticatedMessageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.authenticatedMessageThreads.AuthenticatedMessageThread;
import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select amt from AuthenticatedMessageThread amt where amt.id = ?1")
	AuthenticatedMessageThread findOneById(int authenticatedMessageThreadId);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int messageThreadId);

	@Query("select amt from AuthenticatedMessageThread amt where amt.id = ?1")
	AuthenticatedMessageThread findOneAuthenticatedMessageThreadById(int authenticatedMessageThreadId);

	@Query("select a from Authenticated a where a.userAccount.username=?1")
	Authenticated findAuthenticatedByName(String authenticatedName);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int creatorId);

	@Query("select amt from AuthenticatedMessageThread amt where amt.authenticated.id=?2 and amt.messageThread.id=?1")
	AuthenticatedMessageThread findAuthenticatedMessageThreadByMessageThreadIdAndAuthenticatedId(int messageThreadId, int authenticatedId);

	@Query("select amt from AuthenticatedMessageThread amt where amt.messageThread.id=?1")
	Collection<AuthenticatedMessageThread> findManyAuthenticatedMessageThreadByThreadId(int messageId);
}
