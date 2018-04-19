package com.example.bashirbin.real_v20;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bashirbin on 11/29/2017.
 */

public class QuestionBank {

    // in this class we use Question class and
    // datasbe helpher class so we have to instantaite them

    List<Question> list = new ArrayList<>();
    Dbhelpher dbhelpher;

    // this method return the size of the list of question in the list array
    public int getLenth(){
        return list.size();
    }

    // this method return the question base on the index passed
    // get Question from the index passsed into the method
    public String getQuestion (int i){
        return list.get(i).getQuestion();
    }

    // this method return a single multiple question based on  the list index
    // and remember that the num is option is + 1 (plus one )
    public String getChoice(int index,int num){
        return list.get(index).getChoice(num-1);
    }

    //this method return the correct answer of the basesd on the index passed

    public String getCorrectAnswer (int a){
        return list.get(a).getAnswer();
    }
    public void intitializeQuestion(Context context){
        dbhelpher = new Dbhelpher(context);
        list =dbhelpher.getAllQuestionList();

        // if the list is empty populate the list with this intital value
        if(list.isEmpty()){
                
            //dbhelpher.addQuestions(new Question("when did google acquire android", new String[]{"2001","2003","2004","2005"},"2005"));
            dbhelpher.addQuestions(new Question("2. when which is the first english alphabet ", new String[]{" A "," B "," C "," D "}," A "));
            dbhelpher.addQuestions(new Question("3. when which is the last english alphabet ", new String[]{" A "," B "," Y "," Z "}," Z "));
            dbhelpher.addQuestions(new Question("4. when which is the Second english alphabet ", new String[]{" A "," B "," C "," D "}," B "));
            dbhelpher.addQuestions(new Question("1. Usman Dan Fodio university Sokoto  was founded in what year ?  ", new String[]{" 1976 "," 1957 "," 1975 "," 1977 "}," 1975 "));
            dbhelpher.addQuestions(new Question("5. Usman Dan Fodio university was located at  ?  ", new String[]{" sakkwato "," sakoto "," soktto "," sokoto "}," sokoto "));

            // get the list from database again



            list= dbhelpher.getAllQuestionList();


        }


    }















}
