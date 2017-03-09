stringEquals:
sw	$ra, 124($sp)
lw	$a2, 0($a0)
lw	$a3, 0($a1)
bne	$a2, $a3, stringEquals_neq
addu	$a0, $a0, 4
addu	$a1, $a1, 4
stringEquals_start:
lb	$a2, 0($a0)
lb	$a3, 0($a1)
addu	$a0, $a0, 1
addu	$a1, $a1, 1
bne	$a2, $a3, stringEquals_neq
beq	$a2, 0, stringEquals_eq
j	stringEquals_start
stringEquals_eq:
li	$v0, -1
j stringEquals_end
stringEquals_neq:
li	$v0, 0
stringEquals_end:
lw	$ra, 124($sp)
jr	$ra
