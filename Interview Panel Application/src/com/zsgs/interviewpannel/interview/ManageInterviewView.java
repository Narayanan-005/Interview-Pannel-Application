package com.zsgs.interviewpannel.interview;

import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.model.Candidate;

import java.util.List;
import java.util.Scanner;

public class ManageInterviewView {
    private ManageInterviewModel manageInterviewModel;

    public ManageInterviewView() {
        manageInterviewModel = new ManageInterviewModel(this);
    }

    public void initMap() {
        manageInterviewModel.mapCandidates();
    }

    public void showMsg(String mappingCandidatesSucessfully) {
        System.out.println("\n" + mappingCandidatesSucessfully);
    }

    public void initSetRating() {
        manageInterviewModel.setRating();
    }

    public void initShowCandidate() {
        manageInterviewModel.showCandidate();
    }

    public void shoeCandidates(List<Candidate> mapCandidates) {
        for (Candidate candidate : mapCandidates) {
            System.out.println("CandidateId:" + candidate.getId() + " Name:" + candidate.getName() + " Skills:" + candidate.getSkills());
        }
    }

    public void getRating(Candidate candidate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CandidateId:" + candidate.getId() + " Name:" + candidate.getName() + " Skills:" + candidate.getSkills());
        System.out.println("\nEnter Rating");
        candidate.setRating(scanner.nextInt());
        candidate.setState("completed");
    }

    public void initResult() {
        boolean hasCompleted = InterviewPannelDataBase.getInstance().chechInterviewCompleted();
        if (hasCompleted) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n1.Based on Number of Candidates\n2.Based on Ratings\n0.Back");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        resultBasedOnNumber();
                        break;
                    case 2:
                        resultBasedOnRating();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid Input Enter Valid Input");
                }
            }
        } else {
            showMsg("Interview Not Completed");
        }
        ;
    }

    private void resultBasedOnRating() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter number of candidates");
        int number=scanner.nextInt();
        manageInterviewModel.getResutRating(number);
    }

    private void resultBasedOnNumber() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter number of candidates");
        int number=scanner.nextInt();
        manageInterviewModel.getResutNumber(number);
    }

    public void showResult(List<Candidate> result) {
        for(Candidate candidate:result){
            System.out.println("Name:"+candidate.getName()+" Rating:"+candidate.getRating());
        }
    }
}
