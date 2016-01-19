% Database of facts

/*hasSymptoms(nathalie, gradual, [headache, fever, sore_throat] ).
hasSymptoms(nathalie, sudden, [fever, muscle_aches, runny_nose] ).
hasSymptoms(nathalie, gradual, [headache, fever, nasal_drainage, sore_throat] ).
hasSymptoms(nathalie, sudden, [fever, muscle_aches, runny_nose] ).
*/hasSymptoms(nathalie, sudden, [fever, muscle_aches, headache, runny_nose, sore_throat] ).
/**/

% How to diagnose patients
% disease involves fever

hasDisease(Person, Disease) :-
	hasSymptoms(Person, Speed, Symptoms),
	disease(Disease, Speed, Symptoms).
	
disease(strep_throat, _, Symptoms) :-
	member(fever, Symptoms),
	member(sore_throat, Symptoms),
	member(headache, Symptoms),
	not(member(nasal_drainage, Symptoms)).

disease(flu, sudden, Symptoms) :-
	member(fever, Symptoms),
	combination(Symptoms),
	not(disease(strep_throat, sudden, Symptoms)).

disease(acute_bronchitis, Speed, Symptoms) :-
	member(fever, Symptoms),
	member(persistent_cough, Symptoms),
	member(mucus, Symptoms),
	member(wheezing, Symptoms),
	member(shortness_of_breath, Symptoms),
	not(disease(strep_throat, Speed, Symptoms)),
	not(disease(flu, Speed, Symptoms)).
	
disease(gastroenteritis, Speed, Symptoms) :-
	member(fever, Symptoms),
	member(watery_diarrhea, Symptoms),
	gasO1(Symptoms),
	gasO2(Symptoms),
	not(disease(strep_throat, Speed, Symptoms)),
	not(disease(flu, Speed, Symptoms)),
	not(disease(acute_bronchitis, Speed, Symptoms)).
	
% disease does not involve fever

disease(allergies, _, Symptoms):-
	member(itchy_eyes, Symptoms),
	member(sneezing, Symptoms),
	allergies(Symptoms),
	not(member(fever, Symptoms)).

disease(cold, _, Symptoms):-
	member(sneezing, Symptoms),
	member(sore_throat,Symptoms),
	member(headache, Symptoms),
	member(congestion, Symptoms),
	member(runny_nose, Symptoms),
	not(disease(allergies, _, Symptoms)).
	
disease(sinusitis, _, Symptoms):-
	sin(Symptoms),
	member(headache, Symptoms),
	member(dry_cough, Symptoms),
	not(disease(allergies, _, Symptoms)),
	not(disease(cold, _, Symptoms)).
	
	
	
/*
	 HELPERS
*/

% flu help
combination(Symptoms) :- 
	fluSymptoms(X), member(X, Symptoms),
	fluSymptoms(Y), not(X = Y), member(Y, Symptoms), !.

fluSymptoms(muscle_aches).
fluSymptoms(chills).
fluSymptoms(sore_throat).
fluSymptoms(runny_nose).
fluSymptoms(cough).

% gastro... help
gasO1(Symptoms):-
	member(headache, Symptoms),!.
gasO1(Symptoms):-
	member(muscle_aches, Symptoms),!.
	
gasO2(Symptoms):-
	member(nausea, Symptoms),!.
gasO2(Symptoms):-
	member(vomiting, Symptoms),!.
	
% allergies help	
allergies(Symptoms):-
	member(runny_nose, Symptoms),!.
allergies(Symptoms):-
	member(itchy_nose, Symptoms),!.
	
% sinusitis help
sin(Symptoms):-
	member(swelling, Symptoms).
sin(Symptoms):-
	member(pain, Symptoms).