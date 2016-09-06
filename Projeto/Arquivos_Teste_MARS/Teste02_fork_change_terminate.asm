.include "macros.asm"

.text
main: 	
	fork(P1)
	fork(P2)
	fork(P3)
	processChange
	
	
P1:
	add $s0, $zero, 2
	loop1: addi $s0, $s0, -1
	processChange
	bnez $s0, loop1
	processTerminate

P2:
	add $s0, $zero, 3
	loop2: addi $s0, $s0, -1
	processChange
	bnez $s0, loop2
	processTerminate

P3:
	add $s0, $zero, 4
	loop3: addi $s0, $s0, -1
	processChange
	bnez $s0, loop3
	
	done
      
      
