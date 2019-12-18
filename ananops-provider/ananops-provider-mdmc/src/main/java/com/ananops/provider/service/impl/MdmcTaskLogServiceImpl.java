package com.ananops.provider.service.impl;

import com.ananops.provider.mapper.MdmcTaskItemLogMapper;
import com.ananops.provider.mapper.MdmcTaskLogMapper;
import com.ananops.provider.model.domain.MdmcTaskItemLog;
import com.ananops.provider.model.domain.MdmcTaskLog;
import com.ananops.provider.model.dto.MdmcTaskItemLogDto;
import com.ananops.provider.model.dto.MdmcTaskLogDto;
import com.ananops.provider.service.MdmcTaskLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class MdmcTaskLogServiceImpl implements MdmcTaskLogService {

    @Resource
    MdmcTaskLogMapper taskLogMapper;
    @Resource
    MdmcTaskItemLogMapper taskItemLogMapper;

    @Override
    public String submitTaskLog(MdmcTaskLogDto taskLogDto) {
        MdmcTaskLog taskLog = new MdmcTaskLog();

        taskLog.setId(taskLogDto.getTaskLogId());
        taskLog.setTaskId(taskLogDto.getTaskId());

        taskLog.setStatus(taskLogDto.getStatus());
        taskLog.setMovement(taskLogDto.getMovement());
        taskLog.setStatusTimestamp(taskLogDto.getStatus_timestamp());
        taskLog.setLastOperator(taskLogDto.getLast_operator());


        int i=taskLogMapper.insert(taskLog);
        if (i<1){return "出错";}



        List<MdmcTaskItemLogDto> taskItemLogDtoList=taskLogDto.getTaskItemLogDtoList();
        for (int a=0;a<taskItemLogDtoList.size();a++){
            MdmcTaskItemLog taskItemLog=new MdmcTaskItemLog();
            Random rand1 = new Random();
            Long task_item_log_id=rand1.nextLong();
            taskItemLog.setId(task_item_log_id);
            taskItemLog.setMovement(taskItemLogDtoList.get(a).getDescription());
            taskItemLog.setTaskId(taskItemLogDtoList.get(a).getTaskId());

            int b=taskItemLogMapper.insert(taskItemLog);
            if (b<1){return "出错";}
        }

       return "success";


    }
}
