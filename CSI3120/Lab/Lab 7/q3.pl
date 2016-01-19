#!/usr/bin/env perl

$line = 1;
while (<STDIN>) {
	foreach $word (@ARGV) {
		if (/$word/) {
			$index{$word} = $index{$word}."$line";
		}
	}
	$line++;
}

foreach $word (@ARGV) {
	if (defined $index{$word}) {
		print "$word: $index{$word}\n";
	} else {
		print "$word: Not Found\n";
	}
}
