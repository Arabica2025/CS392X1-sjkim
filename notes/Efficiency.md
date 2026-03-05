# Big-O notation

e.g.

Let $f$ and $g$ be two natural number functions 
 - $f: ℕ \rightarrow ℕ$
 - $g: ℕ \rightarrow ℕ$

$f(n)$ is $O(g(n))$ if:
$$\begin{aligned}
f(n) ≤ K \times g(n) \space \text{when n is "big enough"} \\
K = \text{constant}
\end{aligned}
$$

1. O(1): constant

e.g.
- $1000$: constant; therefore it is $O(1)$
- $n$: not constant; therefore it is not $O(1)$ -> linear

2. $O(n^2)$: quadratic

e.g.
- $\frac {n(n+1)}{2}$ is $O(n^2)$

3. Power functions
- grows exponentially

e.g.
- $f(n) = 2^n$
    - $f(10) = 2^{10} = 1024 \approx 10^3$
    - $f(20) = 2^{20} \approx (10^3)^2 = 1M \text{(Million)}$

# Soft-O Notation
- Ignore log factors

### Notation: $\tilde{O}(f(n))$

## Time Analysis for binary search
### e.g.
Let $T(n)$ be the time for binary search on an array of size $n$
- the number of binary digits used to represent number: $log_{10} {n} \rightarrow O(log n)$

$$\begin{aligned}
T(n) = T(\frac n {2}) + O(1) \\
T(n) = O(log n) = \tilde O(1)
\end{aligned}
$$

## 1. Big O notation problem

$f(n)$ is $O(n^2)$ -> it only represents the **upper bound**.<br>
i.e. $f(n) ≤ K \times n^2$

## 2. Soft O notation
$\tilde O(n^2)$