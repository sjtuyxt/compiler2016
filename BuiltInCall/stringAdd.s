stringAdd:
sw	$ra, 124($sp)
lw	$a2, 0($a0)
lw	$a3, 0($a1)
add	$a3, $a2, $a3
move	$a2, $a0
move	$a0, $a3
add	$a0, $a0, 8
div	$a0, $a0, 4
mul	$a0, $a0, 4
li	$v0, 9
syscall
sw	$a3, 0($v0)
addu	$a0, $v0, 4
addu	$a2, $a2, 4
stringAdd_label1:
lb	$a3, 0($a2)
beqz	$a3, stringAdd_label2
sb	$a3, 0($a0)
addu	$a2, $a2, 1
addu	$a0, $a0, 1
j	stringAdd_label1
stringAdd_label2:
addu	$a1, $a1, 4
stringAdd_label3:
lb	$a3, 0($a1)
beqz	$a3, stringAdd_label4
sb	$a3, 0($a0)
addu	$a1, $a1, 1
addu	$a0, $a0, 1
j	stringAdd_label3
stringAdd_label4:
li	$a3, 0
sb	$a3, 0($a0)
lw	$ra, 124($sp)
jr	$ra
