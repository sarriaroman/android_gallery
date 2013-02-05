# Photo Gallery for Android with ICS Style

This gallery lets you implement in a simple way a powerful photo gallery for your project. You just need to send the array of images and the Gallery will do the rest.

## How to use it

Important: This library require the following library to show the pictures: https://github.com/chrisbanes/PhotoView, please download and follow the first step previous to import this library.

1) Download the repo from GitHub and import it into your Eclipse workspace.
2) Go to your project properties.
3) In the Android section go to the bottom and add the gallery project in the Project Libraries section.
4) Create a new Class and extend it from GalleryActivity.

Example
``` java
public class Gallery extends GalleryActivity {

}
```

5) Declare the new Activity in your AndroidManifest.xml

``` xml
<activity android:name="Gallery" android:configChanges="keyboard|keyboardHidden|orientation|screenSize"></activity>
```

6) Now you just need to send the urls with an intent.

``` java
String[] urls = new String[] {"http://918thefan.com/wp-content/uploads/2011/06/Speed-Racer.jpg", "http://images.wikia.com/speedracer/images/8/8e/MACH_6.jpg"};

Intent gallery = new Intent(this, com.example.Gallery.class);
gallery.putStringExtra("url", urls);
startActivity(gallery);
```

7) Enjoy!

## Photo View

If you want to use just the photo viewer also you can.

1) Create a new Class and extend it from PhotoActivity.

Example
``` java
public class PhotoViewer extends PhotoActivity {
}
```

2) Declare the new Activity in your AndroidManifest.xml

``` xml
<activity android:name="PhotoViewer" android:configChanges="keyboard|keyboardHidden|orientation|screenSize"></activity>
```

3) Now you just need to send the url with an intent.

``` java
String url = "http://918thefan.com/wp-content/uploads/2011/06/Speed-Racer.jpg";

Intent photo = new Intent(this, com.example.PhotoViewer.class);
gallery.putStringExtra("url", url);
startActivity(photo);
```

## License

    Copyright 2013 Román A. Sarria

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.