#!/usr/bin/env/perl -w

$pattern = <STDIN>;
chomp($pattern);
while( <> ) {
    chomp;
    if (/$pattern/) {
        print "Match : |$`<$&>$'|\n";
    }
    else {
        print "No match.\n";
    }
}
