package com.ensa.pizzahub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ensa.pizzahub.model.Order;
import com.ensa.pizzahub.model.OrderItem;
import com.ensa.pizzahub.model.Pizza;
import com.ensa.pizzahub.model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 3;
    
    // Database Name
    private static final String DATABASE_NAME = "Userdata.db";

    // User Section
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    // drop user table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    // Order Section
    private static final String TABLE_ORDER = "user_order";
    // Order Table Columns names
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_ORDER_STATE = "order_state";
    private static final String COLUMN_ORDER_USER_ID = "order_user_id";
    // create table sql query
    private String CREATE_ORDER_TABLE = "create table " + TABLE_ORDER + "(" + COLUMN_ORDER_ID + " integer primary key autoincrement," +
            COLUMN_ORDER_STATE + " integer," + COLUMN_ORDER_USER_ID + " integer," +
            " FOREIGN KEY("+COLUMN_ORDER_USER_ID+") REFERENCES "+TABLE_USER+"("+COLUMN_USER_ID+"))";
    // drop user table sql query
    private String DROP_ORDER_TABLE = "DROP TABLE IF EXISTS " + TABLE_ORDER;


    // Order items section
    private static final String TABLE_ORDER_ITEM = "order_item";
    // Order Table Columns names
    private static final String COLUMN_ORDER_ITEM_ID = "order_item_id";
    private static final String COLUMN_ORDER_ITEM_QUANTITY= "order_item_quantity";
    private static final String COLUMN_ORDER_ITEM_ORDER_ID= "order_item_order_id";
    private static final String COLUMN_ORDER_ITEM_PRICE = "order_item_price";
    // create table sql query
    private String CREATE_ORDER_ITEM_TABLE = "create table " + TABLE_ORDER_ITEM + "(" + COLUMN_ORDER_ITEM_ID + " integer primary key autoincrement," +
            COLUMN_ORDER_ITEM_QUANTITY + " integer,"+COLUMN_ORDER_ITEM_PRICE+ " NUMBER," + COLUMN_ORDER_ITEM_ORDER_ID + " integer," +
            " FOREIGN KEY("+COLUMN_ORDER_ITEM_ORDER_ID+") REFERENCES "+TABLE_ORDER+"("+COLUMN_ORDER_ID+"))";
    // drop user table sql query
    private String DROP_ORDER_ITEM_TABLE = "DROP TABLE IF EXISTS " + TABLE_ORDER_ITEM;

    // Pizza Section
    private static final String TABLE_PIZZA = "pizza";
    // Pizza Table Columns names
    private static final String COLUMN_PIZZA_ID = "pizza_id";
    private static final String COLUMN_PIZZA_NAME = "pizza_name";
    private static final String COLUMN_PIZZA_DESCRIPTION = "pizza_description";
    private static final String COLUMN_PIZZA_PRICE_S = "pizza_price_s";
    private static final String COLUMN_PIZZA_PRICE_M = "pizza_price_m";
    private static final String COLUMN_PIZZA_PRICE_L = "pizza_price_l";
    private static final String COLUMN_PIZZA_DELIVERY_TIME = "pizza_delivery_time";
    //private static final String COLUMN_PIZZA_SIZE = "pizza_size";
    private static final String COLUMN_PIZZA_IMAGE = "pizza_image";


    // create table sql query
    private String CREATE_PIZZA_TABLE = "CREATE TABLE " + TABLE_PIZZA + "("
            + COLUMN_PIZZA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PIZZA_NAME + " TEXT UNIQUE,"
            + COLUMN_PIZZA_DESCRIPTION + " TEXT," + COLUMN_PIZZA_PRICE_S + " NUMBER,"+
            COLUMN_PIZZA_PRICE_M + " NUMBER,"+
            COLUMN_PIZZA_PRICE_L + " NUMBER,"+
            COLUMN_PIZZA_DELIVERY_TIME + " NUMBER,"+
            COLUMN_PIZZA_IMAGE+" TEXT)";
    // drop user table sql query
    private String DROP_PIZZA_TABLE = "DROP TABLE IF EXISTS " + TABLE_PIZZA;




    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        System.out.println(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        System.out.println(CREATE_ORDER_ITEM_TABLE);
        db.execSQL(CREATE_ORDER_ITEM_TABLE);
        System.out.println(CREATE_PIZZA_TABLE);
        db.execSQL(CREATE_PIZZA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop Tables if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_ORDER_TABLE);
        db.execSQL(DROP_ORDER_ITEM_TABLE);
        db.execSQL(DROP_PIZZA_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) throws Exception {
        if(checkUser(user.getEmail())){
            throw new Exception("Email already exist!");
        }
        else{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());
            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();
        }

    }
    //
    public void addPizza(Pizza pizza){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PIZZA_NAME,pizza.getName());
        values.put(COLUMN_PIZZA_DESCRIPTION,pizza.getDescription());
        values.put(COLUMN_PIZZA_IMAGE,pizza.getImagePath());
        values.put(COLUMN_PIZZA_PRICE_S,pizza.getPrice_s());
        values.put(COLUMN_PIZZA_PRICE_M,pizza.getPrice_m());
        values.put(COLUMN_PIZZA_PRICE_L,pizza.getPrice_l());
        values.put(COLUMN_PIZZA_DELIVERY_TIME,pizza.getDeliveryTime());
        db.insert(TABLE_PIZZA, null, values);
        db.close();
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }
    public List<Pizza> getAllPizza(){
        String[] columns = {
                COLUMN_PIZZA_ID,
                COLUMN_PIZZA_NAME,
                COLUMN_PIZZA_DESCRIPTION,
                COLUMN_PIZZA_PRICE_S,
                COLUMN_PIZZA_PRICE_M,
                COLUMN_PIZZA_PRICE_L,
                COLUMN_PIZZA_DELIVERY_TIME,
                COLUMN_PIZZA_IMAGE
        };
        String sortOrder =
                COLUMN_PIZZA_NAME + " ASC";
        List<Pizza> pizzaList = new ArrayList<Pizza>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PIZZA, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,
                sortOrder);
        if (cursor.moveToFirst()) {
            do {
                Pizza pizza = new Pizza();
                pizza.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PIZZA_ID))));
                pizza.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PIZZA_NAME)));
                pizza.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PIZZA_DESCRIPTION)));
                pizza.setPrice_s(cursor.getDouble(cursor.getColumnIndex(COLUMN_PIZZA_PRICE_S)));
                pizza.setPrice_m(cursor.getDouble(cursor.getColumnIndex(COLUMN_PIZZA_PRICE_M)));
                pizza.setPrice_l(cursor.getDouble(cursor.getColumnIndex(COLUMN_PIZZA_PRICE_L)));

                // Adding pizza record to list
                pizzaList.add(pizza);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pizzaList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void updatePizza(Pizza pizza){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PIZZA_NAME,pizza.getName());
        values.put(COLUMN_PIZZA_DESCRIPTION,pizza.getDescription());
        values.put(COLUMN_PIZZA_IMAGE,pizza.getImagePath());
        values.put(COLUMN_PIZZA_PRICE_S,pizza.getPrice_s());
        values.put(COLUMN_PIZZA_PRICE_M,pizza.getPrice_m());
        values.put(COLUMN_PIZZA_PRICE_L,pizza.getPrice_l());
        values.put(COLUMN_PIZZA_DELIVERY_TIME,pizza.getDeliveryTime());
        db.update(TABLE_PIZZA, values,COLUMN_PIZZA_ID+ " = ?",
                new String[]{String.valueOf(pizza.getId())});
        db.close();

    }
    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public  void deletePizza(Pizza pizza){
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_PIZZA, COLUMN_PIZZA_ID+ " = ?",
                new String[]{String.valueOf(pizza.getId())});
        db.close();
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is to create an user order
     *
     * @param order
     */
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_USER_ID, order.getUser().getId());
        // Inserting Row
        Long id = db.insert(TABLE_ORDER, null, values);
        for (OrderItem item: order.getItems()) {
            ContentValues itemValues = new ContentValues();
            itemValues.put(COLUMN_ORDER_ITEM_ORDER_ID, id);
            itemValues.put(COLUMN_ORDER_ITEM_QUANTITY, item.getQuantity());
            // Inserting Row
            db.insert(TABLE_ORDER_ITEM, null, itemValues);
        }
        db.close();
    }

    /**
     * This method is to update an user order
     *
     * @param order
     */
    public void deleteOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_ORDER, COLUMN_ORDER_ID + " = ?",
                new String[]{String.valueOf(order.getId())});
        db.close();
    }

    public void addOrderItem(OrderItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues itemValues = new ContentValues();
        itemValues.put(COLUMN_ORDER_ITEM_ORDER_ID, item.getOrder().getId());
        itemValues.put(COLUMN_ORDER_ITEM_QUANTITY, item.getQuantity());
        itemValues.put(COLUMN_ORDER_ITEM_PRICE, item.getPrice());
        // Inserting Row
        db.insert(TABLE_ORDER_ITEM, null, itemValues);
        db.close();
    }

    public void deleteOrderItem(OrderItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_ORDER_ITEM, COLUMN_ORDER_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public void updateOrderItem(OrderItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ITEM_ORDER_ID, item.getOrder().getId());
        values.put(COLUMN_ORDER_ITEM_QUANTITY, item.getQuantity());
        values.put(COLUMN_ORDER_ITEM_PRICE, item.getPrice());
        // updating row
        db.update(TABLE_ORDER_ITEM, values, COLUMN_ORDER_ITEM_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public void setPizzas() {
        addPizza(new Pizza("4Fromages",
                "Coulis, chevre, mozzarella, emmenthal, roquefort, creme fraiche, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/DzPTGC6/froma4.png"));
        addPizza(new Pizza("Regina" ,
                "Coulis, chorizo, poivrons, fromage, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/DLjHY42/regina.png"));
        addPizza(new Pizza("Chorizo" ,
                "Coulis, jambon, champignons, fromage, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/MCsjXHH/chorizo.png"));
        addPizza(new Pizza("Marguerita",
                "Coulis, double fromages, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/LZsJ2Jj/marguerita.png"));
        addPizza(new Pizza("Campagne",
                "Coulis, double chevre, champignons, herbes de Provence, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/tM7JD3Y/campagne.png"));
        addPizza(new Pizza("Poulet"  ,
                "Coulis, poulet, chevre, creme fraiche, fromage, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/MfwpcXN/poulet.png"));
        addPizza(new Pizza("3Fromages"   ,
                "Coulis, poulet, chevre, creme fraiche, fromage, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/MfwpcXN/poulet.png"));
        addPizza(new Pizza("Chevre",
                "Coulis, chevre, creme fraiche, oeuf, fromage, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/bWvRRV3/chevre.png"));
        addPizza(new Pizza("Champignons" ,
                "Coulis, champignons, fromage, olives.",
                12,
                18,
                25,
                5,
                "https://i.ibb.co/Xx0sRcy/champi.png"));
        System.out.println(getAllPizza().toString());
    }
}