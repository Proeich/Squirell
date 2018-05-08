package PROG2_SS2018.Aufgabe1;

public class Engine {

    public static void main(String[] args) throws Exception{
        Engine en = new Engine();
        en.run();
    }

    private Brett br = new Brett();
    Player[] players;
    public Engine(){
    }

    public void run() throws Exception{

        players = new Player[2];
        //players = new player[3];
        players[0] = new Player("Spieler1", 'o');
        players[1] = new Player("Spieler2", 'x');
        //players[2] = new player("Spieler3", 'y');

        while(true){
            for(Player p:players){
                br.insert(p.askMovement(),p);
                if (br.gewonnen(p)){
                    System.out.println(br.toString());
                    System.out.println(p.toString() + " hat Gewonnen!");
                    System.exit(1);
                }
                System.out.println(br.toString());
            }
        }
    }

    //Funktionen zum Testen

    public void getStone(int input, int stein){}

    public void showBrett(char[][] input){
        int index1 = 0;
        int index2 = 0;
        for (int i = 1; i<br.feld.length;i++) {
            System.out.print(i);
        }
        System.out.println();
        for(char[] k: input){
            for(char n: k){
                System.out.print(n);
                index2++;
            }
            System.out.println();
            index1++;
        }
        for (int i = 1; i<br.feld.length;i++) {
            System.out.print(i);
        }
        System.out.println();
    }

}

class Player{
    private String name;
    private char stein;
    public Player(){}
    public Player(String name, char stein){
        this.name = name;
        this.stein = stein;
    }

    public int askMovement() throws Exception{
        int k = 0;
        while(true) {
            System.out.print("An welcher Stelle wollen sie eine Stein einwerfen?" + '\n' +
                    "Der Bereich ist 1 - 8" + '\n');
            k = System.in.read() - 48;
            if(k >= 1 && k <= 8){
                break;
            }
            System.out.println("Dies war keine gültige Eingabe!" + '\n');
        }

        return k;
    }

    @Override
    public String toString() {
        return name;
    }

    public char getStein() {
        return stein;
    }
}

class Brett {

    int anzahlSpielerSteine; // wie viele Steine schon platziert wurden
    int maxX = 8; // Breite des Feldes
    int maxY = 8; // Höhe des Feldes
    char[][] feld = new char[maxY][maxX]; // das Feld
    char platzhalter = '♥'; // was dort im Array liegt, wo keine Spielersteine liegen
    public Brett()
    {
        anzahlSpielerSteine = 0;
        for(int i = 0; i < maxY; i++)
        {
            for(int k = 0; k < maxX; k++)
            {
                feld[i][k] = platzhalter;
            }
        }
    }

    public boolean gewonnen(Player spieler)
    {
        if(anzahlSpielerSteine >= 7) // Es müssen natürlich mindestens 7 Steine im Spiel sein, damit jemand gewinnen kann
        {
            char stein = spieler.getStein();
            for(int i = maxY-1; i >= 0; i--) // Es wird sich (von unten) jede Zeile angeschaut
            {
                int s = 0; // wie viele Steine liegen schon in der Zeile?
                int ss = 0; // wie viele Steine des Spielers liegen schon hintereinander in der Zeile?
                for(int k = 0; k < maxX; k++) // die Zeile wird Zeichen für Zeichen durchlaufen
                {
                    if(feld[i][k] != platzhalter) // Spielerstein oder Platzhalter?
                    {
                        s++; // Steine, Spielerunabhängig zählen (für die Zeile)
                        if(feld[i][k] == stein)
                        {
                            //System.out.println("X detected");
                            ss++;
                            if(ss == 4) // bedeutet: 4 Steine in einer Reihe
                            {
                                return true;
                            }
                            if(s>=3) // liegen vor der aktuellen Stelle nur Platzhalter, kann man dort keine Steine aufstapeln
                            { // man muss also nicht prüfen, ob nach links oben diagonal Steine liegen
                                // theoretisch könnte man diesen Teil in eine extra Funktion auslagern, da der Code reduntant ist
                                // bis auf die Stelle feld[k-u][i-u]
                                int sd = 1; // Anzahl diagonaler Steine
                                for (int u = 1; u<=4; u++)
                                {
                                    if(feld[i-u][k-u] == stein)
                                    {
                                        sd ++;
                                    }
                                    else
                                        break;
                                    if(sd == 4)
                                        return true;
                                }
                            } // hier das Ganze dann nochmal für diagonal nach rechts oben...
                            // davor bitte Prüfen, ob nach Rechts noch nmidnestens 3 Steine Platz ist
                            if(maxX-k >= 3)
                            {
                                int sd = 1; // Anzahl diagonaler Steine
                                for (int u = 1; u<=4; u++)
                                {
                                    if(feld[i-u][k+u] == stein)
                                    {
                                        sd ++;
                                    }
                                    else
                                        break;
                                    if(sd == 4)
                                        return true;
                                }
                            }
                            // dann sollte man natürlich noch prüfen, ob nach oben vier Steine liegen...
                            // davor natürlich noch prüfen, ob überhaupt genug Platz nach Oben ist!
                            if(maxY- i < 3)
                            {
                                //System.out.println("nach oben ist noch Platz");
                                int so = 1;
                                for(int u = 1; u <4; u++)
                                {
                                    if(feld[i - u][k] == stein)
                                        so ++;
                                    //System.out.println(so);
                                }
                                if(so == 4)
                                    return true;
                            }
                        }
                        else
                            ss = 0; // Diese Variable zählt ja die aneinanderliegenden Steine vom bestimmten Spieler

                    }
                }

            }

        }
        return false;
    }

    public void insert(int x, Player spieler)
    {
        --x;
        if(x >= 0 && x < maxX)
        {
            if(feld[0][x] != platzhalter)
            {
                System.out.println("This column is already full!");
                // alternativ: man könnte hier auch das Spielfeld erweitern
            }
            else
            {
                for(int i = maxY -1; i >= 0; i--)
                {
                    if(feld[i][x] == platzhalter)
                    {
                        feld[i][x] = spieler.getStein();
                        break;
                    }
                }
                anzahlSpielerSteine++;
            }
        }
    }
    public String toString()
    {
        String s = "";
        String skala = " ";
        for(int i = 0; i<maxY; i++)
        {
            s += i + 1;
            for(int k = 0; k < maxX; k++)
            {
                if(i == maxY-1)
                    skala += (k+1);
                s += feld[i][k];
            }
            s+= "\n";
        }
        s = skala +"\n" +  s + skala;
        return s;
    }

}

