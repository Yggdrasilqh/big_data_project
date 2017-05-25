package com.yggdrasil.tools;

import com.yggdrasil.entity.Record;
import com.yggdrasil.entity.Student;
import com.yggdrasil.entity.Website;
import com.yggdrasil.repository.RecordRepository;
import com.yggdrasil.repository.StudentRepository;
import com.yggdrasil.repository.WebsiteRepository;
import com.yggdrasil.service.HadoopService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yggdrasil on 2017/3/23.
 */
@Component
//@EnableAutoConfiguration
public class GenerateData {
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private WebsiteRepository websiteRepository;
    @Resource
    private HadoopService hadoopService;
    @Resource
    private RecordRepository recordRepository;
    private static Logger LOG = Logger.getLogger(GenerateData.class);

    @Transactional
    public void generate(List<String> sNoList, List<String> wNoList, Date startDate, Date endDate, int length,String path) throws Exception {

        List<Student> students;
        List<Website> websites;
        if(sNoList.size() > 0 || wNoList.size() > 0) {
            students = studentRepository.findAll(sNoList);
            websites = websiteRepository.findAll(wNoList);
        } else {
            students = studentRepository.findAll();
            websites = websiteRepository.findAll();
        }
        MyRandom stuRandom = new MyRandom();
        MyRandom webRandom = new MyRandom();

        File inputFile = new File(path + new Date().getTime());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFile));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Student student;
        Website website;
        Date date;
        long dateInterval = (endDate.getTime()-startDate.getTime())/length;
        for(long tempDate = startDate.getTime() ; tempDate < endDate.getTime() ; tempDate += dateInterval){
            date = stuRandom.getRandomDate(new Date(tempDate), new Date(tempDate+dateInterval));
            student = stuRandom.getRandomNumberIn(students);
            website = webRandom.getRandomNumberIn(websites);
            bufferedWriter.write(student.getSno() + "," +
                    student.getSname() + "," +
                    student.getSsex() + "," +
                    website.getUrl() + "," +
                    website.getWname() + "," +
                    simpleDateFormat.format(date));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        LOG.info("已将"+length+"条数据写入文件"+inputFile.getAbsolutePath());
        recordRepository.saveAndFlush(new Record(inputFile.getName(),length,null,0));
        new Thread(() -> {
            //TODO: 目前calculate为测试方法，正式方法为下面被注释的
//            hadoopService.calculate(inputFile.getAbsolutePath(), "/input" + inputFile.getName(), inputFile.getName());
            hadoopService.calculate(inputFile.getName());
        }).start();
//        hadoopService.calculate(inputFile.getAbsolutePath(),"/input"+inputFile.getName(),inputFile.getName());
    }
}