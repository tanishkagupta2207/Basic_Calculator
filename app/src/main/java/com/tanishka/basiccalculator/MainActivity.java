package com.tanishka.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, sol_tv;
    MaterialButton btn_c,btn_openb, btn_closeb, btn_div, btn_mult, btn_sub, btn_add,btn_eq,btn_ac,btn_point;
    MaterialButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_tv);
        sol_tv = findViewById(R.id.sol_tv);
        assignId(btn_c,R.id.btn_c);
        assignId(btn_ac,R.id.btn_ac);
        assignId(btn_0,R.id.btn_0);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);
        assignId(btn_point,R.id.btn_point);
        assignId(btn_add,R.id.btn_add);
        assignId(btn_sub,R.id.btn_sub);
        assignId(btn_mult,R.id.btn_mult);
        assignId(btn_div,R.id.btn_div);
        assignId(btn_openb,R.id.btn_openb);
        assignId(btn_closeb,R.id.btn_closeb);
        assignId(btn_eq,R.id.btn_eq);
    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btn_text = btn.getText().toString();
        String datatocal = sol_tv.getText().toString();
        if(btn_text.equals("AC")){
            sol_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(btn_text.equals("=")){
            sol_tv.setText(result_tv.getText());
            return;
        }
        if(btn_text.equals("C")){
            datatocal = datatocal.substring(0,datatocal.length()-1);
        }else{
            datatocal+=btn_text;
        }
        sol_tv.setText(datatocal);
        String final_res = getresult(datatocal);
        if(!final_res.equals("Err")){
            result_tv.setText(final_res);
        }
    }

    String getresult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String final_result = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(final_result.endsWith(".0")){
                final_result = final_result.replace(".0","");
            }
            return final_result;
        }catch (Exception e){
            return "Err";
        }
    }
}