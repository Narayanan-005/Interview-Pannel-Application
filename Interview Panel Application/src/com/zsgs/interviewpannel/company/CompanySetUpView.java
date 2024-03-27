package com.zsgs.interviewpannel.company;

import com.zsgs.interviewpannel.admin.AdminView;
import com.zsgs.interviewpannel.model.Company;

import java.util.Scanner;

public class CompanySetUpView {
    private CompanySetUpModel companySetUpModel;

    public CompanySetUpView() {
        companySetUpModel = new CompanySetUpModel(this);
    }

    public void init() {
        companySetUpModel.startSetUp();
    }

    public void setUpCompany() {
        Scanner scanner = new Scanner(System.in);
        Company company = new Company();
        System.out.println("\nEnter Company name ");
        company.setName(scanner.nextLine());
        System.out.println("Enter company email");
        company.setEmail(scanner.nextLine());
        System.out.println("Enter Company Phone Number");
        company.setPhoneNo(scanner.nextLine());
        System.out.println("Enter Company Address");
        company.setAddress(scanner.nextLine());
        companySetUpModel.insetCompany(company);
    }

    public void showMessage(String invalidCompanyName) {
        System.out.println("\n" + invalidCompanyName);
    }

    public void onCompanySetupComplete() {
        System.out.println("\nCompany Setup Completed");
        new AdminView().init();
    }
}
