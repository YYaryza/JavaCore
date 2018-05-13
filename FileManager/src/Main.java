import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        NumberFiles = FileManager.calculateFiles("D://new//PYTHON");
        System.out.println("Number Files = " + FileManager.calculateFiles("D://new//PYTHON"));
        System.out.println("Number Directories = " + FileManager.calculateDirs("D://new//PYTHON"));
        System.out.println("Copy Success: " + FileManager.copy("D:\\Java_Tasks.txt","D:\\new\\Java_Tasks.txt"));
//        System.out.println("Copy Success: " + FileManager.copy("D:\\sorce\\","D:\\dest"));
    }

}

