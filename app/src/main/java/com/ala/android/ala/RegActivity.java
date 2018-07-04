package com.ala.android.ala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {
    EditText name1,name2,name3,en1,en2,en3,mail,batch;
    Button reg;
    SharedPreferences sharedpreferences;
    DatabaseReference mDatabase;


    public static final String MyPREFERENCES = "GroupPrefs" ;
    public static final String Name1 = "name1Key";
    public static final String Name2 = "name2Key";
    public static final String Name3 = "name3Key";
    public static final String En1 = "en1Key";
    public static final String En2 = "en2Key";
    public static final String En3 = "en3Key";
    public static final String GroupID = "groupKey";
    public static final String Batch = "batch";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        name1=(EditText)findViewById(R.id.name1);
        name2=(EditText)findViewById(R.id.name2);
        name3=(EditText)findViewById(R.id.name3);
        en1=(EditText)findViewById(R.id.en1);
        en2=(EditText)findViewById(R.id.en2);
        en3=(EditText)findViewById(R.id.en3);
        mail=(EditText)findViewById(R.id.email);
        batch=(EditText)findViewById(R.id.batch);
        reg=(Button)findViewById(R.id.regbutton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor data = sharedpreferences.edit();
                data.putString(Name1,name1.getText().toString());
                data.putString(Name2,name2.getText().toString());
                data.putString(Name3,name3.getText().toString());
                data.putString(En1,en1.getText().toString());
                data.putString(En2,en2.getText().toString());
                data.putString(En3,en3.getText().toString());
                data.putString(Batch,batch.getText().toString());

                String s1,s2,s3;
                s1=en1.getText().toString();
                s2=en2.getText().toString();
                s3=en3.getText().toString();

                groups g1=new groups(name1.getText().toString(),s1,mail.getText().toString(),name2.getText().toString(),s2,name3.getText().toString(),s3);

                mDatabase = FirebaseDatabase.getInstance().getReference();

                if(s1.length()!=12 )
                {
                    Toast.makeText(getApplicationContext(),"Enter valid Enrollment no.",Toast.LENGTH_SHORT).show();
                }
                else {
                    String id=s1.substring(10)+s2.substring(10)+s3.substring(10);
                    data.putString(GroupID, id);
                    data.commit();
                    mDatabase.child("Groups").child(id).setValue(g1);
                    Intent up=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(up);
                }
            }
        });

    }
}
class groups
{
    public String name1;
    public String en1;
    public String mail;
    public String name2;
    public String en2;
    public String name3;
    public String en3;

    groups(){};
    groups(String name1,String en1,String mail,String name2,String en2,String name3,String en3)
    {
        this.name1=name1;
        this.en1=en1;
        this.mail=mail;
        this.name2=name2;
        this.en2=en2;
        this.name3=name3;
        this.en3=en3;
    }
}
