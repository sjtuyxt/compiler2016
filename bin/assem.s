.data
_end: .asciiz "\n"
	.align 2
_buffer: .space 256
	.align 2

var0:	.word	0
var1:	.word	0
var2:	.word	0
var3:	.word	0
var4:	.word	0
var5:	.word	0
var6:	.word	0
var7:	.word	0
var8:	.word	0
var9:	.word	0
var10:	.word	0
var11:	.word	0
var12:	.word	0
var13:	.word	0
var14:	.word	0
var15:	.word	0
var16:	.word	0
var17:	.word	0
var18:	.word	0
var19:	.word	0
var20:	.word	0
var21:	.word	0
str0:	.word	13
.asciiz	"no solution!\n"
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
sw	$ra	32($sp)
lw	$v1,	var9
li	$ra,	48004
move	$a0,	$ra
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$v1,	$v0
lw	$a1,	var8
move	$a1,	$v1
lw	$a2,	var11
li	$ra,	48004
move	$a0,	$ra
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$a2,	$v0
lw	$a3,	var10
move	$a3,	$a2
lw	$t0,	var16
li	$ra,	36
move	$a0,	$ra
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$t0,	$v0
lw	$t1,	var15
move	$t1,	$t0
lw	$t2,	var18
li	$ra,	40
move	$a0,	$ra
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$t2,	$v0
lw	$t3,	var17
move	$t3,	$t2
sw	$t3,	var17
sw	$t2,	var18
sw	$t1,	var15
sw	$t0,	var16
sw	$a3,	var10
sw	$a2,	var11
sw	$a1,	var8
sw	$v1,	var9
sub	$sp	304
jal	funcmain
add	$sp	304
lw	$ra	32($sp)
jr	$ra

origin:
sw	$ra,	76($sp)
li	$ra,	0
lw	$s7,	var1
move	$s7,	$ra
li	$ra,	0
lw	$s6,	var12
move	$s6,	$ra
lw	$s5,	0($sp)
li	$ra,	4
mul	$s5,	$s5,	$ra
li	$ra,	4
add	$s5,	$s5,	$ra
lw	$s4,	12($sp)
move	$a0,	$s5
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$s4,	$v0
lw	$s3,	var19
move	$s3,	$s4
li	$ra,	0
lw	$s2,	var20
move	$s2,	$ra
sw	$s7,	var1
sw	$s6,	var12
sw	$s5,	0($sp)
sw	$s4,	12($sp)
sw	$s3,	var19
sw	$s2,	var20
label0:
lw	$s7,	var20
lw	$s6,	0($sp)
sw	$s7,	var20
sw	$s6,	0($sp)
bge	$s7,	$s6,	label2
lw	$s7,	0($sp)
li	$ra,	4
mul	$s7,	$s7,	$ra
li	$ra,	4
add	$s7,	$s7,	$ra
lw	$s6,	28($sp)
move	$a0,	$s7
jal	new
div	$a0,	$a0,	4
sub	$a0,	$a0,	1
sw	$a0,	0($v0)
move	$s6,	$v0
lw	$s5,	36($sp)
lw	$s4,	var20
li	$ra,	4
mul	$s5,	$s4,	$ra
lw	$s3,	var19
add	$s5,	$s5,	$s3
sw	$s6,	4($s5)
li	$ra,	0
lw	$s2,	var21
move	$s2,	$ra
sw	$s7,	0($sp)
sw	$s6,	28($sp)
sw	$s5,	36($sp)
sw	$s4,	var20
sw	$s3,	var19
sw	$s2,	var21
label3:
lw	$s7,	var21
lw	$s6,	0($sp)
sw	$s7,	var21
sw	$s6,	0($sp)
bge	$s7,	$s6,	label5
lw	$s7,	56($sp)
lw	$s6,	var20
li	$ra,	4
mul	$s7,	$s6,	$ra
lw	$s5,	var19
add	$s7,	$s7,	$s5
lw	$s4,	52($sp)
lw	$s4,	4($s7)
lw	$s3,	60($sp)
lw	$s2,	var21
li	$ra,	4
mul	$s3,	$s2,	$ra
add	$s3,	$s3,	$s4
li	$ra,	0
sw	$ra,	4($s3)
sw	$s7,	56($sp)
sw	$s6,	var20
sw	$s5,	var19
sw	$s4,	52($sp)
sw	$s3,	60($sp)
sw	$s2,	var21
label4:
lw	$s7,	var21
lw	$s6,	64($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
sw	$s7,	var21
sw	$s6,	64($sp)
j	label3
label5:
label1:
lw	$s7,	var20
lw	$s6,	68($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
sw	$s7,	var20
sw	$s6,	68($sp)
j	label0
label2:
lw	$ra,	76($sp)
jr	$ra,
check:
sw	$ra,	20($sp)
lw	$s7,	4($sp)
lw	$s6,	0($sp)
li	$ra,	0
sge	$s7,	$s6,	$ra
neg	$s7	$s7
lw	$s5,	12($sp)
lw	$s4,	var0
slt	$s5,	$s6,	$s4
neg	$s5	$s5
lw	$s3,	8($sp)
and	$s3,	$s5,	$s7
sw	$s7,	4($sp)
sw	$s6,	0($sp)
sw	$s5,	12($sp)
sw	$s4,	var0
sw	$s3,	8($sp)
move	$v0,	$s3
lw	$ra,	20($sp)
jr	$ra,
lw	$ra,	20($sp)
jr	$ra,
addList:
sw	$ra,	108($sp)
lw	$s7,	24($sp)
lw	$s6,	0($sp)
li	$ra,	4
mul	$s7,	$s6,	$ra
lw	$s5,	var19
add	$s7,	$s7,	$s5
lw	$s4,	20($sp)
lw	$s4,	4($s7)
lw	$s3,	28($sp)
lw	$s2,	4($sp)
li	$ra,	4
mul	$s3,	$s2,	$ra
add	$s3,	$s3,	$s4
lw	$s1,	16($sp)
lw	$s1,	4($s3)
lw	$s0,	12($sp)
li	$ra,	-1
seq	$s0,	$s1,	$ra
neg	$s0	$s0
sw	$s7,	24($sp)
sw	$s6,	0($sp)
sw	$s5,	var19
sw	$s4,	20($sp)
sw	$s3,	28($sp)
sw	$s2,	4($sp)
sw	$s1,	16($sp)
sw	$s0,	12($sp)
sub	$sp,	24
sw	$s2,	0($sp)
jal	check
add	$sp,	24
lw	$s7,	36($sp)
move	$s7,	$v0
lw	$s6,	0($sp)
sw	$s7,	36($sp)
sw	$s6,	0($sp)
sub	$sp,	24
sw	$s6,	0($sp)
jal	check
add	$sp,	24
lw	$s7,	44($sp)
move	$s7,	$v0
lw	$s6,	40($sp)
lw	$s5,	36($sp)
and	$s6,	$s7,	$s5
lw	$s4,	32($sp)
lw	$s3,	12($sp)
and	$s4,	$s6,	$s3
li	$ra,	0
sw	$s7,	44($sp)
sw	$s6,	40($sp)
sw	$s5,	36($sp)
sw	$s4,	32($sp)
sw	$s3,	12($sp)
beq	$s4,	$ra,	label6
lw	$s7,	var12
lw	$s6,	48($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
lw	$s5,	56($sp)
li	$ra,	4
mul	$s5,	$s7,	$ra
lw	$s4,	var8
add	$s5,	$s5,	$s4
lw	$s3,	0($sp)
sw	$s3,	4($s5)
lw	$s2,	64($sp)
li	$ra,	4
mul	$s2,	$s7,	$ra
lw	$s1,	var10
add	$s2,	$s2,	$s1
lw	$s0,	4($sp)
sw	$s0,	4($s2)
lw	$t9,	68($sp)
lw	$t8,	var14
li	$ra,	1
add	$t9,	$t8,	$ra
lw	$t7,	80($sp)
li	$ra,	4
mul	$t7,	$s3,	$ra
lw	$t6,	var19
add	$t7,	$t7,	$t6
lw	$t5,	76($sp)
lw	$t5,	4($t7)
lw	$t4,	84($sp)
li	$ra,	4
mul	$t4,	$s0,	$ra
add	$t4,	$t4,	$t5
sw	$t9,	4($t4)
lw	$t3,	88($sp)
lw	$t2,	var5
seq	$t3,	$s0,	$t2
neg	$t3	$t3
lw	$t1,	96($sp)
lw	$t0,	var4
seq	$t1,	$s3,	$t0
neg	$t1	$t1
lw	$a3,	92($sp)
and	$a3,	$t1,	$t3
li	$ra,	0
sw	$s7,	var12
sw	$s6,	48($sp)
sw	$s5,	56($sp)
sw	$s4,	var8
sw	$s3,	0($sp)
sw	$s2,	64($sp)
sw	$s1,	var10
sw	$s0,	4($sp)
sw	$t9,	68($sp)
sw	$t8,	var14
sw	$t7,	80($sp)
sw	$t6,	var19
sw	$t5,	76($sp)
sw	$t4,	84($sp)
sw	$t3,	88($sp)
sw	$t2,	var5
sw	$t1,	96($sp)
sw	$t0,	var4
sw	$a3,	92($sp)
beq	$a3,	$ra,	label8
li	$ra,	1
lw	$s7,	var13
move	$s7,	$ra
sw	$s7,	var13
j	label9
label8:
label9:
j	label7
label6:
label7:
lw	$ra,	108($sp)
jr	$ra,
funcmain:
sw	$ra,	300($sp)
li	$ra,	106
sub	$sp,	80
sw	$ra,	0($sp)
jal	origin
add	$sp,	80
lw	$s7,	0($sp)
move	$s7,	$v0
sub	$sp,	128
jal	getInt
add	$sp,	128
lw	$s6,	4($sp)
move	$s6,	$v0
lw	$s5,	var0
move	$s5,	$s6
lw	$s4,	12($sp)
li	$ra,	1
sub	$s4,	$s5,	$ra
lw	$s3,	var5
move	$s3,	$s4
lw	$s2,	var4
move	$s2,	$s4
li	$ra,	0
lw	$s1,	var20
move	$s1,	$ra
sw	$s7,	0($sp)
sw	$s6,	4($sp)
sw	$s5,	var0
sw	$s4,	12($sp)
sw	$s3,	var5
sw	$s2,	var4
sw	$s1,	var20
label10:
lw	$s7,	var20
lw	$s6,	var0
sw	$s7,	var20
sw	$s6,	var0
bge	$s7,	$s6,	label12
li	$ra,	0
lw	$s7,	var21
move	$s7,	$ra
sw	$s7,	var21
label13:
lw	$s7,	var21
lw	$s6,	var0
sw	$s7,	var21
sw	$s6,	var0
bge	$s7,	$s6,	label15
lw	$s7,	52($sp)
lw	$s6,	var20
li	$ra,	4
mul	$s7,	$s6,	$ra
lw	$s5,	var19
add	$s7,	$s7,	$s5
lw	$s4,	48($sp)
lw	$s4,	4($s7)
lw	$s3,	56($sp)
lw	$s2,	var21
li	$ra,	4
mul	$s3,	$s2,	$ra
add	$s3,	$s3,	$s4
li	$ra,	-1
sw	$ra,	4($s3)
sw	$s7,	52($sp)
sw	$s6,	var20
sw	$s5,	var19
sw	$s4,	48($sp)
sw	$s3,	56($sp)
sw	$s2,	var21
label14:
lw	$s7,	var21
lw	$s6,	60($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
sw	$s7,	var21
sw	$s6,	60($sp)
j	label13
label15:
label11:
lw	$s7,	var20
lw	$s6,	64($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
sw	$s7,	var20
sw	$s6,	64($sp)
j	label10
label12:
li	$ra,	-2
lw	$s7,	var15
sw	$ra,	4($s7)
li	$ra,	-1
lw	$s6,	var17
sw	$ra,	4($s6)
li	$ra,	-2
sw	$ra,	8($s7)
li	$ra,	1
sw	$ra,	8($s6)
li	$ra,	2
sw	$ra,	12($s7)
li	$ra,	-1
sw	$ra,	12($s6)
li	$ra,	2
sw	$ra,	16($s7)
li	$ra,	1
sw	$ra,	16($s6)
li	$ra,	-1
sw	$ra,	20($s7)
li	$ra,	-2
sw	$ra,	20($s6)
li	$ra,	-1
sw	$ra,	24($s7)
li	$ra,	2
sw	$ra,	24($s6)
li	$ra,	1
sw	$ra,	28($s7)
li	$ra,	-2
sw	$ra,	28($s6)
li	$ra,	1
sw	$ra,	32($s7)
li	$ra,	2
sw	$ra,	32($s6)
sw	$s7,	var15
sw	$s6,	var17
label16:
lw	$s7,	var1
lw	$s6,	var12
sw	$s7,	var1
sw	$s6,	var12
bgt	$s7,	$s6,	label17
lw	$s7,	172($sp)
lw	$s6,	var1
li	$ra,	4
mul	$s7,	$s6,	$ra
lw	$s5,	var8
add	$s7,	$s7,	$s5
lw	$s4,	168($sp)
lw	$s4,	4($s7)
lw	$s3,	var6
move	$s3,	$s4
lw	$s2,	184($sp)
li	$ra,	4
mul	$s2,	$s6,	$ra
lw	$s1,	var10
add	$s2,	$s2,	$s1
lw	$s0,	180($sp)
lw	$s0,	4($s2)
lw	$t9,	var7
move	$t9,	$s0
lw	$t8,	200($sp)
li	$ra,	4
mul	$t8,	$s3,	$ra
lw	$t7,	var19
add	$t8,	$t8,	$t7
lw	$t6,	196($sp)
lw	$t6,	4($t8)
lw	$t5,	204($sp)
li	$ra,	4
mul	$t5,	$t9,	$ra
add	$t5,	$t5,	$t6
lw	$t4,	192($sp)
lw	$t4,	4($t5)
lw	$t3,	var14
move	$t3,	$t4
li	$ra,	0
lw	$t2,	var21
move	$t2,	$ra
sw	$s7,	172($sp)
sw	$s6,	var1
sw	$s5,	var8
sw	$s4,	168($sp)
sw	$s3,	var6
sw	$s2,	184($sp)
sw	$s1,	var10
sw	$s0,	180($sp)
sw	$t9,	var7
sw	$t8,	200($sp)
sw	$t7,	var19
sw	$t6,	196($sp)
sw	$t5,	204($sp)
sw	$t4,	192($sp)
sw	$t3,	var14
sw	$t2,	var21
label18:
lw	$s7,	var21
li	$ra,	8
sw	$s7,	var21
bge	$s7,	$ra,	label20
lw	$s7,	228($sp)
lw	$s6,	var21
li	$ra,	4
mul	$s7,	$s6,	$ra
lw	$s5,	var15
add	$s7,	$s7,	$s5
lw	$s4,	224($sp)
lw	$s4,	4($s7)
lw	$s3,	232($sp)
lw	$s2,	var6
add	$s3,	$s2,	$s4
lw	$s1,	240($sp)
li	$ra,	4
mul	$s1,	$s6,	$ra
lw	$s0,	var17
add	$s1,	$s1,	$s0
lw	$t9,	236($sp)
lw	$t9,	4($s1)
lw	$t8,	244($sp)
lw	$t7,	var7
add	$t8,	$t7,	$t9
sw	$s7,	228($sp)
sw	$s6,	var21
sw	$s5,	var15
sw	$s4,	224($sp)
sw	$s3,	232($sp)
sw	$s2,	var6
sw	$s1,	240($sp)
sw	$s0,	var17
sw	$t9,	236($sp)
sw	$t8,	244($sp)
sw	$t7,	var7
sub	$sp,	112
sw	$s3,	0($sp)
sw	$t8,	4($sp)
jal	addList
add	$sp,	112
lw	$s7,	220($sp)
move	$s7,	$v0
sw	$s7,	220($sp)
label19:
lw	$s7,	var21
lw	$s6,	248($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
sw	$s7,	var21
sw	$s6,	248($sp)
j	label18
label20:
lw	$s7,	var13
li	$ra,	1
sw	$s7,	var13
bne	$s7,	$ra,	label21
j	label17
j	label22
label21:
label22:
lw	$s7,	var1
lw	$s6,	256($sp)
move	$s6,	$s7
li	$ra,	1
add	$s7,	$s7,	$ra
move	$s7,	$s7
sw	$s7,	var1
sw	$s6,	256($sp)
j	label16
label17:
lw	$s7,	var13
li	$ra,	1
sw	$s7,	var13
bne	$s7,	$ra,	label23
lw	$s7,	280($sp)
lw	$s6,	var4
li	$ra,	4
mul	$s7,	$s6,	$ra
lw	$s5,	var19
add	$s7,	$s7,	$s5
lw	$s4,	276($sp)
lw	$s4,	4($s7)
lw	$s3,	284($sp)
lw	$s2,	var5
li	$ra,	4
mul	$s3,	$s2,	$ra
add	$s3,	$s3,	$s4
lw	$s1,	272($sp)
lw	$s1,	4($s3)
move	$a0,	$s1
sub	$sp,	128
jal	toString
add	$sp,	128
lw	$s0,	268($sp)
move	$s0,	$v0
move	$a0,	$s0
sub	$sp,	128
jal	println
add	$sp,	128
lw	$t9,	264($sp)
move	$t9,	$v0
sw	$s7,	280($sp)
sw	$s6,	var4
sw	$s5,	var19
sw	$s4,	276($sp)
sw	$s3,	284($sp)
sw	$s2,	var5
sw	$s1,	272($sp)
sw	$s0,	268($sp)
sw	$t9,	264($sp)
j	label24
label23:
lw	$s7,	292($sp)
la	$s7,	str0
move	$a0,	$s7
sub	$sp,	128
jal	print
add	$sp,	128
lw	$s6,	288($sp)
move	$s6,	$v0
sw	$s7,	292($sp)
sw	$s6,	288($sp)
label24:
li	$ra,	0
move	$v0,	$ra
lw	$ra,	300($sp)
jr	$ra,
lw	$ra,	300($sp)
jr	$ra,
