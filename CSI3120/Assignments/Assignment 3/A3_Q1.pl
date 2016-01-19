#!/usr/bin/env perl
$five = 5;
$h = h;
%lexicon = (
	"(W|w)inter" => "Hiver",
	"(S|s)ummer" => "Été",
	"(F|f)all" => "Automne",
	"(D|d)ue Date" => "Date de Remise",
	"(D|d)ue Time" => "Heure de Remise",
	"(A|a)ssignment" => "Devoir",
	"(P|p)roblem" => "Problème",
	"(M|m)ark" => "Point",
	"(N|n)ovember" => "Novembre");
	
$month = /January|February|March|April|May|June|July|August|September|Ocotober|November|December/;

	for($hour = 00; $hour < 12; $hour++)
	{
	 $Hr{$hour} .= (($hour + 12) % 24);
	}


while(<>){
	#read lines
		
	chomp($_);
	
	#TRANSLATE TIME
	s/(0?[0-9]|1[0-1]):([0-5]\d)(am)/<french\>$1$h$2<\/french>/g; 
	s/([0-9]|1[0-1]):([0-5]\d)(pm)/<french\>$Hr{$1}h$2<\/french>/g; 
	
	#TRANSLATE DATE
	s/($month) ([0]?[1-9]|[1-2]\d|[3][0-1]), (\d\d\d\d)/<french\>$2 $1 $3<\/french>/g;
	
	#TRANSLATE COURSE CODE
	s/([\w]{3})(\d)1(\d{2})/<french\>$1$2$five$3<\/french>/g;
	
	#TRANSLATE WORDS
	foreach $key (keys %lexicon) {
		s/$key/<french>$lexicon{$key}<\/french>/g;
	}
	
	
	print "$_\n";
}