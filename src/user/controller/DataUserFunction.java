/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.controller;

import admin.controller.DataAdminFunction;
import admin.model.Config;
import admin.model.employee;
import exam.controller.ExamModify;
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

/**
 *
 * @author admin
 */
public class DataUserFunction {

    public static List<employee> getUserList(String s) {
        List<employee> dataList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "select * from users";
            if (s != null && !s.isEmpty()) {
                sql += " where fullname like ?";
            }
            statement = conn.prepareStatement(sql);
            if (s != null && !s.isEmpty()) {
                statement.setString(1, s);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee e = new employee(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname"),
                        resultSet.getString("dob"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("gender")
                );
                dataList.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return dataList;
    }
    public static employee getUserListById(int id) {
        List<employee> dataList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "select * from users";
            if (id !=0) {
                sql += " where id = ?";
            }
            statement = conn.prepareStatement(sql);
            if (id !=0) {
                statement.setInt(1, id);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee e = new employee(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname"),
                        resultSet.getString("dob"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("gender")
                );
                dataList.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return dataList.get(0);
    }
    
    public static int checkForgetPassword(String nameTk,String phoneNumber) {
        List<employee> dataList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;
        int check =0;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "select * from users where username = ? and phone_number = ?";
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, nameTk);
            statement.setString(2, phoneNumber);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                check = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return check;
    }
    
    public static void updatePassword(String s,int id) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "update users set password = ? where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, s);
            statement.setInt(2, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    

    public static void updateUser(employee e) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "update users set fullname = ?, dob = ?, address = ?, phone_number = ?, gender = ? where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, e.getName());
            statement.setString(2, e.getDob());
            statement.setString(3, e.getAddress());
            statement.setString(4, e.getPhone());
            statement.setString(5, e.getGender());
            statement.setInt(6, e.getId());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void deleteUser(int id) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "delete from users where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void insertUser(employee e) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "insert into users(username,password,fullname,dob,address,phone_number,gender) "
                    + "values (?,?, ?, ?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, e.getNameTK());
            statement.setString(2, e.getPassword());
            statement.setString(3, e.getName());
            statement.setString(4, e.getDob());
            statement.setString(5, e.getAddress());
            statement.setString(6, e.getPhone());
            statement.setString(7, e.getGender());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static int checkUser(String s) {
        Connection conn = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            conn = DriverManager.getConnection(Config.DB_URL, Config.USERNAME, Config.PASSWORD);

            String sql = "select * from users";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(s)) {
                    check=true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataUserFunction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (check == false) {
            return 0;
        } else {
            return 1;
        }
    }
}
