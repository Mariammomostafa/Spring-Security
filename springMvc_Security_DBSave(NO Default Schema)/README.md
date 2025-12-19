# Spring MVC security project [Default Security Schema with Dao Layer]
## for MVC :
- create Dispatcher Servlet class & another class for it's configurations
- create bean of InternalResourceViewResolver for detect the path of jsp pages 
## for Security :
- This project concerns with spring security Without using Default Security Schema where we able to :
- create our own table called customer 
- create SecurityConfig class which responsible for creation SecurityFilterChain bean
   - This class MUST be annotated by @Configuration & @EnableWebSecurity
- create SecurityInitializer class which responsible to looking for SecurityFilterChain bean & start security stuff
- create signupDto to transfer the data from jsp pages (frontend) to controllers 
- create Dao layer to save users tocustomer table & for all CRUD operations using JdbcTempalte
- create MyUserDetailsService class which implement UserDetailsService to override loadUserByUsername() which used in login process
- create AuthenticationProvider & pass custom UserDetailsService & passwordEncoder to it to use  them while load users from    DB 
- create DataSource bean for connection object which contains all DB info (url , username , password )
- create JdbcTemplate to apply CRUD operations 
- create bean of PasswordEncoder to encode password before saving it & will be used in login process where encode the entered password firest , then compare it with one saved in      DB whhich is already encoded
- Override SecurityFilterChain to :
    - NOT make all requests authenticated & permit for some to accept them without authentication (login)
    - permit the path of jsp pages to be accessed without authentication
    -	Create custom login page
          -	create loginProcessingUrl which will be handled by spring without need to create handler method in controller to handle this url 
    -	Create custom logout :
           - custom logout url (/customlogout) which is POST request & that is default way that will be handled by spring
           - disable the session & delete SessionId

### in JSP pages :
   - After Successfull login , show links based on user's roles
   - if user has ONLY role : USER , so ONLY UserDashBoard link will be viewed to him
   - if user has ONLY role : ADMIN , so ONLY AdminDashBoard link will be viewed to him
   - if user has both roles USER & ADMIN , so both UserDashBoard & AdminDashBoard links will be viewed to him
   - use Security-taglib dependency for this 
### Prerequisites
- Java 21
- spring Webmvc 6.2.7
- Servlet-Api 6.0.0
- spring-security-config 6.2.7
- spring-security-web  6.2.7
- mysql-connector-j  9.1.0
- jstl 3.0.0
- IDE (Eclips)
