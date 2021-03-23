package com.example.avjindersinghsekhon.minimaltodo;

import com.example.avjindersinghsekhon.minimaltodo.Utility.CategoryItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;
import com.example.avjindersinghsekhon.minimaltodo.Utility.TodoNotificationService;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

public class TestCategoryItem extends TestCase {

    private final String CAT_TITLE = "This is some title";

    /**
     * Check that a new category item with the default constructor has no title
     */
    public void testDefaultConstructor(){
        CategoryItem categoryItem = new CategoryItem();
        assertEquals("",categoryItem.getTitle());
    }

    /**
     * Check that a category created with a title saves that title
     */
    public void testParameterConstructor(){
        CategoryItem categoryItem = new CategoryItem(CAT_TITLE);
        assertEquals(CAT_TITLE,categoryItem.getTitle());
    }

    /**
     * Ensure that we can convert CategoryItem objects to JSON
     */
    public void testObjectMarshallingToJson() {
        CategoryItem categoryItem = new CategoryItem(CAT_TITLE);

        try {
            JSONObject json = categoryItem.toJSON();
            assertEquals(CAT_TITLE, json.getString("cattitle"));
        } catch (JSONException e) {
            fail("Exception thrown during test execution: " + e.getMessage());
        }
    }

    /**
     * Ensure that we can create CategoryItem objects from JSON data
     */
    public void testObjectUnmarshallingFromJson() {
        CategoryItem categoryItem = new CategoryItem();

        try {

            JSONObject json = categoryItem.toJSON();
            CategoryItem itemFromJson = new CategoryItem();
            itemFromJson.jsonToItem(json);

            assertEquals(categoryItem.getTitle(), itemFromJson.getTitle());

        } catch (JSONException e) {
            fail("Exception thrown during test execution: " + e.getMessage());
        }

    }

    /**
     * Check if setting the category to which a task belongs works
     */
    public void testAddingTasktoCategory(){

        ToDoItem item = new ToDoItem();
        CategoryItem categoryItem = new CategoryItem(CAT_TITLE);

        item.setCategoryBelongs(CAT_TITLE);

        assertEquals(item.getCategoryBelongs(),categoryItem.getTitle());

    }

    /**
     * Ensure that when converting a ToDoItem, that belongs to a category, to a JSON and then BACK to a ToDoItem, that it retains the category it's associated with
     */
    public void testAddingTasktoCategoryJSON(){

        ToDoItem item = new ToDoItem();
        CategoryItem categoryItem = new CategoryItem(CAT_TITLE);

        item.setCategoryBelongs(CAT_TITLE);

        try {

            JSONObject json = item.toJSON();
            ToDoItem itemFromJson = new ToDoItem();
            itemFromJson.jsonToItem(json);

            assertEquals(categoryItem.getTitle(), itemFromJson.getCategoryBelongs());

        } catch (JSONException e) {
            fail("Exception thrown during test execution: " + e.getMessage());
        }

    }

    /**
     * Ensure that when converting a ToDoItem to a JSON that it holds the Category with which it's associated with
     */
    public void testAddingTasktoCategoryFromJSON() {

        ToDoItem item = new ToDoItem();
        CategoryItem categoryItem = new CategoryItem(CAT_TITLE);

        item.setCategoryBelongs(CAT_TITLE);

        try {
            JSONObject json = item.toJSON();
            assertEquals(categoryItem.getTitle(), json.getString("todocategory"));
        } catch (JSONException e) {
            fail("Exception thrown during test execution: " + e.getMessage());
        }
    }

}
