# APP HOTEL - BACKEND
This project is a hotel management application that allows users to manage rooms, customers, and reservations. The application is built using Spring Boot and provides a REST API to interact with the system.

---

## Configuration 

To run App-Hotel, you need to have the Java Development Kit (JDK) 17.0.6 and MySql installed and configured on your machine.

## Features 
- Room Management: Create, update, delete, and view rooms. 
- Customer Management: Create, update, delete, and view customers. 
- Reservation Management: Create reservations for specific rooms and customers.

>**Endpoints & Demonstration: Rooms**  
  Rooms: /hotel/rooms   
>
| Method | Endpoint | Description | Request Body Example | Success Response Example | Error Codes | 
|--------|---------------------|--------------------------------------|-----------------------------------|--------------------------|----------------------------| 
| GET | /hotel/rooms | Get all rooms | N/A | 200 OK, [{...}, {...}] | N/A | 
| GET | /hotel/rooms/{id} | Get a room by ID | N/A | 200 OK, {...} | 404 Not Found | 
| GET | /hotel/rooms/number/{roomNumber} | Get a room by number | N/A | 200 OK, {...} | 400 Bad Request, 404 Not Found | 
| GET | /hotel/rooms/available/{guestCount} | Get available rooms by guest count | N/A | 200 OK, [{...}, {...}] | 204 No Content |
| POST | /hotel/rooms | Create a new room | ```json {"roomNumber": 232, "roomType": "Doble", "pricePerNight": 87.0} ``` | 201 Created, {...} | 400 Bad Request, 409 Conflict | 
| PUT | /hotel/rooms/{id} | Update an existing room | ```json {"roomNumber": 232, "roomType": "Doble", "pricePerNight": 90.0} ``` | 200 OK, {...} | 400 Bad Request, 404 Not Found, 409 Conflict | 
| DELETE | /hotel/rooms/{id} | Delete a room | N/A | 200 OK | 404 Not Found

---
>**Endpoints & Demonstration: Customers**  
  Customers: /hotel/customers   
>
| Method | Endpoint | Description | Request Body Example | Success Response Example | Error Codes | 
|--------|----------|--------------|----------------------|--------------------------|------------| 
| GET | /hotel/customers | Get all customers | N/A | 200 OK, [{...}, {...}] | 404 Not Found | 
| GET | /hotel/customers/{id} | Get a customer by ID | N/A | 200 OK, {...} | 404 Not Found | 
| GET | /hotel/customers/lastname/{lastName} | Get customers by last name | N/A | 200 OK, [{...}, {...}] | 404 Not Found | 
| GET | /hotel/customers/passport/{passport} | Get customers by passport | N/A | 200 OK, {...} | 404 Not Found | 
| POST | /hotel/customers | Create a new customer | ```json {"name": "Juan", "lastName": "Fiaschetti", "passport": "104456768", "phone": "2494 097331"} ``` | 201 Created, {...} | 400 Bad Request, 409 Conflict | 
| PUT | /hotel/customers/{id} | Update an existing customer | ```json {"name": "Pedro", "lastName": "Fiaschetti", "passport": "104456768", "phone": "2494 097331"} ``` | 200 OK, {...} | 400 Bad Request, 404 Not Found, 409 Conflict | 
| DELETE | /hotel/customers/{id} | Delete a customer | N/A | 200 OK | 404 Not Found |

### Reservations

---
>**Endpoints & Demonstration: Hotel**  
  Hotel: /hotel  
>
| METHOD | ENDPOINT | DESCRIPTION | Request Body Example | Success Response Example | Error Codes | 
|--------|----------|-------------|----------------------|--------------------------|-------------| 
| POST | /hotel/rooms/{customerId}/reservation | Create a new reservation for a customer in a room | ```json {"roomNumber": 412, "guestCount": 3, "checkInDate": "2023-05-04T16:56:20.263Z", "checkOutDate": "2023-05-09T16:56:20.263Z"} ``` | 201 Created, {...} | 400 Bad Request, 404 Not Found | 
| POST | /rooms/{nroHab}/checkout | Create a Check-out | N/A | 200 OK | 400 Bad Request, 404 Not Found | 
| GET | /rooms/{roomId}/customer | Get the customer of a room | N/A | 200 OK, {...} | 404 Not Found |
>
## Request bodies
### Room
```json
{
    "roomNumber": 232,
    "roomType": "Doble",
    "pricePerNight" : 87.0
}
```

> Summary

* id - generated automatically

* roomNumber - the room's number

* roomType - Room type according to the number of guests it can accomodate: "Simple", "Doble", "Triple", "Cuadruple"

* pricePerNight - the cost per night of the room



### Customer
```json
{
    "name": "Juan",
    "lastName": "Fiaschetti",
    "passport":"104456768",
    "phone": "2494 097331"
}
```

> Summary
* id - generated automatically

* name - the guest name

* lastName - the guest surname

* passport -  (unique) (only 11 characters)

* phone - Phone number

### Check-in
```json
{
    "roomNumber": 412,
    "guestCount": 3,
    "checkInDate": "2023-05-04T16:56:20.263Z",
    "checkOutDate": "2023-05-09T16:56:20.263Z"
}
```

> Summary

* roomNumber - Room's number to be occupied

* guestCount - Number of guests occupying the room

* checkInDate - Check-in date

* checkOutDate - Check-out date




## Technologies used


* Java JDK (17.0.6)
* Spring Boot (3.0.5)
* Maven
* MySQL
* Postman
---
## Thankings

```
Thank you for taking the time reading this project. I hope that the information presented 
here was helpful. If you have any feedback or suggestions, please feel free to reach out to me!
```

