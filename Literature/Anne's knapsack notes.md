# Knapsack Problems

### What is a knapsack problem
The knapsack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible. In other words, to maximise the density of the container's value.

In our case however, we’re not working with weight but rather with volume, given that we have to fit certain parcels into the container the volume is our restriction instead of the weight.

Knapsack problems can be defined in 3 ways: 
1. 0-1 knapsack problems.
2. Bounded knapsack problems.
3. Unbounded knapsack problems, the type we're dealing with.

It is defined such that we: 

> Maximize [The sum of all the values in the container through this formula](https://en.wikipedia.org/wiki/Knapsack_problem#Definition), with *V i* being the value of a certain parcel, and *X i* the number of said v-parcel.

Subject to with the weight of each object, in our case we’d replace this with volume. However it’s a bit more complicated than that, because not only does the sum of the total volume of our parcels need to be smaller than the volume of our container (obviously), we’d have to make sure it fits (because we can’t saw any boxes in half).

There is no polynomial-time algorithm for this problem.

### What is polynomial-time and why do I care

An algorithm is said to be solvable in polynomial-time if the number of steps required to complete the algorithm for a given input is for some nonnegative integer, where X is the complexity of the input. Polynomial-time algorithms are said to be "fast." is defined as all algorithms which solve a problem in  steps or less. So a problem is solvable in polynomial time if it’s solvable in less than  steps (I am not entirely sure what the k stands for, because that could obviously drastically change the number of steps, so I assume it can’t be random).

There is a pseudo-polynomial time algorithm using dynamic programming.
