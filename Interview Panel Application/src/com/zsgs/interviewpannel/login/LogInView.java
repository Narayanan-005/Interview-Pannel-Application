package com.zsgs.interviewpannel.login;

import com.zsgs.interviewpannel.InterviewPannelApplication;
import com.zsgs.interviewpannel.company.CompanySetUpView;
import com.zsgs.interviewpannel.manageinterviewer.ManageInterviewerView;

import java.util.Scanner;

public class LogInView {
    private LogInModel logInModel;

    public LogInView() {
        logInModel = new LogInModel(this);
    }

    public void init() {
        System.out.println("\n--- " + InterviewPannelApplication.getInstance().getAppName() + " ---" + "\nversion " + InterviewPannelApplication.getInstance().getAppVertion());
        System.out.println("\nPlease login to proceed");
        login();
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter User Name");
        String userName = scanner.next();
        System.out.println("Enter Password");
        String password = scanner.next();
        logInModel.login(userName, password);
    }

    public void onFailedMessage(String invalidUserName) {
        System.out.println(invalidUserName);
        chechForLogIn();
    }

    private void chechForLogIn() {
        System.out.println("\nDo you want to try again\nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            login();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\nThank You For Using Application");
        } else {
            System.out.println("\nInvalid Choice enter valid choice");
            chechForLogIn();
        }
    }

    public void onSucessMessage(String loginSucessfull) {
        System.out.println("\n" + loginSucessfull);
        new CompanySetUpView().init();
    }

    public void onSucessMessageInterviewer(String loginSucessful) {
        System.out.println("\n"+loginSucessful);
        new ManageInterviewerView().init();
    }
}
