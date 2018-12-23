# Android Animations and Transitions
These are the full project files for the album cover app used in Treehouse's Animations and Transitions course.

[Documentation for this project](https://docs.google.com/document/d/16d5h4MTKKhYKzeFlnYeTuba7MuR7mOipVhwBgLFxi-0/edit?usp=sharing)

NOTE:

I make some changes in the Gradle version to 3.0.1 to accommodates the Google() repo for implementations of dependencies. 
Then I also change sme code in the AlbumDetailActivity.java to adapting the latest butter knife libraries. Other than 
that I have not found any errors so far.

I need to get rid of the Butter Knife bindings and annotations since it looks like do not run well with the current 
Gradle configuration. Thus I can erased the butter knife implementations and annotation codes from the app's Gradle
configurations.

Therefore this app now runs in traditional ways of binding views and data. The other note needs to be addressed the 
recyclerView adapter is in form of inner class (static). The adapter still utilize same principle but the adapter is 
inside the recyclerView's parent activity Java class.

For the mirror the code is still there but I do not install the mirror plugin in the app. Those package is useless so far
to be honest. This is because although I implements the mirror sandbox dependency it does not do much other than prevent
invoking errors during runtime since sandbox package calls for these dependency.
