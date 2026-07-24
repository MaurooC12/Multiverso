package modelo;

import grafo.Grafo;

public class DatosCivilizaciones {

    public static Grafo cargarGrafo() {
        Grafo grafo = new Grafo();

        // ============================================================
        // 1. CREAR CIVILIZACIONES
        // ============================================================
        
        // Europa Occidental
        grafo.agregarNodo(new Civilizacion("Britanos", "Defensiva", "Media", 80, 80, "Catedral"));
        grafo.agregarNodo(new Civilizacion("Francos", "Ofensiva", "Media", 90, 70, "Catedral de San Vito"));
        grafo.agregarNodo(new Civilizacion("Godos", "Ofensiva", "Fácil", 60, 50, "Mausoleo de Teodorico"));
        grafo.agregarNodo(new Civilizacion("Celtas", "Ofensiva", "Fácil", 55, 45, "Roca de Cashel"));
        grafo.agregarNodo(new Civilizacion("Vikingos", "Ofensiva", "Fácil", 60, 50, "Iglesia de Borgund"));
        grafo.agregarNodo(new Civilizacion("Teutones", "Defensiva", "Difícil", 100, 120, "Abadía de Maria Laach"));

        // Europa del Este / Mediterráneo
        grafo.agregarNodo(new Civilizacion("Italianos", "Defensiva", "Media", 85, 85, "Catedral de Milán"));
        grafo.agregarNodo(new Civilizacion("Bizantinos", "Defensiva", "Difícil", 110, 130, "Santa Sofía"));
        grafo.agregarNodo(new Civilizacion("Eslavos", "Ofensiva", "Media", 85, 70, "Catedral de San Basilio"));
        grafo.agregarNodo(new Civilizacion("Magiares", "Ofensiva", "Fácil", 65, 55, "Castillo de Hunyad"));
        grafo.agregarNodo(new Civilizacion("Hunos", "Ofensiva", "Fácil", 70, 50, "Arco de Constantino"));
        grafo.agregarNodo(new Civilizacion("Turcos", "Ofensiva", "Media", 90, 75, "Mezquita Azul"));

        // Medio Oriente / Norte de África
        grafo.agregarNodo(new Civilizacion("Españoles", "Ofensiva", "Media", 85, 70, "Alhambra"));
        grafo.agregarNodo(new Civilizacion("Portugueses", "Defensiva", "Difícil", 95, 105, "Torre de Belém"));
        grafo.agregarNodo(new Civilizacion("Bereberes", "Ofensiva", "Fácil", 65, 55, "Torre Hasán"));
        grafo.agregarNodo(new Civilizacion("Sarracenos", "Ofensiva", "Media", 85, 70, "Gran Mezquita de Samarra"));
        grafo.agregarNodo(new Civilizacion("Persas", "Ofensiva", "Difícil", 105, 100, "Taq Kasra"));
        grafo.agregarNodo(new Civilizacion("Mongoles", "Ofensiva", "Fácil", 70, 55, "Gran Tienda de Gengis Kan"));

        // África Subsahariana / India
        grafo.agregarNodo(new Civilizacion("Malíes", "Defensiva", "Media", 80, 85, "Gran Mezquita de Djenné"));
        grafo.agregarNodo(new Civilizacion("Etíopes", "Ofensiva", "Media", 85, 70, "Iglesia de San Jorge"));
        grafo.agregarNodo(new Civilizacion("Indostaníes", "Ofensiva", "Media", 90, 75, "Tumba de Humayun"));
        grafo.agregarNodo(new Civilizacion("Gurjaras", "Ofensiva", "Media", 85, 70, "Templo de Somnath"));
        grafo.agregarNodo(new Civilizacion("Bengalíes", "Defensiva", "Difícil", 95, 105, "Somapura Mahavihara"));
        grafo.agregarNodo(new Civilizacion("Dravídicos", "Ofensiva", "Media", 85, 70, "Templo Brihadiśvara"));

        // Sureste Asiático
        grafo.agregarNodo(new Civilizacion("Chinos", "Defensiva", "Media", 85, 90, "Templo del Cielo"));
        grafo.agregarNodo(new Civilizacion("Japoneses", "Ofensiva", "Fácil", 65, 55, "Tōdai-ji"));
        grafo.agregarNodo(new Civilizacion("Coreanos", "Defensiva", "Difícil", 95, 105, "Templo de Bulguksa"));
        grafo.agregarNodo(new Civilizacion("Vietnamitas", "Defensiva", "Media", 80, 85, "Templo de la Literatura"));
        grafo.agregarNodo(new Civilizacion("Jemer", "Defensiva", "Difícil", 100, 110, "Angkor Wat"));
        grafo.agregarNodo(new Civilizacion("Malayos", "Ofensiva", "Fácil", 60, 50, "Templo de Kalasan"));
        grafo.agregarNodo(new Civilizacion("Birmanos", "Ofensiva", "Media", 80, 65, "Pagoda de Shwezigon"));

        // América
        grafo.agregarNodo(new Civilizacion("Aztecas", "Ofensiva", "Media", 85, 70, "Templo Mayor"));
        grafo.agregarNodo(new Civilizacion("Mayas", "Defensiva", "Media", 80, 85, "Chichén Itzá"));
        grafo.agregarNodo(new Civilizacion("Incas", "Defensiva", "Media", 80, 85, "Machu Picchu"));
        grafo.agregarNodo(new Civilizacion("Mapuche", "Ofensiva", "Fácil", 60, 50, "Pucará de Quitor"));
        grafo.agregarNodo(new Civilizacion("Muisca", "Defensiva", "Media", 75, 80, "Templo del Sol"));

        // ============================================================
        // 2. CONEXIONES (TABLA DEFINITIVA)
        // ============================================================
        
        // Europa Occidental
        grafo.agregarConexion("Britanos", "Francos");
        grafo.agregarConexion("Britanos", "Celtas");
        grafo.agregarConexion("Britanos", "Vikingos");
        grafo.agregarConexion("Britanos", "Eslavos");

        grafo.agregarConexion("Francos", "Teutones");
        grafo.agregarConexion("Francos", "Godos");
        grafo.agregarConexion("Francos", "Españoles");

        grafo.agregarConexion("Teutones", "Italianos");
        grafo.agregarConexion("Teutones", "Bizantinos");

        grafo.agregarConexion("Italianos", "Bizantinos");
        grafo.agregarConexion("Italianos", "Españoles");
        grafo.agregarConexion("Italianos", "Portugueses");

        grafo.agregarConexion("Bizantinos", "Turcos");
        grafo.agregarConexion("Bizantinos", "Magiares");
        grafo.agregarConexion("Bizantinos", "Persas");

        grafo.agregarConexion("Turcos", "Sarracenos");
        grafo.agregarConexion("Turcos", "Bereberes");

        grafo.agregarConexion("Sarracenos", "Persas");

        grafo.agregarConexion("Persas", "Mongoles");
        grafo.agregarConexion("Persas", "Gurjaras");

        grafo.agregarConexion("Mongoles", "Chinos");
        grafo.agregarConexion("Mongoles", "Sarracenos");

        grafo.agregarConexion("Chinos", "Japoneses");
        grafo.agregarConexion("Chinos", "Coreanos");
        grafo.agregarConexion("Chinos", "Vietnamitas");

        grafo.agregarConexion("Japoneses", "Malayos");
        grafo.agregarConexion("Japoneses", "Vikingos");

        grafo.agregarConexion("Malayos", "Birmanos");
        grafo.agregarConexion("Malayos", "Vietnamitas");

        grafo.agregarConexion("Birmanos", "Indostaníes");
        grafo.agregarConexion("Birmanos", "Jemer");

        grafo.agregarConexion("Indostaníes", "Gurjaras");
        grafo.agregarConexion("Indostaníes", "Persas");

        grafo.agregarConexion("Gurjaras", "Bengalíes");
        grafo.agregarConexion("Gurjaras", "Dravídicos");
        grafo.agregarConexion("Gurjaras", "Malíes");

        grafo.agregarConexion("Malíes", "Portugueses");

        grafo.agregarConexion("Bereberes", "Sarracenos");
        grafo.agregarConexion("Bereberes", "Malíes");
        grafo.agregarConexion("Bereberes", "Españoles");

        grafo.agregarConexion("Españoles", "Portugueses");

        grafo.agregarConexion("Aztecas", "Mayas");
        grafo.agregarConexion("Aztecas", "Incas");
        grafo.agregarConexion("Aztecas", "Españoles");

        grafo.agregarConexion("Incas", "Mapuche");
        grafo.agregarConexion("Incas", "Etíopes");

        grafo.agregarConexion("Mapuche", "Muisca");
        grafo.agregarConexion("Mapuche", "Aztecas");
        grafo.agregarConexion("Mapuche", "Españoles");

        grafo.agregarConexion("Muisca", "Españoles");
        grafo.agregarConexion("Muisca", "Mayas");
        grafo.agregarConexion("Muisca", "Aztecas");

        grafo.agregarConexion("Mayas", "Mapuche");
        grafo.agregarConexion("Mayas", "Incas");

        grafo.agregarConexion("Vikingos", "Eslavos");
        grafo.agregarConexion("Vikingos", "Malayos");

        grafo.agregarConexion("Celtas", "Vikingos");
        grafo.agregarConexion("Celtas", "Eslavos");

        grafo.agregarConexion("Eslavos", "Magiares");
        grafo.agregarConexion("Eslavos", "Teutones");
        grafo.agregarConexion("Eslavos", "Mongoles");

        grafo.agregarConexion("Magiares", "Hunos");
        grafo.agregarConexion("Magiares", "Turcos");

        grafo.agregarConexion("Hunos", "Mongoles");
        grafo.agregarConexion("Hunos", "Godos");
        grafo.agregarConexion("Hunos", "Persas");

        grafo.agregarConexion("Godos", "Vikingos");
        grafo.agregarConexion("Godos", "Bizantinos");
        grafo.agregarConexion("Godos", "Portugueses");

        grafo.agregarConexion("Portugueses", "Bereberes");
        grafo.agregarConexion("Portugueses", "Etíopes");

        grafo.agregarConexion("Etíopes", "Malíes");
        grafo.agregarConexion("Etíopes", "Sarracenos");

        grafo.agregarConexion("Dravídicos", "Indostaníes");
        grafo.agregarConexion("Dravídicos", "Etíopes");
        grafo.agregarConexion("Dravídicos", "Bengalíes");

        grafo.agregarConexion("Bengalíes", "Jemer");
        grafo.agregarConexion("Bengalíes", "Birmanos");
        grafo.agregarConexion("Bengalíes", "Indostaníes");

        grafo.agregarConexion("Jemer", "Malayos");
        grafo.agregarConexion("Jemer", "Chinos");

        grafo.agregarConexion("Vietnamitas", "Jemer");

        grafo.agregarConexion("Coreanos", "Vietnamitas");
        grafo.agregarConexion("Coreanos", "Japoneses");
        grafo.agregarConexion("Coreanos", "Britanos");

        return grafo;
    }
}