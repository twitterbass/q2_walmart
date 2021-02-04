```
git clone https://github.com/twitterbass/q2_walmart.git

cd q2_walmart-main 

javac -d . ReservationSystem.java 

javac *.java 

java Solution 
```

Assumptions: 
- Assume the audience likes to sit further from the big screen, then the reservations are handled based the search for open seats from top to bottom (further to closer to the screen) and from left to right. 

- Even though the safety rules are: three seats & 1 rows, I take into account if 2 people sit diagonally to each other by enforcing "3 seat rule" to the row above and below. 

