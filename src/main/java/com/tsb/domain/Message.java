package com.tsb.domain;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message implements Serializable{
	
	private String role;
	private String content;
	
	public Message(String role) {
		this.role = role;
	}
}
