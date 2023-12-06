package com.yeqifu.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {
    private Long id;
    private String person;
    private String indata;
    private String type;
    private String operatingtime;
    private String result;
    private String ip;
}
