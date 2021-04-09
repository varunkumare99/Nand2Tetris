// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Put your code here.

@i
M=0  // i = 0

@R2
M=0  //RAM[2] = 0

(BEGINNING)
@R0
D=M 
@i
D=D-M
@END
D;JEQ  // if i==R0 then goto END

@R1
D=M
@R2
M=M+D  // R2 = R2 + R1, R1 is added R0 times to get R2 = R0*R1

@i
M=M+1  // i = i + 1
@BEGINNING
0;JMP // goto sum

(END)
@END
0;JMP
