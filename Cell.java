class Cell {
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;


    private int score;
    private String type;
    private boolean[] moveable = new boolean[4];
    private boolean[] nextDirect = new boolean[4]; 
    private int length;
    private int width;

    Cell (){
        this.score = 0;
        this.type = "X";
        for (int i = 0; i < this.moveable.length; i++){
            this.moveable[i] = false;
            this.nextDirect[i] = false;
        }
    }

    Cell(String cellsData){
        for (int i = 0; i < this.moveable.length; i++){
            this.moveable[i] = false;
            this.nextDirect[i] = false;
        }
        String[] strArray = cellsData.split(" ");
        for (int i = 0; i < strArray.length; i++){
            if(strArray[i].equals("S")){
                this.score = 3;
                this.type = "S";
            }
            else if(strArray[i].equals("E")){
                this.score = 0;
                this.type = "E";
                for(int j = 0; j < 4; j++)
                    if(j == 1)
                        this.moveable[j] = false;

                    else
                        this.moveable[j] = true;
            }
            else if(strArray[i].equals("B")){
                this.score = 0;
                this.type = "B";
                this.moveable[RIGHT] = true;
            }
            else if(strArray[i].equals("b")){
                this.score = 0;
                this.type = "b";
                this.moveable[LEFT] = true;
            }
            else if(strArray[i].equals("C")){
                this.score = 0;
                this.type = "C";
            }
            else if(strArray[i].equals("H")){
                this.score = 2;
                this.type = "H";
            }
            else if(strArray[i].equals("P")){
                this.score = 1;
                this.type = "P";
            }
            else if(strArray[i].equals("U")){
                this.moveable[UP] = true;
            }
            else if(strArray[i].equals("D")){
                this.moveable[DOWN] = true;
            }
            else if(strArray[i].equals("L")){
                this.moveable[LEFT] = true;
            }
            else if(strArray[i].equals("R")){
                this.moveable[RIGHT] = true;
            }
        }
        if (strArray.length == 2){
            if (strArray[1].equals("U"))
                this.nextDirect[UP] = true;
            else if(strArray[1].equals("D"))
                this.nextDirect[DOWN] = true;
            else if(strArray[1].equals("L"))
                this.nextDirect[LEFT] = true;
            else if(strArray[1].equals("R"))
                this.nextDirect[RIGHT] = true;
        }
        else if(strArray.length == 3){
            if (strArray[2].equals("U"))
                this.nextDirect[UP] = true;
            else if(strArray[2].equals("D"))
                this.nextDirect[DOWN] = true;
            else if(strArray[2].equals("L"))
                this.nextDirect[LEFT] = true;
            else if(strArray[2].equals("R"))
                this.nextDirect[RIGHT] = true;
        }

    }
    
    void setScore(int score){
        this.score = score;
    }

    void setType(String type){
        this.type = type;
    }

    void setMoveable(boolean[] moveable){
        this.moveable = moveable.clone();
    }

    void setNextDirect(boolean[] nextDirect){
        this.nextDirect = nextDirect.clone();
    }
    
    void setLength(int length){
        this.length = length;
    }

    void setWidth(int width){
        this.width = width;
    }

    int getLength(){
        return this.length;
    }

    int getWidth(){
        return this.width;
    }

    int getScore(){
        return this.score;
    }
    String getType(){
        return this.type;
    }
    int getNextDirect(){ // 다음 셀의 방향
        if(this.nextDirect[UP])
            return UP;
        else if(this.nextDirect[DOWN])
            return DOWN;
        else if(this.nextDirect[LEFT])
            return LEFT;
        else if(this.nextDirect[RIGHT])
            return RIGHT; 
        else
            return -1;
    }
    boolean[] getArrayMoveable(){ //플레이어가 움직일 수 있는 방향
        return this.moveable;
    }

    boolean[] getArrayNextDirect(){
        return this.nextDirect;
    }
}
