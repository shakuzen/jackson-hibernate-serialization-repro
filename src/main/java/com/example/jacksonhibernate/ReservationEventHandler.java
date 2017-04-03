package com.example.jacksonhibernate;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
class ReservationEventHandler {

	/**
	 * Maintains the back-references to parent entities when saving or updating
	 * a {@link Reservation}.
	 *
	 * @param reservation resource to be created/saved
	 */
	@HandleBeforeCreate
	@HandleBeforeSave
	void maintainBackReferences(Reservation reservation) {
		for (Item item : reservation.getItems()) {
			item.getKey().setReservation(reservation);
			for (ItemDetail itemDetail : item.getItemDetails()) {
				itemDetail.getKey().setItem(item);
				for (Extra extra : itemDetail.getExtras()) {
					extra.getKey().setItemDetail(itemDetail);
				}
			}
		}
	}
}
