package com.ananops.provider.model.enums;

public enum MdmcTaskItemTroubleTypeEnum {
    TroubleType1(1,"故障类型1"),

    TroubleType2(2, "故障类型2"),

    TroubleType3(3, "故障类型3"),

    TroubleType4(4, "故障类型4"),

    TroubleType5(5, "故障类型5"),

    TroubleType6(6, "故障类型6"),

    TroubleType7(7, "故障类型7");

    private Integer type;

    private String status;

    MdmcTaskItemTroubleTypeEnum(Integer type, String status){
        this.type = type;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Integer getType(){
        return type;
    }

    public static MdmcTaskItemTroubleTypeEnum getEnum(String type) {
        for (MdmcTaskItemTroubleTypeEnum ele : MdmcTaskItemTroubleTypeEnum.values()) {
            if (ele.getStatus().equals(type)) {
                return ele;
            }
        }
        return null;
    }

    public String toString() {
        return String.valueOf ( this.type );
    }
}
