package com.alcode.az.fillingstation.service;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.net.InetAddress;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeService {

    private static final String NTP_SERVER = "pool.ntp.org"; // Default NTP server

    // Method 1: Get current local time
    public LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    // Method 2: Get current server time (assume server is in UTC)
    public LocalTime getCurrentServerTime() {
        return LocalTime.now(Clock.systemUTC());
    }

    // Method 3: Get current local date
    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    // Method 4: Get current server date (assume server is in UTC)
    public LocalDate getCurrentServerDate() {
        return LocalDate.now(Clock.systemUTC());
    }

    // Method 5: Get current local timestamp
    public LocalDateTime getCurrentLocalTimestamp() {
        return LocalDateTime.now();
    }

    // Method 6: Get current server timestamp (assume server is in UTC)
    public LocalDateTime getCurrentServerTimestamp() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    // Method 7: Get current local time as a formatted string
    public String getFormattedLocalTime(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.now().format(formatter);
    }

    // Method 8: Get current server time as a formatted string
    public String getFormattedServerTime(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.now(Clock.systemUTC()).format(formatter);
    }

    // Method 9: Get current local date as a formatted string
    public String getFormattedLocalDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.now().format(formatter);
    }

    // Method 10: Get current server date as a formatted string
    public String getFormattedServerDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.now(Clock.systemUTC()).format(formatter);
    }

    // Method 11: Get current local timestamp as a formatted string
    public String getFormattedLocalTimestamp(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }

    // Method 12: Get current server timestamp as a formatted string
    public String getFormattedServerTimestamp(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(Clock.systemUTC()).format(formatter);
    }

    // Method 13: Get current time zone
    public ZoneId getCurrentTimeZone() {
        return ZoneId.systemDefault();
    }

    // Method 14: Convert a timestamp to a specific time zone
    public ZonedDateTime convertToTimeZone(LocalDateTime timestamp, ZoneId zoneId) {
        return timestamp.atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId);
    }

    // Method 15: Get the difference between two timestamps in seconds
    public long getDifferenceInSeconds(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds();
    }

    // Method 16: Get the current epoch time in seconds
    public long getCurrentEpochTime() {
        return Instant.now().getEpochSecond();
    }

    // Method 17: Get the current epoch time in milliseconds
    public long getCurrentEpochTimeMillis() {
        return Instant.now().toEpochMilli();
    }

    // Method 18: Get the current day of the week
    public DayOfWeek getCurrentDayOfWeek() {
        return LocalDate.now().getDayOfWeek();
    }

    // Method 19: Get the current month
    public Month getCurrentMonth() {
        return LocalDate.now().getMonth();
    }

    // Method 20: Get the current year
    public int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    // Method 21: Add days to the current local date
    public LocalDate addDaysToLocalDate(int days) {
        return LocalDate.now().plusDays(days);
    }

    // Method 22: Subtract days from the current local date
    public LocalDate subtractDaysFromLocalDate(int days) {
        return LocalDate.now().minusDays(days);
    }

    // Method 23: Check if a year is a leap year
    public boolean isLeapYear(int year) {
        return Year.of(year).isLeap();
    }

    // Method 24: Get the number of days in a month
    public int getDaysInMonth(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    // Method 25: Get NTP time from a specified NTP server
    public LocalDateTime getNtpTime(String ntpServer) {
        try {
            NTPUDPClient client = new NTPUDPClient();
            client.setDefaultTimeout(5000); // Set timeout to 5 seconds
            client.open();

            InetAddress hostAddr = InetAddress.getByName(ntpServer);
            TimeInfo timeInfo = client.getTime(hostAddr);
            timeInfo.computeDetails(); // Compute offset/delay if needed

            long ntpTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
            client.close();

            // Convert NTP time to LocalDateTime in UTC
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(ntpTime), ZoneId.of("UTC"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve NTP time", e);
        }
    }

    // Method 26: Get NTP time from the default NTP server
    public LocalDateTime getNtpTime() {
        return getNtpTime(NTP_SERVER);
    }

    // Method 27: Convert NTP time to a specific time zone
    public LocalDateTime getNtpTimeInTimeZone(String ntpServer, ZoneId zoneId) {
        LocalDateTime ntpTime = getNtpTime(ntpServer);
        return ntpTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    // Method 28: Compare NTP time with local time
    public String compareNtpTimeWithLocalTime(String ntpServer, ZoneId zoneId) {
        LocalDateTime ntpTime = getNtpTimeInTimeZone(ntpServer, zoneId);
        LocalDateTime localTime = LocalDateTime.now();

        Duration difference = Duration.between(localTime, ntpTime);
        long secondsDifference = difference.getSeconds();

        if (secondsDifference == 0) {
            return "NTP time and local time are the same.";
        } else if (secondsDifference > 0) {
            return "NTP time is ahead of local time by " + secondsDifference + " seconds.";
        } else {
            return "NTP time is behind local time by " + Math.abs(secondsDifference) + " seconds.";
        }
    }
}
