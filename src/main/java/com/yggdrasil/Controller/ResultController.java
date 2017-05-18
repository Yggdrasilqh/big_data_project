package com.yggdrasil.Controller;

import com.sun.org.apache.regexp.internal.RE;
import com.yggdrasil.entity.Result;
import com.yggdrasil.repository.ResultRepository;
import org.apache.hadoop.util.hash.Hash;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yggdrasil on 2017/5/4.
 */
@RestController
@RequestMapping("/result")
public class ResultController {
    @Resource
    private
    ResultRepository resultRepository;

    private static Logger log = Logger.getLogger(ResultController.class);

    @RequestMapping("/getByDataName")
    public @ResponseBody HashMap<List<String>,List<String>> getByDataName(String dataName) {

        List<String[]> tempResults = new ArrayList<>();

        resultRepository.findByDataName(dataName).stream()
                .map(Result::getTaskResult)
                .map(a -> a.split("/"))
                .forEach(tempResults::add);
        HashMap<List<String>, List<String>> results = new HashMap<>();
        for (String[] result : tempResults) {
            List<String> name = new ArrayList<>();
            List<String> number = new ArrayList<>();
            for (String s : result) {
                String[] temp = s.split(",");
                name.add("\"" +temp[0] + "\"");
                number.add(temp[1]);
            }
            results.put(name, number);
        }
        log.info(results);
        return results;
    }
}
