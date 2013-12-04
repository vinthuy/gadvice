package com.ga.fdfs;

import java.io.InputStream;

import org.csource.common.NameValuePair;

import com.ga.utils.StringUtils;

public interface IFastTemplate {

	public String uploadFile(String localFileName, String extName,
			NameValuePair[] pair) throws Exception;

	public int deleteFile(String fileId) throws Exception;

	public String modifyFile(String fileId, String newLocalFileName,
			String extName, NameValuePair[] pair) throws Exception;

	public String uploadFile(InputStream inStream, String uploadFileName,
			long fileLength) throws Exception;

	public String[] uploadFileByStream(String groupName, InputStream inStream,
			String uploadFileName, long fileLength) throws Exception;

}
