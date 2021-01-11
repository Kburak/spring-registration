package com.kburakengin.registerlogin.demo.controller;

import com.kburakengin.registerlogin.demo.model.Customer;
import com.kburakengin.registerlogin.demo.service.CustomerService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Log
@Controller
public class CustomerController {

    private static final String REDIRECT_HOME = "redirect:/";

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @GetMapping("/new-customer")
    public String showNewCustomerForm(Model model) {
        // create model attribute
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_customer";
        }
        else {
            //save customer to db
            this.customerService.saveCustomer(customer);
            log.info("Customer Created");
            return "customer-list";
        }
    }

    @GetMapping("/customer-list")
    public String getCustomerList(Model model) {
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "customer_list";
    }

    @GetMapping("/update-customer-form/{id}")
    public String showUpdateCustomerForm(@PathVariable(value = "id") Long id, Model model) {

        Customer customer = this.customerService.getCustomerById(id);

        model.addAttribute(customer);

        return "update_customer";
    }

    @PostMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable(value = "id") Long id, @ModelAttribute("customer") @Valid Customer customer,
                                 BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            log.warning("Something Went Wrong, maybe missing fields.");
            customer.setId(id);
            return "update_customer";
        }
        this.customerService.updateCustomer(customer);
        log.info("Updated Successfully");
        return "customer_list";
    }

    @GetMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Long id) {
        this.customerService.deleteCustomer(id);
        return REDIRECT_HOME;
    }
}
