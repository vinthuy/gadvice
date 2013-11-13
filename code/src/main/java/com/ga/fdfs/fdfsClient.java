package com.ga.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Date;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.UploadCallback;

public class fdfsClient {

	private static final String upload_time = "uploadTime";

	private static final String group_name = "group1";
	public fdfsClient() {
	}

	/**
	 * @description 初始化fdfs客户端
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @throws MyException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @since gadvice1.0.0
	 */
	public static void initFdfsClient() throws FileNotFoundException,
			IOException, MyException {
		String conFilePath = fdfsClient.class.getClassLoader()
				.getResource("fdfs_client.conf").getPath().toString();
		ClientGlobal.init(conFilePath);
	}

	/**
	 * @description 上传文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param localFileName
	 *            本地文件
	 * @param extName
	 *            扩展文件
	 * @param pair
	 *            文件附加信息
	 * @return 服务器文件地址
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public static String uploadFile(String localFileName, String extName,
			NameValuePair[] pair) throws Exception {
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient1 s_clent1 = new StorageClient1(trackerServer,
				storageServer);
		String file_id = s_clent1.upload_file1(localFileName, extName, pair);
		trackerServer.close();
		return file_id;
	}

	/**
	 * @description 删除文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param fileId
	 * @return
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public static int deleteFile(String fileId) throws Exception {
		// 建立连接
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient1 s_clent1 = new StorageClient1(trackerServer,
				storageServer);
		int delCode = s_clent1.delete_file1(fileId);
		trackerServer.close();
		return delCode;
	}

	/**
	 * @description 修改上传的文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param fileId
	 * @param newLocalFileName
	 *            新上传的本地文件路径
	 * @param extName
	 *            扩展文件
	 * @param pair
	 *            文件附加信息
	 * @return 服务器文件地址
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public static String modifyFile(String fileId, String newLocalFileName,
			String extName, NameValuePair[] pair) throws Exception {
		// 建立连接
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = tracker.getStoreStorage(trackerServer, group_name);
		StorageClient1 s_clent1 = new StorageClient1(trackerServer,
				storageServer);
		int delCode = s_clent1.delete_file1(fileId);
		String file_id = null;
		if (delCode == 0) {
			file_id = s_clent1.upload_file1(group_name,newLocalFileName, extName, pair);
		}
		trackerServer.close();
		return file_id;
	}

	/**
	 * @description 根据流上传文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param inStream
	 * @param uploadFileName
	 * @param fileLength
	 * @return
	 * @throws IOException
	 * @since gadvice1.0.0
	 */
	public String uploadFile(InputStream inStream, String uploadFileName,
			long fileLength) throws IOException {
		byte[] fileBuff = getFileBuffer(inStream, fileLength);
		String fileId = "";
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			return fileId;
		}

		// 建立连接
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = tracker.getStoreStorage(trackerServer, group_name);
		StorageClient1 client = new StorageClient1(trackerServer, storageServer);

		// 设置元信息
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));

		// 上传文件
		try {
			fileId = client.upload_file1(group_name,fileBuff, fileExtName, metaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		trackerServer.close();
		return fileId;
	}

	/**
	 * @description 根据文件输入流上传文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param inStream
	 * @param fileLength
	 * @return
	 * @since gadvice1.0.0
	 */
	private byte[] getFileBuffer(InputStream inStream, long fileLength)
			throws IOException {
		byte[] buffer = new byte[256 * 1024];
		byte[] fileBuffer = new byte[(int) fileLength];
		int count = 0;
		int length = 0;

		while ((length = inStream.read(buffer)) != -1) {
			for (int i = 0; i < length; ++i) {
				fileBuffer[count + i] = buffer[i];
			}
			count += length;
		}
		return fileBuffer;
	}

	/**
	 * @description 根据文件流上传文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param groupName
	 *            组名，可为NULL
	 * @param inStream
	 * @param uploadFileName
	 * @param fileLength
	 *            上传文件的大小
	 * @return results[0]: groupName, results[1]: remoteFilename.
	 * @throws IOException
	 * @since gadvice1.0.0
	 */
	public String[] uploadFileByStream(String groupName, InputStream inStream,
			String uploadFileName, long fileLength) throws IOException {
		String[] results = null;
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			return results;
		}
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = tracker.getStoreStorage(trackerServer, group_name);;
		StorageClient1 client = new StorageClient1(trackerServer, storageServer);

		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));

		try {
			results = client.upload_file(group_name, fileLength,
					new UploadFileSender(inStream), fileExtName, metaList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		trackerServer.close();

		return results;
	}

	/**
	 * @description 上传文件回调类
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @version 1.0, 2013-11-2
	 * @see
	 * @since gadvice1.0
	 */
	private static class UploadFileSender implements UploadCallback {

		private InputStream inStream;

		public UploadFileSender(InputStream inStream) {
			this.inStream = inStream;
		}

		public int send(OutputStream out) throws IOException {
			int readBytes;
			while ((readBytes = inStream.read()) > 0) {
				out.write(readBytes);
			}
			return 0;
		}
	}

	/**
	 * @description 获取资源服务器地址
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @return
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public static String getFastSourceUrl() throws Exception {
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

	// 以下为测试方法
	private static void doTest() {

		try {
			String local_file_name = "D:/vinappend.jpg";
			NameValuePair[] pair = { new NameValuePair(upload_time,
					new Date().toString()) };
			String conFilePath = fdfsClient.class.getClassLoader()
					.getResource("fdfs_client.conf").getPath().toString();
			// String conf_filename = "/fdfs_client.conf";
			ClientGlobal.init(conFilePath);
			// // 配置trackergroup族
			// TrackerGroup group = new TrackerGroup(
			// new InetSocketAddress[] { new InetSocketAddress(
			// "192.168.0.106", 22122) });
			// TrackerClient client = new TrackerClient(group);
			// // 由tracker分析出一个可用 的storage链接
			// TrackerServer ts = client.getConnection();
			//
			// StorageServer storage = client.getStoreStorage(ts);
			// // System.out.println(storage.getSocket().);
			//
			// StorageClient1 s_clent1 = new StorageClient1(ts, storage);
			TrackerClient tracker = new TrackerClient();
			TrackerServer ts = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 s_clent1 = new StorageClient1(ts, storageServer);

			String file_id = s_clent1
					.upload_file1(local_file_name, "jpg", pair);
//			System.out.println(s_clent1.get_metadata1(file_id));
//			System.out.println(s_clent1.get_file_info1(file_id));
//			System.out.println(s_clent1.delete_file1(file_id));
			InetSocketAddress inetSockAddr = ts.getInetSocketAddress();
			String file_url = "http://"
					+ inetSockAddr.getAddress().getHostAddress();
			if (ClientGlobal.g_tracker_http_port != 80) {
				file_url += ":" + ClientGlobal.g_tracker_http_port;
			}
			file_url += "/" + file_id;
			if (ClientGlobal.g_anti_steal_token) {
				int time1 = (int) (System.currentTimeMillis() / 1000);
				String token = ProtoCommon.getToken(file_id, time1,
						ClientGlobal.g_secret_key);
				file_url += "?token=" + token + "&time1=" + ts;
			}
			System.out.println("file url: " + file_url);

			ts.close();

		} catch (Exception io) {
			io.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		doTest();
	}
}
