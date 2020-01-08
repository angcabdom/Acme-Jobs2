
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneMessageById(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select amt.authenticated.userAccount.username from AuthenticatedMessageThread amt where amt.messageThread.id = ?1")
	Collection<String> findManyAuthenticatedByMessageThreadId(int messageThreadId);

	@Query("select m from Message m where m.messageThread.id = ?1 ")
	Collection<Message> findManyByMessageThreadId(int messageThreadId);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findCreatorById(int creatorId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
