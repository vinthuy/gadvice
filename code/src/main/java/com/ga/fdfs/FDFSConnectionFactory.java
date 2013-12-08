package com.ga.fdfs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingDeque;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

import com.ga.utils.StringUtils;

public class FDFSConnectionFactory {

	FDFSConnectionFactory() {

	}

	/**
	 * @description 获取资源服务器地址
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @return
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public String getFastSourceUrl() throws Exception {
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		InetSocketAddress inetSockAddr = trackerServer.getInetSocketAddress();
		String file_url = "http://"
				+ inetSockAddr.getAddress().getHostAddress();
		if (ClientGlobal.g_tracker_http_port != 80) {
			file_url += ":" + ClientGlobal.g_tracker_http_port;
		}
		trackerServer.close();
		return file_url;
	}

	public FDFSConnection getConnection() throws Exception {
		FDFSConnection connection = null;
		StorageServer storageServer = null;
		TrackerServer ts = getTrackerServer();
		if (ts == null)
			throw new FdfsException("trackerServer is null");
		StorageClient1 storageConnection = new StorageClient1(ts, storageServer);
		connection = new FDFSConnection();
		connection.setFactory(this);
		connection.setTrackerServer(ts);
		connection.setStorageConnection(storageConnection);
		return connection;
	}

	public FDFSConnection getConnection(String group_name) throws Exception {
		FDFSConnection connection = null;
		StorageServerInfo ss = getStorageServer();
		if (ss == null)
			throw new FdfsException("trackerServer is null");
		connection = new FDFSConnection();
		connection.setFactory(this);
		connection.setStorageServerInfo(ss);
		StorageClient1 storageConnection = new StorageClient1(ss.getTs(), ss.getSs());
		connection.setStorageConnection(storageConnection);
		return connection;
	}

	// 以下trackerServerList 参数设置---start
	private LinkedBlockingDeque<TrackerServer> trackerServerList = new LinkedBlockingDeque<TrackerServer>();
	private int trackerServerMount = 10;


	@SuppressWarnings("unused")
	private void CreateTrackerServer() throws IOException {
		CreateTrackerServer(trackerServerMount);
	}

	@SuppressWarnings("unused")
	private void CreateTrackerServer(TrackerGroup trackerGroup)
			throws IOException {
		CreateTrackerServer(trackerServerMount, trackerGroup);
	}

	private void CreateTrackerServer(int maxTrackerCount,
			TrackerGroup trackerGroup) throws IOException {
		for (int i = 0; i < maxTrackerCount; i++) {
			TrackerClient tracker = null;
			if (trackerGroup == null)
				tracker = new TrackerClient();
			else {
				tracker = new TrackerClient(trackerGroup);
			}
			trackerServerList.add(tracker.getConnection());
		}
	}

	private void CreateTrackerServer(int maxTrackerCount) throws IOException {
		CreateTrackerServer(maxTrackerCount, null);
	}

	public TrackerServer getTrackerServer() {
		return trackerServerList.remove();
	}

	public void releaseConnection(TrackerServer ts) {
		trackerServerList.add(ts);
	}

	public int getTrackerServerMount() {
		return trackerServerMount;
	}

	public void setTrackerServerMount(int trackerServerMount) {
		this.trackerServerMount = trackerServerMount;
	}

	// 以下trackerServerList 参数设置---end
	/**************************************************************************
	 ***************** 关于有groupName的设置***************************************
	 ************************************************************************/
	
	
	private LinkedBlockingDeque<StorageServerInfo> storageServerList = new LinkedBlockingDeque<StorageServerInfo>();
	private int storageServerMount = 60;

	@SuppressWarnings("unused")
	private void CreateStorageServer(String groupName) throws IOException {
		CreateStorageServer(null, storageServerMount, groupName);
	}

	@SuppressWarnings("unused")
	private void CreateStorageServer(int storageServerMount, String groupName)
			throws IOException {
		CreateStorageServer(storageServerMount, groupName);
	}

	public int getStorageServerMount() {
		return storageServerMount;
	}

	public void setStorageServerMount(int storageServerMount) {
		this.storageServerMount = storageServerMount;
	}

	private void CreateStorageServer(TrackerGroup trackerGroup,
			int maxStorageServerMount, String groupName) throws IOException {
		for (int i = 0; i < maxStorageServerMount; i++) {
			TrackerClient tracker = null;
			if (trackerGroup == null)
				tracker = new TrackerClient();
			else {
				tracker = new TrackerClient(trackerGroup);
			}
			StorageServer ss = null;
			TrackerServer ts = tracker.getConnection();
			if (StringUtils.isNotNullOrBlank(groupName))
				ss = tracker.getStoreStorage(ts, groupName);
			else
				ss = tracker.getStoreStorage(ts);
			StorageServerInfo si = new StorageServerInfo();
			si.setSs(ss);
			si.setTs(ts);
			storageServerList.add(si);
		}
	}

	public StorageServerInfo getStorageServer() {
		return storageServerList.remove();
	}

	public void releaseConnection(StorageServerInfo ss) {
		storageServerList.add(ss);
	}
	
	public void closedConnection(FDFSConnection connection,String groupName){
		if(StringUtils.isNullOrEmpty(groupName))
		connection.close();
		else connection.close(groupName);
	}
	public void closedConnection(FDFSConnection connection){
		
		connection.close();
	}
	
}
