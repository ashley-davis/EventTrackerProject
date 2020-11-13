package com.skilldistillery.dogwalker.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String name;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public Dog() {
	super();
	// TODO Auto-generated constructor stub
}

public Dog(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Dog other = (Dog) obj;
	if (id != other.id)
		return false;
	return true;
}

@Override
public String toString() {
	return "Dog [id=" + id + ", name=" + name + "]";
}

}
