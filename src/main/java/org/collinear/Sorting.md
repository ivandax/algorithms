## Sorting

We accomplish the goal of sorting thanks to passing callbacks.
Our goal is to implement a sort() method that will sort any type of data:
Number, strings, files. How can we do it without knowing the data type?

## Rules

Some obvious but relevant rules when sorting is that the items will follow a "total order". 
That means fulfilling properties like:

Antisymmetry, Transitivity, and totality.

A intransitive relation would rock-paper-scissors, because it's not true that a > b > c
because c beats a.

## Comparable

The key of sorting in java is the implementation of compareTo() in our data type.

## Insertion sort

An inversion is a pair of keys that are out of order.
Insertion sort works very quickly when the array is partially sorted
It's partially sorted when the presence of "inversions" is less
than a given constant times the size

It takes more time than selection sort in the case that 
the array is in descending order, because it has to do more exchanges



