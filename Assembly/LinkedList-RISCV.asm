#Myrsini Gkolemi 3929
#Construction of Linked List with functions
#Also print values of list that are greater than the given integer

.data	
	
	int_console:    .asciz "Write an integer:\n"
	cmp_console:	.asciz "Write an integer to be compared\n"
	   new_line:	.asciz "\n"
	   seperator:   .asciz "Second part of the program:\n"


.text

		#dummy node creation
		jal ra, node_alloc		#allocate data for dummy node
	
		sw x0 , 0(x10)			#store data (in this case zero) with offset 0
		sw x0 ,	4(x10)			#pointer (holds adress of nextnode).Here nxtPtr points to NULL.No node added yet.
	
		add x8 ,x0, x10      		# pointer s0 :=	head of the List	Both hold the address of dummy node.
		add x9 ,x0, x10			# pointer s1 := tail of the List 	Tail points at head
	
first_part:
			
	
		jal ra , read_int		#call read_int() function 		
		
		bge x0, x10, second_part	# return value in a0
		
		addi  sp, sp, -4   		# PUSH: allocate 4 Bytes on the stack (caller saved)
        	sw    x10, 0(sp)   		# save x10 because of temporal register
				
		jal ra, node_alloc
		
		lw   x28,  0(sp)		# retrieve the integer value , put on temporal register
		addi sp , sp, 4			# POP: free 4 Bytes on the stack 
				
		sw  x28, 0(x10)			#store data at address with offset 0
	        sw  x0 , 4(x10)			#nxtPtr points to NULL
	
	        sw  x10, 4(x9)	        	#take the address of given node and store it at previous node nxtPtr
	
	        add x9, x0 , x10		#make tail point at current address of last node	  		
				
      		
        	j first_part                    #Jump back to first_part , read another integer
      	       	
second_part:
 		la   x10, seperator		#separate second part of the program from first with a print
 		addi x17 ,x0 , 4
 		ecall 
 		
	        jal   ra, read_int    		#uses read int to read integer to compare  
				      		#x10 contains integer to be compared

		blt  x10 , x0 , exit		#if integer less than 0   		
		     					     	
				      		#Now search_list is called with parameters:
				     		#1)integer in x10
				      		#2)(x8) head of the list
		add  x11, x0, x8     		#store x8 to x11 
			
		jal  ra , search_list	 
		
		
	        j second_part

						#for functions arguments are held in a0-a7
						#return value of address is held in a0 (+a1)

read_int:

	la    x10, int_console			#Prints message on screen/Asks the user of an integer
	addi  x17 , x0 , 4
	ecall

	addi x17, x0 , 5			#Reads an integer from keyboard
	ecall
	
	jr  ra , 0

node_alloc:

	addi x10, x0, 8				#allocate heap 8 Bytes for the contents of node 
	addi x17, x0 , 9	
	ecall
	
	jr ra , 0
		
search_list:
		
		addi  sp, sp, -4   		# PUSH: allocate 4 Bytes on the stack ( caller saved)
        	sw    ra , 0(sp)   		# save return address
	
		
	while:
		beq  x11, x0 , return_s	        #if pointer reaches null exit
						#first parameter of print node is stored in  x11  (address of node to print contents of)
						#second parameter is the integer in x10 register
				
		addi  sp, sp, -4   		# PUSH: allocate 4 Bytes on the stack ( caller saved)
        	sw    x10, 0(sp)   		# save x10 because temporal register
		        		    	        		    
			               		    
		jal  ra , print_node		#set return address       
		
		lw   x10,  0(sp)		# retrieve the integer value , put on temporal register
		addi sp , sp, 4			# POP: free 4 Bytes on the stack 	    
			    	    	    
		lw  x11, 4(x11)			#it_ptr=it_ptr->nextPtr	    
	
		j while
	    
	return_s:
		
		lw x1, 0(sp)		    	    		    	    			    	    		    	    			    	    		    	    			    	    		    	    		    	    		    	    			    	    		    	    			    	    		    	    			    	    		    	    
		jr ra ,0
				    

print_node:	   
	
		lw   x28, 0(x11)              		#store integer from address node to x28 temporal register
	
		bge  x10, x28, return_p			#if data in INTEGER < NODE->DATA   [rs1 < rs2] 
	
		add  x10 , x28 , x0          		#print integer 
		addi x17, x0, 1
		ecall
	
	
		la   x10, new_line         		#print new line
		addi x17, x0, 4
		ecall
	
	return_p:
	
		jr ra, 0	   		#return without printing         			            			            			            


exit:   
	addi x17, x0 , 10			#exit from program
 	ecall
 	

