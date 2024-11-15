# APP HOTEL - BACKEND
<h3>This project is a hotel management application that allows users to manage rooms, customers, and reservations. The application is built using Spring Boot and provides a REST API to interact with the system.</h3>

---

<h3> Configuration </h3>

To run App-Hotel, you need to have the Java Development Kit (JDK) 17.0.6 and MySql installed and configured on your machine.

## Features 
- Room Management: Create, update, delete, and view rooms. 
- Customer Management: Create, update, delete, and view customers. 
- Reservation Management: Create reservations for specific rooms and customers.

>**Endpoints & Demonstration: Rooms**  
  Rooms: /hotel/rooms   
>
| Method | Endpoint | Description | 
|--------|-------------------------------|---------------------------------------------| 
| GET | /hotel/rooms | Get all rooms | 
| GET | /hotel/rooms/{id} | Get a room by ID | 
| GET | /hotel/rooms/number/{roomNumber} | Get a room by number | 
| POST | /hotel/rooms | Create a new room | 
| PUT | /hotel/rooms/{id} | Update an existing room | 
| DELETE | /hotel/rooms/{id} | Delete a |

---
>**Endpoints & Demonstration: Customers**  
  Customers: /hotel/customers   
>
| Method | Endpoint | Description |
|--------|-------------------------------|---------------------------------------------| 
| GET | /hotel/customers | Get all customers | 
| GET | /hotel/customers/{id} | Get a customer by ID | 
| GET | /hotel/customers/lastname/{lastName} | Get customers by last name | 
| GET | /hotel/customers/passport/{passport} | Get customers by passport | 
| POST | /hotel/customers | Create a new customer | 
| PUT | /hotel/customers/{id} | Update an existing customer | 
| DELETE | /hotel/customers/{id} | Delete a customer | ### Reservations

---
>**Endpoints & Demonstration: Hotel**  
  Hotel: /hotel  
>
| METHOD | ENDPOINT                                                    | DESCRIPTION                                       |
|--------|:------------------------------------------------------------|:--------------------------------------------------|
| POST   | /hotel/rooms/{customerId}/reservation                       | Create a new reservation for a customer in a room | 
| POST   | /rooms/{nroHab}/checkout                                    | Create a Check-out                                | 
| GET    | /rooms/{roomId}/customer                                    | Get the customer of a room                        | 
>

# Room's body
```json
{
    "roomNumber": 232,
    "roomType": "Doble",
    "pricePerNight" : 87.0
}
```

> Summary

<li>id - generated automatically

<li>roomNumber - the room's number

<li>roomType - Room type according to the number of guests it can accomodate: "Simple", "Doble", "Triple", "Cuadruple"

<li>pricePerNight - the cost per night of the room

>

# Customer's body
```json
{
    "name": "Juan",
    "lastName": "Fiaschetti",
    "passport":"104456768",
    "phone": "2494 097331"
}
```

> Summary
<li>id - generated automatically

<li>name - the guest name

<li>lastName - the guest surname

<li>passport -  (unique) (only 11 characters)

<li>phone - Phone number

# Check-in's body
```json
{
    "roomNumber": 412,
    "guestCount": 3,
    "checkInDate": "2023-05-04T16:56:20.263Z",
    "checkOutDate": "2023-05-09T16:56:20.263Z"
}
```

> Summary

<li>roomNumber - Room's number to be occupied

<li>guestCount - Number of guests occupying the room

<li>checkInDate - Check-in date

<li>checkOutDate - Check-out date


<h3>

> Technologies used

</h3>

<li> Java JDK (17.0.6)
<li> Spring Boot (3.0.5)
<li> Maven
<li> MySQL
<li> Postman

<br>
<br>

>
> Thankings
>



```
Thank you for taking the time reading this project. I hope that the information presented 
here was helpful. If you have any feedback or suggestions, please feel free to reach out to me!
```

