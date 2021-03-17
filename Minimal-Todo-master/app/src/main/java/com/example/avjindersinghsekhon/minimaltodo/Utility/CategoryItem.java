package com.example.avjindersinghsekhon.minimaltodo.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class CategoryItem extends TaskItem {

    private String title;
    private UUID categoryIdentifier;
    private ArrayList<ToDoItem> tasks;

    private static final String CATTITLE = "cattitle";
    private static final String CATIDENTIFIER = "catidentifier";

    private static final String ITEMTYPE = "itemtype";
    private final String TASKTYPE = "category";

    public CategoryItem(){
        title = "";
        tasks = new ArrayList<>();
        categoryIdentifier = UUID.randomUUID();
    }

    public CategoryItem(String title){
        this.title = title;
        tasks = new ArrayList<>();
        categoryIdentifier = UUID.randomUUID();
    }

    public CategoryItem(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString(CATTITLE);

    }

    public String getTitle(){
        return this.title;
    }

    public ArrayList<ToDoItem> getTasks(){
        return this.tasks;
    }

    public void addTask(ToDoItem item){

        tasks.add(item);
    }

    // .remove(item) returns a boolean
    // "Removes the first occurrence of the specified element from this list, if it is present."
    public boolean removeTask(ToDoItem item){
        return tasks.remove(item);
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public JSONObject toJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CATTITLE,title);
        jsonObject.put(CATIDENTIFIER,categoryIdentifier.toString());
        jsonObject.put(ITEMTYPE,TASKTYPE);

        return jsonObject;
    }

    @Override
    public void jsonToItem(JSONObject jsonObject) throws JSONException {

        title = jsonObject.getString(CATTITLE);
        categoryIdentifier = UUID.fromString(jsonObject.getString(CATIDENTIFIER));

    }

    @Override
    public UUID getIdentifier() {
        return categoryIdentifier;
    }
}
