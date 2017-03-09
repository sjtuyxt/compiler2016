new:
sw	$ra, 124($sp)

li	$v0, 9
syscall

lw	$ra, 124($sp)
jr	$ra
