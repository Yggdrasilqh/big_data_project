package com.yggdrasil.Controller;

import com.yggdrasil.entity.RandomList;
import com.yggdrasil.entity.Student;
import com.yggdrasil.entity.Website;
import com.yggdrasil.repository.WebsiteRepository;
import com.yggdrasil.service.StudentDAO;
import com.yggdrasil.tools.GenerateData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yggdrasil on 2017/4/6.
 */
@Controller
public class RandomController {

    @Resource
    private
    StudentDAO studentDAO;
    @Resource
    private WebsiteRepository websiteRepository;
    @Resource
    private GenerateData generateData;
    @Value("${randomDataPath}")
    private String path;

    @GetMapping("/random")
    public String random(Model model) {
        List<Student> students = studentDAO.findAll();
        List<Website> websites = websiteRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("websites", websites);
        return "random";
    }

//    @PostMapping("/random")
//    public boolean random(RandomList randomList) throws Exception {
//        generateData.generate(randomList.getStuList(), randomList.getUrlList(), randomList.getStartTime(), randomList.getEndTime(), 99, path);
//        return true;
//    }

    @PostMapping("/random")
    public @ResponseBody
    String random(@RequestBody RandomList randomList) throws Exception {
        if (randomList.getLength() > 0 && randomList.getStartTime().getTime() < randomList.getEndTime().getTime()) {
            generateData.generate(randomList.getStuList(),
                    randomList.getWebList(),
                    randomList.getStartTime(),
                    randomList.getEndTime(),
                    randomList.getLength(),
                    path);
            return "success";
        } else {
            return "error";
        }
    }
}
