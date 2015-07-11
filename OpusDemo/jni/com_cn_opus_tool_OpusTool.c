
#include <com_cn_opus_tool_OpusTool.h>


JNIEXPORT jstring JNICALL Java_com_cn_opus_tool_OpusTool_nativeGetString
  (JNIEnv *env, jobject obj){
	return (*env)->NewStringUTF(env, "Hello 中文 Opus");
}


JNIEXPORT jint JNICALL Java_com_cn_opus_tool_OpusTool_encode_1opus_1file
  (JNIEnv *env, jobject obj, jstring wav_path, jstring opus_path){
    return encode_wav_file(wav_path,opus_path);
}

JNIEXPORT jint JNICALL Java_com_cn_opus_tool_OpusTool_decode_1opus_1file
  (JNIEnv *env, jobject obj, jstring wav_path, jstring opus_path){
	return decode_opus_file(wav_path,opus_path);
}

