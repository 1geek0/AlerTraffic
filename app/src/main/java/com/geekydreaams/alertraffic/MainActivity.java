package com.geekydreaams.alertraffic;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;


public class MainActivity extends AppCompatActivity {
    
    @Bind(R.id.toolbar_mainActivity)
    Toolbar toolbar_mainActivity;
    @Bind(R.id.traffic_view)
    Data_View data_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final WebSocketConnection socketConnection = new WebSocketConnection();
        //Init
        setSupportActionBar(toolbar_mainActivity);

        //WebSocket
        String server = "ws://192.168.1.12:8888/trafficsock";

        try{
            Log.i("socket", "Attempting Connection");
            socketConnection.connect(server, new WebSocketHandler(){
                @Override
                public void onOpen() {
                    super.onOpen();
                    Log.i("socket", "Success");
                    socketConnection.sendTextMessage("Hello");
                }

                @Override
                public void onClose(int code, String reason) {
                    super.onClose(code, reason);
                }

                @Override
                public void onTextMessage(String payload) {
                    super.onTextMessage(payload);
                    try {
                        JSONObject message_json = new JSONObject(payload);
                        String traffic_location = message_json.getString("traffic");
                        String data_view_string = data_view.getmData()+"";
                        if (data_view_string.equals("null")) {
                            data_view_string = traffic_location;
                        } else {
                            if (data_view_string.contains(traffic_location)) {
                            } else {
                                data_view_string += "\n" + traffic_location;
                            }
                        }
                        data_view.setmData(data_view_string);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (WebSocketException e){
            Log.e("socket", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
