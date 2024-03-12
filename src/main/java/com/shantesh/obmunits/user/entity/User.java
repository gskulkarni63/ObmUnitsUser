package com.shantesh.obmunits.user.entity;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@Column(name = "personId")
	private String personId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "email")
	private String email;

	@Column(name = "dateOfBirth")
	private LocalDate dateOfBirth;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teamId", referencedColumnName = "teamId")
	private Team team;

}


