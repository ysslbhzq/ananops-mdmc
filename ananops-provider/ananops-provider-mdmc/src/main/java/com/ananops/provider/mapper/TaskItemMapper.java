package com.ananops.provider.mapper;

import com.ananops.provider.base.MyMapper;
import com.ananops.provider.model.domain.TaskItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface TaskItemMapper extends MyMapper<TaskItem> {
    List<TaskItem> selectByMaintainerIdAndStatus(@Param("maintainer_id") Long maintainer_id, @Param("status") Integer status);
}