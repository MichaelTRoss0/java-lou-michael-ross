# Exercise: Dependency Injection Racer

Your assignment for this lab is to utilize Spring's Dependency injection to wire up a racing simulation app, without touching or manipulating any of the existing Java code.

Remember, to work with Spring's DI - you'll have to create the correctly shaped beans within an application context XML file.

## Complete the following steps:

- Only change the 'car-factory.xml' resource.
- Create 6 racers...
  - One 'HamsterBall'.
  - A JWBeetle w/ a hybrid engine.
  - A Pixel Tank w/ a low powered engine.
  - A Digital Porshe w/ an efficient engine.
  - A Drag Racer w/ a high powered engine.
  - And finally a Vehicle bean called 'theJunker' w/ a Broken engine.
- Make sure each racer w/ an engine has some gas (25 gallons should be good)!
- Create a race that will last 100 miles. And don't forget it's announcer!
- If you've completed the above, first clean & build - but then run the project!

## Completing The Exercise
If you've wired up & run your app successfully - can you answer the following?

- Who won the race?
- How was this DI different with Spring than without?
- Then, stretch goal - add a mechanic to the race. Who's the winner now?