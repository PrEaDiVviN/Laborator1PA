package com.company;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class GreedyAlgorithmTest {

    private List<Item> lista;
    private Map<Item, List<Item>> representation = new HashMap<>();
    public CatalogRepresentationGraph generateRandom(int bound) {
        var items = IntStream.rangeClosed(0, bound).mapToObj(i -> new Song("S" + i,"S"+ i,"//somelocation") ).toArray(Item[]::new);
        lista = new ArrayList<>();
        lista.addAll(Arrays.asList(items));
        ///initializam lista de legaturi
        for (Item it: lista) {
            representation.put(it,new ArrayList<>());
        }
        ///generam legaturile
        Random rand = new Random();
        for (Item it: lista) {
            int numarLeg = rand.nextInt(bound/10 + 1);
            int generateFirstIndex = rand.nextInt(bound);
            while(generateFirstIndex + numarLeg > bound)
                generateFirstIndex = rand.nextInt(bound);
           //adaugam legaturile care incep cu indexul generateFirstindex
            for (int i = generateFirstIndex; i < generateFirstIndex + numarLeg; i++) {
                ///itemul este adaugat in ambele liste
                representation.get(it).add(items[i]);
                representation.get(items[i]).add(it);
            }
        }
        Catalog cat = new Catalog();
        cat.setItems(lista);
        CatalogRepresentationGraph catRep = new CatalogRepresentationGraph(cat);
        catRep.setRepresentation(representation);
        return catRep;
    }

    @org.junit.jupiter.api.Test
    public Solution solve(CatalogRepresentationGraph graph) {
        StringBuilder sol = new StringBuilder("");
        ///la fiecare pas, alegem numarul maxim de iteme (fix unul din fiecare legatura)
        List<Item> chosen = new ArrayList<>();
        int zi = 1;
        while(graph.getCatalog().getItems().size()!=0) { // cat timp lista de elemente nu este goala, continuam
            for (Item it: graph.getCatalog().getItems()) {
                boolean poateFiAdaugat = true;
                for (Item legatura : graph.getRepresentation().get(it))
                    if (chosen.contains(legatura)) {
                        poateFiAdaugat = false;
                        break;
                    }
                if(poateFiAdaugat)
                    chosen.add(it);
            }
            sol.append("Iteme ce vor fi vizualizate in ziua ");
            sol.append(zi);
            sol.append(" --------------------------------\n");
            for (Item it: chosen) {
                sol.append(it.getName());
                sol.append("\n");
            }
            graph.getCatalog().getItems().removeAll(chosen); //stergem elementele din lista de elemente
            chosen = new ArrayList<>(); //reinitializam
            zi = zi + 1;
        }
        return new Solution(sol);
    }
}