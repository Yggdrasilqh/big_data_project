package com.yggdrasil.tools;

import com.yggdrasil.entity.Student;
import com.yggdrasil.entity.Website;
import com.yggdrasil.repository.StudentRepository;
import com.yggdrasil.repository.WebsiteRepository;
import com.yggdrasil.service.HadoopService;
import com.yggdrasil.tools.MyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        MyRandom<Student> stuRandom = new MyRandom<>();
        MyRandom<Website> webRandom = new MyRandom<>();

        File inputFile = new File(path + new Date().getTime());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFile));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Student student;
        Website website;
        Date date;
        long dateInterval = (startDate.getTime()-endDate.getTime())/length;
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

        hadoopService.calculate(inputFile.getAbsolutePath(),"/input"+inputFile.getName());
    }
}