# Decisions
Information about my design choices and development process.
## Environment
At first I though about using Xamarin Framework with Visual Studio since I'm comfortable with C# and Visual Studio environment. However, for the purposes of this task I decided to use Android Studio and Java since I am new to both IDE and the language.
## Design Choices
### First Part
To fetch popular actor list from themoviedb api I used the endpoint /person/popular.
Fetching needs to be asynchronous so I used volley library to handle requests.
To display fetched actors I use ListView by writing a custom ArrayAdapter.
Themoviedb API returns actors by pages, so I start with loading the first page, everytime user scrolls down to the end of the list I request for the new page.
### Second Part
I had several approaches for the search part. On task details it says that actors should be searched by first name but API makes search in full name and also returns partial matchings.
The first version of search were looking every actor in search response and controls if its matched by first name. However it was taking some time since it needs to request every page of the results.
Also I think about hashing actor names while loading the popular actor list, this would give us constant time on search however correct results would be given only after all actors fetched, which takes long time.
Finally after talking with Erinç Argımak, we decided to make an assumption and use the "total_results" field of search response.    
