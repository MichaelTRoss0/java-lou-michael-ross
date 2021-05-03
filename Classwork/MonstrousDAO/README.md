# Bad Monsters
The objective of this lab is to practice designing and implementing stateful unit testing.

- Copy the Project into your Classwork
- Only add JUnit tests to the test packages section of the project.
- Create a JUnit test suite given the following interface: 
```
    public Monster addMonster(int id, Monster m);
    public Monster getMonster(int id);
    public List<Monster> getAllMonsters();
    public void updateMonster(int id, Monster m);
    public Monster removeMonster(int id);
```
- Run your test suite against the Dao implementations provided. (Make a copy for each!)
	- All tests should pass w/ 'AGoodMonsterDao' implementation.
	- Then run them against each BadMonsterDAO implementation (there are several!)
	 - Find the 'bad code' in each implementation!
 - Write a test for each that will very clearly exercise/uncover each Bad DAO's issue!