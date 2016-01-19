#!/usr/bin/env perl

if(@ARGV != 2){
	die "usage: q2.pl <dictionary> <file>\n";
}

open DIC, $ARGV[0] or die "Not a file: $ARGV[0]\n";

while (<DIC>){
	chomp;
	if (/(\w+)=(.*)/) {
		$trans{1}=$2;
	}
}

close DIC;

open FILE, ARGV[1] or die "Not a file: ARGV[1]\n";

while (<FILE>) {
	$line = '';
	while(/(\W*)(\w+)(.*)/){
		$word = $2;
		if (defined $trans{$word}) {
			$word = $trans{$word};
		}
		$line = $line.$1.$word;
		$_ = $3;
	}
	print $line.$_."\n";
}
close FILE;

0;