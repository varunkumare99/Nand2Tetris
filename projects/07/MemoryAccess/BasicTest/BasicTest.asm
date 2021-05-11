//push constant 10
 @10
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


//push constant 21
 @21
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 22
 @22
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop argument 2
 @SP    //SP-- 
 M=M-1 
 @2
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


//pop argument 1
 @SP    //SP-- 
 M=M-1 
 @1
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


//push constant 36
 @36
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop this 6
 @SP    //SP-- 
 M=M-1 
 @6
 D=A
 @THIS
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


//push constant 42
 @42
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 45
 @45
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop that 5
 @SP    //SP-- 
 M=M-1 
 @5
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


//push constant 510
 @510
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop temp 6
 @SP    //SP-- 
 M=M-1 
 @6
 D=A
 @5
 A=D+A  //D = segment + index
 D=A  
 @R13 
 M=D    //Ram[13] = segment + index
 @SP 
 A=M 
 D=M    //D=*SP
 @R13 
 A=M
 M=D    //*addr = *sp


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


//push that 5
 @5
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


//push this 6
 @6
 D=A
 @THIS
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push this 6
 @6
 D=A
 @THIS
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


//push temp 6
 @6
 D=A
 @5
 A=D+A  //D = segment + index
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


