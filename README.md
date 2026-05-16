## Team Work Plan

### Person 1: Main Screen, Menu, and Search

Responsible for:
- Main app structure
- MainActivity
- Options menu with 3 dots
- Search screen
- Search results list
- Item result layout

Main files:
- MainActivity.java
- SearchFragment.java
- ItemAdapter.java
- activity_main.xml
- fragment_search.xml
- item_result.xml
- res/menu/main_menu.xml

---

### Person 2: Report Lost Item and Database

Responsible for:
- Report Lost Item screen
- Lost item form
- Saving lost item data into SQLite
- DatabaseHelper class
- AndroidManifest.xml setup

Main files:
- ReportLostFragment.java
- DatabaseHelper.java
- fragment_report_lost.xml
- AndroidManifest.xml

---

### Person 3: Report Found Item and Data Sending

Responsible for:
- Report Found Item screen
- Found item form
- Sending submitted data back to SearchFragment
- Bundle and Interface communication

Main files:
- ReportFoundFragment.java
- fragment_report_found.xml

---

## App Menu Structure

The app will use a 3 dots menu with these options:

```text
Menu
├── Report Lost
├── Report Found
└── Search
