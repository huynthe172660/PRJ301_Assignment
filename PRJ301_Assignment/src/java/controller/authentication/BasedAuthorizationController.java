/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Role;
import model.User;

/**
 *
 * @author AD
 */
public abstract class BasedAuthorizationController extends BasedRequiredAuthenticationController {

    private boolean isAuthorized(User user, String url) {
        UserDBContext context = new UserDBContext();
        ArrayList<Role> roles = context.getRolesByUsernameAndUrl(user.getUsername(), url);
        user.setRoles(roles);
        return user.getRoles().size() > 0;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if (isAuthorized(user, request.getServletPath())) {
            doPost(request, response, user, user.getRoles());
        } else {
            response.getWriter().println("access denied!");
        }
    }

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, User user, ArrayList<Role> roles) throws ServletException, IOException;

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, User user, ArrayList<Role> roles) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        if (isAuthorized(user, request.getServletPath())) {
            doGet(request, response, user, user.getRoles());

        } else {
            response.getWriter().println("access denied!");
        }
    }

}
