package SMTP;

import model.mail.Mail;
import model.mail.Person;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Manage the communication with a SMTP server in order to send mails
 * @author Burgener François, Curchod Bryan
 */
public class SmtpClient implements ISmtpClient {

    private String serverAdress;
    private int serverPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    /**
     * Constructor of a client
     * @param serverAdress SMTP server to use
     * @param serverPort open port in the server
     */
    public SmtpClient(String serverAdress, int serverPort){
        this.serverAdress = serverAdress;
        this.serverPort = serverPort;
    }

    /**
     * Create the socket and the reader/writer.
     */
    private void connect(){
        try {
            socket = new Socket(serverAdress,serverPort);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * End the communication with the SMTP server, and close the I/O streams.
      * @throws IOException
     */
    private void disconnect() throws IOException {
        writer.println("QUIT");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
    }

    /**
     * manage the "discussion" with the server to forge a mail.
     * @param mail mail to send
     * @throws IOException
     */
    public void sendMail(Mail mail) throws IOException {
        connect();

        LinkedList<String> receivers = mail.getTo();
        String line = reader.readLine();
        writer.println("EHLO test");
        writer.flush();
        line = reader.readLine();

        if(line.startsWith("250")){
            while(!line.startsWith("250")){
                line = reader.readLine();
            }

        }else{
            throw new IOException("Error !");
        }

        writer.println("MAIL FROM: " + mail.getFrom());
        writer.flush();
        line = reader.readLine();


        for(String to : receivers){
            writer.println("RCPT TO: " + to);
            writer.flush();
            line = reader.readLine();
        }

        writer.println("DATA");
        writer.flush();
        line = reader.readLine();
        writer.println("From: " + mail.getFrom());
        writer.print("To: " + receivers.removeFirst());
        for(String to : receivers){
            writer.print(",  " + to);
        }
        writer.print("\r\n");
        writer.println(mail.getSubject());
        writer.println(mail.getBody());
        writer.println(".");
        writer.flush();

        line = reader.readLine();

        disconnect();
    }
}
