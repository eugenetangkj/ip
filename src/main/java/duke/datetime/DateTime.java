package duke.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

/**
 * This class provides methods to process dates given by the user, whether it is included with time or not.
 */
public abstract class DateTime {
    /**
     * Returns a <code>Temporal</code> that encapsulates date and or time information.
     *
     * @param rawDateString The raw String that contains date and or time information.
     * @return the <code>Temporal</code> with the date and or time information.
     * @throws DateTimeParseException if the raw String is not of the correct date format
     *                                as requested in the command list of the bot.
     */
    public static Temporal getDateTimeObject(String rawDateString)
            throws DateTimeParseException {
        //Possible formats, with and without time
        String timePatternOne = "yyyy-MM-dd HH:mm";
        String timePatternTwo = "yyyy-MM-dd";
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern(timePatternOne);
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern(timePatternTwo);

        //Determine which format
        boolean hasTime = (rawDateString.length() > timePatternTwo.length());
        DateTimeFormatter formatterToUse = (hasTime)
                ? formatterWithTime
                : formatterWithoutTime;
        if (hasTime) {
            //A date with time
            return LocalDateTime.parse(rawDateString, formatterToUse);
        } else {
            //A date without time
            return LocalDate.parse(rawDateString, formatterToUse);
        }
    }

    /**
     * Determines if two dates specify a valid duration.
     *
     * @param start The <code>Temporal</code> encapsulating the start date and time.
     * @param end The <code>Temporal</code> encapsulating the end date and time.
     * @return true if start happens before or is equal to end, else false.
     */
    public static boolean isValidDuration(Temporal start, Temporal end) {
        assert start != null : "Invalid start temporal.";
        assert end != null : "Invalid end temporal.";


        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            return ((LocalDateTime) end).isAfter((LocalDateTime) start)
                    || ((LocalDateTime) end).equals((LocalDateTime) start);
        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            return ((LocalDate) end).isAfter((LocalDate) start) || ((LocalDate) end).equals((LocalDate) start);
        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            LocalDate endDateOnly = LocalDate.of(((LocalDateTime) end).getYear(), (
                    (LocalDateTime) end).getMonthValue(), ((LocalDateTime) end).getDayOfMonth());
            return (endDateOnly.isAfter((LocalDate) start)) || (endDateOnly.equals((LocalDate) start));
        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            LocalDate startDateOnly = LocalDate.of(((LocalDateTime) start).getYear(), (
                    (LocalDateTime) start).getMonthValue(), ((LocalDateTime) start).getDayOfMonth());
            return (((LocalDate) end).isAfter(startDateOnly)) || (((LocalDate) end).equals(startDateOnly));
        }
        return true;
    }

    /**
     * Determines if one date is equal to another date, based on year, month and day.
     *
     * @param start The Temporal encapsulating the start date and time.
     * @param end The Temporal encapsulating the end date and time.
     * @return true if both refer to the same day, else false.
     */
    public static boolean isEqualDate(Temporal start, Temporal end) {
        assert start != null : "Start is an invalid temporal.";
        assert end != null : "End is an invalid temporal.";

        if (start instanceof LocalDateTime && end instanceof LocalDateTime) {
            return ((LocalDateTime) end).equals((LocalDateTime) start);
        } else if (start instanceof LocalDate && end instanceof LocalDate) {
            return ((LocalDate) end).equals((LocalDate) start);
        } else if (start instanceof LocalDate && end instanceof LocalDateTime) {
            LocalDate endDateOnly = LocalDate.of(((LocalDateTime) end).getYear(), (
                    (LocalDateTime) end).getMonthValue(), ((LocalDateTime) end).getDayOfMonth());
            return (endDateOnly.equals((LocalDate) start));
        } else if (start instanceof LocalDateTime && end instanceof LocalDate) {
            LocalDate startDateOnly = LocalDate.of(((LocalDateTime) start).getYear(), (
                    (LocalDateTime) start).getMonthValue(), ((LocalDateTime) start).getDayOfMonth());
            return (((LocalDate) end).equals(startDateOnly));
        }
        return true;
    }

    /**
     * Formats a date, returning a string in either yyyy-MM-dd HH:mm or yyyy-MM-dd format.
     *
     * @return a string representing the formatted deadline.
     */
    public static String formatDate(Temporal date) {
        assert date != null : "Invalid temporal.";
        if (date instanceof LocalDateTime) {
            //Case 1: Got date and time
            LocalDateTime dateTimeObject = (LocalDateTime) date;
            String monthString = dateTimeObject.getMonth().toString().charAt(0)
                    + dateTimeObject.getMonth().toString().substring(1).toLowerCase();
            String dayString = (dateTimeObject.getDayOfMonth() < 10)
                    ? "0" + Integer.toString(dateTimeObject.getDayOfMonth())
                    : Integer.toString(dateTimeObject.getDayOfMonth());
            String hourString = (dateTimeObject.getHour() < 10)
                    ? "0" + Integer.toString(dateTimeObject.getHour())
                    : Integer.toString(dateTimeObject.getHour());
            String minuteString = (dateTimeObject.getMinute() < 10)
                    ? "0" + Integer.toString(dateTimeObject.getMinute())
                    : Integer.toString(dateTimeObject.getMinute());

            return monthString + " " + dayString + " " + dateTimeObject.getYear() + " " + hourString + ":"
                    + minuteString;
        } else {
            //Case 2: Got date only
            LocalDate dateObject = (LocalDate) date;
            String monthString = dateObject.getMonth().toString().charAt(0)
                    + dateObject.getMonth().toString().substring(1).toLowerCase();
            String dayString = (dateObject.getDayOfMonth() < 10)
                    ? "0" + Integer.toString(dateObject.getDayOfMonth())
                    : Integer.toString(dateObject.getDayOfMonth());

            return monthString + " " + dayString + " " + dateObject.getYear();
        }
    }
}
