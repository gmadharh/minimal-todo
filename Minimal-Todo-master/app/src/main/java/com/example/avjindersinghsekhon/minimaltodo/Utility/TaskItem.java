package com.example.avjindersinghsekhon.minimaltodo.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

public abstract class TaskItem implements Serializable {

    public abstract void setTitle(String title);

    public abstract JSONObject toJSON() throws JSONException;
    public abstract void jsonToItem(JSONObject jsonObject) throws JSONException;
    public abstract UUID getIdentifier();

    //public TaskItem(JSONObject jsonObject) throws JSONException{}

}
