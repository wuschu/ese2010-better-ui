import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Event;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserTest extends UnitTest {

	User dominik = new User("dominik@me.com", "password", "DF");
	User francis = new User("francis@me.com", "password", "FB");
	DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
	Date date = new Date();

	@Before
	public void setup() {
		Fixtures.deleteAll();
	}

	@Test
	public void testIfUserHasEmail() {
		assertEquals(dominik.getUserMail(), "dominik@me.com");
	}

	@Test
	public void testCreateCalendar() {
		dominik.createCalendar("testCreation");
		assertFalse(dominik.getCalendarList().isEmpty());
	}

	@Test
	public void testGetCalendar() {
		dominik.createCalendar("testCreation");
		assertTrue(dominik.getCalendarByName("testCreation").isEmtpy());
		dominik.getCalendarByName("testCreation").addEvent(
				new Event("title", formatter.format(date), formatter
						.format(date), true));
		assertFalse(dominik.getCalendarByName("testCreation").isEmtpy());
		assertEquals(dominik.getCalendarByName("testCreation")
				.getAllEventsOfCalendar(),
				"In calendar "
						+ dominik.getCalendarByName("testCreation")
								.getCalendarName()
						+ " are the following events: "
						+ dominik.getCalendarByName("testCreation").toString());
	}

	@Test
	public void testIterator() {
		dominik.createCalendar("dominikCalendar");
		francis.createCalendar("francisCalendar");
		dominik.getCalendarByName("dominikCalendar").addEvent(
				new Event("dominikEvent", formatter.format(date), formatter
						.format(date), true));
		dominik.getCalendarByName("dominikCalendar").addEvent(
				new Event("dominikEvent2", formatter.format(date), formatter
						.format(date), false));

		francis.getCalendarByName("francisCalendar").addEvent(
				new Event("francisEvent", formatter.format(date), formatter
						.format(date), false));

		assertTrue(dominik.getEventsAllowedToSee(
				francis.getCalendarByName("francisCalendar"), date).isEmpty());

		assertFalse(francis.getEventsAllowedToSee(
				dominik.getCalendarByName("dominikCalendar"), new Date(0))
				.isEmpty());

		assertEquals(
				francis.getEventsAllowedToSee(
						dominik.getCalendarByName("dominikCalendar"),
						new Date(0)).size(), 1);

		assertTrue(francis
				.getEventsAllowedToSee(
						dominik.getCalendarByName("dominikCalendar"),
						new Date(0))
				.get(0)
				.equals(dominik.getCalendarByName("dominikCalendar")
						.getEventsAsList().get(0)));

		assertFalse(dominik.getEventsAllowedToSee(
				dominik.getCalendarByName("dominikCalendar"), new Date(0))
				.isEmpty());

		/*
		 * Doesn't work yet.... assertEquals(dominik.getEventsAllowedToSee(
		 * dominik.getCalendarByName("dominikCalendar"), new Date(0)),
		 * dominik.getCalendarByName("dominikCalendar").getEventsAsList()
		 * .get(0));
		 */

	}

}
