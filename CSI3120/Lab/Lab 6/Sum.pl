#!/usr/bin/env perl -w

$var = <STDIN>;
chomp($var);
for (my $i = 1; $i <= $var; $i++) {
	for (my $j = 1; $j <= $i; $j++) {
		$sum2 = $sum2 + $j;
	}
}
print"∑∑=$sum2\n";
