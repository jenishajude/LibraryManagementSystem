Library Management System

A complete Spring Boot Library Management System application for REST APIs; its purpose is to demonstrate its various API 

Running the Spring Boot app:
	Navigate to the directory into which you cloned the repo and execute this command : mvn spring-boot:run
	Once started you can access the APIs on port 8081, e.g.http://localhost:8081/api/books
	The port number can be changed by editing the port property in src/main/resources/application.properties file
	
Authentication:

This application uses HTTP Basic Authentication for securing the API.

Example using Postman:

 * Use Postman or any other API testing tool to for the API endpoint access
 * Under Authorization select Basic Auth and provide the admin credentials
	username : admin
	password : admin
	

API Documentation: 

API documentation is generated using Swagger. 
After running the application access the Swagger UI at http://localhost:8081/swagger-ui/
Authentication for Swagger Documentation
username : user
password : user

API endpoints interaction:

Book Entity:

Book{
	id  long
	title	string
	author	string
	isbn	string
	publicationYear	string
}

Book management endpoints:

Retrieve a list of all books.
GET
http://localhost:8081/api/books

API Endpoints api/books

Response:

[
    {
        "title": "The India Story",
        "author": "Bimal Jalal",
        "publicationYear": "2015",
        "isbn": "978-3-16-148410-0",
        "id": 4
    },
    {
        "title": "A Place Called Home",
        "author": "Preeti Shenoy",
        "publicationYear": "2017",
        "isbn": "878-03-16-1478910-1",
        "id": 5
    }
]


Retrieve details of a specific book by ID.
GET
http://localhost:8081/api/books/5

API Endpoints api/books/{id}

Response:
 {
        "title": "A Place Called Home",
        "author": "Preeti Shenoy",
        "publicationYear": "2017",
        "isbn": "878-03-16-1478910-1",
        "id": 5
    }


Add a new book to the library.
POST
http://localhost:8081/api/books

API Endpoints api/books

Request:
	{
        "title": "War and Peace",
        "author": "Tolstoy Gard",
        "publicationYear": "2019",
        "isbn": "878-1478-910-899-1",
    }

Response:

	{
        "title": "War and Peace",
        "author": "Tolstoy Gard",
        "publicationYear": "2019",
        "isbn": "878-1478-910-899-1",
		"id": 6
    }
	

Update an existing book's information.
PUT
http://localhost:8081/api/books/6

API Endpoints api/books/{id}

Request:
	{
        "title": "War and Peace",
        "author": "Tolstoy Gard",
        "publicationYear": "2020",
        "isbn": "878-1478-910-899-1",
    }

Response:

	{
        "title": "War and Peace",
        "author": "Tolstoy Gard",
        "publicationYear": "2020",
        "isbn": "878-1478-910-899-1",
		"id": 6
    }
	

Remove a book from the library.
DELETE
http://localhost:8081/api/books/6

API Endpoints api/books/{id}

Response:
	Book deleted Successfully!
	
	
Patron Entity:

Patron{
	id	long
	name	string
	contactInfo	string
}

Patron management endpoints:

Retrieve a list of all patrons.
GET
http://localhost:8081/api/patrons

API Endpoints api/patrons

Response:
[
    {
        "id": 2,
        "name": "Anny",
        "contactInfo": "0544000789"
    },
    {
        "id": 3,
        "name": "Abe",
        "contactInfo": "0544000799"
    }
]

Retrieve details of a specific patron by ID.
GET
http://localhost:8081/api/patrons/3

API Endpoints api/patrons/{id}

Response:
	{
        "id": 3,
        "name": "Abe",
        "contactInfo": "0544000799"
    }


Add a new patron to the system.
POST
http://localhost:8081/api/patrons

API Endpoints api/patrons

Request:
	{
        "name": "Watson Wane",
        "contactInfo": "0544008989"
    }

Response:
	{
        "id": 4,
        "name": "Watson Wane",
        "contactInfo": "0544008989"
    }

Update an existing patron's information.
PUT
http://localhost:8081/api/patrons/4

API Endpoints api/patrons/{id}

Request:
	{
        "name": "Watson",
        "contactInfo": "0544008989"
    }

Response:
	{
        "id": 4,
        "name": "Watson",
        "contactInfo": "0544008989"
    }
	
Remove a patron from the system.
DELETE
http://localhost:8081/api/patrons/4

API Endpoints api/books/{id}

Response:
	Patron deleted Successfully!
	
Borrowing Record Entity:

BorrowingRecords{
		borrowDate	string
		borrowed	boolean
		borrowedBook	
			Book{
				author	string
				id	long
				isbn	string
				publicationYear	string
				title	string
			}
		borrowedBy	
			Patron{
				contactInfo	string
				id	long
				name	string
			}
		returnDate	string
}

Allow a patron to borrow a book.

POST
http://localhost:8081/api/borrow/4/patron/2

API Endpoints /api/borrow/{bookId}/patron/{patronId}

Response:
{
    "borrowed": true,
    "borrowedBy": {
        "id": 2,
        "name": "Anny",
        "contactInfo": "0544000789"
    },
    "borrowedBook": {
        "title": "The India Story",
        "author": "Bimal Jalal",
        "publicationYear": "2015",
        "isbn": "978-3-16-148410-0",
        "id": 4
    },
    "borrowDate": "2024-01-27",
    "returnDate": ""
}


Record the return of a borrowed book by a patron.

PUT
http://localhost:8081/api/return/4/patron/2

API Endpoints /api/return/{bookId}/patron/{patronId}

Response:
{
    "borrowed": false,
    "borrowedBy": {
        "id": 2,
        "name": "Anny",
        "contactInfo": "0544000789"
    },
    "borrowedBook": {
        "title": "The India Story",
        "author": "Bimal Jalal",
        "publicationYear": "2015",
        "isbn": "978-3-16-148410-0",
        "id": 4
    },
    "borrowDate": "2024-01-27",
    "returnDate": "2024-01-28"
}
