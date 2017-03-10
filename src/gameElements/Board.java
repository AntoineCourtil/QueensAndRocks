package gameElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Vector;


public class Board {

    private Game game;
    private int size;
    private int numberOfPieces;
    private Square[][] board;

    //ATTENTION
    //Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
    //Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...

    public Board() {
        this.game = new Game();
        this.size = 8;
        this.numberOfPieces = 0;

        Square[][] board = new Square[8][8];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = this.game.getEmpty();
            }
        }

        this.board = board;
    }

    public Board(int size_board){
        this.game = new Game();
        this.size = size_board;
        this.numberOfPieces = 0;

        Square[][] board = new Square[size_board][size_board];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = this.game.getEmpty();
            }
        }

        this.board = board;
    }


    public Board(Game game, int size, int numberOfPieces, Square[][] board) {
        this.game = game;
        this.size = size;
        this.numberOfPieces = numberOfPieces;
        this.board = board;
    }

    public Board(Game game, int numberOfPieces, Square[][] board) {
        //Default size : 8

        this.game = game;
        this.size = 8;
        this.numberOfPieces = numberOfPieces;
        this.board = board;
    }


    //------------------ GETTERS --------------------------

    public Game getGame() {
        return game;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public Square[][] getBoard() {
        return board;
    }

    //------------------ SETTERS --------------------------

    public void setGame(Game game) {
        this.game = game;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumberOfPieces(int numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public void setBoard(Square[][] board) {
        this.board = board;
    }


    //---------------TP1------------------------

    public Square getPiece(int i, int j) {
        return this.board[i][j];
    }

    public void setPiece(int i, int j, Square piece) {
        if (this.board[i][j].isEmpty()) {
            this.numberOfPieces++;
        }
        this.board[i][j] = piece;
    }

    public void removePiece(int i, int j) {
        if (!this.board[i][j].isEmpty()) {
            this.numberOfPieces--;
        }
        this.board[i][j] = this.game.getEmpty();
    }

    public boolean isEmpty(int i, int j) {
        return this.board[i][j].isEmpty();
    }

    public boolean isAccessible(int i, int j) {

        boolean ok = true;
        //TEST EN LIGNE
        //TEST EN COLONNE
        //DIAGONALES
        int k = 0;
        while (k < getSize() && ok) {
            //LIGNE
            if (!getSquare(k, j).isEmpty()) ok = false;
            //COLONNE
            if (!getSquare(i, k).isEmpty()) ok = false;
            //DIAGO
            if ((i + k < getSize()) && (j - k >= 0))
                if (!getSquare(i + k, j - k).isEmpty()) ok = false;
            if ((i + k < getSize()) && (j + k < getSize()))
                if (!getSquare(i + k, j + k).isEmpty()) ok = false;
            if ((i - k >= 0) && (j + k < getSize()))
                if (!getSquare(i - k, j + k).isEmpty()) ok = false;
            if ((i - k >= 0) && (j - k >= 0))
                if (!getSquare(i - k, j - k).isEmpty()) ok = false;
            k++;
        }
        return ok;
    }

    public String toStringAccess() {
        String s = toString();
        StringBuilder strb = new StringBuilder(s.length());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (isAccessible(i, j)) {
                    strb.append("O");
                } else {
                    if (s.charAt(i + j) == '\n') strb.append(s.charAt(i + j + 1));
                    else strb.append(s.charAt(i + j));
                }
            }
            strb.append('\n');
        }
        return strb.toString();
    }


    public int numberOfAccessible() {
        int nbAccessible = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.isAccessible(i, j)) {
                    nbAccessible++;
                }
            }
        }

        return nbAccessible;
    }

    public int numberOfQueens() {
        int nbQueens = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.getPiece(i, j) instanceof Queen) {
                    nbQueens++;
                }
            }
        }

        return nbQueens;
    }

    public boolean placeQueen(int i, int j) {
        if (this.isAccessible(i, j)) {
            this.setPiece(i, j, new Queen(this.game.getPlayer0()));
            this.numberOfPieces++;
            return true;
        }
        return false;
    }

    //----------TP2-----------------------


    public ArrayList<Board> depthFirstSearch(Board b) {
        ArrayList<Board> successeurs = new ArrayList<>();
        if(b.isSolution()){
            successeurs.add(b);
            //System.out.println("SOLUTION");
            return successeurs;
        }
        for(Board b_s : b.getSuccessors()){
            //System.out.println(b.getSuccessors().size());
            ArrayList<Board> cheminDuSuccesseur = depthFirstSearch(b_s);

            try{
                if(cheminDuSuccesseur.size() == 0)
                    throw new NoSuchElementException();

                for(Board cheminSuccess : cheminDuSuccesseur){
                    successeurs.add(cheminSuccess);
                }
                successeurs.add(b_s);
                return successeurs;
            }catch (NoSuchElementException e){
                //e.printStackTrace();
            }
        }
        return successeurs;
    }

    public String solutionSteps(Board b){
        StringBuilder strb = new StringBuilder();

        ArrayList<Board> solution = depthFirstSearch2(b);
        Collections.reverse(solution);

        int i = 0;

        for(Board board : solution){
            strb.append("\n\n________ ETAPE "+i+" ________\n");

            strb.append(board.toString());
            i++;
        }

        strb.append("\n");
        return strb.toString();
    }


    public boolean isSolution() {
        return numberOfQueens() == getSize();
    }

    public ArrayList<Board> getNewSuccessors() {
        ArrayList<Board> alsuccess = new ArrayList<>();

        int j = numberOfQueens();

        //System.out.println(j);

        for (int i = 0; i < getSize(); i++) {
            if (isAccessible(i, j)) {
                Board b_clone = clone();
                b_clone.placeQueen(i, j);
                alsuccess.add(b_clone);
            }
        }
        return alsuccess;
    }

    public ArrayList<Board> depthFirstSearch2(Board b) {
        ArrayList<Board> successeurs = new ArrayList<>();
        if(b.isSolution()){
            successeurs.add(b);
            return successeurs;
        }
        for(Board b_s : b.getNewSuccessors()){
            ArrayList<Board> cheminDuSuccesseur = depthFirstSearch2(b_s);

            try{
                if(cheminDuSuccesseur.size() == 0)
                    throw new NoSuchElementException();

                for(Board cheminSuccess : cheminDuSuccesseur){
                    successeurs.add(cheminSuccess);
                }
                successeurs.add(b_s);
                return successeurs;
            }catch (NoSuchElementException e){
                //e.printStackTrace();
            }
        }
        return successeurs;
    }


    //------------TP3----------------------
    public boolean isAccessible2(int i, int j, Player currentPlayer) {
        // TODO Auto-generated method stub
        return false;
    }


    public boolean placeQueen2(int i, int j, Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean placeRock2(int i, int j, Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    public int getNumberOfRocksLeft(Player player) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getScore(Player player) {
        // TODO Auto-generated method stub
        return 0;
    }


    //----------------------TP4&5--------------------------
    public boolean isFinal() {
        // TODO Auto-generated method stub
        return false;
    }

    public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
        // TODO Auto-generated method stub
        return null;
    }

    public Square getSquare(int i, int j) {
        //TODO VERIF
        return board[i][j];
    }

    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                strb.append(getSquare(i, j).toString());
            }
            strb.append("\n");
        }
        return strb.toString();
    }

    public Board clone() {

        //System.out.println("_____CLONE____________");

        Square[][] boardCopy = new Square[getSize()][getSize()];

        //System.arraycopy(this.getBoard(), 0, boardCopy, 0, getSize());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }

        Board b = new Board(this.getGame(), this.getSize(), this.getNumberOfPieces(), boardCopy);

        //System.out.println("++Copy Original++\n" + b.toString() + "\n+++\n");

        //b.placeQueen(0,0);

        /*System.out.println(toString());
        System.out.println("----");
        System.out.println(b.toString());


        System.out.println("_____FIN CLONE____________");*/


        return b;
    }


    /**
     * Get toutes les solutions disponibles
     *
     * @return les successors
     */
    public ArrayList<Board> getSuccessors() {
        ArrayList<Board> alsuccess = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (isAccessible(i, j)) {
                    Board b_clone = clone();
                    b_clone.placeQueen(i, j);
                    alsuccess.add(b_clone);
                }
            }
        }
        return alsuccess;
    }

    /**
     * Convertit un board en un tableau unidimen (voir td+cours)
     * Note que les lignes ou sont positionnées les queens -1 si pas de queen sur la colonne
     * @return
     */
    public int[] boardToArray(){
        int[] array_board = new int[getSize()];
        boolean queen_dans_la_ligne;
        for (int i = 0; i < getSize(); i++) {
            queen_dans_la_ligne = false;
            for (int j = 0; j < getSize(); j++) {
                if(getPiece(i,j) instanceof Queen){
                    queen_dans_la_ligne = true;
                    array_board[i] = j;
                    break;//On sort de la boucle car 1 seule queen dans chaque lignes
                }
            }
            if(!queen_dans_la_ligne) array_board[i] = -1;
        }
        return  array_board;
    }

    /**
     * Opération inverse de board to array
     */
    public Board arrayToBoard(int[] array_board){
        Board b = new Board(array_board.length);

        for(int i = 0; i<array_board.length; i++){
            if(array_board[i] != -1){
                b.placeQueen(i,array_board[i]);
            }
        }
        return b;
    }

    /**
     * A voir si c'est bien comme cela qu'il faut faire car je reconverti en board pour pouvoir
     * utiliser isAccessible
     * @param array
     * @return
     */
    public ArrayList<int[]> getArraySuccessors(int[] array){
        ArrayList<int[]> alsuccess = new ArrayList<>();
        Board b = arrayToBoard(array);
        for(int i = 0; i<array.length; i++ ){
            for (int j = 0; j<array.length; j++){
                if(b.isAccessible(i,j)){
                    int [] tab = new int[array.length];
                    System.arraycopy(array, 0, tab, 0, array.length);
                    tab[i] = j;//Placemement de la reine
                    alsuccess.add(tab);
                }
            }
        }
        return alsuccess;
    }

    /**
     * Normalement il doit y avoir une reine par ligne pour que ce soit une solution
     * @param array
     * @return
     */
    public boolean isSolutionArray(int[] array){
        boolean solution = true;
        for(int ligne: array){
            if(ligne == -1) solution = false;
        }
        return solution;
    }

    public ArrayList<int[]> depthFirstSearchArray(int[] initialState){
        ArrayList<int[]> successeurs = new ArrayList<>();

        if(isSolutionArray(initialState)){
            successeurs.add(initialState);
            return successeurs;
        }
        for (int[] b_s : getArraySuccessors(initialState)){
            ArrayList<int[]> chemin_successeur = depthFirstSearchArray(b_s);

            try{
                if(chemin_successeur.size() == 0)
                    throw new NoSuchElementException();

                for(int[] cheminSuccess : chemin_successeur){
                    successeurs.add(cheminSuccess);
                }
                successeurs.add(b_s);
                return successeurs;
            }catch (NoSuchElementException e){

            }
        }
        return successeurs;
    }
    

    public ArrayList<int[]> depthFirstSearchArray(){
        int[] initialState = boardToArray();
        return depthFirstSearchArray(initialState);
    }



}
