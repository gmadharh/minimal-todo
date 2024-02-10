/**
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Miikka Andersson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.example.avjindersinghsekhon.minimaltodo;

import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * JUnit tests to verify functionality of ToDoItem class.
 */
public class TestTodoItem extends TestCase {
    private final Date CURRENT_DATE = new Date();
    private final String TEXT_BODY = "This is some text";
    private final String TEXT_LINK = "www.google.com";
    private final boolean PRIORITY_OFF = false;
    private final boolean PRIORITY_ON = true;
    private final boolean REMINDER_OFF = false;
    private final boolean REMINDER_ON = true;

     /**
      * Check we can construct a ToDoItem object using the three parameter constructor
      */
    public void testThreeParameterConstructor() {
        ToDoItem toDoItem = getToDoItem(REMINDER_OFF,PRIORITY_OFF);
        assertEquals(TEXT_BODY, toDoItem.getToDoText());
        assertEquals(REMINDER_OFF, toDoItem.hasReminder());
        assertEquals(CURRENT_DATE, toDoItem.getToDoDate());
        // assert if the priority is off, when we set it to off in the constructor
        assertEquals(PRIORITY_OFF, toDoItem.isPriority());
    }

     /**
      * Ensure we can marshall ToDoItem objects to Json
      * Added an assert here for testing if the link is written to the JSON
      */
    public void testObjectMarshallingToJson() {
        ToDoItem toDoItem = getToDoItem(REMINDER_ON,PRIORITY_ON);

        try {
            JSONObject json = toDoItem.toJSON();
            assertEquals(TEXT_BODY, json.getString("todotext"));
            assertEquals(REMINDER_ON, json.getBoolean("todoreminder"));
            // testing if the link was written to the json
            assertEquals(TEXT_LINK,json.getString("todolink"));
            // testing if the priority was written to the json, when its set to on
            assertEquals(PRIORITY_ON,json.getBoolean("todopriority"));
            assertEquals(String.valueOf(CURRENT_DATE.getTime()), json.getString("tododate"));
        } catch (JSONException e) {
            fail("Exception thrown during test execution: " + e.getMessage());
        }
    }

    /**
    * Ensure we can create ToDoItem objects from Json data by using the json constructor
     * Added an assert here to test if the link can be retrieved from the JSON into a ToDoItem object
    */
    public void testObjectUnmarshallingFromJson() {
        ToDoItem originalItem = getToDoItem(REMINDER_OFF,PRIORITY_OFF);

        try {

            JSONObject json = originalItem.toJSON();
            ToDoItem itemFromJson = new ToDoItem();
            itemFromJson.jsonToItem(json);

            assertEquals(originalItem.getToDoText(), itemFromJson.getToDoText());
            // testing if the link retrieved matches the one in the object
            assertEquals(originalItem.getmLink(),itemFromJson.getmLink());
            // testing if the priority boolean retrieved matches the one in the object, which is off
            assertEquals(originalItem.isPriority(),itemFromJson.isPriority());
            assertEquals(originalItem.getToDoDate(), itemFromJson.getToDoDate());
            assertEquals(originalItem.hasReminder(), itemFromJson.hasReminder());
            assertEquals(originalItem.getIdentifier(), itemFromJson.getIdentifier());

        } catch (JSONException e) {
            fail("Exception thrown during test execution: " + e.getMessage());
        }

        if(originalItem.getLinkDeleted() == true) {
            assertEquals(originalItem.getmLink(), "");
        }
    }

    private ToDoItem getToDoItem(boolean hasReminder,boolean hasPriority) {
        return new ToDoItem(TEXT_BODY,TEXT_BODY,TEXT_LINK,hasReminder, CURRENT_DATE,hasPriority);
    }
}
