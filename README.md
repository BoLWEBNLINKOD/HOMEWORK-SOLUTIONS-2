# HOMEWORK-SOLUTIONS-2
**1. `MUDController.java`**  
- import java.util.Scanner; — импортирует класс для чтения пользовательского ввода.  
- public class MUDController { — объявляет основной контроллер игры.  
- private Player player; — хранит игрока.  
- private boolean running; — управляет игровым циклом.  
- public MUDController(Player player) { — конструктор, инициализирует игрока и включает цикл.  
- public void runGameLoop() { — запускает основной игровой цикл.  
- Scanner scanner = new Scanner(System.in); — считывает ввод пользователя.  
- while (running) { — цикл игры до выхода.  
- handleInput(input); — обрабатывает введённую команду.  
- private void handleInput(String input) { — разбирает и выполняет команду.  
- String[] parts = input.trim().split(" ", 2); — делит ввод на команду и аргумент.  
- switch (command) { — выбирает действие по команде.  
- case "look": lookAround(); — описывает текущую комнату.  
- case "move": move(argument); — двигает игрока.  
- case "pick": — проверяет команду "pick up".  
- case "inventory": checkInventory(); — показывает инвентарь.  
- case "help": showHelp(); — показывает команды.  
- case "quit": running = false; — завершает игру.  
- default: System.out.println("Unknown command."); — для неизвестных команд.  

**2. `Player.java`**  
- public class Player { — класс игрока.  
- private Room currentRoom; — текущая комната игрока.  
- private List<Item> inventory; — инвентарь.  
- public Player(Room startingRoom) { — конструктор, задаёт начальную комнату.  
- public Room getCurrentRoom() { — возвращает текущую комнату.  
- public void setCurrentRoom(Room room) { — меняет комнату игрока.  
- public void addItem(Item item) { — добавляет предмет в инвентарь.  
- public List<Item> getInventory() { — возвращает инвентарь.  

**3. `Room.java`**  
- public class Room { — класс комнаты.  
- private String name; — имя комнаты.  
- private String description; — описание комнаты.  
- private Map<String, Room> connections; — направляет к другим комнатам.  
- private Map<String, Item> items; — предметы в комнате.  
- public Room(String name, String description) { — конструктор комнаты.  
- public void connectRoom(String direction, Room room) { — связывает комнаты.  
- public Room getConnectedRoom(String direction) { — возвращает комнату по направлению.  
- public void addItem(Item item) { — добавляет предмет в комнату.  
- public Item getItem(String itemName) { — находит предмет по имени.  
- public void removeItem(Item item) { — удаляет предмет.  
- public String describe() { — возвращает описание комнаты и предметов.  

**4. `Item.java`**  
- public class Item { — класс предмета.  
- private String name; — имя предмета.  
- public Item(String name) { — конструктор предмета.  
- public String getName() { — возвращает имя предмета.  

**5. `Main.java`**  
- public class Main { — Главный класс для входа в игру.  
- public static void main(String[] args) { — запускает игру.  
- Room room1 = new Room(...); — создаёт комнату 1.  
- Room room2 = new Room(...); — создаёт комнату 2.  
- room1.connectRoom("forward", room2); — соединяет комнаты.  
- room1.addItem(new Item("sword")); — кладёт меч в комнату.  
- Player player = new Player(room1); — создаёт игрока.  
- MUDController controller = new MUDController(player); — создаёт контроллер.  
- controller.runGameLoop(); — запускает игру.  
