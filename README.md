MVVM Reactive App Created using the Random User API.

Here is the API documentation: https://randomuser.me/documentation


● Master/Detail flow in which at least 50 profile image thumbnails

are shown per page on a grid on a “Home” screen.

● The Home screen have a search bar where there user can search a specific profile by

their name.

● The search bar have “suggestions” feature where a list of suggested results will pop

under the search field based on user input.

● When users tap on a thumbnail on the grid or a result in the suggestion list, they’re taken

into a new screen in which the large image is shown on the top of the screen, and some

basic user data is listed below: username, first name, last name, email address, etc.

● Feature to save user number as a phone contact with name field auto completed in

the “Add Contact” screen of the default phone app on the device.

● The App have the possibility to mark as “favorite” a user. When this occurs, a

new list is displayed on the home screen with the saved users.

This list have horizontal scrolling and is displayed on top of the home

screen with the saved users, followed by the users list loaded from the API as a Grid.

This favorites list is displayed always when it exist at least one element on it, even if the user

closes the app.

● The Grid have pagination or “infinite scroll”. TODO: implemnet Pagination from Android.

● MVVM Android Architecture Blueprints https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live/

● The App have UI and UX based on Google Material Design. (https://material.io/design/)
