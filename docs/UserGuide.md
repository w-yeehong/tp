---
layout: page
title: User Guide
---

# Covigent - User Guide

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)<br>
    3.1 [Command Format](#31-command-format)<br>
    3.2 [Add a patient: `addpatient`](#32-add-a-patient-addpatient)<br>
    3.3 [Edit a patient: `editpatient`](#33-edit-a-patient-editpatient)<br>
    3.4 [Add rooms in hotel: `addRooms`](#34-add-rooms-in-hotel-addrooms)<br>
    3.5 [Finds the next room which is free to use: `findRoom`](#35-finds-the-next-room-which-is-free-to-use-findroom)<br>
    3.6 [Save the data](#36-save-the-data)<br>
 4. [FAQ](#4-faq)
 5. [Command Summary](#5-command-summary)


--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

Covigent is a desktop app for managing information of quarantined individuals and the tasks to be done by staff of the quarantine facilities. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you type fast, Covigent can improve your efficiency in managing your patients and tasks instead of using traditional GUI apps.


## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. (*Coming soon*) Download the latest `covigent.jar` from [here](https://github.com).  

1. Copy the file to the folder you want to use as the _home folder_ for the application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:

   * **`addpatient`** `n/John Doe t/37.4 d/20200910-20200924 p/98765432 a/35` : Adds a quarantined individual named `John Doe` to the application.

   * **`editpatient`** `Mary t/36.7 p/91234567` : Updates the temperature and phone number of an individual named `Mary` to 36.7 and 91234567 respectively.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

### 3.1 Command Format
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addpatient n/NAME`, `NAME` is a parameter which can be used as `addpatient n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [c/COMMENT]` can be used as `n/John Doe c/Is vegan` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
</div>

### 3.2 Add a patient: `addpatient`


Adds a quarantined individual to the application.

Format: `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]`

* Adds a quarantined individual with the specified name, temperature, period of stay, phone number and age.
* Temperature must be to 1 decimal place (e.g. 37.0 instead of 37).
* Period of stay is in the format YYYYMMDD-YYYYMMDD.
* It is optional to include the comment field for the quarantined individual.

Examples:
* `addpatient n/John Doe p/98765432 t/37.4 d/20200910-20200924 a/35`
* `addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic`

### 3.3 Edit a patient: `editpatient`

Edits an existing patient in the application.

Format: `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`

* Edits the patient with the specified `NAME`. The name refers to the name of the patient inputted into the application eariler. The name **must match exactly with the name of the patient**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `NAME` is case-insensitive.
* `TEMPERATURE` must be to 1 decimal place (e.g. 37.0 instead of 37).
* `PERIOD_OF_STAY` is in the format YYYYMMDD-YYYYMMDD.

Examples:
*  `editpatient john doe p/91234567` Edits the phone number of john doe to be `91234567`.
*  `editpatient alex t/36.7 a/21 d/20200303-20200315` Edits the temperature, age and period of stay of alex to be `36.7`, `21` and `20200303-20200315` respectively.

### 3.4 Add rooms in hotel: `addRooms`

Adds the number of room in a hotel

Format: `addRooms NUMBER_OF_ROOMS`

* Adds NUMBER_OF_ROOMS rooms into the hotel system 

Examples:
* `addRooms 123`
* `addRooms 400`

### 3.5 Finds the next room which is free to use: `findRoom`

Finds the room with the lowest room number that is free for use

Format: `findRoom`

* Finds the room number of least value that can be safely used for accommodation

Examples:
* `findRoom`

### 3.6 Save the data

Covigent data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Covigent home folder.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action | Format, Examples
--------|------------------
**Add Patient** | `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]` <br> e.g.,`addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic`
**Edit Patient** | `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`<br> e.g.,`editpatient James Lee t/36.5`
**Add Rooms** | `addRooms NUMBER_OF_ROOMS` <br> e.g., `addRooms 123`
**Find Room** | `findRoom` <br> e.g `findRoom`

