# API Restful com Spring Boot e MongoDB
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/weslleycabral/cadastro_pessoas/blob/main/LICENSE) 

## Sobre o projeto
Esta aplicação faz parte do processo seletivo da Attus e tem como objetivo criar, editar e consultar usuários e endereços, com a possibilidade de indicar o endereço principal. 

## Modelo conceitual
![Modelo Conceitual](https://github.com/weslleycabral/assets/blob/main/DiagramaUML.png)

## Tecnologias utilizadas
### Back end
- Java
- Spring Boot
- H2 Database Engine
- Maven

## Como executar o projeto

### Pré-requisitos
Java 17

### Enpoints e considerações
Listarei abaixo os endpoints para criação, consulta e edição de usuários e endereços.

```java
/users // GET E POST
/users/{userId} // GET e PUT
/users/{userId}/address // GET e POST
/users/{userId}/address/{addressId} // GET e PUT
```

### Decisões arquiteturais
- O objetivo do projeto é criar, editar e consultar usuários e endereços. Como os endereços são criados por usuários e acessados através deles, decidi criar apenas um controlador, o UserResource. Isso ocorre porque é necessário definir o usuário antes de solicitar os endereços associados a ele.
- Para criar um novo endereço é necessário fazer um ```POST``` nesse endpoint ```/users/{userId}/address``` com o JSON abaixo

  ```
  {
    street: String,
    cep: String,
    number: String,
    city: Integer,
    isPrincipal: Boolean
  }
  ```
  
  Os campos ```"street", "cep" e "number"``` são editáveis, porém a cidade é pré-cadastrada no banco e seu ```ID``` precisa ser inserido em ```"city"```. Para fins de teste, criei cidades no banco e abaixo deixarei uma breve lista dos números que você pode usar:

  | ID | STATE_ID |       NAME       |
  |---|----------|-----------------|
  | 1 |    1     |     São Paulo   |
  | 2 |    2     | Juazeiro do Norte |
  | 3 |    2     |     Fortaleza   |
  | 4 |    3     |     Curitiba    |
  | 5 |    3     |    Paranaguá    |
  | 6 |    1     |      Santos     |

  Deixarei abaixo um exemplo de como criar um endereço:

  ```java
  Method: POST
  Route: /users/{userId}/address
  Body: JSON
  
  Request Body Example:
  {
    "street": "Super Mario Street",
    "cep": "101010-100",
    "number": "7",
    "city": 1,
    "isPrincipal": true
  }
  ```
  
  ```isPrincal``` pode ser ```true``` ou ```false```, define se o endereço é o principal ou não.

- Caso deseje editar um endereço criado, é necessário indicar o usuário e o endereço, e enviar um JSON contendo apenas os campos que deseja editar. Abaixo, deixarei um exemplo para modificar o número da casa e definir como endereço principal:

  ```java
  Method: POST
  Route: /users/{userId}/address/{addressId}
  Body: JSON
  
  Request Body Example:
  {
    "number": "New house number",
    "isPrincipal": true
  }
  sPrincipal": true
  }
  ```

### Clonar projeto
```bash
# clonar repositório
git clone https://github.com/weslleycabral/cadastro_pessoas.git
```
## Agradecimentos

Agradeço à Attus pela oportunidade de participar deste processo seletivo. Sem dúvida, os desafios propostos me permitiram aprimorar a qualidade do meu código e aprender melhores práticas.

## Autor

Weslley Juan Cabral Landim

- Linkedin: [Weslley Cabral](https://www.linkedin.com/in/weslley-cabral-890bb629a/)
- Email: weslley.tec@hotmail.com
- Contato: [Whatsapp](https://api.whatsapp.com/send?phone=5541996111543)
