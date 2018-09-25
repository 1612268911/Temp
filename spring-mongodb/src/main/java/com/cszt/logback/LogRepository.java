package com.cszt.logback;

import org.springframework.data.mongodb.repository.MongoRepository;

//保存日志的持久层接口
public interface LogRepository extends MongoRepository<MyLog, String> {
}
