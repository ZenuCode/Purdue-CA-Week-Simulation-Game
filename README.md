# Purdue-CA-Week-Simulation-Game

## Overview:
Working as a Summer Conference Assistant (CA) at Purdue, I wanted to create a scheduler which would take into account of preferred hours, unavailability, and equal amount of hours assigned. Through this I needed to take into account of many factors which resulted in me making this program. After creating the scheduler I wanted to also create a simulation game which goes through the variety of tasks and questions that are given as a daily task of a CA and the output results vary from the user input's decisions. (Like similar intelligent decision game released in the modern game world)

## Objective:
1. Create a Scheduler for Employees
2. Create a Playable Simulation Game

## Components:
1. Schedule csv file.

## How to Play / Rules of Game:
1. The Schedule
  - The game starts with reading a csv file which uses that data to assign the most effecient and preferred schedule.
  - After schedule is created, you are able to swap schedules before the game is started.
  -
2. The Game Rules
Basic Rules:
  - There are 3 parts of the game to be cleared: Frieda Parker Halls, Meredith South Halls and the Mail Room.
  - Each employee in your schedule is given 3 lives and 70 stamina at the start.
  - If employee loses all lives, their names are discarded from the schedule and will need to be filled by the user.
  - Stamina is a part of the game where increases if made the correct decisions of the given situation.
  - If wrong decision is made stamina is decreased for how severe the situation is and the method selected by user to solve the issue.
  - Stamina is also lost constantly by how long they work. This is a comparison to people becoming tired when working in the real world.
  - When stamina becomes low, there will be given a "slack off" choice which basically the employee will be able to choose if they would slack off or will do the professional decision. If not caught slacking off, it will be considered as resting time and stamina will be restored.
  
Functionality:
  - Each part of the game allows the user to know the current time, the current person on shift and also being able to change the shift while playing the game.
  - They are constantly able to see the overall schedule, the employee status(life and stamina) and able to adjust the schedule in needing to rest an employee.
  
How to Play:
- You will be given many situations and options to choose from.
- These decisions will change the stamina and life of employees from the schedule.
- If needed, change the schedule to rest a player!
- If able to clear all 3 halls for the week, you have successfully passed the CA Simluation Game!
