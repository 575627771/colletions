package com.xiaohong.wu.collections.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/10 15:13
 **/
public class FtpClient implements AutoCloseable {


    private FTPClient ftpClient;

    /**
     * 获取ftp连接
     *
     * @param host     域名
     * @param port     端口
     * @param userName 用户名
     * @param pwd      密码
     * @return {@link FTPClient}
     * @throws IOException io异常
     */
    private FtpClient getFtpClient(String host, String port, String userName, String pwd) throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(host, Integer.parseInt(port));
        ftpClient.login(userName, pwd);
        int replyCode = ftpClient.getReplyCode();
        ftpClient.setDataTimeout(60000);
        //设置上传缓存大小
        ftpClient.setBufferSize(1024);
        //设置编码
        ftpClient.setControlEncoding("UTF-8");
        //设置文件类型
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftpClient.disconnect();
            throw new RuntimeException("FTP server refuse connect!");
        }
        return this;
    }



    @Override
    public void close() throws Exception {
        if (ftpClient != null) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}
