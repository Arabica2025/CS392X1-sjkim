```Java
package MyLibrary.MyQueue;

import java.util.util.Consumer;
import java.util.function.BiConsumer;

interface MyQueue<T>{
    T top();

    @Override
    public T deque$raw(){
    T itm = itms[frst];
    nitm -= 1;
    frst = (frst + 1) % itms.length; 
    // this is circular array
    return;
    }


}
```
how to share libraries or import and etc
1) create abstract class<br>
`abstract MyQueueBase<T>`<br>
- use `@Override` to override methods in abstract class to 
differentiate the original and the temporal overriden version.

1. Java Style Coding: Exception handling
2. Monadic style coding: use special value to indicate error
    - e.g. if a key is not found, then return `-1` or `null`