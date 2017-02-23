package com.arjinmc.ndkdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Eminem on 2017/2/19.
 */

public class JNIUtils {

    private static final String TAG = "print from Java";

    /****************************************************************************/
    /**c methods                                                                */
    /****************************************************************************/
    public static native void cSayHello();

    public static native int cAdd(int x,int y);

    public static native int[] cIntArray(int[] intArray);

    public static native String cString(String str);

    public static native Student cCreate();

    public static native List<Student> cUpdateStudent(List<Student> studentList);


    public static native void jSayHello();

    public static native void jShowToast(Context context,String msg);

    public static native int jInt(int i);

    public static native int jAdd(int x,int y);


    /****************************************************************************/
    /**java methods                                                             */
    /****************************************************************************/
    public static void sayHellow(){
        Log.d(TAG,"hello");
    }

    public static void showToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static int showInt(int i) { return i;}

    public static int add(int x,int y){return x+y;};


}
