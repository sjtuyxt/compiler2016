parseInt:
sw	$ra, 124($sp)
li	$a1, 0
addu	$a2, $a0, 4
lb	$a3, 0($a2)
bge	$a3, 48, parseInt_label1
addu	$a2, $a2, 1
lb	$a3, 0($a2)
li	$a1, 1
parseInt_label1:
sub	$a0, $a3, 48
parseInt_label2:
addu	$a2, $a2, 1
lb	$a3, 0($a2)
blt	$a3, 48, parseInt_label3
bgt	$a3, 57, parseInt_label3
sub	$a3, $a3, 48
mul	$a0, $a0, 10
add	$a0, $a0, $a3
j parseInt_label2
parseInt_label3:
move	$v0, $a0
beq	$a1, 0, parseInt_label4
neg	$v0, $v0
parseInt_label4:
lw	$ra, 124($sp)
jr	$ra
