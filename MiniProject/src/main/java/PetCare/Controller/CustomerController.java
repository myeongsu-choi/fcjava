package PetCare.Controller;

import PetCare.Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers;
    private MedicalRecordController recordController;

    public CustomerController(MedicalRecordController recordController) {
        this.customers = new ArrayList<>();
        this.recordController = recordController;
    }

    public boolean isPhoneNumberExist(String phoneNumber) {
        for(Customer customer : customers) {
            if(customer.getPhoneNumber().equals(phoneNumber))
                return true;
        }
        return false;
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public Customer findCustomer(String phoneNumber) {
        for(Customer customer : customers) {
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    public void removeCustomers(String phoneNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getPhoneNumber().equals(phoneNumber)) {
                customers.remove(i);
                recordController.removeMedicalRecord(phoneNumber); // 해당 고객의 진료 기록 삭제
            }
        }
    }
}
