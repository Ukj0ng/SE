class Player {
    private String color;
    private int score;
    private int numberOfBridgeCard;
    private int rank;
    private boolean inplay;
    private boolean arrive;
    private String currentType;
    private int currentLength;
    private int currentWidth;
    

    // private Cell position; 위치를 좌표로 정할까?
    Player(String color){
        this.color = color;
        this.score = 0;
        this.numberOfBridgeCard = 0;
        this.rank = 0;
        this.inplay = true;
        this.arrive = false;
        this.currentType = "S";
        this.currentLength = 3;
        this.currentWidth = 3;
    }

    void arriveCell(Cell cell){
        if(cell.getWidth() == 3 && cell.getLength() == 3)
            this.score += 0;
        else
            this.score += cell.getScore();
        this.arrive = isArrive(cell.getType());
        this.currentType = cell.getType();
    }
    
    boolean isArrive(String str){
        if(str.equals("E"))
            return true;
        return false;
    }
    void playerInfo(){
        System.out.printf("Color : %s\nscore : %d\nnumberofBridgeCard : %d\n\n", this.color, this.score, this.numberOfBridgeCard);
    }
    void setScore(int rank){
        int score = 0;
        if(rank == 1){
            score = 7;
        }
        else if(rank == 2){
            score = 3;
        }
        else if(rank == 3){
            score = 1;
        }
        this.score += score;
        System.out.printf("%d등으로 %d점을 획득했습니다!\n", rank, score);
    }
    void addNumberOfBridgeCard(){
        this.numberOfBridgeCard++;
    }
    void minusNumberOfBridgeCard(){
        this.numberOfBridgeCard--;
    }
    void setRank(int rank){
        this.rank = rank;
    }
    void setInPlay(boolean now){
        this.inplay = now;
    }
    void setArrive(boolean arrive){
        this.arrive = arrive;
    }
    void setCurrentLength(int length){
        this.currentLength = length;
    }

    void setCurrentWidth(int width){
        this.currentWidth = width;
    }
    String getColor(){
        return this.color;
    }
    int getScore(){
        return this.score;
    }
    int getRank(){
        return this.rank;
    }
    boolean getInPlay(){
        return this.inplay;
    }
    boolean getArrive(){
        return this.arrive;
    }
    int getnumberOfBridgeCard(){
        return this.numberOfBridgeCard;
    }
    int getCurrentLength(){
        return this.currentLength;
    }
    int getCurrentWidth(){
        return this.currentWidth;
    }
    String getCurrentType(){
        return this.currentType;
    }
    

}