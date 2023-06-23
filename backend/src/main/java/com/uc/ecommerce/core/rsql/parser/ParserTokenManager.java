package com.uc.ecommerce.core.rsql.parser;


import java.io.IOException;
import java.io.PrintStream;

public class ParserTokenManager implements ParserConstants {
    public PrintStream debugStream;
    static final long[] jjbitVec0 = new long[]{-2L, -1L, -1L, -1L};
    static final long[] jjbitVec2 = new long[]{0L, 0L, -1L, -1L};
    static final int[] jjnextStates = new int[]{23, 26, 7, 9, 10, 2, 4, 5, 14, 15};
    public static final String[] jjstrLiteralImages = new String[]{"", null, null, null, null, null, null, null, null, null, "(", ")", null, null};
    public static final String[] lexStateNames = new String[]{"DEFAULT"};
    static final long[] jjtoToken = new long[]{16353L};
    static final long[] jjtoSkip = new long[]{6L};
    protected SimpleCharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;

    public void setDebugStream(PrintStream ds) {
        this.debugStream = ds;
    }

    private final int jjStopStringLiteralDfa_0(int pos, long active0) {
        return -1;
    }

    private final int jjStartNfa_0(int pos, long active0) {
        return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    private int jjStopAtPos(int pos, int kind) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        return pos + 1;
    }

    private int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '\t':
                return this.jjStartNfaWithStates_0(0, 2, 0);
            case ' ':
                return this.jjStartNfaWithStates_0(0, 1, 23);
            case '(':
                return this.jjStopAtPos(0, 10);
            case ')':
                return this.jjStopAtPos(0, 11);
            default:
                return this.jjMoveNfa_0(1, 0);
        }
    }

    private int jjStartNfaWithStates_0(int pos, int kind, int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;

        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException var4) {
            return pos + 1;
        }

        return this.jjMoveNfa_0(state, pos + 1);
    }

    private int jjMoveNfa_0(int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 27;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;

        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }

            long l;
            if (this.curChar < '@') {
                l = 1L << this.curChar;

                do {
                    --i;
                    switch (this.jjstateSet[i]) {
                        case 0:
                            if ((-8646932755092865025L & l) != 0L) {
                                if (kind > 5) {
                                    kind = 5;
                                }

                                this.jjCheckNAdd(0);
                            }
                            break;
                        case 1:
                            if ((-8646932755092865025L & l) != 0L) {
                                if (kind > 5) {
                                    kind = 5;
                                }

                                this.jjCheckNAdd(0);
                            } else if ((5764607523034234880L & l) != 0L) {
                                if (kind > 13) {
                                    kind = 13;
                                }

                                this.jjstateSet[this.jjnewStateCnt++] = 18;
                            } else if (this.curChar == ' ') {
                                this.jjAddStates(0, 1);
                            } else if (this.curChar == '!') {
                                this.jjCheckNAdd(15);
                            } else if (this.curChar == '=') {
                                this.jjCheckNAddTwoStates(14, 15);
                            } else if (this.curChar == ',') {
                                if (kind > 9) {
                                    kind = 9;
                                }
                            } else if (this.curChar == ';') {
                                if (kind > 8) {
                                    kind = 8;
                                }
                            } else if (this.curChar == '"') {
                                this.jjCheckNAddStates(2, 4);
                            } else if (this.curChar == '\'') {
                                this.jjCheckNAddStates(5, 7);
                            }
                        case 2:
                        case 7:
                        case 14:
                        case 21:
                        case 22:
                        case 23:
                        default:
                            break;
                        case 3:
                            this.jjCheckNAddStates(5, 7);
                            break;
                        case 4:
                            if ((-549755813889L & l) != 0L) {
                                this.jjCheckNAddStates(5, 7);
                            }
                            break;
                        case 5:
                            if (this.curChar == '\'' && kind > 6) {
                                kind = 6;
                            }
                            break;
                        case 6:
                            if (this.curChar == '"') {
                                this.jjCheckNAddStates(2, 4);
                            }
                            break;
                        case 8:
                            this.jjCheckNAddStates(2, 4);
                            break;
                        case 9:
                            if ((-17179869185L & l) != 0L) {
                                this.jjCheckNAddStates(2, 4);
                            }
                            break;
                        case 10:
                            if (this.curChar == '"' && kind > 7) {
                                kind = 7;
                            }
                            break;
                        case 11:
                            if (this.curChar == ';' && kind > 8) {
                                kind = 8;
                            }
                            break;
                        case 12:
                            if (this.curChar == ',' && kind > 9) {
                                kind = 9;
                            }
                            break;
                        case 13:
                            if (this.curChar == '=') {
                                this.jjCheckNAddTwoStates(14, 15);
                            }
                            break;
                        case 15:
                            if (this.curChar == '=' && kind > 12) {
                                kind = 12;
                            }
                            break;
                        case 16:
                            if (this.curChar == '!') {
                                this.jjCheckNAdd(15);
                            }
                            break;
                        case 17:
                            if ((5764607523034234880L & l) != 0L) {
                                if (kind > 13) {
                                    kind = 13;
                                }

                                this.jjstateSet[this.jjnewStateCnt++] = 18;
                            }
                            break;
                        case 18:
                            if (this.curChar == '=' && kind > 13) {
                                kind = 13;
                            }
                            break;
                        case 19:
                            if (this.curChar == ' ') {
                                this.jjAddStates(0, 1);
                            }
                            break;
                        case 20:
                            if (this.curChar == ' ' && kind > 8) {
                                kind = 8;
                            }
                            break;
                        case 24:
                            if (this.curChar == ' ' && kind > 9) {
                                kind = 9;
                            }
                    }
                } while (i != startsAt);
            } else if (this.curChar < 128) {
                l = 1L << (this.curChar & 63);

                do {
                    --i;
                    switch (this.jjstateSet[i]) {
                        case 0:
                        case 1:
                            if ((-4611686018427387905L & l) != 0L) {
                                if (kind > 5) {
                                    kind = 5;
                                }

                                this.jjCheckNAdd(0);
                            }
                            break;
                        case 2:
                            if (this.curChar == '\\') {
                                this.jjstateSet[this.jjnewStateCnt++] = 3;
                            }
                            break;
                        case 3:
                            this.jjCheckNAddStates(5, 7);
                            break;
                        case 4:
                            if ((-268435457L & l) != 0L) {
                                this.jjCheckNAddStates(5, 7);
                            }
                        case 5:
                        case 6:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 24:
                        default:
                            break;
                        case 7:
                            if (this.curChar == '\\') {
                                this.jjstateSet[this.jjnewStateCnt++] = 8;
                            }
                            break;
                        case 8:
                            this.jjCheckNAddStates(2, 4);
                            break;
                        case 9:
                            if ((-268435457L & l) != 0L) {
                                this.jjCheckNAddStates(2, 4);
                            }
                            break;
                        case 14:
                            if ((576460743847706622L & l) != 0L) {
                                this.jjAddStates(8, 9);
                            }
                            break;
                        case 21:
                            if (this.curChar == 'd') {
                                this.jjstateSet[this.jjnewStateCnt++] = 20;
                            }
                            break;
                        case 22:
                            if (this.curChar == 'n') {
                                this.jjstateSet[this.jjnewStateCnt++] = 21;
                            }
                            break;
                        case 23:
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 25;
                            } else if (this.curChar == 'a') {
                                this.jjstateSet[this.jjnewStateCnt++] = 22;
                            }
                            break;
                        case 25:
                            if (this.curChar == 'r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 24;
                            }
                            break;
                        case 26:
                            if (this.curChar == 'o') {
                                this.jjstateSet[this.jjnewStateCnt++] = 25;
                            }
                    }
                } while (i != startsAt);
            } else {
                int hiByte = this.curChar >> 8;
                int i1 = hiByte >> 6;
                long l1 = 1L << (hiByte & 63);
                int i2 = (this.curChar & 255) >> 6;
                long l2 = 1L << (this.curChar & 63);

                do {
                    --i;
                    switch (this.jjstateSet[i]) {
                        case 0:
                        case 1:
                            if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
                                if (kind > 5) {
                                    kind = 5;
                                }

                                this.jjCheckNAdd(0);
                            }
                        case 2:
                        case 5:
                        case 6:
                        case 7:
                        default:
                            break;
                        case 3:
                        case 4:
                            if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
                                this.jjCheckNAddStates(5, 7);
                            }
                            break;
                        case 8:
                        case 9:
                            if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
                                this.jjCheckNAddStates(2, 4);
                            }
                    }
                } while (i != startsAt);
            }

            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }

            ++curPos;
            if ((i = this.jjnewStateCnt) == (startsAt = 27 - (this.jjnewStateCnt = startsAt))) {
                return curPos;
            }

            try {
                this.curChar = this.input_stream.readChar();
            } catch (IOException var13) {
                return curPos;
            }
        }
    }

    private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
        switch (hiByte) {
            case 0:
                if ((jjbitVec2[i2] & l2) != 0L) {
                    return true;
                }

                return false;
            default:
                return (jjbitVec0[i1] & l1) != 0L;
        }
    }

    public ParserTokenManager(SimpleCharStream stream) {
        this.debugStream = System.out;
        this.jjrounds = new int[27];
        this.jjstateSet = new int[54];
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = stream;
    }

    public ParserTokenManager(SimpleCharStream stream, int lexState) {
        this(stream);
        this.SwitchTo(lexState);
    }

    public void ReInit(SimpleCharStream stream) {
        this.jjmatchedPos = this.jjnewStateCnt = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        this.ReInitRounds();
    }

    private void ReInitRounds() {
        this.jjround = -2147483647;

        for (int i = 27; i-- > 0; this.jjrounds[i] = Integer.MIN_VALUE) {
        }

    }

    public void ReInit(SimpleCharStream stream, int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }

    public void SwitchTo(int lexState) {
        if (lexState < 1 && lexState >= 0) {
            this.curLexState = lexState;
        } else {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
    }

    protected Token jjFillToken() {
        String im = jjstrLiteralImages[this.jjmatchedKind];
        String curTokenImage = im == null ? this.input_stream.GetImage() : im;
        int beginLine = this.input_stream.getBeginLine();
        int beginColumn = this.input_stream.getBeginColumn();
        int endLine = this.input_stream.getEndLine();
        int endColumn = this.input_stream.getEndColumn();
        Token t = Token.newToken(this.jjmatchedKind, curTokenImage);
        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;
        return t;
    }

    public Token getNextToken() {


        Token matchedToken;
        do {
            try {
                this.curChar = this.input_stream.BeginToken();
            } catch (IOException var7) {
                this.jjmatchedKind = 0;
                matchedToken = this.jjFillToken();
                return matchedToken;
            }

            this.jjmatchedKind = Integer.MAX_VALUE;
            this.jjmatchedPos = 0;
            int curPos = this.jjMoveStringLiteralDfa0_0();
            if (this.jjmatchedKind == Integer.MAX_VALUE) {
                int error_line = this.input_stream.getEndLine();
                int error_column = this.input_stream.getEndColumn();
                String error_after = null;
                boolean EOFSeen = false;

                try {
                    this.input_stream.readChar();
                    this.input_stream.backup(1);
                } catch (IOException var8) {
                    EOFSeen = true;
                    error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
                    if (this.curChar != '\n' && this.curChar != '\r') {
                        ++error_column;
                    } else {
                        ++error_line;
                        error_column = 0;
                    }
                }

                if (!EOFSeen) {
                    this.input_stream.backup(1);
                    error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
                }

                throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
            }

            if (this.jjmatchedPos + 1 < curPos) {
                this.input_stream.backup(curPos - this.jjmatchedPos - 1);
            }
        } while ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) == 0L);

        matchedToken = this.jjFillToken();
        return matchedToken;
    }

    private void jjCheckNAdd(int state) {
        if (this.jjrounds[state] != this.jjround) {
            this.jjstateSet[this.jjnewStateCnt++] = state;
            this.jjrounds[state] = this.jjround;
        }

    }

    private void jjAddStates(int start, int end) {
        do {
            this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
        } while (start++ != end);

    }

    private void jjCheckNAddTwoStates(int state1, int state2) {
        this.jjCheckNAdd(state1);
        this.jjCheckNAdd(state2);
    }

    private void jjCheckNAddStates(int start, int end) {
        do {
            this.jjCheckNAdd(jjnextStates[start]);
        } while (start++ != end);

    }
}