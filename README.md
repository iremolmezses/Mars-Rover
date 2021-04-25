MARS ROVER

When the rover touches down on Mars, it is initialised with its current coordinates and the direction it is facing. 

Default initial position is (4, 2, EAST), but this can be changed using application.properties.

The rover can be given a command string (case insensitive) which can broken into concrete commands that the rover can execute.

Supported commands are:

F -> Move forward on current heading

B -> Move backwards on current heading

L -> Rotate left by 90 degrees

R -> Rotate right by 90 degrees
 
Example command string: FLFFFRFLB or flfffrflb

Once a full valid command string has been supplied to /api/command endpoint (HTTP POST - text/plain), the rover executes the command and reports it's current coordinates and heading in the format (x, y) DIRECTION

An example response is (6, 4) NORTH

REQUIREMENTS

1- Java 11

2- Maven 3.6.3

INSTRUCTIONS

1- Run `mvn clean install` and make sure that the build succeeds and all tests pass

2- Run MarsRoverApplication, the service would start at localhost:8080

3- Make POST request to http://localhost:8080/api/command with a command string in text/plain format as request body 
   or import the marsrover.postman_collection.json and just press send
