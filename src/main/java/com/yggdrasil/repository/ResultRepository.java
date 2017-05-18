package com.yggdrasil.repository;

import com.yggdrasil.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yggdrasil on 2017/5/4.
 */
public interface ResultRepository extends JpaRepository<Result,Integer> {
    List<Result> findByDataName(String dataName);
}
