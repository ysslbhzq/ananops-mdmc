package com.ananops.provider.model.enums;

public enum MdmcTaskLevelEnum {
    Level1(1,"紧急程度1"),

    Level2(2, "紧急程度2"),

    Level3(3, "紧急程度3"),

    Level4(4, "紧急程度4"),

    Level5(5, "紧急程度5"),

    Level6(6, "紧急程度6"),

    Level7(7, "紧急程度7");

    private Integer type;

    private String status;

    MdmcTaskLevelEnum(Integer type, String status){
        this.type = type;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Integer getType(){
        return type;
    }

    public static MdmcTaskLevelEnum getEnum(String type) {
        for (MdmcTaskLevelEnum ele : MdmcTaskLevelEnum.values()) {
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
