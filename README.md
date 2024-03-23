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

## Conclusions of Quick Union and Binary Search

- Quick union is an algorithm that allows us to efficiently track grouped members of an array.
- It involves a data structure which is an array of integers where each item is a member of the
  analysed group.
- Then we create a tree for each group, so that related items share the same root.
- The most efficient version we studied is the weighted quick union with path compression, which
  makes sure that we always add the smaller tree into the larger one when making an union, and
  also makes each group point directly to the root.
- This can be applied in real life to many things: If we had a large set of users of a platform
  and wanted to know who are connected to who, we could transform that array into the quick
  union data structure and check it with the algorithm.

- Binary search is a simple algorithm that allows us to quickly find an item in a sorted array.
- By always checking for a match in the middle of the array and then discarding half of the
  array, the array access are less than mapping through the whole array, making it more efficient.



