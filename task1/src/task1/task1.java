package task1;

import java.io.*;  
import java.util.Random;  
  
  
public class task1{     
    public static void main(String[] args){  
        String filepath = System.getProperty("user.dir");     
        filepath +="\\aa.txt";  
        System.out.println(filepath);   
        try{  
            File file = new File(filepath);           
            if(!file.exists())  //if not have a file named "aa.txt"
            {    
                file.createNewFile();  
                System.out.println("aa.txt created");               
            }  
            //created file and enter
            FileWriter fw = new FileWriter(file);        
            BufferedWriter bw = new BufferedWriter(fw);  
            
            //generate a random data and write to file
            
	     /*   public static String getRandomString(int length) { //length is means String's length
	            String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	            Random random = new Random();   
	            StringBuffer sb = new StringBuffer();   
	            for (int i = 0; i < length; i++) {   
	                int number = random.nextInt(base.length());   
	                sb.append(base.charAt(number));   
	            }   
	            return sb.toString();   
	         }  
	     */ 
            
            for (int j=0 ; j<10000*250 ; j++){     
            StringBuffer sb = new StringBuffer();   
            String[] base = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
            
            Random r1 = new Random();//the length of word
            Random r2 = new Random();//letter
            
            for(int a=0; a<10; a++){
            	for (int i = 0; i < r1.nextInt(9); i++) {   
                 int number = r2.nextInt(25);
                 sb.append(base[number]);   
                 bw.write(String.valueOf(sb.toString()));      //write a random number    
            	}   
            sb.append(" ");
            }
            bw.newLine(); 
         }  
         bw.close();  
         fw.close();   
        }   
        catch (Exception e){  
            e.printStackTrace();  
        }         
    }     
}
