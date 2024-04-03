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

### Back end
Pré-requisitos: Java 17

### Enpoints e considerações
Listarei abaixo os endpoints para criação, consulta e edição de usuários e endereços.

```java
/users // GET E POST
/users/{userId} // GET e PUT
/users/{userId}/address // GET e POST
/users/{userId}/address/{addressId} // GET e PUT
```

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
