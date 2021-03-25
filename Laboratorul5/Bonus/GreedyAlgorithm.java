package com.company;

import java.util.ArrayList;
import java.util.List;

public class GreedyAlgorithm extends Algorithm{

    @Override
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
