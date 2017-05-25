package com.yggdrasil.service;

import com.yggdrasil.entity.Record;
import com.yggdrasil.entity.Result;
import com.yggdrasil.repository.RecordRepository;
import com.yggdrasil.repository.ResultRepository;
import com.yggdrasil.tools.HadoopConnector;
import com.yggdrasil.tools.RunShell;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yggdrasil on 2017/4/20.
 */
@Service
public class HadoopService {
    @Resource
    private HadoopConnector hadoopConnector;
    @Resource
    private ResultRepository resultRepository;
    @Resource
    private RecordRepository recordRepository;

    public void calculate(String localPath, String hdfsPath,String dataName){

        //上传原始数据
        hadoopConnector.uploadFile(localPath, hdfsPath);
        long time = new Date().getTime();
        //计算并下载数据
        hdfsPath += "/out" + time;
        RunShell.run("hadoop jar StudentFromTime-1.0-SNAPSHOT-jar-with-dependencies.jar " + localPath + " " + hdfsPath);
        hadoopConnector.downloadFile(
                localPath,
                hdfsPath);
        try {
            sendDateToDatabase(localPath, dataName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculate(String name){
        try {
            Thread.sleep(16000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Record record = recordRepository.findOne(name);
        record.setStatus(1);
        recordRepository.saveAndFlush(record);
    }

    public void sendDateToDatabase(String localPath,String dataName) throws IOException, ClassNotFoundException {

        for (int i = 1; i <= 10; i++) {
            BufferedReader bufferedReader = this.getBufferedReader(localPath, i);
            String line;
            String resultContent = "";
            while ((line = bufferedReader.readLine()) != null) {
                Result result = new Result();
                line = line.replaceAll("\t", ",");
                resultContent += line + "/";
                result.setDataName(dataName);
                result.setTaskResult(line);
                resultRepository.saveAndFlush(result);
            }
        }
    }


    public BufferedReader getBufferedReader(String localPath, int id) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(localPath + id + "/part-0000.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        return new BufferedReader(inputStreamReader);
    }

}
