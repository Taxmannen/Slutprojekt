package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title", nullable = false, length = 32) 	
	private String title;
	
	@Column(name="location", nullable = false, length = 32) 	
	private String location;
	
	@Column(name="startdate", nullable = false, length = 32) 	
	private String startdate;
	
	@Column(name="enddate", nullable = false, length = 32) 	
	private String enddate;
	
	@Column(name="starttime", nullable = false, length = 32) 	
	private String starttime;
	
	@Column(name="endtime", nullable = false, length = 32) 	
	private String endtime;
	
	@Column(name="creator") 	
	private int creator;
	
	public Event() { }
	public Event(String title, String location, String startdate, String enddate, String starttime, String endtime, int creator) {
		this.title = title;
		this.location = location;
		this.startdate = startdate;
		this.enddate = enddate;
		this.starttime = starttime;
		this.endtime = endtime;
		this.creator = creator;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	
	public String getStartDate() { return startdate; }
	public void setStartDate(String startdate) { this.startdate = startdate; }
	
	public String getEndDate() { return enddate; }
	public void setEndDate(String enddate) { this.enddate = enddate; }
	
	public String getStartTime() { return starttime; }
	public void setStartTime(String starttime) { this.starttime = starttime; }
	
	public String getEndTime() { return endtime; }
	public void setEndTime(String endtime) { this.endtime = endtime; }
	
	public int getCreator() { return creator; }
	public void setCreator(int creator) { this.creator = creator; }
}
