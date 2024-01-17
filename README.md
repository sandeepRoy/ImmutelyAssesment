# Beware!!!! 

## It's been 1 months, since i've submitted the assingment, still haven't heard back.  

# ImmutelyAssesment
```
Immutely Assesment - Sandeep Roy
github - https://github.com/sandeepRoy/ImmutelyAssesment
email - sandeep.roy2014@gmail.com
phone - 9178386506
```

A. SETUP

1. Please ensure to update the database connection url and credentials in application.properties of ImmutelyProduct.
   Also please make sure, a blank schema with name - 'immutely' exists in your database. You leave the schema blank and or import immutely.sql
   

2. Kindly, run the two projects(ImmutelyProduct & ImmutelyClient) separately using a Spring compatible IDE(Intellij IDEA).
   You can run the projects using their Main class(ImmutlyProductsApplication & ImmutlyClientApplication)

   or

   Create the jar file using 'mvn clean install' for both the projects,

   you can run the jar file inside target folder using 'java -jar ImmutlyClient-0.0.1-SNAPSHOT.jar' & 'java -jar ImmutlyProducts-0.0.1-SNAPSHOT.jar'

3. Once both the projects are up & running on port 8080 & 8081 respectively, please open below two swagger docs
   a) http://localhost:8080/swagger-ui/index.html - Product related CRUDs
   b) http://localhost:8081/swagger-ui/index.html - Multiple products price update
   
B. Product CRUDs at http://localhost:8080/swagger-ui/index.html

1. Get all products -  
   -> Please use this endpoint : GET - immutly/products
   -> This endpoint doesn't expectes any arguments or path variables
   -> depending on number of records in table, relevent list will be displayed in below format(assuming 2 records present)
   ```
   [
  	  {
    		"id": 2,
    		"name": "Asus Vivobook 15",
    		"description": "Laptop",
    		"price": 26903,
    		"availibility": "In Stock"
  	  },
  	  {
    		"id": 3,
    		"name": "Asus Zenfone Max M1",
    		"description": "Smartphone",
    		"price": 9563,
    		"availibility": "In Stock"
  	  }
   ]
   ```
      
 
 3. Get a product by providing id
   -> Please use this endpoint : GET - /immutly/products/{product_id}
   -> This endpoint expectes a path variables of integer type
   -> depending on the provided path variable, relevent data will be displayed in below format
   A. Success:
   ```
   {
  	"id": 2,
  	"name": "Asus Vivobook 15",
  	"description": "Laptop",
  	"price": 26903,
  	"availibility": "In Stock"
   }
   ```
   
   B. ID not present:
   ```
   {
  	"timestamp": "2023-12-16T16:07:31.218+00:00",
  	"status": 500,
  	"error": "Internal Server Error",
  	"message": "Product not found with given id",
  	"path": "/immutly/products/1"
   }
   ```
3. Create a Product
   -> Please use this endpoint : POST - /immutly/products
   -> No argument/ path varibale expected
   -> depending on the provided payload, relevent data will be displayed in below format
   
   -> Scenario - 1 : all valid data
   
   Request: 
   ```
   {
  	"name": "Apple Macbook Air",
  	"description": "Laptop",
  	"price": 59256.00,
  	"availibility": "In Stock"
   }
   ```
   Response:
   ```
   {
  	"id": 36,
  	"name": "Apple Macbook Air",
  	"description": "Laptop",
  	"price": 59256,
  	"availibility": "In Stock"
   }
   ```
   -> Scenario - 2 : price negetive
   
   Request:
   ``` 
   {
  	"name": "Apple Macbook Air",
  	"description": "Laptop",
  	"price": -59256.00,
  	"availibility": "In Stock"
   }
   ```
   Response:
   ```
   {
  	"timestamp": "2023-12-16T16:13:03.121+00:00",
  	"status": 500,
  	"error": "Internal Server Error",
  	"message": "Product price is lesser than Zero",
  	"path": "/immutly/products"
   }
   ```
   -> Scenario - 3 : Missing Field data
   
   Request:
   ``` 
   {
  	"name": "Apple Macbook Air",
  	"description": "Laptop",
  	"price": -59256.00,
  	"availibility": "In Stock"
   }
   ```
   Response:
   ```
   {
  	"timestamp": "2023-12-16T16:13:03.121+00:00",
  	"status": 500,
  	"error": "Internal Server Error",
  	"message": "Field / Fields can't be empty",
  	"path": "/immutly/products"
   }
   ```

5. Update a Product by providing id
   -> Please use this endpoint : PUT - /immutly/products/{product_id}
   -> argument/ path varibale expected
   -> depending on the provided payload, relevent data will be displayed in below format
   -> Scenarios are simmilar to POST & GET By ID
   
   -> Scenario - 1: All valid data
   Request: product_id = 35(in argument)
   ```
   {
  	"name": "Apple Macbook Air",
  	"description": "Laptop",
  	"price": 54890.00,
  	"availibility": "In Stock"
   }
   ```
   Response:
   ```
   {
  	"id": 35,
  	"name": "Apple Macbook Air",
  	"description": "Laptop",
  	"price": 54890,
  	"availibility": "In Stock"
   }
   ```
   -> other scenarios like, negetive price / field missing repsonses are simmilar as of POST's
 
   -> Scenario - 2 : ID Not present
   
   Request : product_id : 37(not present in database table) and simmilar payload like scenario - 1
   
   Response:
   ``` 
   {
  	"timestamp": "2023-12-16T16:21:30.534+00:00",
  	"status": 500,
  	"error": "Internal Server Error",
  	"message": "Product not found with given id",
  	"path": "/immutly/products/37"
   }
   ```

7. Update a List of Product price updates, consisting of id & price pair
   -> Please use this endpoint : PUT - /immutly/products/price_update
   -> argument/ path varibale not expected
   -> depending on the provided payload, relevent data will be displayed in below format
   -> Scenarios are simmilar to GET All Products, except this time only those updated products are displayed
   -> Use this same endpoint to do a list of price update in ImmutelyClient as well
   
   -> Scenario - 1 : All valid id and price
   
   Request:
   ```
   [
  	{
    		"id": 2,
    		"price": 15000.00
  	},
  	{
    		"id": 3,
    		"price": 25000.00
  	}
   ]
   ```
   Response:
   ```
   [
  	{
    		"id": 2,
    		"name": "Asus Vivobook 15",
    		"description": "Laptop",
    		"price": 67000,
    		"availibility": "In Stock"
  	},
  	{
    		"id": 3,
    		"name": "Asus Zenfone Max M1",
   	 	"description": "Smartphone",
    		"price": 70000,
    		"availibility": "In Stock"
  	}
   ]
   ```
   -> Scenario 2 : Invalid id : "Product not found with given id", negetive price : "Product price is lesser than Zero"


9. Delete product by providing id
   -> Please use this endpoint : DELETE - /immutly/products/{product_id}
   -> argument/ path varibale expected
   -> depending on the provided arguement/ path variable, relevent product will be deleted
   -> If no id found matching provided argument, then exception thrown
   
   
C. Product price updates at http://localhost:8081/swagger-ui/index.html

1. Update a List of Product price updates, consisting of id & price pair
   -> Please use this endpoint : PUT - /immutly/products/price_update
   -> argument/ path varibale not expected
   -> depending on the provided payload, relevent data will be displayed in below format
   -> Scenarios are simmilar to GET All Products, except this time only those updated products are displayed
   -> Use this same endpoint to do a list of price update in ImmutelyClient as well
   
   -> Scenario - 1 : All valid id and price
   
   Request:
   ```
   [
  	{
    		"id": 2,
    		"price": 15000.00
  	},
  	{
    		"id": 3,
    		"price": 25000.00
  	}
   ]
   ```
   Response:
   ```
   [
  	{
    		"id": 2,
    		"name": "Asus Vivobook 15",
    		"description": "Laptop",
    		"price": 67000,
    		"availibility": "In Stock"
  	},
  	{
    		"id": 3,
    		"name": "Asus Zenfone Max M1",
   	 	"description": "Smartphone",
    		"price": 70000,
    		"availibility": "In Stock"
  	}
   ]
   ```
   -> Scenario 2 : Invalid id : "Product not found with given id", negetive price : "Product price is lesser than Zero"
     
   
THANK YOU.
