 @256 //SP = 256
 D=A 
 @SP 
 M=D 


 @NestedCall.null$ret.0  //PUSH RETURN_ADDRESS
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
 @NestedCall.Sys.init  //GOTO FUNCTION_NAME
0;JMP 
 (NestedCall.null$ret.0)   //(RETURN ADDRESS)


//function Sys.init 0
 (NestedCall.Sys.init) 


//push constant 4000
 @4000
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


//push constant 5000
 @5000
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


//call Sys.main 0
 @NestedCall.Sys.init$ret.0  //PUSH RETURN_ADDRESS
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
 @NestedCall.Sys.main  //GOTO FUNCTION_NAME
0;JMP 
 (NestedCall.Sys.init$ret.0)   //(RETURN ADDRESS)


//pop temp 1
 @SP    //SP-- 
 M=M-1 
 @1
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


//label LOOP
 (NestedCall.Sys.init$LOOP) 


//goto LOOP
 @NestedCall.Sys.init$LOOP
 0;JMP 


//function Sys.main 5
 (NestedCall.Sys.main) 
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
 @SP    //LOCAL VAR_+3 = 0
 A=M
 M=0
 @SP    //SP++
 M=M+1
 @SP    //LOCAL VAR_+4 = 0
 A=M
 M=0
 @SP    //SP++
 M=M+1
 @SP    //LOCAL VAR_+5 = 0
 A=M
 M=0
 @SP    //SP++
 M=M+1


//push constant 4001
 @4001
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


//push constant 5001
 @5001
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


//push constant 200
 @200
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop local 1
 @SP    //SP-- 
 M=M-1 
 @1
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


//push constant 40
 @40
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop local 2
 @SP    //SP-- 
 M=M-1 
 @2
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


//push constant 6
 @6
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//pop local 3
 @SP    //SP-- 
 M=M-1 
 @3
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


//push constant 123
 @123
 D=A
 @SP
 A=M
 M=D    //*SP = *i
 @SP 
 M=M+1  //SP++


//call Sys.add12 1
 @NestedCall.Sys.main$ret.0  //PUSH RETURN_ADDRESS
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
 @NestedCall.Sys.add12  //GOTO FUNCTION_NAME
0;JMP 
 (NestedCall.Sys.main$ret.0)   //(RETURN ADDRESS)


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


//push local 2
 @2
 D=A
 @LCL
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push local 3
 @3
 D=A
 @LCL
 A=D+M  //D = segment + index
 D=M    //D = *addr
 @SP    //Push value 
 A=M 
 M=D 
 @SP    //SP++
 M=M+1 


//push local 4
 @4
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


//function Sys.add12 0
 (NestedCall.Sys.add12) 


//push constant 4002
 @4002
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


//push constant 5002
 @5002
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


//push constant 12
 @12
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


