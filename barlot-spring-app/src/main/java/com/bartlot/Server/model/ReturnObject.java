package com.bartlot.Server.model;

public class ReturnObject {

    public String status;
    public Object object;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }

}
