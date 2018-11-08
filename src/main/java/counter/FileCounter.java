package counter;

import analysis.UsageLog;
import choice.WordChoice;
import fileService.FileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileCounter {

    private File textFile;

    private UsageLog usageLog;

    public FileCounter(String path, FileService fileService) {
        this.usageLog = new UsageLog();
        if(fileService.checkFileOnRemoteRepository(path)){
            this.textFile = fileService.retrieveFileFromRemote(path);
        } else {
            this.textFile = new File(path);
        }
    }

    public int count(WordChoice choice) {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));

            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split("\\s");
                for (int i = 0; i < words.length; i++) {
                    String fileWord = choice.caseSensitive ? words[i] : words[i].toLowerCase();

                    if (choice.match(fileWord))
                        count++;
                }
                line = reader.readLine();
            }
            usageLog.addStringUsage(choice.chosenWord);
            return count;
        } catch (IOException e) {
            System.out.println("Error while counting");
        }
        return 0;
    }

    public UsageLog getUsageLog(){
        return usageLog;
    }

    public void setUsageLog(UsageLog usageLog){
        this.usageLog = usageLog;
    }
}
