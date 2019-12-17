package com.ananops.provider.service;

import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.dto.MdmcDeviceTroubleDto;
import com.ananops.provider.model.dto.MdmcStatusRepairsDto;
import com.ananops.provider.model.dto.MdmcTroubleInfoDto;
import com.ananops.provider.model.dto.MdmcWorkLoadDto;

import java.util.List;

public interface MdmcTaskItemService {
    List<MdmcTroubleInfoDto> getTroubleInfo(Long task_id);





//    List<MdmcTaskCategoryDto> getTaskList(Long creator_id);

//    List<MdmcTaskCategoryDto> getTaskListByStatus(Long user_id,Integer status);


//    List<MdmcTaskCategoryDto> getTaskListByLevel(Long user_id,Integer level);



//    List<MdmcRepairsTaskDto> getRepairsListByStatus(Long maintainer_id,Integer status);

    List<MdmcStatusRepairsDto> getRepairsStatusCountList(Long maintainer_id);


    MdmcWorkLoadDto getWorkLoad(Long taskItem_id);


    List<MdmcTaskItem> selectAll() ;

    List<MdmcDeviceTroubleDto> getDeviceTroubleList(Long task_id);
}
