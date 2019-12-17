package com.ananops.provider.service;

import com.ananops.provider.model.domain.MdmcReview;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.dto.*;

import java.util.List;

/**
 * Created by zhs on 2019/12/5 13:58
 */
public interface MdmcTaskService {

//    MdmcTask changeTaskStatus(Long taskId, Integer status);
//
//    MdmcTask getTaskInfo(Long taskId);
//
//    MdmcTask saveTask(MdmcTask task);
//
    String submitTask(MdmcOrderDto order) throws Exception;
//
//    String leaderApprovePass(MdmcApproveInfoDto approveInfo) throws Exception;
//
//    void leaderApproveFail(String data);
//
//    void cancelTask(String data);
//
//    void serviceProviderReceiveTask(String data);
//
//    void serviceProviderRejectTask(String data);
//
//    void maintenanceWorkerReceiveTask(String data);
//
//    void maintenanceWorkerRejectTask(String data);
//
//    void maintenanceWorkerEnsureService(String data);
//
//    void maintenanceWorkerExchangeTask(String data);
//
//    void maintenanceWorkerApplyForDevices(String data);
//
//    void ensureService(String data);
//
//    void serviceProviderApproveBillPass(String data);
//
//    void serviceProviderApproveBillFail(String data);
//
//    void leaderApproveBillPass(String data);
//
//    void leaderApproveBillFail(String data);
//
//    void leaderEnsureAndPay(String data);
//
//    void leaderRejectPay(String data);
//
//    void evaluate(String data);
//
//    Long dispatchTask(Long taskId);

//    List<MdmcStatusRepairsDto> getRepairsStatusCount();

    List<MdmcOrderDto> getTaskList(Long user_id);

    List<MdmcTask> getTaskListByClassify(MdmcQueryDto queryDto);

//    Void addTaskRecord(MdmcOrderDto taskDto);

    List<MdmcProcessingDto> getProcessingList(Long task_id);

    MdmcResultDto getProcessInfo(Long task_id);

    List<MdmcOrderDto> getRepairsList(Long maintainer_id);

    List<MdmcRepairsTaskDto> getFacilityTaskList(Long facilitator_id);

    List<MdmcOrderDto> getPrincipalTaskList(Long principal_id);

    MdmcCheckDto getApprovalInfo(Long task_id);

    MdmcWorkLoadDto getWorkLoad(Long task_id);

    List<MdmcOrderDto> getTaskListByStatus(Integer status);

    String deleteTask(Long task_id);

    String updateTask(MdmcUpdateTaskDto updateTaskDto);

    String submitApproval(MdmcApproveInfoDto approveInfoDto);

    String submitResult(MdmcResultDto resultDto);

    String submitReview(MdmcReview review);

    List<MdmcResultDto> getResult(Long task_id);

    MdmcTypeDto getTypeList();

    MdmcTask getTask(Long task_id);

    String checkTask(MdmcCheckDto checkDto);

    String acceptTask(MdmcCheckDto checkDto);

    String confirmTask(MdmcCheckDto checkDto);
}
