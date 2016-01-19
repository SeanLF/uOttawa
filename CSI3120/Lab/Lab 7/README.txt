Lab 7: Perl

Exercise 1. Another micro-parser
You want to validate that HTML-style tags in a file balance, that is, for each opening tag <T> there is a matching closing tag </T>. Allow for nesting, as in the following:
<exampletag> <anothertag> </anothertag> </exampletag>
Crossed tag are not valid, as in this:
<tag1>
<tag2>
</tag1>
</tag2>
Assume one tag per line, no other text, no arguments, white space around tags. There may be other lines, without a tag -- they do not matter for balance.

Exercise 2. Global translation
You will have to do a global find-and-replace on a file, using a set of word-to-word translations given in a separate file, each translation in the form source=target. Assume you are working on Perl word boundaries. Beware of circular translations.
Example file test.txt:
This is a good test but a sentence with bad misteaks. We wanted a bad test.
Example dictionary testDic.dic:
sentence=paragraph good=bad
bad=good misteaks=words want=have is=wasn't
Example:
globalRename.pl testDic.dic test.txt
This gives
This wasn't a bad test but a paragraph with good words. We have a good test.
Use the following construct to read files:
open HANDLE, $fileName; while (<HANDLE>) {...} close HANDLE;

Exercise 3. Line index
Build an index of a text file (provided in <STDIN>). For each word on a list passed as a command-line argument, the indexer program will locate each occurrence of this word in <STDIN>, and record the line number where it occured. The resulting index will be printed in the order of the arguments passed. Assume Perl word boundaries.
Example:
indexer.pl good becomes bad < test.txt
This gives
good: 1
becomes: Not found bad: 1 2

Exercise 4. Occurence count
A similar exercise: for each word on a list passed as a command-line argument, if you find it in standard input, annotate it with the running count of its occurences.
Example:
addCounts.pl a bad < test.txt
This gives
This is a (1) good test but a (2) sentence with bad (1) misteaks. We wanted a (3) bad (2) test.
Y ou may have to use the s///e; construct; look for it using man perlop.
