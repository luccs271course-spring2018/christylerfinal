# Answers

## - Try using a `TreeMap` and a `HashMap` instead of `MyHashMap`.
1. Yes, the frequencies were different in the MyHashMap compared to TreeMap and HashMap.
2.  Best - TreeMap
    Middle - HashMap
    Worst - MyHashMap

## - How are `%` and `Math.floorMod` different? Which works more reliably for computing a hash table index?
- % returns the remainder of two separate numbers while Math.floorMod rounds the remainder down to its lowest integer using modular division.
In the case of negative numbers, math.floormod will have a different result and account for negative numbers. Therefore making it more reliable.

## - What is the time complexity of `MyHashMap.size()`, and how could you make it much more efficient?
- O(n). We could make a separate variable that counts the amount of entries added, and then returns that variable.

## - How does this implementation compare to one where you would directly use your linked `Node` class from the earlier assignment?
## Answer briefly in terms of ease of implementation, correctness, reliability, and performance.
 - The linked node class seemed to be simpler to implement than the MyHashMap but with a more complex implementation sometimes comes
 better performance and more flexibility. Both programs seemed pretty reliable however.
