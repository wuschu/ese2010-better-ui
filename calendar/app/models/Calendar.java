package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Calendar {
	public String name;
	String email;
	List<Event> eventList;

	public Calendar(String name, String email) {
		this.name = name;
		this.email = email;
		eventList = new LinkedList<Event>();
	}

	public void addEvent(Event event) {
		eventList.add(event);
	}

	public List<Event> getEventAtDate(Date date) {
		List<Event> eventsAtDateList = new LinkedList<Event>();
		for (Event event : eventList) {
			if (((event.getStartTime().getTime() / 1000 / 60 / 60 / 24) <= (date
					.getTime() / 1000 / 60 / 60 / 24))
					&& ((date.getTime() / 1000 / 60 / 60 / 24) <= (event
							.getEndTime().getTime() / 1000 / 60 / 60 / 24))) {
				eventsAtDateList.add(event);
			}

		}
		return eventsAtDateList;
	}

	public String getOwner() {
		return email;
	}

	public String getCalendarName() {
		return name;
	}

	public boolean isEmtpy() {
		return eventList.isEmpty();
	}

	public List<Event> getEventsAsList() {
		return eventList;
	}

	public String getAllEventsOfCalendar() {
		return "In calendar " + name + " are the following events: "
				+ eventList.toString();
	}

	public String toString() {
		return eventList.toString();
	}

}
