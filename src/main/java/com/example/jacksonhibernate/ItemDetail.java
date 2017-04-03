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
class ItemDetail {

	@EmbeddedId
	@JsonUnwrapped
	private ItemDetailKey key;

	@OneToMany(mappedBy = "key.itemDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Extra> extras = new LinkedHashSet<>();

	private String status;

	@Data
	@EqualsAndHashCode(exclude = "item", callSuper = false)
	@ToString(exclude = "item")
	@Embeddable
	static class ItemDetailKey implements Serializable {
		private Short lineNo;

		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		@RestResource(exported = false)
		private Item item;
	}

}
