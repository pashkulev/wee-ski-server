# Wee-Ski Server

- [Wee-Ski Project Description](#wee-ski-project-description)
- [Wee-Ski Server Description](#wee-ski-server-description)
- [Authorities and Access](#authorities-and-access)
  - [Anonymous](#anonymous)
  - [User](#user)
  - [Moderator](#moderator)
  - [Admin](#admin)
  - [Root Admin](#root-admin)

- [Final Notes](#final-notes)

## Wee-Ski Project Description
The Wee-Ski project is intended to serve as a social network for
like minded skiers, mountain lovers and enthusiasts. Registered users
should be able to communicate with each other, organize gatherings,
parties and any kind of ski related events in different ski resorts 
around the world. And since I am a ski instructor, and also the developer
of this application, it also advertises the different courses I'm
planning to organize.

## Wee-Ski Server Description
The Wee-Ski Spring Boot Server provides a REST API for my Wee-Ski Angular Application. 
It uses MySql database, Hibernate with Spring Data JPA on top of it, 
Spring Data Rest, HATEOAS and Spring Security with JWT authentication.
At this stage the application has the following entities: 
- user
- role
- course
- comment
- log
- blocked_email

As I mentioned I'm using Spring Data Rest in this application so what I tried to
achieve is to use the provided endpoints by the Spring Data Rest Repositories 
and only use controllers and services for more custom operations like 
image uploading, changing user's authorities, enrolling a user in a course, etc. 
For example the logs don't have neither controller nor service, only Repository.
I have registered a logging interceptor which is responsible for
saving the logs and that's all. For retrieving the logs I'm using the 
provided Spring Data Rest endpoints.
I've also created appropriate projection interfaces of the entities so that 
when they are returned after a GET request,
only the needed data is provided and any delicate information 
like user passwords remains hidden.

## Authorities and Access

#### Anonymous
Anonymous users have access to most of the functionality
at this stage of the app. This means that they are not restricted
to make GET requests and explore the content of the site.
The only thing they can not do is interact with the app
(vote, comment, post, have their own profile)

For the authenticated users, the app provides 4 user roles: 
USER, MODERATOR, ADMIN, and ROOT_ADMIN.

#### User
Users should be able to access their profile, update it,
add friends, communicate, organize gatherings and events, 
create posts, reply with comments, vote for different things,
enroll in courses, etc. 

#### Moderator
Moderators don't have any special responsibilities and privileges 
at this stage of the app.

#### Admin
The big difference comes with the admin roles. This is where I put 
most of my efforts for this project so far.
An admin can access all registered users. He can see
their details and the authorities they have. An admin is able to block 
users. After that these users won't be able to login.
He can also unblock them. An admin is also able to entirely delete 
a user. On deletion the user's email is saved in the blocked_emails table
so that he can not register with this email any more. 

An Admin can create new courses, edit existing courses and delete courses.

Finally admins have access to all server logs.
They can track all the server activity. Every log contains
accessed uri, request method, response status, timestamp and the identity
of the user that made the request. The only non registered requests 
are the GET requests with status 200 OK, first because they are too much 
and also because they are not that interesting. Since the logs are 
really a lot, an appropriate full featured pagination is provided.

#### Root Admin
The last role is the ROOT_ADMIN. This authority is owned by only one 
user in the app. Except for all the privileges an Admin has, 
the ROOT_ADMIN can also modify user's authorities. He can make other
users admins or remove the admin authority of existing admins.
He is not restricted to block or delete any other user. 
He is the God Object:))

## Final Notes
At the moment this project is on hold because right now 
I have other priorities related with my studies. 
I managed to cover the project requirements of 
the Spring MVC course and had my 
public defense just a few weeks ago. I was graded 5.79 out of 6
mostly because of the presence of a few magical strings:)) 
I really like this project and how it's going so
I'll definitely extend it as soon as possible.

I hope you enjoy my project. Cheers!
