
package acme.entities.authenticatedMessageThreads;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuthenticatedMessageThread extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@ManyToOne(optional = false)
	private Authenticated		authenticated;

	@ManyToOne(optional = false)
	private MessageThread		messageThread;
}
