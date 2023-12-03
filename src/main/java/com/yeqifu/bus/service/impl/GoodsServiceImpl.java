package com.yeqifu.bus.service.impl;

import com.sun.deploy.security.SessionCertStore;
import com.yeqifu.bus.Dto.Out.ShowGoodOut;
import com.yeqifu.bus.Dto.entiry.Series;
import com.yeqifu.bus.Dto.entiry.SeriesData;
import com.yeqifu.bus.Dto.entiry.XAxisBase;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.mapper.GoodsMapper;
import com.yeqifu.bus.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public boolean save(Goods entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Goods entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Goods getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public void deleteGoodsById(Integer id) {
        //根据商品id删除商品销售信息
        this.getBaseMapper().deleteSaleByGoodsId(id);
        //根据商品id删除商品销售退货信息
        this.getBaseMapper().deleteSaleBackByGoodsId(id);
        //根据商品id删除商品进货信息
        this.getBaseMapper().deleteInportByGoodsId(id);
        //根据商品id删除商品退货信息
        this.getBaseMapper().deleteOutportByGoodsId(id);
        //删除商品信息
        this.removeById(id);
    }

    /**
     * 查询所有库存预警商品
     * @return
     */
    @Override
    public List<Goods> loadAllWarning() {
        List<Goods> goods = baseMapper.loadAllWarning();
        return goods;
    }

    public ShowGoodOut showAllGood(){
        List<Goods> goodsList= baseMapper.loadAllWarning();
        ShowGoodOut out=new ShowGoodOut();
        List<Integer> list=new ArrayList<>();
        List<String> xAxisList=new ArrayList<>();
        for (Goods good:goodsList) {
            list.add(good.getNumber());
            xAxisList.add(good.getGoodsname());
        }
        Series series=new Series();
        SeriesData seriesData=new SeriesData();
        seriesData.setData(list);
        seriesData.setName("");
        seriesData.setType("bar");
        seriesData.setBarWidth("60%");
        List<SeriesData> seriesDataList=new ArrayList<>();
        seriesDataList.add(seriesData);
        series.setSeriesData(seriesDataList);
        XAxisBase xAxis=new XAxisBase();
        xAxis.setType("category");
        xAxis.setData(xAxisList);
        out.setSeries(series);
        out.setXAxis(xAxis);
        return out;
    }

    public ShowGoodOut AllGoodsSale(){
        ShowGoodOut out=new ShowGoodOut();
        List<Goods> goodsList= baseMapper.loadAllWarning();
        List<String> goodsnames=new ArrayList<>();
        Series series=new Series();
        List<SeriesData> seriesDataList=new ArrayList<>();
        //设置横坐标的值,
        List<String> mouthList=getMouthList();
        XAxisBase xAxis=new XAxisBase();
        List<String> xAxisData=new ArrayList<>();
        for (int i = mouthList.size()-1; i >=0 ; i--) {
            String s=mouthList.get(i).substring(0,7);
            xAxisData.add(s);
        }
        xAxis.setData(xAxisData);
        out.setXAxis(xAxis);
        //设置折线图的值
        for (Goods good:goodsList) {
            SeriesData seriesData=new SeriesData();
            //设置折线图的legend
            goodsnames.add(good.getGoodsname());
            //设置折线图每个折线的名字
            seriesData.setName(good.getGoodsname());
            //为折线图赋值
            int []nums=new int[12];
            for (int i=0;i<mouthList.size();i++) {
                Map<String,String> map=new HashMap<>();
                map.put("id",good.getId().toString());
                map.put("date",mouthList.get(mouthList.size()-i-1));
                if (baseMapper.selectSalesCount(map)!=null){
                    nums[i]=baseMapper.selectSalesCount(map);
                }else {
                    nums[i]=0;
                }
            }
            List<Integer> numbers=new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                numbers.add(nums[i]);
            }
            seriesData.setData(numbers);
            seriesData.setType("line");
            seriesData.setStack("总量");
            seriesDataList.add(seriesData);
        }
        out.setLegend(goodsnames);
        series.setSeriesData(seriesDataList);
        out.setSeries(series);
        return out;
    }

    public static List<String> getMouthList(){
        List<String> mouthList=new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(long i = 0L;i <= 11L; i++){
            LocalDate localDate = today.minusMonths(i);
            String s=localDate.toString();
            mouthList.add(s);
        }
        return mouthList;
    }

}
