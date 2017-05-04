package com.yggdrasil.service;

import com.yggdrasil.tools.HadoopConnector;
import com.yggdrasil.tools.RunShell;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yggdrasil on 2017/4/20.
 */
@Service
public class HadoopService {
    @Resource
    private HadoopConnector hadoopConnector;

    public boolean calculate(String localPath, String hdfsPath) {

        //上传原始数据
        hadoopConnector.uploadFile(localPath, hdfsPath);

        //计算并下载数据
        //RunShell.run("-------jar 12345678910------");
        //hadoopConnector.downloadFile("" ,"" );

        return true;
    }
}
