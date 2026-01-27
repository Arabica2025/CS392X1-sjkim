Java supports **two** kinds of **Polymorphism** (code reuse)

1. OOP: inheritance polymorphism
2. **Generics(FP)** / Templates(C++)

# Recursion

- deep recursion
- tail-recursion: can be translated into loop directly
    - using tail-recursion: as efficient as the loop implementation
    - But Java does not do tail-recursion automatically: I have to make this manually

## Example
Computing Factorials:
```LaTex
0! = 1, 1! = 1, 2! = 1 * 2 = 2, 3! = 1 * 2 * 3 = 6 ...
```
1. Base case:
```Python
fact(0) = 1
```

2. Recursive call:
```Python
if n > 0:
    fact(n) = n * fact(n-1)
```

* example code (Python); if-statement
```Python
def fact(n):
    if (n > 0):
        return n * fact(n-1)
    else:
        return 1
```

* improved-version? (if-expression)
```Python
def fact(c):
    return 0 if (c==0) else n * fact(n-1)
```

* if-expression in Java
```Java
(n == 0 ? 0 : n * fact(n-1))
```

* another version of recurion
loop(3,1) -> loop(2,3)
          -> loop(1,6)
          -> loop(0,6)
          -> 6
```Python
def fact(n):
    def loop(n, r):
        if (n>0):
            return (loop(n-1), n*r)
        else:
            return r
    return loop(n,1)
```

In this version:
```Python
if (n>0):
    return (loop(n-1), n*r)
```
This is called **tail-recursive** call.

Parametric Polymorphism (Generics)



