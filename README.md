# CIT300 - University Student Record and Campus Route Management System

## Group Members
| Name | Student ID | Responsibility | Individual Contribution |
|------|-----------|-----------------|--------------------------|
| _Fill in_ | _Fill in_ | Linked list + student-record management | _Fill in_ |
| _Fill in_ | _Fill in_ | Stack + queue implementation | _Fill in_ |
| _Fill in_ | _Fill in_ | BST + hashing | _Fill in_ |
| _Fill in_ | _Fill in_ | Graph + BFS/DFS | _Fill in_ |

> Replace the placeholders above before submission. The assignment brief
> requires every member's name, student ID, assigned responsibility, and
> individual contribution to be recorded correctly.

## How to Compile and Run

```bash
cd src
javac *.java
java Main
```

## Project Structure
- `Student.java` — student record model
- `StudentLinkedList.java` — Requirement 2: linked list storage
- `ActionStack.java` — Requirement 3: recent-actions / undo history
- `ServiceQueue.java` — Requirement 4: FIFO service requests
- `StudentBST.java` — Requirement 5: BST keyed on Student ID
- `StudentHashTable.java` — Requirement 6: hashing for fast ID search
- `CampusGraph.java` — Requirements 7–11: graph (adjacency list), BFS/DFS
- `Main.java` — menu-driven console interface (Requirements 12–14)

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

## Still To Do
- [ ] Test every menu option end-to-end
- [ ] Fill in group member details above
- [ ] Add more edge-case handling if your team wants extra robustness
- [ ] Set up GitHub repo with branches/commits per member
- [ ] Record demo video (< 15 minutes, all faces visible)
- [ ] Submit via LMS before 29th September, with Google Drive Editor
      access given to asanka.r@sltc.ac.lk and kaushika.w@sltc.ac.lk if using Drive
