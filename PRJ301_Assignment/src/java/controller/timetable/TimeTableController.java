/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import controller.authentication.BasedRequiredAuthenticationController;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.TimeSlot;
import model.Account;
import util.DateUtils;
import static util.DateUtils.getSQLDatesBetween;

/**
 *
 * @author AD
 */
public class TimeTableController extends BasedRequiredAuthenticationController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account user) throws ServletException, IOException {
        int id = user.getAccId();
        String s_from = request.getParameter("from");
        String s_to = request.getParameter("to");
        ArrayList<Date> dates = new ArrayList<>();
        if (s_from == null)// this week
        {
            dates = (ArrayList<Date>) DateUtils.getDatesOfCurrentWeek();
        } else {
            try {
                dates = (ArrayList<Date>) getSQLDatesBetween(s_from, s_to);
            } catch (ParseException ex) {
                Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Date from = dates.get(0);
        Date to = dates.get(dates.size() - 1);
        
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.list();

        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Session> sessions = sesDB.getSessions(id, from, to);
        

        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("sessions", sessions);
        request.setAttribute("accname", user.getAccName());

        request.getRequestDispatcher("../view/screen/timetable.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account user) throws ServletException, IOException {
        int id = user.getAccId();
        String s_from = request.getParameter("from");
        String s_to = request.getParameter("to");
        ArrayList<Date> dates = new ArrayList<>();
        if (s_from == null)// this week
        {
            dates = (ArrayList<Date>) DateUtils.getDatesOfCurrentWeek();
        } else {
            try {
                dates = (ArrayList<Date>) getSQLDatesBetween(s_from, s_to);
            } catch (ParseException ex) {
                Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Date from = dates.get(0);
        Date to = dates.get(dates.size() - 1);

        
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.list();

        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Session> sessions = sesDB.getSessions(id, from, to);
        

        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("sessions", sessions);
        request.setAttribute("accname", user.getAccName());

        request.getRequestDispatcher("../view/screen/timetable.jsp").forward(request, response);
    }

}
