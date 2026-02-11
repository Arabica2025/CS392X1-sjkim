def fact(n):
    def loop(n,r):
        if (n > 0):
            return loop(n-1, n*r)
        else:
            return r
    return loop(n,1)

print(fact(3))