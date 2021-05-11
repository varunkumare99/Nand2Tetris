//push constant 111
 @111
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 333
 @333
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 888
 @888
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop static 8
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @StaticTest.8 
 M=D    //static.i = *sp


//pop static 3
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @StaticTest.3 
 M=D    //static.i = *sp


//pop static 1
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @StaticTest.1 
 M=D    //static.i = *sp


//push static 3
 @StaticTest.3 
 D=M
 @SP
 A=M
 M=D    //*SP = static.index
 @SP 
 M=M+1  //SP++


//push static 1
 @StaticTest.1 
 D=M
 @SP
 A=M
 M=D    //*SP = static.index
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


//push static 8
 @StaticTest.8 
 D=M
 @SP
 A=M
 M=D    //*SP = static.index
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


