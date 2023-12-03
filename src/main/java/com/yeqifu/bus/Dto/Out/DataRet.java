package com.yeqifu.bus.Dto.Out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data

public class DataRet<T> implements Serializable {
    @ApiModelProperty(notes = "交易处理代码")
    private String code;
    @ApiModelProperty(notes = "返回信息")
    private String message;
    @ApiModelProperty(notes = "返回数据结构体")
    private T data;
    public DataRet(T data) {
        this.data = data;
    }
    public void setPassInfo(String success,String retMsg){
        this.code=success;
        this.message=retMsg;
    }

}
