package com.zsgs.interviewpannel.datalayer;

import com.zsgs.interviewpannel.model.Candidate;
import com.zsgs.interviewpannel.model.Company;
import com.zsgs.interviewpannel.model.Credential;
import com.zsgs.interviewpannel.model.Interviewer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterviewPannelDataBase {
    private static InterviewPannelDataBase interviewPannelDataBase;
    private Set<Credential> credentialSet = new HashSet<>();
    private List<Candidate> candidateList = new ArrayList<>();
    private List<Interviewer> interviewerList = new ArrayList<>();
    private List<Candidate> mapCandidates = new ArrayList<>();
    private List<Candidate> resultCandidates = new ArrayList<>();
    private Credential currentCredential;
    private Company company;

    private InterviewPannelDataBase() {
    }

    public void insertCompany(Company company) {
        this.company = company;
    }

    public void chechAdmin() {
        boolean hasAdmin = false;
        for (Credential credential : credentialSet) {
            if (credential.getUserName().equals("Zsgs") && credential.getPassWord().equals("Admin") && credential.getRole() == 'A') {
                hasAdmin = true;
            }
        }
        if (!hasAdmin) {
            Credential credential1 = new Credential();
            credential1.setUserName("Zsgs");
            credential1.setPassWord("Admin");
            credential1.setRole('A');
            credentialSet.add(credential1);
        }
    }

    private Credential getCredential(String userName) {
        for (Credential credential : credentialSet) {
            if (credential.getUserName().equals(userName)) {
                return credential;
            }
        }
        return null;
    }

    public boolean validateUserNameFound(String userName) {
        boolean hasUser = false;
        for (Credential credential : credentialSet) {
            if (credential.getUserName().equals(userName)) {
                currentCredential = credential;
                hasUser = true;
                break;
            }
        }
        return hasUser;
//        Credential credential = getCredential(userName);
//        if (credential == null) {
//            return false;
//        }
//        currentCredential = credential;
//        return true;
    }

    public boolean validateCredentialUsernotFound(String emailId) {
        boolean hasUser = false;
        for (Credential credential : credentialSet) {
            if (credential.getUserName().equals(emailId)) {
                hasUser = true;
                break;
            }
        }
        return !hasUser;
    }

    public String validateUserPassWord(String userName, String password) {
        if (currentCredential.getPassWord().equals(password)) {
            return "" + currentCredential.getRole();
        }
        return "F";
    }


    public static InterviewPannelDataBase getInstance() {
        if (interviewPannelDataBase == null) {
            interviewPannelDataBase = new InterviewPannelDataBase();
        }
        return interviewPannelDataBase;
    }

    public Company getCompany() {
        return company;
    }

    public boolean candidateLimitChech() {
        if (candidateList.size() >= 10) {
            return false;
        }
        return true;
    }

    public boolean addCandidate(Candidate candidate) {
        boolean hasCandidate = false;
        for (Candidate candidate1 : candidateList) {
            if (candidate.getMailId().equals(candidate1.getMailId())) {
                hasCandidate = true;
            }
        }
        if (hasCandidate) {
            return false;
        } else {
            if (candidateList.size() == 0) {
                candidate.setId(1);
            } else {
                Candidate preCandidate = candidateList.get(candidateList.size() - 1);
                candidate.setId(preCandidate.getId() + 1);
            }
            return candidateList.add(candidate);
        }
    }

    public boolean interviewerLimitCheck() {
        if (interviewerList.size() >= 1) {
            return false;
        }
        return true;
    }

    public boolean addInterviewer(Interviewer interviewer) {
        boolean hasInterviewer = false;
        for (Interviewer interviewer1 : interviewerList) {
            if (interviewer.getEmailId().equals(interviewer1.getEmailId())) {
                hasInterviewer = true;
                break;
            }
        }
        if (hasInterviewer) {
            return false;
        } else {
            if (interviewerList.size() == 0) {
                interviewer.setId(1);
            } else {
                Interviewer preInterviewer = interviewerList.get(interviewerList.size() - 1);
                interviewer.setId(preInterviewer.getId() + 1);
            }
            return interviewerList.add(interviewer);
        }
    }

    public boolean addCredential(Credential credential) {
        boolean hasCredential = false;
        for (Credential credential1 : credentialSet) {
            if (credential.getUserName().equals(credential1.getUserName())) {
                hasCredential = true;
                break;
            }
        }
        if (hasCredential) {
            return false;
        } else {
            credentialSet.add(credential);
            return true;
        }
    }


    public void retrive() {
        this.company = BackUpRetrive.getInstance().retriveCompany();
        this.credentialSet = BackUpRetrive.getInstance().retriveCredentials();
        this.candidateList = BackUpRetrive.getInstance().retriveCandidates();
        this.interviewerList = BackUpRetrive.getInstance().retriveInteviewers();
    }

    public void backUp() {
        BackUpRetrive.getInstance().backUpCompany(company);
        BackUpRetrive.getInstance().backUpCredentials(credentialSet);
        BackUpRetrive.getInstance().backUpCandidates(candidateList);
        BackUpRetrive.getInstance().backUpInterviewers(interviewerList);
    }


    public boolean mapCandidates() {
        if (this.mapCandidates.addAll(candidateList)) {
            mapCandidates.forEach(candidate -> candidate.setState("processing"));
            return true;
        }
        return false;
    }

    public List<Candidate> getMapCandidates() {
        return mapCandidates;
    }

    public boolean chechInterviewCompleted() {
        if (resultCandidates.size() == 0 || resultCandidates.get(resultCandidates.size() - 1).getState().equals("waiting") || resultCandidates.get(resultCandidates.size() - 1).getState().equals("processing")) {
            return false;
        }
        sortResult();
        return true;
    }

    private void sortResult() {
        resultCandidates.sort((a, b) -> b.getRating() - a.getRating());
    }

    public List<Candidate> getResultNumber(int number) {
        if (number >= mapCandidates.size()) {
            return mapCandidates;
        } else {
            List<Candidate> result = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                result.add(mapCandidates.get(i));
            }
            return result;
        }
    }

    public List<Candidate> getResultRating(int number) {
        List<Candidate> result = new ArrayList<>();
        for (Candidate candidate : mapCandidates) {
            if (candidate.getRating() >= number) {
                result.add(candidate);
            }
        }
        return result;
    }

    public void setResult(List<Candidate> mapCandidates) {
        resultCandidates.addAll(mapCandidates);
    }
}
