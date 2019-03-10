package ua.kharkov.khpi.database.beans;

import java.io.Serializable;

public abstract class Entity implements Serializable{
	
	private static final long serialVersionUID = 8467257860808346237L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
