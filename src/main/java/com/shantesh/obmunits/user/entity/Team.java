package com.shantesh.obmunits.user.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="team")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
	@Id
	@Column(name="teamId")
	private String teamId;
	
	@Column(name="teamName")
	private String teamName;

	@OneToMany(mappedBy = "team")
	private Set<User> user;
	
}
