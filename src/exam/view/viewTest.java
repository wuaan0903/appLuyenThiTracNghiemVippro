/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package exam.view;

import admin.model.Config;
import com.mysql.cj.protocol.x.Notice;
import com.sun.jdi.connect.spi.Connection;

import exam.controller.ExamModify;
import exam.model.Question;
import exam.model.exam;
import java.awt.Color;
import java.awt.image.TileObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Timer;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import user.view.HistoryList;

/**
 *
 * @author admin
 */
public class viewTest extends javax.swing.JFrame {

    /**
     * Creates new form viewTest
     */
    List<Question> dataList;
    List<exam> ex;
    Question q;
    int pos = 0;
    private int totalTimeInSeconds;
    private int initialTimeInSeconds;// Thời gian tổng cộng cho bài kiểm tra tính bằng giây (300 giây = 5 phút)
    private Timer countdownTimer;

    public class DatabaseConnection {

        private static java.sql.Connection connection;

        public static java.sql.Connection getConnection() {
            try {
                if (connection == null || connection.isClosed()) {

                    connection = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    public viewTest(exam e, String id, String name) {
        initComponents();
        setTitle("App Luyện Thi Trắc Nghiệm Design by Nhóm 6-21CN1");
        idU.setText(id);
        nameU.setText(name);
        dataList = ExamModify.getQuestion(e.getNameExam(), e.getNumberExam());
        View();
        ViewList();
        nameExam.setText(e.getNameExam());
        numberExa.setText("" + e.getNumberExam());
        totalTimeInSeconds = e.getThoigian() * 60;
        startCountdown();
    }

    private void startCountdown() {
        initialTimeInSeconds = totalTimeInSeconds;
        countdownTimer = new Timer(1000, e -> {
            if (totalTimeInSeconds > 0) {
                int minutes = totalTimeInSeconds / 60;
                int seconds = totalTimeInSeconds % 60;

                String timeString = String.format("%02d:%02d", minutes, seconds);
                time.setText(timeString);

                totalTimeInSeconds--; // Giảm thời gian tổng cộng
            } else {
                // Hết giờ
                time.setText("Hết giờ!");
                countdownTimer.stop();
                ResultForm rf = new ResultForm(this.getKq(), this.getScore(), this.getTime(), dataList, idU.getText(), nameU.getText(), nameExam.getText());
                rf.setVisible(true);
                this.dispose();
                // Dừng đồng hồ đếm ngược khi hết giờ
                // Tuỳ chọn: thực hiện các hành động cần thiết khi hết giờ
            }
        });
        countdownTimer.start(); // Bắt đầu đồng hồ đếm ngược
    }

    int getTime() {

//        int timeSpentInSeconds = initialTimeInSeconds - totalTimeInSeconds;
//        ResultForm rf = new ResultForm(this.getScore(), timeSpentInSeconds, dataList, idU.getText(),nameU.getText(),nameExam.getText());
//        rf.setVisible(true);
//        this.dispose();
        return initialTimeInSeconds - totalTimeInSeconds;
    }

    public void View() {

        q = dataList.get(pos);
        this.question.setText("<html>"+"Câu số " + (pos + 1) + " : " + q.getQuestion()+ "</html>");
        this.answerA.setText("<html>A." + q.getAnswerA() + "</html>");
        this.answerB.setText("<html>B." + q.getAnswerB()+"</html>");
        this.answerC.setText("<html>"+"C." + q.getAnswerC()+ "</html>");
        this.answerD.setText("<html>D." + q.getAnswerD() + "</html>");

        switch (q.getStatus()) {
            case 1:
                OnOff(true, false, false, false);
                break;
            case 2:
                OnOff(false, true, false, false);
                break;
            case 3:
                OnOff(false, false, true, false);
                break;
            case 4:
                OnOff(false, false, false, true);
                break;
            default:
                this.buttonGroup1.clearSelection();
                break;
        }
    }

    public void ViewList() {
        DefaultListModel model = new DefaultListModel();
        this.listQ.setModel(model);
        int n = 1;
        for (Question x : dataList) {
            model.addElement("Câu số : " + n++);

        }
    }

    public int Choice() {
        int n = 0;
        if (this.answerA.isSelected()) {
            n = 1;
        }
        if (this.answerB.isSelected()) {
            n = 2;
        }
        if (this.answerC.isSelected()) {
            n = 3;
        }
        if (this.answerD.isSelected()) {
            n = 4;
        }
        return n;
    }

    double getScore() {
        double d = 0;
        for (Question x : dataList) {
            if (x.getAnswer() == x.getStatus()) {
                d++;
            }
        }

        double score = d * (10.0 / dataList.size());

        // Sử dụng DecimalFormat để chỉ giữ lại hai số sau dấu phẩy
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(score));
    }

    String getKq() {
        int correctAnswers = 0;
        for (Question x : dataList) {
            if (x.getAnswer() == x.getStatus()) {
                correctAnswers++;
            }
        }
        int totalQuestions = dataList.size();
        return correctAnswers + "/" + totalQuestions; // Trả về số câu đúng trên tổng số câu hỏi
    }

    String formatScore(double score) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(score);
    }

    String getCurrentDateTimeAsString() {
        Date currentDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return dateFormat.format(currentDateTime);
    }

    public void OnOff(boolean a, boolean b, boolean c, boolean d) {
        this.answerA.setSelected(a);
        this.answerB.setSelected(b);
        this.answerC.setSelected(c);
        this.answerD.setSelected(d);
    }

    public void goToNextQuestion() {
        if (pos < dataList.size() - 1) { // Kiểm tra xem còn câu hỏi tiếp theo không
            q.setStatus(Choice()); // Lấy trạng thái câu hỏi hiện tại
            dataList.set(pos, q); // Cập nhật câu hỏi hiện tại trong danh sách

            pos++; // Tăng chỉ số câu hỏi để chuyển đến câu hỏi tiếp theo
            View(); // Hiển thị câu hỏi tiếp theo
//            ViewResult(); // Cập nhật kết quả
        }
        // Có thể thêm thông báo nếu không còn câu hỏi tiếp theo
    }

    private String convertSecondsToTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        question = new javax.swing.JLabel();
        BackBtn = new javax.swing.JButton();
        NextBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        answerA = new javax.swing.JRadioButton();
        answerB = new javax.swing.JRadioButton();
        answerD = new javax.swing.JRadioButton();
        answerC = new javax.swing.JRadioButton();
        submitBtn = new javax.swing.JButton();
        ExitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listQ = new javax.swing.JList<>();
        time = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        idU = new javax.swing.JLabel();
        nameU = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        dfdf = new javax.swing.JLabel();
        numberExam = new javax.swing.JLabel();
        nameExam = new javax.swing.JLabel();
        sds = new javax.swing.JLabel();
        numberExa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        question.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        question.setForeground(new java.awt.Color(255, 255, 255));
        question.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        question.setText("Câu số 1 :       1+1 = ?");
        question.setPreferredSize(new java.awt.Dimension(500, 59));

        BackBtn.setBackground(new java.awt.Color(0, 153, 153));
        BackBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/left-arrow-solid-60 (1).png"))); // NOI18N
        BackBtn.setBorder(null);
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        NextBtn.setBackground(new java.awt.Color(0, 153, 153));
        NextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/right-arrow-solid-60 (1).png"))); // NOI18N
        NextBtn.setBorder(null);
        NextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(738, Short.MAX_VALUE)
                    .addComponent(NextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NextBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        answerA.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(answerA);
        answerA.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        answerA.setForeground(new java.awt.Color(255, 255, 255));
        answerA.setText("A. 1");
        answerA.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        answerA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        answerA.setPreferredSize(new java.awt.Dimension(433, 59));

        answerB.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(answerB);
        answerB.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        answerB.setForeground(new java.awt.Color(255, 255, 255));
        answerB.setText("B. 2");
        answerB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        answerB.setPreferredSize(new java.awt.Dimension(433, 59));

        answerD.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(answerD);
        answerD.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        answerD.setForeground(new java.awt.Color(255, 255, 255));
        answerD.setText("D. 4");
        answerD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        answerD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        answerD.setPreferredSize(new java.awt.Dimension(433, 59));
        answerD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerDActionPerformed(evt);
            }
        });

        answerC.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(answerC);
        answerC.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        answerC.setForeground(new java.awt.Color(255, 255, 255));
        answerC.setText("C. 3");
        answerC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        answerC.setPreferredSize(new java.awt.Dimension(433, 59));
        answerC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(answerC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 128, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(answerA, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answerB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answerC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answerD, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        submitBtn.setBackground(new java.awt.Color(0, 153, 153));
        submitBtn.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(0, 0, 0));
        submitBtn.setText("Nộp bài");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        ExitBtn.setBackground(new java.awt.Color(0, 153, 153));
        ExitBtn.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        ExitBtn.setForeground(new java.awt.Color(0, 0, 0));
        ExitBtn.setText("Thoát");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        listQ.setBackground(new java.awt.Color(204, 255, 255));
        listQ.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        listQ.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        listQ.setForeground(new java.awt.Color(51, 51, 51));
        listQ.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Câu 1", "Câu 2", "Câu 3", "Câu 4", "Câu 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listQ.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listQValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listQ);

        time.setBackground(new java.awt.Color(0, 0, 0));
        time.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        time.setForeground(new java.awt.Color(0, 0, 0));
        time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/timer-regular-36.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        idU.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        idU.setForeground(new java.awt.Color(0, 0, 0));
        idU.setText("1");

        nameU.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        nameU.setForeground(new java.awt.Color(0, 0, 0));
        nameU.setText("Phan Van Tươi");

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("MSV:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idU, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameU, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(nameU, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idU, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        dfdf.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        dfdf.setForeground(new java.awt.Color(0, 0, 0));
        dfdf.setText("Môn thi :");

        numberExam.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        numberExam.setForeground(new java.awt.Color(0, 0, 0));
        numberExam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        nameExam.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        nameExam.setForeground(new java.awt.Color(0, 0, 0));
        nameExam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        sds.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        sds.setForeground(new java.awt.Color(0, 0, 0));
        sds.setText("Đề số :");

        numberExa.setFont(new java.awt.Font("Montserrat", 1, 20)); // NOI18N
        numberExa.setForeground(new java.awt.Color(0, 0, 0));
        numberExa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(numberExam, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sds, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberExa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(dfdf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameExam, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dfdf, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameExam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(numberExam, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sds, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(numberExa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ExitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(321, 321, 321))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(submitBtn)
                        .addGap(12, 12, 12)
                        .addComponent(ExitBtn))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        listExam le = new listExam(nameExam.getText(), idU.getText(), nameU.getText());
        le.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void listQValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listQValueChanged
        // TODO add your handling code here:
        int n = Choice();
        q.setStatus(n);
        dataList.set(pos, q);
        pos = this.listQ.getSelectedIndex();
        View();
    }//GEN-LAST:event_listQValueChanged

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed

        int lastQuestionStatus = Choice();
        q.setStatus(lastQuestionStatus);
        dataList.set(pos, q);
        double score = this.getScore();
        int timer = this.getTime();
        String formattedTime = convertSecondsToTime(timer);
        String kqU = this.getKq();
        String date = this.getCurrentDateTimeAsString();

        // Lấy thông tin người dùng hoặc các dữ liệu cần thiết từ form
        String selectedName = (String) this.nameExam.getText(); // Thay thế nameExam bằng thành phần tương ứng trên form của bạn
        String NameUser = (String) this.nameU.getText();
        int numberEx = Integer.parseInt(this.numberExa.getText());
        int userID = Integer.parseInt(this.idU.getText()); // Thay thế idd bằng thành phần tương ứng trên form của bạn

        // Kết nối đến cơ sở dữ liệu
        try (java.sql.Connection connection = DatabaseConnection.getConnection()) {
            // Thực hiện truy vấn SQL để chèn dữ liệu vào bảng history_test
            String sql = "INSERT INTO history_test (user_id,name, nameExam, ID_exam,kq, total, time_completed, date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userID);
                preparedStatement.setString(2, NameUser);
                preparedStatement.setString(3, selectedName);
                preparedStatement.setInt(4, numberEx);
                preparedStatement.setString(5, kqU);
                preparedStatement.setString(6, String.valueOf(score));
                preparedStatement.setString(7, formattedTime);
                preparedStatement.setString(8, date);

                // Thực hiện lưu dữ liệu vào bảng
                preparedStatement.executeUpdate();
            }

            // Hiển thị cửa sổ kết quả
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi, có thể hiển thị thông báo lỗi cho người dùng nếu cần
        }
        ResultForm rf = new ResultForm(this.getKq(), this.getScore(), this.getTime(), dataList, idU.getText(), nameU.getText(), nameExam.getText());
        rf.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_submitBtnActionPerformed

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        // TODO add your handling code here:
        if (pos > 0) { // Kiểm tra xem còn câu hỏi tiếp theo không
            q.setStatus(Choice()); // Lấy trạng thái câu hỏi hiện tại
            dataList.set(pos, q); // Cập nhật câu hỏi hiện tại trong danh sách

            pos--; // Tăng chỉ số câu hỏi để chuyển đến câu hỏi tiếp theo
            View(); // Hiển thị câu hỏi tiếp theo
//            ViewResult(); // Cập nhật kết quả
        }
        // Có thể thêm thông báo nếu không còn câu hỏi tiếp theo
    }//GEN-LAST:event_BackBtnActionPerformed

    private void NextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextBtnActionPerformed
        // TODO add your handling code here:
        goToNextQuestion();
    }//GEN-LAST:event_NextBtnActionPerformed

    private void answerCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answerCActionPerformed

    private void answerDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answerDActionPerformed

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
            java.util.logging.Logger.getLogger(viewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton NextBtn;
    private javax.swing.JRadioButton answerA;
    private javax.swing.JRadioButton answerB;
    private javax.swing.JRadioButton answerC;
    private javax.swing.JRadioButton answerD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel dfdf;
    private javax.swing.JLabel idU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listQ;
    private javax.swing.JLabel nameExam;
    private javax.swing.JLabel nameU;
    private javax.swing.JLabel numberExa;
    private javax.swing.JLabel numberExam;
    private javax.swing.JLabel question;
    private javax.swing.JLabel sds;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
