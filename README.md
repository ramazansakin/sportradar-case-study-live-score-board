# SportRadar Case Study
## Simple World Cup Live Score Board Application

The project is developed via Maven, Java-17 on Intellij Idea. Firstly, I added a few test libraries to start TDD approach.
Every RGR cycle, I tried to make each commit simple and suitable for TDD so I created the RED cycle first to get a failure on a
specific case then I tried to make it GREEN via JUST developing required part of code and then if needs, I tried to make some
REFACTORINGs to make the code and project architecture clean and also I tried to use some Design Patterns and principles and 
added comments on required places. Now all the test cases are GREEN. I also wanted to add some additional patterns like
Command with Strategy Design Pattern where we are selecting the command and delivering it to specific implementation but 
because of lack of time, I want to leave it simple. 

### How it is running:

#### Commands :
<li> <b><i>start</i></b> : Starts and add a match to the Live Score Board / usage : start Turkey Uruguay</li>
<li> <b><i>update</i></b> : Updates scores for related match on the board / usage : update Turkey 1 Uruguay 0</li>
<li> <b><i>finish</i></b> : Finishes and removes a match from Live Score Board / usage : remove Turkey</li>
<li> <b><i>summary</i></b> : Provides a summary of all matches and scores currently on the Live Score Board / usage : summary</li>

<br/>
<br/>

> Sample Working Scenario

![Sample Working Scenario](/misc/sample-usage-test-case.JPG)