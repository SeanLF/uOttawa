def fct():
	s = input()
	char = 0
	num = 0
	for c in s:
		if c.isalpha():
			char = char +1
		elif c.isdigit():
			num = num + 1
	print(char," ", num)
    
def f():
    freq = {}
    fid = open('/Users/Sean/Dropbox/2ndYear/CSI2120-Paradigms/Python/input.txt','r')
    for line in fid:
        for word in line.split():
            freq[word] = freq.get(word,0)+1
    fid.close
    words = [key for key in freq.keys()]
    words.sort()
    for w in word:
        print('{0}{1}'.format(w,freq[w]))
        
def quiz():
    fields = [(chr(let),chr(num)) for let in range(ord('A'),ord('I')) for num in range(ord('1'),(ord('8')))]
    return fields
