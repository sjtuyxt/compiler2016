toString:
sw	$ra, 124($sp)
move	$a1, $a0
li	$a2, 0
toString_label1:
div	$a1, $a1, 10
addu	$a2, $a2, 1
bnez	$a1, toString_label1
move	$a1, $a0
move	$a0, $a2
addu	$a0, $a0, 9
divu	$a0, $a0, 4
mulou	$a0, $a0, 4
li	$v0, 9
syscall
bltz	$a1, toString_label2
sw	$a2, 0($v0)
addu	$a0, $v0, 4
addu	$a0, $a0, $a2
j	toString_label3
toString_label2:
abs	$a1, $a1
addu	$a2, $a2, 1
sw	$a2, 0($v0)
addu	$a0, $v0, 4
li	$a3, 45
sb	$a3, 0($a0)
addu	$a0, $a0, $a2
toString_label3:
li	$a2, 0
sb	$a2, 0($a0)
toString_label4:
subu	$a0, $a0, 1
rem	$a3, $a1, 10
addu $a3, $a3, 48
sb	$a3, 0($a0)
div	$a1, $a1, 10
bnez	$a1, toString_label4
lw	$ra, 124($sp)
jr	$ra
