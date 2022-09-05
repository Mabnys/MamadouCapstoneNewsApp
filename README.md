# MamadouCapstoneNewsApp

## AndroidBootcamp

This repo is created to store the homeworks related to the repo.

***Note that all the branches and homeworks have their own branches that are merged to the development branch.***

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
- [ ] Switch following Source Properties to use enum classes:
  #### . Category
   #### . Language
    #### . Country    
- [ ] Create a  **NewsService** with a single method to get articles.
- [ ] Reference **NewsService** from Activity and get the articles.
- [ ] Your main activity layout should have a ScrollView at the top level.
- [ ] Inflate a custom layout and loop through articles and set text (like title, author, etc) on the layout.
- [ ] There should be an equivalent number of articles for each article element in your list.

### *- Nice to have (for Above and Beyond):*
- [ ] Convert **NewsService** to an interface and create an **InMemoryNewsServiceImpl** class that implements **NewsService** to use in the Activity.  
- [ ] Use a custom view called **ArticleView** that wraps the custom layout.
- [ ] Add nulls in your articles list and use the *filter()* extension function!
