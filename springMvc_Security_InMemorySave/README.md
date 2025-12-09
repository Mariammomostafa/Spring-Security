# Spring Mvc security project 
- this project concerns with spring security where we able to :
- create SecurityConfig class which responsible for creation SecurityFilterChain bean
   - This class MUST be annotated by @Configuration & @EnableWebSecurity
- create SecurityInitializer class which responsible to looking for SecurityFilterChain bean & start security stuff
- create some users manulaly using User class where add username , password , roles & return object of UserDetails
- save some users in memeory (registeration) 
- when user login , retrieve user's info from memory to authenticate it (if user exists or not)
- Using InMemoryUserDetailsManager to save users & retrieve them
- Override SecurityFilterChain to :
    o	NOT make all requests authenticated & permit for some to accept them without authentication (login)
    o	Create custom login page 
    o	Create custom logout page 


### Prerequisites
- Java 21
- spring Webmvc 6.2.12
- Servlet-Api
- IDE (Eclips)
