import requests
import json
import os
import sys

indexName ="tmdb"
queryFolder ="./query"
headers={"Content-Type":"application/json","Accept":"application/json"}

def search(query, printHighlight):
    print query
    url = "http://localhost:9200/%s/_search" %indexName
    resp = requests.get(url,headers=headers,data=json.dumps(query))
    hits = json.loads(resp.text)["hits"]
    print "\r\n######################## Results ################################\r\n"
    print "No\tScore\t\t\tTitle"
    for idx, hit in enumerate(hits["hits"]):
        print "%s\t%s\t\t\t%s" %(idx+1, hit["_score"], hit["_source"]["title"])
        print "---------------------------------------------------------------"
        if printHighlight:
            if (("highlight" in hit.keys()) and ("title" in hit["highlight"].keys())):
                hl = ";".join(hit["highlight"]["title"]).replace("<em>","\033[1;31;40m").replace("</em>","\033[0m")
                print "title: \033[0;32;40m%d hit(s)\033[0m \r\n%s\r\n--" %(len(hit["highlight"]["title"]) ,hl)
            if (("highlight" in hit) and ("overview" in hit["highlight"])):
                hl = ";".join(hit["highlight"]["overview"]).replace("<em>","\033[1;31;40m").replace("</em>","\033[0m")
                print "overview: \033[0;32;40m%d hit(s)\033[0m \r\n%s\r\n--" %( len(hit["highlight"]["overview"]), hl)


def select_query():
    print "\r\n>> Please select the query file.\r\n"
    queryList=os.listdir(queryFolder)
    for idx, queryItem in enumerate(queryList):
        print "[%d] %s" %(idx,queryItem)
    userInput = raw_input()
    try:
        selectIndex = int(userInput)
    except ValueError:
        selectIndex =-1
    if(selectIndex==-1 or selectIndex > len(queryList)):
        print '\033[31mPlease provide a valid integer \033[0m'
        msg = "from 0 to %d." %(len(queryList))
        print msg
        exit()
    queryName = queryList[selectIndex]
    fileName="%s/%s" %(queryFolder,queryName)
    f = open(fileName)
    query = {}
    if f:
        query = json.loads(f.read());
    return query

def main():
    highlight = False
    for arg in sys.argv:
        if arg == "h" or arg == "hl" or arg == "highlight":
            highlight = True
    query = select_query()
    search(query, highlight)

if __name__== "__main__":
  main()
