# HomeworkTask
Hello Baltic Amadeus team!

I am very pleased to present to You my homework task for applying to Junior Android Engineer position. 
I have to admit that the code is really amateur and there is a lot of things that I would like to fix or add, which I will name later. 
Overall, this task was very fun and definitely trained my patience.

## Task: 
Create an application which shows list of items containing post titles and names of users. Screen should have a pull to refresh.
Data should be stored in the database, initially loaded from database and refreshed from API.
If an error occurs (for example, no network connection) - error dialog shown with general error message, cancel and retry buttons.

APIs:
* Posts: https://jsonplaceholder.typicode.com/posts
* User details: https://jsonplaceholder.typicode.com/users/%7buser_id%7d

Use:
+ Kotlin
+ Coroutines + Coroutines Flow
+ MVVM/MVI pattern (I used MVVM)
+ Clean architecture principles (I used my knowledge from my previous work)
+ Reactive pattern (I was not sure what exactly this means but I used `StateFlow`)
+ Android Architecture Components (UI, Repository, Data layers)
+ Dependency Injection (Dagger2/Hilt)
+ Room

## Time spent:
* UI: 6 hours
* API: 7 hours
* Database/Room: 12 hours
* Other (for example, gradle etc.): 3 hours
* not included: technical issues as I was working on a weak laptop, that has not seen any love since early 2022, nor it has the power needed for actual developing.

## How to run this project:
* install Android Studio (I used Flamingo | 2022.2.1)
* clone this repository
* open repository folder in Android Studio
check out the code, build it, run it, have fun with it

## Acceptance criteria:
*    when opened, user must see: launch screen with the logo, then circular loading indicator if data from database is taking time to load, then main screen;
*    the main screen is composed of items that contain post titles and names of users;
*    user must be able to scroll;
*    user must be able to pull in order to refresh, data is refreshed from API;
*    if there is a network error, user must see error screen, containing: "Opps! Something went wrong", retry and cancel buttons;
*    when retry button is clicked, the data is loaded from API, "Retried!" toast appears to inform user that action is performed. If error is resolved, data loads from API. If error remains, user continually sees error screen;
*    when cancel button is clicked, the data is loaded from database (like after launching the app), "Canceled!" toast appears to inform user that action is performed.

## Future fixes:
* when retry button is clicked (for the first couple times), the previous data appears for a split second
* in the home screen `PullRefreshIndicator` uses layout space
* integrating a database with Room was a first time for me, so there are definitely things to improve
* overall UI design is very primitive
* handling for more errors and showing actual `AlertDialog`
