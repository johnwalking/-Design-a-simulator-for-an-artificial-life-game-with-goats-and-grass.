# -Design-a-simulator-for-an-artificial-life-game-with-goats-and-grass.

Objective: practicing polymorphism and class inheritance. in Java.
 
 
Description:
In this assignment, you will design a simulation of predatory and prey relationship where goats are predators and grass is the prey. Goats appear in the console window of fixed size as X’s and grass as I’s. (Examples are shown in the following section.) The “world” consists of a 35x20 array. All creatures live in cells of this array. The world does not wrap around, i.e., there are firm boundaries at the edges of the array. Initially there are 5 goats and 10 blades of grass scattered randomly. As time processes, each creature in the world takes its turn to act for living. The complete set of turns by all living creatures is called a pass. During each pass, goats move around and eat grass while the grass grows. 

During a pass, each goat is allowed to move in a randomly selected direction, provided the square is not currently occupied by another goat or outside the boundaries of the world. When the attempt is not legal, the goat does not get another chance to try again. That is, a goat has only one chance to move in each pass. When a goat moves to a new cell, it gets to eat any grass on the cell. Each goat starts with 20 food points and consumes 1 point with each pass. Every time the goat eats a blade of grass it gains 5 points.

Goats also age (become older). When a goat is born, it is zero days old. It then grows one day older with each pass until it dies (disappear) at the age of 70 days. However, between the ages of 50 and 55 inclusively, a goat can have a baby goat for each pass. To do so, the mommy goat picks a random direction and generates a baby goat one cell away in that direction, provided that the cell is legal. If the square contains a blade of grass, the mommy goat also gets to eat the grass and acquires the usual food points. However, the mommy goat does not move during this process. There are no daddy goats and we won’t worry about how the goats get pregnant. Goats die if their food points reach zero or their lives are over 70 days.

The behavior of grass is simpler. Grass never moves and doesn’t eat. Grass starts out with an age of zero and grows one day older with each pass. When a blade of grass reaches 6 days, it dies (disappears). Grass can sprout new grass between the age of 3 and 5 inclusively. Like goats, a mommy grass picks a random direction and generates a baby grass at that neighboring cell if it is empty. If it fails, it does not do another attempt. 

During each pass of the simulation, the program scan the world row by row from left to right and call upon each creature found to act. You have to make sure that each creature only acts once in each pass even if it has moved. For example, a goat may move to a new cell that will be scanned in a later time. In this case, you must have a way of jumping over that goat. In addition, baby goats and baby grass do not act during their first day of existence; they are merely by-products of existing creatures.

A sample interface is shown as below. I suggest you to play with the sample executable first to get a feel of what it does. The user can specify three optional parameters from the com-mand line. The first parameter is the number of passes to run. The second parameter is the number of passes between every display of the world. The third parameter is the seed for the random number generator. For example, java –classpath bin assign3/ALife 100 10 1 means that the simulation will run for 100 passes and display the world once every 10 passes with the seed of 1 for the random number generator. If the user specifies zero passes, or a negative number, the program defaults to a single pass.
 
Assignment directory: /usr/local/class/javap/assign/assign3
 
Sample output: a sample output is given as follows.

li@ghost 10:34am [86] assign3> java -classpath bin assign3/ALife 101 100 1

   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4

0

1

2                   I

3

4

5                                     X

6

7                                                         X

8

9                                                                   I

0                                                 I

1

2               I

3

4   X               I                                                 X

5

6                                       I               I

7     I                                                             I

8                           X                             I

9

------------------------------------------------------------------------

  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4

0 I I I I   I   I I I I I I I I I I   I I I I I I I   I I I I I I I I I

1   I   I I   I I I   I I   I     I I I I   I I I I   I     I       I I

2 I I I I I   I I I I I     I I   I I   I I I   I I   I I I I I I I I I

3 I I I I I   I I I I   I I I I I   I     I   I I I I I I I I   I   I I

4     I     I I I I   I I I I I I I I   I   I   I I       I I I I I

5   I I I I I I       I I   I     I I I I I   I I I   I I I   I I I I I

6   I I I I   I I I I I   I I   I I I I       I I I   I I     I I I I

7   I   I I I I I   I   I I I I   I I I I I   I   I I I   I I I I I I

8 I I I I   I I I I I I         I   I   I   I I     I I I I     I I I

9   I I I I   I     I I I I I I I I I I   I I I I       I I I I I I

0   I I I I   I   I I     I I   I   I   I I I   I I I I I   I I I   I I

1 I I     I I   I       I I I   I I I I I X   I I I   I   I I I I I

2 I I I I I I I I I I X     I   I I I I I I I I I I     I       I   I I

3   I I I I I I I I   X     I I   X   I I I I I I I I I I I I     I   I

4 X     I I I I   I I   X   I   I   X     I   I       I I I I I I I I

5 I       I   I I       X     I I I       I I   I I I I   I I I I   I

6 I   I I   I I I I I I I I   I I I I   I I I I I     I I     I I I I I

7 I   I   I I I I I   I I I I I I I I I I I I I I I I I I I       I   I

8 I I I I X         X I I   I   I I   I   I   I I I I I   I I I I I I

9   I   I I         I I I I   I I       I I I I I     I   I I   I I   I

------------------------------------------------------------------------

1097030xx
 
What are given:

You are given a skeleton main class: ALife.java to start with. In this file, you can find a sample main method with the command-line parameters being parsed already. In the main method, in addition to parsing the command line parameters, you will also create a World object and call its mainLoop method with appropriate parameters. An incomplete version of the World class is also given but there are many more to add into the class for the simulation to run. The source java files, the generated class files, and possibly generated documentation files are put into the src, bin, and doc directories, respectively, in the assign3 directory. There is also a Makefile in that directory to help you compile and run the program or even generate the documentation. You are also welcome to take a look at the Makefile and make necessary modifications such as the command-line parameters.
 
 
What to hand in:
You can feel free to choose the platform on which you develop your program since this assignment will only use textual input and output. For example, if you use Eclipse to develop your program, please name the project and package names all assign3 and main class ALife. Once you have finished developing the program for the assignment, please find where the project is located in your computer and then use an FTP program to send the src and bin directory to the ghost server. Then, please try to compile your java program on ghost to ensure that it compiles and runs successfully before you submit it. (You can use the Makefile to help you compile and run.)
