package cz.martykan.forecastie.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cz.martykan.forecastie.R;

public class Main2Activity extends AppCompatActivity {

    TextView todayTemp, todayCity;
    TextView todayDesc;
    TextView todaywind;
    TextView todayPress;
    TextView todayHumid,tv3;
    Button save1,load,save2;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String City = "cityKey";
    public static final String Desc = "descKey";
    public static final String Temp = "tempKey";
    public static final String Pressure = "pressureKey";
    public static final String Humidity = "humidityKey";
    public static final String Wind = "windKey";

   /*public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String City = "cityKey";
    public static final String Temp = "tempKey";
    public static final String Pressure = "pressureKey";
    public static final String Humidity = "humidityKey";
    public static final String Wind = "windKey";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle b = getIntent().getExtras();
        final SQLiteDatabase db;
        todayCity = (TextView) findViewById(R.id.todayCity);

        todayTemp = (TextView) findViewById(R.id.todayTemp);
        todayDesc = (TextView) findViewById(R.id.todayDesc);
        todaywind = (TextView) findViewById(R.id.todaywind);
        todayPress = (TextView) findViewById(R.id.todayPress);
        todayHumid = (TextView) findViewById(R.id.todayHumid);
        tv3 = (TextView) findViewById(R.id.tv3);
        save1 = (Button) findViewById(R.id.save1);
        save2 = (Button) findViewById(R.id.save2);
        load = (Button) findViewById(R.id.load);

        todayCity.setText(b.getCharSequence("city1"));
        todayDesc.setText(b.getCharSequence("desc1"));
        todayTemp.setText(b.getCharSequence("temp1"));
        todayPress.setText(b.getCharSequence("pressure1"));
        todayHumid.setText(b.getCharSequence("humidity1"));
        todaywind.setText(b.getCharSequence("wind1"));

        final String c = todayCity.getText().toString();
        final String d = todayDesc.getText().toString();
        final String t  = todayTemp.getText().toString();
        final String h = todayHumid.getText().toString();
        final String p = todayPress.getText().toString();
        final String w  = todaywind.getText().toString();

        final SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_APPEND);
        //db = openOrCreateDatabase("WeatherappDB", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS weatherapp(city VARCHAR,desc VARCHAR,temperature VARCHAR,wind VARCHAR,pressure VARCHAR,humidity VARCHAR);");
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS weather_app1(city VARCHAR,desc VARCHAR,temp VARCHAR,wind VARCHAR,pressure VARCHAR,humid VARCHAR);");
        save1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"saving", Toast.LENGTH_SHORT).show();
                //db.execSQL("INSERT INTO weather VALUES('" + todayCity.getText() + "','" + todayDesc.getText() + "','" + todayTemp.getText() + "','" + todaywind.getText() + "','" + todayPress.getText() + "','" + todayHumid.getText() + "');");
                db.execSQL("INSERT INTO weather_app1 VALUES('" + todayCity.getText() + "','" + todayDesc.getText() + "','" + todayTemp.getText() + "','" + todaywind.getText() + "','" + todayPress.getText() + "','" + todayHumid.getText() + "');");
                //db.execSQL("insert into weather values('aaa','30cel','bbb','hum','gh','gggg')");
                showMessage("Success", "Record added");

                /*SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(City, c);
                editor.putString(Desc, d);
                editor.putString(Temp, t);
                editor.putString(Pressure, p);
                editor.putString(Humidity, h);
                editor.putString(Wind, w);
                editor.commit();
                Toast.makeText(Main2Activity.this,"Thanks",Toast.LENGTH_LONG).show();*/


            }
        });
        /*load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c1 = sharedpreferences.getString(City, null);
                String d1 = sharedpreferences.getString(Desc, null);
                String t1 =sharedpreferences.getString(Temp, null);
                String p1= sharedpreferences.getString(Pressure, null);
                String h1 = sharedpreferences.getString(Humidity, null);
                String w1 = sharedpreferences.getString(Wind, null);

                String result1= "City - "+c1+"\n"+"Desc - "+d1+"\n"+"Temp - "+t1+"\n"+"Pressure - "+p1+"\n"+"Humid - "+h1+"\n"+"Wind - "+w1;
                tv3.setText(result1);
            }
        });*/
        load.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cursor c=db.rawQuery("SELECT * FROM weather_app1", null);

                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("City: "+c.getString(0)+"\n");
                    buffer.append("Description: "+c.getString(1)+"\n");
                    buffer.append("Temperature: "+c.getString(2)+"\n");
                    buffer.append("wind: "+c.getString(3)+"\n");
                    buffer.append("Pressure: "+c.getString(4)+"\n");
                    buffer.append("Humidity: "+c.getString(5)+"\n");

                }
                showMessage("Weather Details", buffer.toString());




            }
        });
    }

    private void showMessage(String title, String message) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
