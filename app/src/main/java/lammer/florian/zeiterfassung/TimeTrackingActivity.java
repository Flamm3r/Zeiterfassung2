package lammer.florian.zeiterfassung;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import lammer.florian.zeiterfassung.db.DbHelper;

public class TimeTrackingActivity extends AppCompatActivity {

    private EditText _startDateTime;
    private EditText _endDateTime;
    private Button _startCommand;
    private Button _endCommand;

    private DateFormat _dateTimeFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_tracking);

        _startDateTime = (EditText) findViewById(R.id.StartDateTime);
        _endDateTime = (EditText) findViewById(R.id.EndDateTime);
        _startCommand = (Button) findViewById(R.id.StartCommand);
        _endCommand = (Button) findViewById(R.id.EndCommand);

        //Error 4 debugging
        /*int zero = 0;
        double error = 100/zero;*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        _startCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentTime = Calendar.getInstance();

                DbHelper dbHelper = new DbHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                DateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd'T 'HH:mm", Locale.GERMANY);

                ContentValues values = new ContentValues();
                values.put("start_time", dbFormat.format(currentTime.getTime()));
                db.insert("time_data", null, values);
                db.close();
                dbHelper.close();

                _startDateTime.setText(_dateTimeFormatter.format(currentTime.getTime()));

                /*DEBUG
                Logging
                Log.d("TimeTrackingActivity", "onClick für Start-Button aufgerufen");
                Toast
                Toast.makeText(TimeTrackingActivity.this, "onClick für Start-Button aufgerufen", Toast.LENGTH_SHORT).show();
                */
            }
        });

        _endCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                _endDateTime.setText(_dateTimeFormatter.format(currentTime.getTime()).toString());
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        _startCommand.setOnClickListener(null); //Deregistrieren der Methode
        _endCommand.setOnClickListener(null);
    }
}
