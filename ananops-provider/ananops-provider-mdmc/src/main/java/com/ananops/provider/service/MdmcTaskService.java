package com.ananops.provider.service;

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

    String faciTransfer(Long taskId,List<Long> all);

    void timeLimit(final Long id, final int delay,List<Long> all);

    List<MdmcStatusRepairsDto> getRepairsStatusCountList(Long maintainer_id);

    List<MdmcOrderDto> getTaskList(Long user_id);

//    Void addTaskRecord(MdmcOrderDto taskDto);

    List<MdmcProcessingDto> getProcessingList(Long task_id);

    MdmcProcessInfoDto getProcessInfo(Long task_id);

    List<MdmcRepairsTaskDto> getRepairsList(Long maintainer_id);

    List<MdmcRepairsTaskDto> getFacilityTaskList(Long facilitator_id);

    List<MdmcPrincipleTaskDto> getPrincipalTaskList(Long principal_id);

    MdmcApproveInfoDto getApprovalInfo(Long task_id);

    MdmcWorkLoadDto getWorkLoad(Long task_id);

    List<MdmcOrderDto> getTaskListByStatus(Integer status);

    String deleteTask(Long task_id);

    String updateTask(MdmcUpdateTaskDto updateTaskDto);

    //从对应关系中删除该工单
    void deleteFaciMap(MdmcUpdateTaskDto updateTaskDto);

    String submitApproval(MdmcApproveInfoDto approveInfoDto);
}
