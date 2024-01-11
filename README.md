# APP HOTEL - BACKEND
<h3>App-Hotel is a project made with java and Spring Boot that help hotels to manage their rooms and guests using Rest API architecture.</h3>

---

<h3> Configuration </h3>

To run Hotel Booker, you need to have the Java Development Kit (JDK) 17.0.6 and MySql installed and configured on your machine.



>**Endpoints & Demonstration: Rooms**  
  Rooms: /hotel/habitaciones   
>
| METHOD | ENDPOINT | DESCRIPTION | CODE |
|-----|:---------------|:---------------|:---------------|
|     GET| /buscar/{id}   |       Find a room by id     | 200 |
|     GET| /listardisponibles/{cantHuespedes}     |        Find available rooms according to guests' number     | 200 |
|     GET| /buscarhabitacion/{nro}    |        Find a room by number       | 200    |
|     GET| /listar    |        List all rooms       | 200    |
|     POST| /guardar    |       Create a new room    | 201 |
|     POST| /actualizar/{id}    |       Update a room    | 200    |
|     POST| /borrar/{id}    |       Delete room from database  | 200     |

>Note:  I always use GET to query tables, POST to create, PUT to update, and DELETE to delete, but this time I was forced to use them this way to connect back-end to front-end, due to my inexperience using Angular.
---
>**Endpoints & Demonstration: Guests**  
  Guests: /hotel/clientes   
>
| METHOD | ENDPOINT | DESCRIPTION | CODE |
|-----|:---------------|:---------------|:---------------|
|     GET| /buscar/{id}   |       Find a guest by id     | 200 |
|     GET| /listarporapellidos/{surname}     |        List all guests according to the surname     | 200 |
|     GET| /buscarpordni/{dni}    |        Find a guest by dni      | 200    |
|     GET| /listar    |        List all guests       | 200    |
|     POST| /guardar    |       Create a new guest    | 200 |
|     POST| /actualizar/{id}    |       Update a guest    | 200    |
|     POST| /borrar/{id}    |       Delete guest from database  | 200     |


---
>**Endpoints & Demonstration: Hotel**  
  Hotel: /hotel  
>
| METHOD | ENDPOINT | DESCRIPTION | CODE |
|-----|:---------------|:---------------|:---------------|
|     POST| /reservar/{idCliente}    |     Create a Check-in      | 200 |
|     POST| /checkout/{nroHab}    |       Create a Check-out   | 200    |
|     POST| /mostrarcliente/{idHabitacion}    |       Return the guest of a room   | 200     |
>

# Room's body
```json
{
    "nroHabitacion": 232,
    "tipoHabitacion": "Triple",
    "precio" : 8700.0
}
```

> Summary

<li>id - generated automatically

<li>nroHabitacion - the room's number

<li>tipoHabitacion - Room type according to the number of guests it can accomodate: "Simple", "Doble", "Triple", "Cuadruple"

<li>precio - the cost per night of the room

>

# Guest's body
```json
{
    "nombre": "Juan",
    "apellido": "Fiaschetti",
    "dni":"104456768",
    "telefono": "2494 097331"
}
```

> Summary
<li>id - generated automatically

<li>nombre - the guest name

<li>apellido - the guest surname

<li>dni - Documento Nacion de Identidad (unique) (only 11 characters)

<li>telefono - Phone number

# Check-in's body
```json
{
    "nroHabitacion": 412,
    "cantHuespedes": 3,
    "fechaDeIngreso": "2023-05-04T16:56:20.263Z",
    "fechaDeEgreso": "2023-05-09T16:56:20.263Z"
}
```

> Summary

<li>nroHabitacion - Room's number to be occupied

<li>cantHuespedes - Number of guests occupying the room

<li>fechaDeIngreso - Check-in date

<li>fechaDeEgreso - Check-out date


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

