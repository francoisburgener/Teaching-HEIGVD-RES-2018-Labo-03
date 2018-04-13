import config.ConfigurationManager;
import config.IConfigurationManager;
import model.Prank.Prank;
import model.Prank.PrankGenerator;

import java.io.IOException;

public class MailMain {
    public static void main(String...args){
        try {
            IConfigurationManager configurationManager = new ConfigurationManager();
            PrankGenerator prankGenerator = new PrankGenerator(configurationManager);
            prankGenerator.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
