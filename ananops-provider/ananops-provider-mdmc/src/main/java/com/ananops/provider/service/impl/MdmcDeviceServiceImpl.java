package com.ananops.provider.service.impl;

import com.ananops.provider.mapper.MdmcDeviceMapper;
import com.ananops.provider.mapper.MdmcTaskItemMapper;
import com.ananops.provider.model.domain.MdmcDevice;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.dto.MdmcDeviceInfoDto;
import com.ananops.provider.service.MdmcDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/26 21:43
 */
@Service
public class MdmcDeviceServiceImpl implements MdmcDeviceService {
    @Resource
    private MdmcDeviceMapper mdmcDeviceMapper;
    @Resource
    private MdmcTaskItemMapper mdmcTaskItemMapper;

    public Integer insertRecord(MdmcDevice mdmcDevice){
        return mdmcDeviceMapper.insert(mdmcDevice);
    }

    @Override
    public List<MdmcDeviceInfoDto> getDeviceInfoList(Long task_id) {

        List<MdmcDeviceInfoDto> deviceInfoDtoList=new ArrayList<>();
        MdmcTaskItem taskItem=new MdmcTaskItem();
        taskItem.setTaskId(task_id);
        List<MdmcTaskItem> taskItemList=mdmcTaskItemMapper.select(taskItem);
        for (int i=0;i<taskItemList.size();i++){
            MdmcDeviceInfoDto deviceInfoDto=new MdmcDeviceInfoDto();
            deviceInfoDto.setDeviceNo(taskItemList.get(i).getDeviceId());
            deviceInfoDto.setOrderId(task_id);
            deviceInfoDto.setDeviceName(taskItemList.get(i).getDeviceName());
            deviceInfoDto.setDeviceLongtitude(taskItemList.get(i).getDeviceLongitude());
            deviceInfoDto.setDeviceLatitude(taskItemList.get(i).getDeviceLatitude());
            deviceInfoDtoList.add(deviceInfoDto);
        }
        return deviceInfoDtoList;
    }

//    /*获取单个报修单的设备信息*/
//    @Override
//    public MdmcDeviceInfoDto getDeviceInfo(Long device_id) {
//        MdmcDevice device=mdmcDeviceMapper.selectByPrimaryKey(device_id);
//        MdmcDeviceInfoDto deviceInfoDto=new MdmcDeviceInfoDto();
//        deviceInfoDto.setDeviceNo(device_id);
//        deviceInfoDto.setDeviceModel(device.getDeviceModel());
//        deviceInfoDto.setDeviceType(device.getDeviceType());
//
//        MdmcTaskItem taskItem=mdmcTaskItemMapper.selectByDeviceId(device_id);
//        deviceInfoDto.setDeviceLatitude(taskItem.getDeviceLatitude());
//        deviceInfoDto.setDeviceLongtitude(taskItem.getDeviceLongitude());
//        deviceInfoDto.setDeviceName(taskItem.getDeviceName());
//        deviceInfoDto.setOrderId(taskItem.getId());
//
//        return deviceInfoDto;
//    }


}
