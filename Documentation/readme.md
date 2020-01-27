# Software Engineering
## Assignment-1 : JavaArgs Code Cleanup
### Parth Goyal (20171148)

### Introduction:

This is clean version of the javaargs package by unclebob (https://github.com/unclebob/javaargs).
This piece of code implements different features which when given different *command line arguments*, displays different values of the given set of *parameters*. This package displays the command line arguments passed to the file when run using the java command. 
For example,
- When the command line argument has **-l**, then **logging** is displayed **true**, else **false**.
- For the command line argument has **-p [val]**, then the value of **port** is displayed to the given value in the **bracket**, else it is **zero**.
- When the command line argument has **-d [name]**, then the name of the **directory** is displayed to the given name in the **bracket**, else it is **null**.

The other  command line arguments can be the following, 
(which can be used by changing *the schema* in the *ArgsMain file*):
* Schema:
    - char    - Boolean arg.
    - char*   - String arg.
    - char#   - Integer arg.
    - char##  - double arg.
    - char[*] - one element of a string array.


* Example schema: (f,s*,n#,a##,p[*])
Coresponding command line: "-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3

### How To Run:
First we need to  install *ant* by the following command:
* install ant by running *'sudo apt-get install ant'*

Then to compile the code go to the folder where you have cloned this repo and run the following commands:
  * run *'ant compile'*
  * run *'ant jar'*
 
To run the code, we have to run the *ArgsMain.java* file using the given below java command:
  * run *'java -cp build/jar/args.jar marshaler.cleancoder.args.ArgsMain[command line arguments]'*
  * The output would depend on the arguments.
    * For example:
        > Input: java -cp build/jar/args.jar javaargs.cleanercode.args.ArgsMain -l'
          \> Output: logging is true, port:0, direcory:
         Inut: java -cp build/jar/args.jar javaargs.cleanercode.args.ArgsMain -p "8080"'
         \> Output: logging is false, port: 8080, directory:
         And other combinations of these three values as we have chosen our schema as:
        "l, p#, d*"

Now to run the tests, we have to run the following command from the root of the folder:
* *'java -cp "lib/junit-4.13.jar:lib/hamcrest-core-1.3.jar:build/jar/args.jar"* *./test/javaargs/cleanercode/args/ArgsTest.java [name of the test function]'*

### Implementation:
-  The variables declared in the classes are arranged alphabetically, it helps the programmer to go through the code in sequence.
-  In Args.java file the "schema" and "args" variable changed to class variables. It enables programmer to use these variables in other functions of the same class and no of arguments in various functions decreased. 
-  Initilisation of marshalers and argsFound in class only, earlier it was initialised in a different function.
- Divided the function parseSchemaElement into two functions parseSchemaElement and insertInMarshaler.
- parseSchemaElement is validating the schema element while insertInMarshaler is  used to insert the schema element into the marshaler earlier both the functionalities was done by the parseSchemaElement only.
- The variables are named to convey the semantic meaning and importance of the data to be stored.
- Changed the directory structure to separately represent the documentation and the code structure.
- Proper indentation of brackets is done which enables quick and easy reading.
- Classes addressing different aspects have been seperated by packages. (Two directories named "args" and "exception" formed to separate the corresponding files).
- Made a new hashmap, schemaToMarshalerMap, which maps all the schema elements which could be present to the corresponding Marshaler functions such as **"\"->"StringArgumentMarshaler"** , etc. This increases the readability of the method parseSchemaElement and also decreases the cyclomatic complexity of the code.
- This increases the readability of the parseSchemaElement function as now it is broken down to two functions and also which earlier had **5 if-else** statements and is now reduced to only **1 if-else** statement.


### Test Cases:

- testIntegerWithDouble(): Checked if Double schema argument works when given an Integer value. It works correctly.
- testDoubleWithInteger(): Made a new unit test to check if error is returned when a double value is passed to an integer schema element. It returns an exception.