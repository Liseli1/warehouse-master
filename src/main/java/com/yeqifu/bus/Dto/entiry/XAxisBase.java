package com.yeqifu.bus.Dto.entiry;

import lombok.Data;

import java.util.List;

@Data
public class XAxisBase {
    /*type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']*/
    private String type;
    List<String> data;
}
