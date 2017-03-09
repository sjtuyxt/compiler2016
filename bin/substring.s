substring:
sw	$ra, 124($sp)
addu	$a1, $a1, 4
addu	$a1, $a1, $a0
addu	$a2, $a2, 4
addu	$a2, $a2, $a0
li	$v0, 9
subu	$a0, $a2, $a1
addu	$a0, $a0, 9
divu	$a0, $a0, 4
mulou	$a0, $a0, 4
syscall
subu	$a3, $a2, $a1
addu	$a3, $a3, 1
sw	$a3, 0($v0)
addu	$a0, $v0, 4
substring_label1:
bgt	$a1, $a2, substring_label2
lb	$a3, 0($a1)
sb	$a3, 0($a0)
addu	$a1, $a1, 1
addu	$a0, $a0, 1
j substring_label1
substring_label2:
li	$a3, 0
sb	$a3, 0($a0)
lw	$ra, 124($sp)
jr	$ra
