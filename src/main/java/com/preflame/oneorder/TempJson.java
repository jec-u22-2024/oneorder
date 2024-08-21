package com.preflame.oneorder;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 実装されていないときに返すだけのクラス
 */
public class TempJson extends JSONObject {
    private HttpStatus stat = HttpStatus.NOT_IMPLEMENTED;
    {
        this.put("log", "This Service is not implemented. sorry");
    }

    public ResponseEntity<Object> getJson() {
        return new ResponseEntity<Object>(this.toMap(), this.stat);
    }

    public void setHttpStatus(HttpStatus stat) {
        this.stat = stat;
    }
}
