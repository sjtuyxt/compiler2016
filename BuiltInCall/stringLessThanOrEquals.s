stringLessThanOrEquals:
sw	$ra, 124($sp)
addu	$a0, $a0, 4
addu	$a1, $a1, 4
stringLessThanOrEquals_start:
lb	$a2, 0($a0)
lb	$a3, 0($a1)
addu	$a0, $a0, 1
addu	$a1, $a1, 1
add	$v0, $a2, $a3
beq	$v0, 0, stringLessThanOrEquals_yes
beq	$a2, 0, stringLessThanOrEquals_yes
beq	$a3, 0, stringLessThanOrEquals_no
blt	$a2, $a3, stringLessThanOrEquals_yes
bgt	$a2, $a3, stringLessThanOrEquals_no
j	stringLessThanOrEquals_start
stringLessThanOrEquals_yes:
li	$v0, -1
j stringLessThanOrEquals_end
stringLessThanOrEquals_no:
li	$v0, 0
stringLessThanOrEquals_end:
lw	$ra, 124($sp)
jr	$ra
