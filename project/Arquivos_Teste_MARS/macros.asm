########## SERVI�OS DE SO ################
# Done - fim de programa sem erros
.macro done
	li $v0,10	#servi�o de fim de programa
	syscall
.end_macro

# Criar processos
.macro fork(%ptr)
	la $a0, %ptr
	li $v0, 19
	syscall
.end_macro

# Trocar processos
.macro processChange
	li $v0, 20
	syscall
.end_macro

# Finalizar Processo
.macro processTerminate
	li $v0, 21
	syscall
.end_macro



