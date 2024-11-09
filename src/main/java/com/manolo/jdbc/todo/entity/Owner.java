package com.manolo.jdbc.todo.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("owner")
@Builder
public record Owner(
		@Id Integer id,
        String fullName,
        String email,
        String username,

//		@MappedCollection(idColumn = "id", keyColumn = "owner_id")
//		Set<Todo> todos,

		@Column(value = "address_id")
		AggregateReference<Address, Integer> addressRef
) {
	public Owner withAddressRef(Integer addressId) {
		return new Owner(id, fullName, email, username, AggregateReference.to(addressId));
	}
}
