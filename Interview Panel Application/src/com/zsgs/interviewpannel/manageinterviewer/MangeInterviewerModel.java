package com.zsgs.interviewpannel.manageinterviewer;

import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.model.Credential;
import com.zsgs.interviewpannel.model.Interviewer;

class MangeInterviewerModel {
    private ManageInterviewerView manageInterviewerView;

    MangeInterviewerModel(ManageInterviewerView interviewerView) {
        this.manageInterviewerView = interviewerView;
    }

    public void addInterviewer(Interviewer interviewer) {
        if (interviewer.getName().length() > 2 && interviewer.getName().length() < 50) {
            if(InterviewPannelDataBase.getInstance().interviewerLimitCheck()){
                if (validateMail(interviewer.getEmailId()) && InterviewPannelDataBase.getInstance().validateCredentialUsernotFound(interviewer.getEmailId()) && validatePhone(interviewer.getPhoneNumber())) {
                    if (InterviewPannelDataBase.getInstance().addInterviewer(interviewer)) {
                        Credential credential = manageInterviewerView.addInterviewerCredentials();
                        credential.setUserName(interviewer.getEmailId());
                        credential.setRole('I');
                        InterviewPannelDataBase.getInstance().addCredential(credential);
                        manageInterviewerView.showSucessAddInterviewer("Interviewer " + interviewer.getName() + " add Sucessfully");
                    } else {
                        manageInterviewerView.showErrorMessageAddInterviewer("Interviewer Already Exist");
                    }
                } else {
                    manageInterviewerView.showErrorMessageAddInterviewer("Invalid Email or Email Already Exist or Phone Number");
                }
            }else {
                manageInterviewerView.showMsg("Interviewver Limit Exeed");
            }
        } else {
            manageInterviewerView.showErrorMessageAddInterviewer("Invalid Name");
        }
    }

    private boolean validatePhone(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    private boolean validateMail(String mailId) {
        return mailId.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
