package com.xiaohong.wu.collections.ftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Ftp 上传 下载文件工具类
 * 兼容ftp和sftp两种模式
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/10 9:47
 **/
public class SftpClient implements AutoCloseable {

    private static final String STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
    private static final String NO = "no";
    private static final int TIMEOUT = 100000;
    private static final String SFTP = "sftp";

    private volatile static SftpClient sftpClient;

    /**
     * sftp session
     */
    private Session session;
    /**
     * sftp channel
     */
    private ChannelSftp channelSftp;

    private SftpClient() {
    }

    /**
     * 单例模式
     *
     * @return {@link SftpClient}
     */
    public static SftpClient getInstance() {
        if (sftpClient == null) {
            synchronized (SftpClient.class) {
                if (sftpClient == null) {
                    sftpClient = new SftpClient();
                }
            }
        }
        return sftpClient;
    }

    /**
     * 暴露出去的链接方法
     *
     * @param host     域名
     * @param port     端口
     * @param userName 用户名
     * @param pwd      密码
     * @return {@link SftpClient}
     * @throws JSchException 异常
     */
    public SftpClient connection(String host, String port, String userName, String pwd) throws JSchException {
        return getSession(host, port, userName, pwd).getFtpUtil();
    }

    /**
     * 暴露出去的链接方法
     *
     * @param host       域名
     * @param port       端口
     * @param userName   用户名
     * @param pwd        密码
     * @param privateKey 私钥
     * @return {@link SftpClient}
     * @throws JSchException 异常
     */
    public SftpClient connection(String host, String port, String userName, String pwd, String privateKey) throws JSchException {
        return getSession(host, port, userName, pwd, privateKey).getFtpUtil();
    }

    /**
     * 暴露出去的链接方法
     *
     * @param host     域名
     * @param port     端口
     * @param userName 用户名
     * @param pwd      密码
     * @param timeOut  超时时间
     * @return {@link SftpClient}
     * @throws JSchException 异常
     */
    public SftpClient connection(String host, String port, String userName, String pwd, Integer timeOut) throws JSchException {
        return getSession(host, port, userName, pwd, timeOut).getFtpUtil();
    }

    /**
     * 暴露出去的链接方法
     *
     * @param host       域名
     * @param port       端口
     * @param userName   用户名
     * @param pwd        密码
     * @param privateKey 私钥
     * @param timeOut    超时时间
     * @return {@link SftpClient}
     * @throws JSchException 异常
     */
    public SftpClient connection(String host, String port, String userName, String pwd, String privateKey, Integer timeOut) throws JSchException {
        return getSession(host, port, userName, pwd, privateKey, timeOut).getFtpUtil();
    }

    /**
     * 从sftp服务器指定路径下下载文件到本地
     *
     * @param path      路径
     * @param localPath 本地路径
     */
    public void getSftpFile(String path, String localPath) {
        try {
            channelSftp.get(path, localPath);
            this.close();
        } catch (Exception e) {
            throw new RuntimeException("下载文件失败", e);
        }
    }

    /**
     * 从sftp服务器指定路径下下载文件到内存
     *
     * @param path 目标文件路径
     * @return 目标文件
     */
    public String getSftpFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(channelSftp.get(path), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param path
     * @return
     */
    public byte[] getSftpFileToByte(String path) {
        byte[] result = null;
        try (InputStream inputStream = channelSftp.get(path)) {
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int rc = 0;
            while ((rc = inputStream.read(bytes, 0, bytes.length)) > 0) {
                baos.write(bytes, 0, rc);
            }
            result = baos.toByteArray();
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从sftp上获取指定文件，并返回集合
     *
     * @param path 路径
     * @return {@link List<String>}
     */
    public List<String> getSftpFileToList(String path) {
        List<String> list = new LinkedList<>();
        try (InputStream inputStream = channelSftp.get(path)) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String str;
            while ((str = bf.readLine()) != null) {
                list.add(str);
            }
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 上传文件到远程服务器
     *
     * @param localPath  本地路径
     * @param remotePath 远程路径
     */
    public void put(String localPath, String remotePath) {
        try {
            channelSftp.put(localPath, remotePath);
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传指定文件
     *
     * @param source     源文件
     * @param remotePath 上传路径
     */
    public void upload(String source, String remotePath) {
        try {
            channelSftp.put(source, remotePath);
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件
     *
     * @param remoteFile 远程文件
     */
    public void delFile(String remoteFile) {
        if (isConnect()) {
            try {
                channelSftp.rm(remoteFile);
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SftpClient getFtpUtil() throws JSchException {
        if (session != null && !session.isConnected()) {
            session.connect();
        }
        if (channelSftp == null || !channelSftp.isConnected()) {
            channelSftp = (ChannelSftp) session.openChannel(SFTP);
            channelSftp.connect();
        }
        return this;
    }

    private boolean isConnect() {
        return session.isConnected() && channelSftp.isConnected();
    }

    /**
     * @param host     域名
     * @param port     端口
     * @param userName 用户名
     * @param pwd      密码
     * @return {@link Session}
     * @throws JSchException 异常
     */
    private SftpClient getSession(String host, String port, String userName, String pwd) throws JSchException {
        return getSession(host, port, userName, pwd, null, TIMEOUT);
    }

    /**
     * @param host       域名
     * @param port       端口
     * @param userName   用户名
     * @param pwd        密码
     * @param privateKey 撕私钥
     * @return {@link Session}
     * @throws JSchException 异常
     */
    private SftpClient getSession(String host, String port, String userName, String pwd, String privateKey) throws JSchException {
        return getSession(host, port, userName, pwd, privateKey, TIMEOUT);
    }

    /**
     * @param host     域名
     * @param port     端口
     * @param userName 用户名
     * @param pwd      密码
     * @param timeOut  超时时间
     * @return {@link Session}
     * @throws JSchException 异常
     */
    private SftpClient getSession(String host, String port, String userName, String pwd, Integer timeOut) throws JSchException {
        return getSession(host, port, userName, pwd, null, timeOut);
    }

    /**
     * 获取sftp session
     *
     * @param host       域名
     * @param port       端口
     * @param userName   用户名
     * @param pwd        密码
     * @param privateKey 私钥
     * @param timeOut    超时时间
     * @return {@link Session}
     * @throws JSchException {@link JSchException} JSch异常
     */
    private SftpClient getSession(String host, String port, String userName, String pwd, String privateKey, Integer timeOut) throws JSchException {
        JSch jSch = new JSch();

        if (StringUtils.isNotBlank(privateKey)) {
            jSch.addIdentity(privateKey);
        }
        Session session = jSch.getSession(userName, host, Integer.parseInt(port));
        session.setTimeout(timeOut);
        if (StringUtils.isNotBlank(pwd)) {
            session.setPassword(pwd);
        }
        Properties config = new Properties();
        config.put(STRICT_HOST_KEY_CHECKING, NO);
        session.setConfig(config);
        session.connect();
        this.session = session;
        return this;
    }


    @Override
    public void close() throws Exception {
        if (channelSftp != null && channelSftp.isConnected()) {
            channelSftp.quit();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public static void main(String[] args) throws JSchException {
        String sftpFile = new SftpClient().connection("", "", "", "").getSftpFile("");
    }
}
