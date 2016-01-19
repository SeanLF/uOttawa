#!/usr/bin/env perl

@tagStack = ();

while (<>) {
	if(/<(\/?)(\w+)>/){
		$tag = $2;
		if(length $1){
			die "closing at level 0: $tag\n"
			unless ($tag eq pop(@tagStack));
		} else {
			push @tagStack, $tag;
		}
	}
}
die "unclosed: ".join(",", @tagStack)."\n" if($tagStack > 0);

print "All tags match!\n";