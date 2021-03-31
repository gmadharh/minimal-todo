package com.example.avjindersinghsekhon.minimaltodo.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class ToDoItem extends TaskItem {
    private String mToDoText;
    private boolean mHasReminder;
    //add description
    private String mToDoDescription;

    private String catIdent;
    // add a link
    private String mLink;
    // boolean for priority
    private boolean mPriority;
    // boolean for whether or not the link was deleted
    private Boolean mLinkDeleted;
    //    private Date mLastEdited;
    private int mTodoColor;
    private Date mToDoDate;
    private UUID mTodoIdentifier;
    private CategoryItem categoryBelongs;
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
    private static final String TODOCATEGORY = "todocategory";

    private static final String ITEMTYPE = "itemtype";
    private final String TASKTYPE = "task";


    public ToDoItem(String todoBody,String tododescription,String todoLink,boolean hasReminder, Date toDoDate,boolean hasPriority) {
        mToDoText = todoBody;
        mHasReminder = hasReminder;
        mToDoDate = toDoDate;
        mLink = todoLink;
        categoryBelongs = new CategoryItem();
        catIdent = "None";
        mPriority = hasPriority;
        mToDoDescription = tododescription;
        mTodoColor = 1677725;
        mTodoIdentifier = UUID.randomUUID();
    }

    // setter for title
    @Override
    public void setTitle(String mToDoText) {
        this.mToDoText = mToDoText;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TODOTEXT, mToDoText);
        jsonObject.put(TODOREMINDER, mHasReminder);
        jsonObject.put(TODODESCRIPTION, mToDoDescription);
        jsonObject.put(TODOLINK,mLink);
        jsonObject.put(TODOPRIORITY,mPriority);
        jsonObject.put(TODOCATEGORY,catIdent);
//        jsonObject.put(TODOLASTEDITED, mLastEdited.getTime());
        if (mToDoDate != null) {
            jsonObject.put(TODODATE, mToDoDate.getTime());
        }
        jsonObject.put(TODOCOLOR, mTodoColor);
        jsonObject.put(TODOIDENTIFIER, mTodoIdentifier.toString());
        jsonObject.put(ITEMTYPE,TASKTYPE);

        return jsonObject;
    }

    @Override
    public void jsonToItem(JSONObject jsonObject) throws JSONException {

        mToDoText = jsonObject.getString(TODOTEXT);
        mToDoDescription = jsonObject.getString(TODODESCRIPTION);
        mHasReminder = jsonObject.getBoolean(TODOREMINDER);
        mTodoColor = jsonObject.getInt(TODOCOLOR);
        mLink = jsonObject.getString(TODOLINK);
        mPriority = jsonObject.getBoolean(TODOPRIORITY);
        catIdent = jsonObject.getString(TODOCATEGORY);


        mTodoIdentifier = UUID.fromString(jsonObject.getString(TODOIDENTIFIER));

//        if(jsonObject.has(TODOLASTEDITED)){
//            mLastEdited = new Date(jsonObject.getLong(TODOLASTEDITED));
//        }
        if (jsonObject.has(TODODATE)) {
            mToDoDate = new Date(jsonObject.getLong(TODODATE));
        }

    }

    @Override
    public String toString() {
        return this.mToDoText;
    }


    // empty constructor which makes a default note with placeholder info
    public ToDoItem() {
        this("Clean my room","Sweep and Mop my Room","www.google.com", true, new Date(),false);
    }

    /**
     * Gets the Category string for which this task belongs to
     * @return Category string that the task is in, none or empty if none
     */
    public String getCategoryBelongs(){
       return this.catIdent;
    }

    /**
     * Sets the Category string for which this task belongs to
     * @param category Category to set this task to belong to
     */
    public void setCategoryBelongs(String category){
        this.catIdent = category;
    }

    // getter for the description
    public String getmToDoDescription() { return mToDoDescription;}

    // setter for the description
    public void setmToDoDescription(String mToDoDescription){this.mToDoDescription = mToDoDescription;}

    // getter for the title
    public String getToDoText() {
        return mToDoText;
    }

    // getter for the link
    public String getmLink(){return mLink;}

    // setter for the link
    public void setmLink(String todoLink){this.mLink = todoLink;}

    //getter for delete link button status
    public boolean getLinkDeleted(){return mLinkDeleted;}

    //getter for delete link button status
    public void setLinkDeleted(boolean status){this.mLinkDeleted = status;}

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

