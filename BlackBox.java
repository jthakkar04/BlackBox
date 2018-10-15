import java.util.*;

/**
 * Created by Jagat Thakkar 02 March 2018
 *
 */
public class BlackBox {
    public static Scanner scanner = new Scanner(System.in);
    public static char box[][]; // The matrix for the game.
    public static int size;       // to store the number of rows and columns.
    public static int numball;
    public static int numballReset;
    public static int numlink;
    public static boolean end;
    public static int score;
    public static int high_score = (int) Math.pow(2,31) - 1;
    public static int guessesRight = 0;
    final static String cord = "Choose the new coordinates (row, column) to play the next step or say submit/quit to" +
            " end the game:";
    final static String again = "Would you like to play again? (yes/no)";

    /**
     * The default constructor which places default values to the class variables
     */
    public BlackBox()
    {
        this.box=new char[0][0];
        this.size=0;
        this.numball=0;
        this.numlink=0;
        this.end=false;
        this.score=0;
    }
    /**
     * The parameterized constructor which places values to the class variables
     */
    public BlackBox(int size, int numball, int numlink, boolean end, int score)
    {
        this.box=new char[size][size];
        this.size=size;
        this.numball=numball;
        this.numlink=numlink;
        this.end=end;
        this.score=score;
    }
    /**
     * The main function takes input for the difficulty level and call the functions initialize(int) and
     * playgame()
     */
    public static void main(String[] args) {
       //Todo:start the game print the welcome message and ask for correct difficulty level.
        //Todo: end the game if the user says quit.
        //Todo:call the functions initialize and playgame()
        // Todo: take care of high score
        System.out.println("Welcome to the Blackbox Game. Please choose the difficulty level:easy/medium/hard or quit" +
                           " to end the game");
        BlackBox blackBox = new BlackBox(size,3,0,false,0);
        do {
            String option = scanner.nextLine();
            option = option.toLowerCase();
            if (option.equals("easy")) {
                size = 7;
                break;
            } else if (option.equals("medium")) {
                size = 9;
                break;
            } else if (option.equals("hard")) {
                size = 10;
                break;
            } else if (option.equals("quit")){
                System.exit(0);
            } else {
                System.out.println("Wrong input. Please choose the difficulty level:easy/medium/hard or quit");
            }
        } while (true);
        blackBox.initialize();
        blackBox.playgame();

    }
    /**
     * The initialize funtion
     */
    public void initialize() {
        //Todo:initialise the Box[][]
        //Todo: place the 'X' and the '#'
        // Todo: place 3 '0''s randomly.

        box = new char[size][size];

        box[0][0] = 'X';
        box[0][size - 1] = 'X';
        box[size - 1][size - 1] = 'X';
        box[size - 1][0] = 'X';

        Random random = new Random();

        box[random.nextInt(size - 2) + 1][random.nextInt(size - 2) + 1] = '0';
        box[random.nextInt(size - 2) + 1][random.nextInt(size - 2) + 1] = '0';
        box[random.nextInt(size - 2) + 1][random.nextInt(size - 2) + 1] = '0';

        int count = 0; //Count is to check if there is overlap of balls
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                if (box[j][k] == '0') {
                    count++;
                }
            }
        }
        if (count == 2) { // if there are only two balls after randomization, there will be another one set
            box[random.nextInt(size - 2) + 1][random.nextInt(size - 2) + 1] = '0';
        }

        for (int j = 1; j < size - 1; j++) {
            box[0][j] = '#';
        }
        for (int j = 1; j < size - 1; j++) {
            box[j][size - 1] = '#';
        }
        for (int j = size - 2; j >= 1; j--) {
            box[size - 1][j] = '#';
        }
        for (int j = size - 2; j >= 1; j--) {
            box[j][0] = '#';
        }
    }

    /**
     * The printbox funtion prints out the matrix in a particular format as given in the handout.
     */

    public static void printBoxCreds(){
        int current = 1;
        for (int i = 0; i < size; i++) {
            System.out.print( (current + i) + "  | ");
            for (int j = 0; j < size ; j++) {
                if (box[i][j] == '\u0000' || box[i][j] == '0') {
                    System.out.print(" " + " | ");
                } else {
                    System.out.print(box[i][j] + " | ");
                }
            }
            System.out.println();
        }
    } //extension for printbox

    public static void printbox() {
        //Todo:print the box in the correct order
        // for  5*5 example
        /* 1  2  3  4  5  6  7
         ======================
        1|X |# |# |# |# |# |X |
        2|# |  |  |  |  |  |# |
        3|# |  |  |  |  |  |# |
        4|# |  |  |  |  |  |# |
        5|# |  |  |  |  |  |# |
        6|# |  |  |  |  |  |# |
        7|X |# |# |# |# |# |X |
         ======================*/
        //place the guesses as the come and print the balls when the player enter submit.
        if (size == 7) {
            System.out.println("     1   2   3   4   5   6   7   ");
            System.out.println("   =============================");
            printBoxCreds();
            System.out.println("   =============================");
        }  if (size == 9){
            System.out.println("     1   2   3   4   5   6   7   8   9   ");
            System.out.println("   =====================================");
            printBoxCreds();
            System.out.println("   =====================================");
        }  if (size == 10){
            System.out.println("     1   2   3   4   5   6   7   8   9   10   ");
            System.out.println("   =========================================");
            printBoxCreds();
            System.out.println("   =========================================");
        }

    }

    public void yesOrNo(){ // should the program run again or not
        do {
            String yesNo = scanner.next();
            if (yesNo.toLowerCase().equals("yes")) {
                score = 0;
                numball = numballReset;
                initialize();
                break;
            } else if (yesNo.toLowerCase().equals("no")) {
                System.exit(0);
            } else if (!yesNo.toLowerCase().equals("yes") || !yesNo.toLowerCase().equals("no")){
                System.out.println("Wrong input.");
                if (yesNo.toLowerCase().equals("yes")) {
                    score = 0;
                    numball = numballReset;
                    initialize();
                    break;
                } else if (yesNo.toLowerCase().equals("no")) {
                    System.exit(0);
                }
            }
        } while (true);
    } //extension for playgame loop

    /**
     * The playgame funtion opens the first cell and is the main controller for the game. It calls various function when needed.
     */

    public void playgame() {
        //Todo:Take input of a guess or hint from the user.
        //Todo:Check for valid input
        //Todo:call required functions
        //Todo:keep tab on score.
        printbox();
        System.out.println(cord);
        String nextStep = "";
        do{
            nextStep = scanner.nextLine();
            if (nextStep.equals("quit")) {
                System.exit(0);
            } else if (nextStep.contains(",")) {
                String cordOneString = nextStep.substring(0, nextStep.indexOf(","));
                int cordOne = Integer.parseInt(cordOneString);
                String cordTwoString = nextStep.substring(nextStep.indexOf(",") + 1);
                int cordTwo = Integer.parseInt(cordTwoString);
                check(cordOne, cordTwo);
                break;
            } else if (nextStep.equals("submit")) {
                if (numball != 0) {
                    System.out.println("You did not place 3 balls.");
                }
                if (score < high_score) {
                    high_score = score;
                    System.out.println(high_score);
                    score = 0;
                }
                if (guessesRight != numballReset){
                    System.out.println("You did not successfully hit the balls." + again);
                    yesOrNo();
                } else {
                    System.out.println("You did it ;)! Good job!\n" + "Score = " + high_score + "\n" + again);
                    yesOrNo();
                }
                break;
            } else if (!nextStep.equals("submit") || !nextStep.equals("quit")){
                System.out.println("Wrong input." + "Choose the new coordinates (row, column) to play the next" +
                        " step or say submit/quit to end the game:");
            }
        } while (true);
    }

    /**
     * The check funtion takes in the row and column in the matrix, checks for Hit (H), Reflection (R) or Divergence(#num)
     *
     */
    public void check(int i,int j) {
        //Todo:place a guess when the input of i and j are valid
        //Todo:Check for a Hit
        //Todo:Check for a reflection
        //Todo:Check for a bounce
        //Todo:Print a statement telling the user they cannot place a fourth ball.

        try {
            if ((i == 1 && j == 1) || (i == 1 && j == size) || (i == size && j == 1) || (i == size && j == size)) {
                System.out.println("Not a valid input.");
                playgame();
            }
            if (i < 1 || j < 1) {
                System.out.println("Not a valid input.");
                playgame();
            }
            if (i > size || j > size) {
                System.out.println("Not a valid input.");
                playgame();
            }
            if (i != 1 && j != 1 && i != size && j != size) {
                if (numball > 0) {
                    placeball(i, j);
                    numball--;
                } else {
                    System.out.println("No balls left.");
                }
                playgame();
            }

            if (reflectionCheck(i,j)){
                reflectionCheck(i,j);
            }
            if (!reflectionCheck(i,j)){
                deflectionCheck(i,j);
                if (!deflectionCheck(i,j)){
                    hitcheck(i,j);
                    if (!hitcheck(i,j)){
                        straightRay(i,j);
                    }
                }
            }
        playgame();
        } catch (Exception e) { }
    }

    /**
     * places ball and changes to *
     */
    public boolean placeball(int i,int j) {
        i = i - 1;
        j = j - 1;
        if (i != 0 && i != size - 1 && j != 0 && j != size - 1) {
            if (numball == 0){
                System.out.println("No balls left.");
                return false;
            }
            if (box[i][j] == '0'){
                guessesRight++;
            }
            if (box[i][j] != '*') {
                box[i][j] = '*';
                numballReset++;
                score++;
                return true;
            } else{
                System.out.println("A guess is already placed there.");
            }
        }
            return false;
    }
    /**
     * The hitcheck funtion takes in the row and column in the matrix, checks for Hit (H)
     *
     */
    public boolean hitcheck(int i,int j) {
        //todo: check if the ray causes a HIT as defined in the handout
        i = i - 1;
        j = j - 1;
        for (int k = 0; k < size; k++) {
            try {
                if (box[i][j + k] == '0') {//Check for vertical
                    score++;
                    box[i][j] = 'H';
                    return true;
                }
            } catch (Exception e) { }
        }
        for (int k = 0; k < size; k++) {
            try {
                if (box[i + k][j] == '0') {//Check for horizontal
                    score++;
                    box[i][j] = 'H';
                    return true;
                }
            }catch (Exception e){}
        }
        for (int k = size - 1; k > 0; k--) {
            try {
                if (box[i][j - k] == '0') {//Check for vertical
                    score++;
                    box[i][j] = 'H';
                    return true;
                }
            } catch (Exception e) { }
        }
        for (int k = size - 1; k > 0 ; k--) {
            try{
                if (box[i - k][j] == '0') {//Check for horizontal
                    score++;
                    box[i][j] = 'H';
                    return true;
                }
            } catch (Exception e) { }
        }
        return false;
    }
    /**
     * The check funtion takes in the row and column in the matrix, checks for Reflection (R)
     *
     */


    public boolean reflectionCheck(int i,int j) {
        //todo: check if the ray causes a Reflection as defined in the handout
        i = i -1;
        j = j -1;
        try {
            if (i != size - 1 && j != 0) {
                if (box[i + 1][j - 1] == '0') { // 45 degree corner bottom left
                    score++;
                    box[i][j] = 'R';
                    return true;
                }
            }
            if (i != 0 && j != size - 1) {
                if (box[i - 1][j + 1] == '0') { // 45 degree corner top right
                    score++;
                    box[i][j] = 'R';
                    return true;
                }
            }
            if (i != 0 && j!= 0) {
                if (box[i - 1][j - 1] == '0') { // 45 degree corner top left
                    score++;
                    box[i][j] = 'R';
                    return true;
                }
            }
            if (i != size - 1 && j != size - 1) {
                if (box[i + 1][j + 1] == '0') { // 45 degree corner bottom right
                    score++;
                    box[i][j] = 'R';
                    return true;
                }
            }
        } catch (Exception e) { }
        try {
            for (int k = 1; k < size - 1; k++) {
                if (j == 0) {
                    if (box[i + 1][k] == '0' && box[i - 1][k] == '0') {//Check for horizontal going right
                        box[i][j] = 'R';
                        return true;
                    }
                }
            }
            for (int k = 1; k < size - 1; k++) {
                if (i == 0) {
                    if (box[k][j + 1] == '0' && box[k][j - 1] == '0') {//Check for vertical going down
                        box[i][j] = 'R';
                        return true;
                    }
                }
            }
        } catch (Exception e) { }
        try {
            for (int k = size - 1; k > 0; k--) {
                if (j == size - 1) {
                    if (box[i + 1][k] == '0' && box[i - 1][k] == '0') {//Check for horizontal going left
                        box[i][j] = 'R';
                        return true;
                    }
                }
            }
            for (int k = size - 1; k > 0 ; k--) {
                if (i == size - 1) {
                    if (box[k][j + 1] == '0' && box[k][j - 1] == '0') {//Check for vertical going up
                        box[i][j] = 'R';
                        return true;
                    }
                }
            }
        } catch (Exception e) { }
        return false;
    }
    /**
     * The check function takes in the row and column in the matrix, checks for Divergence(#num)
     *
     */


    public boolean deflectionCheck(int i,int j) {
        //todo: check if the ray causes a Deflection as defined in the handout
        i = i - 1;
        j = j - 1;
        if (box[i][j] == '#'){
            if (j == 0){
                try {
                    for (int k = 0; k < size - 1; k++) {
                        if (box[i + 1][k + 1] == '0') { // going right, sends beam up
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[0][k] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                    for (int k = 0; k < size - 1; k++) {
                        if (box[i - 1][k + 1] == '0') {// going right, sends beam down
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[size - 1][k] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                } catch (Exception e){}
            }//j == 0
            if (j == size - 1){
                try {
                    for (int k = size - 1; k > 0; k--) {
                        if (box[i + 1][k - 1] == '0') { // going left, sends beam up
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[0][k] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                    for (int k = size - 1; k > 0 ; k--) {
                        if (box[i - 1][k - 1] == '0') { // going left, sends beam down
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[size - 1][k] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                }catch (Exception e){}
            }// j = size - 1
            if (i == 0){
                try {
                    for (int k = 0; k < size - 1; k++) {
                        if (box[k + 1][j - 1] == '0') { // going down, sends beam right
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[k][size - 1] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                    for (int k = 0; k < size - 1; k++) {
                        if (box[k + 1][j + 1] == '0') {// going down, sends beam left
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[k][0] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                } catch (Exception e){}
            }// i = 0
            if (i == size - 1){
                try {
                    for (int k = size - 1; k > 0; k--) {
                        if (box[k - 1][j - 1] == '0') { // going down, sends beam right
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[k][size - 1] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                    for (int k = size - 1; k > 0 ; k--) {
                        if (box[k - 1][k + 1] == '0') { // going up, sends beam left
                            numlink++;
                            box[i][j] = (char) (numlink + 48);
                            box[k][0] = (char) (numlink + 48);
                            score++;
                            return true;
                        }
                    }
                }catch (Exception e){}
            }// i = size - 1
        }// first if

        return false;
    }
    /**
     * The straightRay funtion takes in the row and column in the matrix, checks for Straight ray
     *
     */

    public boolean straightRay(int i,int j) {
        //todo: check if the ray is a straight ray as defined in the handout
        i = i - 1;
        j = j - 1;

        if (box[i][j] == '#') {
            for (int k = size - 1; k >= 0 ; k--) {
                try {
                    if (box[k][j] != '0') {
                        numlink++;
                        box[size - 1][j] = (char) (numlink+48);
                        box[0][j] = (char) (numlink+48);
                        score++;
                        return true;
                    }
                } catch (Exception e){}
            }
            for (int k = size - 1; k >= 0 ; k--) {
                try {
                    if (box[i][k] != '0') {
                        numlink++;
                        box[i][size - 1] = (char) (numlink+48);
                        box[i][0] = (char) (numlink+48);
                        score++;
                        return true;
                    }
                } catch (Exception e){}
            }
        }
        return false;
    }


    /**
     * The following definitions are the getters and setter functions which have to be implemented
     *
     */

    public char[][] getBox() {
        return box;
    }
    public int getscore() {
        return score;
    }
    public int getNumball() {
        return numball;
    }
    public int getNumlink() {
        return numlink;
    }
    public boolean getend() {
        return end;
    }
    public void setBox(char[][] box) {
        BlackBox.box = box;
    }
    public void setSize(int size){ }
    public void setNumball(int Numball) { }
    public void setNumlink(int Numlink) { }
    public void setEnd(boolean end) { }
    public void setScore(int score) { }
}