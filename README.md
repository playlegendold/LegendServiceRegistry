![Legend](https://static.playlegend.net/full-logo-stone-highres.png)

# LegendServiceRegistry
LegendServiceRegistry is a easy to use api for registering and accesing services on **Bukkit**, **BungeeCord** and other **independent platforms**.

## Project structure
LegendServiceRegistry is split into three modules.
- A platform independent module named legendserviceregistry-common
- A module specifically made for Bukkit named legendserviceregistry-bukkit
- A module for BungeeCord named legendserviceregistry-proxy

## Maven Repository
LegendServiceRegistry is available through our Maven [repository](https://repository.playlegend.net).
You have to replace **module** and **version** with your desired values. 

### Gradle (Kotlin)
```kotlin
repositories {
    maven("https://repository.playlegend.net/artifactory/opensource/")
}

dependencies {
    compileOnly("net.playlegend:legendserviceregistry-MODULE:VERSION:all")
}
```

### Maven
```xml
<repositories>
    <!-- This adds the Legend Maven repository to the build -->
    <repository>
        <id>legend-repo</id>
        <url>https://repository.playlegend.net/artifactory/opensource</url>
    </repository>
</repositories>

<dependency>
    <groupId>net.playlegend</groupId>
    <artifactId>legendserviceregistry-MODULE</artifactId>
    <version>VERSION</version>
    <classifier>all</classifier>
</dependency>
```

## Build a custom version
To build your own version of LegendServiceRegistry just execute the following command in project root.
```shell script
./gradlew shadowJar
```
You can find your artifacts in `/legendserviceregistry-MODULE/libs/`.

## A quick example
You need to define a service interface with your desired methods.
This interface needs to extend the Service interface provided by LegendServiceRegistry.
```java
public interface TestService extends Service {

  void doSomeCrazyStuff();

  void doSomeOtherCrazyStuff();

}
```

This class provides an implementation of your created interface. 
It's also possible to directly implement Service interface in this class and do it without an additional interface.
```java
public class TestServiceImplementation implements TestService {

  @Override
  public void doSomeCrazyStuff() {
    System.out.println("Hello World!");
  }

  @Override
  public void doSomeOtherCrazyStuff() {
    StringBuilder builder = new StringBuilder();
    builder.append("Hello");
    builder.append(" ");
    builder.append("World!");
    System.out.println(builder.toString());
  }

}
```

This is your main class. Additionally it's your ServiceAccessorOwner. 
The ServiceAccessorOwner is the owner of one or multiple ServiceAccessor. You can access your defined services by using ServiceAccessor class.
```java
public class TestApplication implements ServiceAccessorOwner {

  private final ServiceAccessor accessor = new ServiceAccessor(this);

  public static void main(final String[] args) {
     TestApplication testApplication = new TestApplication();
  }

  public TestApplication() {
    // Register your service
    accessor.register(TestService.class, new TestServiceImplementation());
  }

  @Override
  public String getName() {
    return "TestApplication";
  }
}
```

Contains a simple usage example for event-based systems.
```java
public class TestEventHandler {

  private final TestService testService;

  public TestEventHandler(final ServiceAccessor accessor) {
    // Access your created service via a ServiceAccessor
    testService = accessor.access(TestService.class);
  }

  public void onEvent() {
    // Call some cool methods from your service
    testService.doSomeCrazyStuff();
  }
}
```
