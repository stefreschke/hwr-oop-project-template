![Lint/Fmt](https://github.com/dev-laky/PeakPoker/actions/workflows/check_lint_and_format.yaml/badge.svg?branch=main)
![Testing](https://github.com/dev-laky/PeakPoker/actions/workflows/test.yaml/badge.svg?branch=main)

![image info](/assets/img/peakpoker2.jpeg)

# Peak Poker - Up to Everest!

[TODO]: # (Change README.md Headline to better fit to your project!)

This repository contains a student project created for an ongoing lecture on object-oriented
programming with Kotlin at HWR Berlin (summer term 2025).

> :warning: This code is for educational purposes only. Do not rely on it!

## Development Prerequisites

Installed:

1. IDE of your choice (e.g. IntelliJ IDEA)
2. JDK of choice installed (JDK 21 GraalVM recommended)
3. Maven installed (e.g. through IntelliJ IDEA)
4. Git installed

## Local Development

This project uses [Apache Maven][maven] as build tool.

To build from your shell (without an additional local installation of Maven), ensure that `./mvnw`
is executable:

```
chmod +x ./mvnw
```

I recommend not to dive into details about Maven at the beginning.
Instead, you can use [just][just] to build the project.
It reads the repositories `justfile` which maps simplified commands to corresponding sensible Maven
calls.

With _just_ installed, you can simply run this command to perform a build of this project and run
all of its tests:

```
just build
```

## Abstract

[TODO]: # (Write a short description of your project.)
[TODO]: # (State most important features.)
[TODO]: # (State the most interesting problems you encountered during the project.)

### Project Description

Have you ever wanted to reach the peak of poker? 
This project aims to create a poker game that allows players to experience the thrill of climbing to the top of the poker world - up to the poker everest!
You can play virtual poker games via the *CLI* against other players or a computer opponent.
Start with free 10$ and try to climb up the wealth ladder.

### Challenges

tbd

### Features

- Customize amount of computer players
- Customize amount of human players
- Customize difficulty of computer players
- Customize starting money
- Customize number of rounds
- Customize amount of chips

## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature | Tests |
|--------|---------|-------|
| 1      | /       | /     |

## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description            | Why is it necessary?                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|--------|-----------------|-----------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1      | clikt           | Command Line Interface for Kotlin | Clikt is necessary for this project because it provides a simple and intuitive way to create command-line interfaces in Kotlin. This is especially important for team collaboration as it ensures that all team members can easily understand and modify the command-line interface code. Additionally, Clikt helps in maintaining consistency and reducing boilerplate code, which improves the overall development efficiency and code quality. Lastly it provides command documentation by default. |

## Instructions

[TODO]: # (Remove these instructions once you finished your fork's setup.)

Use a fork of this repository to do implement your project.

Remember to add this repository as a second remote repository (upstream) and pull from the correct
remotes.
This is necessary, because we might apply changes to this template during the next month.

The following section describes how to add multiple remote repositories to your local repository,
which is cloned from the fork.

### Multiple remote repositories

Your local repository should have a reference to both the fork (your own remote repository)
and the original remote repository.
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

[maven]: https://maven.apache.org/
[just]: https://github.com/casey/just
