package com.example.jacksonhibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
class Extra {

	@EmbeddedId
	@JsonUnwrapped
	private ExtraKey key;

	private String type;

	@Data
	@EqualsAndHashCode(exclude = "itemDetail", callSuper = false)
	@ToString(exclude = "itemDetail")
	@Embeddable
	static class ExtraKey implements Serializable {
		private Short lineNo;

		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		@RestResource(exported = false)
		private ItemDetail itemDetail;
	}
}
