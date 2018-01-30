package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entites.Event;
import entites.Member;
import facades.EventFacade;
import facades.MemberFacade;

@Named
@ViewScoped
public class Bean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB private EventFacade eventEJB;
	@EJB private MemberFacade memberEJB;
	
	@ManyToOne
	private List<Event> events;
	
	@OneToMany(mappedBy="event")
	private List<Member> members;
	
	private String title;
	private String location;
	private String startdate;
	private String enddate;
	private String starttime;
	private String endtime;
	
	private String username;
	private String password;
	private String role;
	
	private List<Event> createdEvents;
	private List<Event> attendedEvents;
	private int id;
	
	public void saveEvent(Event e) {
		if(id == e.getCreator()) eventEJB.edit(e);
	}
	
	public void deleteEvent(Event e) {
		eventEJB.remove(e);
		events = eventEJB.findAll();
	}
	
	public void addEvent() {
		Event e = new Event(title, location, startdate, enddate, starttime, endtime, id);
		eventEJB.create(e);
		events = eventEJB.findAll();
	}
	
	public List<Event> getEvent() {
		if(events == null) events = eventEJB.findAll();
		getMember();
		creator();
		return events;
	}
	
	public void saveMember(Member m) {
		memberEJB.edit(m);
	}
	
	public void deleteMember(Member m) {
		memberEJB.remove(m);
		members = memberEJB.findAll();
	}
	
	public List<Member> getMember() {
		if(members == null) members = memberEJB.findAll();
		return members;
	}
	
	public void addMember() {
		Member u = new Member(username, password, role);
		memberEJB.create(u);
		members = memberEJB.findAll();
	}
	
	void creator() {
		String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
		for(Member m : members) {
			if(m.getUsername().equals(user)) id = m.getId();
		}
	}
	
	public void attendEvent(Event e) {
		System.out.println("Jepp");
		/*System.out.println("aaa" + id + members.size());
		Member m = members.get(id - 1);
		m.setEvents(m.getEvents() + "," + e.getId());
		memberEJB.edit(m);*/
	}
	
	
	public List<Event> getCreatedEvents() { 
		if(createdEvents == null) {
			getEvent();
			createdEvents = new ArrayList<Event>();
			for(Event e: events) {
				if(e.getCreator() == id) {
					createdEvents.add(e);
				}
			}
		}
		return createdEvents; 
	}
	
	public List<Event> getAttendedEvents() { 
		if(attendedEvents == null) {
			getEvent();
			attendedEvents = new ArrayList<Event>();
			String[] args = members.get(id-1).getEvents().split(",");
			for(String arg : args) {
				attendedEvents.add(events.get(Integer.parseInt(arg)-1));
			}
		}
		return attendedEvents;
	}
	
	public void setEvent(List<Event> events) { this.events = events; }
	public void setMember(List<Member>members) { this.members = members;   }	
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	public String getStartdate() { return startdate; }
	public void setStartdate(String startdate) { this.startdate = startdate; }
	public String getEnddate() { return enddate; }
	public void setEnddate(String enddate) { this.enddate = enddate; }
	public String getStarttime() { return starttime; }
	public void setStarttime(String starttime) { this.starttime = starttime; }
	public String getEndtime() { return endtime; }
	public void setEndtime(String endtime) { this.endtime = endtime; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
	
	}

