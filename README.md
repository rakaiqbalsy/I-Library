# Belajar Recycler View & Card View

##### 1. In Android Studio, go to File ⇒ New Project and fill all the details required to create a new project. When it prompts to select a default activity, select Blank Activity and proceed.

##### 2. Open build.gradle and add recycler view dependency. com.android.support:recyclerview-v7:26.1.0 and rebuild the project.
build.gradle
```gradle
apply plugin: 'com.android.application'
 
android {
    compileSdkVersion 26
    buildToolsVersion '26.0.2'
    // ..
}
 
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    //..
 
    // RecyclerView
    compile 'com.android.support:recyclerview-v7:26.1.0'
    
    // CardView
    compile 'com.android.support:cardview-v7:26.1.0'

    // Design
    compile 'com.android.support:design:26.1.0'
}
````

##### 3. With the latest version of build tools, Android Studio is creating two layout files for each activity. For main activity, it created activity_main.xml (contains CoordinatorLayout and AppBarLayout) and content_main.xml (for the actual content). Open content_main.xml and the recycler view widget.
activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context=".MainActivity">

    <android.support.design.widget.AppBarLayout
        android:layout_height="wrap_content"
        android:layout_width="match_parent"
        android:theme="@style/AppTheme.AppBarOverlay">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/AppTheme.PopupOverlay" />

    </android.support.design.widget.AppBarLayout>

    <include layout="@layout/content_main"/>

</android.support.design.widget.CoordinatorLayout>
````

content_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:showIn="@layout/activity_main"
    tools:context=".MainActivity">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:scrollbars="vertical" />

</RelativeLayout>
````

##### 4. Open colors.xml and styles.xml located under res ⇒ values.
colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#3F51B5</color>
    <color name="colorPrimaryDark">#303F9F</color>
    <color name="colorAccent">#FF4081</color>
    <color name="year">#999999</color>
    <color name="title">#222222</color>
</resources>
````

styles.xml
```xml
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>
    <style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar" />
    <style name="AppTheme.PopupOverlay" parent="ThemeOverlay.AppCompat.Light" />
</resources>
````

##### 5. Open dimens.xml located under res ⇒ values and add the dimensions.
dimens.xml
```xml
<resources>
    <!-- Default screen margins, per the Android Design guidelines. -->
    <dimen name="activity_horizontal_margin">16dp</dimen>
    <dimen name="activity_vertical_margin">16dp</dimen>
    <dimen name="fab_margin">16dp</dimen>
    <dimen name="row_padding_vertical">10dp</dimen>
</resources>
````

##### After adding the RecyclerView widget, let’s start writing the adapter class to render the data. The RecyclerView adapter is same as ListView but the override methods are different.

##### 6. Create a class named Movie.java and declare title, genre and year. Also add the getter/setter methods to each variable.
Movie.java
```java
public class Movie {
    private String title, genre, year;
 
    public Movie() {
    }
 
    public Movie(String title, String genre, String year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String name) {
        this.title = name;
    }
 
    public String getYear() {
        return year;
    }
 
    public void setYear(String year) {
        this.year = year;
    }
 
    public String getGenre() {
        return genre;
    }
 
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
````

##### 7. Create an layout xml named movie_list_row.xml with the below code. This layout file renders a single row in recycler view by displaying movie name, genre and year of release.
movie_list_row.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="?android:attr/selectableItemBackground"
    android:clickable="true"
    android:focusable="true"
    android:orientation="vertical"
    android:paddingBottom="@dimen/row_padding_vertical"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/row_padding_vertical">
 
    <TextView
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:textColor="@color/title"
        android:textSize="16dp"
        android:textStyle="bold" />
 
    <TextView
        android:id="@+id/genre"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/title" />
 
    <TextView
        android:id="@+id/year"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:textColor="@color/year" />
 
</RelativeLayout>
````

##### 8. Now create a class named MoviesAdapter.java and add the below code. Here onCreateViewHolder() method inflates movie_list_row.xml. In onBindViewHolder() method the appropriate movie data (title, genre and year) set to each row.
MoviesAdapter.java
```java
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
       
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public MoviesAdapter(List<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
````

##### 9. Now open MainActivity.java and do the below changes. Here prepareMovieData() method adds sample data to list view.
MainActivity.java
```java
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
 
import java.util.ArrayList;
import java.util.List;
 
public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
 
        mAdapter = new MoviesAdapter(movieList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
 
        prepareMovieData();
    }
 
    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
 
        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
 
        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);
 
        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);
 
        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);
 
        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);
 
        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);
 
        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);
 
        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);
 
        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);
 
        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);
 
        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);
 
        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);
 
        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);
 
        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);
 
        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);
 
        mAdapter.notifyDataSetChanged();
    }
}
````

##### Now if you run the app, you can see the movies displayed in a list manner.

##### 10. Create an layout xml with card view, so modify movie_list_row.xml with the below code.
movie_list_row.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="?android:attr/selectableItemBackground"
    android:clickable="true"
    android:focusable="true"
    android:orientation="vertical"
    android:layout_margin="@dimen/row_padding_vertical">

    <android.support.v7.widget.CardView
        android:id="@+id/cv_movie"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:padding="@dimen/row_padding_vertical">

            <TextView
                android:id="@+id/title"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentTop="true"
                android:textColor="@color/title"
                android:textSize="16dp"
                android:textStyle="bold" />

            <TextView
                android:id="@+id/genre"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_below="@id/title" />

            <TextView
                android:id="@+id/year"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentRight="true"
                android:textColor="@color/year" />

        </LinearLayout>

    </android.support.v7.widget.CardView>

</RelativeLayout>
````

##### Note :
##### 1. How to show toast if we click it ?
##### 2. How to intent to another activity if we click it ?
##### 3. How to CRUD ?
##### 4. How to Search ?
