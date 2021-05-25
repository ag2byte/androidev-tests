package com.example.l4add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class SetAlarm extends AppCompatActivity {
    TimePicker alarm_time;
    ImageButton donebtn;
    ImageButton cancelbtn;
    EditText title_alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        alarm_time = findViewById(R.id.pick_time);
        donebtn = findViewById(R.id.donebtn);
        cancelbtn = findViewById(R.id.cancelbtn);
        title_alarm  = findViewById(R.id.title_alarm);
        System.out.println("Set alarm Page");

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_alarm.getText().toString();
                if(title.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show();

                }
                else {
                    System.out.println(title);
                    Alarm a = new Alarm (title,alarm_time.getHour(),alarm_time.getHour()  );
                    System.out.println(a.hr+ ":" + a.mins );
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY,a.hr );
                    c.set(Calendar.MINUTE,a.mins);
                    c.set(Calendar.SECOND, 0 );

                    AlarmManager alarmManager = (AlarmManager)
                            getSystemService(ALARM_SERVICE);

                    Intent intent = new Intent(SetAlarm.this,
                            AlertReceiver.class);
                    PendingIntent pintent = PendingIntent.getBroadcast( SetAlarm.this, 0,
                            intent, 0 );

                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pintent);
                    String setmessge = "Alarm set for "+ a.hr +" :" + a.mins;

                    Toast.makeText(getApplicationContext() ,setmessge,Toast.LENGTH_SHORT).show();

                    if (c.before(Calendar.getInstance())) {
                        c.add(Calendar.DATE, 1);
                    }
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pintent);

                }
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationClass ncx = new NotificationClass(getApplicationContext());
                ncx.sendNotification("Wake up","Or you lose");

            }
        });



    }

    private void createAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


    }
}