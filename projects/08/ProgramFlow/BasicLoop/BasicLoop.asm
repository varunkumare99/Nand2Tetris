//push constant 0
 @0
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop local 0
 @SP    //SP-- 
 M=M-1 
 @0
 D=A
 @LCL
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


//label LOOP_START
 (BasicLoop.null$LOOP_START) 


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


//push local 0
 @0
 D=A
 @LCL
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


//pop local 0
 @SP    //SP-- 
 M=M-1 
 @0
 D=A
 @LCL
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


//if-goto LOOP_START
 @SP
 M=M-1
 @SP
 A=M
 D=M
 @BasicLoop.null$LOOP_START
 D;JNE 


//push local 0
 @0
 D=A
 @LCL
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


