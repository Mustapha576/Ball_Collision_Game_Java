
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> file = (ArrayList<String>) Files.readAllLines(Paths.get("board.txt"));
            ArrayList<ArrayList<String>> board = new ArrayList<>();
            for (String a : file) {
                ArrayList<String> empty = new ArrayList<>(Arrays.asList(a.split(" ")));
                board.add(empty);
            }

            ArrayList<String> movelist = (ArrayList<String>) Files.readAllLines(Paths.get("move.txt"));
            ArrayList<String> moves = null;
            for (String b : movelist) {
                moves = new ArrayList<>(Arrays.asList(b.split(" ")));
            }

            BufferedWriter output = new BufferedWriter(new FileWriter("output.txt"));
            List<String> playedmoves = new ArrayList<>();

            output.write("Game board:\n");
            for (ArrayList<String> strings : board) {
                String s = String.join(" ", strings);
                output.write(s + "\n");
            }
            output.write("\n");

            int score = 0;
            assert moves != null;
            for (String move : moves) {
                if (move.equals("L")) {
                    for (int i = 0; i < board.size(); i++) {
                        if (board.get(i).contains("*")) {
                            playedmoves.add("L");
                            int item = board.get(i).indexOf("*");
                            if (item == 0) {
                                switch (board.get(i).get(board.get(i).size() - 1)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i).get(board.get(i).size() - 1).equals("R")) {score += 10;}
                                        else if (board.get(i).get(board.get(i).size() - 1).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(i).set(board.get(i).size() - 1, "*");
                                        break;

                                    case "W":
                                        if (board.get(i).get(item + 1).equals("R") || board.get(i).get(item + 1).equals("Y") || board.get(i).get(item + 1).equals("B")) {
                                            if (board.get(i).get(item + 1).equals("R")) {score += 10;}
                                            else if (board.get(i).get(item + 1).equals("Y")) {score += 5;}
                                            else {score -= 5;}
                                            board.get(i).set(item, "X");
                                            board.get(i).set(item + 1, "*");

                                        } else if (board.get(i).get(item + 1).equals("H")) {
                                            board.get(i).set(item, " ");
                                        } else {
                                            board.get(i).set(item, board.get(i).get(item + 1));
                                            board.get(i).set(item + 1, "*");
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(i).get(board.get(i).size() - 1));
                                        board.get(i).set(board.get(i).size() - 1, "*");
                                        break;
                                }
                            } else {
                                switch (board.get(i).get(item - 1)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i).get(item - 1).equals("R")) {score += 10;}
                                        else if (board.get(i).get(item - 1).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(i).set(item - 1, "*");
                                        break;

                                    case "W":
                                        if (item + 1 == board.get(i).size() - 1) {
                                            if (board.get(i).get(0).equals("R") || board.get(i).get(0).equals("Y") || board.get(i).get(0).equals("B")) {
                                                if (board.get(i).get(0).equals("R")) {score += 10;}
                                                else if (board.get(i).get(0).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(i).set(0, "*");

                                            } else if (board.get(i).get(0).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(i).get(0));
                                                board.get(i).set(0, "*");
                                            }
                                        } else {
                                            if (board.get(i).get(item + 1).equals("R") || board.get(i).get(item + 1).equals("Y") || board.get(i).get(item + 1).equals("B")) {
                                                if (board.get(i).get(item + 1).equals("R")) {score += 10;}
                                                else if (board.get(i).get(item + 1).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(i).set(item + 1, "*");

                                            } else if (board.get(i).get(item + 1).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(i).get(item + 1));
                                                board.get(i).set(item + 1, "*");
                                            }
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(i).get(item - 1));
                                        board.get(i).set(item - 1, "*");
                                        break;
                                }
                            }
                        }

                    }
                }
                if (move.equals("R")) {
                    for (int i = 0; i < board.size(); i++) {
                        if (board.get(i).contains("*")) {
                            playedmoves.add("R");
                            int item = board.get(i).indexOf("*");
                            if (item == board.get(i).size() - 1) {
                                switch (board.get(i).get(0)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i).get(0).equals("R")) {score += 10;}
                                        else if (board.get(i).get(0).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(i).set(0, "*");
                                        break;

                                    case "W":
                                        if (board.get(i).get(item - 1).equals("R") || board.get(i).get(item - 1).equals("Y") || board.get(i).get(item - 1).equals("B")) {
                                            if (board.get(i).get(item - 1).equals("R")) {score += 10;}
                                            else if (board.get(i).get(item - 1).equals("Y")) {score += 5;}
                                            else {score -= 5;}
                                            board.get(i).set(item, "X");
                                            board.get(i).set(item - 1, "*");
                                        } else if (board.get(i).get(item - 1).equals("H")) {
                                            board.get(i).set(item, " ");
                                        } else {
                                            board.get(i).set(item, board.get(i).get(item - 1));
                                            board.get(i).set(item - 1, "*");
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(i).get(0));
                                        board.get(i).set(0, "*");
                                        break;
                                }
                            } else {
                                switch (board.get(i).get(item + 1)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i).get(item + 1).equals("R")) {score += 10;}
                                        else if (board.get(i).get(item + 1).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(i).set(item + 1, "*");
                                        break;

                                    case "W":
                                        if (item - 1 == -1) {
                                            if (board.get(i).get(board.get(i).size() - 1).equals("R") || board.get(i).get(board.get(i).size() - 1).equals("Y") || board.get(i).get(board.get(i).size() - 1).equals("B")) {
                                                if (board.get(i).get(board.get(i).size() - 1).equals("R")) {score += 10;}
                                                else if (board.get(i).get(board.get(i).size() - 1).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(i).set(board.get(i).size() - 1, "*");

                                            } else if (board.get(i).get(board.get(i).size() - 1).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(i).get(board.get(i).size() - 1));
                                                board.get(i).set(board.get(i).size() - 1, "*");
                                            }
                                        } else {
                                            if (board.get(i).get(item - 1).equals("R") || board.get(i).get(item - 1).equals("Y") || board.get(i).get(item - 1).equals("B")) {
                                                if (board.get(i).get(item - 1).equals("R")) {score += 10;}
                                                else if (board.get(i).get(item - 1).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(i).set(item - 1, "*");

                                            } else if (board.get(i).get(item - 1).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(i).get(item - 1));
                                                board.get(i).set(item - 1, "*");
                                            }
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(i).get(item + 1));
                                        board.get(i).set(item + 1, "*");
                                        break;
                                }
                            }

                        }

                    }
                }
                if (move.equals("U")) {
                    for (int i = 0; i < board.size(); i++) {
                        if (board.get(i).contains("*")) {
                            playedmoves.add("U");
                            int item = board.get(i).indexOf("*");
                            if (i == 0) {
                                switch (board.get(board.size() - 1).get(item)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i).get(board.size() - 1).equals("R")) {score += 10;}
                                        else if (board.get(i).get(board.size() - 1).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(board.size() - 1).set(item, "*");
                                        break;

                                    case "W":
                                        if (board.get(i + 1).get(item).equals("R") || board.get(i + 1).get(item).equals("Y") || board.get(i + 1).get(item).equals("B")) {
                                            if (board.get(i + 1).get(item).equals("R")) {score += 10;}
                                            else if (board.get(i + 1).get(item).equals("Y")) {score += 5;}
                                            else {score -= 5;}
                                            board.get(i).set(item, "X");
                                            board.get(i + 1).set(item, "*");

                                        } else if (board.get(i + 1).get(item).equals("H")) {
                                            board.get(i).set(item, " ");
                                        } else {
                                            board.get(i).set(item, board.get(i + 1).get(item));
                                            board.get(i + 1).set(item, "*");
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(board.size() - 1).get(item));
                                        board.get(board.size() - 1).set(item, "*");
                                        break;
                                }
                            } else {
                                switch (board.get(i - 1).get(item)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i - 1).get(item).equals("R")) {score += 10;}
                                        else if (board.get(i - 1).get(item).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(i - 1).set(item, "*");
                                        break;

                                    case "W":
                                        if (i == board.size() - 1) {
                                            if (board.get(0).get(item).equals("R") || board.get(0).get(item).equals("Y") || board.get(0).get(item).equals("B")) {
                                                if (board.get(0).get(item).equals("R")) {score += 10;}
                                                else if (board.get(0).get(item).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(0).set(item, "*");

                                            } else if (board.get(0).get(item).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(0).get(item));
                                                board.get(0).set(item, "*");
                                            }
                                        } else {
                                            if (board.get(i + 1).get(item).equals("R") || board.get(i + 1).get(item).equals("Y") || board.get(i + 1).get(item).equals("B")) {
                                                if (board.get(i + 1).get(item).equals("R")) {score += 10;}
                                                else if (board.get(i).get(item).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(i + 1).set(item, "*");

                                            } else if (board.get(i + 1).get(item).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(i + 1).get(item));
                                                board.get(i + 1).set(item, "*");
                                            }
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(i - 1).get(item));
                                        board.get(i - 1).set(item, "*");
                                        break;
                                }
                            }
                            break;
                        }

                    }
                }
                if (move.equals("D")) {
                    for (int i = 0; i < board.size(); i++) {
                        if (board.get(i).contains("*")) {
                            playedmoves.add("D");
                            int item = board.get(i).indexOf("*");
                            if (i == board.size() - 1) {
                                switch (board.get(0).get(item)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(0).get(item).equals("R")) {score += 10;}
                                        else if (board.get(0).get(item).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(0).set(item, "*");
                                        break;

                                    case "W":
                                        if (board.get(i - 1).get(item).equals("R") || board.get(i - 1).get(item).equals("Y") || board.get(i - 1).get(item).equals("B")) {
                                            if (board.get(i - 1).get(item).equals("R")) {score += 10;}
                                            else if (board.get(i - 1).get(item).equals("Y")) {score += 5;}
                                            else {score -= 5;}
                                            board.get(i).set(item, "X");
                                            board.get(i - 1).set(item, "*");

                                        } else if (board.get(i - 1).get(item).equals("H")) {
                                            board.get(i).set(item, " ");
                                        } else {
                                            board.get(i).set(item, board.get(i - 1).get(item));
                                            board.get(i - 1).set(item, "*");
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(0).get(item));
                                        board.get(0).set(item, "*");
                                        break;
                                }
                            } else {
                                switch (board.get(i + 1).get(item)) {
                                    case "R":
                                    case "Y":
                                    case "B":
                                        if (board.get(i + 1).get(item).equals("R")) {score += 10;}
                                        else if (board.get(i + 1).get(item).equals("Y")) {score += 5;}
                                        else {score -= 5;}
                                        board.get(i).set(item, "X");
                                        board.get(i + 1).set(item, "*");
                                        break;

                                    case "W":
                                        if (i == 0) {
                                            if (board.get(board.size() - 1).get(item).equals("R") || board.get(board.size() - 1).get(item).equals("Y") || board.get(board.size() - 1).get(item).equals("B")) {
                                                if (board.get(board.size() - 1).get(item).equals("R")) {score += 10;}
                                                else if (board.get(board.size() - 1).get(item).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(board.size() - 1).set(item, "*");

                                            } else if (board.get(board.size() - 1).get(item).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(board.size() - 1).get(item));
                                                board.get(board.size() - 1).set(item, "*");
                                            }
                                        } else {
                                            if (board.get(i - 1).get(item).equals("R") || board.get(i - 1).get(item).equals("Y") || board.get(i - 1).get(item).equals("B")) {
                                                if (board.get(i - 1).get(item).equals("R")) {score += 10;}
                                                else if (board.get(i - 1).get(item).equals("Y")) {score += 5;}
                                                else {score -= 5;}
                                                board.get(i).set(item, "X");
                                                board.get(i - 1).set(item, "*");

                                            } else if (board.get(i - 1).get(item).equals("H")) {
                                                board.get(i).set(item, " ");
                                            } else {
                                                board.get(i).set(item, board.get(i - 1).get(item));
                                                board.get(i - 1).set(item, "*");
                                            }
                                        }
                                        break;

                                    case "H":
                                        board.get(i).set(item, " ");
                                        break;

                                    default:
                                        board.get(i).set(item, board.get(i + 1).get(item));
                                        board.get(i + 1).set(item, "*");
                                        break;
                                }
                            }
                            break;
                        }
                    }
                }
            }

            output.write("Your movement is: \n");
            String asd = String.join(" ", playedmoves);
            output.write(asd);
            output.write("\n");
            output.write("\n");
            output.write("Your output is: \n");
            for (ArrayList<String> strings : board) {
                String s = String.join(" ", strings);
                output.write(s + "\n");
            }
            output.write("\n");
            for (ArrayList<String> strings : board) {
                if(strings.contains(" ")){
                    output.write("Game Over!\n");
                }
            }
            output.write("Score: " + score);
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
