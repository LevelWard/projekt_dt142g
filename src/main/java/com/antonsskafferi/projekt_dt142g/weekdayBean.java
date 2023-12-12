package com.antonsskafferi.projekt_dt142g;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.text.DateFormatSymbols;
import java.util.Calendar;

@RequestScoped
@Named
public class weekdayBean {
    private String dayName;


    @PostConstruct
    public void init(){
        int dayOfWeek = dayOfWeekInt();
        dayName = dayString(dayOfWeek);
    }

    public String getDayName() {
        return dayName;
    }

    public String getAutomaticDay(){
        int dayOfWeek = dayOfWeekInt();
        return dayString(dayOfWeek);
    }


    private String dayString(int dayOfWeek){
        String [] weekday = new DateFormatSymbols().getWeekdays();
    //2 är måndag och fredag är 6
        if(dayOfWeek >= 2 && dayOfWeek <= 6){
            String dayName = weekday[dayOfWeek];
            return dayName.substring(0,1).toUpperCase() + dayName.substring(1);
        }else {
            return "Ingen dagen på helger!";
        }
    }

    private int dayOfWeekInt(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
