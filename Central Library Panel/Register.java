/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package central.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Vishal Kumar
 */
public class Register {
    public void register() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.jdbc.Driver");
        Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","password");
                
        System.out.println("SELECT THE OPTION");
        System.out.println("1. student");
        System.out.println("2. employee");
        Scanner sc2=new Scanner(System.in);
        String sx=sc2.nextLine();
        int en=Integer.parseInt(sx);
        switch(en) {
            case 1:
                System.out.println("REGISTER STUDENT HERE:");
                System.out.println("ENTER DETAILS: ");
                System.out.printf("ROLL NO:");
                String roll=sc2.nextLine();
                System.out.printf("NAME:");
                String name=sc2.nextLine();
                System.out.printf("DEPARTMENT:");
                String dept=sc2.nextLine();
                System.out.printf("SEMESTER:");
                String sem=sc2.nextLine();
                System.out.printf("BATCH:");
                String batch=sc2.nextLine();
                
                System.out.printf("PASSWORD:");
                String pass=sc2.nextLine();
                PreparedStatement pst4=con2.prepareStatement("insert into studetail VALUES('"+roll+"','"+name+"','"+dept+"','"+sem+"','"+batch+"')");
                int y=pst4.executeUpdate();
                PreparedStatement pst5=con2.prepareStatement("insert into login VALUES('"+roll+"','"+pass+"','"+name+"','student')");
                int x=pst5.executeUpdate();
                if(y>0 && x>0)
                {
                System.out.println("*********REGISTRATION SUCCESSFUL************");
                System.out.println("your login id is"+roll);
                System.out.println("your password is"+pass);
                }
                break;
            case 2:
                 System.out.println("REGISTER EMPLOYEE HERE:");
                System.out.println("ENTER DETAILS");
                System.out.printf("EMPLOYEE ID:");
                String empid=sc2.nextLine();
                System.out.printf("NAME:");
                String name1=sc2.nextLine();
                System.out.printf("DEPARTMENT :");
                String dept1=sc2.nextLine();
                System.out.printf("JOIN DATE:");
                String joindate=sc2.nextLine();
                System.out.printf("SALARY :");
                String sal=sc2.nextLine();
                System.out.printf("PASSWORD :");
                String pass1=sc2.nextLine();
                PreparedStatement pst6=con2.prepareStatement("insert into empdetail VALUES('"+empid+"','"+name1+"','"+dept1+"','"+joindate+"','"+sal+"')");
                int l=pst6.executeUpdate();
                PreparedStatement pst7=con2.prepareStatement("insert into login VALUES('"+empid+"','"+pass1+"','"+name1+"','employee')");
                int s=pst7.executeUpdate();
                if(l>0 && s>0)
                {
                    System.out.println("*********REGISTRATION SUCCESSFUL************");
                System.out.println("your login id is"+empid);
                System.out.println("your password is"+pass1);
                }
                
                
                
                
               
                        
        }
        
    }
    
}
