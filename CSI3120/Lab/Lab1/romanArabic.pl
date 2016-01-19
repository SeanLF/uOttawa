:- set_prolog_flag(toplevel_print_options,
      [quoted(true), portray(true), attributes(portray), max_depth(100)]).

/* * * * * * (c) Stan Szpakowicz 2009 * * * * * */

% - - - - - - - - - - - -
% a test harness for the Roman <-> Arabic translation
% - - - - - - - - - - - -

testHarness(File) :-
  see(File),
  testAll,
  seen.

testAll :-
  \+ at_end_of_stream,
  !,
  readLine(X, Y),
  translateRomanArabic(X, Y, Result),
  printResult(Result),
  testAll.
testAll.

% - - - - - - - - - - - -
% Roman <-> Arabic translation selector
% - - - - - - - - - - - -

% an integer on input
translateRomanArabic(_X, Y, Roman) :-
  integer(Y),
  !,
  write(Y),
  write(' --> '),
  translateArabic(Y, Roman).
% not an integer on input
translateRomanArabic(X, Y, Arabic) :-
  write(Y),
  write(' --> '),
  translateRoman(X, Y, Arabic).

% - - - - - - - - - - - -
% Roman -> Arabic
% - - - - - - - - - - - -

translateRoman(X, _Y, Arabic) :-
  splitRoman(X, r(Thousands, Hundreds, Tens, Singles)),
  Arabic is 1000 * Thousands + 100 * Hundreds + 10 * Tens + Singles,
  !.

% split a list of symbols, assuming it is a correct Roman numeral
% (fail if it is not), translate four segments into numbers
splitRoman(Roman, r(Thousands, Hundreds, Tens, Singles)) :-
  thousands(Roman, Thousands, Tail100),
  hundreds(Tail100, Hundreds, Tail10),
  tens(Tail10, Tens, Tail1),
  singles(Tail1, Singles, []).

% - - - - - - - -
% thousands
% - - - - - - - -

thousands(Roman, Thousands, Tail) :-
	commonRoman(Roman, Thousands, 'M', 'M', 'C', Tail).

% - - - - - - - -
% hundreds, ten, singles
% - - - - - - - -

hundreds(Roman, Hundreds, Tail) :-
  commonRoman(Roman, Hundreds, 'C', 'D', 'M', Tail).
  
tens(Roman, Tens, Tail) :-
  commonRoman(Roman, Tens, 'X', 'L', 'C', Tail).
  
singles(Roman, Singles, Tail) :-
  commonRoman(Roman, Singles, 'I', 'V', 'X', Tail).

commonRoman([CXI, CXI, CXI | Tail], 3, CXI, _DCV, _MCX, Tail) :-
  !.
commonRoman(['M', 'M', 'M', 'M' | Tail], 4, 'M', _DCV, _MCX, Tail) :- 
  !.
commonRoman([CXI, CXI | Tail], 2, CXI, _DCV, _MCX, Tail) :-
  !.
commonRoman([CXI, DCV | Tail], 4, CXI, DCV, _MCX, Tail) :-
  !.
commonRoman([CXI, MCX | Tail], 9, CXI, _DCV, MCX, Tail) :-
  !.
commonRoman([CXI | Tail], 1, CXI, _DCV, _MCX, Tail) :-
  !.
commonRoman([DCV, CXI, CXI, CXI | Tail], 8, CXI, DCV, _MCX, Tail) :-
  !.
commonRoman([DCV, CXI, CXI | Tail], 7, CXI, DCV, _MCX, Tail) :-
  !.
commonRoman([DCV, CXI | Tail], 6, CXI, DCV, _MCX, Tail) :-
  !.
commonRoman([DCV | Tail], 5, _CXI, DCV, _MCX, Tail) :-
  !.
commonRoman(Tail, 0, _CXI, _DCV, _MCX, Tail).

% - - - - - - - - - - - -
% Arabic -> Roman 
% - - - - - - - - - - - -

% the number is correct
translateArabic(Arabic, Roman) :-
  1 =< Arabic,
  Arabic =< 4999,
  !,
  translateIntoRoman(Arabic, Roman).

translateIntoRoman(Arabic, Roman) :-
  Thousands is Arabic // 1000,
  Hundreds is (Arabic mod 1000) // 100,
  Tens is ((Arabic mod 1000) mod 100) // 10,
  Singles is ((Arabic mod 1000) mod 100) mod 10,
  thousands(Roman, Thousands, Segment100),
  hundreds(Segment100, Hundreds, Segment10),
  tens(Segment10, Tens, Segment1),
  singles(Segment1, Singles, []).

% - - - - - - - - - - - -
% utilities
% - - - - - - - - - - - -

% read a line, return single-character atoms and one big atom
readLine(SingleChars, Together) :-
  get0(Ch),
  readLine(Ch, SingleChars, Codes),
  name(Together, Codes).
  
readLine(10, [], []) :-
  !.
readLine(Ch, [SingleChar | SingleChars], [Ch | Codes]) :-
  name(SingleChar0, [Ch]),
  % this built-in predicate up-cases all letters; not to worry
  upcase_atom(SingleChar0, SingleChar),
  get0(NextCh ),
  readLine(NextCh, SingleChars, Codes).

% print a number or a Roman numeral

printResult(outOfRange(Arabic)) :-
  !,
  writeln(outOfRange(Arabic)).
  
printResult(Result) :-
  number(Result),
  !,
  writeln(Result).
  
printResult(notRoman(Roman)) :-
  !,
  writeln(notRoman(Roman)).
  
printResult(Result) :-
  printRoman(Result).

printRoman([Symbol | Symbols]) :-
  !,
  write(Symbol),
  printRoman(Symbols).
  
printRoman([]) :-
  nl.
