getString:
sw	$ra, 124($sp)

li	$v0, 9
li	$a0, 8192
syscall
addu	$a0, $v0, 4
li	$a1, 8188
li	$v0, 8
syscall
subu	$v0, $a0, 4
getString_label:
lb	$a1, 0($a0)
addu	$a0, $a0, 1
bnez	$a1, getString_label
subu	$a0, $a0, $v0
subu	$a0, $a0, 5
sw	$a0, 0($v0)

lw	$ra, 124($sp)
jr	$ra