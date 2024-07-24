# Document Indexer using Binary Search Tree

## Overview

This repository contains my implementation of a document indexer using a Binary Search Tree (BST). The primary goal of this project was to implement a binary search tree from scratch, use it in an application, and work with the `Comparable` and `Comparator` interfaces to provide various sorting and indexing functionalities.

## Description

The project involves parsing words from a text file and creating an index of words that stores their frequencies and line numbers. The BST is used to store the words in a sorted manner, allowing efficient search and retrieval operations. Additional features include sorting words by frequency and alphabetical order, as well as handling custom comparators.

## Features

### Word Class
- **Fields**:
  - `String word`: The word being indexed.
  - `Set<Integer> index`: A set of line numbers where the word appears.
  - `int frequency`: The frequency of the word in the document.
- **Methods**:
  - `void setWord(String newWord)`
  - `String getWord()`
  - `void setFrequency(int freq)`
  - `int getFrequency()`
  - `void addToIndex(Integer line)`
  - `Set<Integer> getIndex()`
  - `String toString()`: Returns a string representation of the word and its frequency.
  - `int compareTo(Word other)`: Compares words alphabetically.

### BST Class
- **Fields**:
  - `Comparator<T> comparator`: A comparator for custom ordering.
  - `Node<T> root`: The root node of the BST.
- **Methods**:
  - `void insert(T toInsert)`: Inserts a new element into the BST.
  - `T search(T toSearch)`: Searches for an element in the BST.
  - `Iterator<T> iterator()`: Returns an in-order iterator for the BST.

### Index Class
- **Methods**:
  - `BST<Word> buildIndex(String fileName)`: Builds an index from a file using natural alphabetical order.
  - `BST<Word> buildIndex(String fileName, Comparator<Word> comparator)`: Builds an index from a file using a specified comparator.
  - `BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator)`: Builds an index from a list of words using a specified comparator.
  - `ArrayList<Word> sortByAlpha(BST<Word> tree)`: Sorts words alphabetically.
  - `ArrayList<Word> sortByFrequency(BST<Word> tree)`: Sorts words by frequency.
  - `ArrayList<Word> getHighestFrequency(BST<Word> tree)`: Gets words with the highest frequency.

### Comparator Classes
- **IgnoreCase**: Sorts words by case-insensitive alphabetical order.
- **Frequency**: Sorts words by frequency (highest first).
- **AlphaFreq**: Sorts words alphabetically and by frequency in case of a tie.

## Implementation Details

To deepen my understanding of data structures and algorithms, I implemented this project with specific constraints and rules:

- **Binary Search Tree**: Utilizes a BST to store words efficiently and allow for various sorting and searching operations.
- **Comparable and Comparator**: Implements the `Comparable` interface for natural ordering and `Comparator` interfaces for custom sorting.
- **In-Order Iterator**: Provides an in-order iterator for the BST that traverses elements in sorted order.
- **Java Collections Framework**: Uses the Java Collections Framework only for the iterator implementation in the BST and for other specific needs in the Index class.

## Usage

To use the document indexer, create instances of the provided classes and call their methods, or run the provided driver program in the repository.

## Learnings

- **Binary Search Tree**: I learned to implement a binary search tree from scratch and use it for efficient data storage and retrieval.
- **Comparable and Comparator**: I gained experience in implementing and using the `Comparable` and `Comparator` interfaces for custom sorting.
- **Text Processing**: I developed skills in processing text files to create word frequency indices.
- **Problem-Solving**: I addressed edge cases and ensured the robustness of the implementation through extensive testing.

## Conclusion

This project was a valuable experience in understanding the intricacies of implementing and using a binary search tree for document indexing. The `Document Indexer` provides a comprehensive solution for parsing, indexing, and sorting words from a text file, showcasing the power of BSTs and custom comparators.
