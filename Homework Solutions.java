import java.util.Scanner;

public class MUDController {
    private Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the MUD! Type 'help' for a list of commands.");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private void handleInput(String input) {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                if (argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("Invalid command. Did you mean 'pick up <item>'?");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println(currentRoom.describe());
    }

    private void move(String direction) {
        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = currentRoom.getConnectedRoom(direction);

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You move " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(itemName);

        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You pick up the " + item.getName() + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }

    private void checkInventory() {
        System.out.println("You are carrying:");
        for (Item item : player.getInventory()) {
            System.out.println("- " + item.getName());
        }
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look");
        System.out.println("move <forward|back|left|right>");
        System.out.println("pick up <itemName>");
        System.out.println("inventory");
        System.out.println("help");
        System.out.println("quit/exit");
    }
} 

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Item> inventory;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> connections;
    private Map<String, Item> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.connections = new HashMap<>();
        this.items = new HashMap<>();
    }

    public void connectRoom(String direction, Room room) {
        connections.put(direction, room);
    }

    public Room getConnectedRoom(String direction) {
        return connections.get(direction);
    }

    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

    public Item getItem(String itemName) {
        return items.get(itemName.toLowerCase());
    }

    public void removeItem(Item item) {
        items.remove(item.getName().toLowerCase());
    }

    public String describe() {
        StringBuilder desc = new StringBuilder();
        desc.append("Room: ").append(name).append("\n").append(description).append("\nItems here: ");
        if (items.isEmpty()) {
            desc.append("none");
        } else {
            desc.append(String.join(", ", items.keySet()));
        }
        return desc.toString();
    }
}

public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Room startRoom = new Room("Start Room", "A dimly lit stone chamber.");
        Room nextRoom = new Room("Hallway", "A long, narrow hallway.");
        startRoom.connectRoom("forward", nextRoom);

        Item sword = new Item("sword");
        startRoom.addItem(sword);

        Player player = new Player(startRoom);
        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}
