 @256 //SP = 256
 D=A 
 @SP 
 M=D 


 @FibonacciElement.null$ret.0  //PUSH RETURN_ADDRESS
 D=A 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @LCL  //PUSH LCL
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @ARG  //PUSH ARG
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THIS  //PUSH THIS
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THAT  //PUSH THAT
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @SP    //ARG = SP-5-numArgs
 D=M 
 @5 
 D=D-A 
 @0 
 D=D-A 
 @ARG 
 M=D 
 @SP    //LCL=SP
 D=M 
 @LCL 
 M=D 
 @FibonacciElement.Sys.init  //GOTO FUNCTION_NAME
0;JMP 
 (FibonacciElement.null$ret.0)   //(RETURN ADDRESS)


//function Main.fibonacci 0
 (FibonacciElement.Main.fibonacci) 


//push argument 0
 @0
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 2
 @2
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//lt
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=M 
 @SP    //POP val1 
 M=M-1 
 @SP 
 A=M 
 D=M-D  //D=val1 -val2 
 @SET_TRUE_0 
 D;JLT 
 @SET_FALSE_0 
 0;JMP 
 (SET_TRUE_0) 
 D=-1 
 @AFTER_FALSE_0 
 0;JMP 
 (SET_FALSE_0) 
 D=0 
 (AFTER_FALSE_0) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//if-goto IF_TRUE
 @SP
 M=M-1
 @SP
 A=M
 D=M
 @FibonacciElement.Main.fibonacci$IF_TRUE
 D;JNE 


//goto IF_FALSE
 @FibonacciElement.Main.fibonacci$IF_FALSE
 0;JMP 


//label IF_TRUE
 (FibonacciElement.Main.fibonacci$IF_TRUE) 


//push argument 0
 @0
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//return
 @LCL   //END_FRAME(R13) = LCL
 D=M 
 @R13 
 M=D 
 @R13   //RETADDR(R14) = *(END_FRAME(R13)-5) 
 D=M 
 @5 
 A=D-A 
 D=M 
 @R14 
 M=D
 @SP    //*ARG = POP()
 M=M-1 
 @SP 
 A=M 
 D=M 
 @ARG 
 A=M 
 M=D 
 @ARG   //SP = ARG + 1 
 D=M 
 @1 
 D=D+A 
 @SP 
 M=D 
 @R13   //THAT = *(END_FRAME(R13)-1) 
 D=M 
 @1 
 A=D-A 
 D=M 
 @THAT 
 M=D 
 @R13   //THIS = *(END_FRAME(R13)-2) 
 D=M 
 @2 
 A=D-A 
 D=M 
 @THIS 
 M=D 
 @R13   //ARG = *(END_FRAME(R13)-3) 
 D=M 
 @3 
 A=D-A 
 D=M 
 @ARG 
 M=D 
 @R13   //LCL = *(END_FRAME(R13)-4) 
 D=M 
 @4 
 A=D-A 
 D=M 
 @LCL 
 M=D 
 @R14   //GOTO RETADDR 
 A=M 
 0;JMP 


//label IF_FALSE
 (FibonacciElement.Main.fibonacci$IF_FALSE) 


//push argument 0
 @0
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 2
 @2
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//sub
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=M 
 @SP    //POP val1 
 M=M-1 
 @SP 
 A=M 
 D=M-D  //D=val1 -val2 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//call Main.fibonacci 1
 @FibonacciElement.Main.fibonacci$ret.0  //PUSH RETURN_ADDRESS
 D=A 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @LCL  //PUSH LCL
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @ARG  //PUSH ARG
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THIS  //PUSH THIS
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THAT  //PUSH THAT
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @SP    //ARG = SP-5-numArgs
 D=M 
 @5 
 D=D-A 
 @1 
 D=D-A 
 @ARG 
 M=D 
 @SP    //LCL=SP
 D=M 
 @LCL 
 M=D 
 @FibonacciElement.Main.fibonacci  //GOTO FUNCTION_NAME
0;JMP 
 (FibonacciElement.Main.fibonacci$ret.0)   //(RETURN ADDRESS)


//push argument 0
 @0
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 1
 @1
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//sub
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=M 
 @SP    //POP val1 
 M=M-1 
 @SP 
 A=M 
 D=M-D  //D=val1 -val2 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//call Main.fibonacci 1
 @FibonacciElement.Main.fibonacci$ret.1  //PUSH RETURN_ADDRESS
 D=A 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @LCL  //PUSH LCL
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @ARG  //PUSH ARG
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THIS  //PUSH THIS
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THAT  //PUSH THAT
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @SP    //ARG = SP-5-numArgs
 D=M 
 @5 
 D=D-A 
 @1 
 D=D-A 
 @ARG 
 M=D 
 @SP    //LCL=SP
 D=M 
 @LCL 
 M=D 
 @FibonacciElement.Main.fibonacci  //GOTO FUNCTION_NAME
0;JMP 
 (FibonacciElement.Main.fibonacci$ret.1)   //(RETURN ADDRESS)


//add
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=M 
 @SP    //POP val1 
 M=M-1 
 @SP 
 A=M 
 D=M+D  //D=val1 +val2 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//return
 @LCL   //END_FRAME(R13) = LCL
 D=M 
 @R13 
 M=D 
 @R13   //RETADDR(R14) = *(END_FRAME(R13)-5) 
 D=M 
 @5 
 A=D-A 
 D=M 
 @R14 
 M=D
 @SP    //*ARG = POP()
 M=M-1 
 @SP 
 A=M 
 D=M 
 @ARG 
 A=M 
 M=D 
 @ARG   //SP = ARG + 1 
 D=M 
 @1 
 D=D+A 
 @SP 
 M=D 
 @R13   //THAT = *(END_FRAME(R13)-1) 
 D=M 
 @1 
 A=D-A 
 D=M 
 @THAT 
 M=D 
 @R13   //THIS = *(END_FRAME(R13)-2) 
 D=M 
 @2 
 A=D-A 
 D=M 
 @THIS 
 M=D 
 @R13   //ARG = *(END_FRAME(R13)-3) 
 D=M 
 @3 
 A=D-A 
 D=M 
 @ARG 
 M=D 
 @R13   //LCL = *(END_FRAME(R13)-4) 
 D=M 
 @4 
 A=D-A 
 D=M 
 @LCL 
 M=D 
 @R14   //GOTO RETADDR 
 A=M 
 0;JMP 


//function Sys.init 0
 (FibonacciElement.Sys.init) 


//push constant 4
 @4
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//call Main.fibonacci 1
 @FibonacciElement.Sys.init$ret.0  //PUSH RETURN_ADDRESS
 D=A 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @LCL  //PUSH LCL
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @ARG  //PUSH ARG
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THIS  //PUSH THIS
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @THAT  //PUSH THAT
 D=M 
 @SP 
 A=M 
 M=D 
 @SP 
 M=M+1 
 @SP    //ARG = SP-5-numArgs
 D=M 
 @5 
 D=D-A 
 @1 
 D=D-A 
 @ARG 
 M=D 
 @SP    //LCL=SP
 D=M 
 @LCL 
 M=D 
 @FibonacciElement.Main.fibonacci  //GOTO FUNCTION_NAME
0;JMP 
 (FibonacciElement.Sys.init$ret.0)   //(RETURN ADDRESS)


//label WHILE
 (FibonacciElement.Sys.init$WHILE) 


//goto WHILE
 @FibonacciElement.Sys.init$WHILE
 0;JMP 


