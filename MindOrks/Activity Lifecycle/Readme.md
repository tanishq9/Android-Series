### Activity Lifecycle

#### Activity
A screen which is shown to the user. It is this screen that manages the interactions and the content visible on the screen.

#### Lifecycle 
Different states an app goes through in Android

<ul>
  <li>onCreate() : creates the activity</li>
  <li>onStart() : when the user can see the screen</li>
  <li>onResume() : when the user can interact with the screen</li>
  <li>onPause() : when part of activity is partially visible</li>
  <li>onStop() : when app is not visible to user</li>
  <li>onDestroy() : when activity is destroyed</li>
  <li>onRestart() : when we minimize the app and open it again</li>
<ul>

<img src="https://user-images.githubusercontent.com/35667308/78688754-33494200-7913-11ea-9547-5de0af7ed9d7.png"></img>


Some other use cases :

<ul>
<li>Asking Permissions : Do it in onStart(), the reason is lets say the user minimizes the screen when permission is asked, then he goes to setting to enable the permission himself, and then goes back to the app, in this case, the app will still be asking for permissions, but if he writes the askPermissions() method in onStart() then that dialog box of asking permissions will not show.</li>
<li>Pause Video on Youtube : When screen is minimized and resume when screen is opened back, write these pause and resume functionality in pause() and resume() method of lifecycle.</li>
<li>Weather App : Assume that user minimizes the screen and switches to insta, Fb app and other apps as well, so after that he navigates back to the our weather app, then onCreate() wont be called since the activity wasnt destroyed, but onrestart() will be called followed by onstart() so if the updateWeather() function is written in onStart() then the user will see live updates whenever the app is opened and not neccessarily when he destroys the activity and opens the app again.</li>
</ul>
