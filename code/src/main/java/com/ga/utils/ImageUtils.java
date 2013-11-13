package com.ga.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description 图片工具类
 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
 * @version 1.0, 2013-11-2
 * @see
 * @since girladvice1.0
 */
public class ImageUtils {

	/**
	 * @description 按标准缩放图片
	 * @author <a href="mailto:vinthuy@qq.com">胡瑞永</a>
	 * @param iconFile
	 * @param sourceSuffix
	 * @param dest
	 * @return
	 * @throws Exception
	 * @since social-commons1.0.0
	 */
	public static void zoomOutImage(MultipartFile iconFile,
			String sourceSuffix, File dest) throws Exception {
		BufferedImage originalImage = ImageIO.read(iconFile.getInputStream());
		double times = 0;
		double times1 = (double) originalImage.getWidth() / 100;
		double times2 = (double) originalImage.getHeight() / 100;
		if (times1 < times2 || times1 == times2) {
			times = times1;
		} else
			times = times2;
		if (times == 0)
			times = 1;
		int width = (int) (originalImage.getWidth() / times);
		int height = (int) (originalImage.getHeight() / times);
		BufferedImage newImage = new BufferedImage(width, height,
				originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		ImageIO.write(newImage, sourceSuffix, dest);
		g.dispose();
	}

}