import json
import sys
import numpy as np
import pandas as pd
from Classifier import KNearestNeighbours
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


# Load dataset to a data frame
#data = pd.read_csv(r'/Users/eduardopelaez/Downloads/demo/src/main/resources/movie_metadata.csv')
data = result_dataFrame
# Create a new data frame with relevant columns only
df = data[['genres', 'movie_title', 'imdb_score', 'plot_keywords']].copy()
df['imdb_score'] = df['imdb_score'].astype(np.float64)
# Fetch genres of all movies
genres_all_movies = [df.loc[i]['genres'].split('|') for i in df.index]
# Find the list of genres of all movies in alphabetical order
genres = sorted(list(set([item for sublist in genres_all_movies for item in sublist])))


# Initialize lists for movie data as well as titles
full_data = list()
movie_titles = list()
# Iterate over the data frame
for i in df.index:
    # Append movie title and the index of the movie
    movie_titles.append((df.loc[i]['movie_title'].strip(), i, df.loc[i]['plot_keywords'].strip()))
    # Add list of genres of the movies (1/0) to movie data
    movie_data = [1 if genre in df.loc[i]['genres'].split('|') else 0 for genre in genres]
    # Add IMDb score of the movie to the movie data
    movie_data.append(df.loc[i]['imdb_score'])
    # Add record of movie to main data list
    full_data.append(movie_data)


# Create JSON files for data and movie titles for faster load to the Recommmender
data_dump = r'data.json'
titles_dump = r'titles.json'
with open(data_dump, 'w+', encoding='utf-8') as f:
    json.dump(full_data, f)
with open(titles_dump, 'w+', encoding='utf-8') as f:
    json.dump(movie_titles, f)


movies = [title[0] for title in movie_titles]
#select_movie = 'Dry Spell'
select_movie = sys.argv[1]
genres = full_data[movies.index(select_movie)]

#print(genres)
#print('---------')

def KNN_Movie_Recommender(test_point, k):
    # Create dummy target variable for the KNN Classifier
    target = [0 for item in movie_titles]
    # Instantiate object for the Classifier
    model = KNearestNeighbours(full_data, target, test_point, k=k)
    #print(model)
    # Run the algorithm
    model.fit()
    #print('Pase')
    # Print list of 10 recommendations < Change value of k for a different number >
    table = []
    #print(table)
    for i in model.indices:
        # Returns back movie title and imdb link
        table.append([movie_titles[i][0], movie_titles[i][2],full_data[i][-1]])
    #print(table)
    return table

test_points = genres
#print(test_points)
table = KNN_Movie_Recommender(test_points, 5)

#print("Recomendaciones")
#print(table)
response = pd.DataFrame(table, columns=['movie_title', 'plot_keywords', 'imdb_score']).to_json(orient='records')
print(response)