// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//initialize: index, start_index, end_index
@SCREEN
D=A
@start_index		// start_index = 16,383
M=D-1

@KBD
D=A
@end_index		// end_index = 24,576
M=D

@index
M=0          		// index = 0


//paintint screen, listen to keyboard
(LISTEN_KEYBOARD)
@KBD
D=M
@PAINT_WHITE
D;JEQ

//starting painting the screen black
@index
D=M
@SCREEN
D=D+A
@end_index
D=D-M
@LISTEN_KEYBOARD  	// if screen is fully black then go back to listening for keystroke
D;JEQ

@index
D=M
@SCREEN
A=A+D
M=-1            	//RAM[SCREEN+INDEX] = -1
@index
M=M+1 			//index = index + 1
@LISTEN_KEYBOARD       //if no key pressed continue painting black else paint white
0;JMP


(PAINT_WHITE)
@index
D=M
@SCREEN
D=D+A
@start_index
D=D-M
@LISTEN_KEYBOARD  	// if screen is already white then go back to listening for keystroke
D;JEQ

@index
D=M
@SCREEN
A=A+D
M=0   			//RAM[SCREEN+INDEX] = 0
@index
M=M-1 			// index = index - 1
@LISTEN_KEYBOARD    	//if no key pressed continue painting white else paint black
0;JMP
