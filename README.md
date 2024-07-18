# My-Paint-JAVA
Basic Paint app made by using java, having very basic features of a typical Paint app.




DESCRIPTION: 
The Basic Paint App is a simple drawing application developed in Java using Swing. It allows users to draw on a canvas with different colors and brush sizes, as well as erase parts of their drawings. The application includes basic features such as clearing the canvas and selecting brush sizes.


FEATURES: 
Draw with different colors: Black, Blue, Green, Red.
Eraser tool to remove parts of the drawing.
Adjustable brush size from 5 to 25.
Clear the canvas to start a new drawing.
User-friendly interface with intuitive controls.


PREREQUISITES: 
Java Development Kit (JDK) 8 or higher
A development environment like IntelliJ IDEA, Eclipse, or any other Java IDE


USAGE: 
Select Color: Click on the color buttons (Black, Blue, Green, Red) to choose your drawing color.
Brush Size: Use the dropdown menu to select the desired brush size.
Eraser: Click the "Eraser" button to switch to the eraser tool. Use the dropdown menu to adjust the eraser size.
Clear Canvas: Click the "Clear" button to clear the entire canvas and start a new drawing.


FUTURE ENHANCEMENTS:
1.Making the application capable to save the data.
2.Adding more paint like features.


CODE OVERVIEW:
BasicPaintApp: The main class that sets up the application window and contains the main method.
DrawingPanel: A custom JPanel where the drawing operations are handled.
clearImage(): Method to clear the drawing canvas.
draw(int x, int y): Method to handle drawing with the selected color and brush size.
erase(int x, int y): Method to handle erasing parts of the drawing.
