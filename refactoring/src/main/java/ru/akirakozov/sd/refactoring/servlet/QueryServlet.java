package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.command.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter("command");

        var command = switch (commandName) {
          case "max" -> new Max();
          case "min" -> new Min();
          case "sum" -> new Sum();
          case "count" -> new Count();
          default -> new UnknownCommand(commandName);
        };
        command.process(response);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
