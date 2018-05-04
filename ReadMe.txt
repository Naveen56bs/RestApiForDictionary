

1) REST API end point for adding english words into the dictionary: 
     -->POST request needs to be fired 
	 -->Data to be sent in Application/JSON format
	 -->Please refer to  snapshot PositiveCases/CreateRequestForAddingWord.png
	
	http://localhost:8080/DictionaryImplementation/dictionary/create
	Ex: 
	
	{
		"word":"Naveen"
	}

TODO: I had to rectify the logic to avoid adding duplicate words to the dictionary,
		I needed more time, I couldn't do that part. If required in future, I can do that as well.


2)REST API end point for deleting english words into the dictionary 
	-->DELETE request needs to be fired 
	-->Data to be sent in Application/JSON format
	-->Please refer to  snapshot PositiveCases/DeleteRequestForRemovingWord.png
	
	http://localhost:8080/DictionaryImplementation/dictionary/delete
	
	Ex:
	{
		"word" :"abcdef"
	}
	

3) REST API end point which takes a word as input, return a JSON array of `similar` words and order them based on `similarity`
	-->GET request needs to be fired 
	-->Data would be the part of the request fired
	-->Please refer to PositiveCases/GetRequestForSimilarWords.png
	
	http://localhost:8072/DictionaryImplementation/dictionary/abcdef
	

4) Please refer to the image CanonicalPath.png, I have highlighted in the console where you can refer to dict.txt, 
	words are added/ deleted/ retrieved from this file.