/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam.controller;

import admin.model.Config;
import exam.model.Question;
import exam.model.exam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.controller.DataUserFunction;

/**
 *
 * @author admin
 */
public class ExamModify {
    public static List<exam> getExamList(String s,int a) {
        List<exam> dataList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "select * from list_exam";
            if(s != null && !s.isEmpty()) {
                sql += " where NameExam like ?";
            }
            if(a!=0)
            {
                sql += "and numberExam = ? ";
            }
            statement = conn.prepareStatement(sql);
            if(s != null && !s.isEmpty()) {
                statement.setString(1, s);
                
            }
            if(a!=0)
            {
                statement.setInt(2, a);
            }
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                exam ex = new exam(
                        resultSet.getInt("id"),
                        resultSet.getString("NameExam"),
                        resultSet.getInt("numberExam"),
                        resultSet.getInt("soCauHoi"),
                        resultSet.getInt("thoigian")
                );
                dataList.add(ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return dataList;
    }
    public void check(){
        
    }
    
    public static List<Question> getQuestion(String s,int a) {
        List<Question> dataList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "select * from cauhoi";
            if(s != null && !s.isEmpty()) {
                sql += " where NameExam like ?";
            }
            if(a!=0)
            {
                sql += " and numberExam = ?";
            }
            statement = conn.prepareStatement(sql);
            if(s != null && !s.isEmpty()) {
                statement.setString(1, s);
            }
            if(a!=0)
            {
                statement.setInt(2,a);
            }
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Question q = new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("NameExam"),
                        resultSet.getInt("numberExam"),
                        resultSet.getString("question"),
                        resultSet.getString("answerA"),
                        resultSet.getString("answerB"),
                        resultSet.getString("answerC"),
                        resultSet.getString("answerD"),
                        resultSet.getInt("answer_correct"),
                        resultSet.getInt("status")
                );
                dataList.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return dataList;
    }
    
    public static void insertExam(exam exam) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "insert into list_exam(NameExam,numberExam, soCauHoi, thoigian) "
                    + "values (?,?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, exam.getNameExam());
            statement.setInt(2, exam.getNumberExam());
            statement.setInt(3, exam.getSoCauHoi());
            statement.setInt(4, exam.getThoigian());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void insertQuestion(Question q) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "insert into cauhoi(NameExam,numberExam, question, answerA, answerB, answerC, answerD, answer_correct, status) "
                    + "values (?,?, ?, ?,?,?, ?, ?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, q.getNameExam());
            statement.setInt(2, q.getNumberExam());
            statement.setString(3, q.getQuestion() );
            statement.setString(4, q.getAnswerA() );
            statement.setString(5, q.getAnswerB());
            statement.setString(6, q.getAnswerC());
            statement.setString(7, q.getAnswerD());
            statement.setInt(8, q.getAnswer());
            statement.setInt(9, 0);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void updateQuestion(Question q,String s,int a) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "update cauhoi set question = ?, answerA = ?, answerB = ?, answerC = ?, answerD = ?,answer_correct = ? where NameExam = ? and numberExam = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, q.getQuestion());
            statement.setString(2, q.getAnswerA());
            statement.setString(3, q.getAnswerB());
            statement.setString(4, q.getAnswerC());
            statement.setString(5, q.getAnswerD());
            statement.setInt(6, q.getAnswer());
            statement.setString(7, s);
            statement.setInt(8, a);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void deleteQưestion(String s,int a) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "delete from cauhoi where NameExam = ? and numberExam = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, s);
            statement.setInt(2, a);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void update(exam exam) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "update customer set fullname = ?, email = ?, phone_number = ?, birthday = ?, address = ? where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, exam.getNameExam());
            statement.setInt(2, exam.getSoCauHoi());
            statement.setInt(3, exam.getThoigian());
            statement.setInt(4, exam.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);
            
            String sql = "delete from list_exam where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

public static List<exam> searchExam(String name, int numberOfQuestions) {
    List<exam> result = new ArrayList<>();
    
    Connection conn = null;
    PreparedStatement statement = null;
    
    try {
        conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

        
        String sql = "SELECT * FROM list_exam WHERE NameExam LIKE ? AND numberExam = ?";
        statement = conn.prepareStatement(sql);

        
        statement.setString(1, "%" + name + "%");  
        statement.setInt(2, numberOfQuestions);

       
        ResultSet resultSet = statement.executeQuery();

        
        while (resultSet.next()) {
        exam ex = new exam(
        resultSet.getInt("id"),
        resultSet.getString("NameExam"),
        resultSet.getInt("numberExam"),
        resultSet.getInt("soCauHoi"),
        resultSet.getInt("thoigian")
    );
    result.add(ex);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Đóng tài nguyên
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    return result;
}

}
