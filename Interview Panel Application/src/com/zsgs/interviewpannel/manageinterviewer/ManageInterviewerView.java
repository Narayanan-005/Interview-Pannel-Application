package com.zsgs.interviewpannel.manageinterviewer;

import com.zsgs.interviewpannel.InterviewPannelApplication;
import com.zsgs.interviewpannel.datalayer.BackUpRetrive;
import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.interview.ManageInterviewView;
import com.zsgs.interviewpannel.login.LogInView;
import com.zsgs.interviewpannel.model.Credential;
import com.zsgs.interviewpannel.model.Interviewer;

import java.util.Scanner;

public class ManageInterviewerView {
    private MangeInterviewerModel mangeInterviewerModel;

    public ManageInterviewerView() {
        this.mangeInterviewerModel = new MangeInterviewerModel(this);
    }

    public void init() {
        System.out.println("Interviewer features");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n1.Show Candidates List\n2.Set Rating\n7.Log Out\n0.Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new ManageInterviewView().initShowCandidate();
                    break;
                case 2:
                    new ManageInterviewView().initSetRating();
                    break;
                case 7:
                    new LogInView().login();
                    return;
                case 0:
                    InterviewPannelDataBase.getInstance().backUp();
                    System.out.println("Thank You For Using " + InterviewPannelApplication.getInstance().getAppName());
                    return;
                default:
                    System.out.println("\nInvalid input Please Enter Valid Choice");
                    init();
            }
        }
    }


    public void initAdd() {
        System.out.println("Enter Inteviewer Details Here");
        addInterviewer();
    }

    private void addInterviewer() {
        Scanner scanner = new Scanner(System.in);
        Interviewer interviewer = new Interviewer();
        System.out.println("Enter Interviewer Name");
        interviewer.setName(scanner.nextLine());
        System.out.println("Enter Interviewer EmailId");
        interviewer.setEmailId(scanner.nextLine());
        System.out.println("Enter Interviewer PhoneNumber");
        interviewer.setPhoneNumber(scanner.nextLine());
        System.out.println("Enter Interviewer Location");
        interviewer.setLocarion(scanner.nextLine());
        mangeInterviewerModel.addInterviewer(interviewer);
    }

    public void showErrorMessageAddInterviewer(String erroMsg) {
        System.out.println("\n" + erroMsg);
        addInterviewer();
    }

    public void showSucessAddInterviewer(String s) {
        System.out.println("\n" + s);
        chechForAddInterviewer();
    }

    private void chechForAddInterviewer() {
        System.out.println("\nDo you wand to add more Interviewet \nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            addInterviewer();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("Thank You for adding Interviewer");
        } else {
            System.out.println("Invalid Input Enter Valid input");
            chechForAddInterviewer();
        }
    }

    public Credential addInterviewerCredentials() {
        Credential credential = new Credential();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCreate password");
        String passWord = scanner.next();
        System.out.println("Confirm Password");
        if (passWord.equals(scanner.next())) {
            credential.setPassWord(passWord);
        }else{
            System.out.println("Wrong Password");
            addInterviewerCredentials();
        }
        return credential;
    }

    public void showMsg(String interviewverLimitExeed) {
        System.out.println("\n"+interviewverLimitExeed);
    }
}
