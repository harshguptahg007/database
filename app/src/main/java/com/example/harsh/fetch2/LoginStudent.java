package com.example.harsh.fetch2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginStudent extends AppCompatActivity {

    private EditText vemail,vroll;
    private Button login;
    private String msg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        vemail=(EditText)findViewById(R.id.email_edit);
        vroll=(EditText)findViewById(R.id.rollno_edit);

        login=(Button)findViewById(R.id.login_button);

        /*List<Students> students=Fetch.myStudentsDatabase.myStudentsDao().getStudentLoginDetails();

        String info="";
        int i=0;

        for(Students stud:students){

            String email=stud.getEmail();
            int roll=stud.getRollNo();

            if(vemail.getText().toString().equalsIgnoreCase(email)){

                if(Integer.parseInt(vroll.getText().toString())==roll) {
                    msg = "User Verified";
                }else{
                    msg="Invalid User";
                }
                break;
            }else{
                msg="Invalid User";
            }

            i++;

            info=info+"\n\nEmail : "+email+"\nRoll No : "+roll;

        }*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Students> students=Fetch.myStudentsDatabase.myStudentsDao().getStudentLoginDetails();

                String info="";
                int i=0;

                for(Students stud:students){

                    String email=stud.getEmail();
                    int roll=stud.getRollNo();

                    if(vemail.getText().toString().equalsIgnoreCase(email)){

                        if(Integer.parseInt(vroll.getText().toString())==roll) {
                            msg = "User Verified";

                            Intent viewResult=new Intent(LoginStudent.this,ViewLogin.class);
                            startActivity(viewResult);

                        }else{
                            msg="Invalid User";
                        }
                        break;
                    }else{
                        msg="Invalid User";
                    }

                    i++;

                    info=info+"\n\nEmail : "+email+"\nRoll No : "+roll;

                }

                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

                //Intent viewResult=new Intent(LoginStudent.this,ViewLogin.class);
                //startActivity(viewResult);

            }
        });
    }
}
