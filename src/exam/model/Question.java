/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam.model;

/**
 *
 * @author admin
 */
public class Question {
    private int id;
    private String NameExam;
    private int numberExam;
    private String question;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    private int answer;
    private int status;

    public Question() {
    }

    
    public Question(String NameExam, int numberExam, String question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, int answer, int status) {
        this.NameExam = NameExam;
        this.numberExam = numberExam;
        this.question = question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.answer = answer;
        this.status = status;
    }
    
    

    public Question(int id, String NameExam, int numberExam, String question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, int answer, int status) {
        this.id = id;
        this.NameExam = NameExam;
        this.numberExam = numberExam;
        this.question = question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.answer = answer;
        this.status = status;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getNameExam() {
        return NameExam;
    }

    public void setNameExam(String NameExam) {
        this.NameExam = NameExam;
    }

    public int getNumberExam() {
        return numberExam;
    }

    public void setNumberExam(int numberExam) {
        this.numberExam = numberExam;
    }
    

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String AnswerA) {
        this.AnswerA = AnswerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String AnswerB) {
        this.AnswerB = AnswerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String AnswerC) {
        this.AnswerC = AnswerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String AnswerD) {
        this.AnswerD = AnswerD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}