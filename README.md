# HWR OOP Lecture Project - Gruppe 4: Todo

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

> :warning: This code is for educational purposes only. Do not rely on it!

## Abstract

[TODO]: # (Write a short description of your project.)
[TODO]: # (State most important features.)
[TODO]: # (State the most interesting problems you encountered during the project.)

## Feature List

### Library

| Number | Implemented        | Feature                | Tests              |
|--------|:------------------:|------------------------|:------------------:|
| 1      | :heavy_check_mark: | Task (Todo)            | :heavy_check_mark: |
| 2      | :heavy_check_mark: | Projekt                | :heavy_check_mark: |
| 3      | :heavy_check_mark: | Tag                    | :heavy_check_mark: |
| 4      | :heavy_check_mark: | Tasks im Projekt       | :heavy_checkmark:  |
| 5      | :heavy_check_mark: | Tasks mit Tag          | :heavy_check_mark: |
| 6      | :heavy_check_mark: | Dead Lines             | :heavy_check_mark: |
| 7      | :x:                | Daily Checklist        | :x:                |
| 8      | :x:                | In Tray / Eingang      | :x:                |
| 9      | :x:                | Kalender               | :x:                |
| 10     | :x:                | Zeitzuordnung          | :x:                |
| 11     | :x:                | "Someday, Maybe" Liste | :x:                |
| 12     | :x:                | Weekly Planning        | :x:                |
| 13     | :x:                | Weekly Review          | :x:                |


### User Interface

| Number | Implemented | Feature               | Tests |
|--------|-------------|-----------------------|-------|
| 14     | :x:         | Anlegen von Tasks     | :x:   |
| 15     | :x:         | Anlegen von Projekten | :x:   |
| 16     | :x:         | Anlegen von Tags      | :x:   |
| 17     | :x:         | Listen anzeigen       | :x:   |
| 18     | :x:         | Next Task             | :x:   |
| 19     | :x:         | Task abschließen      | :x:   |
| 20     | :x:         | Speichern von Tasks   | :x:   |
| 21     | :x:         | Laden von Tasks       | :x:   |


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
