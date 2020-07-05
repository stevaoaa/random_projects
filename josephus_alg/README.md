# An algorithm to find the solution to Josefus's Problem using a double linked list java implementation.

##Problem description:

Josefus's Problem describes the following situation: A group of soldiers is surrounded and there is no hope of victory, but there is only one horse available to escape and look for reinforcements. 

To determine which soldier should escape to find help, they form a circle and draw a hat number. Starting with a soldier drawn at random, a count is performed up to the number drawn. When the count is finished, the soldier at which the count stopped is removed from the circle, a new number is drawn and the count starts over at the soldier following the one that was eliminated. Therefore, with each round, the circle decreases by one, until only one soldier remains and is chosen for the task.

Using a size 10 vector, draw numbers between -9 and 9 for each hat consultation and simulate the process, printing the number of the soldier eliminated each round and the number of the chosen soldier at the end. Negative values ​​move the count to the left, while positive values ​​move to the right. Consider that 0 is an invalid value and perform a new draw in this case. Represent soldiers in the data structure as a type capable of storing a unique identifier for each individual.
