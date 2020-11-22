# Kotlin introduction

This README contains table of contents for the 3apedia presentation and serves as a guideline.

## Plan
1. Presentation
2. Basics
    - Variables and immutability
    - Loops, when, if else, elvis operator, nullable, default args
    - Arrays, Lists and Collections
    - Objects, classes, data classes, inheritance, enums
    - Destructuring, Equality, type checks,  
    - Functions, lambdas, higher order functions, extension functions
    - Generics 
    - Sealed classes, type aliases, interfaces
    - Interoperability, annotations and serialization
3. Coding exercises

    These exercises will focus in reading and evaluating data from a data file: 
    `data/hearthstone_data_set.json`. You will need to read the file and serialize
    the json data into data structures for processing.
    
    1. Create a function that will process the data based by player type ("me" or "opponent") and 
    find all types of unique cards played (card history) by the player.
        ```text
        CardName1
        CardName2
        CardName3
        ```

    2. Create a function that will process the data to find all heroes the player played and how many 
    games he won with them, sorted from most wins to least, eg:
        ```text
        Shaman:  Wins 5 Losses 1
        Mage:    Wins 3 Losses 3
        Paladin: Wins 1 Losses 3
        ```
    3. **[Bonus]** Create a function that will process the data to find 5 games where player won, sorted by 
    the total of mana spent on cards. Output should be like the following:
        ```text
        ---------------------------
        totalManaSpent: 50
        playerHero: "Shaman"
        opponentHero: "Warlock"
        opponentTotalManaSpent: 45
        wasPlayerFirst: false
        ---------------------------
        totalManaSpent: 30
        playerHero: "Paladin"
        opponentHero: "Mage"
        opponentTotalManaSpent: 56
        wasPlayerFirst: true
        ---------------------------
        ```
        
4. Concurrency presentation
5. Concurrency
    - Coroutines, cancelation, coroutine scopes and composition
    - Coroutine context and dispatchers
    - Channels and shared mutable state 
    - Flow
6. Coding example 2 (45min)
    1. Create a suspendable function which will fetch "items" concurrently from
    [HackerNewsApi](https://github.com/HackerNews/API). Use already existing implementation to achieve
    this.
       
    2. Create a suspendable function that will accept an array of strings (words) and array of Int ids (item id).
    That function will issue requests for all items and their "kids" items and each upon receiving the result
    will send it to a channel where a number of workers will process the data and search for occurrences of 
    given words and send out to a second channel those results where they will be gathered and printed when the 
    channels get closed.
        ```text
        -fetch(x)-> \                     / -> process(xRes) -> \   
        -fetch(y)->  -> responseChannel ->  -> process(yRes) ->  -> resultChannel -> print
        -fetch(z)-> /                     \ -> process(zRes) -> /
        ```
        For given input like following: `search(listOf(8863, 6723), listOf('tv', 'impression', 'data'))`
        output should look like the following sorted by number of occurrences:
        ```text
        word: "tv"         | occurrences: 10 | in: 4562, 9678 | parent: 2456  
        word: "impression" | occurrences: 3  | in: 1335, 3458 | parent: 6932 
        word: "data"       | occurrences: 5  | in: 4562, 6453 | parent: 9638 
        ```
    3. **[Bonus]** Replace the above task with Flow.

## References
 - [Official Kotlin docs](https://kotlinlang.org/docs/reference/basic-syntax.html)
 - [Kotlin serialization](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/serialization-guide.md)

## Literature
 - [Atomic Kotlin](https://leanpub.com/AtomicKotlin)