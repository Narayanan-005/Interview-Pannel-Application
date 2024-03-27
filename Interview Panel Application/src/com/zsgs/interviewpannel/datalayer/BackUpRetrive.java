package com.zsgs.interviewpannel.datalayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zsgs.interviewpannel.model.Candidate;
import com.zsgs.interviewpannel.model.Company;
import com.zsgs.interviewpannel.model.Credential;
import com.zsgs.interviewpannel.model.Interviewer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackUpRetrive {
    private static BackUpRetrive backUpRetrive;

    private BackUpRetrive() {

    }
    private void createFileIfNotExist(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("cound't create file");
            }
        }
    }

    public static BackUpRetrive getInstance() {
        if (backUpRetrive == null) {
            backUpRetrive = new BackUpRetrive();
        }
        return backUpRetrive;
    }


    public void backUpCompany(Company company) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/company.json");
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(file);
            String string = gson.toJson(company);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("cound't write file");
        }
    }

    public Company retriveCompany() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/company.json");
        createFileIfNotExist(file);
        Company company = null;
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer stringBuffer = new StringBuffer();
            String string = reader.readLine();
            while (string != null) {
                stringBuffer.append(string);
                string = reader.readLine();
            }
            company = gson.fromJson(stringBuffer.toString(), Company.class);
            reader.close();
        } catch (IOException e) {
            System.out.println("coldn't read from file");
        }
        return company;
    }

    public void backUpCredentials(Set<Credential> credentialSet) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/Credentials.json");
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(file);
            String string = gson.toJson(credentialSet);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("cound't write file");
        }
    }

    public Set<Credential> retriveCredentials() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/Credentials.json");
        createFileIfNotExist(file);
        Set<Credential> credentialSet = new HashSet<>();
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer stringBuffer = new StringBuffer();
            String string = reader.readLine();
            while (string != null) {
                stringBuffer.append(string);
                string = reader.readLine();
            }
            if(stringBuffer.isEmpty()){
                return credentialSet;
            }
            Type type = new TypeToken<Set<Credential>>() {
            }.getType();
            credentialSet = gson.fromJson(stringBuffer.toString(), type);
            reader.close();
        } catch (IOException e) {
            System.out.println("coldn't read from file");
        }
        return credentialSet;
    }

    public void backUpCandidates(List<Candidate> candidateList) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/Candidates.json");
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(file);
            String string = gson.toJson(candidateList);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("cound't write file");
        }
    }

    public List<Candidate> retriveCandidates() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/Candidates.json");
        createFileIfNotExist(file);
        List<Candidate> candidateList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer stringBuffer = new StringBuffer();
            String string = reader.readLine();
            while (string != null) {
                stringBuffer.append(string);
                string = reader.readLine();
            }
            if(stringBuffer.isEmpty()){
                return candidateList;
            }
            Type type = new TypeToken<List<Candidate>>() {
            }.getType();
            candidateList = gson.fromJson(stringBuffer.toString(), type);
            reader.close();
        } catch (IOException e) {
            System.out.println("coldn't read from file");
        }
        return candidateList;
    }

    public void backUpInterviewers(List<Interviewer> interviewerList) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/Interviewers.json");
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter(file);
            String string = gson.toJson(interviewerList);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("cound't write file");
        }
    }

    public List<Interviewer> retriveInteviewers() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Interview_Panel_App/InterviewJson/Interviewers.json");
        createFileIfNotExist(file);
        List<Interviewer> interviewerList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer stringBuffer = new StringBuffer();
            String string = reader.readLine();
            while (string != null) {
                stringBuffer.append(string);
                string = reader.readLine();
            }
            if(stringBuffer.isEmpty()){
                return interviewerList;
            }
            Type type = new TypeToken<List<Interviewer>>() {
            }.getType();
            interviewerList = gson.fromJson(stringBuffer.toString(), type);
            reader.close();
        } catch (IOException e) {
            System.out.println("coldn't read from file");
        }
        return interviewerList;
    }
}
