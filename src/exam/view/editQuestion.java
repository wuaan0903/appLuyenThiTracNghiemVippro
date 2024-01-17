/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package exam.view;

import admin.model.Config;
import static admin.model.Config.PASSWORD;
import admin.view.ExamForm;
import com.mysql.cj.protocol.x.Notice;
import com.sun.jdi.connect.spi.Connection;
import exam.view.*;
import exam.controller.ExamModify;
import exam.model.Question;
import java.awt.Window;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

/**
 *
 * @author admin
 */
public class editQuestion extends javax.swing.JFrame {

    /**
     * Creates new form addQuestion
     */
    List<Question> dataList = new ArrayList<>();
    int pos = 0;
    Question q;
    String nameExam1;
    int numberExam1;
    int soCau;

    public editQuestion() {
        initComponents();
        nameExam1 = "TEST";
        numberExam1 = 2;
        soCau = 3;
        for (int i = 0; i < soCau; i++) {
            Question q = new Question();
            q.setStatus(0);
            dataList.add(q);
        }
        loadDataFromDatabase();
        View();

    }

    public editQuestion(String s, int a, int b) {
        initComponents();
        setTitle("App Luyện Thi Trắc Nghiệm Design by Nhóm 6-21CN1");
        nameExam.setText("Môn học : " + s);
        numberExam.setText("ĐỀ THI SỐ " + a);
        nameExam1 = s;
        numberExam1 = a;
        soCau = b;
        cauHoi.setText("Câu hỏi " + (pos + 1));
        for (int i = 0; i < b; i++) {
            Question q = new Question();
            q.setStatus(0);
            dataList.add(q);
        }
        loadDataFromDatabase();
        View();

    }

    private void loadDataFromDatabase() {
        dataList = ExamModify.getQuestion(nameExam1, numberExam1);

    }

    public void View() {
        if (pos >= 0 && pos < dataList.size()) {
            cauHoi.setText("Câu Hỏi " + (pos + 1));
            nameExam.setText("Môn học : " + nameExam1);
            numberExam.setText("ĐỀ THI SỐ " + numberExam1);

            q = dataList.get(pos);
            this.question.setText(q.getQuestion());
            this.answerA.setText(q.getAnswerA());
            this.answerB.setText(q.getAnswerB());
            this.answerC.setText(q.getAnswerC());
            this.answerD.setText(q.getAnswerD());
            int answerValue = q.getAnswer();
            this.answerCorrect.setText(convertNumberToLetter(answerValue));
        }
    }

    private String convertNumberToLetter(int number) {
        switch (number) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            default:
                return "";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameExam = new javax.swing.JLabel();
        numberExam = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        backBtn1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        question = new javax.swing.JTextPane();
        cauHoi = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        answerA = new javax.swing.JTextPane();
        cauHoi1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        answerC = new javax.swing.JTextPane();
        cauHoi8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        answerCorrect = new javax.swing.JTextPane();
        cauHoi10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        answerD = new javax.swing.JTextPane();
        cauHoi9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        answerB = new javax.swing.JTextPane();
        cauHoi2 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameExam.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        nameExam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        numberExam.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        numberExam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        nextBtn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        nextBtn.setText("Câu tiếp");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        backBtn.setText("Câu sau");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        backBtn1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        backBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/left-arrow-circle-solid-24.png"))); // NOI18N
        backBtn1.setText("Quay lại");
        backBtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtn1ActionPerformed(evt);
            }
        });

        question.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jScrollPane1.setViewportView(question);

        cauHoi.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        cauHoi.setText("Câu hỏi 1 :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(cauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(cauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        answerA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        answerA.setPreferredSize(new java.awt.Dimension(445, 59));
        jScrollPane7.setViewportView(answerA);

        cauHoi1.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        cauHoi1.setText("Đáp án A :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cauHoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cauHoi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        answerC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        answerC.setPreferredSize(new java.awt.Dimension(445, 59));
        jScrollPane14.setViewportView(answerC);

        cauHoi8.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        cauHoi8.setText("Đáp án C :");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cauHoi8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cauHoi8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14))
                .addContainerGap())
        );

        answerCorrect.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jScrollPane16.setViewportView(answerCorrect);

        cauHoi10.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        cauHoi10.setText("Đúng :");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cauHoi10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cauHoi10, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addComponent(jScrollPane16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        answerD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        answerD.setPreferredSize(new java.awt.Dimension(445, 59));
        jScrollPane15.setViewportView(answerD);

        cauHoi9.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        cauHoi9.setText("Đáp án D :");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cauHoi9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cauHoi9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        answerB.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        answerB.setPreferredSize(new java.awt.Dimension(445, 59));
        jScrollPane8.setViewportView(answerB);

        cauHoi2.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        cauHoi2.setText("Đáp án B : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cauHoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cauHoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );

        saveBtn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        saveBtn.setText("Lưu");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(354, 354, 354)
                        .addComponent(numberExam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 34, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameExam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameExam, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numberExam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn)
                    .addComponent(nextBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(saveBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        if (pos - 1 >= 0) {
            if (!question.getText().isEmpty()
                    && !answerA.getText().isEmpty()
                    && !answerB.getText().isEmpty()
                    && !answerC.getText().isEmpty()
                    && !answerD.getText().isEmpty()
                    && !answerCorrect.getText().isEmpty()) {

                String selectedAnswer = answerCorrect.getText().toUpperCase();

                int answerValue = 0;

                switch (selectedAnswer) {
                    case "A":
                        answerValue = 1;
                        break;
                    case "B":
                        answerValue = 2;
                        break;
                    case "C":
                        answerValue = 3;
                        break;
                    case "D":
                        answerValue = 4;
                        break;
                    default:
                        JOptionPane.showMessageDialog(rootPane, "Giá trị đúng không hợp lệ.");
                        return; // Kết thúc phương thức nếu giá trị không hợp lệ
                }

                Question q = new Question(
                        nameExam1,
                        numberExam1,
                        question.getText(),
                        answerA.getText(),
                        answerB.getText(),
                        answerC.getText(),
                        answerD.getText(),
                        answerValue,
                        1
                );

                dataList.set(pos, q);
            }

            pos--;
            View();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Đã đến câu hỏi đầu tiên, không thể quay lại.");
        }
    }//GEN-LAST:event_backBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        if (!this.question.getText().isEmpty()
                && !this.answerA.getText().isEmpty()
                && !this.answerB.getText().isEmpty()
                && !this.answerC.getText().isEmpty()
                && !this.answerD.getText().isEmpty()
                && !this.answerCorrect.getText().isEmpty()) {

            String selectedAnswer = this.answerCorrect.getText().toUpperCase();
            int answerValue;

            switch (selectedAnswer) {
                case "A":
                    answerValue = 1;
                    break;
                case "B":
                    answerValue = 2;
                    break;
                case "C":
                    answerValue = 3;
                    break;
                case "D":
                    answerValue = 4;
                    break;
                default:
                    answerValue = 0; // Giá trị mặc định nếu không phải A, B, C, D
                    break;
            }

            if (answerValue != 0) { // Nếu giá trị hợp lệ
                Question q = new Question(
                        nameExam1,
                        numberExam1,
                        this.question.getText(),
                        this.answerA.getText(),
                        this.answerB.getText(),
                        this.answerC.getText(),
                        this.answerD.getText(),
                        answerValue,
                        1
                );

                dataList.set(pos, q);

                if (pos + 1 < soCau) {
                    pos++;
                    View();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Không thể chuyển sang câu hỏi tiếp theo. Hãy lưu câu hỏi nếu bạn đã nhập đủ câu hỏi.");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Giá trị đúng không hợp lệ.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống các ô trên !");
        }
    }//GEN-LAST:event_nextBtnActionPerformed

    private void backBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtn1ActionPerformed
        ExamForm ef = new ExamForm();
        ef.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtn1ActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (!this.question.getText().isEmpty()
                && !this.answerA.getText().isEmpty()
                && !this.answerB.getText().isEmpty()
                && !this.answerC.getText().isEmpty()
                && !this.answerD.getText().isEmpty()
                && !this.answerCorrect.getText().isEmpty()) {

            String selectedAnswer = this.answerCorrect.getText().toUpperCase();
            int answerValue;

            switch (selectedAnswer) {
                case "A":
                    answerValue = 1;
                    break;
                case "B":
                    answerValue = 2;
                    break;
                case "C":
                    answerValue = 3;
                    break;
                case "D":
                    answerValue = 4;
                    break;
                default:
                    answerValue = 0; // Giá trị mặc định nếu không phải A, B, C, D
                    break;
            }

            if (answerValue != 0) { // Nếu giá trị hợp lệ
                Question q = new Question(
                        nameExam1,
                        numberExam1,
                        this.question.getText(),
                        this.answerA.getText(),
                        this.answerB.getText(),
                        this.answerC.getText(),
                        this.answerD.getText(),
                        answerValue,
                        1
                );

                dataList.set(pos, q);
            }}
        
        ExamModify.deleteQưestion(nameExam1, numberExam1);
        for (Question que : dataList) {
        ExamModify.insertQuestion(que);
        }
        JOptionPane.showMessageDialog(rootPane, "Thêm câu hỏi vào đề thành công !");
        ((JFrame) SwingUtilities.getRoot(rootPane)).dispose();

        // Mở trang mới
        ExamForm ef = new ExamForm();
        ef.setVisible(true);
        
    }//GEN-LAST:event_saveBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editQuestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editQuestion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane answerA;
    private javax.swing.JTextPane answerB;
    private javax.swing.JTextPane answerC;
    private javax.swing.JTextPane answerCorrect;
    private javax.swing.JTextPane answerD;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton backBtn1;
    private javax.swing.JLabel cauHoi;
    private javax.swing.JLabel cauHoi1;
    private javax.swing.JLabel cauHoi10;
    private javax.swing.JLabel cauHoi2;
    private javax.swing.JLabel cauHoi8;
    private javax.swing.JLabel cauHoi9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel nameExam;
    private javax.swing.JButton nextBtn;
    private javax.swing.JLabel numberExam;
    private javax.swing.JTextPane question;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables
}
