# Sistema de reservas 


### This system was developed and designed for applications that require some form of reservation, such as cinemas, restaurants, and hotels.



## Technologies
- Java 17
- Sprig boot
- Spring Security
- MySQL
- JWT
- BcriptPasswordEncoder
- JPA
- Lombok


  # ROUTES 
  
### User
  
| Method | Endpoint           | Description             | Access    |
| ------ | ------------------ | ----------------------- | --------- |
| POST   | `/auth/register`   | Create a new user       | Public    |
| POST   | `/auth/login`      | Authenticate user       | Public    |
| PUT    | `/auth/user`       | Update user information | Protected |
| DELETE | `/auth/user/{cpf}` | Delete user by CPF      | Protected |



### RESOURCE 

| Method | Endpoint                | Description             | Access    |
| ------ | ----------------------- | ----------------------- | --------- |
| POST   | `/auth/resource`        | Create a new resource   | Public    |
| GET    | `/auth/resource`        | Get all resources       | Public    |
| GET    | `/auth/resource/{name}` | Get resources by name   | Public    |
| PUT    | `/auth/resource/{name}` | Update resource by name | Protected |
| DELETE | `/auth/resource/{name}` | Delete resource by name | Protected |



### RESERVATION

| Method | Endpoint                                    | Description                             | Access    |
| ------ | ------------------------------------------- | --------------------------------------- | --------- |
| POST   | `/auth/reservation/{resourceId}`            | Create a reservation by resource ID     | Protected |
| POST   | `/auth/reservation/confirm/{reservationId}` | Confirm a reservation by reservation ID | Protected |
| GET    | `/auth/reservation`                         | Get all user reservations               | Protected |
| DELETE | `/auth/reservation/{reservationId}`         | Cancel a reservation by reservation ID  | Protected |

  






  

