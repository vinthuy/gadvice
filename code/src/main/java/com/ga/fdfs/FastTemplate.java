package com.ga.fdfs;

import java.io.InputStream;
import org.csource.common.NameValuePair;

import com.ga.utils.StringUtils;

public class FastTemplate implements IFastTemplate {

	private FDFSConnectionFactory factory;

	public FDFSConnectionFactory getFactory() {
		return factory;
	}

	public void setFactory(FDFSConnectionFactory factory) {
		this.factory = factory;
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
		FDFSConnection connection = factory.getConnection();
		String fileId = null;
		if (StringUtils.isNullOrBlank(localFileName))
			throw new FdfsException("localFileName is null");
		if (StringUtils.isNullOrBlank(extName))
			throw new FdfsException("extName is null");
		if (connection == null)
			throw new FdfsException("connection is null");
		if (pair == null)
			throw new FdfsException("pair is null");
		fileId = connection.uploadFile(localFileName, extName, pair);
		connection.close();
		return fileId;
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
		FDFSConnection connection = factory.getConnection();
		if (StringUtils.isNullOrBlank(fileId))
			throw new FdfsException("localFileName is null");
		if (connection == null)
			throw new FdfsException("storageConnection is null");
		int code = connection.deleteFile(fileId);
		connection.close();
		return code;
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
		FDFSConnection connection = factory.getConnection();
		if (connection == null)
			throw new FdfsException("storageConnection is null");
		if (StringUtils.isNullOrBlank(fileId))
			throw new FdfsException("fileId is null");
		if (StringUtils.isNullOrBlank(newLocalFileName))
			throw new FdfsException("newLocalFileName is null");
		if (StringUtils.isNullOrBlank(extName))
			throw new FdfsException("extName is null");
		if (pair == null)
			throw new FdfsException("pair is null");
		fileId = connection.modifyFile(fileId, newLocalFileName, extName, pair);
		connection.close();
		return fileId;
	}

	/**
	 * @description 根据流上传文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param inStream
	 * @param uploadFileName
	 * @param fileLength
	 * @return
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public String uploadFile(InputStream inStream, String uploadFileName,
			long fileLength) throws Exception {
		FDFSConnection connection = factory.getConnection();
		if (connection == null)
			throw new FdfsException("storageConnection is null");
		if (StringUtils.isNullOrBlank(uploadFileName))
			throw new FdfsException("uploadFileName is null");
		if (inStream == null)
			throw new FdfsException("inStream is null");

		String fileId = connection.uploadFile(inStream, uploadFileName,
				fileLength);
		connection.close();
		return fileId;
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
	 * @throws Exception
	 * @since gadvice1.0.0
	 */
	public String[] uploadFileByStream(String groupName, InputStream inStream,
			String uploadFileName, long fileLength) throws Exception {
		FDFSConnection connection = factory.getConnection(groupName);
		if (connection == null)
			throw new FdfsException("storageConnection is null");
		if (inStream == null)
			throw new FdfsException("inStream is null");
		if (StringUtils.isNullOrBlank(groupName))
			throw new FdfsException("groupName is null");
		if (StringUtils.isNullOrBlank(uploadFileName))
			throw new FdfsException("uploadFileName is null");
		String[] results = connection.uploadFileByStream(groupName, inStream,
				uploadFileName, fileLength);
		connection.close(groupName);
		return results;
	}

}
