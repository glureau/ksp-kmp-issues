

```
    ./gradlew compileKotlinJs --rerun-tasks
```

This code isn't safe at all:
- parsing is shitty, you should use a complete syntax parser
- compiler code is only giving the basics for creating a KSP project and parse the content of a method
- for generating code, I recommend using kotlin-poet from Square

Have fun!

