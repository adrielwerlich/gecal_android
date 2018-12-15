package com.smp.adrie.complaintsystem.activities;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Complaint {

    private boolean suffered;
    private boolean witnessed;
    private boolean physicalViolence;
    private boolean psychologicalViolence;
    private boolean sexualHarassment;
    private boolean rape;
    private boolean moralHarassment;
    private boolean robery;
    private boolean homophoby;
    private boolean lesbophoby;
    private boolean transphoby;
    private boolean verbalAttack;
    private boolean others;

    private String comments;
    private String name;
    private String email;
    private String phone;
    private String data_relato;
    private String plataforma;
    private boolean success = false;
    CountDownLatch done;

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getData_relato() {
        return data_relato;
    }

    public void setData_relato(String data_relato) {
        this.data_relato = data_relato;
    }

    public Complaint() {

    }

    public Task saveToFirebase(final Activity activity){

        done = new CountDownLatch(1);
        this.plataforma = "mobile";
        this.data_relato = new Date().toString();
        DatabaseReference fireRef = Firebase.getFirebase();
        return fireRef.child("denuncias")
                .child(String.valueOf(new Random()
                .nextInt(999999)))
                .setValue( this );
// .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> t) {
//                        if (t.isSuccessful()){
//                            try {
//                                done.await();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            Toast.makeText(activity, "Registro feito", Toast.LENGTH_LONG).show();
//                            success = true;
//                        } else {
//                            Log.e("[COMPLAINT NOT SAVED]", t.getResult().toString());
//                            Toast.makeText(activity, "Registro não realizado. Erro!",
//                                    Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                });
//        try {
//            done.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return success;
    }

    public boolean isSuffered() {
        return suffered;
    }

    public void setSuffered(boolean suffered) {
        this.suffered = suffered;
    }

    public boolean isWitnessed() {
        return witnessed;
    }

    public void setWitnessed(boolean witnessed) {
        this.witnessed = witnessed;
    }

    public boolean isPhysicalViolence() {
        return physicalViolence;
    }

    public void setPhysicalViolence(boolean physicalViolence) {
        this.physicalViolence = physicalViolence;
    }

    public boolean isPsychologicalViolence() {
        return psychologicalViolence;
    }

    public void setPsychologicalViolence(boolean psychologicalViolence) {
        this.psychologicalViolence = psychologicalViolence;
    }

    public boolean isSexualHarassment() {
        return sexualHarassment;
    }

    public void setSexualHarassment(boolean sexualHarassment) {
        this.sexualHarassment = sexualHarassment;
    }

    public boolean isRape() {
        return rape;
    }

    public void setRape(boolean rape) {
        this.rape = rape;
    }

    public boolean isMoralHarassment() {
        return moralHarassment;
    }

    public void setMoralHarassment(boolean moralHarassment) {
        this.moralHarassment = moralHarassment;
    }

    public boolean isRobery() {
        return robery;
    }

    public void setRobery(boolean robery) {
        this.robery = robery;
    }

    public boolean isHomophoby() {
        return homophoby;
    }

    public void setHomophoby(boolean homophoby) {
        this.homophoby = homophoby;
    }

    public boolean isLesbophoby() {
        return lesbophoby;
    }

    public void setLesbophoby(boolean lesbophoby) {
        this.lesbophoby = lesbophoby;
    }

    public boolean isTransphoby() {
        return transphoby;
    }

    public void setTransphoby(boolean transphoby) {
        this.transphoby = transphoby;
    }

    public boolean isVerbalAttack() {
        return verbalAttack;
    }

    public void setVerbalAttack(boolean verbalAttack) {
        this.verbalAttack = verbalAttack;
    }

    public boolean isOthers() {
        return others;
    }

    public void setOthers(boolean others) {
        this.others = others;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments_() {
        return comments_;
    }

    public void setComments_(String comments_) {
        this.comments_ = comments_;
    }

    private String comments_;



}
