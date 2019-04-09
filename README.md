# Deplike Intern Task
An Android app implemented in Android Studio. Lists popular actors from themoviedb.org and basic search functionality which returns number of actors with given name.
 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
### Prerequisites

```
Android Studio 3 or newer.
```

### Installing

Create an account on [Themoviedb](themoviedb.org) and get a API key.
```
Navigate to folder %ProjectPath%\app\src\main\res\values
```

Create a new xml file named "locals.xml" and paste following in it, use your own API key:
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="api_key">api_key=YOUR_API_KEY_HERE</string>
</resources>
```

Build and run!

## Built With

* [Volley](https://github.com/google/volley) - HTTP library
* [Picasso](https://square.github.io/picasso/) - Image loading library
