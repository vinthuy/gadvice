package com.ga.fdfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.csource.fastdfs.UploadCallback;

/**
 * @description 上传文件回调类
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see
 * @since gadvice1.0
 */
public class UploadFileSender implements UploadCallback {

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

