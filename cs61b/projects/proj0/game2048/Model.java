package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /** Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        /** Question 4 start. */

        board.setViewingPerspective(side);                         /** Sets our side as North. */

        for (int col = 0; col < board.size(); col++) {              /** We use columns this time in our for loop first */
                                                                    /** as our viewing perspective is North. */
            boolean hasMerged = false;
            int limit = board.size() - 1;
            for (int row = board.size() - 1; row >= 0; row--) {   /** Goes from top of the column down, with the index 3
             /** representing our */
                /** 4th element for instance. */
                if (board.tile(col, row) == null || row == limit) {
                                                                          /** Does nothing. */
                }
                /** Below is for non-null limits which do not merge with our value (but we have to move our value anyway). */
                else if (board.tile(col, limit) != null && board.tile(col, limit).value() != board.tile(col, row).value()) {
                    limit--;
                    if (row < limit) {
                        hasMerged = board.move(col, limit, board.tile(col, row));   /** Found in 'Board.java', */
                                                                                    /** moves the tile value (even thought the dot
                                                                                    /** function is in an equality) */
                                                                                    /** and returns if a move is a merge. */
                        changed = true;

                    }

                }
                /** Below is for null limits or values that merge (which are obviously with non-null limits). */
                else {


                    if (board.tile(col, limit) != null) {
                        score += board.tile(col, limit).value() * 2;                  /** Updates the score, which only happens when there is a merging, */
                                                                                      /** not counting when the limit is null, since then there is no merging. */
                        hasMerged = board.move(col, limit, board.tile(col, row));

                        changed = true;

                        limit--;                                  /** Limit reduced since only two tiles can merge. */

                    }
                    else {
                        hasMerged = board.move(col, limit , board.tile(col, row));         /** 'hasMerged' is always false when the limit is null as in this case. */

                        changed = true;
                    }
                }
            }
    }

        board.setViewingPerspective(Side.NORTH);              /** Resets our North side to normal. */


        /** Question 4 end. */
        /** Below is what has to be done at the end of our tilt method. */

        checkGameOver();              /** Checks if the game is over and sets the gameOver variable appropriately. */
        if (changed) {
            setChanged();             /** Marks our set as changed. */
        }
        return changed;
    }





    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    /** Question 1 */
    public static boolean emptySpaceExists(Board b) {
        for (int x = 0; x < b.size(); x += 1) {                     /** 'for (statement 1; statement 2; statement 3) { // code block to be executed}'.*/
            for (int y = 0; y < b.size(); y += 1) {                 /** Statement 1 is executed (one time) before the execution of the code block, statement 2 defines the condition for executing the code block and  */
                if (b.tile(x, y) == null) {                         /** statement 3 is executed (every time) after the code block has been executed. */
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    /** Question 2 */
    public static boolean maxTileExists(Board b) {
        for (int x = 0; x < b.size();  x++){                /** x++ is an altenative way of adding 1 to x. */
            for (int y = 0; y < b.size(); y++){
                if (b.tile(x, y) == null){
                    continue;
                }
                Tile currentTile = b.tile(x, y);             /** Object is of data  or class 'Tile' defined in 'Tile' file. */
                if (currentTile.value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    /** Question 3 */
    public static boolean atLeastOneMoveExists(Board b) {
        return emptySpaceExists(b) || mergeable(b);        /** exp1 || exp2 evaluates exp1.
                                                           If exp1 is true then exp2 is not evaluated (known as short circuit evaluation).
                                                           If exp1 returns false then exp 2 is evaluated.
                                                           If exp1 OR exp2 is true then (exp1||exp2) evaluates as true. */
    }

    /** Checks if the neighbour tile is able to be merged with. */
    public static boolean mergeable(Board b) {
        int boardSize = b.size();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Tile currentTile = b.tile(col, row);
                if (isEqualToRight(b, currentTile) || isEqualToAbove(b, currentTile)) {
                    return true;
                }
            }
        }
        return false;
    }


    /** Checks if the tile to the right is able to be merged with. */
    public static boolean isEqualToRight(Board b, Tile t) {
        int boardSize = b.size();
        if (t.col() + 1 < boardSize) {
            return b.tile(t.col() + 1, t.row()).value() == t.value();
        }
        return false;
    }

    /** Checks if the tile above is able to be merged with. */
    public static boolean isEqualToAbove(Board b, Tile t) {
        int boardSize = b.size();

        if (t.row() + 1 < boardSize) {
            return b.tile(t.col(), t.row() + 1).value() == t.value();
        }
        return false;
    }




    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}

