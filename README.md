# Comparing-Hash-Table-Collision-Resolutions-in-Java
This was an assignment for my computer science data structures course where I had to compare different collision resolutions for Hash Table data structures. I compared linear probing, quadratic probing or chaining as the methods of collision resolution

# Part	1:	Object	Orientated	design	and	interaction	of	experiment
## The	Item	Class
This	is	the	class	that	is	used	to	store	the	information	
about	an	individual	object	of	the	time	series	of	power	
usage	for	a	suburban	dwelling.	This	stores	
information	such	as	the	date	that	it	was	recorded,	the	
global	active	power	and	the	amount	of	voltage	used.	It	
has	both	accessor	and	mutator	methods	for	receiving	
and	changing	the	specific	information	about	the	3	
indicated	instance	variables.	It	also	has	toString	
method	(which	would	be	used	by	other	classes	as	I	
will	indicate	below)	that	returns	the	date/time,	power	
and	voltage	of	a	specific	object	in	a	neatly	organized	
string.	It	has	a	compareTo	function,	which	will	
compare	the	date/time	strings	of	two	Item	objects.

## The	Hashing	function	used	throughout
All	hash	tables	in	this	class	infrastructure	uses	a	hash	
function	that	hashes	a	string	by	converting	each	
character	of	the	string	to	Unicode	and	multiplying	by	
37,	followed	by	moding	by	the	size	of	the	table	i.e.	it	
uses	the	sequence:	
hashVal	=	(37	*	hashVal)	+	key.charAt(i);

## The	HashTable	class
This	is	the	class	used	to	set	the	foundations	for	a	hash	
table.	It	serves	as	a	parent	class	to	both	the	
HashTableQuadratic	and	HashTableLinear	classes.	
This	class	has	instance	variables	that	will	store	the	
size	of	the	hash	table,	the	amount	of	occupied	spaces	
in	the	table	and	the	load	factor	of	the	table.	The	class	
will	hold	an	array	of	‘Item’	objects,	which	will	serve	as	the	hash	table	itself	(The	
size	of	which	will	be	specified	by	invoking	the	constructor).	There	are	also	
instance	variables	that	stores	the	amount	of	probes	required	for	an	insertion	into	
the	table	and	the	amount	of	probes	required	for	a	particular	insertion	into	the	
table.	The	class	is	defined	as	abstract	for	ease	of	use	in	coding	the	main	class	
(PowerHashApp)	and	so	that	the	methods	for	searching	and	inserting	can	be	
defined	in	the	concrete	derived	classes.	It	hashes	the	date/time	keys	with	a	hash	
function	as	defined	above.	There	is	a	method	that	is	called	whenever	an	insertion	
takes	place	that	updates	the	load	factor	of	the	hash	table.	There	is	also	a	method	
that	will	return	the	total	amount	of	probes	that	were	required	over	all	the	
various	insertions	into	the	table.	There	are	some	useful	methods	that	were	not	
required,	however	I	added	them	for	fun	such	as	the	printTable	method	which	
shows	how	items	were	stored	in	the	hash	table	in	a	neat	manner.	


## The	HashTableLinear	class
This	class	is	a	child	class	of	the	HashTable	class	that	is	uses	linear	probing	as	its	
collision	resolution.	It	has	methods	for	searching	and	inserting	that	overrides	the	
abstract	methods	in	the	HashTable	class.	The	method	for	inserting	into	a	hash	
table	checks	that	the	table	is	not	full	– if	the	table	is	full	the	program	will	exit.	The	
insertion	method	uses	linear	probing	as	a	resolution	if	any	collisions	arise	during	
the	insertion	process.	Once	an	item	is	successfully	inserted	the	amount	of	probes	
required	for	that	particular	insertion	will	be	stored	in the	probesInsert	array	as	
defined	in	the	parent	class	and	the	load	factor	will	be	updated.	The	method	for	
searching	also	uses	linear	probing	to	find	a	specific	power	usage	item	that	the	
user	is	searching	for	– it	uses	a	date/time	string	as	the	key	for	searching	the	hash	
table.	

## Optimizations	made	for	quadratic	probing
Whenever	quadratic	probing	occurs	in	the	class	below,	to	optimize	the	
computing	resources	required	to	pursue	a	quadratic	probe,	I	have	decided	to	use	
a	bit	shift	whenever	the	hash	value	is	updated	which	provides	the	same	result	in	
a	way	more	efficient	manner.	

## The	HashTableQuadratic	class
This	class	is	a	child	class	of	the	HashTable	class	that	is	uses	quadratic	probing	as	
its	collision	resolution.	It	has	methods	for	searching	and	inserting	that	overrides	
the	abstract	methods	in	the	HashTable	class.	The	method	for	inserting	into	a	
hash	table	checks	that	the	table	is	not	full	– if	the	table	is	full	the	program	will	
exit.	It	will	do	a	further	second	check	to	ensure	that	the	amount	of	probes	that
have	taken	place	is	not	greater	than	the	size	of	the	table.	The	insertion	method	
uses	quadratic	probing	as	a	resolution	if	any	collisions	arise	during	the	insertion	
process.	It	uses	the	bit	shift	sequence	as	defined	above.	Once	an	item	is	
successfully	inserted	the	amount	of	probes	required	for	that	particular	insertion	
will	be	stored	in	the	probesInsert	array	as	defined	in	the	parent	class	and	the	
load	factor	will	be	updated.	The	method	for	searching	also	uses	quadratic	
probing	by	bit	shifting	to	find	a	specific	power	usage	item	that	the	user	is	
searching	for	– it	uses	a	date/time	string	as	the	key	for	searching	the	hash	table.	

## The	LinkedList	class
I	decided	to	create	my	own	linked	list	class	after	experimenting	with	Java’s	pre-
made	LinkedList	and	ArrayList	classes.	I	did	this	because	I	felt	as	if	the	
requirements	I	needed	for	the	chaining	collision	resolution	were	not	fulfilled	by	
Java’s	implementation	of	linked	lists.	Essentially,	this	is	a	class	that	stores	a	
linked	list.	It	has	a	private	inner	class	that	stores	a	node	in	a	linked	list	– this	was	
done	for	encapsulation	purposes.	The	inner	Node	class	has	the	typical	
constructors,	accessor	and	mutator	methods for	a	Node	that	deal	with	the	data	
being	stored	in	the	node	and	the	link	to	the	next	node	in	the	linked	list.	In	the	
linked	list	outer	class	there	is	the	addToStart	method,	which	will	add	a	node	to	
the	start	of	the	linked	list	by	ensuring	that	insertion	will	not	create	duplicates.	
After	an	insertion	is	completed,	the	probe	count	is	updated.	The	find	method	is	
used	for	searching	the	linked	list	for	a	particular	key	– it	traverses	the	list	and	
returns	the	date/time	value	the	user	is	looking	for.	The	find	method	successfully	
updates	the	amount	of	probes	required	for	searching	and	also	for	inserting	(as	


the insert	method	uses	this	method	to	ensure	that	there	are	no	duplicates).	
There	are	also	some	useful	methods	that	are	used	for	clearing	the	list,	checking	if	
the	list	is	empty,	outputting	the	list	and	getting	the	size	of	the	particular	list.

## The	HashTableChaining	class
This	is	the	class	used	to	set	the	foundations	for	a	hash	table	that	uses	chaining	as	
its	collision	resolution	This	class	has	instance	variables	that	will	store	the	size	of	
the	hash	table,	the	amount	of	occupied	spaces	in	the	table	and	the	load	factor	of	
the	table.	The	class	will	hold	an	array	of	‘LinkedList’	objects,	which	will	serve	as	
the	hash	table	itself	(The	size	of	which	will	be	specified	by	invoking	the	
constructor).	There	are	also	instance	variables	that	store	the	amount	of	probes	
required	for	an	insertion	into	the	table	and	the	amount	of	probes	required	for	a	
particular	insertion	into	the	table.	It	hashes	the	date/time	keys	with	a	hash	
function	as	defined	above.	There	is	a	method	that	is	called	whenever	an	insertion	
takes	place	that	updates	the	load	factor	of	the	hash	table.	There	is	also	a	method	
that	will	return	the	total	amount	of	probes	that	were	required	over	all	the	
various	insertions	into	the	table.	There	are	some	useful	methods	that	were	not	
required,	such	as	the	printTable	method,	which	shows	how	items	were	stored	in	
the	hash	table	in	a	neat	manner.	The	insertion	method	uses	chaining	as	a	
resolution	if	any	collisions	arise	during	the	insertion	process.	It	ensures	that	no	
duplicates	would	arise	if	an	insertion	occurs.	Once	an	item	is	successfully	
inserted	the	amount	of	probes	required	for	that	particular	insertion	(for	
checking	if	there	will	be	a	duplicate	and	for	the	insertion	itself)	will	be	stored	in	
the	probesInsert	array	as	defined	in	the	parent	class	and	the	load	factor	will be	
updated.	The	method	for	searching	 is	used	to	find	a	specific	power	usage	item	
that	the	user	is	searching	for	– it	uses	a	date/time	string	as	the	key	for	searching	
the	hash	table.	The	search	method	will	hash	the	key	being	searched	for	and	
search	the	linked	list	that	is	stored	at	that	hash	value.	

## The	PowerHashApp	class
This	is	the	main	class	that	will	run	procedures	regarding	interacting	with	various	
types	of	hash	table.	The	user	will	choose	which	collision	resolution	they	wish	to	
use,	the	size	of	the	table	(Which	will	be	checked	by	this	program	if	it	is	a	prime	
number	– I	based	this	code	off	of	the	code	specified	at	
https://beginnersbook.com/2014/01/java-program-to-check-prime-number/),	
the	data	file	from	which	input	will	be	received,	and	the	amount	of random	keys	
the	user	wishes	to	search	for.	It	has	instance	variables	to	store	a	hash	table	
(which	uses	linear	or	quadratic	probing)	and	a	hash	table,	which	uses	chaining.	It	
has	instance	variables	for	storing	a	list	of	all	the	date/time	keys	and	an	array	that	
randomizes	those	keys	to	a	specific	size	of	an	array	as	specified	by	the	user.	
There	is	a	method	called	build	that	will	build	a	hash	table	by	using	scanners	to	
read	in	the	‘cleaned_data.csv’	file	and	make	the	hash	table	a	particular	size	as	
specified	by	the	user.	There	are	methods	that	are	used	in	conjunction	to	create	a	
random	subset	of	search	keys	for	the	program	to	search	a	hash	table	with	in	
order	to	record	results.	There	is	a	method	that	will	run	the	sequence	of	searches	
and	record	the	various	results	from	those	searches	using	other	methods	such	as	
finding	the	total,	average	and	maximum	amount	of	search	probes	for	a	particular	
search	sequence.	There	is	also	a	method	that	records	these	results	to	a	file	that	
will	be	specified	by	the	user.	


# Part	2:	Goal	of	Experiment	and	Method	of	Execution

The	Goal
The	goal	of	this	experiment	was	to	use	the	above	OOP	infrastructure	to	
demonstrate	the	performance	differences	when	using	different	collision	
resolutions	for	hash	tables.	This	experiment	shows	the	performance	between	
collision	resolutions	using:	linear	probing,	quadratic	probing	and	chaining.	
Furthermore,	the	goal	of	the	test	will	also	be	to	show	how	the	load	factor	of	these	
particular	types	of	hash	tables	will	effect	the	different	tables	performance

The	execution
In	order	to	do	this	test	effectively	and	efficiently	I	decided	to	use	the	java	class	
infrastructure	that	I	have	described	above.	The	testing	was	done	in	the	
PowerHashApp	class.	I	used	the	methods	I	described	in	part	1	above	to	do	the	
testing	following	this	sequence:

- The	user	chooses	the	table	size
    o Program	ensures	this	is	a	prime	number
- The	user	chooses	his/her	required	collision	resolution	(linear,	quadratic	
    or	chaining)
- The	user	enters	the	input	file	– typically	in	the	case	of	this	practical	it	will	
    be	‘cleaned_data.csv’
- The	user	inputs	the	K	value	(amount	of	keys)	– this	is	the	amount	of	keys	
    the	program	will	search	for	in	the	hash	table:
       o The	program	will	be	the	specified	hash	table
         ! The	probes	required	for	each	insertion	are	recorded
       o The	program	will	make	a	subset	(size	K)	of	the	input	key	values	to	
          search	for	in	the	hash	table
            ! This	uses	a	file	I	created	called	“FileWithSearchKeys.txt”
       o The	search	sequence	will	begin:
         ! The	program	will	search	the	hash	table	for	every	key	in	the	
             subset
         ! The	amount	of	probes	for	each	search	is	recorded	and	
             stored	in	an	array.	The	following	procedures	use	this	array	
             to	find:
                - The	total	amount	of	probes	required	over	all	
                   searches	is	recorded
                - The	average	amount	of	probes	required	over	all	
                   searches	is	recorded
                - The	highest	amount	of	probes	for	a	search	in	this	
                   sequence	is	recorded
       o The	above	information	is	appended	to	a	file,	typically	called	
          ‘InsertionPerformance.txt’
- I	did	the	above	sequence	5	time	for	each	type	of	collision	resolution	and	
    copied	the	outcomes	and	results	of	the	sequence	to	three	different	csv	
    files	as	can	be	found	in	the	‘data’	directory.
- I	used	excel	to	graph	the	information	in	a	manner	the	reader/marker	can	
    understand	efficiently	as	will	be	discussed	in	part	3.


**Part	3:	Results	and	Discussion**
Found in report.pdf
