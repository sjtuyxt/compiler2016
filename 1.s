.data
_end: .asciiz "\n"
	.align 2
_buffer: .space 256
	.align 2

var0:	.word	0
var1:	.word	0
str0:	.word	1
.asciiz	" "
.align	2
str1:	.word	1
.asciiz	"\n"
.align	2

.text
getInt:
	li $v0, 5
	syscall
	jr $ra

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

new:
sw	$ra, 124($sp)

li	$v0, 9
syscall

lw	$ra, 124($sp)
jr	$ra

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

print:
sw	$ra, 124($sp)

li	$v0, 4
addu	$a0, $a0, 4
syscall

lw	$ra, 124($sp)
jr	$ra

println:
	sw	$ra, 124($sp)
    
    li	$v0, 4
    addu	$a0, $a0, 4
    syscall
    
	la $a0, _end
	syscall
	
    lw	$ra, 124($sp)
    jr	$ra

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

length:
	lw $v0, 0($a0)
	jr $ra

size:
	lw $v0, 0($a0)
	jr $ra

ord:
	add $v1, $a0, $a1
	lb $v0, 4($v1)
	jr $ra


main:
sw	$ra	40($sp)
sub	$sp	120
jal	funcmain
add	$sp	120
lw	$ra	40($sp)
jr	$ra

exchange:
sw	$ra,	48($sp)
lw	$s6,	0($sp)
mul	$s7,	$s6,	4
lw	$s5,	var1
add	$s7,	$s7,	$s5
lw	$s4,	4($s7)
move	$s3,	$s4
lw	$s1,	4($sp)
mul	$s2,	$s1,	4
add	$s2,	$s2,	$s5
lw	$s0,	4($s2)
mul	$t9,	$s6,	4
add	$t9,	$t9,	$s5
sw	$s0,	4($t9)
mul	$t8,	$s1,	4
add	$t8,	$t8,	$s5
sw	$s3,	4($t8)
lw	$ra,	48($sp)
jr	$ra,
makeHeap:
sw	$ra,	140($sp)
lw	$s6,	var0
sub	$s7,	$s6,	1
div	$s5,	$s7,	2
move	$s4,	$s5
li	$ra,	0
move	$s3,	$ra
li	$ra,	0
move	$s2,	$ra
sw	$s7,	20($sp)
sw	$s5,	16($sp)
sw	$s4,	0($sp)
sw	$s3,	8($sp)
sw	$s2,	4($sp)
label0:
lw	$s7,	0($sp)
li	$ra,	0
blt	$s7,	$ra,	label1
lw	$s6,	0($sp)
mul	$s7,	$s6,	2
lw	$s5,	8($sp)
move	$s5,	$s7
mul	$s4,	$s6,	2
add	$s3,	$s4,	1
lw	$s1,	var0
slt	$s2,	$s3,	$s1
neg	$s2	$s2
li	$ra,	0
sw	$s7,	40($sp)
sw	$s5,	8($sp)
sw	$s4,	56($sp)
sw	$s3,	52($sp)
sw	$s2,	48($sp)
bne	$s2,	$ra,	label4
lw	$s7,	48($sp)
move	$s6,	$s7
sw	$s6,	44($sp)
j	label5
label4:
lw	$s6,	0($sp)
mul	$s7,	$s6,	2
mul	$s5,	$s7,	4
lw	$s4,	var1
add	$s5,	$s5,	$s4
lw	$s3,	4($s5)
mul	$s2,	$s6,	2
add	$s1,	$s2,	1
mul	$s0,	$s1,	4
add	$s0,	$s0,	$s4
lw	$t9,	4($s0)
slt	$t8,	$t9,	$s3
neg	$t8	$t8
lw	$t7,	44($sp)
lw	$t6,	48($sp)
and	$t7,	$t6,	$t8
sw	$s7,	68($sp)
sw	$s5,	72($sp)
sw	$s3,	64($sp)
sw	$s2,	84($sp)
sw	$s1,	80($sp)
sw	$s0,	88($sp)
sw	$t9,	76($sp)
sw	$t8,	60($sp)
sw	$t7,	44($sp)
label5:
lw	$s7,	44($sp)
li	$ra,	0
beq	$s7,	$ra,	label2
lw	$s6,	0($sp)
mul	$s7,	$s6,	2
add	$s5,	$s7,	1
lw	$s4,	8($sp)
move	$s4,	$s5
sw	$s7,	100($sp)
sw	$s5,	96($sp)
sw	$s4,	8($sp)
j	label3
label2:
label3:
lw	$s6,	8($sp)
mul	$s7,	$s6,	4
lw	$s5,	var1
add	$s7,	$s7,	$s5
lw	$s4,	4($s7)
lw	$s2,	0($sp)
mul	$s3,	$s2,	4
add	$s3,	$s3,	$s5
lw	$s1,	4($s3)
sw	$s7,	112($sp)
sw	$s4,	108($sp)
sw	$s3,	120($sp)
sw	$s1,	116($sp)
ble	$s1,	$s4,	label6
lw	$s7,	0($sp)
lw	$s6,	8($sp)
sub	$sp,	52
sw	$s7,	0($sp)
sw	$s6,	4($sp)
jal	exchange
add	$sp,	52
move	$s7,	$v0
sw	$s7,	124($sp)
j	label7
label6:
label7:
lw	$s6,	0($sp)
sub	$s7,	$s6,	1
move	$s6,	$s7
sw	$s7,	132($sp)
sw	$s6,	0($sp)
j	label0
label1:
li	$ra,	0
move	$v0,	$ra
lw	$ra,	140($sp)
jr	$ra,
lw	$ra,	140($sp)
jr	$ra,
adjustHeap:
sw	$ra,	168($sp)
li	$ra,	0
move	$s7,	$ra
li	$ra,	0
move	$s6,	$ra
li	$ra,	0
move	$s5,	$ra
sw	$s7,	12($sp)
sw	$s6,	8($sp)
sw	$s5,	4($sp)
label8:
lw	$s6,	4($sp)
mul	$s7,	$s6,	2
lw	$s5,	0($sp)
sw	$s7,	32($sp)
bge	$s7,	$s5,	label9
lw	$s6,	4($sp)
mul	$s7,	$s6,	2
lw	$s5,	8($sp)
move	$s5,	$s7
mul	$s4,	$s6,	2
add	$s3,	$s4,	1
lw	$s1,	0($sp)
slt	$s2,	$s3,	$s1
neg	$s2	$s2
li	$ra,	0
sw	$s7,	40($sp)
sw	$s5,	8($sp)
sw	$s4,	56($sp)
sw	$s3,	52($sp)
sw	$s2,	48($sp)
bne	$s2,	$ra,	label12
lw	$s7,	48($sp)
move	$s6,	$s7
sw	$s6,	44($sp)
j	label13
label12:
lw	$s6,	4($sp)
mul	$s7,	$s6,	2
mul	$s5,	$s7,	4
lw	$s4,	var1
add	$s5,	$s5,	$s4
lw	$s3,	4($s5)
mul	$s2,	$s6,	2
add	$s1,	$s2,	1
mul	$s0,	$s1,	4
add	$s0,	$s0,	$s4
lw	$t9,	4($s0)
slt	$t8,	$t9,	$s3
neg	$t8	$t8
lw	$t7,	44($sp)
lw	$t6,	48($sp)
and	$t7,	$t6,	$t8
sw	$s7,	68($sp)
sw	$s5,	72($sp)
sw	$s3,	64($sp)
sw	$s2,	84($sp)
sw	$s1,	80($sp)
sw	$s0,	88($sp)
sw	$t9,	76($sp)
sw	$t8,	60($sp)
sw	$t7,	44($sp)
label13:
lw	$s7,	44($sp)
li	$ra,	0
beq	$s7,	$ra,	label10
lw	$s6,	4($sp)
mul	$s7,	$s6,	2
add	$s5,	$s7,	1
lw	$s4,	8($sp)
move	$s4,	$s5
sw	$s7,	100($sp)
sw	$s5,	96($sp)
sw	$s4,	8($sp)
j	label11
label10:
label11:
lw	$s6,	8($sp)
mul	$s7,	$s6,	4
lw	$s5,	var1
add	$s7,	$s7,	$s5
lw	$s4,	4($s7)
lw	$s2,	4($sp)
mul	$s3,	$s2,	4
add	$s3,	$s3,	$s5
lw	$s1,	4($s3)
sw	$s7,	112($sp)
sw	$s4,	108($sp)
sw	$s3,	120($sp)
sw	$s1,	116($sp)
ble	$s1,	$s4,	label14
lw	$s6,	4($sp)
mul	$s7,	$s6,	4
lw	$s5,	var1
add	$s7,	$s7,	$s5
lw	$s4,	4($s7)
move	$s3,	$s4
lw	$s1,	8($sp)
mul	$s2,	$s1,	4
add	$s2,	$s2,	$s5
lw	$s0,	4($s2)
mul	$t9,	$s6,	4
add	$t9,	$t9,	$s5
sw	$s0,	4($t9)
mul	$t8,	$s1,	4
add	$t8,	$t8,	$s5
sw	$s3,	4($t8)
move	$s6,	$s1
sw	$s7,	132($sp)
sw	$s6,	4($sp)
sw	$s4,	128($sp)
sw	$s3,	124($sp)
sw	$s2,	144($sp)
sw	$s0,	140($sp)
sw	$t9,	148($sp)
sw	$t8,	156($sp)
j	label15
label14:
j	label9
label15:
j	label8
label9:
li	$ra,	0
move	$v0,	$ra
lw	$ra,	168($sp)
jr	$ra,
lw	$ra,	168($sp)
jr	$ra,
heapSort:
sw	$ra,	88($sp)
li	$ra,	0
move	$s7,	$ra
li	$ra,	0
move	$s6,	$ra
sw	$s7,	0($sp)
sw	$s6,	4($sp)
label16:
lw	$s7,	4($sp)
lw	$s6,	var0
bge	$s7,	$s6,	label18
lw	$s6,	var1
lw	$s7,	4($s6)
lw	$s5,	0($sp)
move	$s5,	$s7
lw	$s4,	4($sp)
lw	$s2,	var0
sub	$s3,	$s2,	$s4
sub	$s1,	$s3,	1
mul	$s0,	$s1,	4
add	$s0,	$s0,	$s6
lw	$t9,	4($s0)
sw	$t9,	4($s6)
sub	$t8,	$s2,	$s4
sub	$t7,	$t8,	1
mul	$t6,	$t7,	4
add	$t6,	$t6,	$s6
sw	$s5,	4($t6)
sub	$t5,	$s2,	$s4
sub	$t4,	$t5,	1
sw	$s7,	24($sp)
sw	$s6,	var1
sw	$s5,	0($sp)
sw	$s3,	40($sp)
sw	$s1,	36($sp)
sw	$s0,	44($sp)
sw	$t9,	32($sp)
sw	$t8,	56($sp)
sw	$t7,	52($sp)
sw	$t6,	60($sp)
sw	$t5,	72($sp)
sw	$t4,	68($sp)
sub	$sp,	172
sw	$t4,	0($sp)
jal	adjustHeap
add	$sp,	172
move	$s7,	$v0
sw	$s7,	64($sp)
label17:
lw	$s6,	4($sp)
add	$s7,	$s6,	1
move	$s6,	$s7
sw	$s7,	80($sp)
sw	$s6,	4($sp)
j	label16
label18:
li	$ra,	0
move	$v0,	$ra
lw	$ra,	88($sp)
jr	$ra,
lw	$ra,	88($sp)
jr	$ra,
funcmain:
sw	$ra,	116($sp)
sub	$sp,	128
jal	getString
add	$sp,	128
move	$s7,	$v0
move	$a0,	$s7
sub	$sp,	128
jal	parseInt
add	$sp,	128
move	$s6,	$v0
lw	$s5,	var0
move	$s5,	$s6
mul	$s4,	$s5,	4
add	$s4,	$s4,	4
move	$a0,	$s4
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$s3,	$v0
lw	$s2,	var1
move	$s2,	$s3
li	$ra,	0
move	$s1,	$ra
sw	$s7,	12($sp)
sw	$s6,	8($sp)
sw	$s5,	var0
sw	$s4,	24($sp)
sw	$s3,	20($sp)
sw	$s2,	var1
sw	$s1,	0($sp)
label19:
lw	$s7,	var1
move	$a0,	$s7
sub	$sp,	128
jal	size
add	$sp,	128
move	$s6,	$v0
lw	$s5,	0($sp)
sw	$s6,	36($sp)
bge	$s5,	$s6,	label21
lw	$s6,	0($sp)
mul	$s7,	$s6,	4
lw	$s5,	var1
add	$s7,	$s7,	$s5
sw	$s6,	4($s7)
sw	$s7,	44($sp)
label20:
lw	$s6,	0($sp)
add	$s7,	$s6,	1
move	$s6,	$s7
sw	$s7,	52($sp)
sw	$s6,	0($sp)
j	label19
label21:
sub	$sp,	144
jal	makeHeap
add	$sp,	144
move	$s7,	$v0
sw	$s7,	56($sp)
sub	$sp,	92
jal	heapSort
add	$sp,	92
move	$s7,	$v0
li	$ra,	0
lw	$s6,	0($sp)
move	$s6,	$ra
sw	$s7,	60($sp)
sw	$s6,	0($sp)
label22:
lw	$s7,	var1
move	$a0,	$s7
sub	$sp,	128
jal	size
add	$sp,	128
move	$s6,	$v0
lw	$s5,	0($sp)
sw	$s6,	72($sp)
bge	$s5,	$s6,	label24
lw	$s6,	0($sp)
mul	$s7,	$s6,	4
lw	$s5,	var1
add	$s7,	$s7,	$s5
lw	$s4,	4($s7)
move	$a0,	$s4
sub	$sp,	128
jal	toString
add	$sp,	128
move	$s3,	$v0
move	$a0,	$s3
sub	$sp,	128
jal	print
add	$sp,	128
move	$s2,	$v0
la	$s1,	str0
move	$a0,	$s1
sub	$sp,	128
jal	print
add	$sp,	128
move	$s2,	$v0
sw	$s7,	88($sp)
sw	$s4,	84($sp)
sw	$s3,	80($sp)
sw	$s2,	76($sp)
sw	$s1,	92($sp)
label23:
lw	$s6,	0($sp)
add	$s7,	$s6,	1
move	$s6,	$s7
sw	$s7,	100($sp)
sw	$s6,	0($sp)
j	label22
label24:
la	$s7,	str1
move	$a0,	$s7
sub	$sp,	128
jal	print
add	$sp,	128
move	$s6,	$v0
li	$ra,	0
move	$v0,	$ra
lw	$ra,	116($sp)
jr	$ra,
lw	$ra,	116($sp)
jr	$ra,
