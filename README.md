# HWR OOP Project - To-Do-List

This project is part of OOP with Paul, Benjamin, Jonas and Christian 2023

Yellow - Objects, classes, other java options
Red - Applications of project
Green - net to know - function of "gtd"

Starts with In-Tray -- "lose Gedanken welche keine wirklichen Tasks beinhaltet"


This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

> :warning: This code is for educational purpose only. Do not rely on it!

## Abstract
To Do List
Features follow the "getting things done" method

[TODO]: # (Write a short description of your project.)
[TODO]: # (State most important features.)
[TODO]: # (State the most interesting problems you encountered during the project.)


## Flowchart

```mermaid
classDiagram
Tags <|-- |> Project
Project <|-- |> Task
Tags <|-- Task
Task <|-- TaskBuilder
Project <|-- ProjectBuilder
TaskBuilder : - String title
TaskBuilder : - String description
TaskBuilder : - LocalDateTime dateTimeDeadline
TaskBuilder : - TaskPriority priority
TaskBuilder : - LocalDateTime dateTimePlannedStart
TaskBuilder : - LocalDateTime dateTimePlannedEnd
TaskBuilder : + setTitle(String title)
TaskBuilder : + setDescription(String description)
TaskBuilder : + setDateTimeDeadline(LocalDateTime deadline)
TaskBuilder : + setPriority(TaskPriority priority)
TaskBuilder : + setDateTimePlannedStart(LocalDateTime startDateTime)
TaskBuilder : + setDateTimePlannedEnd(LocalDateTime endDateTime)
TaskBuilder : + build()
Task : -String title
Task : -String description
Task : -TaskStatus status
Task : -Project project
Task : -Set<Tag> tags
Task : -LocalDateTime dateTimeCreated
Task : -LocalDateTime dateTimeDone
Task : -LocalDateTime dateTimePlannedStart
Task : -LocalDateTime dateTimePlannedEnd
Task : -LocalDateTime deadline
Task : +finishTask()
Task : +addTag(Tag tag)
Task : +removeTag(Tag tag)
Task : +toFurtherStatus()
Task : +toPreviousStatus()
Task : +removeFromProject()
Task : +getTitle()
Task : +getDescription()
Task : +getStatus()
Task : +getPriority()
Task : +getProject()
Task : +getTags()
Task : +getDateCreated()
Task : +getDateDone()
Task : +getDatePlannedStart()
Task : +getDatePlannedEnd()
Task : +getDeadline()
Task : +setTitle(String title)
Task : +setDescription(String description)
Task : +setPriority(TaskPriority priority)
Task : +setPlannedDate(LocalDateTime startDate, LocalDateTime endDate)
Task : +setDeadline(LocalDateTime deadline)
Task : +changeProject(Project project)
Tags : -String Name
Tags : +getTitle()
Project : -String title
Project : -String description
Project : -LocalDateTime dateTimeCreated
Project : -LocalDateTime dateTimeDeadline
Project : -Set<Task> tasks
Project : +addTask(Task task)
Project : +removeTask(Task task)
Project : +changeTitle(String title)
Project : +changeDescription(String description)
Project : +changeDeadline(LocalDateTime deadline)
Project : +getTitle()
Project : +getDescription()
Project : +getDateTimeCreated()
Project : +getDeadline()
Project : +getTasks()
ProjectBuilder : -String title
ProjectBuilder : -String description
ProjectBuilder : -LocalDateTime deadline
ProjectBuilder : +setTitle(String title)
ProjectBuilder : +setDescription(String description)
ProjectBuilder : +setDeadline(LocalDateTime deadline)
ProjectBuilder : +build()
```
## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature | Tests |
|--------|---------|-------|
| 1      | Task       | Implemented     |
| 2      | Project | Need Improvement     |
| 3      | Tags    | /     |
| 4      | Database| /     |
|5|Database Handler|/|
|6|IO Handler|/|


## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | Tag,Project,Task               | Need Dependencies between classes                      |                     |
