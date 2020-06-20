package Logic_menus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    // a singelton class for saving game logs

    private static Logger logger;

    private Logger() {


    }

    private File logFile;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public static Logger getInstance() {
        if (logger == null) logger = new Logger();
        return logger;
    }

    void loggedIn(String username, long id) {
        logFile = new File("Game Data//logs//" + username + "-" + id+".txt");
        try {
            fileWriter = new FileWriter(logFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Login At:" + getCurrentDate());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {

        }

    }

    void signedUp(String username, String password, long id) {
        logFile = new File("Game Data//logs//" + username + "-" + id+".txt");
        try {
            fileWriter = new FileWriter(logFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Username : " + username);
            bufferedWriter.newLine();
            bufferedWriter.write("Password : " + password);
            bufferedWriter.newLine();
            bufferedWriter.write("Created At : " + getCurrentDate());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
bufferedWriter.flush();

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    void writeLog(String event, String description) {
        try {
            bufferedWriter.write(event + ":" + description + " At: " + getCurrentDate());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){

        }

    }
    void exitGame(){
        try{
            bufferedWriter.write("exiting game At: " + getCurrentDate());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){

        }

    }

    private String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now)).toString();
    }
}
