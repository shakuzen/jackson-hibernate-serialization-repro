package com.example.jacksonhibernate;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
class Reservation {

	@Id
	@GeneratedValue
	private UUID id;

	@OneToMany(mappedBy = "key.reservation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Item> items = new LinkedHashSet<>();

	private String something;

}
