# CS392-2026-Spring-sjkim
BU CS392, Spring, 2026

## Mirror the class repo

Please create a private repository that mirrors this one and update
frequently.

Step 1:

Clone the class repository and go to the cloned repo:

```
git clone --mirror https://github.com/hwxi/CS392-2026-Spring.git
cd CS392-2026-Spring.git
```

Step 2:

Mirror-push the class repo into my CS392X1 repo:

```
git push --mirror https://github.com/Arabica2025/CS392X1-sjkim.git
```

Go up to the parent directory and make a normal working clone of my own repo:
```
cd ..
git clone https://github.com/Arabica2025/CS392X1-sjkim.git
cd CS392X1-sjkim
```
Step 4:

Add upstream pointing to the class repo:
```
git remote add upstream https://github.com/hwxi/CS392-2026-Spring.git
git remote -v
```

Step 3:

Sync with the class repo *frequently*

merge upstream/main into your main:

```
git checkout main
git fetch upstream
git merge upstream/main
git push origin main
```
