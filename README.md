## How to add the alg4 library

Download the algs4.jar Library:

You can download the algs4.jar file from the algs4 website.
Add algs4.jar to Your Project's Libraries:

- In IntelliJ IDEA, open your project.
- Copy the algs4.jar file to a directory within your project, e.g., lib.
- Right-click on the lib directory in your project.
- Choose "Add as Library..." from the context menu.
- IntelliJ IDEA will detect the JAR file and add it as a library to your project.

Configure Module Dependencies:

- Open your project settings:
- Navigate to File > Project Structure (or press Ctrl + Alt + Shift + S).
- Go to the Modules section.
- Select your module.
- Go to the Dependencies tab.
- Click on the + button.
- Choose JARs or directories.
- Navigate to the lib directory within your project and select algs4.jar.
- Click "OK" to add it as a dependency for your module.
- Verify Import:

Now, you should be able to import edu.princeton.cs.algs4.StdIn in your Java classes without any errors.

## Testing the Percolation class

We can use the main() of InteractivePercolationVisualizer to
start an interactive viewer that tests our implementation of the
percolation class.

## Compiling a java file

Compiling a simple java file and add it to the root:

` javac src/Sample.java -d .`

We can then run it with:

` java Sample`

When using the Intellij, with the configuration described above, we can
run the classes from the Intellij UI, but the packages will not be recognized
when compiling and running from the command line.

We can do these steps to do so, for example:

`javac -cp lib/algs4.jar src/ThreeSum.java -d .`

This will create a .class compiled file:

`ThreesSum.class`

Then we can:

`java -cp ".;lib/algs4.jar" ThreeSum numbers.txt`

## Reformat file

`ctrl + alt + L`

### How to compile a java class:

`javac -cp lib/algs4.jar src/main/java/org/binarysearch/ThreeSum.java -d .`

Will create the compiled file on org/binarysearch/ThreeSum
Then run the compiled class:

`java -cp ".;lib/algs4.jar" org/binarysearch/ThreeSum numbers.txt`

This will run the program. It runs well after adding the algs4 lib.

