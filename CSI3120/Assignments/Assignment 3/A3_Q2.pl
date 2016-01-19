#!/usr/bin/perl -w
while(<>){
	chomp($_);
	foreach my $splitword (split /\s+/) {
		Translate($splitword);
	}
	foreach my $key (keys %words) {
		s/\b$key\b/$words{$key}/g;
	}
	print $_;
}



use LWP::UserAgent;

sub Translate {
  $to_translate = $_[0];
  $to_translate =~ s/^\s+|\s+$//g; #trim
  $to_translate =~ s/[\$#@~!&*()\[\];.,:?^ `\\\/]+//g; #remove punctuation
  
  #Get the http response from Babylon
  $userAgent = LWP::UserAgent->new(ssl_opts => { verify_hostname => 0 });
  $response = $userAgent->get("http://translation.babylon.com/english/to-french/$to_translate/");

  # Throw error if not a success
  unless ($response->is_success) {
    print "Could not translate $_[0] due to " . $response->status_line;
    print "\n"
  }

  #Get the content in UTF8 if possible
  my $content = $response->decoded_content();
  if (utf8::is_utf8($content)) {
    binmode STDOUT,':utf8';
  } else {
    binmode STDOUT,':raw';
  }

  #Extract the first occurrence of an html tag that surrounds translations
  $content =~ qr/(?im-sx:<div style="color: #6c8aa9; font-weight: bold;">(\w*)(.*))/im;

  # Our sentence will be the second match (.*)
  $sentence = $2;
  $sentence =~ s/^\s+//; # left trim white space
  @temp_words = split(/,/, $sentence); #split on the comma

  $temp_word = $temp_words[0]; #Our temp word will be the first object in the array from the split
  $temp_word =~ qr/^([\w|\s|']*)/im; # Match for word characters only, this will avoid punctuation or whitespace
  $word = $1; #First match is our word.
  $word =~ s/^\s+|\s+$//g; #trim white space
  if (defined $word and $word ne "") {
  $words{$to_translate} = $word;	  
  }
}