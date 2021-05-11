//push constant 17
 @17
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 17
 @17
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//eq
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
 D;JEQ 
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


//push constant 17
 @17
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 16
 @16
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//eq
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
 @SET_TRUE_1 
 D;JEQ 
 @SET_FALSE_1 
 0;JMP 
 (SET_TRUE_1) 
 D=-1 
 @AFTER_FALSE_1 
 0;JMP 
 (SET_FALSE_1) 
 D=0 
 (AFTER_FALSE_1) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 16
 @16
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 17
 @17
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//eq
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
 @SET_TRUE_2 
 D;JEQ 
 @SET_FALSE_2 
 0;JMP 
 (SET_TRUE_2) 
 D=-1 
 @AFTER_FALSE_2 
 0;JMP 
 (SET_FALSE_2) 
 D=0 
 (AFTER_FALSE_2) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 892
 @892
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 891
 @891
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
 @SET_TRUE_3 
 D;JLT 
 @SET_FALSE_3 
 0;JMP 
 (SET_TRUE_3) 
 D=-1 
 @AFTER_FALSE_3 
 0;JMP 
 (SET_FALSE_3) 
 D=0 
 (AFTER_FALSE_3) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 891
 @891
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 892
 @892
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
 @SET_TRUE_4 
 D;JLT 
 @SET_FALSE_4 
 0;JMP 
 (SET_TRUE_4) 
 D=-1 
 @AFTER_FALSE_4 
 0;JMP 
 (SET_FALSE_4) 
 D=0 
 (AFTER_FALSE_4) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 891
 @891
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 891
 @891
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
 @SET_TRUE_5 
 D;JLT 
 @SET_FALSE_5 
 0;JMP 
 (SET_TRUE_5) 
 D=-1 
 @AFTER_FALSE_5 
 0;JMP 
 (SET_FALSE_5) 
 D=0 
 (AFTER_FALSE_5) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 32767
 @32767
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 32766
 @32766
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//gt
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
 @SET_TRUE_6 
 D;JGT 
 @SET_FALSE_6 
 0;JMP 
 (SET_TRUE_6) 
 D=-1 
 @AFTER_FALSE_6 
 0;JMP 
 (SET_FALSE_6) 
 D=0 
 (AFTER_FALSE_6) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 32766
 @32766
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 32767
 @32767
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//gt
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
 @SET_TRUE_7 
 D;JGT 
 @SET_FALSE_7 
 0;JMP 
 (SET_TRUE_7) 
 D=-1 
 @AFTER_FALSE_7 
 0;JMP 
 (SET_FALSE_7) 
 D=0 
 (AFTER_FALSE_7) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 32766
 @32766
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 32766
 @32766
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//gt
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
 @SET_TRUE_8 
 D;JGT 
 @SET_FALSE_8 
 0;JMP 
 (SET_TRUE_8) 
 D=-1 
 @AFTER_FALSE_8 
 0;JMP 
 (SET_FALSE_8) 
 D=0 
 (AFTER_FALSE_8) 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 57
 @57
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 31
 @31
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 53
 @53
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


//push constant 112
 @112
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


//neg
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=-M   //D=-val1 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//and
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=M 
 @SP    //POP val1 
 M=M-1 
 @SP 
 A=M 
 D=M&D  //D=val1 &val2 
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push constant 82
 @82
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//or
 @SP    //POP val2 
 M=M-1 
 @SP 
 A=M 
 D=M 
 @SP    //POP val1 
 M=M-1 
 @SP 
 A=M 
 D=M|D  //D=val1 |val2 
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


