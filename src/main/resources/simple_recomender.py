#!/usr/bin/env python
# coding: utf-8

# In[2]:


# Import Pandas
import pandas as pd
import sys

# Load Movies Metadata
metadata = pd.read_csv('/Users/eduardopelaez/Downloads/demo/src/main/resources/movie_metadata.csv', low_memory=False)

# Print the first three rows
# metadata.head(3)


# In[3]:


# Calculate mean of vote average column
C = metadata['imdb_score'].mean()
#print(C)


# In[4]:


# Calculate the minimum number of votes required to be in the chart, m
m = metadata['num_user_for_reviews'].quantile(0.90)
#print(m)


# In[5]:


# Filter out all qualified movies into a new DataFrame
q_movies = metadata.copy().loc[metadata['num_user_for_reviews'] >= m]
q_movies.shape


# In[6]:


# Function that computes the weighted rating of each movie
def weighted_rating(x, m=m, C=C):
    v = x['num_user_for_reviews']
    R = x['imdb_score']
    # Calculation based on the IMDB formula
    return (v/(v+m) * R) + (m/(m+v) * C)


# In[7]:


# Define a new feature 'score' and calculate its value with `weighted_rating()`
q_movies['score'] = q_movies.apply(weighted_rating, axis=1)


# In[8]:


#Sort movies based on score calculated above
q_movies = q_movies.sort_values('score', ascending=False)

#Print the top 15 movies
print(q_movies[['movie_title', 'imdb_score', 'num_user_for_reviews', 'score']].head(10).to_json(orient='records'))


#def coldStartResponse():
    #print()q_movies[['movie_title', 'imdb_score', 'num_user_for_reviews', 'score']].head(10))
