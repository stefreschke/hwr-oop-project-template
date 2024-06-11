# HWR OOP Lecture Project Template

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

This is an application for the game "Doppelkopf". It's a card game for four players.

At the moment it's possible to create a game with four players. A card deck can be created, shuffled and dealt to the
four players hands. Then every player can choose a card to play and it will be checked, if it's allowed to play this card. After that all played cards can be compared. Every player has a score. After a comparison the points of the four played cards are added at the score of
the player with the highest card.

At first one problem was testing more test cases in one test. If we can test only one test case per test, we would need
too many tests. With soft assertion now we have an opportunity to test more test cases in one test.

An other problem was to omit println commands and to find an other possibility to log out informations.

## Feature List

| Number | Feature                                                                                                       | Tests                                                                                                                                                                                                                        |
|--------|---------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1      | create 48 cards with a shortcut (four colors, six different values, each card twice)                          | testAllCards, testAllCardsForInitialize, testCountCards, testColor                                                                                                                                                           |
| 2      | cards have a shortcut, a strenght and a value                                                                 | testShortcut, testPointsAndStrenght                                                                                                                                                                                          | 
| 3      | shuffle and deal cards                                                                                        | testShuffle, checkPlayerHands                                                                                                                                                                                                |
| 4      | create 4 Players (own cards,own score)                                                                        | testCreatePlayer, checkPlayerHands, testPoints                                                                                                                                                                               |
| 5      | play one round (every player plays one card and get a winner)                                                 | testOneRound                                                                                                                                                                                                                 | 
| 6      | compare cards and find the card with the highest strength                                                     | testFIndHighestCard                                                                                                                                                                                                          |
| 7      | create game thru CLI-Command "game `gameID` create `player1` `player2` `player3` `player4`"                   | testCreateGameWithNewID, testCreateGameWithFileNotExisting                                                                                                                                                                   |  
| 8      | File is saved to current working directory                                                                    | testCreateGameWhenGameAlreadyExists                                                                                                                                                                                          | 
| 9      | Re and Contra Parties                                                                                         | testSetGroupWithCards, testSetGroup                                                                                                                                                                                          |
| 10     | check if played card is even allowed to play                                                                  | testCheckCard                                                                                                                                                                                                                |
| 11     | current players of a game are saved also in the doppelkopf.csv file along with all cards they own             | testSavePlayersToFile, testFormatPlayerCardsReturn, testUpdateLine_ContainsPlayerName, testUpdateLine_DoesNotContainPlayerName, testUpdateFileContent_GameIDMatches, testUpdateFileContent_GameIDDoesNotMatch, testWriteFile |
| 12     | the CLI-Command "game play" initialize, shuffles, deal the cards and also save each players cards in the file | testDealCardsIsCalled, testShuffleDeckIsCalled, testExecuteCallsSaveCards, testPlayCommand_initCards, testParsePlayer, testParseCommandExecutesCreateCommand, testParseCommandWithNoArgs                                     |


## Additional Dependencies

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| /      | /               | /                      | /                    |

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
