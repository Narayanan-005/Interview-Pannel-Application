package com.zsgs.interviewpannel.managecandidate;

import com.zsgs.interviewpannel.model.Candidate;

import java.util.Scanner;

public class ManageCandidateView {
    private ManageCandidateModel manageCandidateModel;

    public ManageCandidateView() {
        manageCandidateModel = new ManageCandidateModel(this);
    }

    public void initAdd() {
        System.out.println("Enter Candidates Details Here");
        addCandidate();
    }

    private void addCandidate() {
        Scanner scanner = new Scanner(System.in);
        Candidate candidate = new Candidate();
        System.out.println("Enter Candidate Name");
        candidate.setName(scanner.nextLine());
        System.out.println("Enter Candidate EmailId");
        candidate.setMailId(scanner.nextLine());
        System.out.println("Enter Candidate PhoneNumber");
        candidate.setPhoneNumber(scanner.nextLine());
        System.out.println("Enter Candidate Skills");
        candidate.setSkills(scanner.nextLine());
        System.out.println("Enter Candidate Location");
        candidate.setLocation(scanner.nextLine());
        manageCandidateModel.addCandidate(candidate);
    }

    public void showErrorMessageAddCandidate(String erroMsg) {
        System.out.println("\n" + erroMsg);
        addCandidate();
    }

    public void showSucessAddCandidate(String s) {
        System.out.println("\n" + s);
        chechForAddCandidate();
    }

    private void chechForAddCandidate() {
        System.out.println("\nDo you wand to add more candidate \nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            addCandidate();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("Thank You for adding candidates");
        } else {
            System.out.println("Invalid Input Enter Valid input");
            chechForAddCandidate();
        }
    }

    public void showMsg(String candidateLimitExeed) {
        System.out.println("\n"+candidateLimitExeed);
    }
}
