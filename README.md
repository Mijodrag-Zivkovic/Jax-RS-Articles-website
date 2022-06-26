# Jax-RS-Articles-website
Articles website made with Jax-RS (backend) and Vue (frontend) </br>
Articles are divided in categories, made by writers. Writers can also create tags and assign them to their articles.
Website has 2 types of authorised users:
  - writer - has privileges of basic CRUD operations over articles, tags, categories 
  - admin - all of the above, with the privilege of adding or removing users (writers)
 
Unauthorised users can only read articles, search articles, or filter articles by certain criteria </br>
Authorisation is implemented with the help of JWT </br>
Database used: MySQL </br>

How to run project:
- you will need to install MySQL 
- you will need to download lates tversion of Tomcat, as Jax-RS is dependant on it, and configure backnd project through your favourite IDE
- you will need to install Node.js, as Vue.js libraries are downloaded using node packet manager (npm) 
