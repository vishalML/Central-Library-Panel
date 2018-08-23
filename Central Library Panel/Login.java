/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package central.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Vishal Kumar
 */
public class Login {
    public void login() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","password");
        Scanner sc1=new Scanner(System.in);
        System.out.println("Enter Login Id: ");
        String id=sc1.nextLine();
        System.out.println("Enter your password: ");
        String pass=sc1.nextLine();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select role from login where loginid='"+id+"'AND password='"+pass+"'");
        if(rs!=null&& rs.next()) {
            int ch1;
            String r = rs.getString(1);
            System.out.println(r);
            if(r.equals("student")) {
                System.out.println("SELECT THE GIVEN OPTIONS");
                System.out.println("1.View all books");
                System.out.println("2.Search book");
                System.out.println("3.Exit");
                String ch2=sc1.nextLine();
                int chn=Integer.parseInt(ch2);
                switch(chn) {
                    case 1 :
                        ResultSet rs1=st.executeQuery("select * from books");
                        while(rs1.next()) {
                            String bookid=rs1.getString(1);
                            String title=rs1.getString(2);
                            String subject=rs1.getString(3);
                            String author=rs1.getString(4);
                            String status=rs1.getString(5);
                            System.out.println(bookid+" "+title+" "+subject+" "+author+" "+status);
                        }
                        break;
                     case 2 :
                        System.out.println("Enter text to search");
                        String t=sc1.nextLine();
                        ResultSet rs2=st.executeQuery("select * from books  where subject='"+t+"'");
                        while(rs2.next()) {
                            String bookid1=rs2.getString(1);
                            String title1=rs2.getString(2);
                            String subject1=rs2.getString(3);
                            String author1=rs2.getString(4);
                            String status1=rs2.getString(5);
                            System.out.println(bookid1+" "+title1+" "+subject1+" "+author1+" "+status1);
                        }
                         
                        break;
                    case 3 :
                        System.exit(0);
                        break;
                    default :
                        System.out.println("invalid option");
                        break;
                         
                         
                    }
            }
             
                    else if(r.equals("employee")) {
                        int i=104;
                        System.out.println("SELECT THE GIVEN OPTIONS");
                        System.out.println("1.for add book");
                        System.out.println("2.for delete book");
                        System.out.println("3.for list all book");
                        System.out.println("4.for search of book");
                        System.out.println("5.for issue book");
                        System.out.println("6.for Add new Employee");
                        System.out.println("7.for exit");
                        String chi=sc1.nextLine();
                        int ch3=Integer.parseInt(chi);
                        switch(ch3) {
                         case 1:
                            System.out.println("Enter the Title");
                            String titlet=sc1.nextLine();
                            System.out.println("Enter Subject");
                            String subjectt=sc1.nextLine();
                            System.out.println("Enter Author");
                            String authort=sc1.nextLine();
                            int  bookidt=i;
                            PreparedStatement pst = con.prepareStatement("insert into books VALUES('"+bookidt+"','"+titlet+"','"+subjectt+"','"+authort+"',' AVAILABLE')");
                            int j=pst.executeUpdate();
                            if(j>0) {
                                System.out.println("*********NEW BOOK ADDED TO RECORD**************");
                                System.out.println("THANK YOU");
                             
                            }
                        i++;
                            break;
                        case 2:
                            System.out.println("Enter the bookid");
                            String bookidb=sc1.nextLine();
                            PreparedStatement pst1=con.prepareStatement("Delete *from books where bookid='"+bookidb+"'");
                            int k = pst1.executeUpdate();
                            if(k>0) {
                                System.out.println("********BOOK DELETED FROM RECORD*********");
                                System.out.println("THANK YOU");
                             
                             }
                            break;
                        case 3:
                            ResultSet rs1=st.executeQuery("select * from books");
                            while(rs1.next()) {
                                String bookid=rs1.getString(1);
                                String title=rs1.getString(2);
                                String subject=rs1.getString(3);
                                String author=rs1.getString(4);
                                String status=rs1.getString(5);
                                System.out.println(bookid+" "+title+" "+subject+" "+author+" "+status);
                            }
                            break;
                        case 4:
                            System.out.println("Enter text to search");
                            String t=sc1.nextLine();
                            ResultSet rs2=st.executeQuery("select * from books  where subject='"+t+"'");
                            while(rs2.next()) {
                                String bookid1=rs2.getString(1);
                                String title1=rs2.getString(2);
                                String subject1=rs2.getString(3);
                                String author1=rs2.getString(4);
                                String status1=rs2.getString(5);
                                System.out.println(bookid1+" "+title1+" "+subject1+" "+author1+" "+status1);
                            }
                            break;
                        case 5:
                            System.out.println("Enter BookId");
                            String b=sc1.nextLine();                             
                            ResultSet rs3=st.executeQuery("select * from books  where bookid='"+b+"'");
                            String role=null;
                            System.out.println("Enter students roll no");
                            String r1=sc1.nextLine();
                            ResultSet rs4=st.executeQuery("select * from studetail  where roll no='"+r1+"'");
                            if(rs3!=null&& rs3.next() && rs4!=null && rs4.next()) {
                                while(rs3.next()) {
                                    role=rs3.getString(4);
                                    if(role.equals("AVAILABLE")) {
                                        PreparedStatement pst2=con.prepareStatement("update books set as role='ISSUED' where bookid='"+b+"'");
                                        PreparedStatement pst3=con.prepareStatement("insert into issuedetail VALUES('"+b+"','"+r1+",'"+id+"')");
                                        System.out.println("*******BOOK ISSUED TO*********"+r1);
                                        System.out.println("THANK YOU");
                                    }
                            else
                                System.out.println("Book is already issued to someone");
                             
                             
                             
                    }
                 
            }
                            else {
                                System.out.println("******BOOK ISSUE ERROR********");
                                System.out.println("THANK YOU");
                            }
                            break;       
                     }
                  
       }}
         
         else {
         
             System.out.println("Invalid details");
        
    }
    
}}
