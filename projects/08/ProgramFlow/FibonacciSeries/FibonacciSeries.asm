//push argument 1
 @1
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//pop pointer 1
 @SP 
 M=M-1  //SP--
 @SP
 A=M
 D=M    //D=*SP
 @THAT 
 M=D    //thisThat = *sp


//push constant 0
 @0
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop that 0
 @SP    //SP-- 
 M=M-1 
 @0
 D=A
 @THAT
 A=D+M  //D = segment + index
 D=A  
 @R13 
 M=D    //Ram[13] = segment + index
 @SP 
 A=M 
 D=M    //D=*SP
 @R13 
 A=M
 M=D    //*addr = *sp


//push constant 1
 @1
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop that 1
 @SP    //SP-- 
 M=M-1 
 @1
 D=A
 @THAT
 A=D+M  //D = segment + index
 D=A  
 @R13 
 M=D    //Ram[13] = segment + index
 @SP 
 A=M 
 D=M    //D=*SP
 @R13 
 A=M
 M=D    //*addr = *sp


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


//pop argument 0
 @SP    //SP-- 
 M=M-1 
 @0
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=A  
 @R13 
 M=D    //Ram[13] = segment + index
 @SP 
 A=M 
 D=M    //D=*SP
 @R13 
 A=M
 M=D    //*addr = *sp


//label MAIN_LOOP_START
 (FibonacciSeries.null$MAIN_LOOP_START) 


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


//if-goto COMPUTE_ELEMENT
 @SP
 M=M-1
 @SP
 A=M
 D=M
 @FibonacciSeries.null$COMPUTE_ELEMENT
 D;JNE 


//goto END_PROGRAM
 @FibonacciSeries.null$END_PROGRAM
 0;JMP 


//label COMPUTE_ELEMENT
 (FibonacciSeries.null$COMPUTE_ELEMENT) 


//push that 0
 @0
 D=A
 @THAT
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push that 1
 @1
 D=A
 @THAT
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


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


//pop that 2
 @SP    //SP-- 
 M=M-1 
 @2
 D=A
 @THAT
 A=D+M  //D = segment + index
 D=A  
 @R13 
 M=D    //Ram[13] = segment + index
 @SP 
 A=M 
 D=M    //D=*SP
 @R13 
 A=M
 M=D    //*addr = *sp


//push pointer 1
 @THAT 
 D=M
 @SP
 A=M
 M=D    //*SP = THIS
 @SP 
 M=M+1  //SP++


//push constant 1
 @1
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


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


//pop pointer 1
 @SP 
 M=M-1  //SP--
 @SP
 A=M
 D=M    //D=*SP
 @THAT 
 M=D    //thisThat = *sp


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


//pop argument 0
 @SP    //SP-- 
 M=M-1 
 @0
 D=A
 @ARG
 A=D+M  //D = segment + index
 D=A  
 @R13 
 M=D    //Ram[13] = segment + index
 @SP 
 A=M 
 D=M    //D=*SP
 @R13 
 A=M
 M=D    //*addr = *sp


//goto MAIN_LOOP_START
 @FibonacciSeries.null$MAIN_LOOP_START
 0;JMP 


//label END_PROGRAM
 (FibonacciSeries.null$END_PROGRAM) 


