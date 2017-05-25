package com.yggdrasil.Controller;

import com.yggdrasil.entity.Record;
import com.yggdrasil.repository.RecordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yggdrasil on 2017/5/25.
 */
@RestController
@RequestMapping("/record")

public class RecordController {
    @Resource
    private RecordRepository recordRepository;
    @RequestMapping("/getAll")
    public List<Record> getAllRecord(){
        return recordRepository.findAll();
    }
}
