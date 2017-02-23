package com.arjinmc.ndkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private Button btnCPrint,btnCAdd,btnCIntArray,btnCString,btnCCustom,btnCCustomArray;
    private Button btnJPrint,btnJToast,btnJInt,btnJAdd;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("fun");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        btnCPrint = (Button) findViewById(R.id.btn_c_print);
        btnCPrint.setOnClickListener(this);
        btnCAdd = (Button) findViewById(R.id.btn_c_add);
        btnCAdd.setOnClickListener(this);
        btnCIntArray = (Button) findViewById(R.id.btn_c_intArray);
        btnCIntArray.setOnClickListener(this);
        btnCString = (Button) findViewById(R.id.btn_c_string);
        btnCString.setOnClickListener(this);
        btnCCustom = (Button) findViewById(R.id.btn_c_custom);
        btnCCustom.setOnClickListener(this);
        btnCCustomArray = (Button) findViewById(R.id.btn_c_custom_object_array);
        btnCCustomArray.setOnClickListener(this);

        btnJPrint = (Button) findViewById(R.id.btn_j_print);
        btnJPrint.setOnClickListener(this);
        btnJToast = (Button) findViewById(R.id.btn_j_toast);
        btnJToast.setOnClickListener(this);
        btnJInt = (Button) findViewById(R.id.btn_j_int);
        btnJInt.setOnClickListener(this);
        btnJAdd = (Button) findViewById(R.id.btn_j_add);
        btnJAdd.setOnClickListener(this);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_c_print:
                JNIUtils.cSayHello();
                break;
            case R.id.btn_c_add:
                Toast.makeText(this,"1+2 = "+JNIUtils.cAdd(1,2)+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_c_intArray:
                int[] intArray = new int[]{1,2,3};
                intArray = JNIUtils.cIntArray(intArray);
                Toast.makeText(this,"intArray is {"+intArray[0]+","+intArray[1]+","+intArray[2]+"}"
                        ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_c_string:
                Toast.makeText(this,JNIUtils.cString("arjinmc"),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_c_custom:
                Student stu = JNIUtils.cCreate();
                Toast.makeText(this,stu.toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_c_custom_object_array:
                List<Student> students = new ArrayList<>();
                students.add(new Student("jin",10));
                students.add(new Student("john",20));
                students = JNIUtils.cUpdateStudent(students);
                Toast.makeText(this,"student john age is "+students.get(1).getAge()
                        ,Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_j_print:
                JNIUtils.jSayHello();
                break;
            case R.id.btn_j_toast:
                JNIUtils.jShowToast(MainActivity.this,"This is toast");
                break;
            case R.id.btn_j_int:
                Toast.makeText(this,"int data is "+JNIUtils.jInt(2)
                        ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_j_add:
                Toast.makeText(this,"add is "+JNIUtils.jAdd(1,2)
                        ,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
