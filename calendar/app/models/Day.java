package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Day {

	public int dayOfTheMonth;
	public int monthOfTheYear;
	public int year;
	public String dateAsString;
	public boolean hasEvents;
	public static DateFormat formatter = new SimpleDateFormat(
			"dd.MM.yyyy, HH:mm");

	public Day(int dayOfTheMonth, int monthOfTheYear, int year,
			Calendar calendar) throws ParseException {
		this.dayOfTheMonth = dayOfTheMonth;
		this.monthOfTheYear = monthOfTheYear;
		this.year = year;
		this.dateAsString = dayOfTheMonth + "." + monthOfTheYear + "." + year
				+ ", 08:00";

		this.hasEvents = !calendar
				.getEventAtDate(formatter.parse(dateAsString)).isEmpty();

	}

}
