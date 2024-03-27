package com.zsgs.interviewpannel.managecandidate;

import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.model.Candidate;

class ManageCandidateModel {
    private ManageCandidateView manageCandidateView;

    ManageCandidateModel(ManageCandidateView manageCandidateView) {
        this.manageCandidateView = manageCandidateView;
    }

    public void addCandidate(Candidate candidate) {
        if (candidate.getName().length() > 2 && candidate.getName().length() < 50) {
            if (validateMail(candidate.getMailId()) && validatePhone(candidate.getPhoneNumber())) {
                if(InterviewPannelDataBase.getInstance().candidateLimitChech()){
                    if (InterviewPannelDataBase.getInstance().addCandidate(candidate)) {
                        manageCandidateView.showSucessAddCandidate("Candidate " + candidate.getName() + " add Sucessfully");
                    } else {
                        manageCandidateView.showErrorMessageAddCandidate("Candidate Already Exist");
                    }
                }else {
                    manageCandidateView.showMsg("Candidate Limit Exeed");
                }
            } else {
                manageCandidateView.showErrorMessageAddCandidate("Invalid Email or Phone Number");
            }
        } else {
            manageCandidateView.showErrorMessageAddCandidate("Invalid Name");
        }
    }

    private boolean validatePhone(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    private boolean validateMail(String mailId) {
        return mailId.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
