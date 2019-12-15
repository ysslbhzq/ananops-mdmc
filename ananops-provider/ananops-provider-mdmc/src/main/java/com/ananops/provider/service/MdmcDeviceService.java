package com.ananops.provider.service;

import com.ananops.provider.model.domain.MdmcDevice;
import com.ananops.provider.model.dto.MdmcDeviceInfoDto;

import java.util.List;


public interface MdmcDeviceService {

    Integer insertRecord(MdmcDevice mdmcDevice);

    List<MdmcDeviceInfoDto> getDeviceInfoList(Long device_id);


}
