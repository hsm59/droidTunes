package com.hussainmukadam.droidtunes;

import android.provider.BaseColumns;

import com.hussainmukadam.droidtunes.data.FavoritesContract;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by hussain on 14/7/17.
 */

public class ContractClassUnitTest {

    @Test
    public void inner_class_exists() throws Exception{
        Class[] innerClasses = FavoritesContract.class.getDeclaredClasses();
        assertEquals("There should be 1 Inner Class inside the contract Class", 1, innerClasses.length);
    }

    @Test
    public void inner_class_type_correct() throws Exception{
        Class[] innerClasses = FavoritesContract.class.getDeclaredClasses();
        assertEquals("Cannot find inner class to complete the unit test", 1, innerClasses.length);
        Class entryClass = innerClasses[0];
        assertTrue("Inner Class should implement BaseColumns interface", BaseColumns.class.isAssignableFrom(entryClass));
        assertTrue("Inner Class should be final", Modifier.isFinal(entryClass.getModifiers()));
        assertTrue("Inner Class should be static", Modifier.isStatic(entryClass.getModifiers()));
    }

    @Test
    public void inner_class_members_correct() throws Exception{
        Class[] innerClasses = FavoritesContract.class.getDeclaredClasses();
        assertEquals("Cannot find inner class to complete the unit test", 1, innerClasses.length);
        Class entryClass = innerClasses[0];
        Field[] allFields = entryClass.getDeclaredFields();
        assertEquals("There should be exactly 13 String members in the inner class", 13, allFields.length);

        for(Field field: allFields) {
            assertTrue("All Members in the contract class should be String", field.getType()==String.class);
            assertTrue("All Members in the contract class should be Final", Modifier.isFinal(field.getModifiers()));
            assertTrue("All Members in the contract class should be Static", Modifier.isStatic(field.getModifiers()));
        }
    }
}
