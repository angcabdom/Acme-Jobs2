
package acme.entities.requests_;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "dateLimit")
})
public class Request_ extends DomainEntity {

	//serialisation identifier
	private static final long	serialVersionUID	= 1L;

	//atributos

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dateLimit;

	@NotBlank
	@Column(length = 1000)
	private String				text;

	@NotNull
	private Money				money;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^R[A-Z]{4}\\-\\d{5}$")
	private String				ticker;

}
