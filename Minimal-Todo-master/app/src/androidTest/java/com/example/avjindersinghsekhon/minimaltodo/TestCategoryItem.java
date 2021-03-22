package com.example.avjindersinghsekhon.minimaltodo;

import com.example.avjindersinghsekhon.minimaltodo.Utility.CategoryItem;

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
}
