import requests
import json
import os

APP_NAME= os.getenv('APP_SEARCH_NAME')
APP_PWD = os.getenv('APP_SEARCH_PWD')

def extract():
    f = open('./tmdb.json')
    if f:
         return json.loads(f.read());        
    return {}


def index_all(movieDict={}):

    for id, movie in movieDict.iteritems(): 
        index_doc(movie)
        print(id)


def index_doc(doc):
    content=[]
    content.append(doc)
    resp = requests.post("http://localhost:3002/api/as/v1/engines/"+APP_NAME+"/documents", 
        headers={"content-type":"application/json","Authorization": "Bearer "+APP_PWD}, 
        data=json.dumps(content))
    print resp



def main():
    movieDict = extract()
    index_all(movieDict=movieDict)
  
if __name__== "__main__":
  main()    
