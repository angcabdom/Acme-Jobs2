
package acme.entities.configuration;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	//serialisation identifier
	private static final long	serialVersionUID	= 1L;

	//atributos
	@NotBlank
	private String				title;
	@NotNull
	private Double				spamThreshold;

	//@ElementCollection(targetClass = String.class)
	@ElementCollection(fetch = FetchType.EAGER)
	@NotNull
	@Column(length = 1000)
	private Collection<String>	spamWords;

}
