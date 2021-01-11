package com.kburakengin.registerlogin.demo.service;

import com.kburakengin.registerlogin.demo.model.Customer;
import com.kburakengin.registerlogin.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws RuntimeException{

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        Customer customer = null;

        if (optionalCustomer.isPresent()) {
             customer = optionalCustomer.get();
        } else {
            throw new RuntimeException("Customer not found for id ::" + id);
        }

        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }
}
