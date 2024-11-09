package com.manolo.jdbc.user.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;

@Table("user")
@Builder
public record User(
		@Id Integer id,
		String username,
		String password,
		Boolean accountNonExpired,
		Boolean accountNonLocked,
		Boolean credentialsNonExpired,
		Boolean enabled,
		String firstName,
		String lastName,
		String emailAddress,
		LocalDate birthdate,
//		@JsonIgnore
		@MappedCollection(idColumn = "user_id", keyColumn = "authority_id")
		Set<UserAuthority> authorityRefs
) {
	public User {
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		enabled = true;
	}

	public void addAuthorityRef(Integer authorityId) {
		this.authorityRefs.add(new UserAuthority(AggregateReference.to(authorityId)));
	}

	public void removeAuthorityRef(Integer authorityId) {
		this.authorityRefs.remove(new UserAuthority(AggregateReference.to(authorityId)));
	}

	public List<Integer> getAuthorityIds() {
		return this.authorityRefs.stream()
				.map(UserAuthority::authorityId)
				.map(AggregateReference::getId)
				.collect(Collectors.toList());
	}

}
