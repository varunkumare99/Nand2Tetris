//function SimpleFunction.test 2
 (SimpleFunction.SimpleFunction.test) 
 @SP    //LOCAL VAR_+1 = 0
 A=M
 M=0
 @SP    //SP++
 M=M+1
 @SP    //LOCAL VAR_+2 = 0
 A=M
 M=0
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


//push local 1
 @1
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


//not
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=!M   //D=!val1 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


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


