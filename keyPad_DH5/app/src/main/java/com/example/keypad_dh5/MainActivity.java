package com.example.keypad_dh5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> pin = new ArrayList<Integer>();
    Button n0;
    Button n1;
    Button n2;
    Button n3;
    Button n4;
    Button n5;
    Button n6;
    Button n7;
    Button n8;
    Button n9;
    Button dele;
    Button clear;
    Integer indexPin = 0;
    EditText editText;
    String pinStr = "";
    IntWrapper pw = new IntWrapper();
    StringWrapper randomPin = new StringWrapper();
    JSONWrapper jsonWrap = new JSONWrapper();
    private RequestQueue queue;
    private final String userURL = "https://deltahacksv-204a8.firebaseapp.com/api/user/Joy";
    private final String verifyURL = "https://deltahacksv-204a8.firebaseapp.com/api/verifyPin/1";
    private final String authURL = "https://deltahacksv-204a8.firebaseapp.com/api/authPin/";
    private final String cargoURL = "https://deltahacksv-204a8.firebaseapp.com/api/cargo";
    String lol = "http://ptsv2.com/t/zom4j-1548588924/post";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting Password
        queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, userURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int password = response.getInt("password");
                            pw.setValue(password);
                        }
                        catch (Exception e) { pw.setValue(1999); }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Sorry, could not connect to network", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

        editText = (EditText) findViewById(R.id.editText);
        n1 = findViewById(R.id.n1);
        n1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexPin != 4) {
                    pin.add(1);
                    indexPin++;
                    pinStr = pinStr + "1";
                    editText.setText(editText.getText().insert(editText.getText().length(), "1"));
                }
            }
        });

        n2 = findViewById(R.id.n2);
        n2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(2);
                    indexPin++;
                    pinStr = pinStr + "2";
                    editText.setText(editText.getText().insert(editText.getText().length(), "2"));
                }
            }
        });

        n3 = findViewById(R.id.n3);
        n3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(3);
                    indexPin++;
                    pinStr = pinStr + "3";
                    editText.setText(editText.getText().insert(editText.getText().length(), "3"));
                }
            }
        });

        n4 = findViewById(R.id.n4);
        n4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(4);
                    indexPin++;
                    pinStr = pinStr + "4";
                    editText.setText(editText.getText().insert(editText.getText().length(), "4"));
                }
            }
        });

        n5 = findViewById(R.id.n5);
        n5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(5);
                    indexPin++;
                    pinStr = pinStr + "5";
                    editText.setText(editText.getText().insert(editText.getText().length(), "5"));
                }
            }
        });

        n6 = findViewById(R.id.n6);
        n6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(6);
                    indexPin++;
                    pinStr = pinStr + "6";
                    editText.setText(editText.getText().insert(editText.getText().length(), "6"));
                }
            }
        });

        n7 = findViewById(R.id.n7);
        n7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(7);
                    indexPin++;
                    pinStr = pinStr + "7";
                    editText.setText(editText.getText().insert(editText.getText().length(), "7"));
                }
            }
        });

        n8 = findViewById(R.id.n8);
        n8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(8);
                    indexPin++;
                    pinStr = pinStr + "8";
                    editText.setText(editText.getText().insert(editText.getText().length(), "8"));
                }
            }
        });

        n9 = findViewById(R.id.n9);
        n9.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(9);
                    indexPin++;
                    editText.setText(editText.getText().insert(editText.getText().length(), "9"));
                }
            }
        });

        n0 = findViewById(R.id.n0);
        n0.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (indexPin != 4) {
                    pin.add(0);
                    indexPin++;
                    editText.setText(editText.getText().insert(editText.getText().length(), "0"));
                }
            }
        });

        dele = findViewById(R.id.dele);
        dele.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (pin.size() != 0) {
                    pin.remove(indexPin - 1);
                    indexPin--;
                    editText.setText(editText.getText().delete(editText.getText().length() - 1,
                            editText.getText().length()));
                }
            }
        });

        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for (int i = 0; i < pin.size(); i++) {
                    pin.remove(i);
                }
                indexPin = 0;
                editText.setText("");
            }
        });

        editText.addTextChangedListener(new KeyInputWatcher());
    }

    public void getPin(final NetworkCallback callback){

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, authURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    randomPin.setValue(response.getString("password"));
                    callback.onSuccess();
                } catch (JSONException exception) {
                    Toast.makeText(MainActivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                callback.onFailure();
            }
        });
        queue.add(req);
    }

    public void getCargoJson(final NetworkCallback callback){
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, cargoURL + "/1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonWrap.setValue(response);
                callback.onSuccess();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                callback.onFailure();
            }
        });
        queue.add(req);
    }

    private class KeyInputWatcher implements TextWatcher {
         boolean correctPass = false;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(correctPass) {
                getPin(new NetworkCallback() {
                    @Override
                    public void onSuccess() {
                        String pinStr = randomPin.getValue();
                        if(editText.getText().toString().equals(pinStr)) {
                            //if()
                            //String auth = editText.getText().toString();
                            getCargoJson(new NetworkCallback() {
                                @Override
                                public void onSuccess() {
                                    JSONObject cargo = jsonWrap.getValue();
                                    try {
                                        cargo.put("alarm", false);
                                    } catch (JSONException e) {
                                        Toast.makeText(MainActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                    }


                                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, cargoURL, cargo, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                        }
                                    }, new Response.ErrorListener() {

                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    });
                                    queue.add(req);
                                }

                                @Override
                                public void onFailure() {

                                }
                            });

                        }
                        else if(editText.getText().toString().length() == pinStr.length())
                        {
                            Toast.makeText(MainActivity.this, "Sorry, wrong pin!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });

            }

            else if (editText.getText().length() == 4 && editText.getText().toString().equals(Integer.toString(pw.getValue()))) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("verify", true);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, verifyURL, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(MainActivity.this, "Successfully Authenticated", Toast.LENGTH_SHORT).show();
                            correctPass = true;
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Sorry, please try again!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(jsonObjectRequest);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Sorry, please try again!", Toast.LENGTH_SHORT).show();
                }
            } else if (editText.getText().length() == 4 && !editText.getText().toString().equals(Integer.toString(pw.getValue()))) {
                Toast.makeText(MainActivity.this, "Sorry, wrong password!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) { }
    }
}

interface NetworkCallback {
    void onSuccess();
    void onFailure();
}