package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages {

	private int id;
	private Nerc nerc;
	private int customer_affected;
	private LocalDateTime began;
	private LocalDateTime finished;
	private long duration;
	private int year;
	
	public PowerOutages(int id, Nerc nerc, int customer_affected, LocalDateTime began, LocalDateTime finished) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.customer_affected = customer_affected;
		this.began = began;
		this.finished = finished;
		this.year=began.getYear();
		LocalDateTime temp= LocalDateTime.from(began);
		this.duration= temp.until(finished, ChronoUnit.HOURS);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getCustomer_affected() {
		return customer_affected;
	}

	public void setCustomer_affected(int customer_affected) {
		this.customer_affected = customer_affected;
	}

	

	public LocalDateTime getBegan() {
		return began;
	}

	public void setBegan(LocalDateTime began) {
		this.began = began;
	}

	public LocalDateTime getFinished() {
		return finished;
	}

	public void setFinished(LocalDateTime finished) {
		this.finished = finished;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	

	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", nerc=" + nerc + ", customer_affected=" + customer_affected + ", began="
				+ began + ", finished=" + finished + ", duration=" + duration + ", year=" + year + "]" + "\n";
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
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
