---
layout: page
title: Yun Qing's Project Portfolio Page
---

## Project: Covigent

Covigent is a desktop application that is used to aid hotel staff handle the Covid-19 situation better. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a patient to Covigent and the attributes of the patient.
 * What it does: allows the user to create a new patient with attributes (name, period of stay, temperature, age, phone and an optional comment) in Covigent.
 
 * Justification: This feature is highly necessary because the user will not be able to manage the quarantine facility with Covigent if new patient cannot be added. The attributes of the patient such as temperature and period of stay are also central and important in the context of a quarantine facility.
 
 * Highlights: This enhancement affects patient-related commands to be added in future. In particular, if the attributes of the patient are not properly designed in an OOP fashion to be extensible, future developers may find it difficult to make any updates to the attributes of patient.
 
* **New Feature**: Added the ability to delete a patient from Covigent.
 * What it does: allows the user to remove a patient from Covigent when the patient is no longer staying in the quarantine facility.
 
 * Justification: This feature improves the product significantly because the user may no longer want to keep track of the data of patient that are no longer staying in the quarantine facility. This allows the user to focus only on patients residing in the quarantine facility.
 
* **New Feature**: Added the ability to search for a room in Covigent.
 * What it does: allows the user to search for a room that a particular patient is residing in or search for a room with a given room number.
 
 * Justification: This feature improves the user experience of the product significantly because it makes it easier for the user to search for a room to get the room details easily without having to scroll through the room list manually.
 
* **Code contributed**: [View on RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chiamyunqing&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed release [v1.3](https://github.com/AY2021S1-CS2103T-W12-1/tp/releases) on GitHub.

* **Enhancements to existing features**:
  * Modified the GUI from AB3 to get a basic structure for the GUI of Covigent. (Pull request [\#24](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/24))
  * Improved the GUI by adding in a panel to display patient information. (Credit: Code reused from LeeMingDe)(Pull requests [\#110](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/110)
  * Wrote unit tests for `addpatient`, `deletepatient`, `searchroom`, `allocateroom` and `listroom` commands and patient attributes.(Pull requests [\#10](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/10), [\#59](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/59), [\#60](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/60), [\#99](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/99))
  * Refactored the codebase to reflect the functionalities of classes clearly. (Pull request [\#80](https://github.com/AY2021S1-CS2103T-W12-1/tp/pull/80))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addpatient`, `deletepatient`, `searchroom`.
    * Did cosmetic tweaks to existing documentation of features `help`
  * Developer Guide:
    * Added implementation details of the ` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
