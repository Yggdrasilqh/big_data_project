package com.yggdrasil.tools;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;


/**
 * Created by yggdrasil on 2017/4/20.
 */
public class HadoopConnector {
    private FileSystem fileSystem;
    private String path;

    public HadoopConnector(String url) {
        path = url;
        try {
            fileSystem = FileSystem.get(new URI(path), new Configuration(),"root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean makeDir(String DIR){
        try {
            //创建文件夹
            return fileSystem.mkdirs(new Path(DIR));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean uploadFile(String localPath,String hdfsPath) {
        try {
            FSDataOutputStream out = fileSystem.create(new Path(hdfsPath));
            FileInputStream in = new FileInputStream(new File(localPath));
            IOUtils.copyBytes(in, out, 1024, true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean downloadFile(String localPath,String hdfsPath) {
        try {
            FSDataInputStream inputStream = fileSystem.open(new Path(path + hdfsPath));
            FileOutputStream outputStream = new FileOutputStream(localPath);
            IOUtils.copyBytes(inputStream, outputStream, 1024, true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
