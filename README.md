# ICS4U0-ISP
Instructions Manual:

1. Overall:

Our game will implement our theme of foraging by teaching children which plants are safe to pick and eat, and which are not. 
We then simulate the experience of foraging in the wild to further the user’s understanding by having them act out the proper procedures. 
A heavy emphasis will be placed on safe identification through simple visual cues, as this will be most pertinent to children. 
There are three levels within the game with their details listed below:

Level 1: Learning level
This level involves various slides users can navigate through that provide detailed information about foods both edible and inedible
This level will focus on providing information to the user 
After going through all given slides in this level, users will be able to move onto the next level to test their knowledge 

Level 2: Test level
This level will allow users to move horizontally through a specifically designed room
Users will have to interact with a certain amount of interactable objects on the screen before completing the level as well as getting the prompts from said objects correct
These interactable objects will receive user input to open a new screen, here the user will be tested on specific edible foods (which varies depending on which object was interacted with)
If the user selects the wrong option, an error message will be displayed prompting the user to select the right option 
If the user selects the correct option a congratulatory message will display and the user will be prompted back to the room where they will be able to interact with other items until the level is completed
When all objects are interacted with, the player may continue to the final level

Level 3: Adventure level
This is the final level where users will be put into an open area and are required to forage for as much edible food as possible by walking around with key controls before the timer (daylight) runs out 
Users will have interactable locations to select edible foods 
Users will not be told if their selected foods are edible or not
To properly harvest the foods, users will have to complete a minigame
At the end of the timer, the foods that the user foraged are displayed, and a win/loss message is displayed. The loss message is descriptive of why the loss occurred.
To win the game after the timer is concluded, the user must have a sufficient amount of foraged goods, as well as no harmful goods


This game is only a 1 player game that will only require user interaction from one person through the keyboard 

Players are required to complete the learning level and test level before moving onto the final level
All levels must be played in sequential order, the option to play the next successive level will be only displayed once all the content in the level is completed


2. Objective: 

Our game focuses on teaching safe foraging for childrens aged 5-10. 
This game will not only educate users about safe foraging practices and identification, but also tests users in a fun and engaging way. 
Foraging has become an increasingly popular pastime, with many children participating in foraging with their parents. 
However, children are often more susceptible to picking unhealthy plants or fungi, because they lack experience.
There has been multiple cases in the past of children eating poisonous mushrooms, which led to some unfortunate deaths.
For children, it's easy to mistake a toxic species for an edible one. Statistics show that out of all the mushroom-ingestion cases of hospitalization, the majority of the reported cases are children under six years old eating everything they can get their hands on, not understanding the consequences. 
As such, this game focuses on teaching children about safe foraging from a young age to ensure their safety. 

3. Relevant Info:

Software name: Fresh Forage Adventure
Target audience: Children ages 5-10
Project lead: Nia Decaire
Project members: Jonathan Liu and Patrick Bian 
Development period: May 5th - June 12th 2023 
Length of game: 20 minutes
Client Company: VK Enterprises 
Client Representative: V.Krasteva, CEO
Required device to play: Functional computer

4. Running the game: 

To  play the game please run the Main.java class or the ISP Executable.exe. This will prompt a splash screen to open and the game to begin. 


5. Specific Details:


Splash Screen:
Main Goal: Display the company logo and transition into the main menu
Controls: N/A

Main Menu:
Main Goal: Provide users the option to play the game or read the instructions beforehand
Controls: Mouse click input that can choose "play" or "instructions", pressing on the 
corresponding word will produce the intended action. 

Instruction:
Main Goal: Provide  all basic instructions to each player (though specific level instructions will come back again)
Controls: Mouse click input can choose to move the instructions to the next slide or the slide before, the arrows determine
which slide to be displayed. If the right arrow is pressed, the slide moves to the instructions in the following slide, if the left arrow
is pressed the slides move to the previous instruction slides. An exit button will be at the end to return to the main menu.

Level 1 (Learning):
Main Goal: Provide information about foraging plants and whihc ones are safe and dangerous
Controls: Only mouse clicking to change the information displayed on the screen to the one after or the one before. Information
is automatically displayed on the screen after a click. The option of Next will be displayed on the last plant, this will move the
user to the next level and can be selected with a mouse click. 

Level 2 (Testing):
Main Goal: Provide a pre-set path for the player to access and use while testing their knowledge
Controls: Mouse clicking and 4 arrow keys. Mouse clicking will be used after stepping on a red square which will prompt the user 
to answer a question. The options are avalible to be selected by a mouse click. As well, a pop up will occur after the input telling
if you selected the correct answer or not. You will be able to exit the pop up with a mouse click of "Ok". After the player moves through 
to the end of the maze, a similar pop up will occur to move to the next level. Movement through the maze is controlled with the 4 arrow keys
each corresponding to the associated action on screen. (Right arrow moves character right, up arrow moves character up, etc...).

Level 3 (Escape):
Main Goal: To fully test the user and allow the user to freely roam and test their new found abilities. The user has to "escape" the forest by
selecting the correct foods to eat.
Controls: Keyboard input is required. The user will be freely able to move, with certain obstacles blockign the path such as trees. The goal is to
collect the correct items before the 2 minute timer (far right corner) is up. Poisonous items at the end of 2 minutes will be an automatic loss. Enough
edible items is also required to win (6 edible items collected). All items are highlighted red regardless of edibility. To move around the map, the character
is controlled with the 4 arrow keys each corresponding to the associated action on screen. (Right arrow moves character right, up arrow moves character up, etc...).
As well, to collect an item the z key must be pressed. This will initiate a minigame which will be discuessed below. To view your currently collected items
the user will press i to view what is in their inventory. The user cannot get rid of their inventory. After the 2 minutes is up, the game brings you to a new 
graphic which will display if you collected the edible items and if you collected enough. The user will then be prompted to exit to the end cards which will be confirmed
through mouse clicks on the on-screen buttons.

 
Level 3 - Minigame:
Main Goal: Avoid the dangerous bees and collect the item.
Controls: Keyboard arrows. The character can only be moved left and right using the left and right arrow keys respectively. The user will try to dodge the bees and collect the item. 
If too many bees are collected (health bar on top left), the item will not be successfully collected. If enough items are collected (green bar on left), the user will successfully collect the item.
A onscreen notification will confirm this, later placing the user back into the main map to collect more items. The 2-minute timer will not be active during the minigame.

Miscellaneous: 
Main Goal: N/A
Controls: Mouse clicks. Any pop ups or buttons can be clicked using the mouse/keypad, they will perform the action being attempted. 





