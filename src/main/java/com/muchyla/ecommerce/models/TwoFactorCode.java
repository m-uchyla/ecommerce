package com.muchyla.ecommerce.models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TwoFactorCode {

	@Id
	Long id;
	@OneToOne
	User owner;
	Date deadlineDate;
	
	public TwoFactorCode() {}

	public TwoFactorCode(Long id,User owner) {
		this.id = id;
		this.owner = owner;
		//create deadline 5 min after creating this obj
		Calendar date = Calendar.getInstance();
		long timeInSecs = date.getTimeInMillis();
		this.deadlineDate = new Date(timeInSecs + (5 * 60 * 1000));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	@Override
	public String toString() {
		return "TwoFactorCode [id=" + id + ", owner=" + owner + ", deadlineDate=" + deadlineDate + "]";
	}
	
}
