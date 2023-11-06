/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TakeAttendance;

import controller.authentication.BasedRequiredAuthenticationController;
import dal.AttendanceDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author AD
 */
public class TakeAttendanceController extends BasedRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account user) throws ServletException, IOException {
        SessionDBContext db = new SessionDBContext();
        int id = Integer.parseInt(request.getParameter("id"));
        Session ses = db.getSessions(id);
        request.setAttribute("ses", ses);

        AttendanceDBContext attDb = new AttendanceDBContext();
        ArrayList<Attendance> atts = attDb.getAttendancesBySession(id);

        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/screen/takeattendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account user) throws ServletException, IOException {
        Session ses = new Session();
        ses.setId(Integer.parseInt(request.getParameter("sesid")));
        String[] stuids = request.getParameterValues("stuid");
        ArrayList<Attendance> atts = new ArrayList<>();
        for (String stuid : stuids) {
            Attendance a = new Attendance();
            a.setSession(ses);
            Student s = new Student();
            s.setId(Integer.parseInt(stuid));
            a.setStudent(s);
            a.setStatus(request.getParameter("status" + stuid).equals("present"));
            a.setComment(request.getParameter("comment" + stuid));
            atts.add(a);
            ses.getAtts().add(a);
        }
        SessionDBContext sesDB = new SessionDBContext();
        sesDB.addAttendances(ses);
        request.setAttribute("atts", atts);
        response.sendRedirect(request.getContextPath()+"/assignment/home");
    }

}
