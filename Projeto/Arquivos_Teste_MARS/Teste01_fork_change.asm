.include "macros.asm"

.text
main: 	
	
	fork(P1)
	fork(P2)
	processChange
		
		
P1:					# primeiro loop
	add $s0, $zero, $zero 
	loop1: addi $s0, $s0, 1
	processChange
	j loop1
	
	
P2:					# Segundo loop
	add $s0, $zero, $zero
	loop2: addi $s0, $s0, -1
	processChange
	j loop2
      
      	done
      
