package entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username", nullable = false, length = 32) 	
	private String username;
	
	@Column(name="password", nullable = false, length = 32) 	
	private String password;
	
	@Column(name="role", nullable = false, length = 32) 	
	private String role;
	
	@Column(name="events")	
	private String events;
	
	public Member(){ }
	
	public Member(String username, String password, String role) { 
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
	
	public String getEvents() { return events; }
	public void setEvents(String events) { this.events = events; }
}
