package com.example.harsh.fetch2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Fetch extends AppCompatActivity implements View.OnClickListener {

    public static MyPredictionDatabase myAppDatabase;
    public static MyStudentsDatabase myStudentsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyPredictionDatabase.class,"predictions.db").allowMainThreadQueries().build();
        myStudentsDatabase=Room.databaseBuilder(getApplicationContext(),MyStudentsDatabase.class,"students.db").allowMainThreadQueries().build();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addPredictionDetails:

                InputStream in=getResources().openRawResource(R.raw.predictions);
                BufferedReader reader=new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

                String line="";
                int i=0;
                try {
                    //Step Over headers
                    reader.readLine();

                    while ((line=reader.readLine())!=null){

                        //Split by ','
                        String []tokens=line.split(",");

                        //Read the data
                        Predictions predict=new Predictions();
                        predict.setName(tokens[0]);
                        predict.setRollNo(Integer.parseInt(tokens[1]));
                        predict.setBranch(tokens[2]);
                        predict.setPredicted_marks(Double.parseDouble(tokens[3]));
                        predict.setPredicted_drop(Double.parseDouble(tokens[4]));
                        predict.setPersonality(tokens[5]);
                        predict.setRecommended1(tokens[6]);
                        predict.setRecommended2(tokens[7]);
                        predict.setRecommended3(tokens[8]);
                        predict.setRecommended4(tokens[9]);
                        predict.setRecommended5(tokens[10]);

                        Fetch.myAppDatabase.myPredictionsDao().tablePredictionsAdd(predict);
                        i++;

                    }
                } catch (IOException e) {
                    Log.i("MyActivity","Error reading data file on line "+line ,e);
                }


                Toast.makeText(getApplicationContext(),"Added "+i,Toast.LENGTH_SHORT).show();
                break;

            case R.id.addStudentsDetails :

                InputStream is=getResources().openRawResource(R.raw.clean_4);
                BufferedReader reader1=new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8")));

                String line2="";
                int j=0;
                try {
                    //Step Over headers
                    reader1.readLine();

                    while ((line2=reader1.readLine())!=null){

                        //Split by ','
                        String []tokens=line2.split(",");

                        //Read the data
                        Students stud=new Students();
                        stud.setName(tokens[0]);
                        stud.setFather(tokens[1]);
                        stud.setRollNo(Integer.parseInt(tokens[2]));
                        stud.setStudentNo(Integer.parseInt(tokens[3]));
                        stud.setBranch(tokens[4]);
                        stud.setYear(tokens[5]);
                        stud.setSection(tokens[6]);
                        stud.setCurrent_sem(Integer.parseInt(tokens[7]));
                        stud.setDOB(tokens[8]);
                        stud.setContact(Long.parseLong(tokens[9]));
                        stud.setParent_contact(Long.parseLong(tokens[10]));
                        stud.setEmail(tokens[11]);
                        stud.setHigh_School(Double.parseDouble(tokens[12]));
                        stud.setIntermediate(Double.parseDouble(tokens[13]));
                        stud.setB_tech(Double.parseDouble(tokens[14]));
                        if(tokens[15].length()>0) {
                            stud.setSem_1_marks(Double.parseDouble(tokens[15]));
                        }else{
                            stud.setSem_1_marks(0);
                        }
                        if(tokens[16].length()>0) {
                            stud.setSem_1_attendence(Double.parseDouble(tokens[16]));
                        }else{
                            stud.setSem_1_attendence(0);
                        }
                        if(tokens[17].length()>0) {
                            stud.setSem_2_marks(Double.parseDouble(tokens[17]));
                        }else{
                            stud.setSem_2_marks(0);
                        }
                        if(tokens[18].length()>0) {
                            stud.setSem_2_attendence(Double.parseDouble(tokens[18]));
                        }else{
                            stud.setSem_2_attendence(0);
                        }
                        if(tokens[19].length()>0) {
                            stud.setSem_3_marks(Double.parseDouble(tokens[19]));
                        }else{
                            stud.setSem_3_marks(0);
                        }
                        if(tokens[20].length()>0) {
                            stud.setSem_3_attendence(Double.parseDouble(tokens[20]));
                        }else{
                            stud.setSem_3_attendence(0);
                        }
                        if(tokens[21].length()>0) {
                            stud.setSem_4_marks(Double.parseDouble(tokens[21]));
                        }else{
                            stud.setSem_4_marks(0);
                        }
                        if(tokens[22].length()>0) {
                            stud.setSem_4_attendence(Double.parseDouble(tokens[22]));
                        }else{
                            stud.setSem_4_attendence(0);
                        }
                        if(tokens[23].length()>0) {
                            stud.setSem_5_marks(Double.parseDouble(tokens[23]));
                        }else{
                            stud.setSem_5_marks(0);
                        }
                        if(tokens[24].length()>0) {
                            stud.setSem_5_attendence(Double.parseDouble(tokens[24]));
                        }else{
                            stud.setSem_5_attendence(0);
                        }
                        if(tokens[25].length()>0) {
                            stud.setSem_6_marks(Double.parseDouble(tokens[25]));
                        }else{
                            stud.setSem_6_marks(0);
                        }
                        if(tokens[26].length()>0) {
                            stud.setSem_6_attendence(Double.parseDouble(tokens[26]));
                        }else{
                            stud.setSem_6_attendence(0);
                        }


                        Fetch.myStudentsDatabase.myStudentsDao().tableStudentsAdd(stud);
                        j++;

                    }
                } catch (IOException e) {
                    Log.i("MyActivity","Error reading data file on line "+line2 ,e);
                }

                Toast.makeText(getApplicationContext(),"Added "+j,Toast.LENGTH_SHORT).show();
                break;

            case R.id.viewPredictionDetails :

                Intent viewDetailsIntent=new Intent(this,ViewPredictionsDetails.class);
                startActivity(viewDetailsIntent);

                break;

            case R.id.viewStudentDetails :

                Intent viewStudentDetailsIntent=new Intent(this,ViewStudentDetails.class);
                startActivity(viewStudentDetailsIntent);

                break;

            case R.id.studentLogin :

                Intent viewStudentLoginIntent=new Intent(this,LoginStudent.class);
                startActivity(viewStudentLoginIntent);

                break;

        }

    }
}
