package com.preflame.oneorder.model;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class REModel {
    private HttpStatus stat = HttpStatus.OK;
    public ResponseEntity<Object> getObjectToModel(JSONObject json) {
        return new ResponseEntity<Object>(json.toMap(), getStatus());
    }
    
    public ResponseEntity<Object> getArrayToModel(JSONArray json) {
        return new ResponseEntity<Object>(json.toList(), getStatus());
    }

    public void setStatus(HttpStatus stat) {
        this.stat = stat;
    }

    public HttpStatus getStatus() {
        return this.stat;
    }
}
