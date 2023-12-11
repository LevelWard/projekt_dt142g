package com.antonsskafferi.projekt_dt142g;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Calendar;

@RequestScoped
@Named
public class weekOfMonthBean {

    private int WeekOfMonth;

    @PostConstruct
    public void init(){
        WeekOfMonth = calculate();
    }

    public int getWeekOfMonth() {
        return WeekOfMonth;
    }


    private int calculate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}