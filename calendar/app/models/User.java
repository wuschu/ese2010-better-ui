package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {

	public List<Calendar> calendarList;
	public String email;
	public String password;
	public String fullname;
	public boolean isAdmin;

	public User(String email, String password, String fullname) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		calendarList = new LinkedList<Calendar>();
	}

	public String getUserMail() {
		return email;
	}

	public void createCalendar(String name) {
		calendarList.add(new Calendar(name, this.getUserMail()));
	}

	public List<Calendar> getCalendarList() {
		return calendarList;
	}

	public Calendar getCalendarByName(String calendarname) {
		for (Calendar calendar : calendarList) {
			if (calendar.getCalendarName().equalsIgnoreCase(calendarname)) {
				return calendar;
			}
		}
		return null;
	}

	// iterator
	public List<Event> getEventsAllowedToSee(Calendar calendar, Date date) {
		List<Event> eventsAllowedToSee = new LinkedList<Event>();

		if (calendar.getOwner().equals(this))
			for (Event event : calendar.getEventsAsList()) {
				if (event.getEndTime().after(date)) {
					eventsAllowedToSee.add(event);
				}
			}
		else
			for (Event event : calendar.getEventsAsList()) {
				if (event.isPublic == true && event.getEndTime().after(date)) {
					eventsAllowedToSee.add(event);
				}
			}
		return eventsAllowedToSee;
	}

}
