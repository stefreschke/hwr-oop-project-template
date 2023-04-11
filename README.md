# HWR OOP Lecture Project - Gruppe 4: Todo

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

> :warning: This code is for educational purposes only. Do not rely on it!

## Abstract

[TODO]: # (Write a short description of your project.)
[TODO]: # (State most important features.)
[TODO]: # (State the most interesting problems you encountered during the project.)

## Feature List

### Library

| Number | Implemented | Feature                | Tests |
|--------|-------------|------------------------|-------|
| 1      | /           | Task (Todo)            | /     |
| 2      | /           | Projekt                | /     |
| 3      | /           | Tag                    | /     |
| 4      | /           | Tasks im Projekt       | /     |
| 5      | /           | Tasks mit Tag          | /     |
| 6      | /           | Dead Lines             | /     |
| 7      | /           | Daily Checklist        | /     |
| 8      | /           | In Tray / Eingang      | /     |
| 9      | /           | Kalender               | /     |
| 10     | /           | Zeitzuordnung          | /     |
| 11     | /           | "Someday, Maybe" Liste | /     |
| 12     | /           | Weekly Planning        | /     |
| 13     | /           | Weekly Review          | /     |


### User Interface

| Number | Implemented | Feature                | Tests |
|--------|-------------|------------------------|-------|
| 14     | /           | Anlegen von Tasks      | /     |
| 15     | /           | Anlegen von Projekten  | /     |
| 16     | /           | Anlegen von Tags       | /     |
| 17     | /           | Listen anzeigen        | /     |
| 18     | /           | Next Task              | /     |
| 19     | /           | Task abschließen       | /     |
| 20     | /           | Speichern von Tasks    | /     |
| 21     | /           | Laden von Tasks        | /     |


## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | /               | /                      | /                    |

## Instructions

[TODO]: # (Remove these instructions once you finished your fork's setup.)

Use a fork of this repository to do implement your project.

Remember to add this repository as a second remote repository (upstream) and pull from the correct remotes.
This is necessary, because we might apply changes to this template during the next month.

The following section describes how to add multiple remote repositories to your local repository, which is cloned from the fork.

### Multiple remote repositories

Your local repository should have a reference to both the fork (your own remote repository) and the original remote repository.
To configure your git remote repositories, use the `git remote` command set.

1. Clone your fork and go enter the repository.
```
git clone <fork-url>
cd <created-folder>
```
2. Now your fork is configured as primary remote repository (origin).
Next to origin, you should add the original repository as a second remote repository (upstream).
```
git remote add upstream <repository-url>
```
3. Verify that both remotes are configured correctly.
The following command should list both remotes: origin and upstream.
```
git remote -v
```
4. To fetch changes from all remote repositories, use:
```
git fetch --all
```
5. If there are interesting changes (in e.g. the `main` branch) to merge into your branch, use:
```
git pull upstream main
```
