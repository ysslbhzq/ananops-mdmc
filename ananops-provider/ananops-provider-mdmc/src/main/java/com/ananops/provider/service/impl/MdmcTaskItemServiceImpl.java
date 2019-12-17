package com.ananops.provider.service.impl;

import com.ananops.provider.mapper.MdmcTaskItemLogMapper;
import com.ananops.provider.mapper.MdmcTaskItemMapper;
import com.ananops.provider.mapper.MdmcTaskMapper;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.dto.MdmcStatusRepairsDto;
import com.ananops.provider.model.dto.MdmcTroubleInfoDto;
import com.ananops.provider.model.dto.MdmcWorkLoadDto;
import com.ananops.provider.service.MdmcTaskItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class MdmcTaskItemServiceImpl implements MdmcTaskItemService {
    @Resource
    MdmcTaskItemMapper mdmcTaskItemMapper;

    @Resource
    MdmcTaskMapper mdmcTaskMapper;

    @Resource
    MdmcTaskItemLogMapper mdmcTaskItemLogMapper;


    /*获取工单的故障信息列表*/
    @Override
    public List<MdmcTroubleInfoDto> getTroubleInfo(Long task_id) {

        List<MdmcTroubleInfoDto> troubleInfoDtoList=new ArrayList<>();
        MdmcTaskItem taskItem=new MdmcTaskItem();
        taskItem.setTaskId(task_id);
        List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.select(taskItem);
        for (int i=0;i<taskItemList.size();i++){
            MdmcTroubleInfoDto troubleInfoDto=new MdmcTroubleInfoDto();
            troubleInfoDto.setDescription(taskItemList.get(i).getDescription());
            troubleInfoDto.setCaller(taskItemList.get(i).getCreator());//creator就是报修人，user
            troubleInfoDto.setLevel(taskItemList.get(i).getLevel());
            troubleInfoDto.setOrderId(taskItemList.get(i).getId());

            troubleInfoDtoList.add(troubleInfoDto);

        }
        return troubleInfoDtoList;
    }




//    /*获取报修人报修单列表，存疑*/
//    @Override
//    public List<MdmcTaskCategoryDto> getTaskList(Long creator_id) {
//
//        List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.selectByCreatorId(creator_id);
//        List<MdmcTaskCategoryDto> taskCategoryDtoList=Lists.newArrayList();
//
//        for (int i=0;i<taskItemList.size();i++){
//            MdmcTaskItem taskItem=taskItemList.get(i);
//            MdmcTaskCategoryDto taskCategoryDto=getTaskCategoryDto(taskItem);
//            taskCategoryDtoList.add(taskCategoryDto);
//        }
//
//        return taskCategoryDtoList;
//    }

//    /*获取用户某一状态下报修单列表,mapperXML没写*/
//    @Override
//    public List<MdmcTaskCategoryDto> getTaskListByStatus(Long creator_id, Integer status) {
//
//        List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.selectByCreatorIdAndStatus(creator_id,status);
//        List<MdmcTaskCategoryDto> taskCategoryDtoList=Lists.newArrayList();
//
//        for (int i=0;i<taskItemList.size();i++){
//            MdmcTaskItem taskItem=taskItemList.get(i);
//            MdmcTaskCategoryDto taskCategoryDto=getTaskCategoryDto(taskItem);
//            taskCategoryDtoList.add(taskCategoryDto);
//        }
//
//        return taskCategoryDtoList;
//    }
//
//
//    public MdmcTaskCategoryDto getTaskCategoryDto(MdmcTaskItem taskItem) {
//
//        MdmcTaskCategoryDto taskCategoryDto=new MdmcTaskCategoryDto();
//        taskCategoryDto.setStatus(taskItem.getStatus());
//        taskCategoryDto.setDeviceName(taskItem.getDeviceName());
//        taskCategoryDto.setDeviceLongtitude(taskItem.getDeviceLongitude());
//        taskCategoryDto.setDeviceLatitude(taskItem.getDeviceLatitude());
//        taskCategoryDto.setDescription(taskItem.getDescription());
//        taskCategoryDto.setCreateTime(taskItem.getCreatedTime());
//
//        return taskCategoryDto;
//    }


//    /*获取维修工维修单列表*/
//    @Override
//    public List<MdmcRepairsTaskDto> getRepairsList(Long maintainer_id) {
//
//        MdmcTaskItem taskItem=new MdmcTaskItem();
//        taskItem.setMaintainerId(maintainer_id);
//        List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.select(taskItem);
//        List<MdmcRepairsTaskDto> repairsTaskDtoList=new ArrayList<>();
//        for (int i=0;i<taskItemList.size();i++){
//            MdmcRepairsTaskDto repairsTaskDto=new MdmcRepairsTaskDto();
//            repairsTaskDto.setStatus(taskItemList.get(i).getStatus());
//            repairsTaskDto.setOrderId(taskItemList.get(i).getTaskId());
//            repairsTaskDto.setLevel(taskItemList.get(i).getLevel());
//            repairsTaskDto.setDeviceNo(taskItemList.get(i).getDeviceId());
//            repairsTaskDto.setDeviceName(taskItemList.get(i).getDeviceName());
//            repairsTaskDto.setCreateTime(taskItemList.get(i).getCreatedTime());
//
//            repairsTaskDtoList.add(repairsTaskDto);
//
//        }

//
//        return repairsTaskDtoList;
//    }

//    public MdmcRepairsTaskDto getRepairsTaskDto(MdmcTaskItem taskItem){
//        MdmcRepairsTaskDto repairsTaskDto=new MdmcRepairsTaskDto();
//        repairsTaskDto.setCreateTime(taskItem.getCreatedTime());
//        repairsTaskDto.setDeviceName(taskItem.getDeviceName());
//        repairsTaskDto.setDeviceNo(taskItem.getDeviceId());
//        repairsTaskDto.setLevel(taskItem.getLevel());
//        repairsTaskDto.setOrderId(taskItem.getId());
//        repairsTaskDto.setStatus(taskItem.getStatus());
//
//        return repairsTaskDto;
//    }

    /*维修工获取某一状态下的维修单列表*/
//    @Override
//    public List<MdmcRepairsTaskDto> getRepairsListByStatus(Long maintainer_id, Integer status) {
//        List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.selectByMaintainerIdAndStatus(maintainer_id,status);
//        List<MdmcRepairsTaskDto> repairsTaskDtoList=Lists.newArrayList();
//        for (int i=0;i<taskItemList.size();i++){
//            MdmcTaskItem taskItem=taskItemList.get(i);
//            MdmcRepairsTaskDto repairsTaskDto=getRepairsTaskDto(taskItem);
//            repairsTaskDtoList.add(repairsTaskDto);
//
//        }
//        return repairsTaskDtoList;
//
//    }

    /*维修工获取显示不同状态的工单个数列表,存疑*/
    @Override
    public List<MdmcStatusRepairsDto> getRepairsStatusCountList(Long maintainer_id) {

        List<MdmcStatusRepairsDto> statusRepairsDtoList=new ArrayList<>();
        for (Integer type=4;type<=6;type++){//type指的是status
            MdmcStatusRepairsDto statusRepairsDto=new MdmcStatusRepairsDto();
//            MdmcTaskItem item = new MdmcTaskItem();
//            item.setMaintainerId(Long.valueOf(123L));
//            item.setStatus(4);
            List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.selectByMaintainerIdAndStatus(maintainer_id,type);
//            List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.select(item);
//            int count =mdmcTaskItemMapper.selectCount(item);
            statusRepairsDto.setCount(taskItemList.size());
//            statusRepairsDto.setCount(count);
            statusRepairsDtoList.add(statusRepairsDto);
        }

        return statusRepairsDtoList;
    }



    /*获取单个维修单的工作量*/
    @Override
    public MdmcWorkLoadDto getWorkLoad(Long taskItem_id) {
        MdmcTaskItem taskItem=mdmcTaskItemMapper.selectByPrimaryKey(taskItem_id);
        MdmcWorkLoadDto workLoadDto=new MdmcWorkLoadDto();
        workLoadDto.setCreator(taskItem.getCreator());
        workLoadDto.setActual_finish_time(taskItem.getActualFinishTime());
        workLoadDto.setActual_start_time(taskItem.getActualStartTime());

        return workLoadDto;
    }



//    /*获取用户某一紧急程度报修单列表*/
//    @Override
//    public List<MdmcTaskCategoryDto> getTaskListByLevel(Long user_id, Integer level) {
//        MdmcTaskCategoryDto taskCategoryDto=getTaskCategoryDto(user_id);
//        List<MdmcTaskCategoryDto> taskCategoryDtoList=Lists.newArrayList();
//
//
//    }

    public List<MdmcTaskItem> selectAll(){
        return mdmcTaskItemMapper.selectAll();
    }

    @Override
    public List<MdmcTaskItem> getDeviceTroubleList(Long task_id) {

       MdmcTaskItem taskItem=new MdmcTaskItem();
       taskItem.setTaskId(task_id);
       List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.select(taskItem);

       return  taskItemList;
    }


}
