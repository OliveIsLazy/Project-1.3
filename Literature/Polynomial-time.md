# Polynomial-time
### and pseudo polynomial-time

Polynomial time is an algorithmic property that's runtime is less than [O(n to the power of k)](https://en.wikipedia.org/wiki/Time_complexity#Polylogarithmic_time), where k is an integer and refers to the size of the input.
Here, n referes to some variable that tracks the size of the input which can be defined as:

> The size of the input to a problem is the number of bits required to write itself out.

Therefore, algorithms that have polynomial-time can be formally defined as:

> An algorithm runs in polynomial-time if its runtime is O(x to the power of k) for some constant k, where x denotes the number of bits of input given to the algorithm.

##### What about pseudo polynomial-time

This means that algorithms that operate on numbers can't formally have polynomial-time.
For example, the algorithm to find if n is a prime number could run in O(n to the power of 4) time, and possible faster.
But formally, we would need to take the log of n making the equation O(log of n), which then translates to O(2 to the power of 4x), which is *not* a polynomial.

This is the difference between pseudo polynomial-time: the runtime of pseudo polynomial-time is a polynomial. While informally it appears to be the same it formally is different.
