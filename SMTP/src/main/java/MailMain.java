import config.ConfigurationManager;
import config.IConfigurationManager;
import model.Prank.Prank;
import model.Prank.PrankGenerator;

import java.io.File;
import java.io.IOException;

/**
 * main class. Test our implementation, and allows to launch another prank campaign
 * @author Burgener François, Curchod Bryan
 */
public class MailMain {
    public static void main(String...args){
        // normal use
        if(args.length == 1){
            // check the path to the file, and if the file exists
            String filename = args[0];
            File f = new File(filename);
            if(f.exists()){
                // launch the prank campaign
                try {
                    new PrankGenerator(new ConfigurationManager(filename)).send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File not found, or does not exist.");
            }

        } else if(args.length == 0) { // no arguments given, we simply use the demo method
            demo();
        } else { // too many args
            System.out.println("Too many arguments. Please give the path to the configuration file only !");
        }
    }

    /**
     * Default prank campaign
     */
    private static void demo(){
        try {
            PrankGenerator prankGenerator = new PrankGenerator(new ConfigurationManager("./config/config.properties"));
            prankGenerator.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
