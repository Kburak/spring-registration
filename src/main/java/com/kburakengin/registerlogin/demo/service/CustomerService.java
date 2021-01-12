package com.kburakengin.registerlogin.demo.service;

import com.kburakengin.registerlogin.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    void updateCustomer(Customer customer);
    void deleteCustomer(long id);
}
