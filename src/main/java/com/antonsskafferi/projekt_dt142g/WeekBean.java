package com.antonsskafferi.projekt_dt142g;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Named("week")
@RequestScoped
public class WeekBean {
    public int Week1(int val){
        LocalDate localDate = LocalDate.now();
        LocalDate date = localDate.plusWeeks(val);
        WeekFields weekFields = WeekFields.of(Locale.UK);

        return date.get(weekFields.weekOfWeekBasedYear());
    }
}
