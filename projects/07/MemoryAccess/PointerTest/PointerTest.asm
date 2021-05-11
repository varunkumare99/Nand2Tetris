//push constant 3030
 @3030
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop pointer 0
 @SP 
 M=M-1  //SP--
 @SP
 A=M
 D=M    //D=*SP
 @THIS 
 M=D    //thisThat = *sp


//push constant 3040
 @3040
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop pointer 1
 @SP 
 M=M-1  //SP--
 @SP
 A=M
 D=M    //D=*SP
 @THAT 
 M=D    //thisThat = *sp


//push constant 32
 @32
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop this 2
 @SP    //SP-- 
 M=M-1 
 @2
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


//push constant 46
 @46
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop that 6
 @SP    //SP-- 
 M=M-1 
 @6
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


//push pointer 0
 @THIS 
 D=M
 @SP
 A=M
 M=D    //*SP = THIS
 @SP 
 M=M+1  //SP++


//push pointer 1
 @THAT 
 D=M
 @SP
 A=M
 M=D    //*SP = THIS
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


//push this 2
 @2
 D=A
 @THIS
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


//push that 6
 @6
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


