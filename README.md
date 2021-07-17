## Project 1
The following chips were implemented in HDL:
1. Not
2. And
3. Or
4. Xor
5. Mux
6. DMux
7. Not16
8. And16
9. Or16
10. Mux16
11. Or8Way
12. Mux4Way16
13. Mux8Way16
14. DMux4Way
15. DMux8Way


## Project 2
The following chips were implemented in HDL:
1. HalfAdder
2. FullAdder
3. Add16
4. Inc16
5. ALU



## Project 3
The following chips were implemented in HDL:
1. 1-Bit Register
2. 16-Bit Register
3. RAM8 16-bit / 8-register
4. RAM64 16-bit / 64-register
5. RAM512 16-bit / 512-register
6. RAM4K 16-bit / 4096-register
7. RAM16K 16-bit / 16384-register
8. 16 bit Program Counter



## Project 4
The following programs were implemented in HACK assembly:
1. Mult.asm - Multiplication: in the Hack computer, the top 16 RAM words (RAM[0]...RAM[15]) are also referred to as R0...R15. 
This program computes the value R0*R1 and stores the result in R2
2. Fill.asm - I/O handling: this program illustrates low-level handling of the screen and keyboard devices, as follows. The program runs an infinite loop that listens to the keyboard input. When a key is pressed (any key), the program blackens the screen, i.e. writes "black" in every pixel; the screen should remain fully black as long as the key is pressed.When no key is pressed, the program clears the screen, i.e. writes "white" in every pixel; the screen should remain fully clear as long as no key is pressed.


## Project 5
The following chips were implemented in HDL:
1. Memory
2. CPU
3. Computer


## Project 6
Assembler program that translates programs written in the symbolic Hack assembly language into binary code that can execute on the Hack hardware platform.

#### Usage
prompt> java -jar Assembler_project.jar Xxx.asm

This command should create a new Xxx.hack file that can be executed as-is on
the Hack computer.

## Project 7
VM translator- focusing on the implementation of the VM language's stack arithmetic and memory accesscommands. 

####  Arithmetic Commands
- add
- sub
- neg
- eq
- gt
- lt
- and
- or
- not

#### Memory access Commands
- pop segment i
- push segment i

#### Usage
prompt> java -jar VMtranslator_project.jar Xxx.vm

This command should create a new Xxx.asm file.


## Project 8
VM translator- focusing on the implementation of the VM language's branching commands and function commands. 

####  Branching Commands
- label LABEL
- goto LABEL
- if-goto LABEL

#### Function Commands
- function FUNCTIONNAME nVars
- call FUNCTIONNAME nArgs
- return

#### Usage
prompt> java -jar VMtranslatorComplete_project.jar Xxx

This command should create a new Xxx.asm file. Where XXX is a single vm file or a directory which contains multiple vm files.

## Project 9
Snake Game implemented in Jack Language.
Run it in VM emulator.
![Snake GamePlay](https://github.com/varunkumare99/Nand2Tetris/blob/main/projects/09/SnakeGame/snakeGame.gif)
![Snake Game](https://github.com/varunkumare99/Nand2Tetris/blob/main/projects/09/SnakeGame/image1Score.png)
![Game Score](https://github.com/varunkumare99/Nand2Tetris/blob/main/projects/09/SnakeGame/image2Game.png)

## Project 10
Compiler I : Syntax Analysis
A syntax analyzer that parses Jack programs according to the Jack grammar, producing an XML file that renders the program's structure using marked-up text. 

####  Tokenizer
Tokenizing, a basic service of any syntax analyzer, is the act of breaking a given textual input into a stream of tokens.

#### Parser(Compilation Engine)
parsing is defined narrowly as the act of going over the tokenized input and rendering its grammatical structure using some agreed-upon format. 
The specific parser that we implement here is based on the Jack grammar, and is designed to emit XML output.

#### Usage
prompt> java -jar Complier_1.jar Xxx

For every jack file present the in directory (Xxx) it generates a corresponding XML file.


## Project 11
Compiler II : code generation.
The implementation is based on morphing the syntax analyzer built in the previous project(10) into a full-scale compiler.

#### Symbol Table
The syntax analyzer outputs the following information as part of its XML output using the symbol table:
  - The identifier's name, as done by the current version of the syntax analyzer.
  - The identifier's category: var, argument, static, field, class, or subroutine.
  - If the identifier's category is var, argument, static, or field, the running index assigned to the identifier by the symbol table.
  - Whether the identifier is presently being defined (e.g. the identifier stands for a variable declared in a "var" statement) or used.

#### Code Generation 
It mainly involves morphing the xml output to vm commands.

#### Usage
prompt> java -jar Compiler_2-1.0.0-jar-with-dependencies.jar Xxx

For every jack file present the in directory (Xxx) it generates a corresponding vm file. (provide the complete path of Xxx)

## Project 12
  The following classes were implemented as part of OS:
  - Array.jack
  - Keyboard.jack
  - Math.jack
  - Memory.jack
  - Output.jack
  - Screen.jack
  - String.jack
  - Sys.jack

 Array.jack
 - creation of arrays
 - disposing of arrays were implemented

Keyboard.jack
 - reading characters
 - reading line of characters
 - reading integers
 - detecting keypress were implemented
 
 Math.jack
 - Multiplication
 - Division
 - Square Root
 - max
 - min
 - abs  were implemented using existing hack ALU operations

 Memory.jack
 - alloc
 - dealloc
 - defragmentation were implemented as part of heap management
 
 Output.jack
 - printing characters
 - printing strings
 - printing integers
 - newline
 - backspace
 - moving and displaying cursor using the designed font.

 Screen.jack
 - drawing pixel
 - drawing lines
 - drawing rectangles
 - drawing circles 
 - clear screen were implemented. The Hack computer is a 16 bit. For efficient display methods bit masking techniques were used to manipulate bits.

String.jack
- creation of strings
- appending char to strings
- converting int to string
- converting string to int were implemented.

Sys.jack
- Initialization
- waiting for certain duration
- displaying error codes
- halt were implemented.

[Nand2teris website link] (https://www.nand2tetris.org/course)
