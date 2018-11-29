package com.cszt.repository;

import com.cszt.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author lilin
 * @create 2018/11/19 14:57
 * description:
 */
public interface UserRepository extends ElasticsearchRepository<User,Integer> {

}