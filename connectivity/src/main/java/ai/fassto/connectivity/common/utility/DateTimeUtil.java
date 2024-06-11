package ai.fassto.connectivity.common.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATE_FORMATTER_yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMATTER_HHmm = DateTimeFormatter.ofPattern("HHmm");
    public static final String ZERO_PADDED_FMT_HH_MM_SS_SSSS = " 00:00:00.000";

    /**
     * yyyy-MM-dd HH:mm:ss -> 2022-08-30 05:46:32
     */
    public static String getNowByLocalDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss);
    }

    public static String toStringBy(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /** LocalDate to String **/
    public static String localDateToString(LocalDate localDate, DateTimeFormatter formatter){
        return localDate.format(formatter);
    }

    public static String localTimeToString(LocalTime localTime, DateTimeFormatter formatter){
        return localTime.format(formatter);
    }

    /** LocalDateTime to String **/
    public static String localDateTimeToString(LocalDateTime localDateTime, DateTimeFormatter formatter){
        return localDateTime.format(formatter);
    }

    /** String to LocalDate **/
    public static LocalDate stringToLocalDate(String date, DateTimeFormatter formatter){
        return LocalDate.parse(date, formatter);
    }

    public static LocalTime stringToLocalTime(String date, DateTimeFormatter formatter){
        return LocalTime.parse(date, formatter);
    }

    /** String to LocalDateTime **/
    public static LocalDateTime stringToLocalDateTime(String date, DateTimeFormatter formatter){
        return LocalDateTime.parse(date, formatter);
    }

    /** * yyyyMMdd -> yyyy-MM-dd **/
    public static String stringConvert(String string){
        LocalDate localDate = stringToLocalDate(string, DATE_FORMATTER_yyyyMMdd);
        return localDateToString(localDate, DATE_FORMATTER_yyyy_MM_dd);
    }

    /** * yyyy-MM-dd HH:mm:ss -> yyyy-MM-dd **/
    public static String localDateTimeStringConvert(String string){
        LocalDateTime localDateTime = stringToLocalDateTime(string, DATE_TIME_FORMATTER_yyyy_MM_dd__HHmmss);
        return localDateTimeToString(localDateTime, DATE_FORMATTER_yyyy_MM_dd);
    }

}
