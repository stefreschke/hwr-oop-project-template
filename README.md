# Most impressive Doppelkopf Experience!!! <3

This repository contains a student project created for an ongoing lecture on object-oriented
programming with Java/Kotlin at HWR Berlin (summer term 2024).

> :warning: This code is for educational purposes only. Do not rely on it!

## Prerequisites

Installed:

1. IDE of your choice (e.g. IntelliJ IDEA)
2. JDK of choice installed (e.g. through IntelliJ IDEA)
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

We developed the famous german Card Game Doppelkopf in a Team of 4. 
Now we are grateful to present you the final Result. 
We hope you love it, cause we do. 
Enjoy the Game and have Fun. 

Game is playable.
Extra roles: 
-Zwangshochzeit
-Schweinchen
-Schmeissen
-Herz Ass (highest Value)

Getting to 100% Line Coverage and 99% Mutation Coverage was tough.
We had some major issues getting away from the original GameLoop method. 
Furthermore, we encountered some Problem testing the Persistence Methods. 
But after all we finished with an overall great Pitest Result.  

## Feature List

| Number | Feature         | Tests                                                             |
|--------|-----------------|-------------------------------------------------------------------|
| 1      | Card            | CardTest                                                          |
| 2      | CardGenerator   | CardGenerationTest                                                |
| 3      | CardStack       | ShuffleCardStackTest                                              |
| 4      | Game            | GameEngineTest / CompleteGameTest / HandOutCardsTest / PointsTest |
| 5      | GamePersistence | GamePersistenceTest                                               |
| 6      | Player          | CreatePlayersTest                                                 | 
| 7      | Stich           | StichTest                                                         |

 

## Additional Dependencies

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | /               | /                      | /                    |
