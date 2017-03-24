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
    private int rocksPlayer0;
    private int rocksPlayer1;

    //ATTENTION
    //Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
    //Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...

    public Board() {
        this.game = new Game();
        this.size = 8;
        this.numberOfPieces = 0;
        rocksPlayer0 = rocksPlayer1 = 6;

        Square[][] board = new Square[8][8];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = this.game.getEmpty();
            }
        }

        this.board = board;
    }

    public Board(int size_board) {
        this.game = new Game();
        this.size = size_board;
        this.numberOfPieces = 0;
        rocksPlayer0 = rocksPlayer1 = 6;

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

    public String toStringAccess2(Player player) {
        StringBuilder strb = new StringBuilder();
        strb.append("  ");
        for (int k = 0; k < size; k++) strb.append(k + " ");
        strb.append("\n");
        for (int i = 0; i < size; i++) {
            strb.append(i + " ");
            for (int j = 0; j < size; j++) {
                if (isAccessible2(i, j, player))
                    strb.append("A");
                else {
                    if (isEmpty(i, j))
                        strb.append("X");
                    else
                        strb.append(getSquare(i, j).toString());
                }
                strb.append(" ");
            }
            strb.append("\n");
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

    public int numberOfAccessible2(Player player) {
        int nbAccessible = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.isAccessible2(i, j, player)) {
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

    public int numberOfQueens2(Player player) {
        int nbQueens = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((this.getPiece(i, j) instanceof Queen) && (!getPiece(i, j).isEnemyQueen(player))) {
                    nbQueens++;
                }
            }
        }

        return nbQueens;
    }

    public boolean placeQueen(int i, int j) {
        if (this.isAccessible(i, j)) {
            this.setPiece(i, j, new Queen(this.game.getPlayer0()));
            return true;
        }
        return false;
    }

    //----------TP2-----------------------


    public ArrayList<Board> depthFirstSearch(Board b) {
        ArrayList<Board> successeurs = new ArrayList<>();
        if (b.isSolution()) {
            successeurs.add(b);
            //System.out.println("SOLUTION");
            return successeurs;
        }
        for (Board b_s : b.getSuccessors()) {
            //System.out.println(b.getSuccessors().size());
            ArrayList<Board> cheminDuSuccesseur = depthFirstSearch(b_s);

            try {
                if (cheminDuSuccesseur.size() == 0)
                    throw new NoSuchElementException();

                for (Board cheminSuccess : cheminDuSuccesseur) {
                    successeurs.add(cheminSuccess);
                }
                successeurs.add(b_s);
                return successeurs;
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
            }
        }
        return successeurs;
    }

    public String solutionSteps(Board b) {
        StringBuilder strb = new StringBuilder();

        ArrayList<Board> solution = depthFirstSearch2(b);
        Collections.reverse(solution);

        int i = 0;

        for (Board board : solution) {
            strb.append("\n\n________ ETAPE " + i + " ________\n");

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
        //System.out.println("a");
        if (b.isSolution()) {
            successeurs.add(b);
            return successeurs;
        }
        for (Board b_s : b.getNewSuccessors()) {
            ArrayList<Board> cheminDuSuccesseur = depthFirstSearch2(b_s);

            try {
                if (cheminDuSuccesseur.size() == 0)
                    throw new NoSuchElementException();

                for (Board cheminSuccess : cheminDuSuccesseur) {
                    successeurs.add(cheminSuccess);
                }
                successeurs.add(b_s);
                return successeurs;
            } catch (NoSuchElementException e) {
                //e.printStackTrace();
            }
        }
        return successeurs;
    }


    //------------TP3----------------------

    /**
     * Ici, on va utiliser la représentation d'une boussole
     * pour retenir si l'on a vu des rochers sur le passage qui annulerait l'effet de la queen
     *
     * @param i
     * @param j
     * @param currentPlayer
     * @return
     */
    public boolean isAccessible2(int i, int j, Player currentPlayer) {

        boolean ok = true;
        boolean rock_S = false, rock_E = false, rock_NW = false,
                rock_SW = false, rock_NE = false, rock_SE = false;
        boolean queen_N = false, queen_W = false;

        //Si l'on veut poser sur une case non vide
        if (getSquare(i, j) == null) return false;
        if (!getSquare(i, j).isEmpty()) return false;

        //TEST EN LIGNE
        //TEST EN COLONNE
        //DIAGONALES
        int k = 0;
        while (k < getSize() && ok) {
            //LIGNE
            Square s = getSquare(k, j);
            if (k < i) {//Nord N
                //Si on a déjà rencontré une queen sur cette partie je regarde si je trouve un cailloux
                if (queen_N) {
                    //Si je trouve un cailloux
                    if (s instanceof Rock) {
                        //J'annule la reine trouvée
                        queen_N = false;
                    }
                }
                //Si je trouve une queen sur cette partie je dis que j'en trouve une
                if (s.isEnemyQueen(currentPlayer)) queen_N = true;
            } else {//Sud S
                //Si j'ai pas trouvé de cailloux qui me protège
                if (!rock_S)
                    //Si je trouve une reine c'est foutu, je ne peux pas placer
                    if (s.isEnemyQueen(currentPlayer)) ok = false;
                //Si c'est un rocher je le note il me protegera peut etre après
                if (s instanceof Rock) rock_S = true;
            }
            //COLONNE
            s = getSquare(i, k);
            if (k < j) {//Ouest W
                //Si on a déjà rencontré une queen sur cette partie je regarde si je trouve un cailloux
                if (queen_W) {
                    //Si je trouve un cailloux
                    if (s instanceof Rock) {
                        //J'annule la reine trouvée
                        queen_W = false;
                    }
                }
                if (s.isEnemyQueen(currentPlayer)) {
                    queen_W = true;
                }
            } else {//Est E
                //Si j'ai pas trouvé de cailloux qui me protège
                if (!rock_E)
                    //Si je trouve une reine c'est foutu, je ne peux pas placer
                    if (s.isEnemyQueen(currentPlayer)) ok = false;
                //Si c'est un rocher je le note il me protegera peut etre après
                if (s instanceof Rock) rock_E = true;
            }

            //DIAGO
            if ((i + k < getSize()) && (j - k >= 0)) {
                //Sud ouest SW
                s = getSquare(i + k, j - k);
                //Si j'ai pas trouvé de cailloux qui me protège
                if (!rock_SW)
                    //Si je trouve une reine c'est foutu, je ne peux pas placer
                    if (s.isEnemyQueen(currentPlayer)) ok = false;
                //Si c'est un rocher je le note il me protegera peut etre après
                if (s instanceof Rock) rock_SW = true;
            }
            if ((i + k < getSize()) && (j + k < getSize())) {
                //Sud Est SE
                s = getSquare(i + k, j + k);
                //Si j'ai pas trouvé de cailloux qui me protège
                if (!rock_SE)
                    //Si je trouve une reine c'est foutu, je ne peux pas placer
                    if (s.isEnemyQueen(currentPlayer)) ok = false;
                //Si c'est un rocher je le note il me protegera peut etre après
                if (s instanceof Rock) rock_SE = true;
            }
            if ((i - k >= 0) && (j + k < getSize())) {
                //Nord Est NE
                s = getSquare(i - k, j + k);
                //Si j'ai pas trouvé de cailloux qui me protège
                if (!rock_NE)
                    //Si je trouve une reine c'est foutu, je ne peux pas placer
                    if (s.isEnemyQueen(currentPlayer)) ok = false;
                //Si c'est un rocher je le note il me protegera peut etre après
                if (s instanceof Rock) rock_NE = true;
            }
            if ((i - k >= 0) && (j - k >= 0)) {
                //Nord Ouest NW
                s = getSquare(i - k, j - k);
                //Si j'ai pas trouvé de cailloux qui me protège
                if (!rock_NW)
                    //Si je trouve une reine c'est foutu, je ne peux pas placer
                    if (s.isEnemyQueen(currentPlayer)) ok = false;
                //Si c'est un rocher je le note il me protegera peut etre après
                if (s instanceof Rock) rock_NW = true;

            }
            k++;
        }

        //C'est qu'on avait trouvé une reine mais pas de cailloux pour annuler
        if (queen_N || queen_W) {
            return false;
        }
        //Si on est a faux c'est que l'on ne peux pas poser
        return ok;
    }


    public boolean placeQueen2(int i, int j, Player player) {
        if (this.isAccessible2(i, j, player)) {
            this.setPiece(i, j, new Queen(player));
            //System.out.println("--------------------\n"+this.toStringAccess2(player)+"\n--------------------\n");
            return true;
        }
        return false;
    }

    public boolean placeRock2(int i, int j, Player player) {
        if (getPiece(i, j).isEmpty() && (getNumberOfRocksLeft(player) > 0)) {
            this.setPiece(i, j, new Rock(player));
            this.useRock(player);
            return true;
        }
        return false;
    }

    public int getNumberOfRocksLeft(Player player) {
        if (player.getNumber() == 0) {
            return getRocksPlayer0();
        } else {
            return getRocksPlayer1();
        }
    }

    public int getScore(Player player) {
        int score = 0;

        score += numberOfQueens2(player) * 5;
        score += numberOfRocks2(player) * 2;

        return score;
    }


    //----------------------TP4&5--------------------------
    public boolean isFinal() {
        //Si on ne peux plus poser de reines (le nombre max est atteint) et si un des deux joueurs n'a plus de
        //cailloux à poser

        if(numberOfPieces == size*size){
            return true;
        }

        return isSolution() && ((getRocksPlayer0() == 0) || (getRocksPlayer1() == 0));
    }

    public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
        //Ensemble des états possibles suite au coup du joueur-machine
        ArrayList<Board> successeurs = getSuccessors2(currentPlayer);
        float score_max = Float.NEGATIVE_INFINITY;//moins l'infini
        float score;
        Board e_sortie = new Board(getSize());// Etat indiquant qu'aucun coup n'est possible

        for (Board successor : successeurs) {
            score = evaluation(successor, currentPlayer, minimaxDepth, evaluation, currentPlayer);
            if (score >= score_max) {
                e_sortie = successor;
                score_max = score;
            }
        }
        return e_sortie;
    }

    public Square getSquare(int i, int j) {
        if (i < getSize() || j < getSize())
            return board[i][j];
        return null;
    }

    public int numberOfRocks2(Player player) {
        int nbRocks = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((this.getPiece(i, j) instanceof Rock) && (getPiece(i, j).getPlayer().getNumber() == player.getNumber())) {
                    nbRocks++;
                }
            }
        }

        return nbRocks;
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
        //On recopie les attributs du nombre de rocks restants
        b.rocksPlayer0 = getRocksPlayer0();
        b.rocksPlayer1 = getRocksPlayer1();

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

    //Renvoi les successors pour le player donné (c'est à son tour de jouer)
    public ArrayList<Board> getSuccessors2(Player player) {
        ArrayList<Board> alsuccess = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (isAccessible2(i, j, player)) {
                    Board b_clone = clone();
                    b_clone.placeQueen2(i, j, player);
                    alsuccess.add(b_clone);
                }

                if (isEmpty(i, j)) {
                    Board b_clone2 = clone();
                    if (b_clone2.placeRock2(i, j, player)) {
                        alsuccess.add(b_clone2);
                    }
                }
            }
        }
        return alsuccess;
    }

    /**
     * Convertit un board en un tableau unidimen (voir td+cours)
     * Note que les lignes ou sont positionnées les queens -1 si pas de queen sur la colonne
     *
     * @return
     */
    public int[] boardToArray() {
        int[] array_board = new int[getSize()];
        boolean queen_dans_la_ligne;
        for (int i = 0; i < getSize(); i++) {
            queen_dans_la_ligne = false;
            for (int j = 0; j < getSize(); j++) {
                if (getPiece(i, j) instanceof Queen) {
                    queen_dans_la_ligne = true;
                    array_board[i] = j;
                    break;//On sort de la boucle car 1 seule queen dans chaque lignes
                }
            }
            if (!queen_dans_la_ligne) array_board[i] = -1;
        }
        return array_board;
    }

    /**
     * Opération inverse de board to array
     */
    public Board arrayToBoard(int[] array_board) {
        Board b = new Board(array_board.length);

        for (int i = 0; i < array_board.length; i++) {
            if (array_board[i] != -1) {
                b.placeQueen(i, array_board[i]);
            }
        }
        return b;
    }

    /**
     * A voir si c'est bien comme cela qu'il faut faire car je reconverti en board pour pouvoir
     * utiliser isAccessible
     *
     * @param array
     * @return
     */
    public ArrayList<int[]> getArraySuccessors(int[] array) {
        ArrayList<int[]> alsuccess = new ArrayList<>();
        //Board b = arrayToBoard(array);

        for (int i = 0; i < getSize(); i++) {
            if (array[i] == -1) {
                for (int j = 0; j < getSize(); j++) {
                    //if (b.isAccessible(i, j)) {
                    if (isAccessibleArray(array, i, j)) {
                        int[] tmp = array.clone();
                        tmp[i] = j;
                        alsuccess.add(tmp);
                    }
                }
            }
        }

        /*for (Board bSuccess : b.getNewSuccessors()) {
            alsuccess.add(bSuccess.boardToArray());
        }*/

        return alsuccess;
    }

    public boolean isAccessibleArray(int[] array, int col, int line) {
        for (int i = 0; i < getSize(); i++) {
            if (array[i] == line)
                return false;

            if (col - i >= 0) {
                if (array[col - i] == line + i || array[col - i] == line - i) {
                    return false;
                }
            }

            if (col + i < getSize()) {
                if (array[col + i] == line - i || array[col + i] == line + i) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Normalement il doit y avoir une reine par ligne pour que ce soit une solution
     *
     * @param array
     * @return
     */
    public boolean isSolutionArray(int[] array) {
        boolean solution = true;
        for (int ligne : array) {
            if (ligne == -1) solution = false;
        }
        return solution;
    }

    public ArrayList<int[]> depthFirstSearchArray(int[] initialState) {
        ArrayList<int[]> successeurs = new ArrayList<>();

        if (isSolutionArray(initialState)) {
            successeurs.add(initialState);
            return successeurs;
        }
        for (int[] b_s : getArraySuccessors(initialState)) {
            ArrayList<int[]> chemin_successeur = depthFirstSearchArray(b_s);

            try {
                if (chemin_successeur.size() == 0)
                    throw new NoSuchElementException();

                for (int[] cheminSuccess : chemin_successeur) {
                    successeurs.add(cheminSuccess);
                }
                successeurs.add(b_s);
                return successeurs;
            } catch (NoSuchElementException e) {

            }
        }
        return successeurs;
    }


    public ArrayList<int[]> depthFirstSearchArray() {
        int[] initialState = boardToArray();
        return depthFirstSearchArray(initialState);
    }


    public int getRocksPlayer0() {
        return rocksPlayer0;
    }

    public int getRocksPlayer1() {
        return rocksPlayer1;
    }

    //Quand un player joue un rock on décrémente le nombre qu'il leur reste de rock
    public void useRock(Player player) {
        if (player.getNumber() == 0) {
            rocksPlayer0--;
        } else {
            rocksPlayer1--;
        }
    }


    public float evaluation(Board b, Player currentPlayer, int c, Eval e, Player playing) {
        ArrayList<Board> successors = new ArrayList<>();

        if (b.isFinal()) {
            System.out.println("----------------------------------");

            //Partie nulle
            if (!b.isSolution()) {
                return 0;
            }

            //Partie gagnee
            if (currentPlayer == playing) {
                return Float.POSITIVE_INFINITY;
            }

            //Partie perdue
            else {
                return Float.NEGATIVE_INFINITY;
            }

        }

        if (c == 0) {
            return e.getEval(currentPlayer, b);
        }

        successors = b.getSuccessors2(currentPlayer);

        //Joueur-Machine de jouer
        if (currentPlayer == playing) {
            float score_max = Float.NEGATIVE_INFINITY;

            Player nextPlayer;
            if (currentPlayer == b.getGame().getPlayer0()) {
                nextPlayer = b.getGame().getPlayer1();
            } else {
                nextPlayer = b.getGame().getPlayer0();
            }

            for (Board s : successors) {

                score_max = Math.max(score_max, evaluation(s, nextPlayer, c - 1, e, playing));
            }

            return score_max;
        }

        //Autre joueur de jouer
        else {
            float score_min = Float.POSITIVE_INFINITY;

            Player nextPlayer;
            if (currentPlayer == b.getGame().getPlayer0()) {
                nextPlayer = b.getGame().getPlayer1();
            } else {
                nextPlayer = b.getGame().getPlayer0();
            }

            for (Board s : successors) {

                score_min = Math.min(score_min, evaluation(s, nextPlayer, c - 1, e, playing));

            }


            return score_min;
        }


    }


}
