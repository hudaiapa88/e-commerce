package com.uc.ecommerce.core.rsql.parser;



import com.uc.ecommerce.core.rsql.parser.ast.ComparisonNode;
import com.uc.ecommerce.core.rsql.parser.ast.LogicalOperator;
import com.uc.ecommerce.core.rsql.parser.ast.Node;
import com.uc.ecommerce.core.rsql.parser.ast.NodesFactory;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class Parser implements ParserConstants {
    private NodesFactory factory;
    public ParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private int jj_gen;
    private final int[] jj_la1;
    private static int[] jj_la1_0;
    private List<int[]> jj_expentries;
    private int[] jj_expentry;
    private int jj_kind;

    static {
        jj_la1_init_0();
    }

    public Parser(InputStream stream, String encoding, NodesFactory factory) {
        this(stream, encoding);
        this.factory = factory;
    }

    private String unescape(String s) {
        if (s.indexOf(92) < 0) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder(s.length());

            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '\\') {
                    ++i;
                }

                if (i < s.length()) {
                    sb.append(s.charAt(i));
                }
            }

            return sb.toString();
        }
    }

    public final Node Input() throws ParseException {
        Node node = this.Or();
        this.jj_consume_token(0);
        return node;
    }

    public final Node Or() throws ParseException {
        List<Node> nodes = new ArrayList(3);
        Node node = this.And();
        nodes.add(node);

        while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
                case 9:
                    this.jj_consume_token(9);
                    node = this.And();
                    nodes.add(node);
                    break;
                default:
                    this.jj_la1[0] = this.jj_gen;
                    return (Node) (nodes.size() != 1 ? this.factory.createLogicalNode(LogicalOperator.OR, nodes) : (Node) nodes.get(0));
            }
        }
    }

    public final Node And() throws ParseException {
        List<Node> nodes = new ArrayList(3);
        Node node = this.Constraint();
        nodes.add(node);

        while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
                case 8:
                    this.jj_consume_token(8);
                    node = this.Constraint();
                    nodes.add(node);
                    break;
                default:
                    this.jj_la1[1] = this.jj_gen;
                    return (Node) (nodes.size() != 1 ? this.factory.createLogicalNode(LogicalOperator.AND, nodes) : (Node) nodes.get(0));
            }
        }
    }

    public final Node Constraint() throws ParseException {
        Object node;
        switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 5:
                node = this.Comparison();
                break;
            case 10:
                node = this.Group();
                break;
            default:
                this.jj_la1[2] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
        }

        return (Node) node;
    }

    public final Node Group() throws ParseException {
        this.jj_consume_token(10);
        Node node = this.Or();
        this.jj_consume_token(11);
        return node;
    }

    public final ComparisonNode Comparison() throws ParseException {
        String sel = this.Selector();
        String op = this.Operator();
        List<String> args = this.Arguments();
        return this.factory.createComparisonNode(op, sel, args);
    }

    public final String Selector() throws ParseException {
        this.token = this.jj_consume_token(5);
        return this.token.image;
    }

    public final String Operator() throws ParseException {
        switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 12:
                this.token = this.jj_consume_token(12);
                break;
            case 13:
                this.token = this.jj_consume_token(13);
                break;
            default:
                this.jj_la1[3] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
        }

        return this.token.image;
    }

    public final List<String> Arguments() throws ParseException {
        switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 5:
            case 6:
            case 7:
                Object value = this.Argument();
                return Arrays.asList((String) value);
            case 8:
            case 9:
            default:
                this.jj_la1[4] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            case 10:
                this.jj_consume_token(10);
                value = this.CommaSepArguments();
                this.jj_consume_token(11);
                return (List) value;
        }
    }

    public final List<String> CommaSepArguments() throws ParseException {
        List<String> list = new ArrayList(3);
        String arg = this.Argument();
        list.add(arg);

        while (true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
                case 9:
                    this.jj_consume_token(9);
                    arg = this.Argument();
                    list.add(arg);
                    break;
                default:
                    this.jj_la1[5] = this.jj_gen;
                    return list;
            }
        }
    }

    public final String Argument() throws ParseException {
        switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 5:
                this.token = this.jj_consume_token(5);
                return this.token.image;
            case 6:
            case 7:
                switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
                    case 6:
                        this.token = this.jj_consume_token(6);
                        break;
                    case 7:
                        this.token = this.jj_consume_token(7);
                        break;
                    default:
                        this.jj_la1[6] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                }

                return this.unescape(this.token.image.substring(1, this.token.image.length() - 1));
            default:
                this.jj_la1[7] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
        }
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{512, 256, 1056, 12288, 1248, 512, 192, 224};
    }

    public Parser(InputStream stream) {
        this(stream, (String) null);
    }

    public Parser(InputStream stream, String encoding) {
        this.jj_la1 = new int[8];
        this.jj_expentries = new ArrayList();
        this.jj_kind = -1;

        try {
            this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException(var4);
        }

        this.token_source = new ParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;

        for (int i = 0; i < 8; ++i) {
            this.jj_la1[i] = -1;
        }

    }

    public void ReInit(InputStream stream) {
        this.ReInit(stream, (String) null);
    }

    public void ReInit(InputStream stream, String encoding) {
        try {
            this.jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException(var4);
        }

        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;

        for (int i = 0; i < 8; ++i) {
            this.jj_la1[i] = -1;
        }

    }

    public Parser(Reader stream) {
        this.jj_la1 = new int[8];
        this.jj_expentries = new ArrayList();
        this.jj_kind = -1;
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new ParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;

        for (int i = 0; i < 8; ++i) {
            this.jj_la1[i] = -1;
        }

    }

    public void ReInit(Reader stream) {
        this.jj_input_stream.ReInit(stream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;

        for (int i = 0; i < 8; ++i) {
            this.jj_la1[i] = -1;
        }

    }

    public Parser(ParserTokenManager tm) {
        this.jj_la1 = new int[8];
        this.jj_expentries = new ArrayList();
        this.jj_kind = -1;
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;

        for (int i = 0; i < 8; ++i) {
            this.jj_la1[i] = -1;
        }

    }

    public void ReInit(ParserTokenManager tm) {
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;

        for (int i = 0; i < 8; ++i) {
            this.jj_la1[i] = -1;
        }

    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = this.token).next != null) {
            this.token = this.token.next;
        } else {
            this.token = this.token.next = this.token_source.getNextToken();
        }

        this.jj_ntk = -1;
        if (this.token.kind == kind) {
            ++this.jj_gen;
            return this.token;
        } else {
            this.token = oldToken;
            this.jj_kind = kind;
            throw this.generateParseException();
        }
    }

    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        } else {
            this.token = this.token.next = this.token_source.getNextToken();
        }

        this.jj_ntk = -1;
        ++this.jj_gen;
        return this.token;
    }

    public final Token getToken(int index) {
        Token t = this.token;

        for (int i = 0; i < index; ++i) {
            if (t.next != null) {
                t = t.next;
            } else {
                t = t.next = this.token_source.getNextToken();
            }
        }

        return t;
    }

    private int jj_ntk() {
        return (this.jj_nt = this.token.next) == null ? (this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind) : (this.jj_ntk = this.jj_nt.kind);
    }

    public ParseException generateParseException() {
        this.jj_expentries.clear();
        boolean[] la1tokens = new boolean[14];
        if (this.jj_kind >= 0) {
            la1tokens[this.jj_kind] = true;
            this.jj_kind = -1;
        }

        int i;
        int j;
        for (i = 0; i < 8; ++i) {
            if (this.jj_la1[i] == this.jj_gen) {
                for (j = 0; j < 32; ++j) {
                    if ((jj_la1_0[i] & 1 << j) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }

        for (i = 0; i < 14; ++i) {
            if (la1tokens[i]) {
                this.jj_expentry = new int[1];
                this.jj_expentry[0] = i;
                this.jj_expentries.add(this.jj_expentry);
            }
        }

        int[][] exptokseq = new int[this.jj_expentries.size()][];

        for (j = 0; j < this.jj_expentries.size(); ++j) {
            exptokseq[j] = (int[]) this.jj_expentries.get(j);
        }

        return new ParseException(this.token, exptokseq, tokenImage);
    }

    public final void enable_tracing() {
    }

    public final void disable_tracing() {
    }
}