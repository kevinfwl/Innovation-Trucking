package com.example.keypad_dh5;

import org.json.JSONObject;

public class JSONWrapper {
    private JSONObject value;
    public void setValue(JSONObject val) {
        value = val;
    }
    public JSONObject getValue(){
        return value;
    }
}
