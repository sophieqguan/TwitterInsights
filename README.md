# Twitter User Insight
Generates insights based on tweets of specified Twitter user. 
Implemented IBM Watson's Natural Language Processing API. 

# Functionalities include:
- searching for sentiments/emotion on specific keyword 
	
	e.g. 'Tartaglia is the best': searching for sentiment on keyword 'Tartaglia' will return 'joy' emotion

- getting list of related concepts discussed by user

	e.g. 'thank you for the support': searching for concept will return terms such as 'Gratitude'


# Setup
- Twitter API (using [Twitter4J](https://github.com/Twitter4J/Twitter4J))

	1. register for Twitter Developer in order to get the oauth key and token needed
	
	2. create 'twitter4j.properties' file in root
	
	3. enter the following and fill in with corresponding oauth key/token
	
	```
	debug=true
	oauth.consumerKey=*********************
	oauth.consumerSecret=******************************************
	oauth.accessToken=**************************************************
	oauth.accessTokenSecret=******************************************
	```
	
	more detailed (official) instructions can be found [here](https://twitter4j.org/en/configuration.html)
- IBM Watson NLP API (using [NLP](https://cloud.ibm.com/apidocs/natural-language-understanding))

	1. register for IBM Watson and create NLP app 
	
	2. create 'ibmnlp.properties' file in root
	
	3. enter the following and fill in with corresponding url and token
	
	```
	URL=********************************************
	API=********************************************
	VERSION=2021-03-25
	```

# Input + Output

- Twitter users to be analyzed is listed in 'users.txt' under ./data


- Keywords to search for is listed in 'keywords.txt' under ./data


- results are generated as .csv file under ./data/output
