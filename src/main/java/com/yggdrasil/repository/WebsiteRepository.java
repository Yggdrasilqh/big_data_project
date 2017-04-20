package com.yggdrasil.repository;

import com.yggdrasil.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yggdrasil on 2017/3/23.
 */
@Repository
public interface WebsiteRepository extends JpaRepository<Website,String> {
}
