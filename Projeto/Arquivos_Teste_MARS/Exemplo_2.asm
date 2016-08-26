.include "macros.asm"
# Calcula f = (g+h)-(i+j)
      .data
g: .word  5	# valor 5 armazenado em g
h: .word  2	# valor 2 armanazenado em h
i: .word  1	# valor 1 armanazenado em i
j: .word  3	# valor 3 armanazenado em j
f: .word  0	# valor 0 armanazenado em f

      .text
      # carrengando os valores da memoria
      load_var(g, $s1)
      load_var(h, $s2)
      load_var(i, $s3)
      load_var(j, $s4)
      
      #Realizando o calculo
      add $t0, $s1, $s2	#soma g e h
      add $t1, $s3, $s4	#soma i e j
      sub $s0, $t0, $t1	#(g+h)-(i+j)
      
      #Armazena o resultado na memoria
      store_var(f, $s0)
      
      #imprime resultado
      print_int($s0)
      
      # Terminando o programa
      done
      
