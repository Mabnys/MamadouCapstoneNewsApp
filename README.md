# MamadouCapstoneNewsApp

## AndroidBootcamp

This repo is created to store the homeworks related to the repo.

***Note that all the homeworks have their own branches that are merged to the development branch.***

All Homeworks branches related to this repo are and will be merge with the development branch.

## Week 2 Homework

### *- Need to have (for passing grade):*
- [x] Data classes are created for Article and Source
- [x] 5 TextViews where each one includes text of the article title, author, and source name.
- [x] Nullable types are used for certain properties which can be null such as author, source id. Other properties can be checked from News API documentation.
- [x] Hardcoded news data is saved in ArrayList
- [x] Layout for main activity is created
- [x] Loops are used for iterating the list and data is shown on the screen
- [x] Null checks are done using if/else statements
- [x] Code is divided into multiple functions instead of doing everything in onCreate(). At least one new function should be created.

### *- Nice to have (for Above and Beyond):*
- [x] Use default values with functions
- [x] Use maps to store hardcoded data instead of ArrayList


## Week 3 Homework

### *- Need to have (for passing grade):*
- [x] Switch following Source Properties to use enum classes:
  * #### Category
  * #### Language
  * #### Country    
- [x] Create a  **NewsService** with a single method to get articles.
- [x] Reference **NewsService** from Activity and get the articles.
- [x] Your main activity layout should have a ScrollView at the top level.
- [X] Inflate a custom layout and loop through articles and set text (like title, author, etc) on the layout.
- [x] There should be an equivalent number of articles for each article element in your list.

### *- Nice to have (for Above and Beyond):*
- [ ] Convert **NewsService** to an interface and create an **InMemoryNewsServiceImpl** class that implements **NewsService** to use in the Activity.  
- [X] Use a custom view called **ArticleView** that wraps the custom layout.
- [ ] Add nulls in your articles list and use the *filter()* extension function!



## Week 4 Homework

### *- Need to have:*
- [X] An item row layout file
- [X] Use of RecyclerView to display the news data
- [X] Display news details in a separate screen
- [X] Clicking on a news article should take users to the details screen 

### *- Nice to have:*
- [ ] Swipe to delete an item from the displayed ArrayList in Recyclerview
- [x] Use of Jetpack Navigation
- [ ] Single activity with multiple fragments
- [x] Refined UI design (Bolder news heading, lighter description font styles, use of CardView, appropriate padding, margins–you’re free to use your creativity here)
- [x] Use of SharedPreferences to persist the news data for offline use


## Week 5 Homework

### *- Need to have:*
- [x] News articles are fetched from News API instead of hardcoded data
- [x] Proper parsing of JSON data is done
- [x] Error handling - All exceptions/errors are handled gracefully (Network exception, json exception) by showing error messages
- [x] Internet check before making network calls
- [x] Refresh button functionality to get the latest news data

### *- Nice to have:*
- [x] Using Result/sealed classes for different error states (Loading, Success, Failure)
- [x] Making custom screens/dialogs for different error states
- [ ] Use of logging interceptor to analyze each network request
- [x] Using Glide to load images

## Week 6 Homework

### *- Need to have:*
- [x] Retrofit with coroutines is used for the networking layer
- [x] Appropriate scope is used for each coroutine
- [x] Handle coroutine exceptions
- [x] All network requests are done on a background thread


### *- Nice to have:*
- [x] Handle UI responsively when making network requests, e.g. show loading indicator until response returns
- [ ] Implement Repository (just for API layer - there is no local database yet)
- [ ] Proper use of MutableLiveData and LiveData with proper encapsulation

## Week 7 Homework

### *- Need to have:*
- [x] A Details screen that adds some sort of filter to the image
- [x] Proper use of WorkManager
  * Use of [input data](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#input_output)
  * Handling different [states](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/states-and-observation) of WorkManager 
  * Proper use of constraints (Only apply the filter if battery is not low) 
- [x] Adding 3 constraints to the Worker
  * [See here](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#work-constraints) for list of possible constraints


### *- Nice to have:*
- [x] Saving the file using FileOutputStream
- [x] Apply more than one worker to the WorkManager
  * You could add multiple image filter types (perhaps a blurry image filter and a sepia filter)
  * You could also wrap the article fetching logic (retrofit call to the API) behind a worker! 
  * Or, come up with your own set of work to wrap behind a Worker!
  
## Week 8 Homework

### *- Need to have:*
- [x] Offline mode
  * **NewsDatabase** implementation
  * **Article** with **@Entity** and **@PimaryKey** on a new id property
  * **SourceDao** with **@Entity** and **@PrimaryKey** on existing id property
  * DAO for article and source entities with getArticles()/getSources(), 
     addArticles()/addSources(), and clearArticles() methods.
- [x] Article Search
  * EditText and Button to search articles
  * Repository and DAO methods to query the articles 
 
- [x] Download Only Over WiFi
   * Create a Preference DataStore
   * Feed in the datastore to the repository and fetch the key/value from the datastore. 
   * Toggle menu icon in toolbar or ToggleButton/Checkbox on the main, list layout
   * Toggle menu icon in toolbar or ToggleButton/Checkbox on the main, list layout


### *- Nice to have:*
- [x] Offline mode
  * Relationships defined on **Article** and **Source** entities. One **Source** can have many **Article** so the relationship is 
  [one-to-many](https://developer.android.com/training/data-storage/room/relationships#one-to-many).
- [x] Article Search
  * Use SearchView or a more robust search UI
- [ ] Use a PeriodicWorkRequest to periodically fetch new article data from the NewsAPI.
  * See *optional* section for more details



