# CmeTechnicalChallenge

Author: Gavin Davis

**Anagram Checker**
A Java program to accept a username and 2 text values and return an indicator whether the values are an anagram.
A word is an anagram of another word if both use the same letters in the same quantity but arranged differently.  
For example, 'friend' and 'finder'.

**Requirements**

**Validation**	
For the first release, input values the following inputs shall be rejected
* spaces 
*	numbers
*	special characters
* Entries in the file and cache are unique.

**Persistence & Performance**	
Each time values & results are processed, they shall be written to the following:
*	An external file
*	A cache – (ArrayList)
The file should be appended to, rather than overwritten. 
Improve performance by checking new requests against the cache, prior to processing.
Upon start-up, populate the cache with the previous results stored in the file.

**Maintainability & Supportability**	
Other developers must be able to change code safely; protect your code with tests.
Support teams must be able to deal with issue; this can be achieved with good logging.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Program Details**
* written using java.runtime.version=15.0.1+9-18
* Appropriate javadoc included within source code for instance and static methods

**Program Flow**
* User is prompted to enter username, first word and 2nd word
* The user is then prompted with the details that were entered and indicates if this is an anagram
* The user is then asked if they would like to make another submission (y/n)
* If the user selects "y" the program re-runs
* If the user selects "n" the program terminates

**Assumptions**
* Program flow not indicated so a design decision was made to loop the program asking for input until terminated by the user
* When checking for a previous submission against the cache only textinputs are checked, username is not included
* txt file used for external file for data persistence
* txt file used for log file
* log file should record all events that occur within the program. 
