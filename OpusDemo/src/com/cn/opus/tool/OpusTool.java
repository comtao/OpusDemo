package com.cn.opus.tool;

public class OpusTool {
	static{
		try{
			System.loadLibrary("opustool");
		}catch(Exception e){
			System.out.println("can't load lib...");
		}
	}
	private native String nativeGetString();
	
    private native int encode_opus_file(String in_path, String out_path);
    
    private native int decode_opus_file(String in_path, String out_path);  
    
    public String getNativeGetString(){
    	return nativeGetString();
    }
    
    public int opusEncode(String inPath,String outPath){
    	return encode_opus_file(inPath,outPath);
    }
    
    public int opusDecode(String inPath,String outPath){
    	return decode_opus_file(inPath,outPath);
    }
}
