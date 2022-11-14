package platform.szxyzxx.erweima.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPInputStream;
import ch.ethz.ssh2.SCPOutputStream;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class RemoteCommandUtil {

    //目标服务器端口,默认
    private static int port = 8587;
    private static Connection conn = null;

    /**
     * 登录服务器主机
     *
     * @param ip       主机IP
     * @param userName 用户名
     * @param userPwd  密码
     * @return 登录对象
     */
    public static Connection login(String ip, String userName, String userPwd) {
        boolean flg = false;
        Connection conn = null;
        try {
            conn = new Connection(ip,port);
            //连接
            conn.connect();
            //认证
            flg = conn.authenticateWithPassword(userName, userPwd);
            if (flg) {
                log.info("连接成功");
                return conn;
            }
        } catch (IOException e) {
            log.error("连接失败" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 实现下载服务器上的文件到本地指定目录
     *
     * @param conn      SSH连接信息
     * @param basePath  服务器上的文件地址/home/img
     * @param localPath 本地路径：D:\
     * @throws IOException
     */
    public void downloadFile(Connection conn, String fileName, String basePath, String localPath) throws IOException {
        SCPClient scpClient = conn.createSCPClient();
        try {
            SCPInputStream sis = scpClient.get(basePath + "/" + fileName);
            File f = new File(localPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            File newFile = new File(localPath + fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] b = new byte[4096];
            int i;
            while ((i = sis.read(b)) != -1) {
                fos.write(b, 0, i);
            }
            fos.flush();
            fos.close();
            sis.close();
            log.info("下载完成");
        } catch (IOException e) {
            log.info("文件不存在或连接失败");
            e.printStackTrace();
        } finally {
            log.info("服务关闭");
            closeConn();
        }
    }


    /**
     * 上传文件到服务器
     *
     * @param conn                  SSH连接信息
     * @param f                     文件对象
     * @param remoteTargetDirectory 上传路径
     * @param mode                  默认为null
     */
    public void uploadFile(Connection conn, File f, String remoteTargetDirectory, String mode) {
        try {
            SCPClient scpClient = new SCPClient(conn);
            SCPOutputStream os = scpClient.put(f.getName(), f.length(), remoteTargetDirectory, mode);
            byte[] b = new byte[4096];
            FileInputStream fis = new FileInputStream(f);
            int i;
            while ((i = fis.read(b)) != -1) {
                os.write(b, 0, i);
            }
            os.flush();
            fis.close();
            os.close();
            log.info("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
    }


    /**
     * 关闭服务
     */
    public static void closeConn() {
        if (null == conn) {
            return;
        }
        conn.close();
    }
}
