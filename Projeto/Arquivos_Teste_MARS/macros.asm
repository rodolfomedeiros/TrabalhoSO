########## SERVI�OS DE SO ################
# Done - fim de programa sem erros
.macro done
	li $v0,10	#servi�o de fim de programa
	syscall
.end_macro

.macro fork(%ptr)
	la $a0, %ptr
	li $v0, 19
	syscall
.end_macro

# Imprime inteiro
.macro print_int(%reg)
	add $a0, %reg, $zero  #copiando o valor de reg. para $a0
      	li $v0, 1	      #servi�o impress�o de inteiro
      	syscall
.end_macro
##########################################

# Carrega vari�vel e retorna no registrador do parametro
.macro load_var (%var, %toreg)
	la  $t0, %var        	# carrega o endereco da var para $t0
	lw  %toreg, 0($t0)	# carrega o valor da var para o reg
.end_macro

# Armazenando vari�vel - parametros: nome da var e registrador com valor
.macro store_var (%var, %reg)
	la $t0, %var    # carrega o endereco da var. em $t0
      	sw %reg, 0($t0)	# armazena na mem. o valor do reg.
.end_macro



