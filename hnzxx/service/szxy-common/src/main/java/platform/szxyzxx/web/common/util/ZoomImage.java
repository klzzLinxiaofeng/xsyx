/**   
 * ZoomImage.java Create on 2013-5-10 上午10:11:12   
 *   
 */
package platform.szxyzxx.web.common.util;

/**   
 * @author clouds
 * @version 1.0   
 */
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 一个实用的图像放大缩小程序
 * 
 * @author YuLimin
 */
public class ZoomImage {
	private static Component component = new Canvas();

	/**
	 * 测试用例
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		long t1 = System.currentTimeMillis();
//		ZoomImage zoomImage = new ZoomImage();
//		// 缩小四倍
//
//		zoomImage.createZoomSizeImage("C:/2.jpg", "C:/2_67.jpg", 0.67);
//		zoomImage.createZoomSizeImage("C:/2.jpg", "C:/2_59.jpg", 0.59);
//		System.out.println(System.currentTimeMillis() - t1);
//		// 放大四倍
//		// zoomImage.zoom("C:/",4,false);
	}

	public void createZoomSizeImage(String originalFileName,
			String targetFileName, double zoomRatio) throws IOException {
		File file = new File(originalFileName);
		BufferedImage image = ImageIO.read(file);
		int width = new Double(image.getWidth(null) * zoomRatio).intValue();
		int height = new Double(image.getHeight(null) * zoomRatio).intValue();
		AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(
				width, height);
		FilteredImageSource filteredImageSource = new FilteredImageSource(
				image.getSource(), areaAveragingScaleFilter);
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = bufferedImage.createGraphics();
		graphics.drawImage(component.createImage(filteredImageSource), 0, 0,
				null);
		try {
			File targetFile = new File(targetFileName);
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			ImageIO.write(bufferedImage, "jpg", new File(targetFileName));
			graphics.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 按比例进行放大缩小图像，zoomRatio = 1为原大，zoomRatio > 1为放大，zoomRatio < 1 为缩小
	 * 
	 * @param fileName
	 * @param fileNameTarget
	 * @param zoomRatio
	 * @throws Exception
	 */
	public static BufferedImage createZoomSizeImage(BufferedImage image,
			String targetFileName, double zoomRatio) {
		int width = new Double(image.getWidth(null) * zoomRatio).intValue();
		int height = new Double(image.getHeight(null) * zoomRatio).intValue();
		AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(
				width, height);
		FilteredImageSource filteredImageSource = new FilteredImageSource(
				image.getSource(), areaAveragingScaleFilter);
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = bufferedImage.createGraphics();
		graphics.drawImage(component.createImage(filteredImageSource), 0, 0,
				null);

		try {
			File targetFile = new File(targetFileName);
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			ImageIO.write(bufferedImage, "jpg", new File(targetFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
