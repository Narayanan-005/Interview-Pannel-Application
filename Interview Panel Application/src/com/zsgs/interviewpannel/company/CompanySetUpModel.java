package com.zsgs.interviewpannel.company;

import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.model.Company;

class CompanySetUpModel {
    private CompanySetUpView companySetUpView;
    private Company company;

    CompanySetUpModel(CompanySetUpView companySetUpView) {
        this.companySetUpView = companySetUpView;
        this.company = InterviewPannelDataBase.getInstance().getCompany();
    }

    public void startSetUp() {
        if (company == null) {
            companySetUpView.showMessage("Enter Company Details Here");
            companySetUpView.setUpCompany();
        } else {
            companySetUpView.onCompanySetupComplete();
        }
    }

    public void insetCompany(Company company) {
        if (company.getName().length() > 3 && company.getName().length() < 50) {
            if (validatePhoneNO(company.getPhoneNo()) && validateEmailId(company.getEmail())) {
                insertCompany(company);
                companySetUpView.onCompanySetupComplete();
            } else {
                companySetUpView.showMessage("Invalid Email or Phone Number");
                companySetUpView.setUpCompany();
            }
        } else {
            companySetUpView.showMessage("Invalid Company Name");
            companySetUpView.setUpCompany();
        }
    }

    private void insertCompany(Company company) {
        this.company = company;
        InterviewPannelDataBase.getInstance().insertCompany(company);
    }

    private boolean validateEmailId(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean validatePhoneNO(String phoneNo) {
        return phoneNo.matches("\\d{10}");
    }
}
