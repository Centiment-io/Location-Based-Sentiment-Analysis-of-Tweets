Goal of this Project:
Perform location based sentiment analysis of live twitter feed on a given theme. In this project, we have chosen elections theme and heat map of US showing percentage of positive tweets from each state for Donald Trump is generated. This model is completely independent of the theme we have chosen.  For different theme, say for performing of sentiment analysis of new Google’s Vision API, all we need to do plug in the keywords associated in our model!

Steps :
1) Using twitter4j API, 20,000 live tweets which mentioned certain keywords are collected.

2) Data is cleaned. i.e urls, hashtags etc. were removed.

3) Cleaned tweet is fed to Stanford -Core NLP which gives a sentiment score on a scale of 1 to 4.

4) The origin location data of tweets is not homogeneous. For some tweets, sate name is give and for some, only state code is given, For some tweets only city is given.

5) Data is cleaned to make all the locations data to be homogeneous. I,e covert given location data to it’s state in US.

6) All the data is stored in database

7) Based on above available data, we computed what percent of tweets from each state are positive for each candidate and generated heat map of US using d3js. The same can be seen in index.html


