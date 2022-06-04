import java.util.Scanner;


public class Kalkulator {
    public boolean vprasaj = false;
    public Sequence<Stack<String>> sekvenca = new ArrayDeque();
    public Stack<String> zacasni= new ArrayDeque<>();
    public boolean pogoj = false;

    public Kalkulator() throws CollectionException {
        int velikost = 42;
        for (int  i = 0;i < velikost;i++){
            sekvenca.add(new ArrayDeque<>());
        }
    }
    public void pocistiVse() throws CollectionException {
        for (int i = 0;i<this.sekvenca.size();i++){
            while(!this.sekvenca.get(i).isEmpty()){this.sekvenca.get(i).pop();}
        }
        vprasaj = false;
        pogoj = false;
    }
    public static Scanner funScanner;
    public static void funScan(Scanner izMaina){
        funScanner = izMaina;
    }

    public void procesiraj(String niz) throws CollectionException {
        vprasaj=false;
        Stack i = this.sekvenca.get(0);
        if (niz.charAt(0)=='?'){
            vprasaj=true;
            niz = niz.substring(1);}
        if ((vprasaj==true && pogoj==true) || vprasaj==false){
            switch (niz) {
                case "echo":
                    if (!i.isEmpty())
                        System.out.println(i.top());
                    else
                        System.out.println();
                    break;
                case "pop":
                    i.pop();
                    break;
                case "dup":
                    i.push(i.top());
                    break;
                case "dup2":
                    String x = i.pop().toString();
                    String y = i.pop().toString();
                    i.push(y);
                    i.push(x);
                    i.push(y);
                    i.push(x);
                    break;
                case "swap":
                    x = i.pop().toString();
                    y = i.pop().toString();
                    i.push(x);
                    i.push(y);
                    break;
                case "char":
                    String n = i.pop().toString();
                    char c = (char) Integer.parseInt(n);
                    i.push(c);
                    break;
                case "even":
                    int check = Integer.parseInt(i.pop().toString());
                    if (check % 2 == 0)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case "odd":
                    check = Integer.parseInt(i.pop().toString());
                    if (check % 2 == 1 || check % 2 == -1)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case "!":
                    check = Integer.parseInt(i.pop().toString());
                    int vsota = 1;
                    for (int xyz = 1; xyz <= check; xyz++) {
                        vsota *= xyz;
                    }
                    i.push(vsota);
                    break;
                case "len":
                    n = i.pop().toString();
                    i.push(n.length());
                    break;
                case "<>":
                    int x1 = Integer.parseInt(i.pop().toString());
                    int y1 = Integer.parseInt(i.pop().toString());
                    if (x1 == y1)
                        i.push(0);
                    else
                        i.push(1);
                    break;
                case "<":
                    y1 = Integer.parseInt(i.pop().toString());
                    x1 = Integer.parseInt(i.pop().toString());
                    if (x1 < y1)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case "<=":
                    y1 = Integer.parseInt(i.pop().toString());
                    x1 = Integer.parseInt(i.pop().toString());
                    if (x1 <= y1)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case "==":
                    x1 = Integer.parseInt(i.pop().toString());
                    y1 = Integer.parseInt(i.pop().toString());
                    if (x1 == y1)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case ">":
                    y1 = Integer.parseInt(i.pop().toString());
                    x1 = Integer.parseInt(i.pop().toString());
                    if (x1 > y1)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case ">=":
                    y1 = Integer.parseInt(i.pop().toString());
                    x1 = Integer.parseInt(i.pop().toString());
                    if (x1 >= y1)
                        i.push(1);
                    else
                        i.push(0);
                    break;
                case "+":
                    x1 = Integer.parseInt(i.pop().toString());
                    y1 = Integer.parseInt(i.pop().toString());
                    i.push(x1 + y1);
                    break;
                case "-":
                    y1 = Integer.parseInt(i.pop().toString());
                    x1 = Integer.parseInt(i.pop().toString());
                    i.push(x1 - y1);

                    break;
                case "*":
                    x1 = Integer.parseInt(i.pop().toString());
                    y1 = Integer.parseInt(i.pop().toString());
                    i.push(x1 * y1);
                    break;
                case "/":
                    y1 = Integer.parseInt(i.pop().toString());
                    x1 = Integer.parseInt(i.pop().toString());
                    if (y1 == 0){
                        i.push(0);
                        break;
                    }
                    i.push(x1 / y1);
                    break;
                case "%":
                    x1 = Integer.parseInt(i.pop().toString());
                    y1 = Integer.parseInt(i.pop().toString());
                    i.push(y1 % x1);
                    break;
                case ".":
                    x = i.pop().toString();
                    y = i.pop().toString();
                    i.push(y + "" + x);
                    break;
                case "rnd":
                    x1 = Integer.parseInt(i.pop().toString());
                    y1 = Integer.parseInt(i.pop().toString());
                    int min = x1;
                    int max = y1;
                    if (x1 > y1)
                        min = y1;
                    max = x1;
                    int rndG = (int) (Math.random() * (max - min + 1)) + min;
                    i.push(rndG);
                    break;
                case "then":
                    String check1 = i.pop().toString();
                    if ( !check1.equals("0"))
                        pogoj = true;
                    else
                        pogoj = false;
                    break;
                case "else":
                    if (pogoj == true)
                        pogoj = false;
                    else
                        pogoj = true;
                    break;
                case "print":
                    Stack<String> zacasen1 = new ArrayDeque<>();
                    int indeks = Integer.parseInt(i.pop().toString());
                    Stack izbran = this.sekvenca.get(indeks);
                    int velikost = izbran.size();
                    if (izbran.isEmpty()){
                        System.out.println();
                        break;
                    }
                    if (velikost==1){
                        System.out.println(izbran.top());
                        break;
                    }

                    for (int xyz=0;xyz<velikost;xyz++){
                        x = izbran.pop().toString();
                        zacasen1.push(x);
                    }
                    izbran.push(zacasen1.top());
                    String izpis = zacasen1.pop().toString();
                    velikost--;
                    for (int xyz = 0;xyz<velikost;xyz++){
                        izbran.push(zacasen1.top());
                        izpis+=" " + zacasen1.pop().toString();
                    }
                    System.out.println(izpis);
                    break;
                case "clear":
                    indeks = Integer.parseInt(i.pop().toString());
                    izbran = this.sekvenca.get(indeks);
                    while(!izbran.isEmpty()){
                        izbran.pop();
                    }
                    break;
                case "run": //????????????
                    indeks = Integer.parseInt(i.pop().toString());
                    Stack prepisi = new ArrayDeque();
                    Stack prvoten = this.sekvenca.get(indeks);
                    Stack prepisan = new ArrayDeque();
                    while (!prvoten.isEmpty()){prepisan.push(prvoten.top());prepisi.push(prvoten.pop());}
                    while (!prepisan.isEmpty()){prvoten.push(prepisan.pop());}
                    while (!prepisi.isEmpty()){procesiraj(String.valueOf(prepisi.pop()));}
                    break;
                case "loop": //???????????????
                    indeks = Integer.parseInt(i.pop().toString());
                    int ponovitve = Integer.parseInt(i.pop().toString());
                    Stack prepisi1 = new ArrayDeque();
                    Stack zacasni1 = this.sekvenca.get(indeks);

                    for (int xyz=0;xyz<ponovitve;xyz++) {
                        while (!zacasni1.isEmpty()) {
                            prepisi1.push(zacasni1.pop());
                        }
                        while (!prepisi1.isEmpty()) {
                            zacasni1.push(prepisi1.top());
                            procesiraj(prepisi1.pop().toString());
                        }
                    }
                    break;
                case "fun":
                    indeks = Integer.parseInt(i.pop().toString());
                    ponovitve = Integer.parseInt(i.pop().toString());
                    Stack funStack = this.sekvenca.get(indeks);
                    for (int xyz = 0;xyz<ponovitve;xyz++){
                        String prebrano = funScanner.next();
                        funStack.push(prebrano);
                    }
                    break;
                case "move":
                    indeks = Integer.parseInt(i.pop().toString());
                    ponovitve = Integer.parseInt(i.pop().toString());
                    for (int xyz = 0;xyz<ponovitve;xyz++){
                        sekvenca.get(indeks).push(i.pop().toString());
                    }
                    break;
                case "reverse":
                    Stack<String> zacasenX = new ArrayDeque<>();
                    Stack<String> zacasenY = new ArrayDeque<>();
                    indeks = Integer.parseInt(i.pop().toString());
                    Stack<String> obrnjen = this.sekvenca.get(indeks);
                    while(!obrnjen.isEmpty()){zacasenX.push(String.valueOf(obrnjen.pop())); }
                    while(!zacasenX.isEmpty()){zacasenY.push(zacasenX.pop()); }
                    while(!zacasenY.isEmpty()){obrnjen.push(zacasenY.pop()); }
                    break;
                default:
                    i.push(niz);
                    break;
            }
        }
    }
}
