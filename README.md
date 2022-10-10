# Android Project 5 - *BitFit*

Submitted by: **Corey Smith**

**BitFit** is a health metrics app that allows users to track mood, water, calories, food intake, and sleep 

Time spent: **20** hours spent in total

## Required Features

The following **required** functionality is completed:

- [x] **At least one health metric is tracked (based on user input)**
  - Chosen metric(s): `Mood`
- [x] **There is a "create entry" UI that prompts users to make their daily entry**
- [x] **New entries are saved in a database and then updated in the RecyclerView**
- [x] **On application restart, previously entered entries are preserved (i.e., are *persistent*)**
 
The following **optional** features are implemented:

- [ ] **Create a UI for tracking averages and trends in metrics**
- [x] **Improve and customize the user interface through styling and coloring**
- [ ] **Implement orientation responsivity**
- [ ] **Add a daily photo feature**

The following **additional** features are implemented:

- [x] Implemented fragments for future features such as water, calorie, and sleep tracking, and user log in
- [x] Pop up to delete previous entries from database

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='bitfit.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


GIF created with GIF Maker-Editor by Zatashimalab


## Notes

Creating responsive data. Future work on the project will include collapsing the RecyclerViews into smaller menus, and
converting all data to LiveData types so that changes in the database are more apparent. I used this project mostly to
reinforce practice of RecyclerViews and layouts but designed it with expansion in mind.

## License

    Copyright [2022] [Corey Smith]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
