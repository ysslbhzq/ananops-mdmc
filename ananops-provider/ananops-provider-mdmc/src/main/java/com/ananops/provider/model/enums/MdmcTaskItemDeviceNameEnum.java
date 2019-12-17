package com.ananops.provider.model.enums;


public enum MdmcTaskItemDeviceNameEnum {
    SheBei1(1,"设备1"),

    SheBei2(2, "设备2"),

    SheBei3(3, "设备3"),

    SheBei4(4, "设备4"),

    SheBei5(5, "设备5"),

    SheBei6(6, "设备6"),

    SheBei7(7, "设备7");

//    JieDan(4, "待接单"),
//
//    ZhiXing(5, "待执行"),
//
//    WeiXiu(6, "维修中"),
//
//    QueRenFuWu(7, "待确认服务"),
//
//    SPShenHeZhangDan(8, "待服务商审核账单"),
//
//    LDShenHeZhangDan(9, "待负责人审核账单"),
//
//    YanShou(10, "待验收"),
//
//    PingJia(11, "待评价"),
//
//    WanCheng(12, "已完成");
//
    private Integer type;

    private String status;

    MdmcTaskItemDeviceNameEnum(Integer type, String status){
        this.type = type;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Integer getType(){
        return type;
    }


    public String toString() {
        return String.valueOf ( this.type );
    }

//    public static MdmcTaskItemDeviceNameEnum getEnum() {
//        for (int i=1;i<4;i++){
//
//        }
//
//                return ele;
//
//    }
}
