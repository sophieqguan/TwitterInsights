# Twitter User Insight
Generates insights based on tweets of specified Twitter user. 
Implemented IBM Watson's Natural Language Processing API. 

# Functionalities include:
- searching for sentiments/emotion on specific keyword 
	
	e.g. 'Tartaglia is the best': searching for sentiment on keyword 'Tartaglia' will return 'joy' emotion

- getting list of related keywords discussed by user

	e.g. 'thank you for the support' might return keywords such as 'Gratitude'

# Setup
- Twitter API (using [Twitter4J](https://github.com/Twitter4J/Twitter4J))

	register for Twitter Developer in order to get the oauth key and token needed
	
	instructions for creating 'twitter4j.properties' file can be found [here](https://twitter4j.org/en/configuration.html)
- IBM Watson NLP API (using [NLP](https://cloud.ibm.com/apidocs/natural-language-understanding))

	register for IBM Watson and create NLP app in order to get URL and API key needed to populate 'ibmnlp.properties' file (needs to be created)

