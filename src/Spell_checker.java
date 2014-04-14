    import java.io.*; // requiered Libraries
    import java.util.*;

    
    
    
public class Spell_checker{

   
  // Initializing all necessary variable and Lists. Most of these are only used in the file_reading. java file
    
        public  List<String>  words = new ArrayList<String>();
        public static String [] example;
        public static String [] suggestion_split;
        public    Scanner file;
        public  int i=0;
        public static int wrong_word_length;
        public double n;
        public double hashing = 0;
        public String words_sample;
        public static List<String> wrong_words = new ArrayList<String>();
        public static List<String> wrong_words_no_s = new ArrayList<String>();
        public static StringBuilder sugg = new StringBuilder();
        public static List<List<String>> sugg_string = new ArrayList<List<String>>();
        public static List<String> suggestions= new ArrayList<String>();
        


        public Scanner openFile(String file_name){  
         
            try{
                file = new Scanner(new File(file_name));  // Have to use try/catch statement to create the scanner element from the file
            }
            catch(Exception e){
                System.out.println("Could not find file");
            }
            return file;
        }

//Now that the file is contained in a scanner data type, we can use functions such as .next() to seperate every word
        public List<String> readFile(Scanner file){

            while(file.hasNext()){
                words.add(file.next());
              
            }
           
            return words;
        }

             

// function used to close the files once finished.
        
        public void closeFile(Scanner file){
            file.close();
        }


// Function used to split words into an array of letters from that word
        
        public String [] splitting(List<String> to_split,int i){ // it returns a array of Strings and takes a list and index number as input


            example = to_split.get(i).split("");


            return example;
        
        }


}
