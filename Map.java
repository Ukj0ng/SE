import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Map{
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int LENGTH = 20;
    static final int WIDTH = 40;

    Cell[][] map = new Cell[LENGTH][WIDTH];
    int numberOfPlayer;
    Player[] players = new Player[4];

    void setPlayer(){
        Scanner scan = new Scanner(System.in);
        System.out.printf("플레이할 플레이어 수를 입력하세요(2인 ~ 4인) : ");
        numberOfPlayer = scan.nextInt();
        scan.nextLine();
        int id = 65;
        for(int i = 0; i < numberOfPlayer; i++){
            players[i] = new Player(Character.toString(id++));
        }
    }

    void setMap(){
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                map[i][j] = new Cell();
            }
        }
    }

    void loadMap() throws IOException{
        BufferedReader reader = new BufferedReader(
            new FileReader("C:\\Users\\xb253\\OneDrive\\바탕 화면\\소프트웨어공학 텀프로젝트\\defaultmap.txt")
        );
        
        String str;
        int width = 3;
        int length = 3;
        while((str = reader.readLine()) != null){
            Cell cell = new Cell(str);
            map[length][width].setScore(cell.getScore());
            map[length][width].setType(cell.getType());
            map[length][width].setMoveable(cell.getArrayMoveable());
            map[length][width].setNextDirect(cell.getArrayNextDirect());
            map[length][width].setLength(length);
            map[length][width].setWidth(width);

            if (cell.getType().equals("B")){
                boolean[] moveable = new boolean[4];
                boolean[] nextDirect = new boolean[4]; 
                for (int i = 0; i < moveable.length; i++){
                    if(i == 0 || i == 1)
                        moveable[i] = false;
                    else
                        moveable[i] = true;
                    if(i == nextDirect.length)
                        nextDirect[i] = true;
                    else
                        nextDirect[i] = false;
                }
                map[length][width + 1].setScore(0);
                map[length][width + 1].setType("=");
                map[length][width + 1].setMoveable(moveable);
                map[length][width + 1].setNextDirect(nextDirect);
                map[length][width + 1].setLength(length);
                map[length][width + 1].setWidth(width + 1);
            }


            if(cell.getNextDirect() == UP)
                length--;
            else if(cell.getNextDirect() == DOWN)
                length++;
            else if(cell.getNextDirect() == LEFT)
                width--;
            else if(cell.getNextDirect() == RIGHT)
                width++;
        }
        
        reader.close();
    }

    void showMap(){
        for(int i = 0; i< LENGTH; i++){
            for(int j = 0; j < WIDTH; j++){
                if (!map[i][j].getType().equals("X"))
                    System.out.printf("%2s", map[i][j].getType());
                else
                    System.out.printf("  ");
            }
            System.out.println();
        }
    }
    
    void movePlayer(Map map, String[] moveString, Player player){
        for (int i = 0; i < moveString.length; i++){
            if (moveString[i].equals("U")){
                player.setCurrentLength(player.getCurrentLength() - 1);
            }
            else if(moveString[i].equals("D")){
                player.setCurrentLength(player.getCurrentLength() + 1);
            }
            else if(moveString[i].equals("L")){
                player.setCurrentWidth(player.getCurrentWidth() - 1);
            }
            else if(moveString[i].equals("R")){
                player.setCurrentWidth(player.getCurrentWidth() + 1);
            }
            if (map.map[player.getCurrentLength()][player.getCurrentWidth()].getType().equals("="))
                player.addNumberOfBridgeCard();
            else if(map.map[player.getCurrentLength()][player.getCurrentWidth()].getType().equals("E")){
                player.setArrive(true);
                return;
            }
        }
        
        player.arriveCell(map.map[player.getCurrentLength()][player.getCurrentWidth()]);
    }

    int rollTheDice(Player player){
        Dice dice = new Dice();
        return dice.roll();
    }

    void playerTurn(Map map, Player player){
        Scanner scan = new Scanner(System.in);
        String str;
        String[] moveString;
        int roll;

        System.out.println("1. 주사위 굴리기, 2. 쉬기");
        System.out.print(">> ");
        roll = scan.nextInt();
        scan.nextLine();
        if(roll == 1){
            System.out.println("주사위 굴리기");
            int turn = rollTheDice(player);
            System.out.printf(">> %d\n", turn);
            if(turn - player.getnumberOfBridgeCard() <= 0){
                System.out.printf("움직일 수 없습니다.\n");
                return ;
            }
            System.out.printf("이동할 수 있는 칸 : %d\n", turn - player.getnumberOfBridgeCard());

            while(true){
                boolean moveable = true;
                System.out.printf("이동할 경로를 입력하시오 : ");
                str = scan.nextLine();
                moveString = str.split(" ");
                if(moveString.length != turn - player.getnumberOfBridgeCard()){
                    System.out.println("이동할 수 있는 횟수가 아닙니다. 경로를 다시 입력하세요");
                }
                else{
                    int length = player.getCurrentLength();
                    int width = player.getCurrentWidth();
                    for(int i = 0; i < moveString.length; i++){
                        
                        if(moveString[i].equals("U")){
                            length--;
                        }
                        else if(moveString[i].equals("D")){
                            length++;
                        }
                        else if(moveString[i].equals("L")){
                            width--;
                        }
                        else if(moveString[i].equals("R")){
                            width++;
                        }
                        if(map.map[length][width].getType().equals("E")){
                            String[] endString= new String[i + 1];
                            for(int j = 0; j <= i; j++){
                                endString[j] = moveString[j];
                            }
                            moveString = endString.clone();
                            break;
                        }
                         else if(map.map[length][width].getType().equals("X")){
                            System.out.println("이동할 수 없는 칸입니다. ");
                            moveable = false;
                            break;
                         }
                    }
                    if(moveable){
                        break;
                    }
                }
                
            }
            movePlayer(map, moveString, player);
        }
        else if(roll == 2){
            if(player.getnumberOfBridgeCard() > 0)
                player.minusNumberOfBridgeCard();
        }
    }
    
    void play(Map map) throws IOException{
        int rank = 1;
        boolean finish = false;
        map.setPlayer();
        map.setMap();
        map.loadMap();
        map.showMap();

        while(true){
            for(int i = 0; i < numberOfPlayer; i++){
                if(players[i].getInPlay()){
                    System.out.printf("플레이어 %s의 턴\n", players[i].getColor());
                    map.playerTurn(map, players[i]);
                    if(players[i].getArrive()){
                        players[i].setRank(rank);
                        players[i].setScore(rank);
                        rank++;
                        players[i].setInPlay(false);
                    }
                    int count = 0;
                    for(int k = 0; k < numberOfPlayer; k++){
                        if(!players[k].getInPlay()){
                            count++;
                        }
                        if (count == numberOfPlayer - 1){
                            finish = true;
                        }
                    }
                    if(finish){
                        System.out.println("최종 점수");
                        int max = 0;
                        for (int c = 0; c < numberOfPlayer; c++){
                            System.out.printf("플레이어%s의 점수 : %d\n", players[c].getColor(), players[c].getScore());
                            if(max < players[c].getScore())
                                max = players[c].getScore();
                        }
                        for (int c = 0; c < numberOfPlayer; c++){
                            if(max == players[c].getScore())
                                System.out.printf("플레이어%s 승리!", players[c].getColor());
                        }
                        System.out.println("게임종료");
                        System.exit(0);
                    }
                    map.showMap();
                    for(int j = 0; j < numberOfPlayer; j++){
                        players[j].playerInfo();
                    }
                    System.out.println();
                }
            }
        }
    }
}