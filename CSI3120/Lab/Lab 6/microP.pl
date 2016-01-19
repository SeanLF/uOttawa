#!/usr/bin/env perl -w

while($k = <>){
	chomp($k);
	$old = pop(@a); # get the last item pushed
	# does the new item match the old one?
	if (	($k eq ")" and $old eq "(") or 	($k eq "}" and $old eq "{") or 	($k eq ">" and $old eq "<") or 	($k eq "]" and $old eq "[") ) 
	{
		print "\n$old _ $k\n"
	}
	else 
	{ 
		push(@a, $old); 
		push(@a, $k); 
	}
}

if(@a)
{
	print"\nIncomplete\n";
	print @a;
} 
else 
{
	print "\nAll matches\n";
}
