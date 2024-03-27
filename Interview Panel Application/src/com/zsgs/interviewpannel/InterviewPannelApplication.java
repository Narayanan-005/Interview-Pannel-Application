package com.zsgs.interviewpannel;

import com.zsgs.interviewpannel.datalayer.BackUpRetrive;
import com.zsgs.interviewpannel.datalayer.InterviewPannelDataBase;
import com.zsgs.interviewpannel.login.LogInView;
import com.zsgs.interviewpannel.model.Credential;
import com.zsgs.interviewpannel.model.Interviewer;

public class InterviewPannelApplication {
    private static InterviewPannelApplication interviewPannelApplication;
    private String appName = "Interview Pannel Application";
    private String appVertion = "0.0.1";

    private InterviewPannelApplication() {

    }

    public static InterviewPannelApplication getInstance() {
        if (interviewPannelApplication == null) {
            interviewPannelApplication = new InterviewPannelApplication();
        }
        return interviewPannelApplication;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVertion() {
        return appVertion;
    }

    public void start() {
        InterviewPannelDataBase.getInstance().retrive();
        InterviewPannelDataBase.getInstance().chechAdmin();
        new LogInView().init();
    }

    public static void main(String[] args) {
        InterviewPannelApplication.getInstance().start();
    }
}
