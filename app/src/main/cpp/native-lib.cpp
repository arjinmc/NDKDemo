#include <jni.h>
#include <string>
#include <android/log.h>

#define java_clazz "com/arjinmc/ndkdemo/JNIUtils"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,"print from C",__VA_ARGS__)


/***
 * this file is for c to call java method
 */


extern "C"
JNIEXPORT void JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_jSayHello(JNIEnv *env, jclass claz) {

    //this claz is com/arjinmc/ndkdemo/JNIUtils
    //no need to call findClass
//    jclass mClazz = env->FindClass(java_clazz);
    jmethodID  mid = env->GetStaticMethodID(claz,"sayHellow","()V");
    env->CallStaticVoidMethod(claz,mid);

}

extern "C"
JNIEXPORT void JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_jShowToast(JNIEnv *env, jclass claz, jobject context,
                                             jstring msg) {

    jmethodID mid = env->GetStaticMethodID(claz,"showToast"
            ,"(Landroid/content/Context;Ljava/lang/String;)V");
    env->CallStaticVoidMethod(claz,mid,context,msg);
}

extern "C"
jstring
Java_com_arjinmc_ndkdemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_jInt(JNIEnv *env, jclass clazz, jint i) {

    jmethodID  mid = env->GetStaticMethodID(clazz,"showInt","(I)I");
    return env->CallStaticIntMethod(clazz,mid,i);

}

extern "C"
JNIEXPORT jint JNICALL
Java_com_arjinmc_ndkdemo_JNIUtils_jAdd(JNIEnv *env, jclass clazz, jint x, jint y) {

    jmethodID  mid = env->GetStaticMethodID(clazz,"add","(II)I");
    return env->CallStaticIntMethod(clazz,mid,x,y);

}
