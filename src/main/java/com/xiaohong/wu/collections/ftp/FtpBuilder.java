package com.xiaohong.wu.collections.ftp;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/10 15:13
 **/
public class FtpBuilder {

    public static SftpClient buildSftp() {
        return SftpClient.getInstance();
    }

    public static FtpClient buildFtp(){
        return new FtpClient();
    }



    public static void main(String[] args) throws Exception {


    }

}
