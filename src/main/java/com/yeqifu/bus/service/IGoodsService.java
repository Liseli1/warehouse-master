package com.yeqifu.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeqifu.bus.Dto.Out.ShowGoodOut;
import com.yeqifu.bus.entity.Goods;

import java.util.List;

public interface IGoodsService extends IService<Goods> {

    /**
     * 根据商品id删除商品
     * @param id
     */
    void deleteGoodsById(Integer id);

    /**
     * 加载所有的库存预警商品
     * @return
     */
    List<Goods> loadAllWarning();

    ShowGoodOut showAllGood();

    ShowGoodOut AllGoodsSale();
}
