# GameOfLife

Tekstipõhine Conway Game of Life

Autorid: Osvald Nigola, Leo-Martin Pala

---

## Kirjeldus

Conway Game of Life on matemaatiline mäng, kus on antud mängulaud, mis koosneb ruutudest, 
millest igaüks võib olla elus või surnud. Mängulaual tekib iga sammuga järgmine põlvkond, 
kus elusad ruudud jäävad ellu, kui neil on 2 või 3 elusat naabrit, muidu surevad. 
Surnud ruudud ärkavad ellu, kui neil on täpselt 3 elusat naabrit. 
Mängu "eesmärk" on leida algseis, mis tekitab huvitavaid mustreid.

## Kasutamine

Programmi käivitamisel saab vajutada ruutudel, et neid muuta elusaks või surnuks.
Mängu alustamiseks tuleb vajutada nuppu Alusta ja selle peatamiseks nuppu Peata.
Mängulaua algseisu (ehk kõik on surnud) saab luua nupuga Lähtesta.
Heleda ja tumeda režiimi vahel saab vahetada vastava nimega nupuga.
All vasakus nurgas on tuluke, mis näitab, kas mäng parajasti jookseb (roheline) või on peatatud (punane).

## Todo

- [x] Mängu loogika
- [x] Graafiline kasutajaliides
- [x] Mängu alustamine, peatamine, lähtestamine
- [x] Heleda ja tumeda režiimi vahetamine
- [ ] Mängu kiiruse reguleerimine
- [ ] Mängulaua suuruse muutmine
- [ ] Juhusliku algseisu genereerimine koos seatava tõenäosusega
- [ ] Mängu seadete salvestamine ja laadimine
- [ ] Mängu statistika