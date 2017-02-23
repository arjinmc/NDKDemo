#include <jni.h>
#include <stdlib.h>
#include <android/log.h>

/**
 * this file for java to call c method
 */

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,"print from C",__VA_ARGS__)



JNIEXPORT void JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_cSayHello(JNIEnv *env,jobject instance){

    LOGD("Hello JNI,I'm C");
}

JNIEXPORT jint JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_cAdd(JNIEnv *env, jobject obj, jint x, jint y) {

    return x+y;

}

JNIEXPORT jintArray JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_cIntArray(JNIEnv *env, jclass clazz, jintArray intArray_) {

    jint* intArray = (*env)->GetIntArrayElements(env,intArray_, NULL);
    jsize arraySize = (*env)->GetArrayLength(env,intArray_);

    //each cell of the int array add 1
    for(int i=0;i<arraySize;i++){
       *(intArray+i) +=1;
    }

    return intArray_;
}

JNIEXPORT jstring JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_cString(JNIEnv *env, jobject obj, jstring str_) {
    const char *str = (*env)->GetStringUTFChars(env, str_, NULL);
    int size = sizeof(str_);
    //add "Hi," in front of str_
    char* newStr = malloc(size+3);
    strcpy(newStr,"Hi,");
    strcat(newStr,str);
    (*env)->ReleaseStringUTFChars(env, str_, str);
    return (*env)->NewStringUTF(env, newStr);
}

JNIEXPORT jobject JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_cCreate(JNIEnv *env, jclass clazz) {

    jclass  stuClazz = (*env)->FindClass(env,"com/arjinmc/ndkdemo/Student");
    jmethodID  stuCreateMid = (*env)->GetMethodID(env,stuClazz,"<init>","(Ljava/lang/String;I)V");
    jobject stuObj = (*env)->NewObject(env,stuClazz,stuCreateMid
            ,(*env)->NewStringUTF(env, "john"),18);
    return stuObj;
}

JNIEXPORT jobject JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_cUpdateStudent(JNIEnv *env, jclass clazz, jobject studentList) {

    //get the classname for List
    jclass listClazz = (*env)->GetObjectClass(env,studentList);

    //get the List method get(i)
    jmethodID  list_get = (*env)->GetMethodID(env,listClazz,"get","(I)Ljava/lang/Object;");
    //get the List method size()
    jmethodID  list_size = (*env)->GetMethodID(env,listClazz,"size","()I");

    //get the size of student list
    jsize stuSize = (*env)->CallIntMethod(env,studentList,list_size);
    //get the second student from the student list
    jobject student = (*env)->CallObjectMethod(env,studentList,list_get,1);
    jclass  stuClazz = (*env)->FindClass(env,"com/arjinmc/ndkdemo/Student");
    //get the Student method call setAge(i)
    jmethodID  stuSetAgeMid = (*env)->GetMethodID(env,stuClazz,"setAge","(I)V");
    //change the second student's age as 19
    (*env)->CallVoidMethod(env,student,stuSetAgeMid,19);

    return  studentList;

}

