# Todo-List-App

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

> :warning: This code is for educational purposes only. Do not rely on it!

## Table of Contents

- [Abstract](#abstract)
- [Features](#features)
- [Classes and Tests](#classes-and-tests)
- [Dependencies](#dependencies)

## Abstract

This repository contains a todo application that allows users to create and manage tasks. Tasks can be assigned tags and projects for better organization and categorization.

## Features

- Task management: Users can create, view, update, and delete tasks.
- Tag management: Users can assign tags to tasks for categorization.
- Project management: Users can assign projects to tasks for better organization.
- Menus: The application has menus for easy navigation and interaction.
- Input/Output control: An Io Controller handles input and output operations.

## Classes and Tests

The project consists of the following classes and their corresponding test classes:


| Class                | Description                 | Test Class                |
|----------------------|-----------------------------|---------------------------|
| Task                 | Represents a task            | TaskTest                  |
| Tag                  | Represents a tag             | TagTest                   |
| Project              | Represents a project         | ProjectTest               |
| TodoList             | Manages the task list        | TodoListTest              |
| TaskFactory          | creates Tasks                | TaskFactoryTest           |
| Menu                 | Management of menu options   | MenuTest                  |
| MenuOption           | Represents a menu option     | MenuOptionTest            |
| SelectionResponse    | Response to selected MenuOption   | SelectionResponseTest|
| TaskMenu             | Options on tasks             | TaskMenuTest              |
| TagMenu              | Options on tags              | TagMenuTest               |
| ProjectMenu          | Options on projects          | ProjectMenuTest           |
| TaskData             | data of tasks                | TaskDataTest              |
| ProjectData          | data of projects             | ProjectDataTest           |
| TaskState (Enum)     | state of the tasks           | TaskStateTest             |
| IoController         | Handles input/output         | IoControllerTest          |
| ApplicationController | Controls the application    | ApplicationControllerTest  |

Bitte beachten Sie, dass die Testklassen den tatsächlichen Namen der Klassen gefolgt von "Test" haben.

## Dependencies

The project does not have any additional dependencies beyond the core Java libraries.

