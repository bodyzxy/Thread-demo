# CompletableFuture（异步线程）常用方法

_获取结果_

```
public T get()
public T get(long timeout,TimeUnit unit)
public T join() --->和get一样的作用，只是不需要抛出异常
public T getNow(T valuelfAbsent) --->计算完成就返回正常值，否则返回备胎值（传入的参数），立即获取结果不阻塞

```

_主动触发计算_

```

public boolean complete(T value) ---->是否打断get方法立即返回括号值

```

_对计算结果进行处理--CompletableFutureResult.java_

```angular2html

henApply --->计算结果存在依赖关系，这两个线程串行化---->由于存在依赖关系（当前步错，不走下一步），当前步骤有异常的话就叫停
handle --->计算结果存在依赖关系，这两个线程串行化---->有异常也可以往下走一步

```

_对计算结果进行消费--CompletableFutureApi2Demo.java_

```angular2html
接受任务的处理结果，并消费处理，无返回结果
thenAccept
```

_对比补充_

```angular2html
thenRun(Runnable runnable) :任务A执行完执行B，并且不需要A的结果
thenAccept(Consumer action): 任务A执行完执行B，B需要A的结果，但是任务B没有返回值
thenApply(Function fn): 任务A执行完执行B，B需要A的结果，同时任务B有返回值
```

_对计算速度进行选用--CompletableFutureApiDemo.java_

```angular2html
谁快用谁
applyToEither
```

_对计算结果进行合并--CompletableFutureApi3Demo.java_

```angular2html
两个CompletableStage任务都完成后，最终能把两个任务的结果一起交给thenCombine来处理
先完成的先等着，等待其他分支任务
```

# LockSupport类中的park等待和unpark唤醒---LockSupportDemo.java

_是什么_

LockSupport用于创建锁和其他同步类的基本线程阻塞原语

LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，每个线程都有一个许可（Permit），许可证只能有一个，累加上限是1。

_主要方法_

    阻塞: Peimit许可证默认没有不能放行，所以一开始调用park()方法当前线程会阻塞，直到别的线程给当前线程发放peimit，park方法才会被唤醒。
    park/park(Object blocker)-------阻塞当前线程/阻塞传入的具体线程

    唤醒: 调用unpack(thread)方法后 就会将thread线程的许可证peimit发放，会自动唤醒park线程，即之前阻塞中的LockSupport.park()方法会立即返回。
    unpark(Thread thread)------唤醒处于阻塞状态的指定线程


_和wait的区别_

    wait与notify执行时必须保证wait比notify先执行，如果notify比wait先执行就会导致wait休眠状态的线程无法唤醒；而park和unpark则不会
    我们可以先调用unpark来释放一个许可证，然后park去获取一个；另额park方法并不会释放锁。

_重点_

    LockSupport是用来创建锁和其他同步类的基本线程阻塞原语，所有的方法都是静态方法，可以让线程再任意位置阻塞，阻塞后也有对应的唤醒方法。归根结底，LockSupport时调用Unsafe中的native代码
    LockSupport提供park()和unpark()方法实现阻塞线程和解除线程阻塞的过程，LockSupport和每个使用它的线程都有一个许可（Peimit）关联，每个线程都有一个相关的permit，peimit最多只有一个，重复调用unpark也不会积累凭证。
    形象理解：线程阻塞需要消耗凭证（Permit），这个凭证最多只有一个
    当调用park时，如果有凭证，则会直接消耗掉这个凭证然后正常退出。如果没有凭证，则必须阻塞等待凭证可用；
    当调用unpark时，它会增加一个凭证，但凭证最多只能有1各，累加无效。

_面试_

    为什么LockSupport可以突破wait/notify的原有调用顺序？
    因为unpark获得了一个凭证，之后再调用park方法，就可以名正言顺的凭证消费，故不会阻塞，先发放了凭证后续可以畅通无阻。
    为什么唤醒两次后阻塞两次，但最终结果还会阻塞线程？
    因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，只会增加一个凭证，而调用两次park却需要消费两个凭证，证不够，不能放行。

# Callable接口的实现

```angular2html
1.定义一个线程任务类实现callable接口，申明线程执行结果类型。
2.重写线程任务类的call方法，这个方法可以直接返回执行结果。
3.创建一个Callable线程任务对象。
4.把Callable线程任务对象包装成一个未来任务对象。
5.把未来任务对象包装成线程对象。
6.调用线程start()方法启动线程。
```

_FutureTask方法_

```angular2html
FutureTask是Runnable对象，因为Thread类只能执行Runnable实例的任务对象，所以需要将Callable封装成未来任务对象。

优点：同 Runnable，并且能得到线程执行的结果
缺点：编码复杂

get() 线程会阻塞等待任务执行完成
run() 执行完后会把结果设置到 FutureTask 的一个成员变量，get() 线程可以获取到该变量的值
```

