package com.ga.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.UploadCallback;

import com.ga.utils.StringUtils;

public class FDFSConnection {

	private static final String group_name = "group1";

	private StorageClient1 storageConnection = null;

	private TrackerServer trackerServer = null;

	private StorageServerInfo storageServerInfo = null;

	private FDFSConnectionFactory factory = null;

	public StorageServerInfo getStorageServerInfo() {
		return storageServerInfo;
	}

	public void setStorageServerInfo(StorageServerInfo storageServerInfo) {
		this.storageServerInfo = storageServerInfo;
	}

	public FDFSConnectionFactory getFactory() {
		return factory;
	}

	public TrackerServer getTrackerServer() {
		return trackerServer;
	}

	public void setTrackerServer(TrackerServer trackerServer) {
		this.trackerServer = trackerServer;
	}

	public StorageClient1 getStorageConnection() {
		return storageConnection;
	}

	public void setStorageConnection(StorageClient1 storageConnection) {
		this.storageConnection = storageConnection;
	}

	public void initFdfsClient() throws FileNotFoundException, IOException,
			MyException {
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
	public String uploadFile(String localFileName, String extName,
			NameValuePair[] pair) throws Exception {
		if (StringUtils.isNullOrBlank(localFileName))
			throw new FdfsException("localFileName is null");
		if (StringUtils.isNullOrBlank(extName))
			throw new FdfsException("extName is null");
		if (storageConnection == null)
			throw new FdfsException("storageConnection is null");
		if (pair == null)
			throw new FdfsException("pair is null");
		return storageConnection.upload_file1(localFileName, extName, pair);
	}

	/**
	 * @description 删除文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param fileId
	 * @return
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public int deleteFile(String fileId) throws Exception {
		if (StringUtils.isNullOrBlank(fileId))
			throw new FdfsException("localFileName is null");
		if (storageConnection == null)
			throw new FdfsException("storageConnection is null");
		return storageConnection.delete_file1(fileId);
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
	public String modifyFile(String fileId, String newLocalFileName,
			String extName, NameValuePair[] pair) throws Exception {
		if (storageConnection == null)
			throw new FdfsException("storageConnection is null");
		if (StringUtils.isNullOrBlank(fileId))
			throw new FdfsException("fileId is null");
		if (StringUtils.isNullOrBlank(newLocalFileName))
			throw new FdfsException("newLocalFileName is null");
		if (StringUtils.isNullOrBlank(extName))
			throw new FdfsException("extName is null");
		if (pair == null)
			throw new FdfsException("pair is null");
		int delCode = storageConnection.delete_file1(fileId);
		String file_id = null;
		if (delCode == 0) {
			file_id = storageConnection.upload_file1(group_name,
					newLocalFileName, extName, pair);
		}
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
		if (storageConnection == null)
			throw new FdfsException("storageConnection is null");
		if (StringUtils.isNullOrBlank(uploadFileName))
			throw new FdfsException("uploadFileName is null");
		if (inStream == null)
			throw new FdfsException("inStream is null");

		byte[] fileBuff = getFileBuffer(inStream, fileLength);
		String fileId = "";
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			return fileId;
		}

		// 设置元信息
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));
		// 上传文件
		try {
			fileId = storageConnection.upload_file1(group_name, fileBuff,
					fileExtName, metaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (inStream == null)
			throw new FdfsException("inStream is null");

		byte[] buffer = new byte[1024];
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
		if (storageConnection == null)
			throw new FdfsException("storageConnection is null");
		if (inStream == null)
			throw new FdfsException("inStream is null");
		if (StringUtils.isNullOrBlank(groupName))
			throw new FdfsException("groupName is null");
		if (StringUtils.isNullOrBlank(uploadFileName))
			throw new FdfsException("uploadFileName is null");

		String[] results = null;
		String fileExtName = "";
		if (uploadFileName.contains(".")) {
			fileExtName = uploadFileName.substring(uploadFileName
					.lastIndexOf(".") + 1);
		} else {
			return results;
		}

		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", uploadFileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength",
				String.valueOf(fileLength));

		try {
			results = storageConnection.upload_file(group_name, fileLength,
					new UploadFileSender(inStream), fileExtName, metaList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	public void close() {
		if (trackerServer != null) {
			getOwnerFactory().releaseConnection(trackerServer);
		}
	}

	public void close(String groupName) {
		if (storageServerInfo != null) {
			getOwnerFactory().releaseConnection(storageServerInfo);
		}
	}

	public FDFSConnectionFactory getOwnerFactory() {
		return factory;
	}

	public void setFactory(FDFSConnectionFactory factory) {
		this.factory = factory;
	}
}
