#  People Manegemant 

#### ✒️Autor - [Victor Henrique](https://www.linkedin.com/in/viccttor/)

##  🛠️Technologies and Tools

* Java 11
* Spring Boot
* JPA Hibernate
* H2
* Swagger
* Lombok
* JUnit4
* Hamcrest
* Intellij

##  Functionalities

* Person
    * Create a person
    * Edit a person
    * Consult a person
    * List people
    * Inform the person's main address
  
* Address
    * List person addresses
    * Create address for person

## Anchors
  * [Class Diagram](#ancora0)
  * [API Structure](#ancora1)
    * [Address Controller](#ancora2)
    * [Person Controller](#ancora3)
  * [Access](#ancora4)
    * [Local](#ancora5)
  * [Using all API requests](#ancora7)
    * [Step One - Register person](#ancora8)
    * [Step Two - List people](#ancora9)
    * [Step Three - Register new address for the person with ID 1](#ancora10)
    * [Step Four - List person addresses from ID 1](#ancora11)
    * [Step Five - Change person's primary address](#ancora12)
  * [Errors Handled](#ancora13)
    * [Address](#ancora14)
    * [Person](#ancora15)
  * [Unitary tests](#ancora16)
  * [Test Answers](#ancora17)
    

<a id="ancora0"></a>
## 📝 Class diagram

![diagram](https://github.com/viccttor/PeopleManegemant/blob/main/img/diagram.png)

<a id="ancora1"></a>
## API structure

<a id="ancora2"></a>
### Address Controller


|Rest | URL                         |Função |
|------ |-----------------------------| ------- |
|GET   | /findAdresses               | List person addresses |
|POST   | /newAddress | Create address for person |

<a id="ancora3"></a>
### Person Controller

| Rest  | URL             |Função |
|-------|-----------------| ------- |
| GET   | /findPerson     | Consult a person |
| GET   | /listPeople     | List people |
| POST  | /newPerson      | Create a person |
| PATCH | /setMainAddress | Inform the person's main address |
| PUT   | /updatePerson   | Edit a person |

<a id="ancora4"></a>
## Access

<a id="ancora5"></a>
### URL Local
http://localhost:8080/peopleManagement/swagger-ui/index.html#/

<a id="ancora7"></a>
## Using all API requests

<a id="ancora8"></a>
### Step One - Register person

* No Post method - /newPerson
<p>Example of JSON:</p>

```
{
  "addressDTO": {
    "city": "Carpina",
    "number": "0",
    "personID": 0,
    "street": "Rua Central",
    "zipCode": "55819000"
  },
  "birthDate": "2000-01-01",
  "name": "Pessoa Teste 1"
}
```
* Expected
<p>Code HTPP: 201</p>
<p>Response body:</p>

```
{
  "id": 1,
  "name": "Pessoa Teste 1",
  "birthDate": "2000-01-01",
  "address": {
    "id": 1,
    "street": "Rua Central",
    "zipCode": "55819000",
    "number": "0",
    "city": "Carpina",
    "personID": 1
  }
}
```

#### Register secong person
<p>Example of JSON:</p>

```
{
  "addressDTO": {
    "city": "Carpina",
    "number": "10",
    "personID": 0,
    "street": "Rua sul",
    "zipCode": "55818000"
  },
  "birthDate": "2002-01-01",
  "name": "Pessoa Teste 2"
}
```
* Expected
<p>Code HTPP: 201</p>
<p>Response body:</p>

```
{
  "id": 2,
  "name": "Pessoa Teste 2",
  "birthDate": "2002-01-01",
  "address": {
    "id": 2,
    "street": "Rua sul",
    "zipCode": "55818000",
    "number": "10",
    "city": "Carpina",
    "personID": 2
  }
}
```
<a id="ancora9"></a>
### Step Two - List people

* No GET method - /listPeople

* Expected
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
[
  {
    "id": 1,
    "name": "Pessoa Teste 1",
    "birthDate": "2000-01-01",
    "address": {
      "id": 1,
      "street": "Rua Central",
      "zipCode": "55819000",
      "number": "0",
      "city": "Carpina",
      "personID": 1
    }
  },
  {
    "id": 2,
    "name": "Pessoa Teste 2",
    "birthDate": "2002-01-01",
    "address": {
      "id": 2,
      "street": "Rua sul",
      "zipCode": "55818000",
      "number": "10",
      "city": "Carpina",
      "personID": 2
    }
  }
]
```

<a id="ancora10"></a>
### Step Three - Register new address for the person with ID 1

* No Post method - /newAddress
<p>Example of JSON:</p>

```
{
  "city": "Carpina",
  "number": "20",
  "personID": 1,
  "street": "Rua sul",
  "zipCode": "55819000"
}
```

* Expected
<p>Code HTPP: 201</p>
<p>Response body:</p>

```
{
  "id": 3,
  "street": "Rua sul",
  "zipCode": "55819000",
  "number": "20",
  "city": "Carpina",
  "personID": 1
}
```

#### Register new address for the person with ID 2
<p>Example of JSON:</p>

```
{
  "city": "Carpina",
  "number": "100",
  "personID": 2,
  "street": "Rua Norte",
  "zipCode": "55818000"
}
```

* Expected
<p>Code HTPP: 201</p>
<p>Response body:</p>

```
{
  "id": 4,
  "street": "Rua Norte",
  "zipCode": "55818000",
  "number": "100",
  "city": "Carpina",
  "personID": 2
}
```

<a id="ancora11"></a>
### Step Four - List person addresses from ID 1

* No GET method - /findAddress
<p>Person ID parameter</p>

```
PersonID - 1
```

* Expected
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
[
  {
    "id": 1,
    "street": "Rua Central",
    "zipCode": "55819000",
    "number": "0",
    "city": "Carpina",
    "personID": 1
  },
  {
    "id": 3,
    "street": "Rua sul",
    "zipCode": "55819000",
    "number": "20",
    "city": "Carpina",
    "personID": 1
  }
]
```

#### List person addresses from ID 2

<p>Person ID parameter</p>

```
PersonID - 2
```

<p>Expected</p>
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
[
  {
    "id": 2,
    "street": "Rua sul",
    "zipCode": "55818000",
    "number": "10",
    "city": "Carpina",
    "personID": 2
  },
  {
    "id": 4,
    "street": "Rua Norte",
    "zipCode": "55818000",
    "number": "100",
    "city": "Carpina",
    "personID": 2
  }
]
```

<a id="ancora12"></a>
### Step Five - Change person's primary address

* No GET method - /findPerson

<p>Person ID parameter</p>

```
PersonID - 1
```

<p>Expected</p>
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
{
  "id": 1,
  "name": "Pessoa Teste 1",
  "birthDate": "2000-01-01",
  "address": {
    "id": 1,
    "street": "Rua Central",
    "zipCode": "55819000",
    "number": "0",
    "city": "Carpina",
    "personID": 1
  }
}
```

<p>Person ID parameter</p>

```
PersonID - 2
```

<p>Expected</p>
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
{
  "id": 2,
  "name": "Pessoa Teste 2",
  "birthDate": "2002-01-01",
  "address": {
    "id": 2,
    "street": "Rua sul",
    "zipCode": "55818000",
    "number": "10",
    "city": "Carpina",
    "personID": 2
  }
}
```
<p>OBS: Both have the address that was registered. Let's put the registered address as the main one.</p>

* No PATCH method - /setMainAddress

<p>Person's new address ID</p>

```
newAddressID - 3
```

<p>Person ID parameter</p>

```
PersonID - 1
```

<p>Expected</p>
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
{
  "id": 1,
  "name": "Pessoa Teste 1",
  "birthDate": "2000-01-01",
  "address": {
    "id": 3,
    "street": "Rua sul",
    "zipCode": "55819000",
    "number": "20",
    "city": "Carpina",
    "personID": 1
  }
}
```

<p>Person's new address ID</p>

```
newAddressID - 4
```

<p>Person ID parameter</p>

```
PersonID - 2
```

<p>Expected</p>
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
{
  "id": 2,
  "name": "Pessoa Teste 2",
  "birthDate": "2002-01-01",
  "address": {
    "id": 4,
    "street": "Rua Norte",
    "zipCode": "55818000",
    "number": "100",
    "city": "Carpina",
    "personID": 2
  }
}
```

#### Check the changes by getting all registered people

* No GET method - /listPeople

<p>Expected</p>
<p>Code HTPP: 200</p>
<p>Response body:</p>

```
[
  {
    "id": 1,
    "name": "Pessoa Teste 1",
    "birthDate": "2000-01-01",
    "address": {
      "id": 3,
      "street": "Rua sul",
      "zipCode": "55819000",
      "number": "20",
      "city": "Carpina",
      "personID": 1
    }
  },
  {
    "id": 2,
    "name": "Pessoa Teste 2",
    "birthDate": "2002-01-01",
    "address": {
      "id": 4,
      "street": "Rua Norte",
      "zipCode": "55818000",
      "number": "100",
      "city": "Carpina",
      "personID": 2
    }
  }
]
```

<a id="ancora13"></a>
## Errors Handled

<a id="ancora14"></a>
### Address

* No Post method - /newAddress

<a id="ancora15"></a>
#### Address registration for an unregistered person

<p>Example of JSON:</p>

```
{
  "city": "Teste 3",
  "number": "0",
  "personID": 111,
  "street": "Rua Teste",
  "zipCode": "55818000"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors0](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors0.png)

<a id="ancora16"></a>
#### Letters no zipCode field

<p>Example of JSON:</p>

```
{
  "city": "Teste 3",
  "number": "0",
  "personID": 1,
  "street": "Rua Teste",
  "zipCode": "aaa"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors1](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors1.png)

<a id="ancora17"></a>
#### Letters no number field

<p>Example of JSON:</p>

```
{
  "city": "Teste 3",
  "number": "aa",
  "personID": 1,
  "street": "Rua Teste",
  "zipCode": "55818000"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors2](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors2.png)

<a id="ancora18"></a>
#### Null fields: city, number, street e zipCode

<p>Example of JSON:</p>

```
{
  "city": null,
  "number": "aa",
  "personID": 1,
  "street": "Rua Teste",
  "zipCode": "55818000"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors3](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors3.png)

<a id="ancora19"></a>
#### Null field: personID

<p>Example of JSON:</p>

```
{
  "city": "city teste",
  "number": "aa",
  "personID": null,
  "street": "Rua Teste",
  "zipCode": "55818000"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors4](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors4.png)

<a id="ancora19"></a>
#### Null field: personID

<p>Example of JSON:</p>

```
{
  "city": "city teste",
  "number": "11",
  "personID": null,
  "street": "Rua Teste",
  "zipCode": "55818000"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors0](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors0.png)

<a id="ancora20"></a>
#### Validation of the number of characters in the fields: city, number, personID, street and zipCode

<p>Example of JSON:</p>

```
{
  "city": "city teste",
  "number": "012345",
  "personID": 1,
  "street": "Rua Teste",
  "zipCode": "55818000"
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors6](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors6.png)

<a id="ancora21"></a>
## Person

### OBS
<p>In the registration of people, there is also validation of the address</p>

<a id="ancora22"></a>
#### Null fields: name and birthDate

<p>Example of JSON:</p>

```
{
  "addressDTO": {
    "city": "Carpina",
    "number": "0",
    "personID": 0,
    "street": "Rua Central",
    "zipCode": "55819000"
  },
  "birthDate": "2000-01-01",
  "name": null
}
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors6](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors6.png)

<a id="ancora22"></a>
### Some One Else is address

<p>Person's new address ID</p>

```
newAddressID - 2
```

<p>Person ID parameter</p>

```
PersonID - 1
```

<p>Expected</p>
<p>Code HTPP: 400</p>
<p>In the API</p>

![errors7](https://github.com/viccttor/PeopleManegemant/blob/main/img/errors/errors7.png)

<a id="ancora24"></a>
## Unitary tests

![unitaryTests](https://github.com/viccttor/PeopleManegemant/blob/main/img/unitaryTests.png)


<a id="ancora17"></a>
### Test Answers

```
Qualidade de código

    1. Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

R – Avalio os requisitos e desenvolvo a implementação seguindo as boas práticas
do código limpo:

-  TDD 
- Legibilidade e formatação do Código
- Métodos e classes com apenas uma função
- Nomes de métodos e variáveis autoexplicativos
- Métodos independentes
- Tratamentos de erros


    2. Em qual etapa da implementação você considera a qualidade de software?

R – Considero em primeiro plano, literalmente é a primeira coisa a ser pensada. Pois um código bem escrito, evita/facilita manutenção futura.
```
