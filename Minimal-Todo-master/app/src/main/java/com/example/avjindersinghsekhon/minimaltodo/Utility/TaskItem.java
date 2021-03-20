package com.example.avjindersinghsekhon.minimaltodo.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 *  An abstract class from which both CategoryItem and ToDoItem extend
 *  This was made because there was some functionality that was similar between the classes
 *  Also, it makes it easier to use polymorphism and to work with the code
 *  since it uses ArrayLists to load and write tasks
 *
 *  JSONObjects and jsons are used for the hardware portion
 *  Making an abstract class simplified the logic and avoided repeating code
  */
public abstract class TaskItem implements Serializable {

    /**
     * Used for setting the title of the TaskItem
     * @param title the title
     */
    public abstract void setTitle(String title);

    /**
     *  Converts the TaskItems to a JSONObject
     * @return a JSONObject
     * @throws JSONException
     */
    public abstract JSONObject toJSON() throws JSONException;

    /**
     * Takes in a JSONObject and extracts the information required to make a TaskItem
     * @param jsonObject
     * @throws JSONException
     */
    public abstract void jsonToItem(JSONObject jsonObject) throws JSONException;

    /**
     * Gets the UUID identifier from the Serializable interface
     * @return UUID
     */
    public abstract UUID getIdentifier();

}
