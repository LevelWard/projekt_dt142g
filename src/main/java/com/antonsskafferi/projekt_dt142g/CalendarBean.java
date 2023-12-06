package com.antonsskafferi.projekt_dt142g;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CalendarBean {

    private List<Month> months;

    @PostConstruct
    public void init() {
        months = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            int numberOfDays = LocalDate.of(2023, month, 1).lengthOfMonth();
            months.add(new Month(month, numberOfDays));
        }
    }

    public List<Month> getMonths() {
        return months;
    }

    public List<Integer> getDays(Month month) {
        List<Integer> days = new ArrayList<>();
        for (int day = 1; day <= month.getNumberOfDays(); day++) {
            days.add(day);
        }
        return days;
    }
}
