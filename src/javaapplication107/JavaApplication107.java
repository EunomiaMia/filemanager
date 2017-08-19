package javaapplication107;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class JavaApplication107 {  
    public static void main(String[] args) throws IOException {
     
       Scanner keyboard = new Scanner(System.in);
       int userInput;
       
       System.out.println("You have 7 options:");
       System.out.println("For SHOW folder press 1 and then Enter");
       System.out.println("For SHOW INFORMATIONS about file/folder press 2 and then Enter");
       System.out.println("For CREATE folder press 3 and then Enter");
       System.out.println("For RENAME file/folder press 4 and then Enter");
       System.out.println("For COPY file/folder press 5 and then Enter");
       System.out.println("For MOVE file/foldr press 6 and then Enter");
       System.out.println("For DELETE file/folder press 7 and then Enter");
       userInput = keyboard.nextInt();
       
       if(userInput==1){
       String paths = "";
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter folder path and then press Enter: ");
            paths = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
        File path = new File(paths);
 
    if (path.exists() && path.isDirectory()) {
    String[] elements = path.list();
 
    for (int i = 0; i < elements.length; i++) {
    System.out.println(elements[i]);
                }
            }
       }
       else if(userInput==2){
       String paths = "";
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter file path: ");
            paths = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
        File file = new File(paths);
        System.out.println("Absolute path = " + file.getAbsolutePath());  
        System.out.println("Name = " + file.getName());     
        System.out.println("Path = " + file.getPath());
        if (file.exists()) {
        System.out.println("Lenght = " + file.length());
 
        Instant instant = Instant.ofEpochMilli(file.lastModified());
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
             
            System.out.println("Last modified = " + dateTime.format(dateTimeFormatter));            
file.setReadOnly();
        }
    
    
    Path fp = file.toPath();
    BasicFileAttributes bfa = Files.readAttributes(fp, BasicFileAttributes.class);
    long ms = bfa.creationTime().to(TimeUnit.MILLISECONDS);
    if((ms > Long.MIN_VALUE) && (ms < Long.MAX_VALUE))
    {
    Date cd = new Date(bfa.creationTime().to(TimeUnit.MILLISECONDS));
    System.out.println("File " + file.getName() + " created " + cd.getDate() + "." + (cd.getMonth()+1) + "." + (cd.getYear()+1900) + ".");
        }
       }
       
       else if(userInput==3){
       String paths = "";
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter folder path: ");
            paths = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
        File testDirectory = new File(paths);
    try{
    if(!testDirectory.exists())
        {
        testDirectory.mkdir();
        System.out.println("Created a directoru called  "+ testDirectory.getName());
        }else{
        System.out.println("Directory called " +testDirectory.getName()+ " already exist");
    }
    
    }catch(Exception ex){
    System.out.println("Couldn't create a directory called "+testDirectory.getName() );
    } 
       }
       
       else if(userInput==4){
       String path = "";
        String destination = "";
 
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter file path with old name: ");
            path = bufferRead.readLine();
 
            System.out.println("Enter file path with new name: ");
            destination = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
        
        File oldfile = new File(path);
    File newfile = new File(destination);
    if(!oldfile.exists())
    {
        System.out.println("File doesn't exist!");
        return;
        }
    if(newfile.exists())
    {
        System.out.println("File with desired name already exists!");
        return;
        }
    if(oldfile.renameTo(newfile)){
        System.out.println("Rename successfull");
        }else{
        System.out.println("Rename faild");
            }
       }
       
       else if(userInput==5){
       String path = "";
        String destination = "";
 
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter file path: ");
            path = bufferRead.readLine();
 
            System.out.println("Enter new file path: ");
            destination = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
        
        File afile = new File(path);
    File bfile = new File(destination);
    try(FileInputStream inStream = new FileInputStream(afile);
            FileOutputStream outStream = new FileOutputStream(bfile);){
        byte [] buffer = new byte[1024];
        int length;
        while((length = inStream.read (buffer))>0){
            outStream.write(buffer, 0, length);
            }
        System.out.println("File is copied successsfully!");
    }catch(IOException exc){
        System.out.println(exc);
    }
       }
       
       else if(userInput==6){
       String path = "";
        String destination = "";
 
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter file path: ");
            path = bufferRead.readLine();
 
            System.out.println("Enter destination folder: ");
            destination = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
 
        File afile = new File(path);
        File bfile = new File(destination + "\\" + afile.getName());
 
        try (FileInputStream inStream = new FileInputStream(afile);
                FileOutputStream outStream = new FileOutputStream(bfile);) {
 
            byte[] buffer = new byte[1024];
 
            int length;
 
            while ((length = inStream.read(buffer)) > 0) {
 
                outStream.write(buffer, 0, length);
 
            }
 
            System.out.println("File is moved successfuly!");
            
 
        } catch (IOException exc) {
            System.out.println(exc);
            }
        finally {
           afile.delete();
              }
        
       }
       
       else if(userInput==7){
       String paths = "";
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));) {
 
            System.out.println("Enter file path: ");
            paths = bufferRead.readLine();
 
        } catch (IOException e) {
            System.out.println(e);
        }
        
        File filef = new File(paths);
        if(filef.exists()){
        filef.delete();
        System.out.println("File succesfully deleted!!");
        }
        else{
        System.out.println("Cannot delete "+filef.getName()+ " because "+ filef.getName()+" doesn't exists");
        }
       }
        else{
       System.out.println("You have enterd the wrong command");
       }
    }
    
}
