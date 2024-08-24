# My Personal Project 

I will be designing a **skateboard game** where you have to *avoid obstacles* and *collect items*. Over time the speed 
will increase (subway surfers / google dinosaur vibe), and time will be recorded. This game will display a character 
and obstacles that will be moving toward this character. If the obstacle hits the player, the game will restart or the 
player will lose a life. People who want some entertainment will be playing this game. This project is interesting to 
me because I enjoy skateboarding and playing games like this, and I think it'll be a fun challenge. 



### User Stories

Phase 1 things:
- As a user, I want to be able to add an account to an account list.
- As a user, I want to be able to view all the accounts.
- As a user, I want to be able to add an item to an items list. 
- As a user, I want to be able to view all the items I have collected.
- As a user, I want to be able to view my best time. 
- As a user, I want to be able to be able to add my own account.

Phase 2 things: 
- As a user, I want to be able to save my list of accounts to file (if I so choose)
- As a user, I want to be able to load my list of accounts from file (if I so choose)
- As a user, I want to be able to save my list of items to file (if I so choose)
- As a user, I want to be able to load my list of items from file (if I so choose)
- As a user, I want to be able to save my list of times to file (if I so choose)
- As a user, I want to be able to load my list of times from file (if I so choose)

Later phase things:
- As a user, I want to be able to choose different places to skate.
- As a user, I want to be able to use an item that I have collected. 
- As a user, I want to hear background music.
- As a user, I want to be able to pause the game. 
- As a user, I want to be able to restart the game. 
- As a user, I want to be able to reset all my progress. 

### Instructions for Grader 
**(Phase 3)**
- You can generate the first required action related to the user story "adding multiple X's to Y" by clicking the 
*Add Account* tab and filling in the username and password to add the account to an account list.
- You can generate the second required action related to the user story "adding multiple X's to Y" by clicking the 
*Find Account* tab and filling in the username and password to check if the account exists. 
- You can locate my visual component by clicking on the *Find Account* tab and searching for an account. 
  - happy face if account exists, sad face if not.
- You can save the state of my application by clicking the *Home* tab and then clicking the *save program* button.
- You can reload the state of my application by clicking the *Home* tab and the clicking the 
*load program and show accounts* button.

### Phase 4: Task 2
- Sun Apr 07 17:43:39 PDT 2024
  File loaded.
- Sun Apr 07 17:43:39 PDT 2024
  Account added.
- Sun Apr 07 17:43:39 PDT 2024
  Account added.
- Sun Apr 07 17:43:48 PDT 2024
  File loaded.
- Sun Apr 07 17:43:48 PDT 2024
  Account added.
- Sun Apr 07 17:43:48 PDT 2024
  Account added.
- Sun Apr 07 17:43:48 PDT 2024
  Account added.
- Sun Apr 07 17:43:48 PDT 2024
  File saved.
- Sun Apr 07 17:44:13 PDT 2024
  File loaded.
- Sun Apr 07 17:44:13 PDT 2024
  Account added.
- Sun Apr 07 17:44:13 PDT 2024
  Account added.
- Sun Apr 07 17:44:13 PDT 2024
  Account added.
- Sun Apr 07 17:44:13 PDT 2024
  Account found.
- Sun Apr 07 17:44:17 PDT 2024
  File loaded.
- Sun Apr 07 17:44:17 PDT 2024
  Account added.
- Sun Apr 07 17:44:17 PDT 2024
  Account added.
- Sun Apr 07 17:44:17 PDT 2024
  Account added.
- Sun Apr 07 17:44:17 PDT 2024
  Account not found.
- Sun Apr 07 17:44:45 PDT 2024
  File loaded.
- Sun Apr 07 17:44:45 PDT 2024
  Account added.
- Sun Apr 07 17:44:45 PDT 2024
  Account added.
- Sun Apr 07 17:44:45 PDT 2024
  Account added.
- Sun Apr 07 17:44:45 PDT 2024
  File saved.

### Phase 4: Task 3
(describe the refactoring and your reasons for it) 
<br><br>
If I had more time to work on my project, I would add an extra abstract class *List* that all of my list classes would 
extend. *AccountList*, *ItemList*, and *TimeList* all have similar methods such as *printList* and adding items to 
their own list. Adding an abstract class would reduce duplication of code as well as help organize the classes better. 
You would also only need to add a list field in the abstract class, since all the list classes create a list of 
something (also reducing duplication). Overall an extra *List* abstract class would be a good addition to my project's 
organization. 

