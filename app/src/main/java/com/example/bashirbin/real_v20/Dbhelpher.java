package com.example.bashirbin.real_v20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.R.attr.value;


/**
 * Created by Bashirbin on 11/29/2017.
 */


public class Dbhelpher extends SQLiteOpenHelper {





    public Dbhelpher(Context context) {
        super(context, DbContract.DbConstant.DATABASE_NAME, null, DbContract.DbConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


      // before the bracket there is space
        // please be carefull it should be like this " (" please




            String sql = "CREATE TABLE " + DbContract.DbConstant.TABLE_NAME + " ("
                    +  DbContract.DbConstant.PRIMARY_ID_STRING + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +  DbContract.DbConstant.QUESTION_COLUMN + " TEXT, "
                    +  DbContract.DbConstant.OPTION1_COLUMN + " TEXT, "
                    +  DbContract.DbConstant.OPTION2_COLUMN + " TEXT, "
                    +  DbContract.DbConstant.OPTION3_COLUMN + " TEXT, "
                    +  DbContract.DbConstant.OPTION4_COLUMN + " TEXT, "
                    +  DbContract.DbConstant.ANSWER_COLUMN + " TEXT ); ";

                sqLiteDatabase.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + DbContract.DbConstant.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public long addQuestions(Question question){
        // our database is open with the getWritable dataase method and we are
        // ready to write to write into the database using the content value
        // intead of the conversation raw sql statement

        SQLiteDatabase db = this.getWritableDatabase();
        // the above statement tell the sql that we want to read data into the databse
        ContentValues value = new ContentValues();
        value.put(DbContract.DbConstant.QUESTION_COLUMN,question.getQuestion());
        value.put(DbContract.DbConstant.OPTION1_COLUMN,question.getChoice(0));
        value.put(DbContract.DbConstant.OPTION2_COLUMN,question.getChoice(1));
        value.put(DbContract.DbConstant.OPTION3_COLUMN,question.getChoice(2));
        value.put(DbContract.DbConstant.OPTION4_COLUMN,question.getChoice(3));
        value.put(DbContract.DbConstant.ANSWER_COLUMN,question.getAnswer());

        // the below statement is use to insert the data into the sql
        // by using calling the insert statement on sqliteObject(db)

        long insert = db.insert(DbContract.DbConstant.TABLE_NAME,null,value);
        // we return the long insert variable
        return insert;

    }

    // this method is used to get all question from the database and store into an arraylist
    // or this method extract question from the database and insert it into an arrrayList
    public List<Question> getAllQuestionList(){
    List<Question> questionArrayList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DbContract.DbConstant.TABLE_NAME;

        // our database is open with the getReadable method  and we are
        // ready to  read from the database using the getReadable method and the result
        // is stored in the a cursor
        // a cursor just like what it cursor it point to the item in the database
        //
        // intead of the conversation raw sql statement

        SQLiteDatabase db = this.getReadableDatabase();
        // the above statement tell the sql that we want to read data into the databse


        Cursor cursor = db.rawQuery(selectQuery,null);

        // we loop thriugh the record (item in the database ) and store them into a list
        //
        //TODO: please remenber to change this back to moveNext




       // if(cursor.moveToFirst()) {
        if(cursor.moveToFirst()) {
            do {


                // remenner the Question has two constructor and this is first constructor

                // this is temporal
                Question question = new Question();






                // this is the random fuction that automatically get random option
                // instead of the normal
                Random rand = new Random();
                ArrayList<Integer> unique = new ArrayList<>();
                while (unique.size() < 4) {
                    int random = rand.nextInt(4);
                    if (!unique.contains(random)) {
                        unique.add(random );
                    }
                }




                //this get what ever question is stored in the database(Question  columnn ) and store it in the
                // the arraylist(setQuestion ) index........ etc
                String questtext = cursor.getString(cursor.getColumnIndex(DbContract.DbConstant.QUESTION_COLUMN));
                question.setQuestion(questtext);

                String option1 = cursor.getString(cursor.getColumnIndex(DbContract.DbConstant.OPTION1_COLUMN));
                question.setChoice(unique.get(0), option1);

                String option2 = cursor.getString(cursor.getColumnIndex(DbContract.DbConstant.OPTION2_COLUMN));
                question.setChoice(unique.get(1), option2);

                String option3 = cursor.getString(cursor.getColumnIndex(DbContract.DbConstant.OPTION3_COLUMN));
                question.setChoice(unique.get(2), option3);

                String option4 = cursor.getString(cursor.getColumnIndex(DbContract.DbConstant.OPTION4_COLUMN));
                question.setChoice(unique.get(3), option4);

                String setAnswer = cursor.getString(cursor.getColumnIndex(DbContract.DbConstant.ANSWER_COLUMN));
                question.setAnswer(setAnswer);

                //we add all the item into the tempearal Question model into the
                // the main Arraylist now

                questionArrayList.add(question);

            }

            //TODO please check this later
            while (cursor.moveToNext()) ;
                Collections.shuffle(questionArrayList);


            }


        return questionArrayList;


    }
}
