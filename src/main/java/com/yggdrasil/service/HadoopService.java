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
    HadoopConnector hadoopConnector;

    public boolean calculate(String localPath, String hdfsPath) {
        //TODO
        RunShell.run("..");
        hadoopConnector.downloadFile(hdfsPath, localPath);
        return true;
    }

}
