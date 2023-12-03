package com.yeqifu.bus.Dto.entiry;

import lombok.Data;

import java.util.List;

@Data
public class SeriesData {
   /* name: '直接访问',
    type: 'bar',
    barWidth: '60%',
    data: [10, 52, 200, 334, 390, 330, 220]*/
    private String name;
    private String type;
    private String barWidth;
    private String stack;
    private List<Integer> data;
}
