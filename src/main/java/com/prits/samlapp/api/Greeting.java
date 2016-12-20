package com.prits.samlapp.api;

import java.io.Serializable;
import java.util.UUID;

public class Greeting implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String message;
	
	public Greeting(String message){
		this.id = UUID.randomUUID().toString();
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
