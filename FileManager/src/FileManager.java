import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static int calculateFiles(String path) {
        int numberFiles = 0;
        File directory = new File(path);
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                numberFiles++;
            } else numberFiles += calculateFiles(file.getAbsolutePath());
        }
        return numberFiles;
    }

    public static int calculateDirs(String path) {
        int numberDirectories = 0;
        File directory = new File(path);
        File[] dList = directory.listFiles();

        for (File dir : dList) {
            if (dir.isDirectory()) {
                numberDirectories++;
                numberDirectories += calculateDirs(dir.getAbsolutePath());
            }
        }
        return numberDirectories;
    }

/*  If you use this implementation copying of files than you can't use file exist method after copying file
    private static final Integer BUFFER_SIZE = 1024;
    public static boolean copy(String from, String to) throws IOException, FileNotFoundException  {
        boolean state = false;
        int fileLength;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{
            inputStream = new FileInputStream(from);
            outputStream = new FileOutputStream(to);
            byte[] buffer = new byte[BUFFER_SIZE];

            while ((fileLength = inputStream.read(buffer))>0){
                outputStream.write(buffer,0 , fileLength);
                state = true;
            }

        } finally {
            inputStream.close();
            outputStream.close();
        }
        if (state){
            System.out.println("Copy Succes");
        }
        return state;
    }*/

    /* Данная реализация не позволит выполнять перезапись файла
     */

/*
  private static final Integer BUFFER_SIZE = 1024;
    public static boolean copy(String from, String to) throws IOException{
        boolean success = false;
        int fileLength;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        File fileFrom = new File(from);
        File fileTo = new File(to);

        if (fileFrom.exists() && !fileTo.exists() && fileFrom.isFile()){
            try{
                inputStream = new FileInputStream(from);
                outputStream = new FileOutputStream(to);
                byte[] buffer = new byte[BUFFER_SIZE];

                while ((fileLength = inputStream.read(buffer))>0){
                    outputStream.write(buffer,0 , fileLength);
                    success = true;
                }
            } finally {
                inputStream.close();
                outputStream.close();
            }
        } else return success;
        return success;
    }
*/

    private static final Integer BUFFER_SIZE = 1024;
//    private static String from = "d:\\source";
//    private static String to = "d:\\dist";

    public static boolean copy(String from, String to) throws IOException {

        File directoryFrom = new File(from);
        File directoryTo = new File(to);

        if (directoryFrom.isDirectory() && directoryTo.exists() && directoryFrom.isDirectory()) {
            File[] fList = directoryFrom.listFiles();
//                File fileFrom = new File(from);
//                File fileTo = new File(to);
            if (fList != null) {
                for (File file : fList) {
                    File fileFrom = new File(from + "\\" + file);
                    File fileTo = new File(from + "\\" + file);
                    
                    try {
                        Path pathFrom = fileFrom.toPath();
                        Path pathTo = fileTo.toPath();
                        Files.copy(pathFrom, pathTo);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            }
        } else if (directoryFrom.isDirectory() && !directoryTo.exists()){
            directoryTo.mkdir();
        }
        return true;
    }
}
