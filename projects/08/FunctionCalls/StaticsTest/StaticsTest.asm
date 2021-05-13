 @256 //SP = 256
 D=A 
 @SP 
 M=D 


 @StaticsTest.null$ret.0  //PUSH RETURN_ADDRESS
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
 @StaticsTest.Sys.init  //GOTO FUNCTION_NAME
0;JMP 
 (StaticsTest.null$ret.0)   //(RETURN ADDRESS)


//function Class1.set 0
 (StaticsTest.Class1.set) 


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


//pop static 0
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @Class1.0 
 M=D    //static.i = *sp


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


//pop static 1
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @Class1.1 
 M=D    //static.i = *sp


//push constant 0
 @0
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


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


//function Class1.get 0
 (StaticsTest.Class1.get) 


//push static 0
 @Class1.0 
 D=M
 @SP
 A=M
 M=D    //*SP = static.index
 @SP 
 M=M+1  //SP++


//push static 1
 @Class1.1 
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


//function Class2.set 0
 (StaticsTest.Class2.set) 


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


//pop static 0
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @Class2.0 
 M=D    //static.i = *sp


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


//pop static 1
 @SP    //POP val 
 M=M-1 
 @SP 
 A=M 
 D=M    //D=*SP
 @Class2.1 
 M=D    //static.i = *sp


//push constant 0
 @0
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


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


//function Class2.get 0
 (StaticsTest.Class2.get) 


//push static 0
 @Class2.0 
 D=M
 @SP
 A=M
 M=D    //*SP = static.index
 @SP 
 M=M+1  //SP++


//push static 1
 @Class2.1 
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
 (StaticsTest.Sys.init) 


//push constant 6
 @6
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 8
 @8
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//call Class1.set 2
 @StaticsTest.Sys.init$ret.0  //PUSH RETURN_ADDRESS
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
 @2 
 D=D-A 
 @ARG 
 M=D 
 @SP    //LCL=SP
 D=M 
 @LCL 
 M=D 
 @StaticsTest.Class1.set  //GOTO FUNCTION_NAME
0;JMP 
 (StaticsTest.Sys.init$ret.0)   //(RETURN ADDRESS)


//pop temp 0
 @SP    //SP-- 
 M=M-1 
 @0
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


//push constant 23
 @23
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//push constant 15
 @15
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//call Class2.set 2
 @StaticsTest.Sys.init$ret.1  //PUSH RETURN_ADDRESS
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
 @2 
 D=D-A 
 @ARG 
 M=D 
 @SP    //LCL=SP
 D=M 
 @LCL 
 M=D 
 @StaticsTest.Class2.set  //GOTO FUNCTION_NAME
0;JMP 
 (StaticsTest.Sys.init$ret.1)   //(RETURN ADDRESS)


//pop temp 0
 @SP    //SP-- 
 M=M-1 
 @0
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


//call Class1.get 0
 @StaticsTest.Sys.init$ret.2  //PUSH RETURN_ADDRESS
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
 @StaticsTest.Class1.get  //GOTO FUNCTION_NAME
0;JMP 
 (StaticsTest.Sys.init$ret.2)   //(RETURN ADDRESS)


//call Class2.get 0
 @StaticsTest.Sys.init$ret.3  //PUSH RETURN_ADDRESS
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
 @StaticsTest.Class2.get  //GOTO FUNCTION_NAME
0;JMP 
 (StaticsTest.Sys.init$ret.3)   //(RETURN ADDRESS)


//label WHILE
 (StaticsTest.Sys.init$WHILE) 


//goto WHILE
 @StaticsTest.Sys.init$WHILE
 0;JMP 


