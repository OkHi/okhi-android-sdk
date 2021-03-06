package io.okheart.android.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.okheart.android.OkHi;
import io.okheart.android.R;
import io.okheart.android.callback.OkHiCallback;

public class TestActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 83;
    private EditText firstnameedt, lastnameedt, phoneedt;
    private Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        OkHi.initialize("r:b59a93ba7d80a95d89dff8e4c52e259a", true);

        firstnameedt = findViewById(R.id.firstname);
        lastnameedt = findViewById(R.id.lastname);
        phoneedt = findViewById(R.id.phone);
        submitbtn = findViewById(R.id.submit);

        //displayLog(""+OkHi.checkPermission());

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (OkHi.checkPermission()) {
                    String firstname = firstnameedt.getText().toString();
                    String lastname = lastnameedt.getText().toString();
                    String phone = phoneedt.getText().toString();

                    if ((firstname.length() > 0) && (lastname.length() > 0) && (phone.length() > 0)) {

                        OkHiCallback okHiCallback = new OkHiCallback() {
                            @Override
                            public void querycomplete(JSONObject result) {
                                displayLog(result.toString());

                            }
                        };
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("firstName", firstname);
                            jsonObject.put("lastName", lastname);
                            jsonObject.put("phone", phone);
                            OkHi.displayClient(okHiCallback, jsonObject);
                        } catch (JSONException e) {
                            displayLog("json exception error " + e.toString());
                        }

                    } else {
                        Toast.makeText(TestActivity.this, "Error! Missing firstname or lastname or phonenumber", Toast.LENGTH_LONG).show();
                    }
                } else {
                    OkHi.requestPermission(TestActivity.this, MY_PERMISSIONS_ACCESS_FINE_LOCATION);
                }

            }
        });
    }



    /*

    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 83;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(io.okheart.android.R.layout.activity_test);

        io.okheart.android.OkHi.initialize("r:b59a93ba7d80a95d89dff8e4c52e259a", true);

        String firstname = "Ramogi";
        String lastname = "Ochola";
        String phonenumber = "+254713567907";

        io.okheart.android.callback.OkHiCallback okHiCallback = new io.okheart.android.callback.OkHiCallback() {
            @Override
            public void querycomplete(JSONObject result) {
                displayLog("result " + result);

                String message = result.optString("message");
                displayLog(message);

                if (message.equalsIgnoreCase("fatal_exit")) {
                    JSONObject payload = result.optJSONObject("payload");
                    displayLog("payload " + payload.toString());
                    int errorCode = payload.optInt("errorCode", 0);
                    displayLog("" + errorCode);
                    if (errorCode == -1) {
                        io.okheart.android.OkHi.requestPermission(TestActivity.this, MY_PERMISSIONS_ACCESS_FINE_LOCATION);
                    }

                }

            }
        };

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstName", firstname);
            jsonObject.put("lastName", lastname);
            jsonObject.put("phone", phonenumber);
            io.okheart.android.OkHi.displayClient(okHiCallback, jsonObject);
        } catch (JSONException e) {
            displayLog("json exception error " + e.toString());
        }
    }

     */

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                displayLog("onrequest permission");
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    displayLog("accepted location permissions");
                    Toast.makeText(TestActivity.this, "Press submit", Toast.LENGTH_LONG).show();


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    displayLog("denied location permission");

                }
                return;
            }
        }
    }


    private void displayLog(String logs) {
        Log.i("TestActivity", logs);
    }

}
