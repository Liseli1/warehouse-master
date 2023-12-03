package com.yeqifu.bus.vo;

import com.yeqifu.bus.entity.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends Goods {

    private Integer page=1;
    private Integer limit=10;

    public String toString(){
        return super.toString();
    }

}
