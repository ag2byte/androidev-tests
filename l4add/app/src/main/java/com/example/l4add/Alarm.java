package com.example.l4add;

public class Alarm {
    public String title ;
    public int hr;
    public int mins;

    Alarm(String title, int hr, int mins){
        this.hr = hr;
        this.mins = mins;


        this.title = title;

    }
    public void displayAlarm()
    {
        System.out.println("Title: "+ title);
        System.out.println("Time: "+ hr+":"+mins);

    }
}
