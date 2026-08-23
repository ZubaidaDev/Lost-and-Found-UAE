Lost and Found UAE

Lost and Found UAE is a native Android application for reporting, searching, and managing lost and found items.

The application provides a simple local platform where users can submit lost or found item reports, browse existing listings, search for possible matches, and send matching items for review.

Features

* Report lost and found items
* Add item name, description, location, date, and image
* Browse active lost and found reports
* Search by name, description, or location
* Filter results by lost or found items
* Submit potential item matches for review
* Review pending matches through an admin interface
* Remove resolved items from the database
* Input and date validation

Tech Stack

* Java
* Android SDK
* XML
* SQLite
* Android Fragments
* Gradle

Application Flow

Report Lost / Found Item
          |
          v
     SQLite Database
          |
          v
 Search & Filter Items
          |
          v
 Submit Match Request
          |
          v
     Pending Review
          |
          v
   Admin Resolution

Items are stored locally with either an active or pending status. Active items are available through search, while potential matches can be moved to pending status for review.

Project Structure

app/src/main/
├── java/.../lostandfounduae/
│   ├── MainActivity.java
│   ├── SearchFragment.java
│   ├── ReportLostFragment.java
│   ├── ReportFoundFragment.java
│   ├── AdminDeleteFragment.java
│   ├── AboutFragment.java
│   ├── DatabaseHelper.java
│   └── Item.java
│
├── res/
│   ├── layout/
│   ├── menu/
│   └── values/
│
└── AndroidManifest.xml

Requirements

* Android Studio
* Android SDK 24 or later
* Java 11 compatible environment

Getting Started

Clone the repository:

git clone https://github.com/ZubaidaDev/Lost-and-Found-UAE.git

Open the project in Android Studio, allow Gradle to sync the dependencies, and run the application using an Android emulator or physical device.

Current Scope

The application uses local SQLite storage and is intended as a functional Android prototype.

It currently does not include cloud synchronization, user accounts, remote databases, or production-grade authentication.