package com.cn.opus.record;

public interface IRecord {
	public boolean open(int buffSize);
	public void close();
	public void start();
	public void stop();
}
