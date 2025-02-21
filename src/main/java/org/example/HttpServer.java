package org.example;

import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibí: " + inputLine);
            if (!in.ready()) {break; }
        }
        outputLine =
                "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n" +
                        "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<title>Form Example</title>" +
                        " <meta charset=\"UTF-8\">" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "</head >" +
                        "<body >" +
                        "<h1 > Form with GET</h1 >" +
                        "        <form action=\"/hello\">\n" +
                        "        <form action=\"/hello\">\n" +
                        "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                        "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                        "</form >" +
                        "        <div id=\"getrespmsg\"></div>\n" +
                        "<script >" +
                        "      function loadGetMsg() {" +
                        "                let nameVar = document.getElementById(\"name\").value;\n" +
                        "                const xhttp = new XMLHttpRequest();\n" +
                        "                xhttp.onload = function() {\n" +
                        "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                        "                    this.responseText;\n" +
                        "}" +
                        "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                        "xhttp.send();" +
                        "}" +
                        "</script >" +

                        "<h1> Form with POST</h1 >" +
                        "        <form action=\"/hellopost\">\n" +
                        "            <label for=\"postname\">Name:</label><br>\n" +
                        "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n" +
                        "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n" +
                        "</form>" +

                        "        <div id=\"postrespmsg\"></div>\n" +

                        "<script >" +
                        "   function loadPostMsg(name) {" +
                        "                          let url = \"/hellopost?name=\" + name.value;\n" +


                        "fetch(url, {method:'POST'})" +
                        ".then(x = > x.text())" +
                        "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n" +
                        "}" +
                        "</script >" +
                        "</body >" +
                        "</html >";

        out.println(outputLine);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}