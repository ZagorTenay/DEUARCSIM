# DEUARCSIM
DEU Electronic Universal Automatic Reduced Computer (DEUARC) Simulator

Simulator for a basic computer with reduced instruction set.

In this project, you are expected to design and develop a simulator for a basic computer with reduced instruction set using object oriented programming paradigm. You should use Java programming language and Swing widget toolkit will to implement. The problem domain description for the DEUARC (DEU Electronic Universal Automatic Reduced Computer) is given below.

DEUARC has 9 registers and 3 memory segments (Figure 1). Registers are Address Register, Program Counter, Stack Pointer, Input Register, Output Register, Instruction Register and 3 general purpose registers. Each register has data, load, clear and clock inputs. Additionally, program counter has increment input and stack pointer has increment and decrement inputs. Memory has three segments which are instruction, data and stack memory segments. Each has read enable and data inputs. Data and stack memory segments have write enable input as an extra. Also DEUARC has control unit and ALU (arithmetic logic unit). Control unit processes instructions to direct the micro-operations for computer's memory, registers and arithmetic/logic unit. ALU operates arithmetic and logic operations such as ADD and AND.

DEUARC simulator converts the assembly code to machine code and simulates the program execution phases. It runs the program step by step while showing the phases of instruction cycle (fetching, decoding, execution). DEUARC simulator reads and parses the assembly code, then shows its label table and memory content table. The simulator shows contents of the registers, memory segments, computer operations and their micro operations. It can simulate all operations that DEUARC supports (Table 1). It provides switching between binary / hexadecimal / decimal numbers and exporting hex or mif file of the machine code (HEX code or binary code).

The input files (asm or basm file) include assembly (symbolic) codes. The assembly language of the basic computer is defined by a set of rules. Data is indicated by ‘#’ and address is indicated by ‘@’ characters. An example for assembly code is given in the Code 1. Each line of the language has three columns called fields.

1. The label field may be empty or it may specify a symbolic address. It is followed by a colon (:).

2. The instruction field specifies a machine instruction (Table 1) or a pseudo-instruction (ORG, END, DEC N, HEX N).

3. The comment field may be empty or it may include a comment after a sharp sign (%).

The format of a line is as follow:
>[label]: instruction [%comment]

