package com.zsgs.interviewpannel.interview;

import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.model.Candidate;

import java.util.List;

class ManageInterviewModel {
    private ManageInterviewView manageInterviewView;

    ManageInterviewModel(ManageInterviewView manageInterviewView) {
        this.manageInterviewView = manageInterviewView;
    }

    public void mapCandidates() {
        if (InterviewPannelDataBase.getInstance().mapCandidates()) {
            manageInterviewView.showMsg("Mapping Candidates Sucessfully");
        } else {
            manageInterviewView.showMsg("Mapping Candidates Failed");
        }
    }

    public void setRating() {
        List<Candidate> mapCandidates = InterviewPannelDataBase.getInstance().getMapCandidates();
        if (mapCandidates.size() == 0) {
            manageInterviewView.showMsg("No Candidates Mapped");
        } else {
            for (Candidate candidate : mapCandidates) {
                manageInterviewView.getRating(candidate);
            }
            manageInterviewView.showMsg("Rating Added Sucessfully");
        }
        InterviewPannelDataBase.getInstance().setResult(mapCandidates);
    }

    public void showCandidate() {
        List<Candidate> mapCandidates = InterviewPannelDataBase.getInstance().getMapCandidates();
        if (mapCandidates.size() == 0) {
            manageInterviewView.showMsg("No Candidates Mapped");
        } else {
            manageInterviewView.shoeCandidates(mapCandidates);
        }
    }

    public void getResutNumber(int number) {
        List<Candidate> result = InterviewPannelDataBase.getInstance().getResultNumber(number);
        if (number > result.size()) {
            manageInterviewView.showMsg("Expected Candidates greater than actual candidates");
        } else {
            manageInterviewView.showResult(result);
        }
    }

    public void getResutRating(int number) {
        List<Candidate> result=InterviewPannelDataBase.getInstance().getResultRating(number);
        if(result.size()==0){
            manageInterviewView.showMsg("No Candidates Found");
        }else {
            manageInterviewView.showResult(result);
        }
    }
}
