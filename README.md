# Retail Store Discount API

### Introduction
Retail Store Discount API is a simple project that is calculating discounts for different
types of users, following specific Discount rules:
- User of type "Employee" have 30% discount on the bill
- User of type "Affiliate" have 10% discount on the bill
- User of type "Customer" (or simple user), who is registered for over 2 years, is eligible
for 5% discount on the bill
- If the order contains groceries, no percentage discount is available.
- User, who is eligible to several discounts, can take only one (with biggest percent).
- There is another type of discount which is based on amount, not on the user role, as follows:
for every 100 on the bill, there will be 5 discount, e.g. bill 990, 45 is the discount

### Install & Run
- Clone the repository
- go into the project folder and execute `./mvnw spring-boot:run`
- The app should be up and running on `localhost:8080`

### Tooling
- Swagger - API documentation
- JWT - authenication mechanism
- Jacoco - test report / test coverage
- H2 - in memory db for this demo
- Flyway - migration tooling

### UML Class Diagram - Discount Processing
![](doc/uml.jpg)

### Testing
To run all tests, execute the following command
```
./mvnw clean test
```
If test report is required exec the following:
```
./mvnw clean test jacoco:report
```
The report will be generated in `/target/site/jacoco/index.html`

### API Descriptor
Swagger is available for the API at [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)
![](doc/swagger.png)

### Demo Data
`LoadDemoData` class will load test users when the application starts.
Password is `secret` to all accounts.
Usernames are as follows:
```
customer@retail.com
employee@retail.com
affiliate@retail.com
customer-longterm@retail.com
employee-longterm@retail.com
affiliate-longterm@retail.com
```
The in-memory database is accessible at `http://localhost:8080/h2-console`

### Sample request flow
- Get authentication token
```
Request
POST /api/authenticate
{
  "username": "employee@retail.com",
  "password": "secret"
}
```
```$xslt
Response
{
    "id_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZmZpbGlhdGVAcmV0YWlsLmNvbSIsImF1dGgiOiJBRkZJTElBVEUsQ1VTVE9NRVIiLCJleHAiOjE1NjA2Nzc3NDZ9.Th0jfgMYSqOtMtqb4yS0aSJYcwnadswVx25C5dbZWSxq8fcNso6XLkz9ICy7TKJESx3bx7987FxcySdfS7__kQ"
}
```
- Calculate discount for the logged in user
```$xslt
Request
POST /api/discounts
HEADER: Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZmZpbGlhdGVAcmV0YWlsLmNvbSIsImF1dGgiOiJBRkZJTElBVEUsQ1VTVE9NRVIiLCJleHAiOjE1NjA2Nzc3NDZ9.Th0jfgMYSqOtMtqb4yS0aSJYcwnadswVx25C5dbZWSxq8fcNso6XLkz9ICy7TKJESx3bx7987FxcySdfS7__kQ
PAYLOAD
{
	"bill": 250.5,
	"includeGroceries" : false
}
```
```$xslt
Response
{
    "discountedBill": 215.45,
    "bill": 250.5
}
```

### Improvements
- Export sensitive data from application.yml, use placeholders.
- Setup correct profiling structure - currently only `dev` and `swagger` profiles are created.
- Setup Sonar for code analyzing
- Dockerize the application
- Use proper persistence, not in memory
- If the discount rules are constantly changing, it would be better to persist them and load them on demand.
Also doing this, it would be better approach to create some DSL language for the discount rule engine.


### Task 2
Documentation for this task is available here:
`doc/Technology_Assessment-The_Omni_Channel_Project.pdf`
Link:  [Omni Channel Project assessment](doc/TechnologyAssessment.pdf)
