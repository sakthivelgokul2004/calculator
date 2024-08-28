package com.example.lol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.Toast;

import com.example.lol.databinding.ActivityMainBinding;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       */
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        assginbtn(binding.ac,binding.ac.getId());
        assginbtn(binding.mul,binding.mul.getId());
        assginbtn(binding.muns,binding.muns.getId());
        assginbtn(binding.plus,binding.plus.getId());
        assginbtn(binding.nine,binding.nine.getId());
        assginbtn(binding.eight,binding.eight.getId());
        assginbtn(binding.seven,binding.seven.getId());
        assginbtn(binding.six,binding.six.getId());
        assginbtn(binding.five,binding.five.getId());
        assginbtn(binding.four,binding.four.getId());
        assginbtn(binding.three,binding.three.getId());
        assginbtn(binding.two,binding.two.getId());
        assginbtn(binding.eqs,binding.eqs.getId());
        assginbtn(binding.zeroo,binding.zeroo.getId());
        assginbtn(binding.zero,binding.zero.getId());
        assginbtn(binding.one,binding.one.getId());

    }
    void  assginbtn(Button btn,int id){
        btn = binding.getRoot().findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        btn=binding.getRoot().findViewById(view.getId());
        String btntxt =btn.getText().toString();
        String dtc=binding.maintxt.getText().toString();

        switch (btntxt){
            case "x":
                if(dtc.isEmpty())
                    dtc="";

                else
                    dtc=dtc+"x";
                break;
            case "00":
            case "-":
            case "+":
                if(dtc.isEmpty())
                    dtc="";
                 else
                     dtc=dtc+btntxt;
                 break;
            case "=":
                binding.result.setText(dtc);
                String equation =dtc.replace("x","*");
                dtc=getResult(equation);
                break;
            case "ac":
                dtc="";
                binding.result.setText("");
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                dtc=dtc+btntxt;
            break;


        }


        binding.maintxt.setText(dtc);
    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}

