package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private List<Event> createdEvents;
	private List<Event> attendedEvents;
	private boolean admin;
	private int id;
	private String title, location, startdate, enddate, starttime, endtime;
	private String username, password, role;
	
	public void addEvent() {
		Event e = new Event(title, location, startdate, enddate, starttime, endtime, id);
		eventEJB.create(e);
		events = eventEJB.findAll();
	}
	
	public void saveEvent(Event e) {
		if(id == e.getCreator()) eventEJB.edit(e);
	}
	
	public void deleteEvent(Event e) {
		eventEJB.remove(e);
		events = eventEJB.findAll();
	}
	
	public List<Event> getEvent() {
		if(events == null) events = eventEJB.findAll();
		getMember();
		addCreator();
		return events;
	}

	public void addMember() {
		Member u = new Member(username, password, role, null);
		memberEJB.create(u);
		members = memberEJB.findAll();
	}
	
	public void saveMember(Member m) {
		memberEJB.edit(m);
		System.out.println(m.getUsername() + m.getRole() + m.getPassword());
	}
	
	public void deleteMember(Member m) {
		memberEJB.remove(m);
		members = memberEJB.findAll();
	}
	
	public List<Member> getMember() {
		if(members == null) members = memberEJB.findAll();
		return members;
	}
	
	void addCreator() {
		String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
		for(Member m : members) {
			if(m.getUsername().equals(user)) id = m.getId();
		}
	}
	
	public void attendEvent(Event e) {
		Collections.sort(members, new Comparator<Member>(){
            public int compare(Member a, Member b) {
           	 return String.valueOf(a.getId()).compareToIgnoreCase(String.valueOf(b.getId()));
           }
       });
		Member m = members.get(id-1);
		String str = m.getEvents();
		if(str == null) {
			str = ""; 
		}
		else {
			str += ",";
		}
		String args[] = str.split(",");
		for(String arg : args) {
			if(arg.equals(String.valueOf(e.getId()))) return;
		}
		m.setEvents(str + e.getId());
		System.out.println("Test " +  id + " " + e.getId() + " " + str);
		memberEJB.edit(m);
	}
	
	public List<Event> getAttendedEvents() {
		Collections.sort(members, new Comparator<Member>(){
            public int compare(Member a, Member b) {
           	 return String.valueOf(a.getId()).compareToIgnoreCase(String.valueOf(b.getId()));
           }
       });
		Member m = members.get(id-1);
		for(int i = 0; i < members.size(); i ++) {
			System.out.println(i + " " + members.get(i).getUsername());
		}
		if(m.getEvents() != null && attendedEvents == null) {
			attendedEvents = new ArrayList<Event>();
			String str[] = m.getEvents().split(",");
			for(String arg : str) {
				attendedEvents.add(events.get(Integer.parseInt(arg)-1));
			}
		}
		return attendedEvents;
	}
	
	public List<Event> getCreatedEvents() { 
		if(createdEvents == null) {
			getEvent();
			createdEvents = new ArrayList<Event>();
			for(Event e: events) {
				if(e.getCreator() == id) createdEvents.add(e);
			}
		}
		return createdEvents; 
	}
	
	public boolean isAdmin() {
		if(members == null) members = memberEJB.findAll();
		String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
		for(Member m : members) {
			if(m.getUsername().equals(user)) {
				if(m.getRole().equals("admin")) admin = true;
				else admin = false; 
			}
		}
		return admin;
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