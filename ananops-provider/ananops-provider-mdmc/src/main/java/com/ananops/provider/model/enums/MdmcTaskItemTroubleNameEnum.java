package com.ananops.provider.model.enums;

public enum MdmcTaskItemTroubleNameEnum {

    Name1(1,"故障名称1"),

    Name2(2, "故障名称2"),

    Name3(3, "故障名称3"),

    Name4(4, "故障名称4"),

    Name5(5, "故障名称5"),

    Name6(6, "故障名称6");

    private Integer type;

    private String status;

    MdmcTaskItemTroubleNameEnum(Integer type, String status){
        this.type = type;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Integer getType(){
        return type;
    }

    public static MdmcTaskItemTroubleNameEnum getEnum(String type) {
        for (MdmcTaskItemTroubleNameEnum ele : MdmcTaskItemTroubleNameEnum.values()) {
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
