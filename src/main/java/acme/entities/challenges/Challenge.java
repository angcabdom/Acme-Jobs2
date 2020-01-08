
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Challenge extends DomainEntity {

	//serialisation identifier
	private static final long	serialVersionUID	= 1L;

	//atributos

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;

	@NotBlank
	@Column(length = 1000)
	private String				description;

	@NotBlank
	private String				goalGold;

	@NotBlank
	private String				rewardGold;

	@NotBlank
	private String				goalSilver;

	@NotBlank
	private String				rewardSilver;

	@NotBlank
	private String				goalBronze;

	@NotBlank
	private String				rewardBronze;


	// Derived attributes --------------------------------

	@Transient
	public String getGold() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(this.goalGold);
		result.append(" --> ");
		result.append(this.rewardGold);

		return result.toString();
	}

	@Transient
	public String getSilver() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(this.goalSilver);
		result.append(" --> ");
		result.append(this.rewardSilver);

		return result.toString();
	}

	@Transient
	public String getBronze() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(this.goalBronze);
		result.append(" --> ");
		result.append(this.rewardBronze);

		return result.toString();
	}
}
