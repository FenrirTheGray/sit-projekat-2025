# Style Guide

## Intro
This document serves as a guide for agreed upon, naming conventions, text formatting and visual styles. 

Preferred writting language is English unles stated otherwise.

---

## Contents:

[1. Git](#1.%20Git)
[2. Astah](#2.%20Astah)
[3. Java](#3.%20Java)
[4. Figma](#4.%20Figma)

---

## 1. Git

In this section we define the naming conventions for issue titles, branch names, commit message styles and definitions for tags

### 1.1 Issues
#### 1.1.1 Issue Titles

Issue titles should be in the following format:

``` <Domain> - [<Sub domain> -] <Task name>```


| Domain | (Optional) Sub domain | Task name |
| :- | :- | :- |
|A broad descriptor of the part of the project this issue is connected to| Further specifies which part of the project it refers to if needed | A one sentence description of the issue |

##### Examples
```Usecase Diagram - Porucivanje - Add a use case for "Brisanje artikla iz korpe"```
```CMS Application front - end prototype - Settings Page```

#### 1.1.2 Issue Tags
Tags should be added for easier task classification

#### 1.1.3 Issue Text
Basically a free for all, do as you wish as long as it makes sense

#### 1.1.4 Issue Tags
| Tag Name | Tag Description |
| :- | :- |
|```documentation```|Issues for documentation, they do not add any functionality|
|```bug```|Bug fix issues|
|```design```|Issues for visual design (mostly figma)|
|```backend```|Backend issues (API, Database)|
|```frontend```|Frontend issues (Ordering App, CMS App)|
### 1.2 Branches

#### 1.2.1 Branch Names
Branch names should be in the following format:
```<Branch type>/[<Domain>/]<Task name>```
Words are separated with ```-``` (Kebab case)

| Branch type | (Optional) Domain | Task Name |
| :- | :- | :- |
|Depending on the type of task it can be ```fixes```, ```features``` or ```improvements```|A descriptor of the part of the project this branch is connected to| One sentence description of the task|

##### Examples
```fixes/usecase-diagrams/porucivanje-change-to-bulletpoints```
```improvements/astah-project-restructuring-1```

### 1.3 Commits
#### 1.3.1 Commit Messages
No agreement here

---

## 2. Astah
In this section we define proper naming conventions for usecase and class diagram elements.

For Astah preferred language is Serbian

### 2.1 Usecase diagram
#### 2.1.1 Usecase subsystem name

#### 2.1.2 Usecase name

#### 2.1.3 Usecase description
|Description item| Description value | Recommended style |
| :- | :- | :- |
|UseCase| Name of the usecase | Auto Generated|
|Summary| Description of the usecase | Plain sentence(s)|
|Actor| Usecase actors| Auto Generated |
|Precondition| Conditions that have to be fulfilled before the usecase can be performed. | Simple conditions organized in an unordered list using ```-```
|Postcondition| The resulting change(s) of executing the usecase| Simple results organized in an unordered list using ```-``` |
|Base Sequence|Step by step description of usecase's execution. The steps happen after the precondition has been met. When describing the steps it is assumed that the user has already started the usecase.| Simple actions organized in a numbered list. For nesting use 4 spaces.|
|Branch Sequence|A list of step by step descriptions of all possible branch sequences| Conditions referencing tasks from Base Sequence where branch occurs, followed by nested list of step by step actions when condition is met|
|Exception Sequence| A list of step by step descriptions of all possible error handling sequences | Conditions referencing tasks from Base or Branch Sequence where exception occurs, followed by nested list of step by step actions when condition is met|
---
When using multilevel lists always reset the numbering after entering an inner layer instead of adding additional numbers in the list indicator.

✅ Do this ✅
```
1.
2.
   1.
   2.
   3.
3.
```

❌ Not this ❌

```
1.
2.
    2.1.
    2.2
    2.3
3.
```
---
When referencing base sequence steps for branch sequence or exception sequence use letters after the step number to indicate where the branch/exception happened:

For base sequnce:
```
1. Task A
2. Task B
   1. Task C
   2. Task D
   3. Task E
3. Task F
```
When referencing Task B use: 
```
 1a. Condition A
    1. Task G 
    2. Task H
```
When referenced again: 
```
 1b. Condition B
    1. Task G 
    2. Task H
```

When referencing Task D use:
```
2.2a. Condition B
    1. Task I
    2. Task J
```

### 2.2 Class diagram

---

## 3. Java

---

## 4. Figma

