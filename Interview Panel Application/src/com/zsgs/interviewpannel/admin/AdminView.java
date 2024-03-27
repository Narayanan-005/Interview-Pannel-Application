package com.zsgs.interviewpannel.admin;

import com.zsgs.interviewpannel.InterviewPannelApplication;
import com.zsgs.interviewpannel.datalayer.BackUpRetrive;
import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.interview.ManageInterviewView;
import com.zsgs.interviewpannel.login.LogInView;
import com.zsgs.interviewpannel.managecandidate.ManageCandidateView;
import com.zsgs.interviewpannel.manageinterviewer.ManageInterviewerView;

import java.util.Scanner;

public class AdminView {
    private AdminModel adminModel;

    public AdminView() {
        this.adminModel = new AdminModel(this);
    }

    public void init() {
        System.out.println("Admin features");
        while (true) {
            System.out.println("\n1.Add Candidates\n2.Add Interviewer\n3.Map Candidates\n4.Show Result\n7.Log Out\n0.Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    new ManageCandidateView().initAdd();
                    break;
                case 2:
                    new ManageInterviewerView().initAdd();
                    break;
                case 3:
                    new ManageInterviewView().initMap();
                    break;
                case 4:
                    new ManageInterviewView().initResult();
                    break;
                case 7:
                    new LogInView().login();
                    return;
                case 0:
                    InterviewPannelDataBase.getInstance().backUp();
                    System.out.println("Thank You For Using "+ InterviewPannelApplication.getInstance().getAppName());
                    return;
                default:
                    System.out.println("\nInvalid Input Please Enter Valid Input");
            }
        }
    }
}
