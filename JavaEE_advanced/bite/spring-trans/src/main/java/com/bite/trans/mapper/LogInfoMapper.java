package com.bite.trans.mapper;

import com.bite.trans.model.LogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogInfoMapper {
    @Insert("insert into log_info(user_name, op) values(#{userName},#{op})")
    Integer insertLog(LogInfo logInfo);
}
