package com.nagarro.servlet;

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

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 1024*1024)
public class EditServlet extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "WEB-INF/classes/images";

    /**
     * handles file upload
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        int imageID = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("imgName");
        Part part = request.getPart("file");

        String fileName = extractFileName(part);
        // refines the fileName in case it is an absolute path
        fileName = new File(fileName).getName();

        File file = new File(savePath + File.separator + fileName);

        try {
            int id = Servlet.getID();

            part.write(savePath + File.separator + fileName);
            Queries.totalFileSize(id, file);

        }catch (Exception e) {
            response.sendRedirect("FileSizeError.jsp");
        }


        Queries.editImage(imageID, name, file);
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
}
