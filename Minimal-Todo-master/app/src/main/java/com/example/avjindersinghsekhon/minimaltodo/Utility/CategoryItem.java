package com.example.avjindersinghsekhon.minimaltodo.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class CategoryItem extends TaskItem {

    // declare variables
    private String title;
    private UUID categoryIdentifier;

    // final strings used for writing to the JSON
    private static final String CATTITLE = "cattitle";
    private static final String CATIDENTIFIER = "catidentifier";

    // also used for the JSON
    private static final String ITEMTYPE = "itemtype";
    private final String TASKTYPE = "category";

    /**
     * Creates a Category object with an empty title
     */
    public CategoryItem(){
        title = "";
        categoryIdentifier = UUID.randomUUID();
    }

    /**
     * Creates a Category object with the specified title
     * @param title - title of the Category
     */
    public CategoryItem(String title){
        this.title = title;
        categoryIdentifier = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return this.title;
    }


    /**
     * Returns the title of the Category
     * @return - title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Used for setting the title of the Category
     * @param title - title of Category
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Converts the Category object to JSONObject, used for writing to the file
     * @return JSONObject
     * @throws JSONException
     */
    @Override
    public JSONObject toJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CATTITLE,title);
        jsonObject.put(CATIDENTIFIER,categoryIdentifier.toString());
        jsonObject.put(ITEMTYPE,TASKTYPE);

        return jsonObject;
    }

    /**
     * Takes the fields in the JSONObject and converts them to a Category object
     * @param jsonObject the object to convert to get the information from
     * @throws JSONException
     */
    @Override
    public void jsonToItem(JSONObject jsonObject) throws JSONException {

        title = jsonObject.getString(CATTITLE);
        categoryIdentifier = UUID.fromString(jsonObject.getString(CATIDENTIFIER));

    }

    /**
     * UUID Identifier
     * @return the unique UUID Identifier
     */
    @Override
    public UUID getIdentifier() {
        return categoryIdentifier;
    }
}
