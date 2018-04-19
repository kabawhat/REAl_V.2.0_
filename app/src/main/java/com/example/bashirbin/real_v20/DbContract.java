package com.example.bashirbin.real_v20;

import android.provider.BaseColumns;

/**
 * Created by Bashirbin on 11/29/2017.
 */


// this is the database helpher are all the constant should be declared
    // and it made final so that it will not extended by other class
public  final class DbContract {

    // the construcgtor is made private it will not be instanciated by other class
    // good practice
    private DbContract() {

    }


    // this are ours constance here

    public static final class DbConstant implements BaseColumns {




        public  final  static  String DATABASE_NAME = "SAMPLE.DB";
        public final static int DATABASE_VERSION = 1;
        public final static String TABLE_NAME = "SAMPLE_TABLE";




        public final static String PRIMARY_ID_STRING = BaseColumns._ID;

        public final static String ANSWER_COLUMN = "ANSWER";

        public final static String QUESTION_COLUMN = "QUESTION";

        public final static String OPTION1_COLUMN = "QUESTION1";

        public final static String OPTION2_COLUMN = "QUESTION2";

        public final static String OPTION3_COLUMN = "QUESTION3";

        public final static String OPTION4_COLUMN = "QUESTION4";


    }
}