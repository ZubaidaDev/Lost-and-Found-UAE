# Lost and Found UAE

Mobile Application Development Group Project

## Project Idea

Lost and Found UAE is an Android mobile application that allows users to report lost items and found items.

Users can:
- Report a lost item
- Report a found item
- Search for items
- View item details

This project will use:
- Java
- XML
- SQLite Database

---

## Development Platform

We will use Android Studio to build and test the application.

GitHub will be used to share the project files and manage teamwork.

GitHub Codespaces is not required because Android apps are easier to develop and test using Android Studio with an emulator or a real Android phone.

---

## Team Work Plan

### Person 1: Search Screen

Responsible for:
- Main app screen
- Menu navigation
- Search screen
- Search button logic
- Connecting screens together

Files:
- MainActivity.java
- SearchFragment.java
- activity_main.xml
- fragment_search.xml
- main_menu.xml

---

### Person 2: Database and Adapter

Responsible for:
- SQLite database
- Item model class
- Search results display
- Adapter for showing items
- Manifest setup

Files:
- DatabaseHelper.java
- ItemAdapter.java
- Item.java
- AndroidManifest.xml
- item_result.xml

---

### Person 3: Report Lost and Found

Responsible for:
- Report Lost form
- Report Found form
- Taking user input
- Sending form data to the database
- Helping with screenshots, report, and video demonstration

Files:
- ReportLostFragment.java
- ReportFoundFragment.java
- fragment_report_lost.xml
- fragment_report_found.xml

---

## Balance Check

| Person | Java Files | XML Files | Total |
|---|---:|---:|---:|
| Person 1 | 2 | 3 | 5 |
| Person 2 | 3 | 2 | 5 |
| Person 3 | 2 | 2 | 4 |

This division is simple and balanced enough for a student-level project.

---

## App Menu Structure

The app will use a 3 dots menu with these options:

```text
Menu
├── Report Lost
├── Report Found
├── Search
└── About
```

---

## App Flow

```text
Report Lost → Save to SQLite
Report Found → Save to SQLite
Search → Read from SQLite
Search Result → Show item details
```

---

## GitHub Workflow

### Before starting work

Always pull the latest version first:

```bash
git pull
```

This updates your laptop with the latest files from GitHub.

---

### After finishing work

Save your changes, then push your work to GitHub:

```bash
git add .
git commit -m "Write what you changed"
git push
```

---

## Suggested Screens

1. Home / Search Screen
2. Report Lost Item Screen
3. Report Found Item Screen
4. Search Results Screen
5. Item Details Screen
6. About Scree
