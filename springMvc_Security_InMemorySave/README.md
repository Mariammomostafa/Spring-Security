# Spring Mvc security project 
## for MVC :
- create Dispatcher Servlet class & another class for it's configurations
- create bean of InternalResourceViewResolver for detect the path of jsp pages 
## for Security :
- this project concerns with spring security where we able to :
- create SecurityConfig class which responsible for creation SecurityFilterChain bean
   - This class MUST be annotated by @Configuration & @EnableWebSecurity
- create SecurityInitializer class which responsible to looking for SecurityFilterChain bean & start security stuff
- create some users manulaly using User class where add username , password , roles & return object of UserDetails
- save some users in memeory (registeration) 
- when user login , retrieve user's info from memory to authenticate it (if user exists or not)
- Using InMemoryUserDetailsManager to save users & retrieve them
- create bean of PasswordEncoder to encode password before saving it & will be used in login process where encode the entered password         firest , then compare it with one saved in DB whhich is already encoded
- Override SecurityFilterChain to :
    - NOT make all requests authenticated & permit for some to accept them without authentication (login)
    - permit the path of jsp pages to be accessed without authentication
    -	Create custom login page
          -	create loginProcessingUrl which will be handled by spring without need to create handler method in controller to handle               this url 
    -	Create custom logout :
           - custom logout url (/customlogout) which is POST request & that is default way that will be handled by spring
           - disable the session & delete SessionId


### Prerequisites
- Java 21
- spring Webmvc 6.2.7
- Servlet-Api 6.0.0
- spring-security-config 6.2.7
- spring-security-web  6.2.7
- jstl 3.0.0
- IDE (Eclips)
