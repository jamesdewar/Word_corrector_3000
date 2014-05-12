

// PLEASE READ README TO KNOW HOW TO RUN THIS PROGRAM. ENJOY

// This is the file with the main method. The name of the file is misleading. 
// the program takes in a txt file as command line input. once file reading is compiled. Please run javac file_reading file_name.txt
// You will find a file in the files folder that you can use as sample.

// References: Stack overflow website for syntax
// book: Algorithms for java 4th edition. Helped with initial setup.

import java.util.*;// Requiered Library



public class file_reading extends Spell_checker {   // This class extends Spell Checker in order to share variables and outputs

    public static void main(String[] args) {

// Initializing the some necessary variables.
        
        String mistakes = "The words you mispelled are : ";
        String words_sample;
        Boolean found;
        int blank = 0;
        // HELLLLOOOO TESTING GIT
        System.out.println("The Following program will display words that were mispelled in your selected files, and give you suggestions\n");

//Creation of the 2 instances of the 2 classes
        // dict is for the dictionnary
        // sample is for the sample text being checked

        Spell_checker dict = new Spell_checker();
        Spell_checker sample = new Spell_checker();


//Openning each file (sample text & Dictionnary) openfile function. Found in Spall_checker.java
// readFile (function found in Spell_Chacker.java) is Storing each word in an Array


        Scanner tempFile_dict = dict.openFile("../files/output_new.txt");
        dict.readFile(tempFile_dict);

        Scanner tempFile_sample = sample.openFile(args[0]);
        sample.readFile(tempFile_sample);


// Data Cleaning -- We run a for Loop through each word of the sample text and remove any punctuation and ANy numbers

// Removing ALL signs of punctuation and ALL numerals

        for (int i = 0; i < sample.words.size(); i++) {

            String word_cleanning = sample.words.get(i);

            word_cleanning = word_cleanning.replaceAll("[^A-Za-z0-9]", ""); // Replacing punctuation with no space.

            sample.words.set(i, word_cleanning); // Using arrayList function to set the "cleaned" word back into its location on the sample.word Arraylist.

            if (sample.words.get(i).contains("1")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("2")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("3")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("4")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("5")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("6")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("7")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("8")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("9")) {
                sample.words.remove(i);
            }
            if (sample.words.get(i).contains("0")) {
                sample.words.remove(i);
            }
        }

        // The first search is a Binary search of the words without removing the S's from the end of words. 
        // So that words such as: as,is, ... can be considered as correctly spelled.

        for (int i = 0; i < sample.words.size(); i++) {

            int min = 0; // Setting the Min index for the search
            int max = dict.words.size() - 1; // Setting the Max index for the search
            found = false; // Boolean fot the word being searched
            String word_lowercase = sample.words.get(i).toLowerCase(); // Setting to Lowercase as CompareTO uses Cases to weigh the words
            while (min <= max && !found) { 
                int middle = (min + max) / 2; // Setting the mid 

                if (word_lowercase.equals(dict.words.get(middle))) {  // Checking if the word is the middle value
                    found = true;
                } else if (word_lowercase.compareTo(dict.words.get(middle)) < 0) { // If the word is less than the middle word, the max becomes the middle
                    max = middle - 1;
                } else {  // If the word is more than the middle word, the min becomes the middle
                    min = middle + 1;
                }

            }
            if (!found) { // If the word is not found in the dictionnary it is considered mispelled. 
                wrong_words.add(sample.words.get(i));
            }

        }
        
        // Removing some blank spaces which can be considered as words
        for (int i = 0; i < wrong_words.size(); i++) {
            if (wrong_words.get(i).isEmpty()) {
                wrong_words.remove(i);
            }
        }

//Removing the S from plural words
        // This technique deconstructs a string and rebuilds it 

        StringBuilder temp = new StringBuilder(); // Necessary for after
        String[] temp_final = new String[wrong_words.size()];

        for (int i = 0; i < wrong_words.size(); i++) {

            sample.splitting(wrong_words, i); // Splits the word at inex i into an array of only single letters. hell becomes: [h,e,l,l]
            int n = example.length;

            if (example[n - 1].equals("s")) {  // If the last letter is an s, remove it
                example[n - 1] = "";
            }

            for (String s : example) { // for each loop will append the letters to temp to rebuild it, without the s
                temp.append(s);

            }

            temp_final[i] = temp.toString(); //Converts the stringBuilder back into a string.
            temp.setLength(0); // Resetting temp String builder to 0 for the next word.
            wrong_words.set(i, temp_final[i]); // Setting it back to the Wrond_words arraylist which contains all mispelled words

        }

// NOW we can do the same exact binary search but only for the mispelled words not for the whole sample text. 
        // NO COMMENTS  BECAUSE ITS EXACTLY THE SAME AS ABOVE
// Binary Search of each word through the dictionnary.
        for (int i = 0; i < wrong_words.size(); i++) {

            int min = 0;
            int max = dict.words.size() - 1;
            found = false;
            String word_lowercase = wrong_words.get(i).toLowerCase();
            while (min <= max && !found) {
                int middle = (min + max) / 2;

                if (word_lowercase.equals(dict.words.get(middle))) {
                    found = true;
                } else if (word_lowercase.compareTo(dict.words.get(middle)) < 0) {
                    max = middle - 1;
                } else {
                    min = middle + 1;
                }

            }
            if (!found) {
                wrong_words_no_s.add(wrong_words.get(i));
            }

        }
        for (int i = 0; i < wrong_words_no_s.size(); i++) { // Removing any blank words
            if (wrong_words_no_s.get(i).isEmpty()) {
                wrong_words_no_s.remove(i);
            }
        }

        if (wrong_words_no_s.size() > 0) {
        }
        if (wrong_words_no_s.size() == 0) {
            System.out.println("The sample test was correctly spelled.");
        }



// Suggestion algorithm
        

        suggestion test = new suggestion(); //Creating class of the suggestion algorithm

        for (String word : dict.words) { // For each loop which add every word from the dictionnary into a BK TREE
            test.add(word);
        }

        for (String word : wrong_words_no_s) { // For each loop that searches for suggestions of every wrong word
            System.out.println("The word you mispelled is: " + word);
            test.search(word);

        }

        dict.closeFile(tempFile_dict); // Necessary to close the FILE data type 
        sample.closeFile(tempFile_sample);



    }
}
