package com.retailstore.discounts.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class DateTimeUtil {

    public static Instant nowInUTC() {
        return ZonedDateTime.now(ZoneOffset.UTC).toInstant();
    }

    public static Instant nowMinusYearsInUTC(int years) {
        return ZonedDateTime.now(ZoneOffset.UTC).minusYears(years).toInstant();
    }
}
