package com.example.jacksonhibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
class Item {

	@EmbeddedId
	@JsonUnwrapped
	private ItemKey key;

	@OneToMany(mappedBy = "key.item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ItemDetail> itemDetails = new LinkedHashSet<>();

	private String something;

	@Data
	@EqualsAndHashCode(exclude = "reservation", callSuper = false)
	@ToString(exclude = "reservation")
	@Embeddable
	static class ItemKey implements Serializable {
		private Short lineNo;

		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		@RestResource(exported = false)
		private Reservation reservation;
	}

}
