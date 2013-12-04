package com.ga.fdfs;

import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;

public class StorageServerInfo{
	private  TrackerServer ts ;
	private StorageServer ss ;
	public TrackerServer getTs() {
		return ts;
	}
	public void setTs(TrackerServer ts) {
		this.ts = ts;
	}
	public StorageServer getSs() {
		return ss;
	}
	public void setSs(StorageServer ss) {
		this.ss = ss;
	}
}