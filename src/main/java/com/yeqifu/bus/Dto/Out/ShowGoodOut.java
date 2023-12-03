package com.yeqifu.bus.Dto.Out;

import com.yeqifu.bus.Dto.entiry.Series;
import com.yeqifu.bus.Dto.entiry.XAxisBase;
import lombok.Data;

import java.util.List;

@Data
public class ShowGoodOut {
    private XAxisBase xAxis;
    private Series series;
    private List<String> legend;
}
