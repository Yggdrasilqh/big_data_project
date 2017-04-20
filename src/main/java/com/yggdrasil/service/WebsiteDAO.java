package com.yggdrasil.service;
import com.yggdrasil.entity.Website;
import com.yggdrasil.repository.WebsiteRepository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yggdrasil on 2017/4/20.
 */
@Service
public class WebsiteDAO {
    @Resource
    private WebsiteRepository websiteRepository;

    public List<Website> findAll() {
        return websiteRepository.findAll();
    }
}
