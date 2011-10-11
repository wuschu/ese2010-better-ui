package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.data.validation.Required;

public class Event {
	public String eventName;
	public Date startTime, endTime;
	DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
	public boolean isPublic;

	public Event(@Required String title, @Required String startsAt,
			@Required String endsAt, @Required boolean isPublic) {
		try {
			this.eventName = title;
			this.startTime = formatter.parse(startsAt);
			this.endTime = formatter.parse(endsAt);
			this.isPublic = isPublic;
		} catch (ParseException e) {
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
