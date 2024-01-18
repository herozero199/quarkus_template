package com.odn.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class DateUtils {
    private static String GmtPlus7 = "GMT+7";
    private static String DTFM_DATE_01 = "yyyyMMdd";
    private static String DTFM_DATE_02 = "dd/MM/yyyy";

    // string format "dd/MM/yyyy" in GMT+7 to ZonedDateTime
    public static ZonedDateTime stringDate02_GmtPlus7_ToTime(String input, MutableBoolean result) {
        result.setFalse();

        if (StringUtils.isEmpty(input)) {
            result.setTrue();
            return null;
        }

        try {
            // Define the formatter for the input string format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DTFM_DATE_02);

            // Parse the string to LocalDate (no timezone information)
            LocalDate localDate = LocalDate.parse(input, formatter);

            // Get the ZonedDateTime in GMT+7 timezone
            ZonedDateTime zonedDateTime = localDate.atStartOfDay().atZone(ZoneId.of(GmtPlus7));

            result.setTrue();
            return zonedDateTime;
        } catch (Exception ex) {
            return null;
        }
    }
}
