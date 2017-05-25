package com.yggdrasil.repository;

import com.yggdrasil.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yggdrasil on 2017/5/25.
 */
public interface RecordRepository extends JpaRepository<Record, String> {

}
