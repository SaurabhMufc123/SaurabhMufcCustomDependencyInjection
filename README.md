# SaurabhMufcCustomDependencyInjection

A project which demonstrates how you can build a custom dependency injection. 
This project handles 2 types of scopes for dependecies:
1) Singleton
2) Prototype

Strategy design pattern is used in Unit Testing for the Calculator Service examle.

### How you can use

##### 1. You have to define how you want to map interfaces to their implementations.

##### 2. Add **@SingletonInject** or **@PrototypeInject** annotation to constructor or field to specify the scope.

##### 3. Use **CustomDi** to get your injected class.

### Limitations:

##### 1. Unit testing for Singleton scope for depedency injection missing.