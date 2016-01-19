#!/usr/bin/env perl

while (<STDIN>) {
	foreach $word (@ARGV) {
		s/\b$word\b/$word ."(".$count{$word}.")"/ge;
	}
	print $_;
}
