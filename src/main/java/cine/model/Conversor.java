package cine.model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor {
    final private List<Character> ABC =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().mapToObj(e -> (char)e).collect(Collectors.toList());
    private HashMap<Character, Integer> letraNumero = new HashMap<>();
    private HashMap<Integer, Character> numeroLetra = new HashMap<>();

    public Conversor() {
         armarHash(true);
         armarHash(false);
    }

    public void armarHash(boolean keyLetra) {
        for (Integer indice = 0; indice < this.ABC.size(); indice++) {
            if(keyLetra) {
                this.letraNumero.put(this.ABC.get(indice), indice); 
                continue;
            };
            this.numeroLetra.put(indice, this.ABC.get(indice));
        }
    }

    public int pasarLetraNumero(Character letra) {
        return letraNumero.get(letra);
    }

    public Character pasarNumeroLetra(int numero) {
        return numeroLetra.get(numero);
    }

}
