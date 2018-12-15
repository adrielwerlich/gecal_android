package com.smp.adrie.complaintsystem.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.smp.adrie.complaintsystem.R;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_comments, cb_physical, cb_psychological, cb_sexual_harassment,
        cb_moral_harassment, cb_transfobia, cb_homo, cb_lesb, cb_robery, cb_verbal,
        cb_rape;
    EditText ed_comments, ed_comments_, ed_phone, ed_name, ed_email;
    RadioButton rb_suffered, rb_witnessed;
    RadioGroup rg_choice;
    Button btn_record;
    ScrollView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
//                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFields();



//        ed_comments.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                ed_comments.setTranslationY(-600f);
//
//                return false;
//            }
//        });

    }

    private void initFields() {

        sv = (ScrollView) findViewById(R.id.sv_check_boxes);



        rb_suffered = (RadioButton) findViewById(R.id.rb_suffered);
        rb_witnessed = (RadioButton) findViewById(R.id.rb_witnessed);
        cb_sexual_harassment = (CheckBox) findViewById(R.id.cb_sexual_harassment);
        cb_moral_harassment = (CheckBox) findViewById(R.id.cb_moral_harassment);
        cb_transfobia = (CheckBox) findViewById(R.id.cb_transf);
        cb_homo = (CheckBox) findViewById(R.id.cb_homo);
        cb_physical = (CheckBox) findViewById(R.id.cb_phisical_violence);
        cb_lesb = (CheckBox) findViewById(R.id.cb_lesb);
        cb_psychological = (CheckBox) findViewById(R.id.cb_psychological_violence);
        cb_robery = (CheckBox) findViewById(R.id.cb_robery);
        cb_verbal = (CheckBox) findViewById(R.id.cb_verbal_attack);
        cb_rape = (CheckBox) findViewById(R.id.cb_rape);
        cb_comments = (CheckBox) findViewById(R.id.cb_others);

        ed_comments = (EditText) findViewById(R.id.ed_comments);

        Log.i("[DATE]",new Date().toString());

        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_comments_ = (EditText) findViewById(R.id.ed_comments_);

        btn_record = (Button) findViewById(R.id.btn_record);

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Complaint c = new Complaint();

                if (rb_witnessed.isChecked() || rb_suffered.isChecked()) {

                    if (rb_suffered.isChecked()) c.setSuffered(true);
                    if (rb_witnessed.isChecked()) c.setWitnessed(true);




                    if (cb_homo.isChecked()) c.setHomophoby(true);
                    if (cb_lesb.isChecked()) c.setLesbophoby(true);
                    if (cb_moral_harassment.isChecked()) c.setMoralHarassment(true);
                    if (cb_physical.isChecked()) c.setPhysicalViolence(true);
                    if (cb_psychological.isChecked()) c.setPsychologicalViolence(true);
                    if (cb_rape.isChecked()) c.setRape(true);
                    if (cb_robery.isChecked()) c.setRobery(true);
                    if (cb_sexual_harassment.isChecked()) c.setSexualHarassment(true);
                    if (cb_transfobia.isChecked()) c.setTransphoby(true);
                    if (cb_verbal.isChecked()) c.setVerbalAttack(true);

                    if (cb_comments.isChecked()) {
                        if (!ed_comments.getText().toString().isEmpty()) {
                            c.setComments(ed_comments.getText().toString());
                        } else {
                            Toast.makeText(MainActivity.this, "Comentário vazio. " +
                                    "Preencha algo", Toast.LENGTH_LONG).show();
                            return;
                        }

                    }
                    if (!ed_name.getText().toString().isEmpty())
                        c.setName(ed_name.getText().toString());
                    if (!ed_email.getText().toString().isEmpty())
                        c.setEmail(ed_email.getText().toString());
                    if (!ed_phone.getText().toString().isEmpty())
                        c.setPhone(ed_phone.getText().toString());
                    if (!ed_comments_.getText().toString().isEmpty())
                        c.setComments_(ed_comments_.getText().toString());

                    Task task = c.saveToFirebase(MainActivity.this);

                    task.addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                rb_suffered.setChecked(false);
                                rb_witnessed.setChecked(false);

                                cb_homo.setChecked(false);
                                cb_lesb.setChecked(false);
                                cb_moral_harassment.setChecked(false);
                                cb_physical.setChecked(false);
                                cb_psychological.setChecked(false);
                                cb_rape.setChecked(false);
                                cb_robery.setChecked(false);
                                cb_sexual_harassment.setChecked(false);
                                cb_transfobia.setChecked(false);
                                cb_verbal.setChecked(false);
                                cb_comments.setChecked(false);

                                ed_comments.setText("Comentários");
                                ed_comments.setEnabled(false);

                                ed_name.setText("");
                                ed_phone.setText("");
                                ed_email.setText("");
                                ed_comments_.setText("");

                                sv.fullScroll(ScrollView.FOCUS_UP);


                                Toast.makeText(MainActivity.this, "Registro feito", Toast.LENGTH_LONG).show();

                            } else {
                                Log.e("[COMPLAINT NOT SAVED]", task.getResult().toString());
                                Toast.makeText(MainActivity.this, "Registro não realizado. Erro!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                } else {
                    Toast.makeText(MainActivity.this,
                        "Precisa selecionar vivenciou ou testemunhou",
                        Toast.LENGTH_LONG).show();
                }
            }
        });

        ed_comments_.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // hide keyboard
                    InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        cb_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_comments.isChecked()){
                    ed_comments.setEnabled(true);
                    ed_comments.requestFocus();

                    if (ed_comments.getText().toString().equals("Comentários")){
                        ed_comments.setText("");
                    } else if (ed_comments.getText().toString().equals("")){
                        ed_comments.setText("Comentários");
                    }
                } else {
                    if (ed_comments.getText().toString().equals("")){
                        ed_comments.setText("Comentários");
                    }
                    ed_comments.setEnabled(false);
                }
            }
        });


        ed_phone.addTextChangedListener(PhoneMask.insert("(##)#####-####", ed_phone));

    }

    private boolean checkPhoneMask( String phone ) {

        String formatOne = "\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{4}?";
        String formatTwo = "\\([0-9]{2}?\\)[0-9]{5}?\\-[0-9]{4}?";

        if ( phone.matches( formatOne ) || phone.matches( formatTwo ) ) {
            return true;
        } else {
            return false;
        }
    }

}
