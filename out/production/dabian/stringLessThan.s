stringLessThan:
sw	$ra, 124($sp)
addu	$a0, $a0, 4
addu	$a1, $a1, 4
stringLessThan_start:
lb	$a2, 0($a0)
lb	$a3, 0($a1)
addu	$a0, $a0, 1
addu	$a1, $a1, 1
add	$v0, $a2, $a3
beq	$v0, 0, stringLessThan_no
beq	$a2, 0, stringLessThan_yes
beq	$a3, 0, stringLessThan_no
blt	$a2, $a3, stringLessThan_yes
bgt	$a2, $a3, stringLessThan_no
j	stringLessThan_start
stringLessThan_yes:
li	$v0, -1
j stringLessThan_end
stringLessThan_no:
li	$v0, 0
stringLessThan_end:
lw	$ra, 124($sp)
jr	$ra
