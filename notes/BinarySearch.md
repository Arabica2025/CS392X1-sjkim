# Array
```Java
A[lb, ub]
```
- lb: lower bound
- ub: upper bound

Sqr Brackets: inclusive
Parathesis: exclusive

## `final` keyword
- a non-access modifier used to restrict modification of variables, methods, and classes

```Java
x = x + 1
```
- x on right: address of the variable
- x on left: constant to calculate

`int`: fixed non-class data type (int32 default)

# javac: java compiler inside JVM

- package cannot be found issue resolution: 
1. check if you installed the package
2. if yes, add a path to the package `.jar` file and let javac know the path to look at.

Java code execution

1. Java code -> javac (compiler) convert it into JVM code-> JVM -> interpreter (executes the code)