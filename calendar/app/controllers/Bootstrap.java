package controllers;

import models.Database;
import models.Event;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		User testUser = new User("feller", "password", "DF");
		User otherUser = new User("brue", "password", "FB");

		testUser.createCalendar("My Calendar 1");
		testUser.createCalendar("My Calendar 2");
		otherUser.createCalendar("My Calendar 3");
		otherUser.createCalendar("My Calendar 4");
		testUser.getCalendarByName("My Calendar 1").addEvent(
				new Event("My Event 1", "01.10.2011, 12:00",
						"01.10.2011, 14:00", false));
		testUser.getCalendarByName("My Calendar 1").addEvent(
				new Event("My Event 2", "15.10.2011, 12:00",
						"16.10.2011, 08:00", false));
		otherUser.getCalendarByName("My Calendar 3").addEvent(
				new Event("My Event 3", "16.10.2011, 12:00",
						"16.10.2011, 18:00", true));

		otherUser.getCalendarByName("My Calendar 4").addEvent(
				new Event("My Event 4", "18.10.2011, 12:00",
						"18.10.2011, 18:00", false));

		Database.addUser(testUser);
		Database.addUser(otherUser);

	}

}