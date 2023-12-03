package com.yeqifu.bus.service;

import com.yeqifu.bus.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ICustomerService extends IService<Customer> {

    /**
     * 根据客户id删除客户
     * @param id    客户id
     */
    void deleteCustomerById(Integer id);
}
