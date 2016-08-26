.include "macros.asm"

      	.text
      	main: 	
      		fork(P1)
		fork(P2)
		#ProcessChange
		
		
	P1:
		add $s0, $zero, $zero
		loop1: addi $s0, $s0, 1
		#ProcessChange
		j loop1
	
	
	P2:
		add $s0, $zero, $zero
		loop2: addi $s0, $s0, -1
		#ProcessChange
		j loop2
	
		done
      
      
      
