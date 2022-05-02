#!/usr/bin/env python
# coding: utf-8

# In[89]:


# Import Pandas
import pandas as pd
import mysql.connector as connection


try:
    mydb = connection.MySQLConnection(
        host = "recomendation-project-database.cajn3aappwsy.us-east-1.rds.amazonaws.com",
        database = 'recomendation_project_database',
        user = "admin",
        passwd = "abc123**"
    )

    query = "SELECT * FROM MOVIES_TABLE;"
    result_dataFrame = pd.read_sql(query,mydb)
    mydb.close() #close the connection
except Exception as e:
    mydb.close()


# Load Movies Metadata
#metadata = pd.read_csv('movie_metadata.csv', low_memory=False)
metadata = result_dataFrame
# Print the first three rows
#metadata.head(3)
metadata.head(3)


# In[90]:


# Calculate mean of vote average column
metadata['imdb_score'] = pd.to_numeric(metadata['imdb_score'], downcast="float")
C = metadata['imdb_score'].mean()
#print(C)


# In[91]:


# Calculate the minimum number of votes required to be in the chart, m
metadata['num_critic_for_reviews'] = pd.to_numeric(metadata['num_critic_for_reviews'])
m = metadata['num_critic_for_reviews'].quantile(0.90)
#print(m)


# In[92]:


# Filter out all qualified movies into a new DataFrame
q_movies = metadata.copy().loc[metadata['num_critic_for_reviews'] >= m]
q_movies.shape


# In[93]:


# Function that computes the weighted rating of each movie
def weighted_rating(x, m=m, C=C):
    v = x['num_critic_for_reviews']
    R = x['imdb_score']
    # Calculation based on the IMDB formula
    return (v/(v+m) * R) + (m/(m+v) * C)


# In[94]:


# Define a new feature 'score' and calculate its value with `weighted_rating()`
q_movies['score'] = q_movies.apply(weighted_rating, axis=1)


# In[95]:


#Sort movies based on score calculated above
q_movies = q_movies.sort_values('score', ascending=False)

#Print the top 15 movies
#print(q_movies[['movie_title', 'imdb_score', 'num_user_for_reviews', 'score']].head(10))
print(q_movies[['movie_title', 'imdb_score', 'num_critic_for_reviews', 'score']].head(10).to_json(orient='records'))