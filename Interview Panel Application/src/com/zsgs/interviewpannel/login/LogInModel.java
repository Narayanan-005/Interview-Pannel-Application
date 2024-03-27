package com.zsgs.interviewpannel.login;

import com.zsgs.interviewpannel.InterviewPannelApplication;
import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;

class LogInModel {
    private LogInView logInView;

    LogInModel(LogInView logInView) {
        this.logInView = logInView;
    }

    public void login(String userName, String password) {
        if (InterviewPannelDataBase.getInstance().validateUserNameFound(userName)) {
            String resString = InterviewPannelDataBase.getInstance().validateUserPassWord(userName, password);
            if (resString.equals("A")) {
                logInView.onSucessMessage("login Sucessfull\n Welcome to " + InterviewPannelApplication.getInstance().getAppName());
            } else if (resString.equals("I")) {
                if (InterviewPannelDataBase.getInstance().getCompany() != null) {
                    logInView.onSucessMessageInterviewer("Login Sucessful\n Welcome to " + InterviewPannelApplication.getInstance().getAppName());
                } else {
                    logInView.onFailedMessage("Copany not setup please contact admin");
                }
            } else {
                logInView.onFailedMessage("Invalid Password");
            }
        } else {
            logInView.onFailedMessage("Invalid User Name");
        }
    }

//    private boolean validateUserPassword(String userName, String password) {
//        return userName.equals("Zsgs") && password.equals("Admin");
//    }
//
//    private boolean validateUser(String userName) {
//        return userName.equals("Zsgs");
//    }
}
