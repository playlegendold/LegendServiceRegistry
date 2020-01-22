# LegendServiceRegistry
This is a simple service-pattern registry for java(bukkit/bungee/standalone)

## Build
```
./gradlew shadowJar
```

## Usage
#### Bukkit
```
public class BukkitLegendServiceRegistryPlugin extends LegendBukkitPlugin {

    @Override
    public void onEnable() {
        // get a service
        this.getAccessor().access(ExampleService.class).doStuff();

        // register a service
        this.getAccessor().register(ExampleService.class, LegendExampleService.class);

        // list all dependet services of this plugin
        this.getAccessor().getDependentServices();


        Player player = null;
        Inventory inventory = this.getAccessor().access(InventoryService.class).loadInventory(player);
    }

}
```