package org.weslleycabral.cadastro_pessoas.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


public class SuccessResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private Object data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataUpdate;

    public SuccessResponse() {}


    public SuccessResponse(String message, Object data, LocalDateTime dataUpdate) {
        this.message = message;
        this.data = data;
        this.dataUpdate = dataUpdate;
    }

    public SuccessResponse(String message, LocalDateTime dataUpdate) {
        this.message = message;
        this.dataUpdate = dataUpdate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(LocalDateTime dataUpdate) {
        this.dataUpdate = dataUpdate;
    }
}
