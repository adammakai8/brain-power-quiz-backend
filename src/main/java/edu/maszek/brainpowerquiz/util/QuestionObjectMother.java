package edu.maszek.brainpowerquiz.util;

import edu.maszek.brainpowerquiz.model.property.AnswerOption;
import edu.maszek.brainpowerquiz.model.entity.QuestionEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;

import java.util.List;
import java.util.Map;

public class QuestionObjectMother {

    // Using theme names a lot
    private static final String MATEMATIKA = "Matematika";
    private static final String TORTENELEM = "Történelem";
    private static final String FIZIKA = "Fizika";
    private static final String KEMIA = "Kémia";
    private static final String BIOLOGIA = "Biológia";
    private static final String FOLDRAJZ = "Földrajz";
    private static final String SPORT = "Sport";
    private static final String INFORMATIKA = "Informatika";
    private static final String ANGOL = "Angol";
    private static final String GASZTRONOMIA = "Gasztronómia";
    private static final String IRODALOM = "Irodalom";

    public static List<QuestionEntity> createQuestions(
            final QuestionRepository questionRepository,
            final Map<String, ThemePropertyEntity> themes
    ) {
        return questionRepository.insert(List.of(
                /* --- IRODALOM --- */
                createQuestion(
                        "Mikor született Petőfi Sándor?", 1,
                        List.of(
                                createAnswer("1817", false),
                                createAnswer("1859", false),
                                createAnswer("1901", false),
                                createAnswer("1823", true)
                        ),
                        List.of(themes.get(IRODALOM), themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Ki írta a \"Mama\" című verset?", 0,
                        List.of(
                                createAnswer("Petőfi Sándor", false),
                                createAnswer("Ady Endre", false),
                                createAnswer("Babits Mihály", false),
                                createAnswer("József Attila", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Ki nem külföldi az alábbiak közül?", 0,
                        List.of(
                                createAnswer("Shakespeare", false),
                                createAnswer("Goethe", false),
                                createAnswer("Tolsztoj", false),
                                createAnswer("Weöres Sándor", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Ki nem magyar az alábbiak közül?", 0,
                        List.of(
                                createAnswer("Ady Endre", false),
                                createAnswer("Radnóti Miklós", false),
                                createAnswer("Janus Pannonius", false),
                                createAnswer("Edgar Allen Poe", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Melyik regényben szerepel Mimó néni?", 2,
                        List.of(
                                createAnswer("Az aranyember", false),
                                createAnswer("Tüskevár", false),
                                createAnswer("Egri csillagok", false),
                                createAnswer("Abigél", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Kinek a nevéhez fűzödnek a \"Nagykőrösi balladák\"?", 1,
                        List.of(
                                createAnswer("Petőfi Sándor", false),
                                createAnswer("Jókai Mór", false),
                                createAnswer("Babits Mihály", false),
                                createAnswer("Arany János", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Melyik mű szereplője Egérkirály?", 1,
                        List.of(
                                createAnswer("Csipkerózsika", false),
                                createAnswer("Hattyúk tava", false),
                                createAnswer("Hamupipőke", false),
                                createAnswer("Diótörő", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Hol született Paulo Coelho, író?", 2,
                        List.of(
                                createAnswer("Athén", false),
                                createAnswer("Salvador", false),
                                createAnswer("Madrid", false),
                                createAnswer("Rio de Janeiro", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Kinek a műve a Galamb és béka?", 2,
                        List.of(
                                createAnswer("Csáth Géza", false),
                                createAnswer("Ottlik Géza", false),
                                createAnswer("Végh András", false),
                                createAnswer("Végh Antal", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Hol született Mikszáth Kálmán?", 1,
                        List.of(
                                createAnswer("Györköny", false),
                                createAnswer("Szabadka", false),
                                createAnswer("Pozsony", false),
                                createAnswer("Szklabonya", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Melyik olasz városba játszódik a Rómeó és Júlia?", 0,
                        List.of(
                                createAnswer("Róma", false),
                                createAnswer("Palermo", false),
                                createAnswer("Firenze", false),
                                createAnswer("Verona", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Mi az alliteráció?", 0,
                        List.of(
                                createAnswer("keresztrím", false),
                                createAnswer("bokorrím", false),
                                createAnswer("párosrím", false),
                                createAnswer("betűrím", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Kinek a regénye \"A párizsi Notre-Dame\"?", 1,
                        List.of(
                                createAnswer("Denis Diderot", false),
                                createAnswer("Albert Camus", false),
                                createAnswer("Jules Verne", false),
                                createAnswer("Victor Hugo", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Ki írta a \"Mester és Margarita\" művet?", 2,
                        List.of(
                                createAnswer("Dosztojevszkij", false),
                                createAnswer("Gogol", false),
                                createAnswer("Csehov", false),
                                createAnswer("Bulgakov", true)
                        ),
                        List.of(themes.get(IRODALOM))
                ),
                createQuestion(
                        "Hogy hívják az egyszerű irodistát a 19. századi cári Oroszországban?", 2,
                        List.of(
                                createAnswer("Basmacskin", false),
                                createAnswer("Gogol", false),
                                createAnswer("Tolsztoj", false),
                                createAnswer("Csinovnyik", true)
                        ),
                        List.of(themes.get(IRODALOM), themes.get(TORTENELEM))
                ),
                /* --- MATEMATIKA --- */
                createQuestion(
                        "Melyik szám páros?", 0,
                        List.of(
                                createAnswer("183", false),
                                createAnswer("19535", false),
                                createAnswer("1", false),
                                createAnswer("1900", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Melyik szám prím?", 1,
                        List.of(
                                createAnswer("183", false),
                                createAnswer("117", false),
                                createAnswer("1", false),
                                createAnswer("193", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Melyik síkidom NEM tengelyesen szimmetrikus?", 1,
                        List.of(
                                createAnswer("négyzet", false),
                                createAnswer("téglalap", false),
                                createAnswer("deltoid", false),
                                createAnswer("paralelogramma", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "a = 1, b = 2, c = 1. Mi a másodfokú egyenlet gyökei?", 2,
                        List.of(
                                createAnswer("1.333 és -0.666", false),
                                createAnswer("2 és 0", false),
                                createAnswer("4 és 6.1578", false),
                                createAnswer("-1 és -1", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Mi \"Püthagorasz\" vezetékneve?", 2,
                        List.of(
                                createAnswer("Rodoszi", false),
                                createAnswer("Metapontumi", false),
                                createAnswer("Athéni", false),
                                createAnswer("Szamoszi", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Mennyi 27+54 köbgyöke?", 1,
                        List.of(
                                createAnswer("0", false),
                                createAnswer("1", false),
                                createAnswer("2", false),
                                createAnswer("3", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "2+3+0+1-4-9?", 0,
                        List.of(
                                createAnswer("2", false),
                                createAnswer("-2", false),
                                createAnswer("0", false),
                                createAnswer("-7", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "15 / 3 maradéka?", 0,
                        List.of(
                                createAnswer("2", false),
                                createAnswer("-1", false),
                                createAnswer("1", false),
                                createAnswer("0", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Mennyi a szabályos nyolcszög belső szögeinek összege?", 1,
                        List.of(
                                createAnswer("720", false),
                                createAnswer("900", false),
                                createAnswer("1260", false),
                                createAnswer("1080", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "x kongruens 3 (mod 5). Mennyi a legkisebb 3 jegyű szám?", 2,
                        List.of(
                                createAnswer("104", false),
                                createAnswer("105", false),
                                createAnswer("106", false),
                                createAnswer("103", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "x határozatlan integráltja?", 2,
                        List.of(
                                createAnswer("(x^2)/2 + C", false),
                                createAnswer("(x^2)/3 + C", false),
                                createAnswer("(x^2)/1 + C", false),
                                createAnswer("(x^2)/2 + C", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Mennyi 500-nak a 25%-a?", 0,
                        List.of(
                                createAnswer("100", false),
                                createAnswer("200", false),
                                createAnswer("300", false),
                                createAnswer("125", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Mennyi 100*100?", 0,
                        List.of(
                                createAnswer("100", false),
                                createAnswer("0", false),
                                createAnswer("1000", false),
                                createAnswer("10000", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "Mi az y = 1/x függvény alakja?", 1,
                        List.of(
                                createAnswer("parabola", false),
                                createAnswer("lineáris", false),
                                createAnswer("szigmoid", false),
                                createAnswer("hiperbola", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                createQuestion(
                        "lg(x^2+x-6) = lg(1-x^2). Oldja meg az egyenletet!", 2,
                        List.of(
                                createAnswer("2 és 4", false),
                                createAnswer("4.312 és -4.123", false),
                                createAnswer("1.098 és 0.012", false),
                                createAnswer("2.137 és -1.637", true)
                        ),
                        List.of(themes.get(MATEMATIKA))
                ),
                /* --- FIZIKA --- */
                createQuestion(
                        "Egy autó 60 km/h-val halad. Mit tudunk a gyorsulásáról?", 1,
                        List.of(
                                createAnswer("gyorsul", false),
                                createAnswer("stagnál", false),
                                createAnswer("lassul", false),
                                createAnswer("nem tudjuk", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Mi a tömeg jele?", 0,
                        List.of(
                                createAnswer("r", false),
                                createAnswer("V", false),
                                createAnswer("A", false),
                                createAnswer("m", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Mivel osztjuk a tömeget, hogy megkapjuk a sűrűséget?", 0,
                        List.of(
                                createAnswer("felszín", false),
                                createAnswer("átmérő", false),
                                createAnswer("koncentráció", false),
                                createAnswer("térfogat", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "A trombita jobban hallható, mint a furulya. Mi a befolyásoló?", 1,
                        List.of(
                                createAnswer("frekvencia", false),
                                createAnswer("sebesség", false),
                                createAnswer("erő", false),
                                createAnswer("amplitúdó", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Honnan nézve a nagyobb? A Hold a Földről, vagy fordítva?", 1,
                        List.of(
                                createAnswer("Földről", false),
                                createAnswer("egyforma", false),
                                createAnswer("nem tudjuk", false),
                                createAnswer("Holdról", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Melyik a helyes összefüggés?", 1,
                        List.of(
                                createAnswer("1m/s=1km/h", false),
                                createAnswer("1m/s=10km/h", false),
                                createAnswer("3.6m/s=1km/h", false),
                                createAnswer("1m/s=3.6km/h", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Melyik erőhatás nyílvánul meg csak vonzásban?", 2,
                        List.of(
                                createAnswer("elektromos", false),
                                createAnswer("mágneses", false),
                                createAnswer("mechanikai", false),
                                createAnswer("gravitációs", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Mi idő mértékegység az alábbiak közül?", 0,
                        List.of(
                                createAnswer("gramm", false),
                                createAnswer("köbméter", false),
                                createAnswer("kilógramm", false),
                                createAnswer("secundum", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Ki mérte meg először a légnyomást?", 2,
                        List.of(
                                createAnswer("Pascal", false),
                                createAnswer("Newton", false),
                                createAnswer("Diesel", false),
                                createAnswer("Torricelli", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Mennyi az abszolút 0 kelvinben?", 1,
                        List.of(
                                createAnswer("-273", false),
                                createAnswer("-250", false),
                                createAnswer("-100", false),
                                createAnswer("0", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Hány Celsius fok a víz forráspontja?", 0,
                        List.of(
                                createAnswer("80", false),
                                createAnswer("90", false),
                                createAnswer("110", false),
                                createAnswer("100", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "Mi az akkumulátorok kapacitásának mértékegysége?", 2,
                        List.of(
                                createAnswer("watt", false),
                                createAnswer("volt", false),
                                createAnswer("farad", false),
                                createAnswer("amperóra", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                createQuestion(
                        "A szén 14-es izotópjának felezési ideje:", 2,
                        List.of(
                                createAnswer("21 óra", false),
                                createAnswer("37 nap", false),
                                createAnswer("418 év", false),
                                createAnswer("5736 év", true)
                        ),
                        List.of(themes.get(FIZIKA), themes.get(KEMIA))
                ),
                createQuestion(
                        "Ellenállás = feszültség / ...", 0,
                        List.of(
                                createAnswer("méter", false),
                                createAnswer("ellenállás", false),
                                createAnswer("tömeg", false),
                                createAnswer("áramerősség", true)
                        ),
                        List.of(themes.get(FIZIKA))
                ),
                /* --- KEMIA --- */
                createQuestion(
                        "Mi a szén vegyjele?", 0,
                        List.of(
                                createAnswer("Mg", false),
                                createAnswer("Na", false),
                                createAnswer("S", false),
                                createAnswer("C", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Mi a szamárium vegyjele?", 1,
                        List.of(
                                createAnswer("S", false),
                                createAnswer("As", false),
                                createAnswer("Sc", false),
                                createAnswer("Sm", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Ki találta fel a periódusos rendszert?", 1,
                        List.of(
                                createAnswer("Meyer", false),
                                createAnswer("Marie Currie", false),
                                createAnswer("Dalton", false),
                                createAnswer("Mengyelejev", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Ki fedezte fel a C-vitamint?", 0,
                        List.of(
                                createAnswer("Irinyi János", false),
                                createAnswer("Hevesy György", false),
                                createAnswer("Zemplén Géza", false),
                                createAnswer("Szent-Györgyi", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Hány atomból áll 1 vízmolekula?", 1,
                        List.of(
                                createAnswer("1", false),
                                createAnswer("2", false),
                                createAnswer("4", false),
                                createAnswer("3", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Milyen töltésű a proton?", 0,
                        List.of(
                                createAnswer("negatív", false),
                                createAnswer("semleges", false),
                                createAnswer("nem tudjuk", false),
                                createAnswer("pozitív", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Mi a koncentráció képlete?", 1,
                        List.of(
                                createAnswer("m/V", false),
                                createAnswer("m/n", false),
                                createAnswer("n/m", false),
                                createAnswer("n/V", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Mit jelöl az \"Fe\"?", 0,
                        List.of(
                                createAnswer("alumínium", false),
                                createAnswer("réz", false),
                                createAnswer("arany", false),
                                createAnswer("vas", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Mik alkotják az elektronfelhőt?", 0,
                        List.of(
                                createAnswer("protonok", false),
                                createAnswer("neutronok", false),
                                createAnswer("atomok", false),
                                createAnswer("elektronok", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Mi a metanol képlete?", 1,
                        List.of(
                                createAnswer("CH3-CH2-OH", false),
                                createAnswer("CH3-CH2-Cl", false),
                                createAnswer("CH4", false),
                                createAnswer("CH3-OH", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Hány szénatom alkotja a pirrol gyűrűt?", 2,
                        List.of(
                                createAnswer("3", false),
                                createAnswer("5", false),
                                createAnswer("7", false),
                                createAnswer("4", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Az alábbiak közül melyik purin bázis?", 2,
                        List.of(
                                createAnswer("citozin", false),
                                createAnswer("uracil", false),
                                createAnswer("timin", false),
                                createAnswer("guanin", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Melyik molekula diszacharid?", 2,
                        List.of(
                                createAnswer("fruktóz", false),
                                createAnswer("glukóz", false),
                                createAnswer("galaktóz", false),
                                createAnswer("laktóz", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Az alábbiak közül melyik telítetlen?", 2,
                        List.of(
                                createAnswer("propán", false),
                                createAnswer("bután", false),
                                createAnswer("oktán", false),
                                createAnswer("etén", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                createQuestion(
                        "Mihez viszonyítjuk a standard elektro potenciálokat?", 2,
                        List.of(
                                createAnswer("vas", false),
                                createAnswer("szén", false),
                                createAnswer("magnézium", false),
                                createAnswer("hidrogén", true)
                        ),
                        List.of(themes.get(KEMIA))
                ),
                /* --- BIOLOGIA --- */
                createQuestion(
                        "Milyen állat a kecskebéka?", 0,
                        List.of(
                                createAnswer("hüllő", false),
                                createAnswer("emlős", false),
                                createAnswer("madár", false),
                                createAnswer("kétéltű", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Hány lába van a póknak?", 0,
                        List.of(
                                createAnswer("2", false),
                                createAnswer("4", false),
                                createAnswer("6", false),
                                createAnswer("8", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Mivel ízlelünk?", 0,
                        List.of(
                                createAnswer("fog", false),
                                createAnswer("szem", false),
                                createAnswer("száj", false),
                                createAnswer("nyelv", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Hány ujjunk van két kezünkön?", 0,
                        List.of(
                                createAnswer("6", false),
                                createAnswer("8", false),
                                createAnswer("12", false),
                                createAnswer("10", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Melyik alszik téli álmot?", 0,
                        List.of(
                                createAnswer("ember", false),
                                createAnswer("róka", false),
                                createAnswer("vaddisznó", false),
                                createAnswer("sün", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Hány hallócsontunk van?", 1,
                        List.of(
                                createAnswer("0", false),
                                createAnswer("1", false),
                                createAnswer("2", false),
                                createAnswer("3", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Hány liter vérünk van?", 1,
                        List.of(
                                createAnswer("3", false),
                                createAnswer("4", false),
                                createAnswer("7", false),
                                createAnswer("5", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Hol található a prosztata?", 1,
                        List.of(
                                createAnswer("hugyhólyagban", false),
                                createAnswer("gyomor alatt", false),
                                createAnswer("herezacskóban", false),
                                createAnswer("hugyhólyag alat", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Hol élnek a jegesmedvék?", 1,
                        List.of(
                                createAnswer("Déli-sark", false),
                                createAnswer("Afrika", false),
                                createAnswer("Izland", false),
                                createAnswer("Északi-sark", true)
                        ),
                        List.of(themes.get(BIOLOGIA), themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Melyik fa örökzöld?", 1,
                        List.of(
                                createAnswer("fűzfa", false),
                                createAnswer("nyárfa", false),
                                createAnswer("vörösfenyő", false),
                                createAnswer("lucfenyő", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Mit jelent a \"gaster\" szó?", 2,
                        List.of(
                                createAnswer("agy", false),
                                createAnswer("lép", false),
                                createAnswer("vékonybél", false),
                                createAnswer("gyomor", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Honnan indul a kis vérkör?", 2,
                        List.of(
                                createAnswer("bal pitvar", false),
                                createAnswer("bal kamra", false),
                                createAnswer("jobb pitvar", false),
                                createAnswer("jobb kamra", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Melyik növény NEM évelő?", 2,
                        List.of(
                                createAnswer("zergevirág", false),
                                createAnswer("varjúháj", false),
                                createAnswer("veronika", false),
                                createAnswer("bojtocska", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Milyen állat a bölömbika?", 2,
                        List.of(
                                createAnswer("emlős", false),
                                createAnswer("kétéltű", false),
                                createAnswer("hüllő", false),
                                createAnswer("madár", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                createQuestion(
                        "Milyen tejfog NEM létezik?", 2,
                        List.of(
                                createAnswer("nagyörlő", false),
                                createAnswer("szemfog", false),
                                createAnswer("metszőfog", false),
                                createAnswer("kisörlő", true)
                        ),
                        List.of(themes.get(BIOLOGIA))
                ),
                /* --- FOLDRAJZ --- */
                createQuestion(
                        "Mi Magyarország fővárosa?", 0,
                        List.of(
                                createAnswer("Bukarest", false),
                                createAnswer("Párizs", false),
                                createAnswer("München", false),
                                createAnswer("Budapest", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Mi az USA fővárosa?", 0,
                        List.of(
                                createAnswer("Los Angeles", false),
                                createAnswer("San Francisco", false),
                                createAnswer("New York", false),
                                createAnswer("Washington", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Mi Csongrád-Csanád vármegye székhelye?", 0,
                        List.of(
                                createAnswer("Eger", false),
                                createAnswer("Békéscsaba", false),
                                createAnswer("Szolnok", false),
                                createAnswer("Szeged", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Melyik vármegye székhelye Szombathely?", 0,
                        List.of(
                                createAnswer("Veszprém", false),
                                createAnswer("Somogy", false),
                                createAnswer("Fejér", false),
                                createAnswer("Vas", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Mi Svájc (de facto) fővárosa?", 0,
                        List.of(
                                createAnswer("Tallinn", false),
                                createAnswer("Frankfurt", false),
                                createAnswer("München", false),
                                createAnswer("Bern", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Melyik NEM volt fővárosunk?", 1,
                        List.of(
                                createAnswer("Pozsony", false),
                                createAnswer("Budapest", false),
                                createAnswer("Esztergom", false),
                                createAnswer("Eger", true)
                        ),
                        List.of(themes.get(FOLDRAJZ), themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Hogy mondjuk a városiasodást?", 1,
                        List.of(
                                createAnswer("agglomeráció", false),
                                createAnswer("akkumuláció", false),
                                createAnswer("travenció", false),
                                createAnswer("urbanizáció", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Milyen hosszú az Amazonas?", 1,
                        List.of(
                                createAnswer("6745 km", false),
                                createAnswer("7315 km", false),
                                createAnswer("8002 km", false),
                                createAnswer("6992 km", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Melyik a Bükk-vidék legmagasabb csúcsa?", 1,
                        List.of(
                                createAnswer("Istállós-kő", false),
                                createAnswer("Kőris-hegy", false),
                                createAnswer("Tar-kő", false),
                                createAnswer("Szilvási-kő", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Mi a Börzsöny legmagasabb csúcsa?", 1,
                        List.of(
                                createAnswer("Varsa-tető", false),
                                createAnswer("Pogányvár", false),
                                createAnswer("Ajnácskő", false),
                                createAnswer("Csóványos", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Mi a geodézia?", 2,
                        List.of(
                                createAnswer("földméréstan", false),
                                createAnswer("növénytan", false),
                                createAnswer("csillagászat", false),
                                createAnswer("térképészet", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Mit jelent az orogenezis?", 2,
                        List.of(
                                createAnswer("földrengés", false),
                                createAnswer("vulkanizmus", false),
                                createAnswer("szétmállás", false),
                                createAnswer("hegyképződés", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Melyik a világ harmadik leghosszabb folyója?", 2,
                        List.of(
                                createAnswer("Ob", false),
                                createAnswer("Jenyiszej", false),
                                createAnswer("Nílus", false),
                                createAnswer("Jangce", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Milyen magas a Leány-hegy?", 2,
                        List.of(
                                createAnswer("778 méter", false),
                                createAnswer("945 méter", false),
                                createAnswer("972 méter", false),
                                createAnswer("892 méter", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                createQuestion(
                        "Melyik a 7. legnépesebb vármegyénk?", 2,
                        List.of(
                                createAnswer("Tolna", false),
                                createAnswer("Csongrád-Csanád", false),
                                createAnswer("Baranya", false),
                                createAnswer("Fejér", true)
                        ),
                        List.of(themes.get(FOLDRAJZ))
                ),
                /* --- ANGOL --- */
                createQuestion(
                        "Sam opened the window ..... looked out.", 0,
                        List.of(
                                createAnswer("or", false),
                                createAnswer("yet", false),
                                createAnswer("nor", false),
                                createAnswer("and", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "We went out .... the bad weather.", 0,
                        List.of(
                                createAnswer("although", false),
                                createAnswer("inspite", false),
                                createAnswer("inspite of", false),
                                createAnswer("despite", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "Hogy van az \"alma\" angolul?", 0,
                        List.of(
                                createAnswer("cherry", false),
                                createAnswer("egg", false),
                                createAnswer("pineapple", false),
                                createAnswer("apple", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "Melyik NEM a \"ház\" szót jelenti?", 0,
                        List.of(
                                createAnswer("house", false),
                                createAnswer("home", false),
                                createAnswer("place", false),
                                createAnswer("column", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "I ... a cup of coffee.", 0,
                        List.of(
                                createAnswer("eat", false),
                                createAnswer("swallow", false),
                                createAnswer("kill", false),
                                createAnswer("drink", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "The Tower of London is now used as", 1,
                        List.of(
                                createAnswer("a prison", false),
                                createAnswer("a residence", false),
                                createAnswer("a palace", false),
                                createAnswer("a museum", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "Big Ben is .... in London.", 1,
                        List.of(
                                createAnswer("a tower", false),
                                createAnswer("a statue", false),
                                createAnswer("a square", false),
                                createAnswer("a clock", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "The Bank of England is ...", 1,
                        List.of(
                                createAnswer("in Whitehall", false),
                                createAnswer("in Westminster", false),
                                createAnswer("near Hyde Park", false),
                                createAnswer("in the City", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "In 2012 London hosts its ... Olympics", 1,
                        List.of(
                                createAnswer("1st", false),
                                createAnswer("2nd", false),
                                createAnswer("4th", false),
                                createAnswer("3rd", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "What is the capital of Scotland?", 1,
                        List.of(
                                createAnswer("London", false),
                                createAnswer("Dublin", false),
                                createAnswer("Cardiff", false),
                                createAnswer("Edinburgh", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "How many sonnet has Shakespeare written?", 2,
                        List.of(
                                createAnswer("161", false),
                                createAnswer("132", false),
                                createAnswer("149", false),
                                createAnswer("154", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "Elizabeth the second was the ruler of England since ...?", 2,
                        List.of(
                                createAnswer("1956", false),
                                createAnswer("1948", false),
                                createAnswer("1944", false),
                                createAnswer("1952", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "Who were the previous two rulers of England?", 2,
                        List.of(
                                createAnswer("Edward the 8th, Victoria", false),
                                createAnswer("Victoria, Elizabeth 2nd", false),
                                createAnswer("George the 5th, Charles 3rd", false),
                                createAnswer("Goerge the 6th, Elizabeth 2nd", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "When was Paul McCartney knighted?", 2,
                        List.of(
                                createAnswer("1965", false),
                                createAnswer("1973", false),
                                createAnswer("1962", false),
                                createAnswer("1997", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                createQuestion(
                        "Who is the head of the Anglican church?", 2,
                        List.of(
                                createAnswer("the pope", false),
                                createAnswer("the bishop", false),
                                createAnswer("none of these", false),
                                createAnswer("the ruler", true)
                        ),
                        List.of(themes.get(ANGOL))
                ),
                /* --- TORTENELEM --- */
                createQuestion(
                        "Mikor kezdődött a honfoglalás?", 0,
                        List.of(
                                createAnswer("732", false),
                                createAnswer("777", false),
                                createAnswer("900", false),
                                createAnswer("895", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Hogy hívták az első magyar királyt?", 0,
                        List.of(
                                createAnswer("Kálmán", false),
                                createAnswer("Béla", false),
                                createAnswer("Árpád", false),
                                createAnswer("István", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Hány évig volt a török Magyarországon?", 0,
                        List.of(
                                createAnswer("140", false),
                                createAnswer("49", false),
                                createAnswer("900", false),
                                createAnswer("150", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mi volt Hitler rangja?", 0,
                        List.of(
                                createAnswer("uralkodó", false),
                                createAnswer("császár", false),
                                createAnswer("demokrata", false),
                                createAnswer("diktátor", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Hány ősmagyar vezérünk volt?", 0,
                        List.of(
                                createAnswer("3", false),
                                createAnswer("4", false),
                                createAnswer("6", false),
                                createAnswer("7", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mikor kezdődtek a nagy földrajzi felfedezések?", 1,
                        List.of(
                                createAnswer("17. század", false),
                                createAnswer("14. század", false),
                                createAnswer("16. század", false),
                                createAnswer("15. század", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Ki volt a második ameriaki elnök?", 2,
                        List.of(
                                createAnswer("George Washington", false),
                                createAnswer("Abraham Lincoln", false),
                                createAnswer("Thomas Jefferson", false),
                                createAnswer("John Adams", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Ki volt az első ameriaki elnök, akit meggyilkoltak?", 1,
                        List.of(
                                createAnswer("George Washington", false),
                                createAnswer("John F. Kennedy", false),
                                createAnswer("Thomas Jefferson", false),
                                createAnswer("Abraham Lincoln", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Ki volt a \"Napkirály\"?", 1,
                        List.of(
                                createAnswer("XII. Lajos", false),
                                createAnswer("IV. Henrik", false),
                                createAnswer("IX. Károly", false),
                                createAnswer("XIV. Lajos", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mikor volt az egyházszakadás?", 1,
                        List.of(
                                createAnswer("996", false),
                                createAnswer("1024", false),
                                createAnswer("1094", false),
                                createAnswer("1054", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mikor volt a mohácsi csata?", 1,
                        List.of(
                                createAnswer("1536", false),
                                createAnswer("1516", false),
                                createAnswer("1506", false),
                                createAnswer("1526", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Melyik volt egy német katonai hadművelet?", 2,
                        List.of(
                                createAnswer("Blitzkrieg", false),
                                createAnswer("Wermacht", false),
                                createAnswer("Wunderwaffe", false),
                                createAnswer("Barbarossa", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mikor jött létre a Pápai Állam?", 2,
                        List.of(
                                createAnswer("732", false),
                                createAnswer("1024", false),
                                createAnswer("812", false),
                                createAnswer("756", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mikor volt a visegrádi királytalálkozó?", 2,
                        List.of(
                                createAnswer("1453", false),
                                createAnswer("1305", false),
                                createAnswer("1392", false),
                                createAnswer("1335", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mettől meddig uralkodott V. László?", 2,
                        List.of(
                                createAnswer("1446-1453", false),
                                createAnswer("1458-1490", false),
                                createAnswer("1401-1446", false),
                                createAnswer("1453-1457", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Mikor írták alá a Bethlen-Peyer paktumot?", 2,
                        List.of(
                                createAnswer("1919.03.21.", false),
                                createAnswer("1920.06.04.", false),
                                createAnswer("1918.11.15.", false),
                                createAnswer("1921.12.22.", true)
                        ),
                        List.of(themes.get(TORTENELEM))
                ),
                /* --- INFORMATIKA --- */
                createQuestion(
                        "Mi a Microsoft cég webböngészője?", 0,
                        List.of(
                                createAnswer("Google Chrome", false),
                                createAnswer("Safari", false),
                                createAnswer("Firefox", false),
                                createAnswer("Edge", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Mennyi a 64 binárisa?", 0,
                        List.of(
                                createAnswer("10000", false),
                                createAnswer("1000100", false),
                                createAnswer("1010101", false),
                                createAnswer("100000", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Az alábbiak közül mi mágnes alapú tároló egység?", 0,
                        List.of(
                                createAnswer("CD", false),
                                createAnswer("DVD", false),
                                createAnswer("SSD", false),
                                createAnswer("HDD", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyik NEM az office csomag része?", 0,
                        List.of(
                                createAnswer("Word", false),
                                createAnswer("Excel", false),
                                createAnswer("Access", false),
                                createAnswer("Visual Studio", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyik bemeneti eszköz?", 0,
                        List.of(
                                createAnswer("monitor", false),
                                createAnswer("fejhallgató", false),
                                createAnswer("hangszóró", false),
                                createAnswer("egér", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyik gyengén típusos nyelv?", 1,
                        List.of(
                                createAnswer("C", false),
                                createAnswer("Java", false),
                                createAnswer("C#", false),
                                createAnswer("JavaScript", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Mikor épült az első programozható gép, a Z1?", 1,
                        List.of(
                                createAnswer("1944", false),
                                createAnswer("1933", false),
                                createAnswer("1957", false),
                                createAnswer("1938", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyeknél alkalmaznak veszteségmentes tömörítést?", 1,
                        List.of(
                                createAnswer("jpg", false),
                                createAnswer("mp3", false),
                                createAnswer("wma", false),
                                createAnswer("png", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Hálózati nyomtatás sebességét mi befolyásolja?", 1,
                        List.of(
                                createAnswer("tűzfal", false),
                                createAnswer("szerver típus", false),
                                createAnswer("átviteli idő", false),
                                createAnswer("elegendő RAM", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Állományok védelméhez melyiket érdemes használni?", 1,
                        List.of(
                                createAnswer("DOS", false),
                                createAnswer("FAT", false),
                                createAnswer("FAT32", false),
                                createAnswer("NTFS", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Mennyi 57 kettes komplemense?", 2,
                        List.of(
                                createAnswer("11110101", false),
                                createAnswer("01100111", false),
                                createAnswer("00111001", false),
                                createAnswer("11000111", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyik nyelv NEM kapcsos zárójelet {} használ a blokkokhoz?", 2,
                        List.of(
                                createAnswer("JavaScript", false),
                                createAnswer("Coyote", false),
                                createAnswer("R", false),
                                createAnswer("Python", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyik proceduráis nyelv?", 2,
                        List.of(
                                createAnswer("Prolog", false),
                                createAnswer("Haskell", false),
                                createAnswer("Occam", false),
                                createAnswer("C#", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "int x = 5; {int x = 10;} printf(\"%d\", x). Mi íródik ki?", 2,
                        List.of(
                                createAnswer("10", false),
                                createAnswer("RuntimeError", false),
                                createAnswer("Segmentation fault", false),
                                createAnswer("5", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                createQuestion(
                        "Melyik NEM dll kiterjesztés?", 2,
                        List.of(
                                createAnswer(".dll", false),
                                createAnswer(".sh", false),
                                createAnswer(".so", false),
                                createAnswer(".S", true)
                        ),
                        List.of(themes.get(INFORMATIKA))
                ),
                /* --- SPORT --- */
                createQuestion(
                        "Ki volt az első magyar, aki olimpiát nyert?", 0,
                        List.of(
                                createAnswer("Hosszú Katinka", false),
                                createAnswer("Darnyi Tamás", false),
                                createAnswer("Csik Ferenc", false),
                                createAnswer("Hajós Aldfréd", true)
                        ),
                        List.of(themes.get(SPORT), themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Melyik NEM olimpiai sportág?", 0,
                        List.of(
                                createAnswer("judo", false),
                                createAnswer("taekwando", false),
                                createAnswer("BMX", false),
                                createAnswer("nanbudo", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Hány játékos van egy kosár csapatban?", 0,
                        List.of(
                                createAnswer("8", false),
                                createAnswer("7", false),
                                createAnswer("6", false),
                                createAnswer("5", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Mi NINCS az öttusában?", 0,
                        List.of(
                                createAnswer("lovaglás", false),
                                createAnswer("vívás", false),
                                createAnswer("kerékpározás", false),
                                createAnswer("úszás", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Ki NEM focista?", 0,
                        List.of(
                                createAnswer("Lionel Messi", false),
                                createAnswer("Luis Suarez", false),
                                createAnswer("Neymar", false),
                                createAnswer("Lebron James", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Hány részből áll egy vízilabda meccs?", 1,
                        List.of(
                                createAnswer("2", false),
                                createAnswer("3", false),
                                createAnswer("6", false),
                                createAnswer("4", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Hány méterről dobják a kézilabda büntetőt?", 1,
                        List.of(
                                createAnswer("11", false),
                                createAnswer("9", false),
                                createAnswer("4", false),
                                createAnswer("7", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Mi lett az eredménye az 1953-s angol-magyar meccsnek?", 1,
                        List.of(
                                createAnswer("6:4", false),
                                createAnswer("2:2", false),
                                createAnswer("6:3", false),
                                createAnswer("3:6", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Milyen hosszú a hungaroring?", 1,
                        List.of(
                                createAnswer("3,9 km", false),
                                createAnswer("3,7 km", false),
                                createAnswer("6 km", false),
                                createAnswer("4,4 km", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Hol rendezték a 2016-s foci EB-t?", 1,
                        List.of(
                                createAnswer("Olaszország", false),
                                createAnswer("Magyarország", false),
                                createAnswer("Németország", false),
                                createAnswer("Franciaország", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Hányszoros Európa-bajnok Hosszú Katinka?", 2,
                        List.of(
                                createAnswer("11", false),
                                createAnswer("17", false),
                                createAnswer("9", false),
                                createAnswer("15", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Melyik csapat NINCS az MB1-ben? (2023)", 2,
                        List.of(
                                createAnswer("Fradi", false),
                                createAnswer("Honvéd", false),
                                createAnswer("Újpest", false),
                                createAnswer("Győr", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Milyen magasan van a darts tábla középpontja?", 2,
                        List.of(
                                createAnswer("170 cm", false),
                                createAnswer("167 cm", false),
                                createAnswer("164 cm", false),
                                createAnswer("173 cm", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                createQuestion(
                        "Ki volt a világ legelső nagydíjának győztese?", 2,
                        List.of(
                                createAnswer("Enzo Ferrari", false),
                                createAnswer("Tazio Nuvolari", false),
                                createAnswer("Pieteo Bordino", false),
                                createAnswer("Szisz Ferenc", true)
                        ),
                        List.of(themes.get(SPORT), themes.get(TORTENELEM))
                ),
                createQuestion(
                        "Milyen származású nyerte a legtöbb Grand Slamet (férfi 1)?", 2,
                        List.of(
                                createAnswer("spanyol", false),
                                createAnswer("szerb", false),
                                createAnswer("német", false),
                                createAnswer("svájci", true)
                        ),
                        List.of(themes.get(SPORT))
                ),
                /* --- GASZTRONOMIA --- */
                createQuestion(
                        "Melyik nem gyümölcs az alábbiak közül?", 0,
                        List.of(
                                createAnswer("paradicsom", false),
                                createAnswer("alma", false),
                                createAnswer("körte", false),
                                createAnswer("sárgarépa", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Mi nem kell a pörkölthöz?", 0,
                        List.of(
                                createAnswer("hagyma", false),
                                createAnswer("hús", false),
                                createAnswer("szalonna", false),
                                createAnswer("kukorica", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Melyik NEM egy almafajta?", 0,
                        List.of(
                                createAnswer("Golden", false),
                                createAnswer("Idared", false),
                                createAnswer("Jonatán", false),
                                createAnswer("Silver", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Milyen eredetű étel a csevapcsicsa?", 0,
                        List.of(
                                createAnswer("osztrák", false),
                                createAnswer("olasz", false),
                                createAnswer("horvát", false),
                                createAnswer("szerb", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Melyik országban esznek éti csigát?", 0,
                        List.of(
                                createAnswer("Németország", false),
                                createAnswer("Olaszország", false),
                                createAnswer("Horvátország", false),
                                createAnswer("Franciaország", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Mit jelent a \"puliszka\" szó?", 1,
                        List.of(
                                createAnswer("pörkölt", false),
                                createAnswer("ragu", false),
                                createAnswer("gulyás", false),
                                createAnswer("kása", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Melyik NEM híres magyar borvidék?", 1,
                        List.of(
                                createAnswer("Tokaj", false),
                                createAnswer("Pannon", false),
                                createAnswer("Balaton", false),
                                createAnswer("Zala", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Melyik hal tengeri?", 1,
                        List.of(
                                createAnswer("afrikai harcsa", false),
                                createAnswer("csuka", false),
                                createAnswer("fogassüllő", false),
                                createAnswer("makréla", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Mi NINCS a Songoku pizzán?", 1,
                        List.of(
                                createAnswer("sonka", false),
                                createAnswer("gomba", false),
                                createAnswer("kukorica", false),
                                createAnswer("szalámi", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Milyen nemzetiségű Gordon Ramsey?", 1,
                        List.of(
                                createAnswer("ír", false),
                                createAnswer("kanadai", false),
                                createAnswer("amerikai", false),
                                createAnswer("skót", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Mit jelent a \"flambírozás\"?", 2,
                        List.of(
                                createAnswer("sütés", false),
                                createAnswer("párolás", false),
                                createAnswer("grillezés", false),
                                createAnswer("felgyújtás", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Mi a \"guacamole\" fő összetevője?", 2,
                        List.of(
                                createAnswer("paradicsom", false),
                                createAnswer("padlizsán", false),
                                createAnswer("spenót", false),
                                createAnswer("avokádó", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Miből készült a \"lisztszita\"?", 2,
                        List.of(
                                createAnswer("Spanyolország", false),
                                createAnswer("Olaszország", false),
                                createAnswer("Brazília", false),
                                createAnswer("Mexikó", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Honnan származik az \"enchilada\"?", 2,
                        List.of(
                                createAnswer("damil", false),
                                createAnswer("spárga", false),
                                createAnswer("drót", false),
                                createAnswer("lószőr", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                ),
                createQuestion(
                        "Mi volt a 2009-es ország tortája?", 2,
                        List.of(
                                createAnswer("szilvagombóc", false),
                                createAnswer("szabolcsi almás", false),
                                createAnswer("madártejes", false),
                                createAnswer("pándi meggy", true)
                        ),
                        List.of(themes.get(GASZTRONOMIA))
                )
        ));
    }

    private static QuestionEntity createQuestion(
            final String text,
            final Integer difficulty,
            final List<AnswerOption> answers,
            final List<ThemePropertyEntity> themes
    ) {
        return QuestionEntity.builder()
                .text(text)
                .difficulty(difficulty)
                .answers(answers)
                .themes(themes)
                .build();
    }

    private static AnswerOption createAnswer(final String text, final boolean isCorrect) {
        return AnswerOption.builder()
                .text(text)
                .isCorrect(isCorrect)
                .build();
    }
}
