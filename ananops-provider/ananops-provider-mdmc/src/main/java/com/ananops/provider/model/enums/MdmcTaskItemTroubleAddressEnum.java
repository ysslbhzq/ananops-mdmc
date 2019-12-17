package com.ananops.provider.model.enums;

public enum MdmcTaskItemTroubleAddressEnum {
    Address1(1,"位置1"),

    Address2(2,"位置2"),

    Address3(3,"位置3"),

    Address4(4,"位置4"),

    Address5(5,"位置5"),

    Address6(6,"位置6"),

    Address7(7,"位置7"),

    Address8(8,"位置8"),

    Address9(9,"位置9");

    private Integer type;

    private String status;

    MdmcTaskItemTroubleAddressEnum(Integer type, String status){
        this.type = type;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Integer getType(){
        return type;
    }

    public static MdmcTaskItemTroubleAddressEnum getEnum(String type) {
        for (MdmcTaskItemTroubleAddressEnum ele : MdmcTaskItemTroubleAddressEnum.values()) {
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
