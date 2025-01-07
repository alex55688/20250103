package com.tsb.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class Users implements Serializable{
	
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name="markname", nullable =false)
	private String markname;
	
	@Column(name="password", nullable = false)
	private String password;
}
