# CIT300 - University Student Record and Campus Route Management System

## Project Overview
A Java console application developed for CIT300 - Data Structures and Algorithms. The system manages university student records and campus route navigation using Linked Lists, Stacks, Queues, Trees, Hashing, and Graphs.

## Group Members
| Name | Student ID | Responsibility | Individual Contribution |
|------|-----------|-----------------|--------------------------|
| J.P.A.J.Patabadige | 23DA2-0148 | Menu Integration Student data management | Developed Main.java menu driven interface, input validation, menu options integration, and final project testing/debugging. |
| M.R.K.Mapatuna | 23DA2-0339 | Tree & Hashing Search | Implemented StudentBST.java for binary search operations and StudentHashTable.java for O(1) student ID lookups. |
| M.K.B.Sampath | 23DA2-0183 | Action Stack & Campus Graph | Implemented ActionStack.java for undo/recent action history and CampusGraph.java with adjacency list, location connections, and BFS/DFS traversal. |
| A.V.T.S.Wickramarathne | 23DA2-0425 | Linked List & Service Queue | Implemented StudentLinkedList.java (add, update, delete operations) and ServiceQueue.java for managing student service requests. |


## How to Compile and Run

```bash
cd src
javac *.java
java Main
```

## Project Structure
- `Student.java` — student record model
- `StudentLinkedList.java` — linked list storage
- `ActionStack.java` — recent-actions / undo history
- `ServiceQueue.java` — FIFO service requests
- `StudentBST.java` — BST keyed on Student ID
- `StudentHashTable.java` — hashing for fast ID search
- `CampusGraph.java` — graph (adjacency list), BFS/DFS
- `Main.java` — menu driven console interface

## Requirement Coverage Checklist
- [x] Student records: ID, Name, Programme, Marks
- [x] Linked list storage
- [x] Stack for recent actions/history
- [x] Queue for service requests
- [x] BST for organizing/searching by Student ID
- [x] Hashing for fast Student ID search
- [x] Graph for campus locations/connections (adjacency list)
- [x] Add/remove locations and connections
- [x] Display campus network
- [x] BFS and DFS traversal
- [x] Add/update/delete/search/display for student records
- [x] Menu-driven interface with input validation
- [x] Handling of invalid input, duplicates, missing records

## Screenshots

- **System Menu Screenshot**
![System Menu Screenshot](<Screenshot 2026-09-29 011608.png>)

- **Add Student in system Screenshot**
![Add Student in system](<Screenshot 2026-09-29 011935.png>)

- **Traverse Campus Locations Screenshot**
![Traverse Campus Locations](<Screenshot 2026-09-29 012817.png>)

## Technologies used
- **Language**: Java
- **IDE**: VS code