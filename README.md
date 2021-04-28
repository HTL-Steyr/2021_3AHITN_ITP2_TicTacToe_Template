# TicTacToe

## Beschreibung
Es gilt das Spiel TicTacToe zu programmieren. Das Spiel soll anschließend über das Netzwerk (Sockets) spielbar sein.
Zwischen Server und Client werden die Daten über ein geeignetes, eigens erfundenes Protokoll übertragen.

Die Clients stellen die Daten nur dar. Das heißt also, die gesamte Spiellogik / der gesamte Spielablauf findet serverseitig statt.
Der Server ist also für die Ermittlung des Gewinners, Steuerung des Spielflusses, Fehlerbehandlung, usw... zuständig.

Die Clients dienen somit NUR zur Darstellung des Spielfelds.

## Protokoll
Überlegen Sie sich ein Protokoll zum Nachrichtenaustausch zwischen Client und Server.

### Protokollvorschlag:
Jede Nachricht zwischen Server und Client dient einem eindeutig zuordenbarem Grund. Diese "Gründe" können durch Nummern / Status dargestellt werden.
So könnten zwei Nummernkreise eingeführt werden, z.B. Nachrichten die der Server an den Client schickt, befinden sich im Intervall [100; 199].
Nachrichten, die ein Client an den Server schickt, liegen im Nummernkreis [200;299]. So ist jeder Nachrichtentyp durch eine bestimmte Nummer zu Identifizieren.

Ein Einfaches Beispiel wäre:

   1) Ein Client verbindet sich auf den Server. Der Server vergibt automatisch und zufällig die "Namen" der Spieler. Der soeben verbundene Client spielt als Spieler "O". 
      Die zugehörige Nachricht vom Server an den Client könnte wie folgt aussehen: <code>"100;O"</code>.
   2) Ein weiterer Client verbindet sich auf den Server. Dieser bekommt also den Spieler "X" zugewiesen.
      Die zugehörige Nachricht vom Server an den Client könnte wie folgt aussehen:  <code>"100;X"</code>.
   3) Nun ist das Spiel spielbereit. Es wird der Spieler zufällig gewählt, der beginnen darf. Der Spieler "X" darf beginnen.
      Die zugehörige Nachricht vom Server die Clients könnte wie folgt aussehen:  <code>"101;X"</code>.
   4) Spieler "X" macht einen Zug und setzt die Markierung ins mittlere Feld (2. Reihe / 2. Spalte, dh. 5. Feld). 
      Die Nachricht vom Spieler "X" an den Server könnte wie folgt aussehen:  <code>"200;X;5"</code>.
   5) Der Server schickt nun das derzeitige Spielfeld an die Clients.
      Die zugehörige Nachricht vom Server an die Clients könnte wie folgt aussehen:  <code>"110;-;-;-;-;X;-;-;-;-"</code>.
   6) Nun schickt der Server aus, welcher Spieler als nächstes die Markierung machen darf:
      Die zugehörige Nachricht vom Server an die Clients könnte wie folgt aussehen:  <code>"101;O"</code>.
   7) Spieler "O" macht einen Zug und setzt die ebenfalls Markierung ins mittlere Feld (2. Reihe / 2. Spalte, dh. 5. Feld).
      Die Nachricht vom Spieler "O" an den Server könnte wie folgt aussehen:  <code>"200;O;5</code>".      
   8) Nun registriert der Server, dass das Feld bereits belegt ist. Er sendet einen Fehler an den Client.
      Die zugehörige Nachricht vom Server an den Client könnte wie folgt aussehen:  <code>"102;O;5"</code>.
   9) Spieler "O" setzt die Markierung nun ins erste Feld (1. Reihe / 1. Spalte, dh. 1. Feld).
      Die Nachricht vom Spieler "O" an den Server könnte wie folgt aussehen:  <code>"200;O;1"</code>.
   10) Der Server schickt nun das derzeitige Spielfeld an die Clients.
      Die zugehörige Nachricht vom Server an die Client könnte wie folgt aussehen:  <code>"110;O;-;-;-;X;-;-;-;-"</code>.
   11) Nun schickt der Server aus, welcher Spieler als nächstes die Markierung machen darf:
      Die zugehörige Nachricht vom Server an den Client könnte wie folgt aussehen:  <code>"101;X"</code>.
   12) ...
   13) Sobald das Spiel vorüber ist und ein Sieger feststeht (nehmen wir "X" an), wird ebenfalls eine Nachricht mit dem Gewinner ausgeschickt.
       Die zugehörige Nachricht vom Server an die Client könnte wie folgt aussehen:  <code>"120;X"</code>.
       Sollte Spieler "O" gewinnen, könnte die Nachricht wie folgt lauten:  <code>"120;O"</code>.
       Wenn das Spiel in einem Unentschieden Endet, könnte die Nachricht wie folgt lauten:  <code>"120;-"</code>. 


Dieses Protokoll dient nur zur Erklärung. Sie können Ihr Protokoll nach Belieben umsetzen. 
Ebenso beinhaltet der obenstehende Nachrichtenverlauf nur minimale Fehlerbehandlung. 
Ein Beispiel für einen weiteren Fehler wäre: Was soll passieren, wenn ein Spieler einen Zug macht, der nicht an der Reihe ist?

       
## GUI
Erstellen Sie eine GUI zum Spiel "TicTacToe" über welche der Client bedient wird. 


## Zu beachten
Beachten Sie folgende Punkte:
   1) Formatierung Ihres Codes und Einhaltung der Code Conventions (Namensgebung für Klassen, Variablen, Methoden, ...)
   2) Arbeiten Sie mit Getter- & Settermethoden
   3) Arbeiten Sie mit Konstruktoren
   4) Verwenden Sie Konstanten für die Status und ein passendes Klassensystem für zentrale Aufgaben (setzen eines Feldes, Konvertieren der Nachrichten, auf Sieger prüfen, ...)
   5) Ausschließlich der Server speichert Daten vom Spiel (Spielfeld, Spielstand, welcher Spieler ist an der Reihe, etc...) 
   6) Der gesamte Spielverlauf wird über den Server gesteuert
   7) Die Clients / die GUI enthält nur die Logik zur Darstellung des Spielfelds (welche Sie als Nachricht übermittelt bekommen)

## Zeitplan
   * 28.04.2021 Angabe
   * 03.05.2021 - 09.05.2021 Protokoll für Datenaustausch
   * 10.05.2021 - 16.05.2021 Umsetzung Protokoll am Server + Umsetzung Protokoll am Client + Regelung Spielfluss
   * 17.05.2021 - 23.05.2021 Erstellung der GUI und Darstellung des Spielfelds, Abgabe am 23.05.2021 am Ende der Stunde
   
