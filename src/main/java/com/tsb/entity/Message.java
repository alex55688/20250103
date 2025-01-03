package com.tsb.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message implements Serializable{
	
	private int code;
	private String msg;
}
