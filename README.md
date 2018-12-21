# Android Animations and Transitions
These are the full project files for the album cover app used in Treehouse's Animations and Transitions course.

NOTE:

I make some changes in the Gradle version to 3.0.1 to accomodate the Google() repo for implementations of dependencies. 
Then I also change sme code in the AlbumDetailActivity.java to adapting the latest butter knife libraries. Other than 
that I have not found any errors so far.

The butterknife looks like invokes many problems most likely due to incompatibility with Java 1.8 SDK. Thus I need to revise
the code to replace all butterknife annotations. 

After this I need to ensure that mirrors are still works without butterknife annotations.
