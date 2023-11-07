/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.attendancereport;

import controller.authentication.BasedRequiredAuthenticationController;
import dal.GroupDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import model.Account;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author AD
 */
public class AttendanceReportController extends BasedRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account user) throws ServletException, IOException {
        GroupDBContext gdb = new GroupDBContext();
        ArrayList<Student> stu = gdb.getStudentByGroup(1);
        
        SessionDBContext sdb = new SessionDBContext();
        ArrayList<Session> ses = sdb.getSessionByGroup(1);
        int number = ses.size();
        
        StudentDBContext stdb = new StudentDBContext();
        ArrayList<Attendance> att = stdb.getAttendancesByStuid(1);
        
        HashMap<String, ArrayList<Attendance> > attrp = new HashMap<>();
        for(int i=0;i<stu.size();i++){
            attrp.put(stu.get(i).getName(), stdb.getAttendancesByStuid(stu.get(i).getId()));
        }
        
        
        request.setAttribute("attrp", attrp);
        request.setAttribute("stu", stu);
        request.setAttribute("ses", ses);
        request.setAttribute("att", att);
        request.setAttribute("number", number);
        request.getRequestDispatcher("../view/screen/attendancereport.jsp").forward(request, response);
        
        
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    

}
