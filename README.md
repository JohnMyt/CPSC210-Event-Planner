# My Personal Project

## About My Prersonal Project

I plan on designing a calender which allows you to create sports events on any day, note the scores
of the sports events and view statistics on past sports events. The sports events should allow 
non-rudimentary information to be displayed like: who is playing, where is the event and recording of game. 
People like myself, who play many different sports or even play a sport often and are interested in how they 
are preforming/improving and easy understandability will benefit from my application and would find it useful.

## User Stories

- As a user, I want to be able to add scores to sporting events (complete)
- As a user, I want to be able to see scores of sporting events (complete)
- As a user, I want to be able to add sporting events to different days (complete)
- As a user, I want to be able to remove sporting events in different days (complete)
- As a user, I want to be able to view sporting events that are present in different days (complete)
- As a user, I want to be able to modifiy sporting events for different days (complete)
- As a user, I want to be able to view results of sporting events that are present in different days (complete)
- As a user, I want to be able to view all the days sporting events that have been added to the listofdays (complete)

- As a user, I want to be able to save all the days, their sporting events and scores to file (complete)
- As a user, I want to be able to load all the days, their sporting events and scores from saved file (complete)

# Instructions for End User

- You can view the list of sporting events in the ViewEvents tab (it automatically updates when you 
interact with the ui and manipulate the list of days) (no pop up at start or end)

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by: 
going to the ModifyDays tab and selecting the day, month, year and clicking the "Select/Create Day" button

- You can generate the first additional required action related to the user story "removing all X from Y" by: 
going to the ViewEvents tab and clicking the "Clear List of Days" button

- You can generate the second additional required action related to the user story "removing multiple Xs from Y" by: 
going to the ModifyDays tab and selecting a day, month, year and clicking the "Select/Create Day" button 
(if the date has already been added it will not create a new day) (it shows your selected day that you can delete), 
then clicking the "Remove Selected Day" button 

- You can locate my visual component (bar chart) by: going to the Events Char tab (it automatically updates when 
list of day is modified)

- You can save the state of my application by: going to the ViewEvents tab and clicking the 
"save the list of days to file" button

- You can reload the state of my application by: going to the ViewEvents tab and clicking the 
"load the list of days from file" button

# "Phase 4: Task 2"

- Fri Mar 28 01:04:10 PDT 2025
New Day Added: 1/1/2000
- Fri Mar 28 01:04:13 PDT 2025
New Sporting Event Added:  to 1/1/2000
- Fri Mar 28 01:04:14 PDT 2025
New Sporting Event Added: v to 1/1/2000
- Fri Mar 28 01:04:16 PDT 2025
New Sporting Event Added: b to 1/1/2000
- Fri Mar 28 01:04:20 PDT 2025
New Score Added: 0:0 for v
- Fri Mar 28 01:04:22 PDT 2025
New Score Added: 4:0 for v
- Fri Mar 28 01:04:23 PDT 2025
New Score Added: 4:13 for v
- Fri Mar 28 01:04:25 PDT 2025
New Score Added: 15:13 for v
- Fri Mar 28 01:04:31 PDT 2025
New Day Added: 12/1/2000
- Fri Mar 28 01:04:35 PDT 2025
New Sporting Event Added: vb to 12/1/2000
- Fri Mar 28 01:04:37 PDT 2025
New Score Added: 12:13 for vb
- Fri Mar 28 01:04:45 PDT 2025
New Day Added: 14/1/2000
- Fri Mar 28 01:04:48 PDT 2025
Saved the list of days to file
- Fri Mar 28 01:04:51 PDT 2025
List of Days Cleared
- Fri Mar 28 01:04:52 PDT 2025
Started loading a new list of days from file
- Fri Mar 28 01:04:52 PDT 2025
New Day Added: 1/1/2000
- Fri Mar 28 01:04:52 PDT 2025
New Sporting Event Added:  to 1/1/2000
- Fri Mar 28 01:04:52 PDT 2025
New Sporting Event Added: v to 1/1/2000
- Fri Mar 28 01:04:52 PDT 2025
New Sporting Event Added: b to 1/1/2000
- Fri Mar 28 01:04:52 PDT 2025
New Day Added: 12/1/2000
- Fri Mar 28 01:04:52 PDT 2025
New Sporting Event Added: vb to 12/1/2000
- Fri Mar 28 01:04:52 PDT 2025
New Day Added: 14/1/2000
- Fri Mar 28 01:04:52 PDT 2025
Finished loading the list of days from file
- Fri Mar 28 01:05:00 PDT 2025
New Sporting Event Added: vba to 14/1/2000
- Fri Mar 28 01:05:04 PDT 2025
New Day Added: 28/1/2000
- Fri Mar 28 01:05:18 PDT 2025
Day Removed: 28/1/2000
- Fri Mar 28 01:05:26 PDT 2025
Day Removed: 12/1/2000

# "Phase 4: Task 3"

- Most of the classes in my models package require me to add/remove objects for a list of objects. So, a refactoring I might use to improve my design is adding a generalized helper class for adding and removing objects from a list of objects. This class would be in the model package and would be abstract. Other classes that have adding and removing mechanisms would then extend this class and be able to call the function to add/remove the object (parameter) to/from the list of objects (parameter). Overall, this would reduce redundant/similar code, making the code more understandable and readable.

- Looking at the UML diagram you can see that the ScoreBoard class does not implement writable interface in the persistance package, which is confusing. So, a refactoring I might use to improve my design is relooking at how my Score and ScoreBoard classes are working toghether since, I know that when I run the ui the scores are being saved. What I would probably do is combined them into one class because scores only represents two integers (your and opponenents score). Ultimatly, this would make the design simpilar and reduce uncessary code, making the code more understandable and readable.