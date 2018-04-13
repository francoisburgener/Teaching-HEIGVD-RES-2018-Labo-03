package SMTP;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;

public class SmtpClient implements ISmtpClient {

    private String serverAdress;
    private int serverPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String serverAdress, int serverPort){
        this.serverAdress = serverAdress;
        this.serverPort = serverPort;
    }

    private void connect(){
        try {
            socket = new Socket(serverAdress,serverPort);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMail(Mail mail) throws IOException {
        connect();
        String line = reader.readLine();
        writer.println("EHLO test");
        writer.flush();
        line = reader.readLine();

        if(line.startsWith("250")){
            while(!line.startsWith("250-")){
                line = reader.readLine();
            }

        }else{
            throw new IOException("Error !");
        }

        writer.println("MAIL FROM: " + mail.getFrom());
        writer.flush();
        line = reader.readLine();


        for(String to : mail.getTo()){
            writer.println("RCPT TO: " + to);
            writer.flush();
            line = reader.readLine();
        }

        writer.println("DATA");
        writer.flush();
        line = reader.readLine();
        writer.println("From: " + mail.getFrom());
        writer.print("To: " + mail.getTo()[0]);
        for(int i = 1; i < mail.getTo().length;++i){
            writer.print(",  " + mail.getTo()[i]);
        }
        writer.print("\r\n");
        writer.println(mail.getSubject());
        writer.println(mail.getBody());
        writer.println(".");
        writer.flush();

        line = reader.readLine();

        writer.println("QUIT");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();





    }
}
