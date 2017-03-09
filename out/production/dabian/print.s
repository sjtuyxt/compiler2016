print:
sw	$ra, 124($sp)

li	$v0, 4
addu	$a0, $a0, 4
syscall

lw	$ra, 124($sp)
jr	$ra
