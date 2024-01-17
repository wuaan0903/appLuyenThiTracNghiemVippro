/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package exam.view;

import exam.controller.ExamModify;
import exam.model.Question;
import java.awt.Image;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class HistoryTest extends javax.swing.JFrame {

    /**
     * Creates new form HistoryTest
     */
    List<Question> dataList;
    Question q;
    int pos = 0;
    public HistoryTest() {
        initComponents();
        setTitle("App Luyện Thi Trắc Nghiệm Design by Nhóm 6-21CN1");
        View();
        ViewList();
    }
    public HistoryTest(String time, String score,List<Question> q,String idd, String name, String nameExam)
    {
        initComponents();
        idUser.setText(idd);
        nameUser.setText(name);
        nameEx.setText(nameExam);
        timeU.setText(time);
        scoreU.setText(score);
        dataList = q;
        View();
        ViewList();

    }
    
    public void View() {
        
        q = dataList.get(pos);
        this.question.setText("<html>" +"Câu số " + (pos + 1) + " : " + q.getQuestion()+ "</html>");
        this.answerA.setText("<html>" +"A." + q.getAnswerA()+ "</html>");
        this.answerB.setText("<html>" +"B." + q.getAnswerB()+ "</html>");
        this.answerC.setText("<html>" +"C." + q.getAnswerC()+ "</html>");
        this.answerD.setText("<html>" +"D." + q.getAnswerD()+ "</html>");
        

        this.iconCheckA.setVisible(false);
        this.iconCheckB.setVisible(false);
        this.iconCheckC.setVisible(false);
        this.iconCheckD.setVisible(false);
        
        ImageIcon iconCheck = new ImageIcon("E:\\CODE\\App--Final 171 1\\src\\image\\check-circle-solid-24.png");
        switch (q.getAnswer()) {
            case 1:
                CheckCorrect(true, false, false, false);
                this.iconCheckA.setIcon(iconCheck);
                break;
            case 2:
                CheckCorrect(false, true, false, false);
                this.iconCheckB.setIcon(iconCheck);
                break;
            case 3:
                CheckCorrect(false, false, true, false);
                this.iconCheckC.setIcon(iconCheck);

                break;
            case 4:
                CheckCorrect(false, false, false, true);
                this.iconCheckD.setIcon(iconCheck);
                break;
        }
        if(q.getAnswer()!=q.getStatus())
        {
            
            ImageIcon iconX = new ImageIcon("E:\\CODE\\App--Final 171 1\\src\\image\\x-circle-regular-24.png");
            switch (q.getStatus()) {
            case 1:
                this.iconCheckA.setVisible(true);
                
                this.iconCheckA.setIcon(iconX);
                break;
            case 2:
                this.iconCheckB.setVisible(true);
                
                this.iconCheckB.setIcon(iconX);
                break;
            case 3:
                this.iconCheckC.setVisible(true);
                
                this.iconCheckC.setIcon(iconX);
                break;
            case 4:
                this.iconCheckD.setVisible(true);
                
                this.iconCheckD.setIcon(iconX);
                break;
        }
        }
        

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
    
    public void OnOff(boolean a, boolean b, boolean c, boolean d) {
        this.answerA.setSelected(a);
        this.answerB.setSelected(b);
        this.answerC.setSelected(c);
        this.answerD.setSelected(d);
    }
    public void CheckCorrect(boolean a,boolean b,boolean c,boolean d)
    {
        this.iconCheckA.setVisible(a);
        this.iconCheckB.setVisible(b);
        this.iconCheckC.setVisible(c);
        this.iconCheckD.setVisible(d);
        
    }
    public void CheckFalse(boolean a,boolean b,boolean c,boolean d)
    {
        this.iconCheckA.setVisible(a);
        this.iconCheckB.setVisible(b);
        this.iconCheckC.setVisible(c);
        this.iconCheckD.setVisible(d);
        
    }
    public void goToNextQuestion() {
        if (pos < dataList.size()-1) { // Kiểm tra xem còn câu hỏi tiếp theo không
            q.setStatus(Choice()); // Lấy trạng thái câu hỏi hiện tại
            dataList.set(pos, q); // Cập nhật câu hỏi hiện tại trong danh sách

            pos++; // Tăng chỉ số câu hỏi để chuyển đến câu hỏi tiếp theo
            View(); // Hiển thị câu hỏi tiếp theo

        }
        // Có thể thêm thông báo nếu không còn câu hỏi tiếp theo
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel5 = new javax.swing.JLabel();
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
        iconCheckC = new javax.swing.JLabel();
        iconCheckA = new javax.swing.JLabel();
        iconCheckB = new javax.swing.JLabel();
        iconCheckD = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listQ = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        idU = new javax.swing.JLabel();
        nameU = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        idUser = new javax.swing.JLabel();
        nameUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ReturnBtn = new javax.swing.JButton();
        nameEx = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        timeU = new javax.swing.JLabel();
        scoreU = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jLabel5.setText("jLabel5");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NextBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        buttonGroup1.add(answerA);
        answerA.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        answerA.setForeground(new java.awt.Color(255, 255, 255));
        answerA.setText("A. 1");
        answerA.setPreferredSize(new java.awt.Dimension(449, 53));

        buttonGroup1.add(answerB);
        answerB.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        answerB.setForeground(new java.awt.Color(255, 255, 255));
        answerB.setText("B. 2");
        answerB.setPreferredSize(new java.awt.Dimension(449, 53));

        answerD.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(answerD);
        answerD.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        answerD.setForeground(new java.awt.Color(255, 255, 255));
        answerD.setText("D. 4");
        answerD.setPreferredSize(new java.awt.Dimension(449, 53));
        answerD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerDActionPerformed(evt);
            }
        });

        answerC.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(answerC);
        answerC.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        answerC.setForeground(new java.awt.Color(255, 255, 255));
        answerC.setText("C. 3");
        answerC.setPreferredSize(new java.awt.Dimension(449, 53));

        iconCheckC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check-circle-solid-24.png"))); // NOI18N
        iconCheckC.setText("jLabel1");

        iconCheckA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check-circle-solid-24.png"))); // NOI18N
        iconCheckA.setText("jLabel1");

        iconCheckB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check-circle-solid-24.png"))); // NOI18N
        iconCheckB.setText("jLabel1");

        iconCheckD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/check-circle-solid-24.png"))); // NOI18N
        iconCheckD.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconCheckC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckA, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(answerA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(answerD, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckA))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconCheckD))
                .addContainerGap(58, Short.MAX_VALUE))
        );

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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        idU.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idU.setForeground(new java.awt.Color(0, 0, 0));
        idU.setText("1");

        nameU.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameU.setForeground(new java.awt.Color(0, 0, 0));
        nameU.setText("Phan Van Tươi");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mã sinh viên:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idU, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameU, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(nameU, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idU, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        idUser.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        idUser.setForeground(new java.awt.Color(0, 0, 0));
        idUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idUser.setText("1");

        nameUser.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        nameUser.setForeground(new java.awt.Color(0, 0, 0));
        nameUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameUser.setText("Phan Van Tươi");

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("MSV:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nameUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idUser, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        ReturnBtn.setBackground(new java.awt.Color(204, 255, 204));
        ReturnBtn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        ReturnBtn.setForeground(new java.awt.Color(0, 0, 0));
        ReturnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/left-arrow-circle-solid-24.png"))); // NOI18N
        ReturnBtn.setText("Quay lại");
        ReturnBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnBtnActionPerformed(evt);
            }
        });

        nameEx.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nameEx.setForeground(new java.awt.Color(0, 0, 0));
        nameEx.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        timeU.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        timeU.setForeground(new java.awt.Color(0, 0, 0));

        scoreU.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        scoreU.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Thời gian: ");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Điểm số:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeU, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(timeU, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(scoreU, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ReturnBtn)
                        .addGap(80, 80, 80)
                        .addComponent(nameEx, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(374, 374, 374)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(378, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReturnBtn)
                    .addComponent(nameEx, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(211, 211, 211)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        // TODO add your handling code here:
        if (pos >0) { // Kiểm tra xem còn câu hỏi tiếp theo không
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

    private void answerDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answerDActionPerformed

    private void listQValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listQValueChanged
        // TODO add your handling code here:
        int n = Choice();
        q.setStatus(n);
        dataList.set(pos, q);
        pos = this.listQ.getSelectedIndex();
        View();
    }//GEN-LAST:event_listQValueChanged

    private void ReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnBtnActionPerformed
        listExam le = new listExam( nameEx.getText(),idUser.getText(), nameUser.getText());
        le.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ReturnBtnActionPerformed

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
            java.util.logging.Logger.getLogger(HistoryTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton NextBtn;
    private javax.swing.JButton ReturnBtn;
    private javax.swing.JRadioButton answerA;
    private javax.swing.JRadioButton answerB;
    private javax.swing.JRadioButton answerC;
    private javax.swing.JRadioButton answerD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel iconCheckA;
    private javax.swing.JLabel iconCheckB;
    private javax.swing.JLabel iconCheckC;
    private javax.swing.JLabel iconCheckD;
    private javax.swing.JLabel idU;
    private javax.swing.JLabel idUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listQ;
    private javax.swing.JLabel nameEx;
    private javax.swing.JLabel nameU;
    private javax.swing.JLabel nameUser;
    private javax.swing.JLabel question;
    private javax.swing.JLabel scoreU;
    private javax.swing.JLabel timeU;
    // End of variables declaration//GEN-END:variables
}
