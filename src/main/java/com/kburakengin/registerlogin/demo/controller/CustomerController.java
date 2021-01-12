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
    private static final String REDIRECT_CUSTOMER_LIST = "redirect:/customerList";


    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @GetMapping("/newCustomer")
    public String showNewCustomerForm(Model model) {
        // create model attribute
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_customer";
        }
        else {
            //save customer to db
            this.customerService.saveCustomer(customer);
            log.info("Customer Created");
            return "redirect:/customerList";
        }
    }

    @GetMapping("/customerList")
    public String getCustomerList(Model model) {
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "customer_list";
    }

    @GetMapping("/showUpdateCustomerForm/{id}")
    public String showUpdateCustomerForm(@PathVariable(value = "id") long id, Model model) {

        Customer customer = this.customerService.getCustomerById(id);

        model.addAttribute(customer);

        return "update_customer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable(value = "id") long id, @ModelAttribute("customer") @Valid Customer customer,
                                 BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            log.warning("Something Went Wrong, maybe missing fields.");
            customer.setId(id);
            return "update_customer";
        }
        this.customerService.updateCustomer(customer);
        log.info("Updated Successfully");
        return REDIRECT_CUSTOMER_LIST.concat("?updateSuccess") ;
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") long id) {
        this.customerService.deleteCustomer(id);
        return REDIRECT_CUSTOMER_LIST.concat("?deleteSuccess");
    }
}
