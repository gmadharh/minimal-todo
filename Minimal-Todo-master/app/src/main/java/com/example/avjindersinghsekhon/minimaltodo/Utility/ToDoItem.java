package com.example.avjindersinghsekhon.minimaltodo.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ToDoItem implements Serializable {
    private String mToDoText;
    private boolean mHasReminder;
    //add description
    private String mToDoDescription;
    // add a link
    private String mLink;
    // boolean for priority
    private boolean mPriority;
    //    private Date mLastEdited;
    private int mTodoColor;
    private Date mToDoDate;
    private UUID mTodoIdentifier;
    //add description
    private static final String TODODESCRIPTION = "tododescription";
    private static final String TODOLINK = "todolink";
    private static final String TODOTEXT = "todotext";
    private static final String TODOREMINDER = "todoreminder";
    //    private static final String TODOLASTEDITED = "todolastedited";
    private static final String TODOCOLOR = "todocolor";
    private static final String TODODATE = "tododate";
    private static final String TODOIDENTIFIER = "todoidentifier";
    private static final String TODOPRIORITY = "todopriority";


    public ToDoItem(String todoBody,String tododescription,String todoLink,boolean hasReminder, Date toDoDate,boolean hasPriority) {
        mToDoText = todoBody;
        mHasReminder = hasReminder;
        mToDoDate = toDoDate;
        mLink = todoLink;
        mPriority = hasPriority;
        mToDoDescription = tododescription;
        mTodoColor = 1677725;
        mTodoIdentifier = UUID.randomUUID();
    }

    // constructor which takes in a jsonObject
    // used for writing to file?
    public ToDoItem(JSONObject jsonObject) throws JSONException {
        mToDoText = jsonObject.getString(TODOTEXT);
        mToDoDescription = jsonObject.getString(TODODESCRIPTION);
        mHasReminder = jsonObject.getBoolean(TODOREMINDER);
        mTodoColor = jsonObject.getInt(TODOCOLOR);
        mLink = jsonObject.getString(TODOLINK);
        mPriority = jsonObject.getBoolean(TODOPRIORITY);

        mTodoIdentifier = UUID.fromString(jsonObject.getString(TODOIDENTIFIER));

//        if(jsonObject.has(TODOLASTEDITED)){
//            mLastEdited = new Date(jsonObject.getLong(TODOLASTEDITED));
//        }
        if (jsonObject.has(TODODATE)) {
            mToDoDate = new Date(jsonObject.getLong(TODODATE));
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TODOTEXT, mToDoText);
        jsonObject.put(TODOREMINDER, mHasReminder);
        jsonObject.put(TODODESCRIPTION, mToDoDescription);
        jsonObject.put(TODOLINK,mLink);
        jsonObject.put(TODOPRIORITY,mPriority);
//        jsonObject.put(TODOLASTEDITED, mLastEdited.getTime());
        if (mToDoDate != null) {
            jsonObject.put(TODODATE, mToDoDate.getTime());
        }
        jsonObject.put(TODOCOLOR, mTodoColor);
        jsonObject.put(TODOIDENTIFIER, mTodoIdentifier.toString());

        return jsonObject;
    }


    // empty constructor which makes a default note with placeholder info
    public ToDoItem() {
        this("Clean my room","Sweep and Mop my Room","www.google.com", true, new Date(),false);
    }

    // getter for the description
    public String getmToDoDescription() { return mToDoDescription;}

    // setter for the description
    public void setmToDoDescription(String mToDoDescription){this.mToDoDescription = mToDoDescription;}

    // getter for the title
    public String getToDoText() {
        return mToDoText;
    }

    // setter for the title
    public void setToDoText(String mToDoText) {
        this.mToDoText = mToDoText;
    }

    // getter for the link
    public String getmLink(){return mLink;}

    // setter for the link
    public void setmLink(String todoLink){this.mLink = todoLink;}

    // getter for if the task has a reminder
    public boolean hasReminder() {
        return mHasReminder;
    }

    // setter for the task giving it a reminder
    public void setHasReminder(boolean mHasReminder) {
        this.mHasReminder = mHasReminder;
    }

    // getter for task high priority
    public boolean isPriority() {
        return mPriority;
    }

    // setter for setting high priority
    public void setPriority(boolean mPriority) {
        this.mPriority = mPriority;
    }

    // getter for the date
    public Date getToDoDate() {
        return mToDoDate;
    }

    // getter for the color
    public int getTodoColor() {
        return mTodoColor;
    }

    // setter for the color
    public void setTodoColor(int mTodoColor) {
        this.mTodoColor = mTodoColor;
    }

    // setter for the date
    public void setToDoDate(Date mToDoDate) {
        this.mToDoDate = mToDoDate;
    }

    // getter for identifier
    public UUID getIdentifier() {
        return mTodoIdentifier;
    }
}

