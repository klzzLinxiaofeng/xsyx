package platform.resource.main;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import platform.resource.main.sys.SysContants;
import framework.generic.storage.Storage;
import framework.generic.storage.StorageResult;
import framework.generic.storage.StorageStatusCode;

public class DataSynPractitioner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private ClassPathXmlApplicationContext context = null;

	private Storage storage = null;

	public DataSynPractitioner() {
		try {
			System.out.println("服务启动中");
			context = new ClassPathXmlApplicationContext(
					new String[] { "import_services.xml" });
			context.start();
			storage = (Storage) context.getBean("storage");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		copyDirectiory(SysContants.RESOURCE_SRC_PATH);
	}

	// 复制文件夹
	public void copyDirectiory(String sourceDir) 
			throws IOException {
		// 获取源文件夹当前下的文件或目录
		logger.info("源文件路径sourceDir----->{}", sourceDir);
		File[] file = (new File(sourceDir)).listFiles();
		int sucCount = 0;
		int errorCount = 0;
		if (file != null) {
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 源文件
					File sourceFile = file[i];
					// 目标文件
					String targetPath = (sourceFile.getAbsolutePath()).replace(SysContants.RESOURCE_SRC_PATH_PREFIX, "").replace(sourceFile.getName(), "").replace("\\", "/");
					logger.info("目标文件路径sourceDir----->{}", targetPath);
//					logger.info("上传成功------文件路径为：{}", sourceFile.getAbsoluteFile());
//					logger.error("上传失败------文件路径为：{}", sourceFile.getAbsoluteFile());
					StorageResult result = storage.upload(targetPath, sourceFile.getName(), sourceFile);
					if (result != null) {
						if (StorageStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
							if (logger.isInfoEnabled()) {
								logger.info("上传成功------文件路径为：{}", sourceFile.getAbsoluteFile());
								sucCount++;
							}
							continue;
						}
					} 
					if (logger.isErrorEnabled()) {
						logger.error("上传失败------文件路径为：{}", sourceFile.getAbsoluteFile());
						errorCount++;
					}
				}
				if (file[i].isDirectory()) {
					String dir1 = sourceDir + File.separator + file[i].getName();
					copyDirectiory(dir1);
				}
			}
			logger.info("上传成功总数:{}", sucCount);
			logger.error("上传失败总数:{}", errorCount);
		}
	}

}
