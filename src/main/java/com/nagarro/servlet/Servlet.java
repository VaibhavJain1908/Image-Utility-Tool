package com.nagarro.servlet;

import com.nagarro.entity.users.Users;
import com.nagarro.queries.Queries;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/home")
@MultipartConfig(maxFileSize = 1024*1024)
public class Servlet extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "WEB-INF/classes/images";
    private static int id;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userName = req.getParameter("UserName");
        String password = req.getParameter("Password");

        try {
            Users user = Queries.getUser(userName, password);
            id = user.getId();

            resp.sendRedirect("imageUtility.jsp");

        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
        }

    }

    /**
     * handles file upload
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        String name = request.getParameter("imgName");
        Part part = request.getPart("file");

        String fileName = extractFileName(part);
        // refines the fileName in case it is an absolute path
        fileName = new File(fileName).getName();

        File file = new File(savePath + File.separator + fileName);

        try {
            part.write(savePath + File.separator + fileName);
            Queries.totalFileSize(id, file);
        }catch (Exception e) {
            response.sendRedirect("FileSizeError.jsp");
        }

        Queries.addImage(name, id, file);
        response.sendRedirect("imageUtility.jsp");
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    public static int getID() {
        return id;
    }


}
