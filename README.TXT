
This is a Spell Checker using Binary Search for finding mistakes and a BK TREE (Burkhard and Keller) using Levenshtein distance for suggestions.

Find below a brief explanation of the tree


TO RUN THIS Program:

- COMPILE FILE_READING.JAVA. LOCATION (src/file_reading.java)
- RUN IT WITH command —  java file_reading file_name.txt (how surprising…)


IMPORTANT — THE FILE NEEDS AN INPUT FILE.

There is a sample text file included in the files folded, called example.txt.

run: java file_reading ../files/example.txt if you want to use it.


Nick Johnson did a very good explanation of the BK tree and its use as a Spell checker:
link to his article : http://blog.notdot.net/2007/4/Damn-Cool-Algorithms-Part-1-BK-Trees

The BK tree uses the Levenshtein distance to determine the “closest” string to another “string”. A blurry search.

WE build the tree by adding each word of the Dictionary and each time a word is added we compute the distance between those 2 words. 

then we search the tree for the words “similar” to the given word, and follow the “branches” of the tree as distance between words get smaller, hence more similar.

Very confusing when explained like this. I highly recommend the article that explains it better. Maybe use the Diagram  I made below to help you.
