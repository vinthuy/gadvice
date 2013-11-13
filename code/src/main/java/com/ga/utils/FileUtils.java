package com.ga.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @description 文件工具类
 * @author <a href="mailto:bin.du@daw.so">杜斌</a>
 * @version 1.0, 2013-8-26
 * @see
 * @since social-commons1.0
 */
public class FileUtils {

	private static final int BUFFER_SIZE = 1024;
	public static final String UP_PATH_ROOT = "E:\\tmp";
	
	

	/**
	 * 获取扩文件类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");

		return fileName.substring(pos);
	}

	/**
	 * 复制文件
	 * 
	 * @param src
	 * @param dst
	 */
	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @description  复制文件
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param fin
	 * @param dst 
	 * @throws Exception 
	 * @since girladvice1.0
	 */
	public static void copy(InputStream fin, File dst) throws Exception {
	
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(fin,
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.flush();
					out.close();
				}
			}
		
	}

	/**
	 * 删除原有上传文件
	 * 
	 * @param f
	 */
	public void deleteFile(File old) {
		if (old.isDirectory()) {
			// System.out.println(oldPath + "是文件夹--");
			File[] files = old.listFiles();
			for (File file : files) {
				deleteFile(file);
			}
			old.delete();
		} else {
			old.delete();
		}
	}
	

	/**
	 * 上传文件
	 * 
	 * @param upfolder
	 *            上传文件夹
	 * @param filexFileName
	 *            上传文件名
	 * @param filex
	 *            上传的临时文件
	 * @return 已上传文件
	 */
	public static File upload(String upfolder, String filexFileName, File filex) {

		String UP_PATH = UP_PATH_ROOT + "/" + upfolder + "/";

		String fileExName = FileUtils.getExtention(filexFileName);

		String FileNameBefore = filexFileName.substring(0,
				filexFileName.lastIndexOf("."));
		long FileNameMiddle = System.currentTimeMillis();

		String dtsFileName = new StringBuffer(FileNameBefore)
				.append(FileNameMiddle).append(fileExName).toString();

		File dstFile = new File(UP_PATH + dtsFileName);

		if (!dstFile.exists()) {
			dstFile.getParentFile().mkdirs();
			try {
				dstFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 删除原有文件
		 */

		File paretnFile = dstFile.getParentFile();
		if (paretnFile.exists()) {
			File[] upfilelist = paretnFile.listFiles();
			for (File f : upfilelist) {
				if (f.getName().startsWith(FileNameBefore)
						&& f.getName().endsWith(fileExName)) {
					f.delete();
				}
			}
		}

		FileUtils.copy(filex, dstFile);

		if (filex.exists()) {
			filex.delete();
		}
		return dstFile;
	}
/**
 * 
 * @description 获取文件名部分
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @param fileName
 * @return 
 * @since girladvice1.0
 */
	public static String getMainFileName(String fileName) {
		
		int pos = fileName.lastIndexOf(".");
		if(pos > 0)
		{
			return fileName.substring(0,pos);
		}
		return null;
	}
/**
 * 
 * @description 检查是否是系统头像
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @param fileName
 * @return 
 * @since girladvice1.0
 */
	public static boolean checkSystemHead(String fileName)
	{
		try {
			int systemHeadNum = Integer.parseInt(getMainFileName(fileName));
			if(systemHeadNum>0 && systemHeadNum <17)
			{
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
}
