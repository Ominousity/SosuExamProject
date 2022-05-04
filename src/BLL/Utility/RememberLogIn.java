package BLL.Utility;

import java.io.*;
import java.nio.file.Files;

public class RememberLogIn {
    private File file;

    public boolean ChekFile(){
        if(file.exists()){

            return true;
        }
        return false;
    }

    public void CreateFile(String string) throws IOException {
        if (!ChekFile()){
            File file = new File("Utilities\\tools.txt");
            FileWriter fw = new FileWriter(file,false);

            for (int i = 0; i < string.length() ; i++) {
                fw.write(string.charAt(i));
            }

            fw.close();
        }

    }

    public void WriteToFile(String string) throws IOException {
        FileWriter fw = new FileWriter(file,false);

        for (int i = 0; i < string.length() ; i++) {
            fw.write(string.charAt(i));
        }

        fw.close();
    }

    public String ReadFile() throws IOException {
        FileReader fileReader = new FileReader("Utilities\\tools.txt");
        // Declaring loop variable
        int i;
        // Holds true till there is nothing to read
        while ((i = fileReader.read()) != -1)

            // Print all the content of a file
            System.out.print((char)i);
        return ReadFile();
    }
}
