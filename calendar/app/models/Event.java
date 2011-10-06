package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	public String eventName;
	public Date startTime, endTime;
	DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
	public boolean isPublic;
	String message = null;

	public Event(String title, String startsAt, String endsAt, boolean isPublic) {
		try {
			this.eventName = title;
			this.startTime = formatter.parse(startsAt);
			this.endTime = formatter.parse(endsAt);
			this.isPublic = isPublic;
		} catch (ParseException e) {
			message = "invalid input!!!";
			e.printStackTrace();

		}
	}

	public String getTitle() {
		return eventName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public String toString() {
		return "Event: " + eventName + "\nStarts: " + startTime + "\nEnd: "
				+ endTime + "\n";
	}
}
