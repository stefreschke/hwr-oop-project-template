# HWR OOP Lecture - Group 5 Project (Leitner System) 

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!

## Abstract

For our project, we are creating a library that helps to implement the Leitner system for index card supported learning.

The most important features of our project are:
1. Topic creation
2. Card creation
3. Choice between 3, 5 and 7 box systems per
4. Drawing a random card from boxes that need to be learned
5. Cards and boxes can be saved to and loaded from a file
6. Cards can be moved between boxes
7. Topics can be loaded

We encountered the following interesting problems:
- object oriented design (especially SRP)


## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature                                       | Tests                                                                                                                      |
|--------|-----------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| 1      | Topic creation                                | canCreateTopicWithName                                                                                                     |
| 2      | Card creation                                 | canCreateCard, canGetQuestion, canGetAnswer, canGetId                                                                      |
| 3      | Box creation                                  | x                                                                                                                          |
| 4      | Box can hold cards                            | canContainCards, boxIsEmptyAfterDrawingAllCards                                                                            |
| 5      | Box can return random card                    | canReturnRandomCard                                                                                                        |
| 6      | Cards can be moved up/down                    | canMoveCardUp, canMoveCardUp2Times, canMoveCardUpTopBox, canMoveCardDown, canMoveCardDown2Times, canMoveCardDownBottomBox, |
| 7      | Random card can be drawn from random box      | canGetRandomCardFromRandomBox, canGetRandomBoxIndex                                                                        |
| 8      | Cards and boxes can be saved and loaded       | BoxPersistenceTests, CardPersistenceTests                                                                                  |
| 9      | Trainer can be initialized with 3/5/7 boxes   | canCreateTrainerWith3Boxes, canCreateTrainerWith5Boxes, canCreateTrainerWith7Boxes                                         |
| 10     | Trainer can be initialized with a saved topic | canCreateTrainerFromSave                                                                                                   |

## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description                                   | Why is it necessary?                                     |
|--------|-----------------|----------------------------------------------------------|----------------------------------------------------------|
| 1      | Jackson         | Library for Serialization and Deserialization of objects | This is needed for persistence in the form of JSON files |

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
