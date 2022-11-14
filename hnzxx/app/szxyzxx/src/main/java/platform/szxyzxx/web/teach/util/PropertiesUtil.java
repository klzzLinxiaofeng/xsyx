package platform.szxyzxx.web.teach.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: yhc
 * @Date: 2020/9/24 15:58
 * @Description: 配置文件读取
 */
public class PropertiesUtil {
    static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static String fileName;
    private static String url;

    static {
        // 获取食堂接口信息
        fileName = "System.properties";
        url = PropertiesUtil.getProperty(fileName, "canteen.canteen.server.address") + PropertiesUtil.getProperty(fileName, "canteen.server.url");
        // 获取图书馆接口信息

    }


    /**
     * 根据 文件名称和key读取value
     *
     * @param fileName
     * @param key
     * @return
     */
    public static String getProperty(String fileName, String key) {
        Properties props = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            logger.error("读取配置信息失败: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据key读取value
     *
     * @param filePath
     * @param key
     * @return
     */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            logger.error("读取配置信息失败: {}", e.getMessage());
            return null;
        }
    }
}
