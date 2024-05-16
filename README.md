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
Mängu alustamiseks tuleb vajutada nuppu Alusta ja selle peatamiseks nuppu Peata või vajutama klaviatuuril nuppu p.
Mängulaua algseisu (ehk kõik on surnud) saab luua nupuga Lähtesta.
Heleda ja tumeda režiimi vahel saab vahetada seadetes vastava linnukese märkimisega.
All vasakus nurgas on tuluke, mis näitab, kas mäng parajasti jookseb (roheline) või on peatatud (punane).

## Klasside kirjeldus

### `GameOfLife`
Põhiklass, mis loob kõik komponendid ja panem mängu käima

### `Juhtimine`
vastutab mängu loogika ja kasutajaliidese juhtimise eest. See haldab mängu olekut, töötleb kasutaja sisendeid ja uuendab mängu ekraani.

#### `looNuppuKuulajad()`
Loob nuppudele kuulajad, mis haldavad nupuvajutusi

####  `käivitaMäng()`
Käivitab mängu, uuendades mängu olekut ja ekraani vastavalt mängu seadetele

#### `peataMäng()`
Peatab mängu, lõpetades mängu oleku uuendamise

#### `tegeleRuudulVajutusega(MouseEvent sündmus)`
Tegeleb ruudul vajutusega, muutes ruudu elusaks või surnuks

### `Juhtpaneel`
Vastutab mängu juhtpaneeli eest, kus on nupud mängu alustamiseks, peatamiseks, lähtestamiseks, valitud taustapildi taastamiseks ja seadete avamiseks

### `Laud`
Vastutab mängulaua eest, kus on ruudud ja nende elusoleku olek

#### `initsialiseeriLaud()`
Initsialiseerib mängulaua

#### `uuendaLaud()`
Uuendab mängulaua uute ruutudega

#### `uuendaDisplay()`
joonistab olemasolevad ruudud ekraanile

#### `setTaustapilt(File valitudPilt)`
joonistab valitud pildi mängulauale

#### `salvestaPilt(String failiNimi)`
salvestab mängulaua pildi faili

### `Main`
Klass, mis käivitab mängu

### `ManguOlek`
Klass, mis vastutab mängu järgmise oleku arvutamise eest

#### `järgmineOlek(BitSet eelmine, int lauaLaius, int lauaPikkus)`
Arvutab mängulaua järgmise oleku vastavalt eelmisele olekule ning tagastab selle

### `PildiImport`
Klass, mis vastutab pildi importimise eest

#### `piltToBitSet`
Muudab sisseloetud pildi bitsetiks

#### `keskmineHeledus()`
Arvutab pildi keskmise heleduse

### `Seaded`
Klass, mis hoiab mängu seadeid

### `SeadedAken`
Klass, mis vastutab seadete akna eest, mis tuleb ette, kui kasutaja vajutab nupule Seaded

#### `looSeadeKast()`
Loob seadekasti, kus on nupud seadete muutmiseks

#### `muudaRežiimi()`
Muudab heleda ja tumeda režiimi vahel

#### `avaPilt()`
Avab pildi importimise akna

### `ViganeStartVajutus`
Erind, mis visatakse, kui kasutaja vajutab nuppu Alusta, kui mäng juba käib

## Projekti tegemise protsessi kirjeldus
Alustasime projekti luues Githubi repositooriumi ja jagasime tööd. Esimese asjane tegime mängu loogika, siis algelise graafilise kasutajaliidese.
Sinna otsa lisalime juurde erinevaid funktsioone nagu pildi import, seaded, hele/tume režiim jne, kuiniks kood muutus liiga keeruliseks. 
Lõpuks muutsime koodi loetavamaks ja tegime viimaseid viimistlusi.

## Rühmaliikmete panus
Mõlemad rühmaliikmed panustasid võrdselt koodi kirjutamisse ja mõtlemisse.
Ajakulu mõlemal ca 20h

## tegemise mured
Lõpus muutus programm üsna keeruliseks. Kõik töötas, aga koodi oli palju ja oli keeruline aru saada, kus mingi viga on. 

## Hinnang lõpptulemusele
Lõpptulemus on meie arvates väga hea. Mäng töötab nii nagu peab ja on piisavalt funktsioone, et seda oleks mõnus mängida.

## Kuidas testiti
Iga vähegi suurema muudatuse korral oleme testinud, kas kõik programmi funktsionaalsused töötavad nii nagu nad peavad. Kui midagi ei töötanud, parandasime selle probleemi enne, kui edasi hakkasime koodi muutma.

## Todo

- [x] Mängu loogika
- [x] Graafiline kasutajaliides
- [x] Mängu alustamine, peatamine, lähtestamine
- [x] Heleda ja tumeda režiimi vahetamine
- [x] Hiirega lohistades ruutude elus/surnud seadmine
- [ ] Mängu kiiruse reguleerimine
- [ ] Mängulaua suuruse muutmine
- [ ] Juhusliku algseisu genereerimine koos seatava tõenäosusega
- [ ] Mängu seadete salvestamine ja laadimine
- [ ] Mängu statistika?

### Nõuetele vastavus
- [x] Klaviatuuri input (start/stop/lähtesta nt)
- [x] Erinditöötlus (kasutaja ekslik tegevus, nt start vajutamine kui juba käib)
- [x] Faili kirjutamine ja lugemine (seaded, statistika vms)
- [ ] Koodi kommenteerimine (osaliselt tehtud, tahaks põhjalikumalt)
- [ ] Rühmatöö põhjalikum kirjeldus README failis (klasside eesmärgid ja olulisemad meetodid, iga rühmaliikme panus ja ajakulu, tegemise mured, hinnang lõpptulemusele, kuidas testis
