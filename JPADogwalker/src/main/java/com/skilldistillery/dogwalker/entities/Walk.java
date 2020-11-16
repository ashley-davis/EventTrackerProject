package com.skilldistillery.dogwalker.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "walks")
public class Walk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "start")
	private Date start;
	
	@Column(name = "duration")
	private int duration;

	@Column(name = "cost")
	private float cost;
	
	@Column(name = "completed")
	private Boolean completed;
	
	@Column(name = "cancelled")
	private Boolean cancelled;
	
	// constructors
	public Walk() {
		
	}
	
	public Walk(String name, Date start, int duration, float cost) {
		
		setName(name);
		setStart(start);
		setDuration(duration);
		setCost(cost);
	}
	
	public Walk(String name, Date start, int duration, float cost, boolean completed, boolean cancelled) {
		
		setName(name);
		setStart(start);
		setDuration(duration);
		setCost(cost);
		setCompleted(completed);
		setCancelled(cancelled);
	}
	
	// public getters/setters
	public long getId() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String value) {
		
		name = value;
	}
	
	public Date getStart() {
		
		return (start == null) ? null : new Date(start.getTime());
	}
	
	public void setStart(Date value) {
		
		start = value;
	}
	
	public int getDuration() {
		
		return duration;
	}
	
	public void setDuration(int value) {
		
		duration = value;
	}

	public float getCost() {
	
		return cost;
	}
	
	public void setCost(float value) {
		
		cost = value;
	}

	public Boolean getCompleted() {
		
		return completed;
	}
	
	public boolean isCompletedSet() {
		
		return completed != null;
	}
	
	public void setCompleted(boolean value) {
		
		completed = value;
	}
	
	public Boolean getCancelled() {
		
		return cancelled;
	}
	
	public void setCancelled(boolean value) {
		
		cancelled = value;
	}
	
	public boolean isCancelledSet() {
		
		return cancelled != null;
	}
	
	@PrePersist
	public void prePersist() {
	    
		if (!isCompletedSet())
			setCompleted(false);
		
		if (!isCancelledSet())
			setCancelled(false);
	}
}