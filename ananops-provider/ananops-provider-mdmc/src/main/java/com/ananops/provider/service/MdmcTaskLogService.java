package com.ananops.provider.service;

import com.ananops.provider.model.dto.MdmcTaskLogDto;

public interface MdmcTaskLogService {

    String submitTaskLog(MdmcTaskLogDto taskLogDto);
}
